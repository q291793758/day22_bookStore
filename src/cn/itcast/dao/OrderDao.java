package cn.itcast.dao;

import cn.itcast.domain.Order;

import java.util.List;

public interface OrderDao {
    void add(Order order);

    Order findById(String id);

    // state==true 已发货  state==false 未发货
    List<Order> getAll(Boolean state);

    boolean updateState(String id, Boolean state);
}
