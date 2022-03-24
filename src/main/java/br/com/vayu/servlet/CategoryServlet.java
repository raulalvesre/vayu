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
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = JPAUtil.getEntityManager();
        CategoryDAO categoryDAO = new CategoryDAO(em);

        List<Category> categories = categoryDAO.findAll();

        RequestDispatcher rd = request.getRequestDispatcher("/categoriesList.jsp");
        request.setAttribute("categories", categories);
        rd.forward(request, response);
    }

}
