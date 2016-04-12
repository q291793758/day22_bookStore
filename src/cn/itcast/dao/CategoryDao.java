package cn.itcast.dao;

import cn.itcast.domain.Category;

import java.util.List;

public interface CategoryDao {
    void add(Category c);

    Category findById(String id);

    Boolean deleteById(String id);

    List getAll();
    void update(Category c);
}
