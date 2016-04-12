package cn.itcast.web.controller;

import cn.itcast.domain.Book;
import cn.itcast.domain.Category;
import cn.itcast.service.impl.BussinessServiceImpl;
import cn.itcast.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends HttpServlet {
    BussinessServiceImpl service = new BussinessServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("addBookUI".equalsIgnoreCase(method)) {
            addBookUI(request, response);
        } if ("addBook".equalsIgnoreCase(method)) {
            addBook(request, response);
        }if ("listbooks".equalsIgnoreCase(method)) {
            listbooks(request, response);
        }
    }

    private void listbooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //列出所有图书
        List<Book> books = service.getAllBooks();
        request.setAttribute("books",books);
        request.getRequestDispatcher("./listbook.jsp").forward(request,response);
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //添加图书
        try {
            String savepath=this.getServletContext().getRealPath("/imgages");
            Book book = WebUtils.upload(request, savepath);
            service.addBook(book);
            request.setAttribute("message","添加成功!");
            } catch (Exception e) {
            request.setAttribute("message","添加失败!!");
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/message.jsp").forward(request,response);

    }

    private void addBookUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //跳转添加图书UI
        List<Category> categorys = service.getAllCategory();
        request.setAttribute("categorys",categorys);
        request.getRequestDispatcher("./addBook.jsp").forward(request,response);
    }

}
