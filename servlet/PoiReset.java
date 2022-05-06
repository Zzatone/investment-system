package servlet;

import dao.POIDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/PoiReset")
public class PoiReset extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(true);
        String name= (String) session.getAttribute("username");
        boolean flag=new POIDao().reset_poi(name);
        if (flag){
            resp.sendRedirect(req.getContextPath()+"/UserPage.jsp?message=Successfully");
        }else {
            req.setAttribute("message", "更新失败!");
            req.getRequestDispatcher("/root.jsp").forward(req, resp);
        }
    }
}
