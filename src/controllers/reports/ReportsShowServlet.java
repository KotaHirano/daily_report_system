package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;
import models.Favorite;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        request.setAttribute("report", r);
        request.setAttribute("_token", request.getSession().getId());

        //レポートのidをセッションに保存する
        HttpSession session = request.getSession();
        session.setAttribute("favreport", r.getId());

        //レポートがお気に入りに追加されているか調べる
        Favorite f = null;
        try {
            f = em.createNamedQuery("checkfavorite", Favorite.class)
                  .setParameter("employee_id", ((Employee)request.getSession().getAttribute("login_employee")).getId())
                  .setParameter("report_id", r.getId())
                  .getSingleResult();
        } catch(NoResultException ex) {}


        request.setAttribute("favorite", f); //お気に入り

        //favexistにお気に入りの有無を入れてリクエストスコープに保存

        //fの中身
        /*
        System.out.println("*************************************************");
        System.out.println(f.getId());
        System.out.println("*************************************************");
        */



        em.close();


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);
    }

}