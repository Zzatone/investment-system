package servlet;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/changePower")
public class changepower extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name= (String) req.getSession().getAttribute("username");
        int pow= (int) req.getSession().getAttribute("pow");
        HttpSession session=req.getSession(true);
        if(pow==1){
            boolean flag=new UserDao().updatemes(name);
            if (flag){
                resp.sendRedirect(req.getContextPath()+"/UserPage.jsp?message=Waiting for processing");
            }else {
                req.setAttribute("message", "更新失败!");
                req.getRequestDispatcher("/UserPage.jsp").forward(req, resp);
            }
        }else {
            boolean flag=new UserDao().updatepow_normal(name);
            if (flag){
                session.setAttribute("pow",1);
                resp.sendRedirect(req.getContextPath()+"/UserPage.jsp?message=Update successfully!");
            }else {
                req.setAttribute("message", "更新失败!");
                req.getRequestDispatcher("/UserPage.jsp").forward(req, resp);
            }
        }
    }
}
