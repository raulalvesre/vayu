package br.com.vayu.servlet;

import br.com.vayu.dao.CategoryDAO;
import br.com.vayu.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/categorias/desativar/*")
public class DeactivateCategoryServlet extends HttpServlet {

    private final CategoryDAO categoryDAO;

    public DeactivateCategoryServlet() {
        EntityManager em = JPAUtil.getEntityManager();
        categoryDAO = new CategoryDAO(em);
    }

    @Override
    protected void doDelete(HttpServletRequest req,
                            HttpServletResponse resp) {
        int categoryId = Integer.parseInt(req.getPathInfo().replace("/", ""));

        categoryDAO.deactivateCategory(categoryId);

        resp.setStatus(204);
    }

}
