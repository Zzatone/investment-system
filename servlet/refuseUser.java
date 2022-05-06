package servlet;

import bean.Email;
import bean.History;
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

@WebServlet("/refuseUser")
public class refuseUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        Email email=new EmailDao().selectEmail_byname(name);
        String title="很遗憾";
        String respone="你的申请未被批准，请联系管理员后再做尝试！";
        boolean flag_2=new UserDao().updatemes_super(name);
        if(flag_2){
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
