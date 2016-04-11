package cn.itcast.domain;

public class OrderItem {
    private String id;
    private Book book;   //记住订单项代表的是哪本书
    private int quantity;
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price=price;
        //this.price = this.quantity*this.book.getPrice();
    }
}
