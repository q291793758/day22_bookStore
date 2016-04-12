package cn.itcast.web.client;

import cn.itcast.domain.Cart;
import cn.itcast.domain.User;
import cn.itcast.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends HttpServlet {
    BussinessServiceImpl service = new BussinessServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");

        if ("saveOrder".equals(method)) {
            saveOrder(request, response);
        }

    }



    private void saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect("./register.jsp");
                return;
            }
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            service.addOrder(cart, user);
            request.setAttribute("message", "生成订单成功,请及时付款,我们会尽快为您发货!");
            //生成订单之后,清空购物车.
            request.getSession().removeAttribute("cart");
        } catch (Exception e) {
            request.setAttribute("message", "生成订单失败!!");
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
