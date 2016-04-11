package cn.itcast.junit.test;

import cn.itcast.dao.impl.OrderDaoImpl;
import cn.itcast.domain.Order;
import org.junit.Test;

import java.util.List;

public class OrderDaoImplTest {

        OrderDaoImpl dao = new OrderDaoImpl();
    @Test
    public void testAdd() throws Exception {
    }

    @Test
    public void testFindById() throws Exception {
        Order order = dao.findById("1");
        System.out.println("订单号为:"+order.getId());
    }

    @Test
    public void testGetAll() throws Exception {
        List<Order> list = dao.getAll(true);
        for (Order order : list) {
            System.out.println(order.getOrdertime().toLocaleString());
        }

    }
}