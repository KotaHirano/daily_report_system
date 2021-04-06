package controllers.favorite;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Favorite;
import utils.DBUtil;

/**
 * Servlet implementation class FavoriteIndexServet
 */
@WebServlet("/favorite/index")
public class FavoriteIndexServet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteIndexServet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();
        //お気に入りのリストを作成
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
        }

        List<Favorite> favorites = em.createNamedQuery("getAllFavorites", Favorite.class)
                .setParameter("employee_id", (Employee) request.getSession().getAttribute("login_employee"))
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

     // 全件数を取得
        long favorite_count = (long) em.createNamedQuery("getFvoritesCount", Long.class)
                .setParameter("employee_id", (Employee) request.getSession().getAttribute("login_employee"))
                .getSingleResult();

        em.close();

        System.out.println("*************************************************");
        System.out.println(favorites);
        System.out.println("*****************************************************");

        request.setAttribute("favoritelist", favorites);
        request.setAttribute("favorite_count", favorite_count); // 全件数
        request.setAttribute("page", page); // ページ数

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/favorite/favlist.jsp");
        rd.forward(request, response);
    }

}
