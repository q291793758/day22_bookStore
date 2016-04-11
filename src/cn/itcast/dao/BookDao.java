package cn.itcast.dao;

import cn.itcast.domain.Book;
import cn.itcast.domain.QueryResult;

public interface BookDao {
    void add(Book book);

    Book findById(String id);

    QueryResult pageQuery(int startindex, int pagesize, String categoryId);
}
