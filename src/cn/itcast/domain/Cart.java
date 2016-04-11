package cn.itcast.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

//购物车
public class Cart {
    private double price;
    private Map<String, CartItem> map = new LinkedHashMap<>();

    public void addBook(Book book) {
        CartItem item = map.get(book.getId());
        //查看购物车里的购物项有没有这本书的信息
        if (item == null) {  //没有,添加购物项
            item.setBook(book);
            item.setQuantity(1);
            map.put(book.getId(), item);
        } else {            //有,购物项里书的数量+1
            item.setQuantity(item.getQuantity()+1);
        }
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    public double getPrice() {
        if (map == null) {
            return 0;
        }
        Set<Map.Entry<String, CartItem>> set = map.entrySet();
        for (Map.Entry<String, CartItem> entry : set) {
           this.price=price+ entry.getValue().getPrice();
        }
        return price;

    }

}
