package cn.itcast.service.impl;

import cn.itcast.dao.BookDao;
import cn.itcast.dao.CategoryDao;
import cn.itcast.dao.OrderDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.*;
import cn.itcast.factory.DaoFactory;

import java.util.*;

public class BussinessServiceImpl implements cn.itcast.service.BussinessService {

    private BookDao bookdao = DaoFactory.getInstance().createDao(BookDao.class);
    private CategoryDao categorydao = DaoFactory.getInstance().createDao(CategoryDao.class);
    private OrderDao orderdao = DaoFactory.getInstance().createDao(OrderDao.class);
    private UserDao userdao = DaoFactory.getInstance().createDao(UserDao.class);

    /*******************************************
     * 分类相关服务
     *****************************************/

    @Override
    public void addCategory(Category category) {
        categorydao.add(category);
    }

    @Override
    public void updateCategory(Category category) {
        categorydao.update(category);
    }

    @Override
    public Category findCategoryById(String id) {
        return categorydao.findById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categorydao.getAll();
    }

    @Override
    public Boolean deleteCategory(String id) {
        return categorydao.deleteById(id);
    }

    /*******************************************
     * 图书相关服务
     *****************************************/
    @Override
    public void addBook(Book book) {
        bookdao.add(book);
    }

    @Override
    public Book findBookById(String id) {
        return bookdao.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
       return bookdao.getAllBooks();
    }

    //接收查询信息:currentpage,pagesize,startindex,categoryId等数据
    //返回封装了页面信息的pagebean
    @Override
    public PageBean BookPageQuery(QueryInfo queryInfo) {
        PageBean pageBean = new PageBean();
        QueryResult queryResult = bookdao.pageQuery(queryInfo.getStartIndex(), queryInfo.getPagesize(), queryInfo.getCategoryId());
        pageBean.setTotalrecord(queryResult.getTotalRecord());
        pageBean.setCurrentpage(queryInfo.getCurrentpage());
        pageBean.setPagesize(queryInfo.getPagesize());
        pageBean.setBooklist(queryResult.getList());

        pageBean.getTotalpage();
        pageBean.getNextpage();
        pageBean.getPreviouspage();
        pageBean.getPagebar();

        return pageBean;
    }

    /*******************************************
     * 用户相关服务
     *****************************************/
    @Override
    public void addUser(User user) {
        userdao.add(user);
    }

    @Override
    public User findUserById(String id) {
        return userdao.findByid(id);
    }
    @Override
    public User findUserByNamePassword(String username, String password) {
        return userdao.findByNamePassword(username, password);
    }


    /*******************************************
     * 订单相关服务
     *****************************************/
    //根据用户的购物车产生订单对象
    @Override
    public void addOrder(Cart cart, User user) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUser(user);
        order.setOrdertime(new Date());
        order.setState(false);
        order.setPrice(cart.getPrice());

        //根据购物项获得订单项
        Map<String, CartItem> map = cart.getMap();
        Set<Map.Entry<String, CartItem>> set = map.entrySet();
        Set<OrderItem> orderItemSet = new HashSet<OrderItem>();
        for (Map.Entry<String, CartItem> entry : set) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem();
            orderItem.setId(UUID.randomUUID().toString());
            orderItem.setBook(cartItem.getBook());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            //添加进集合
            orderItemSet.add(orderItem);
        }
        order.setOrderitems(orderItemSet);

        orderdao.add(order);
    }
    @Override
    public Order findOrderById(String id) {
        return orderdao.findById(id);
    }

    @Override
    public List getAllOrders(Boolean state) {
        return orderdao.getAll(state);
    }
}
