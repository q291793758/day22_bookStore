package cn.itcast.dao.impl;

import cn.itcast.domain.Databackup;
import cn.itcast.utils.JdbcUtils_bak;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class DbBakDaoImpl implements cn.itcast.dao.DbBakDao {
    @Override
    public boolean add(Databackup d) {
        try {
            Connection connection = JdbcUtils_bak.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "INSERT  INTO dbbak (id, filename, baktime, description) VALUES (?,?,?,?)";
            Object[] params={d.getId(),d.getFilename(),d.getBaktime(),d.getDescription()};
            int update = runner.update(connection,sql, params);
            return update>0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List getAll() {
        try {
            Connection connection = JdbcUtils_bak.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "SELECT * FROM dbbak";
            return (List) runner.query(connection,sql, new BeanListHandler(Databackup.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Databackup findDatabackupById(String id) {
        try {
            Connection connection = JdbcUtils_bak.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "SELECT * FROM dbbak WHERE id=?";
            return (Databackup) runner.query(connection,sql, id,new BeanHandler(Databackup.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
