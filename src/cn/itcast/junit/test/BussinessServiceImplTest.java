package cn.itcast.junit.test;

import cn.itcast.domain.Book;
import cn.itcast.domain.Category;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.QueryInfo;
import cn.itcast.service.impl.BussinessServiceImpl;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

public class BussinessServiceImplTest {

     BussinessServiceImpl service = new BussinessServiceImpl();

    @Test
    public void testAddCategory() throws Exception {
        Category category = new Category();
        category.setId("3");
        category.setName("JAVASE基础");
        category.setDescription("javase基础课程,毕向东主讲,30天");
        service.addCategory(category);
    }

    @Test
    public void testFindCategoryById() throws Exception {
        Category category = service.findCategoryById("1");
        System.out.println(category.getDescription());
    }

    @Test
    public void testGetAllCategory() throws Exception {
        List<Category> allCategory = service.getAllCategory();
        System.out.println(allCategory.size());
    }

    @Test
    public void testDeleteCategory() throws Exception {
        Boolean aBoolean = service.deleteCategory("3");
        System.out.println(aBoolean);
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setName("Java编程思想");
        book.setDescription("《计算机科学丛书：Java编程思想（第4版）》特点：\n" +
                "　　适合初学者与专业人员的经典的面向对象叙述方式，为更新的JavaSE5/6增加了新的示例和章节。\n" +
                "　　测验框架显示程序输出");
        book.setPrice(71.30);
        book.setAuthor("陈昊鹏");
        book.setImage("/111.jpg");
        book.setCategory(service.findCategoryById("1"));
        service.addBook(book);

    }


    @Test
    public void testFindBookById() throws Exception {
        Book book = service.findBookById("1");
        System.out.println(book.getName());
    }

    @Test
    public void testBookPageQuery() throws Exception {
        QueryInfo queryinfo=new QueryInfo();
        queryinfo.setCurrentpage(1);
        PageBean pageBean = service.BookPageQuery(queryinfo);
        pageBean.getTotalrecord();pageBean.getCurrentpage();pageBean.getPagesize();
        pageBean.getPagebar();
        System.out.println(pageBean.getTotalpage());
        System.out.println(pageBean.getPreviouspage());
        System.out.println(pageBean.getNextpage());
    }

    @Test
    public void testAddUser() throws Exception {

    }

    @Test
    public void testFindUserById() throws Exception {

    }

    @Test
    public void testFindUserByNamePassword() throws Exception {

    }

    @Test
    public void testAddOrder() throws Exception {

    }

    @Test
    public void testFindOrderById() throws Exception {

    }

    @Test
    public void testGetAllOrders() throws Exception {

    }
}