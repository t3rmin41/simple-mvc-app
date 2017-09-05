package simple.mvc.bean;

public class ProductBean {

    private Long id;
    private String title;
    private Double price;
    private boolean deleted;

    public Long getId() {
      return id;
    }
    public ProductBean setId(Long id) {
      this.id = id;
      return this;
    }
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
    public boolean isDeleted() {
      return deleted;
    }
    public ProductBean setDeleted(boolean deleted) {
      this.deleted = deleted;
      return this;
    }
}
