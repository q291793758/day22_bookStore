package cn.itcast.web.client;

import cn.itcast.domain.Book;
import cn.itcast.domain.Cart;
import cn.itcast.service.impl.BussinessServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BuyServlet extends HttpServlet {
    private BussinessServiceImpl service = new BussinessServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Book book = service.findBookById(id);
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart",cart);
        }
        cart.addBook(book);
        response.sendRedirect("./listcart.jsp");

    }
}
