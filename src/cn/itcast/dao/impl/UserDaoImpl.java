package cn.itcast.dao.impl;

import cn.itcast.domain.User;
import cn.itcast.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoImpl implements cn.itcast.dao.UserDao {
    @Override
    public void add(User user) {
        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "INSERT  INTO  user (id, username, password, phone, cellphone, email, address) VALUES (?,?,?,?,?,?,?)";
            Object[] params = {user.getId(), user.getUsername(), user.getPassword(),
                    user.getPhone(), user.getCellphone(), user.getEmail(), user.getAddress()};
            runner.update(connection, sql, params);
            connection.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByid(String id) {
        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "SELECT * FROM user WHERE id=?";
            Object[] params = {id};
            return (User) runner.query(connection, sql, new BeanHandler(User.class), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByNamePassword(String username, String password) {
        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "SELECT * FROM user WHERE username=? AND password=?";
            Object[] params = {username,password};
            return (User) runner.query(connection, sql, new BeanHandler(User.class), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
