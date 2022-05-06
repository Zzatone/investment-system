package servlet;

import dao.ForContentDAO;
import util.sendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/RequestForum")
public class RequestForum extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession(true);
        String content=req.getParameter("content");
        int idforum=Integer.parseInt(req.getParameter("idforum"));
        String name= (String) session.getAttribute("username");
        boolean flag=new ForContentDAO().insert_request(content,name,idforum);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/forum_Page.jsp?message=Update Successfully&idforum="+idforum);
        }else {
            req.setAttribute("message", "更新失败!");
            req.getRequestDispatcher("/forum_Page.jsp?message=Update Successfully&idforum="+idforum).forward(req, resp);
        }
    }
}
