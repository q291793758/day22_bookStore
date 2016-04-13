package cn.itcast.service;

import cn.itcast.domain.*;

import java.util.List;

public interface BussinessService {

    void addCategory(Category category);
    void updateCategory(Category category);
    Category findCategoryById(String id);

    List<Category> getAllCategory();

    Boolean deleteCategory(String id);

    void addBook(Book book);

    Book findBookById(String id);

    List getAllBooks();

    //接收查询信息:currentpage,pagesize,startindex,categoryId等数据
    //返回封装了页面信息的pagebean
    PageBean BookPageQuery(QueryInfo queryInfo);

    void addUser(User user);

    User findUserById(String id);

    User findUserByNamePassword(String username, String password);

    //根据用户的购物车产生订单对象
    void addOrder(Cart cart, User user);
    boolean updateOrderState(String id,boolean state);
    Order findOrderById(String id);

    List<Order> getAllOrders(Boolean state);

    boolean addDatabackup(Databackup databackup);

    List getAllDatabackup();
    Databackup findDatabackupById(String id);
}
