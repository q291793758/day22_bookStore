package cn.itcast.web.controller;

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
        }if ("find".equalsIgnoreCase(method)) {
            find(request, response);
        }
    }

    private void find(HttpServletRequest request, HttpServletResponse response) {


    }

    private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        boolean b= state.equals("true");
        List list = service.getAllOrders(b);
        request.setAttribute("list",list);
        request.getRequestDispatcher("./listorder.jsp").forward(request,response);

    }
}
