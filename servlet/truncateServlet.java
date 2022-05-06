package servlet;

import dao.TruncateDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/truncate")
public class truncateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        if (new TruncateDao().truncateDatabase()){
            resp.sendRedirect(req.getContextPath()+"/root.jsp?message=清空表操作成功！");
        }
    }
}
