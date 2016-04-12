package cn.itcast.web.client;

import cn.itcast.domain.User;
import cn.itcast.service.impl.BussinessServiceImpl;
import cn.itcast.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class RegisterServlet extends HttpServlet {
    BussinessServiceImpl service = new BussinessServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = WebUtils.request2Bean(request, User.class);
            user.setId(UUID.randomUUID().toString());
            service.addUser(user);
            request.setAttribute("message","注册成功!");
            } catch (Exception e) {
            request.setAttribute("message","注册失败!!");
            throw new RuntimeException(e);
        }
        response.setHeader("refresh","2,/index.jsp");
        request.getRequestDispatcher("/message.jsp").forward(request,response);

    }
}
