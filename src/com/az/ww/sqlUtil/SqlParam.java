package com.az.ww.sqlUtil;

import java.util.ArrayList;
import java.util.List;

public class SqlParam
{
  private String searchField;
  private boolean cache;
  private String cacheRegion;
  private List<QueryObject> queryList = new ArrayList<QueryObject>();

  private List<OrderObject> orderList = new ArrayList<OrderObject>();

  public String getSearchField() { return this.searchField; }

  public void setSearchField(String searchField) {
    this.searchField = searchField;
  }
  public boolean isCache() {
    return this.cache;
  }
  public void setCache(boolean cache) {
    this.cache = cache;
  }
  public String getCacheRegion() {
    return this.cacheRegion;
  }
  public void setCacheRegion(String cacheRegion) {
    this.cacheRegion = cacheRegion;
  }
  public List<QueryObject> getQueryList() {
    return this.queryList;
  }
  public void setQueryList(List<QueryObject> queryList) {
    this.queryList = queryList;
  }
  public List<OrderObject> getOrderList() {
    return this.orderList;
  }
  public void setOrderList(List<OrderObject> orderList) {
    this.orderList = orderList;
  }
}

