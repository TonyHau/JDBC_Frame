package com.az.ww.sqlUtil;

public class OrderObject
{
  private String field;
  private String type;
  public static final String ORDER_DESC = "desc";
  public static final String ORDER_ASC = "asc";

  public OrderObject()
  {
  }

  public OrderObject(String field, String type)
  {
    this.field = field;
    this.type = type;
  }

  public String getField() {
    return this.field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public static boolean isValidate(OrderObject obj) {
    if (obj.getField().isEmpty())
      return false;
    if (obj.getType().isEmpty()) {
      return false;
    }
    return true;
  }
}

