package controllers.favorite;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Favorite;
import utils.DBUtil;

/**
 * Servlet implementation class FavoriteDestroyServlet
 */
@WebServlet("/favorite/destroy")
public class FavoriteDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // セッションスコープからお気に入りのIDを取得して
        System.out.println("*************************************************");
        Favorite f = (Favorite) request.getSession().getAttribute("favorite");
        System.out.println(f.getId());
        System.out.println("*****************************************************");


        // 該当のIDのお気に入りをデータベースから取得
        EntityManager em = DBUtil.createEntityManager();
        Favorite m = em.find(Favorite.class, f.getId());

        em.getTransaction().begin();
        em.remove(m); // データ削除
        em.getTransaction().commit();

        em.close();

        // セッションスコープ上の不要になったデータを削除
        request.getSession().removeAttribute("favorite");

        //日報一覧にもどる
        response.sendRedirect(request.getContextPath() + "/reports/index");
    }

}
