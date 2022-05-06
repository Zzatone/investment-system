package servlet;

import bean.Share;
import dao.ShareDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/SearchShareServlet")
public class SearchShareServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        ArrayList<Share> shares=new ArrayList<>();
        shares=new ShareDao().getShareByPartId(id);
        HttpSession session=req.getSession(true);
        session.setAttribute("partshares",shares);
        resp.sendRedirect(req.getContextPath()+"/SearchPage.jsp");
    }
}
