package servlet;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/updatePassword")
public class updatePassword extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pw= (String) req.getParameter("pw");
        HttpSession session=req.getSession(true);
        String name= (String) session.getAttribute("username");
        boolean flag=new UserDao().updatePassword(name,pw);
        if (flag){
            resp.sendRedirect(req.getContextPath()+String.format("/UserPage.jsp?message=%s", URLEncoder.encode("更新成功！", StandardCharsets.UTF_8)));
        }else {
            req.setCharacterEncoding("utf-8");
            req.setAttribute("message","更新失败，请重试");
            req.getRequestDispatcher("UserPage.jsp").forward(req,resp);
        }
    }
}
