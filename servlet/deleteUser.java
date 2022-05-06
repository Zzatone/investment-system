package servlet;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class deleteUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String name= (String) req.getSession().getAttribute("username");
        req.getSession().removeAttribute("username");
        req.getSession().removeAttribute("id");
        req.getSession().removeAttribute("revenue");
        req.getSession().removeAttribute("p1");
        req.getSession().removeAttribute("p2");
        req.getSession().removeAttribute("p3");
        req.getSession().removeAttribute("p5");
        req.getSession().removeAttribute("p4");
        boolean flag=new UserDao().deleteUser(name);
        if (flag){
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
        }else {
            req.setAttribute("message","失败");
            req.getRequestDispatcher(req.getContextPath()+"/UserPage.jsp?link=password_delete").forward(req,resp);
        }
    }
}
