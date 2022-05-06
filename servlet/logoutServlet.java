package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class logoutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(true);
        session.removeAttribute("username");
        session.removeAttribute("id");
        session.removeAttribute("revenue");
        session.removeAttribute("p1");
        session.removeAttribute("p2");
        session.removeAttribute("p3");
        session.removeAttribute("p4");
        session.removeAttribute("p5");
        resp.sendRedirect(req.getContextPath()+"/index.jsp");
    }
}
