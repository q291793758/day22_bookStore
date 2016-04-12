package cn.itcast.domain;
//购物车的购物项
public class CartItem {
    private Book book;
    private int quantity;
    private double price;

    public int getQuantity() {
        this.price=this.book.getPrice()*this.quantity;
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
//        this.price=this.book.getPrice()*this.quantity;
        return price;
    }

    public Book getBook() {

        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
