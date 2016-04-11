package cn.itcast.dao;

import cn.itcast.domain.User;

public interface UserDao {
    void add(User user);

    User findByid(String id);

    User findByNamePassword(String username, String password);
}
