/*    */ package com.az.ww.sqlUtil;
/*    */ 
/*    */ public class QueryObject
/*    */ {
/*    */   private String prepend;
/*    */   private String field;
/*    */   private String condition;
/*    */   private Object value;
/*    */ 
/*    */   public QueryObject()
/*    */   {
/*    */   }
/*    */ 
/*    */   public QueryObject(String prepend, String field, String condition, Object value)
/*    */   {
/* 34 */     this.prepend = prepend;
/* 35 */     this.field = field;
/* 36 */     this.condition = condition;
/* 37 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public String getPrepend() {
/* 41 */     return this.prepend;
/*    */   }
/*    */ 
/*    */   public void setPrepend(String prepend) {
/* 45 */     this.prepend = prepend;
/*    */   }
/*    */ 
/*    */   public String getField() {
/* 49 */     return this.field;
/*    */   }
/*    */ 
/*    */   public void setField(String field) {
/* 53 */     this.field = field;
/*    */   }
/*    */ 
/*    */   public String getCondition() {
/* 57 */     return this.condition;
/*    */   }
/*    */ 
/*    */   public void setCondition(String condition) {
/* 61 */     this.condition = condition;
/*    */   }
/*    */ 
/*    */   public Object getValue() {
/* 65 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(Object value) {
/* 69 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public static boolean isValidate(QueryObject obj) {
/* 73 */     if (obj.getPrepend().isEmpty())
/* 74 */       return false;
/* 75 */     if (obj.getField().isEmpty())
/* 76 */       return false;
/* 77 */     if (obj.getCondition().isEmpty())
/* 78 */       return false;
/* 79 */     if (obj.getValue() == null) {
/* 80 */       return false;
/*    */     }
/* 82 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\webFrame.jar
 * Qualified Name:     com.az.ww.sqlUtil.QueryObject
 * JD-Core Version:    0.6.2
 */