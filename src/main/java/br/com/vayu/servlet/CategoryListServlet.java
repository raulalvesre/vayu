package br.com.vayu.servlet;

import br.com.vayu.dao.CategoryDAO;
import br.com.vayu.models.Category;
import br.com.vayu.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listaCategorias")
public class CategoryListServlet extends HttpServlet {

    private final CategoryDAO categoryDAO;

    public CategoryListServlet() {
        EntityManager em = JPAUtil.getEntityManager();
        categoryDAO = new CategoryDAO(em);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryDAO.findAllInOrder();

        RequestDispatcher rd = request.getRequestDispatcher("/categoryList.jsp");
        request.setAttribute("categories", categories);
        rd.forward(request, response);
    }

}
