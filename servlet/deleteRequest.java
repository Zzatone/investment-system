package servlet;

import bean.History;
import dao.ForContentDAO;
import dao.ForTitleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteRequest")
public class deleteRequest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idcontent=Integer.valueOf(req.getParameter("idcontent"));
        boolean flag=new ForContentDAO().delete_content(idcontent);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/MyRequest.jsp?message=Update Successfully");
        }else {
            req.setAttribute("message", "更新失败!");
            req.getRequestDispatcher("/MyRequest.jsp").forward(req, resp);
        }
    }
}
