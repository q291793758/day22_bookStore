## 1.搭建环境

   * ### 1.1导入开发包
    
    * mysql驱动
    * bdutils框架
    * c3p0连接池
    * beanUtils框架
    * log4j
    * commens-fileupload
    * commens-io
    * jstl开发包
    
   * ### 1.2.1 创建组织程序的包
    * cn.itcast.domain
    * cn.itcast.dao
    * cn.itcast.daoimpl
    * cn.itcast.service
    * cn.itcast.web.controller
    * cn.itcast.web.filter
    * cn.itcast.utils
    * cn.itcast.errors

        * #### 1.2.2创建jsp目录
        * WebRoot(根目录)下创建manager,保存后台相关的jsp
    
         ```<frameset rows="18%,*">
            <frame src="${pageContext.request.contextPath}/manager/head.jsp" name="head">
            <frameset  cols="15%,*">
                <frame src="${pageContext.request.contextPath}/manager/left.jsp" name="left">
                <frame src="#" name="right">
            </frameset>
          </frameset>```
            
         * WebRoot(根目录)下创建client,保存前台相关的jsp
          * ### 1.2.3创建工程所需的数据库
          `cmd mysql -uroot -proot`
          `create database bookstore;
          use bookstore;`
          
          * ### 1.2.4 创建全局工具类和过滤器
          cn.itcast.utils/JdbcUtils
          cn.itcast.utils/WebUtils
          
          cn.itcast.web/filter/CharacterEncodingFilter  解决乱码
          cn.itcast.web/filter/HtmlFilter               转义字符
          cn.itcast.web/TransactionFilter               过滤器管理事务    

    ## 2.设计实体

    * Category(分类)
    
            private String id;
            private String name;
            private String description;
        CREATE TABLE category(
          id VARCHAR(40)   PRIMARY KEY,
          name VARCHAR(40) NOT NULL UNIQUE ,
          description VARCHAR(255)
        );

    * Book (书籍)
    
            private String id;
    	    private String name;
    	    private double price;
    	    private String author;
    	    private String image;  //记住书的图片的位置
    	    private String description;
    	    private Category category;
    	    
    	  CREATE TABLE book(
          id VARCHAR(40)   PRIMARY KEY,
          name VARCHAR(40) NOT NULL UNIQUE ,
          price DECIMAL(8,2) NOT NULL ,
          author VARCHAR(40) NOT NULL ,
          image VARCHAR(100) NOT NULL,
          description VARCHAR(255),
          category_id VARCHAR(40),
          CONSTRAINT category_id_FK FOREIGN KEY (category_id) REFERENCES category(id)
            );
    * Order (订单)
    
            private String id;
    		private Date ordertime;  //下单时间
    		private boolean state;   //订单状态
    		private double price;    //订单总价
    		private User user;    //记住下单人
    		private Set orderitems;   //记住订单所有的订单项
    		
    	CREATE TABLE orders(
        id VARCHAR(40)   PRIMARY KEY,
        date Date NOT NULL ,
        state BOOLEAN NOT NULL ,
        price DECIMAL(8,2) NOT NULL ,
        user_id VARCHAR(40),
        CONSTRAINT user_id_FK FOREIGN KEY (user_id) REFERENCES user(id)
        );
    * OrderItem (订单项)
    
            private String id;
    		private Book book;   //记住订单项代表的是哪本书
    		private int quantity;
    		private double price;
    	
    	CREATE TABLE orderitem (
          id       VARCHAR(40) PRIMARY KEY,
          quantity INT           NOT NULL,
          price    DECIMAL(8, 2) NOT NULL,
          book_id  VARCHAR(40),
          order_id VARCHAR(40),
          CONSTRAINT book_id_FK FOREIGN KEY (book_id) REFERENCES book (id),
          CONSTRAINT order_id_FK FOREIGN KEY (order_id)  REFERENCES orders(id)
        );
    * User (用户)
    
            private String id;
            private String username;
            private String password;
            private String phone;
            private String cellphone;
            private String eamil;
            private String address;
    CREATE TABLE user (
      id VARCHAR(40) PRIMARY KEY ,
      username VARCHAR(40) NOT NULL UNIQUE ,
      password VARCHAR(40) NOT NULL ,
      phone VARCHAR(20) NOT NULL ,
      cellphone VARCHAR(20) NOT NULL ,
      email VARCHAR(40) NOT NULL ,
      address VARCHAR(255) NOT NULL
    );