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

@WebServlet("/categorias/formulario/*")
public class CategoryFormServlet extends HttpServlet {

    private final CategoryDAO categoryDAO;

    public CategoryFormServlet() {
        EntityManager em = JPAUtil.getEntityManager();
        categoryDAO = new CategoryDAO(em);
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() != null) {
            int categoryId = Integer.parseInt(req.getPathInfo().replace("/", ""));
            Category category = categoryDAO.findById(categoryId);
            req.setAttribute("category", category);
        }

        RequestDispatcher rd = req.getRequestDispatcher("/categoryForm.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        String idStr = req.getParameter("id");
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String studyGuide = req.getParameter("studyGuide");
        String activeSwitchValue = req.getParameter("active");
        String orderStr = req.getParameter("order");
        String iconPath = req.getParameter("iconPath");
        String colorCode = req.getParameter("colorCode");

        boolean active = activeSwitchValue != null;
        int order = !orderStr.isBlank() ? Integer.parseInt(orderStr) : 0;
        int id = !idStr.isBlank() ? Integer.parseInt(idStr) : 0;

        Category category = new Category(id,
                code,
                name,
                description,
                studyGuide,
                active,
                order,
                iconPath,
                colorCode);

        if (id == 0) {
            categoryDAO.create(category);
        } else {
            categoryDAO.update(category);
        }

        resp.sendRedirect("/listaCategorias");
    }

}
