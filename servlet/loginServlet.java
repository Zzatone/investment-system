package servlet;

import bean.POI;
import bean.User;
import dao.UserDao;
import util.sendEmail;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=new User();
        POI poi=new POI();
        String username=req.getParameter("username");
        String pw=req.getParameter("pw");
        HttpSession userSession=req.getSession(true);
        if("admin".equals(username)&&"admin".equals(pw)){
            userSession.setAttribute("username",username);
            resp.sendRedirect(req.getContextPath()+"/root.jsp");
            return;
        }
        user.setId(String.valueOf(new UserDao().select_id(username)));
        user.setName(username);
        user.setPassword(pw);
        user.setRevenue(new UserDao().select_User_rev(username));
        int pow=new UserDao().selectpow(username);
        boolean flag =new UserDao().checkValid(user);
        if(flag){
            userSession.setAttribute("username", user.getName());
            userSession.setAttribute("id", user.getId());
            userSession.setAttribute("revenue", user.getRevenue());
            userSession.setAttribute("p1", user.getP1());
            userSession.setAttribute("p2", user.getP2());
            userSession.setAttribute("p3", user.getP3());
            userSession.setAttribute("p4", user.getP4());
            userSession.setAttribute("p5", user.getP5());
            userSession.setAttribute("pow",pow);
            resp.sendRedirect(req.getContextPath()+"/index.jsp?username="+user.getName());
        }
        else {
            req.setAttribute("message","账号或密码错误，请重新输入");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
}
