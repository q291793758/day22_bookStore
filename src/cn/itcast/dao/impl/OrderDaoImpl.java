package cn.itcast.dao.impl;

import cn.itcast.domain.Book;
import cn.itcast.domain.Order;
import cn.itcast.domain.OrderItem;
import cn.itcast.domain.User;
import cn.itcast.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public class OrderDaoImpl implements cn.itcast.dao.OrderDao {
    @Override
    public void add(Order order) {
        try {
            //插入订单Order信息
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
            String sql = "INSERT INTO orders (id, ordertime, state, price, user_id) VALUES (?,?,?,?,?);";
            Object[] params = {order.getId(), order.getOrdertime(), order.getState(),
                    order.getPrice(), order.getUser().getId()};
            runner.update(connection, sql, params);
            //插入订单项OrderItem信息
            Set<OrderItem> items = order.getOrderitems();
            for (OrderItem item : items) {
                sql = "INSERT INTO orderitem (id, quantity, price, book_id, order_id) VALUES (?, ?, ?, ?, ?)";
                params = new Object[]{item.getId(), item.getQuantity(), item.getPrice(),
                        item.getBook().getId(), order.getId()};
                runner.update(connection, sql, params);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order findById(String id) {
        try {

            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            //订单Order信息
            String sql = "SELECT * FROM orders WHERE id=?";
            Object[] params = {id};
            Order order = (Order) runner.query(connection, sql, new BeanHandler(Order.class), params);
            //订单项OrderItem信息
            sql = "SELECT * FROM orderitem where order_id= ?";
            List<OrderItem> list = (List<OrderItem>) runner.query(connection, sql, new BeanListHandler(OrderItem.class), params);
            //找出订单代表的书Book
            for (OrderItem item : list) {
                sql = "SELECT book.* FROM orderitem,book WHERE orderitem.id=? AND book.id=orderitem.book_id";
                Book book = (Book) runner.query(connection, sql, item.getId(), new BeanHandler(Book.class));
                //Book加入到OrderItem中
                item.setBook(book);
            }

            //加入订单项信息
            if (list != null) {
                order.getOrderitems().addAll(list);
            }

            //找出下订单人的信息
            sql = "SELECT user.* FROM user,orders WHERE orders.id=? AND user.id=orders.user_id";
            User user = (User) runner.query(connection, sql, id, new BeanHandler(User.class));

            //User 加入到Order中
            order.setUser(user);
            return order;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // state==true 已发货  state==false 未发货
    @Override
    public List<Order> getAll(Boolean state) {
        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "SELECT * FROM orders WHERE state=?";
            Object[] params = {state};
            List<Order> list = (List<Order>) runner.query(connection, sql, new BeanListHandler(Order.class), params);

            //找出下订单的User
            if (list.size() <= 0&&list==null) {
              return null;
            }
                for (Order order : list) {
                    sql = "SELECT user.* FROM orders,user WHERE orders.state=? AND user.id=orders.user_id";
                    User user = (User) runner.query(connection, sql, state, new BeanHandler(User.class));
                    order.setUser(user);
                }

            return list;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
