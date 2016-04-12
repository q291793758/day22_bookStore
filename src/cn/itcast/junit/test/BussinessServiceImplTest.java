package cn.itcast.junit.test;

import cn.itcast.domain.*;
import cn.itcast.service.impl.BussinessServiceImpl;
import cn.itcast.utils.JdbcUtils;
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
        Boolean aBoolean = service.deleteCategory("2");
        System.out.println(aBoolean);
        JdbcUtils.commitTransaction();
    }

    @Test
    public void testAddBook() throws Exception {
        for (int i = 0; i <20 ; i++) {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString()+i);
        book.setName("JavaEE编程思想"+i);
        book.setDescription("计算机科学丛书");
        book.setPrice(71.30);
        book.setAuthor("陈昊鹏");
        book.setImage("/111.jpg");
        book.setCategory(service.findCategoryById("2"));
        service.addBook(book);
            JdbcUtils.commitTransaction();
        }
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

        System.out.println(pageBean.getTotalpage());
        System.out.println(pageBean.getPreviouspage());
        System.out.println(pageBean.getNextpage());
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("张三");
        user.setPassword("123123");
        user.setAddress("北京崇文门");
        user.setCellphone("123123");
        user.setEmail("aaa@aa.com");
        user.setPhone("123123");
        service.addUser(user);
    }

    @Test
    public void testFindUserById() throws Exception {
        User user = service.findUserById("3d4befdd-6e6c-443e-97c0-e860085102fc");
        System.out.println(user.getUsername()); //检查出domain/User的email属性拼写错误,和数据库不一致导致无法得到正确的值
    }

    @Test
    public void testFindUserByNamePassword() throws Exception {
        User user = service.findUserByNamePassword("张三", "123123");
        System.out.println(user.getUsername());
    }

    @Test
    public void testAddOrder() throws Exception {
        User user = service.findUserById("3d4befdd-6e6c-443e-97c0-e860085102fc");
        Cart cart = new Cart();
        Book book = service.findBookById("1");
        cart.addBook(book); //获取购物车的价格,购物车迭代购物项的价格,购物项的价格不会触发setQuantity

        service.addOrder(cart,user);
        JdbcUtils.commitTransaction();

    }

    @Test
    public void testFindOrderById() throws Exception {
        Order order = service.findOrderById("db8b92a9-4492-46bf-b2b1-0573c929c4b5");
    }

    @Test
    public void testGetAllOrders() throws Exception {
        List<Order> allOrders = service.getAllOrders(true);
    }
}