package controllers.favorite;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
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
 * Servlet implementation class FavoriteCreateServlet
 */
@WebServlet("/favorite/create")
public class FavoriteCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();


        HttpSession session = ((HttpServletRequest)request).getSession();
        Employee e = (Employee)session.getAttribute("login_employee");
        Report r = (Report) session.getAttribute("favreport");

        // Favoriteのインスタンスを生成
        Favorite f = new Favorite();

     // fの各フィールドにデータを代入
        f.setEmployee(e);

        f.setReport(r);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        f.setMadeday(currentTime);



        em.getTransaction().begin();
        em.persist(f);
        em.getTransaction().commit();
        em.close();

        //日報一覧にもどる
        response.sendRedirect(request.getContextPath() + "/reports/index");
    }

}
