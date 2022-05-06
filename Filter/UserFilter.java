package Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        String url=request.getRequestURI();
        if (url.contains("/UserPage.jsp") && request.getSession().getAttribute("username")==null){
            response.sendRedirect("login.jsp");
        }else if (request.getSession().getAttribute("username")==null && url.contains("/SharePage.jsp")){
            response.sendRedirect("login.jsp");
        }else if(request.getSession().getAttribute("username")==null && url.contains("/Sub_page.jsp")){
            response.sendRedirect("login.jsp");
        }else if (request.getSession().getAttribute("username")==null && url.contains("/forum.jsp")){
            response.sendRedirect("login.jsp");
        }
        else {
            filterChain.doFilter(request,response);
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
