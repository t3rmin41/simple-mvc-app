package simple.mvc.app.enums;

public enum OrderStatus {

  PENDING("Pending"),
  IN_PROGRESS("In progress"),
  DELIVERED("Delivered"),
  COMPLETED("Completed");
  
  private String status;
  
  private OrderStatus(String status) {
    this.status = status;
  }
  
  public static String getOrderStatusName(OrderStatus status) {
    for (OrderStatus current : OrderStatus.values()) {
      if (status.equals(current)) {
        return current.status;
      }
    }
    return null;
  }
  
  public static OrderStatus getOrderStatusByName(String statusName) {
    for (OrderStatus current : OrderStatus.values()) {
      if (current.status.equals(statusName)) {
        return current;
      }
    }
    return null;
  }
  
}
