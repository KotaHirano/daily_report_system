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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        List<Favorite> favorites = em.createNamedQuery("getAllFavorites", Favorite.class).getResultList();

        em.close();

        request.setAttribute("favorite", favorites);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/favorite/favlist.jsp");
        rd.forward(request, response);
    }

}
