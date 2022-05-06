package servlet;

import bean.User;
import dao.UserDao;
import util.sendEmail;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class addUserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=new User();
        String name=req.getParameter("username");
        String pw=req.getParameter("pw");
        String revenue=req.getParameter("revenue");
        user.setName(name);
        user.setPassword(pw);
        user.setRevenue(revenue);
        boolean flag=new UserDao().addUser(user);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/login.jsp?message=Registered Successfully!");
        }else {
            req.setAttribute("message","注册失败！");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }
    }
}
