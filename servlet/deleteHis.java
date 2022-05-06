package servlet;

import dao.SubandHisDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteHis")
public class deleteHis extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(true);
        String name= (String) session.getAttribute("username");
        String id_user= (String) session.getAttribute("id");
        String idshare=req.getParameter("idshare");
        boolean flag=new SubandHisDAO().delete_history(id_user,idshare);
        if (flag){
            resp.sendRedirect(req.getContextPath()+"/His_Page.jsp?message=Succeefully");
        }else {
            req.setAttribute("message", "更新失败!");
            req.getRequestDispatcher("/SharePage.jsp").forward(req, resp);
        }

    }
}
