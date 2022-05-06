package servlet;

import dao.SubandHisDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteHisAll")
public class deleteHisAll extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(true);
        String name= (String) session.getAttribute("username");
        String id_user= (String) session.getAttribute("id");
        boolean flag=new SubandHisDAO().delete_history_all(id_user);
        if (flag){
            resp.sendRedirect(req.getContextPath()+"/His_Page.jsp?message=Succeefully");
        }else {
            req.setAttribute("message", "更新失败!");
            req.getRequestDispatcher("/SharePage.jsp").forward(req, resp);
        }
    }
}
