package cn.itcast.web.controller;

import cn.itcast.domain.Category;
import cn.itcast.service.impl.BussinessServiceImpl;
import cn.itcast.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CategoryServlet extends HttpServlet {
    private  BussinessServiceImpl service = new BussinessServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("getAll".equalsIgnoreCase(method)) {
            getAll(request,response);
        }if ("add".equalsIgnoreCase(method)) {
            add(request,response);
        }if ("delete".equalsIgnoreCase(method)) {
            delete(request,response);
        }if ("update".equalsIgnoreCase(method)) {
            update(request,response);
        }if ("updateUI".equalsIgnoreCase(method)) {
            updateUI(request,response);
        }
    }

    private void updateUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Category category = service.findCategoryById(id);
         request.setAttribute("category",category);
        request.getRequestDispatcher("./updateCategory.jsp").forward(request,response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Category category = WebUtils.request2Bean(request, Category.class);
            service.updateCategory(category);
            request.setAttribute("message","修改成功!");
        } catch (Exception e) {
            request.setAttribute("message","修改失败!!");
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            service.deleteCategory(id);
            request.setAttribute("message","删除成功!");
        } catch (Exception e) {
            request.setAttribute("message","删除失败!");
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Category category = WebUtils.request2Bean(request, Category.class);
            category.setId(UUID.randomUUID().toString());
            service.addCategory(category);
            request.setAttribute("message","成功添加分类!");
        } catch (Exception e) {
            request.setAttribute("message","添加分类失败!");
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/message.jsp").forward(request,response);
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = service.getAllCategory();
        request.setAttribute("categories",categories);
        request.getRequestDispatcher("./listcategory.jsp").forward(request,response);
    }


}
