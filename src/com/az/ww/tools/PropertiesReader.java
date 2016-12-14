/*    */ package com.az.ww.tools;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class PropertiesReader extends Properties
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Properties pro;
/*    */ 
/*    */   public String get(String key)
/*    */   {
/* 22 */     this.pro = new Properties();
///* 23 */     InputStream is = getClass().getResourceAsStream("/cofing/jdbc-mysql.properties");
/* 23 */     InputStream is = getClass().getResourceAsStream("/cofing/jdbc-oracle.properties");
/*    */     try {
/* 25 */       this.pro.load(is);
/*    */     } catch (IOException e) {
/* 27 */       e.printStackTrace();
/*    */     }
/* 29 */     return this.pro.getProperty(key);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\webFrame.jar
 * Qualified Name:     com.az.ww.tools.PropertiesReader
 * JD-Core Version:    0.6.2
 */