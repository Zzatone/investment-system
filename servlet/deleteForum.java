package servlet;

import bean.History;
import dao.ForTitleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteForum")
public class deleteForum extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idforum=Integer.valueOf(req.getParameter("idforum"));
        boolean flag=new ForTitleDAO().delete_title(idforum);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/Myforum.jsp?message=Update Successfully");
        }else {
            req.setAttribute("message", "更新失败!");
            req.getRequestDispatcher("/Myforum.jsp").forward(req, resp);
        }
    }
}
