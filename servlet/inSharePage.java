package servlet;

import bean.History;
import bean.Share;
import dao.HistoryDao;
import dao.POIDao;
import dao.ShareDao;
import dao.SubandHisDAO;
import util.LR;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/inSharePage")
public class inSharePage extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(true);
        String idshare=req.getParameter("idshare");
        String iduser= (String) session.getAttribute("id");
        String username= (String) session.getAttribute("username");
        Share share=new ShareDao().getShareById(idshare);
        History history=new HistoryDao().selectHis(idshare);
        Double pre=new LR().LR_six(history);
        Double P=share.getP();
        if (username!=null){
            boolean flag=new POIDao().updatePOI(P,username);
            boolean flag1=new SubandHisDAO().insert_history(iduser,idshare);
        }
        session.setAttribute("share",share);
        session.setAttribute("history",history);
        session.setAttribute("D1",history.getD4());
        session.setAttribute("D2",history.getD3());
        session.setAttribute("D3",history.getD2());
        session.setAttribute("D4",history.getD1());
        session.setAttribute("D5",history.getNewday());
        session.setAttribute("pre",pre);
        resp.sendRedirect(req.getContextPath()+"/SharePage.jsp?idshare="+idshare);
    }
}
