/*    */ package com.az.ww.sqlUtil;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SqlParam
/*    */ {
/*    */   private String searchField;
/*    */   private boolean cache;
/*    */   private String cacheRegion;
/* 30 */   private List<QueryObject> queryList = new ArrayList();
/*    */ 
/* 34 */   private List<OrderObject> orderList = new ArrayList();
/*    */ 
/* 36 */   public String getSearchField() { return this.searchField; }
/*    */ 
/*    */   public void setSearchField(String searchField) {
/* 39 */     this.searchField = searchField;
/*    */   }
/*    */   public boolean isCache() {
/* 42 */     return this.cache;
/*    */   }
/*    */   public void setCache(boolean cache) {
/* 45 */     this.cache = cache;
/*    */   }
/*    */   public String getCacheRegion() {
/* 48 */     return this.cacheRegion;
/*    */   }
/*    */   public void setCacheRegion(String cacheRegion) {
/* 51 */     this.cacheRegion = cacheRegion;
/*    */   }
/*    */   public List<QueryObject> getQueryList() {
/* 54 */     return this.queryList;
/*    */   }
/*    */   public void setQueryList(List<QueryObject> queryList) {
/* 57 */     this.queryList = queryList;
/*    */   }
/*    */   public List<OrderObject> getOrderList() {
/* 60 */     return this.orderList;
/*    */   }
/*    */   public void setOrderList(List<OrderObject> orderList) {
/* 63 */     this.orderList = orderList;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\webFrame.jar
 * Qualified Name:     com.az.ww.sqlUtil.SqlParam
 * JD-Core Version:    0.6.2
 */