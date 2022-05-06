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

@WebServlet("/updateEmail")
public class updateEmail extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method=req.getParameter("way");
        HttpSession session=req.getSession(true);
        String name= (String) session.getAttribute("username");
        String email_temp=req.getParameter("email");
        String email;
        switch (method){
            case "1" : email = email_temp+"@qq.com";break;
            case "2" : email = email_temp+"@126.com";break;
            case "3" : email = email_temp+"@163.com";break;
            default:
                throw new IllegalStateException("Unexpected value: " + method);
        }
        boolean flag=new UserDao().updateEmail(name,email);
        if (flag){
            resp.sendRedirect(req.getContextPath()+String.format("/UserPage.jsp?message=%s", URLEncoder.encode("更新成功！", StandardCharsets.UTF_8)));
        }else {
            req.setCharacterEncoding("utf-8");
            req.setAttribute("message","更新失败，请重试");
            req.getRequestDispatcher("UserPage.jsp").forward(req,resp);
        }
    }
}
