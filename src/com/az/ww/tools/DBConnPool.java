/*    */ package com.az.ww.tools;
/*    */ 
/*    */ import com.mchange.v2.c3p0.ComboPooledDataSource;
/*    */ import com.mchange.v2.c3p0.DataSources;
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ public class DBConnPool
/*    */ {
/* 10 */   private static DBConnPool instance = null;
/*    */   ComboPooledDataSource ds;
/* 13 */   PropertiesReader proReader = new PropertiesReader();
/*    */ 
/*    */   private DBConnPool() throws Exception {
/* 16 */     this.ds = new ComboPooledDataSource();
/* 17 */     this.ds.setDriverClass(this.proReader.get("jdbc.driverClassName"));
/* 18 */     this.ds.setJdbcUrl(this.proReader.get("jdbc.url"));
/* 19 */     this.ds.setUser(this.proReader.get("jdbc.username"));
/* 20 */     this.ds.setPassword(this.proReader.get("jdbc.password"));
/* 22 */     this.ds.setInitialPoolSize(3);
/*    */ 
/* 24 */     this.ds.setMaxPoolSize(10);
/*    */ 
/* 28 */     this.ds.setAcquireIncrement(1);
/*    */ 
/* 30 */     this.ds.setIdleConnectionTestPeriod(60);
/*    */ 
/* 32 */     this.ds.setMaxIdleTime(25000);
/*    */ 
/* 36 */     this.ds.setTestConnectionOnCheckout(true);
/* 37 */     this.ds.setTestConnectionOnCheckin(true);
/*    */ 
/* 39 */     this.ds.setAcquireRetryAttempts(30);
/*    */ 
/* 41 */     this.ds.setAcquireRetryDelay(1000);
/* 42 */     this.ds.setBreakAfterAcquireFailure(true);
/*    */   }
/*    */ 
/*    */   public static final DBConnPool getInstance()
/*    */   {
/* 47 */     if (instance == null) {
/*    */       try {
/* 49 */         instance = new DBConnPool();
/*    */       } catch (Exception e) {
/* 51 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */ 
/* 55 */     return instance;
/*    */   }
/*    */ 
/*    */   public final synchronized Connection getConnection() {
/*    */     try {
/* 60 */       return this.ds.getConnection();
/*    */     } catch (SQLException e) {
/* 62 */       e.printStackTrace();
/*    */     }
/* 64 */     return null;
/*    */   }
/*    */ 
/*    */   protected void finalize() throws Throwable {
/* 68 */     DataSources.destroy(this.ds);
/*    */ 
/* 70 */     super.finalize();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\webFrame.jar
 * Qualified Name:     com.az.ww.tools.DBConnPool
 * JD-Core Version:    0.6.2
 */