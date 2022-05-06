package servlet;

import dao.ShareDao;
import util.sendEmail;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateShare")
public class updateShare extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            boolean flag;
            flag=new ShareDao().updateShare();
            if(flag){
                boolean send=new sendEmail().SendEmail_Update();
                resp.sendRedirect(req.getContextPath()+"/root.jsp?message=Update Successfully");
            }else {
                req.setAttribute("message", "更新失败!");
                req.getRequestDispatcher("/root.jsp").forward(req, resp);
            }
        } catch (MessagingException messagingException) {
                messagingException.printStackTrace();
        }
    }
}
