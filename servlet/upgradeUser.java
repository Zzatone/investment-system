package servlet;

import bean.Email;
import dao.EmailDao;
import dao.UserDao;
import util.sendEmail;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/upgradeUser")
public class upgradeUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        Email email=new EmailDao().selectEmail_byname(name);
        String title="恭喜你";
        String respone="你已经通过审查，成为了高级会员。";
        boolean flag_1=new UserDao().updatepow_super(name);
        boolean flag_2=new UserDao().updatemes_super(name);
        if(flag_1 && flag_2){
            try {
                if(email.getEmail()!=null){
                    boolean flag=new sendEmail().SendEmail_Upgrade(email,title,respone);
                }
                resp.sendRedirect(req.getContextPath()+"/root.jsp?message=Successfully&link=message_manage");
            } catch (MessagingException messagingException) {
                messagingException.printStackTrace();
            }
        }else {
            req.setAttribute("message", "失败!");
            req.getRequestDispatcher("/root.jsp?message=Fail!").forward(req, resp);
        }
    }
}
