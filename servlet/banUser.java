package servlet;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/banUser")
public class banUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username=req.getParameter("inputName");
        if (username!=null && username.equals("admin")){
            resp.sendRedirect(req.getContextPath()+"/root.jsp?message=Access Denied!");
        }
        if (new UserDao().deleteUser(username)){
            resp.sendRedirect(req.getContextPath()+"/root.jsp?message=Ok");
        }
    }
}
