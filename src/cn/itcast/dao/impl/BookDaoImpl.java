package cn.itcast.dao.impl;

import cn.itcast.domain.Book;
import cn.itcast.domain.QueryResult;
import cn.itcast.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.util.List;

public class BookDaoImpl implements cn.itcast.dao.BookDao {
    @Override
    public void add(Book book) {
        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            Object[] params = {book.getId(), book.getName(),book.getPrice(),book.getAuthor(),
                    book.getImage(),book.getDescription(),book.getCategory().getId()};
            String sql="INSERT INTO book(id, name, price, author, image, description, category_id) VALUES (?,?,?,?,?,?,?)";
            runner.update(connection, sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book findById(String id) {
        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql="SELECT * FROM book WHERE id=?";
            Object[] params ={id};
//            runner.query(connection, sql, new BeanHandler(Book.class),params);
            return (Book) runner.query(connection, sql,id, new BeanHandler(Book.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //categoryId==null查询所有book,categoryId!=null或查询某一类book //where category_id =?
    private List<Book> getPageData(int startindex, int pagesize, String categoryId) {
        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            if (categoryId==null) {
                String sql = "SELECT * FROM book LIMIT ?,?";
                Object[] params={startindex,pagesize};
              return (List<Book>) runner.query(connection,sql,new BeanListHandler(Book.class),params);
            }
            String sql = "SELECT * FROM book WHERE category_id=? LIMIT ?,?";
            Object[] params={categoryId,startindex,pagesize};
            return (List<Book>) runner.query(connection,sql,new BeanListHandler(Book.class),params);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    //查询页面总记录数,带categoryId就查询categoryId下的总记录数
    private int getPageTotalRecord(String categoryId) {

        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            Object[] params = {categoryId};
            if (categoryId==null) {
                String sql = "SELECT count(*) FROM book";
                return ((Long) runner.query(connection,sql, new ScalarHandler())).intValue();
            }
            String sql = "SELECT count(*) FROM book WHERE category_id=?";
            return ((Long) runner.query(connection,sql, new ScalarHandler(), params)).intValue();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public QueryResult pageQuery(int startindex, int pagesize, String categoryId){
        QueryResult result = new QueryResult();
        List<Book> pageData = getPageData(startindex, pagesize, categoryId);
        int pageTotalRecord = getPageTotalRecord(categoryId);
        if (pageData != null) {
            result.setList(pageData);
            result.setTotalRecord(pageTotalRecord);
            return result;
        }
        return null;
    }

    @Override
    public List getAllBooks() {
        try {
            Connection connection = JdbcUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "SELECT * FROM book";
          return (List) runner.query(connection,sql, new BeanListHandler(Book.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
