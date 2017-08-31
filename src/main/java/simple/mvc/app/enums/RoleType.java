package simple.mvc.app.enums;

public enum RoleType {

  GUEST("Guest"),
  USER("User"),
  CUSTOMER("Customer"),
  SUPERVISOR("Supervisor"),
  MANAGER("Manager"),
  ADMIN("Admin");
  
  private String code;
  
  private RoleType(String code) {
    this.code = code;
  }
  
  public String getCode() {
    return code;
  }
  
  public static String getRoleTypeName(RoleType type) {
    for (RoleType current : RoleType.values()) {
      if (type.equals(current)) {
        return current.code;
      }
    }
    return null;
  }
  
  public static RoleType getRoleTypeByName(String typeName) {
    for (RoleType current : RoleType.values()) {
      if (current.name().equals(typeName)) {
        return current;
      }
    }
    return null;
  }
  
  public static RoleType getRoleTypeByCode(String typeName) {
    for (RoleType current : RoleType.values()) {
      if (current.code.equals(typeName)) {
        return current;
      }
    }
    return null;
  }
}
