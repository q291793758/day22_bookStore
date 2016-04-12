package cn.itcast.dao.impl;

import cn.itcast.domain.Category;
import cn.itcast.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class CategoryDaoImpl implements cn.itcast.dao.CategoryDao {
    @Override
    public void add(Category c) {
        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "INSERT INTO category (id, name, description) VALUES (?,?,?)";
            Object[] params={c.getId(),c.getName(),c.getDescription()};
            runner.update(connection, sql,params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Category findById(String id) {

        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "SELECT * FROM category WHERE id=?";
            Object[] params={id};
            return (Category) runner.query( connection,sql,new BeanHandler(Category.class),params);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteById(String id) {
        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "DELETE FROM category WHERE id=?";
            int i=  runner.update( connection,sql,id);
            return i > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List getAll() {
        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "SELECT * FROM category";
            return (List) runner.query( connection,sql,new BeanListHandler(Category.class));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Category c) {
        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "UPDATE category SET name=?,description=? WHERE id=?";
            Object[] params={c.getName(),c.getDescription(),c.getId()};
            runner.update(connection, sql,params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
