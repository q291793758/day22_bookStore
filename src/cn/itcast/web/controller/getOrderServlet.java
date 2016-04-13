package cn.itcast.web.controller;

import cn.itcast.domain.Order;
import cn.itcast.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class getOrderServlet extends HttpServlet {
    BussinessServiceImpl service = new BussinessServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("getAll".equalsIgnoreCase(method)) {
            getAll(request, response);
        }
        if ("find".equalsIgnoreCase(method)) {
            find(request, response);
        }
        if ("update".equalsIgnoreCase(method)) {
            update(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            String state = request.getParameter("state");
            Boolean b = state.equalsIgnoreCase("true");
            boolean b1 = service.updateOrderState(id, b);
            if (b1) {
                request.setAttribute("message", "修改成功!");
                request.getRequestDispatcher("/message.jsp").forward(request, response);
                return;
            }
            request.setAttribute("message", "修改失败!!");

        } catch (Exception e) {
            request.setAttribute("message", "修改失败!!");
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Order order = service.findOrderById(id);
        request.setAttribute("order", order);
        request.getRequestDispatcher("./orderdetail.jsp").forward(request, response);

    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        boolean b = state.equals("true");
        List list = service.getAllOrders(b);
        request.setAttribute("list", list);
//        request.getRequestDispatcher("./listorder.jsp").forward(request,response);
        request.getRequestDispatcher("/manag/listorder.jsp").forward(request, response);

    }
}
