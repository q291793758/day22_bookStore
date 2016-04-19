package cn.itcast.web.controller;

import cn.itcast.domain.Category;
import cn.itcast.domain.User;
import cn.itcast.factory.ServiceFactory;
import cn.itcast.service.BussinessService;
import cn.itcast.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    BussinessService service = null;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service = ServiceFactory.getInstance().createBussinessService((User) request.getSession().getAttribute("user"));

        String method = request.getParameter("method");
        if ("getAll".equalsIgnoreCase(method)) {
            getAll(request, response);
        }
        if ("add".equalsIgnoreCase(method)) {
            add(request, response);
        }
        if ("delete".equalsIgnoreCase(method)) {
            delete(request, response);
        }
        if ("update".equalsIgnoreCase(method)) {
            update(request, response);
        }
        if ("updateUI".equalsIgnoreCase(method)) {
            updateUI(request, response);
        }
    }

    private void updateUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        String id = request.getParameter("id");
        Category category = service.findCategoryById(id);
        request.setAttribute("category", category);
        }catch (SecurityException e) {
            request.setAttribute("message", "您没有权限执行此操作! catch by SecurityException");
        } catch (Exception e) {
            if (e.getCause() instanceof ServletException) {
                request.setAttribute("message", "您没有权限执行此操作! catch by Exception");
            } else {
                request.setAttribute("message", "添加分类失败!");
            }
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Category category = WebUtils.request2Bean(request, Category.class);
            service.updateCategory(category);
            request.setAttribute("message", "修改成功!");
        } catch (SecurityException e) {
            request.setAttribute("message", "您没有权限执行此操作! catch by SecurityException");
        } catch (Exception e) {
            if (e.getCause() instanceof ServletException) {
                request.setAttribute("message", "您没有权限执行此操作! catch by Exception");
            } else {
                request.setAttribute("message", "添加分类失败!");
            }
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            service.deleteCategory(id);
            request.setAttribute("message", "删除成功!");
        } catch (SecurityException e) {
            request.setAttribute("message", "您没有权限执行此操作! catch by SecurityException");
        } catch (Exception e) {
            if (e.getCause() instanceof ServletException) {
                request.setAttribute("message", e.getMessage()+"catch by Exception");
            } else {
                request.setAttribute("message", "添加分类失败!");
            }
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Category category = WebUtils.request2Bean(request, Category.class);
            category.setId(UUID.randomUUID().toString());
            service.addCategory(category);
            request.setAttribute("message", "成功添加分类!");
        } catch (SecurityException e) {
            request.setAttribute("message", e.getMessage()+"catch by SecurityException");
        } catch (Exception e) {
            if (e.getCause() instanceof ServletException) {
                request.setAttribute("message", "您没有权限执行此操作! catch by Exception");
            } else {
                request.setAttribute("message", "添加分类失败!");
            }
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        List<Category> categories = service.getAllCategory();
        request.setAttribute("categories", categories);
        }catch (SecurityException e) {
            request.setAttribute("message", e.getMessage()+"!catch by SecurityException");
            request.getRequestDispatcher("/message.jsp").forward(request,response);
            return;
        } catch (Exception e) {
            if (e.getCause() instanceof ServletException) {
                request.setAttribute("message", e.getMessage()+"! catch by Exception");

            } else {
                request.setAttribute("message", "查看失败!");

            }
            request.getRequestDispatcher("/message.jsp").forward(request,response);
            return;
        }
       request.getRequestDispatcher("/manag/listcategory.jsp").forward(request,response);
    }


}
