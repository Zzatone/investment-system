package servlet;

import dao.ForContentDAO;
import dao.ForTitleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/ForumIssue")
public class ForumIssue extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession(true);
        String content=req.getParameter("content");
        String name= (String) session.getAttribute("username");
        String title=req.getParameter("title");
        int idforum=new ForTitleDAO().insert_title(name,title);
        boolean flag=new ForContentDAO().insert_master(content,name,idforum);
        if(flag){
            resp.sendRedirect(req.getContextPath()+"/forum.jsp?message="+ URLEncoder.encode("发布成功", StandardCharsets.UTF_8));
        }else {
            req.setAttribute("message", "更新失败!");
            req.getRequestDispatcher("/forum.jsp?").forward(req, resp);
        }
    }
}
