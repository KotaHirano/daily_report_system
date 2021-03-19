package controllers.favorite;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Favorite;
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

        // Favoriteのインスタンスを生成
        Favorite f = new Favorite();

     // fの各フィールドにデータを代入
        Integer employee = 1984;
        f.setEmoloyee(employee);

        Integer report = 1;
        f.setReport(report);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        f.setMadeday(currentTime);

        em.close();
    }

}
