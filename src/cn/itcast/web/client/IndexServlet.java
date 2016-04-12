package cn.itcast.web.client;

import cn.itcast.domain.Category;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.QueryInfo;
import cn.itcast.service.impl.BussinessServiceImpl;
import cn.itcast.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends HttpServlet {
    BussinessServiceImpl service = new BussinessServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categorys = service.getAllCategory();
        request.setAttribute("categorys", categorys);
        QueryInfo queryInfo = WebUtils.request2Bean(request, QueryInfo.class);
        String category_id = request.getParameter("category_id");
        if (category_id!=null&&!category_id.trim().equals("")) {
            //有带图书分类过来
            queryInfo.setCategoryId(category_id);
        }
        PageBean pagebean = service.BookPageQuery(queryInfo);
        request.setAttribute("pagebean", pagebean);
        request.getRequestDispatcher("./index.jsp").forward(request, response);
    }
}
