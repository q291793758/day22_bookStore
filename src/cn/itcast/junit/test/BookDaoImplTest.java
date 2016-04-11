package cn.itcast.junit.test;

import cn.itcast.dao.impl.BookDaoImpl;
import cn.itcast.domain.Book;
import cn.itcast.domain.Category;
import cn.itcast.domain.QueryResult;
import org.junit.Test;

public class BookDaoImplTest {

    BookDaoImpl dao = new BookDaoImpl();

    @Test
    public void testAdd() throws Exception {
        Book book = new Book();
        book.setId("6");
        book.setName("Spring快速入门");
        book.setAuthor("张孝祥");
        book.setDescription("SPRING快速入门技术");
        book.setPrice(99.80);
        book.setImage("1");
        Category category = new Category();
        category.setId("2");
        book.setCategory(category);
        dao.add(book);

    }

    @Test
    public void testFindById() throws Exception {
        Book book = dao.findById("1");
        System.out.println(book.getName());
    }

    @Test
    public void testPageQuery() throws Exception {
        QueryResult queryResult = dao.pageQuery(0, 3, "1");
        queryResult.getList();
        System.out.println(queryResult.getTotalRecord());
    }
}