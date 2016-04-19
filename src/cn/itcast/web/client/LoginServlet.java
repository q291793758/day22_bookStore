package cn.itcast.web.client;

import cn.itcast.domain.Cart;
import cn.itcast.domain.User;
import cn.itcast.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    BussinessServiceImpl service = new BussinessServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("login".equalsIgnoreCase(method)) {
            login(request, response);
        }
        if ("logout".equalsIgnoreCase(method)) {
            logout(request, response);
        }


    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = service.findUserByNamePassword(username, password);
        if (user == null) {
            request.setAttribute("message", "用户名或密码错误!");
            response.setHeader("refresh", "2,/index.jsp");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }

        request.getSession().setAttribute("user", user);//登录成功
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {  //购物车有信息,跳转到购物车
            request.getRequestDispatcher("./listcart.jsp").forward(request, response);
            return;
        }//购物车是空的,跳转到主页
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}
