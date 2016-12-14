/*    */ package com.az.ww.sqlUtil;
/*    */ 
/*    */ public class OrderObject
/*    */ {
/*    */   private String field;
/*    */   private String type;
/*    */   public static final String ORDER_DESC = "desc";
/*    */   public static final String ORDER_ASC = "asc";
/*    */ 
/*    */   public OrderObject()
/*    */   {
/*    */   }
/*    */ 
/*    */   public OrderObject(String field, String type)
/*    */   {
/* 28 */     this.field = field;
/* 29 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public String getField() {
/* 33 */     return this.field;
/*    */   }
/*    */ 
/*    */   public void setField(String field) {
/* 37 */     this.field = field;
/*    */   }
/*    */ 
/*    */   public String getType() {
/* 41 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setType(String type) {
/* 45 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public static boolean isValidate(OrderObject obj) {
/* 49 */     if (obj.getField().isEmpty())
/* 50 */       return false;
/* 51 */     if (obj.getType().isEmpty()) {
/* 52 */       return false;
/*    */     }
/* 54 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\webFrame.jar
 * Qualified Name:     com.az.ww.sqlUtil.OrderObject
 * JD-Core Version:    0.6.2
 */