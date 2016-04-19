package cn.itcast.dao;

import cn.itcast.domain.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    User findByid(String id);

    User findByNamePassword(String username, String password);
    List getAllPrivilege(User user);
}
