package simple.mvc.bean;

public class ProductBean {

    private String title;
    private Double price;

    public String getTitle() {
        return title;
    }
    public ProductBean setTitle(String title) {
        this.title = title;
        return this;
    }
    public Double getPrice() {
        return price;
    }
    public ProductBean setPrice(Double price) {
        this.price = price;
        return this;
    }
}
