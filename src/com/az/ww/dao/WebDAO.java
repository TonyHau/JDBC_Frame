/*     */ package com.az.ww.dao;
/*     */ 
/*     */ import com.az.helps.pageBean.PageUtils;
/*     */ import com.az.ww.sqlUtil.OrderObject;
/*     */ import com.az.ww.sqlUtil.QueryObject;
/*     */ import com.az.ww.sqlUtil.SqlParam;
/*     */ import com.az.ww.tools.DBConnPool;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class WebDAO
/*     */ {
/*     */   public static void insert(Map<String, Object> valueMap, String tableName)
/*     */     throws SQLException
/*     */   {
/*  33 */     Connection conn = null;
/*  34 */     PreparedStatement st = null;
/*     */     try
/*     */     {
/*  37 */       conn = DBConnPool.getInstance().getConnection();
/*  38 */       conn.setAutoCommit(false);
/*     */ 
/*  40 */       StringBuffer sbSql = new StringBuffer();
/*  41 */       sbSql.append("insert into ");
/*  42 */       sbSql.append(tableName);
/*  43 */       sbSql.append("(");
/*  44 */       int i = 0;
/*  45 */       String s1 = null;
/*  46 */       Set key = valueMap.keySet();
/*  47 */       for (Iterator it = key.iterator(); it.hasNext(); ) {
/*  48 */         String s = (String)it.next();
/*  49 */         if (i == 0)
/*  50 */           s1 = s;
/*     */         else {
/*  52 */           s1 = s1 + "," + s;
/*     */         }
/*  54 */         if (i == valueMap.size() - 1) {
/*  55 */           sbSql.append(s);
/*     */         } else {
/*  57 */           sbSql.append(s);
/*  58 */           sbSql.append(",");
/*     */         }
/*  60 */         i++;
/*     */       }
/*     */ 
/*  63 */       sbSql.append(") values(");
/*  64 */       for (int j = 0; j < i; j++) {
/*  65 */         if (j == 0)
/*  66 */           sbSql.append("?");
/*     */         else {
/*  68 */           sbSql.append(",?");
/*     */         }
/*     */       }
/*     */ 
/*  72 */       sbSql.append(")");
/*  73 */       st = conn.prepareStatement(sbSql.toString());
/*  74 */       String[] s1Array = s1.split(",");
/*  75 */       for (int j = 0; j < i; j++) {
/*  76 */         st.setObject(j + 1, valueMap.get(s1Array[j]));
/*     */       }
/*  78 */       st.executeUpdate();
/*  79 */       conn.commit();
/*     */     } catch (Exception e) {
/*  81 */       System.out.println(e);
/*     */       try {
/*  83 */         conn.rollback();
/*     */       }
/*     */       catch (Exception localException1)
/*     */       {
/*     */       }
/*  88 */       if (st != null)
/*     */         try {
/*  90 */           st.close();
/*     */         }
/*     */         catch (SQLException localSQLException)
/*     */         {
/*     */         }
/*  95 */       if (conn != null)
/*     */         try {
/*  97 */           conn.close();
/*     */         }
/*     */         catch (SQLException localSQLException1)
/*     */         {
/*     */         }
/*     */     }
/*     */     finally
/*     */     {
/*  88 */       if (st != null)
/*     */         try {
/*  90 */           st.close();
/*     */         }
/*     */         catch (SQLException localSQLException2)
/*     */         {
/*     */         }
/*  95 */       if (conn != null)
/*     */         try {
/*  97 */           conn.close();
/*     */         }
/*     */         catch (SQLException localSQLException3)
/*     */         {
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void update(Map<String, Object> valueMap, String tableName, Map<String, Object> conditionMap)
/*     */     throws SQLException
/*     */   {
/* 108 */     Connection conn = null;
/* 109 */     Statement statement = null;
/*     */     try
/*     */     {
/* 112 */       conn = DBConnPool.getInstance().getConnection();
/* 113 */       conn.setAutoCommit(false);
/*     */ 
/* 115 */       statement = conn.createStatement();
/*     */ 
/* 117 */       StringBuffer sbSql = new StringBuffer();
/* 118 */       sbSql.append("update ");
/* 119 */       sbSql.append(tableName);
/* 120 */       sbSql.append(" set ");
/*     */ 
/* 122 */       Set key = valueMap.keySet();
/* 123 */       int i = 0;
/* 124 */       for (Iterator it = key.iterator(); it.hasNext(); ) {
/* 125 */         String s = (String)it.next();
/* 126 */         sbSql.append(s);
/* 127 */         sbSql.append("='");
/*     */ 
/* 132 */         sbSql.append(valueMap.get(s));
/* 133 */         if (i == valueMap.size() - 1)
/* 134 */           sbSql.append("'");
/*     */         else {
/* 136 */           sbSql.append("',");
/*     */         }
/* 138 */         i++;
/*     */       }
/*     */ 
/* 141 */       sbSql.append(" where ");
/* 142 */       int j = 0;
/* 143 */       Set keyC = conditionMap.keySet();
/* 144 */       for (Iterator itC = keyC.iterator(); itC.hasNext(); ) {
/* 145 */         String sC = (String)itC.next();
/* 146 */         sbSql.append(sC);
/* 147 */         sbSql.append("='");
/* 148 */         sbSql.append(conditionMap.get(sC));
/* 149 */         sbSql.append("'");
/* 150 */         if (j != conditionMap.size() - 1) {
/* 151 */           sbSql.append(" and ");
/*     */         }
/* 153 */         j++;
/*     */       }
/*     */ 
/* 156 */       System.out.println(sbSql.toString());
/* 157 */       statement.executeUpdate(sbSql.toString());
/* 158 */       conn.commit();
/*     */     } catch (Exception e) {
/* 160 */       System.out.println(e);
/*     */       try {
/* 162 */         conn.rollback();
/*     */       }
/*     */       catch (Exception localException1)
/*     */       {
/*     */       }
/* 167 */       if (statement != null)
/*     */         try {
/* 169 */           statement.close();
/*     */         }
/*     */         catch (SQLException localSQLException)
/*     */         {
/*     */         }
/* 174 */       if (conn != null)
/*     */         try {
/* 176 */           conn.close();
/*     */         }
/*     */         catch (SQLException localSQLException1)
/*     */         {
/*     */         }
/*     */     }
/*     */     finally
/*     */     {
/* 167 */       if (statement != null)
/*     */         try {
/* 169 */           statement.close();
/*     */         }
/*     */         catch (SQLException localSQLException2)
/*     */         {
/*     */         }
/* 174 */       if (conn != null)
/*     */         try {
/* 176 */           conn.close();
/*     */         }
/*     */         catch (SQLException localSQLException3)
/*     */         {
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void delete(String sqlDel)
/*     */   {
/* 186 */     Connection conn = null;
/* 187 */     Statement statement = null;
/*     */     try {
/* 189 */       conn = DBConnPool.getInstance().getConnection();
/* 190 */       conn.setAutoCommit(false);
/* 191 */       statement = conn.createStatement();
/* 192 */       statement.executeUpdate(sqlDel);
/* 193 */       conn.commit();
/*     */     } catch (Exception e) {
/*     */       try {
/* 196 */         conn.rollback();
/*     */       }
/*     */       catch (Exception localException1)
/*     */       {
/*     */       }
/* 201 */       if (statement != null)
/*     */         try {
/* 203 */           statement.close();
/*     */         }
/*     */         catch (SQLException localSQLException)
/*     */         {
/*     */         }
/* 208 */       if (conn != null)
/*     */         try {
/* 210 */           conn.close();
/*     */         }
/*     */         catch (SQLException localSQLException1)
/*     */         {
/*     */         }
/*     */     }
/*     */     finally
/*     */     {
/* 201 */       if (statement != null)
/*     */         try {
/* 203 */           statement.close();
/*     */         }
/*     */         catch (SQLException localSQLException2)
/*     */         {
/*     */         }
/* 208 */       if (conn != null)
/*     */         try {
/* 210 */           conn.close();
/*     */         }
/*     */         catch (SQLException localSQLException3)
/*     */         {
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void delete(HashMap<String, String> conditionMap, String tableName)
/*     */     throws SQLException
/*     */   {
/* 221 */     Connection conn = null;
/* 222 */     Statement statement = null;
/*     */     try
/*     */     {
/* 225 */       conn = DBConnPool.getInstance().getConnection();
/* 226 */       conn.setAutoCommit(false);
/* 227 */       statement = conn.createStatement();
/*     */ 
/* 229 */       StringBuffer sbSql = new StringBuffer();
/* 230 */       sbSql.append("delete from ");
/* 231 */       sbSql.append(tableName);
/* 232 */       sbSql.append(" where ");
/* 233 */       int j = 0;
/* 234 */       Set keyC = conditionMap.keySet();
/* 235 */       for (Iterator itC = keyC.iterator(); itC.hasNext(); ) {
/* 236 */         String sC = (String)itC.next();
/* 237 */         sbSql.append(sC);
/* 238 */         sbSql.append("='");
/* 239 */         sbSql.append((String)conditionMap.get(sC));
/* 240 */         sbSql.append("'");
/* 241 */         if (j != conditionMap.size() - 1) {
/* 242 */           sbSql.append(" and ");
/*     */         }
/* 244 */         j++;
/*     */       }
/*     */ 
/* 247 */       statement.executeUpdate(sbSql.toString());
/* 248 */       conn.commit();
/*     */     } catch (Exception e) {
/*     */       try {
/* 251 */         conn.rollback();
/*     */       }
/*     */       catch (Exception localException1)
/*     */       {
/*     */       }
/* 256 */       if (statement != null)
/*     */         try {
/* 258 */           statement.close();
/*     */         }
/*     */         catch (SQLException localSQLException)
/*     */         {
/*     */         }
/* 263 */       if (conn != null)
/*     */         try {
/* 265 */           conn.close();
/*     */         }
/*     */         catch (SQLException localSQLException1)
/*     */         {
/*     */         }
/*     */     }
/*     */     finally
/*     */     {
/* 256 */       if (statement != null)
/*     */         try {
/* 258 */           statement.close();
/*     */         }
/*     */         catch (SQLException localSQLException2)
/*     */         {
/*     */         }
/* 263 */       if (conn != null)
/*     */         try {
/* 265 */           conn.close();
/*     */         }
/*     */         catch (SQLException localSQLException3)
/*     */         {
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static List<Map<String, Object>> query(String sql) throws Exception
/*     */   {
/* 275 */     System.out.println(sql);
/* 276 */     Connection conn = null;
/* 277 */     ResultSetMetaData rsmd = null;
/* 278 */     PreparedStatement ps = null;
/* 279 */     ResultSet rs = null;
/* 280 */     List valueList = null;
/*     */     try {
/* 282 */       conn = DBConnPool.getInstance().getConnection();
/* 283 */       conn.setAutoCommit(false);
/*     */ 
/* 285 */       ps = conn.prepareStatement(sql);
/* 286 */       rs = ps.executeQuery();
/* 287 */       rsmd = rs.getMetaData();
/*     */ 
/* 289 */       valueList = new ArrayList();
/* 290 */       while (rs.next()) {
/* 291 */         Map valueMap = new HashMap();
/*     */ 
/* 293 */         if (rsmd != null) {
/* 294 */           int count = rsmd.getColumnCount();
/* 295 */           for (int i = 1; i <= count; i++) {
/* 296 */             valueMap.put(rsmd.getColumnName(i).toLowerCase(), 
/* 297 */               rs.getString(rsmd.getColumnName(i)));
/*     */           }
/*     */         }
/* 300 */         valueList.add(valueMap);
/*     */       }
/* 302 */       conn.commit();
/*     */     } catch (Exception e) {
/* 304 */       System.out.println(e);
/*     */ 
/* 306 */       if (rs != null) {
/*     */         try {
/* 308 */           rs.close();
/*     */         } catch (SQLException e1) {
/* 310 */           e1.printStackTrace();
/*     */         }
/*     */       }
/* 313 */       if (ps != null) {
/*     */         try {
/* 315 */           ps.close();
/*     */         } catch (SQLException e2) {
/* 317 */           e2.printStackTrace();
/*     */         }
/*     */       }
/* 320 */       if (conn != null)
/*     */         try {
/* 322 */           conn.close();
/*     */         } catch (SQLException e3) {
/* 324 */           e3.printStackTrace();
/*     */         }
/*     */     }
/*     */     finally
/*     */     {
/* 306 */       if (rs != null) {
/*     */         try {
/* 308 */           rs.close();
/*     */         } catch (SQLException e) {
/* 310 */           e.printStackTrace();
/*     */         }
/*     */       }
/* 313 */       if (ps != null) {
/*     */         try {
/* 315 */           ps.close();
/*     */         } catch (SQLException e) {
/* 317 */           e.printStackTrace();
/*     */         }
/*     */       }
/* 320 */       if (conn != null) {
/*     */         try {
/* 322 */           conn.close();
/*     */         } catch (SQLException e) {
/* 324 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 329 */     return valueList;
/*     */   }
/*     */ 
/*     */   public static List<Map<String, Object>> query(String tableName, SqlParam sp, PageUtils pageUtils)
/*     */     throws Exception
/*     */   {
/* 335 */     String sql = getSql(tableName, sp, pageUtils);
/* 336 */     System.out.println("sql:" + sql);
/* 337 */     return query(sql);
/*     */   }
/*     */ 
/*     */   private static String getSql(String tableName, SqlParam sp, PageUtils pageUtils)
/*     */   {
/* 342 */     StringBuffer sql = new StringBuffer();
/* 343 */     if ((sp.getSearchField() == null) || ("".equals(sp.getSearchField()))) {
/* 344 */       sql.append("select * from ");
/* 345 */       sql.append(tableName);
/*     */     } else {
/* 347 */       sql.append("select ");
/* 348 */       sql.append(sp.getSearchField());
/* 349 */       sql.append(" from ");
/* 350 */       sql.append(tableName);
/*     */     }
/*     */     QueryObject obj;
/* 353 */     if ((sp.getQueryList() != null) && (sp.getQueryList().size() > 0)) {
/* 354 */       int kk = 0;
/* 355 */       for (Iterator localIterator = sp.getQueryList().iterator(); localIterator.hasNext(); ) { obj = (QueryObject)localIterator.next();
/* 356 */         if (QueryObject.isValidate(obj)) {
/* 357 */           if (kk == 0)
/* 358 */             sql.append(" where ");
/*     */           else {
/* 360 */             sql.append(obj.getPrepend());
/*     */           }
/* 362 */           sql.append(obj.getField());
/* 363 */           sql.append(" ");
/* 364 */           sql.append(obj.getCondition());
/* 365 */           sql.append(" ");
/* 366 */           if ("like".equals(obj.getCondition().toLowerCase())) {
/* 367 */             sql.append(" '");
/* 368 */             sql.append("%");
/* 369 */             sql.append(obj.getValue());
/* 370 */             sql.append("%");
/* 371 */             sql.append("'");
/* 372 */           } else if ("in".equals(obj.getCondition().toLowerCase())) {
/* 373 */             sql.append("(");
/* 374 */             sql.append(obj.getValue());
/* 375 */             sql.append(")");
/*     */           }
/* 377 */           else if ("not in"
/* 377 */             .equals(obj.getCondition().toLowerCase())) {
/* 378 */             sql.append("(");
/* 379 */             sql.append(obj.getValue());
/* 380 */             sql.append(")");
/*     */           }
/* 382 */           else if (!"is not null".equals(obj.getCondition()
/* 382 */             .toLowerCase()))
/*     */           {
/* 385 */             if (!"is null".equals(obj.getCondition()
/* 385 */               .toLowerCase()))
/*     */             {
/* 388 */               sql.append(" '");
/* 389 */               sql.append(obj.getValue());
/* 390 */               sql.append("'");
/*     */             }
/*     */           }
/*     */         } }
/* 394 */       kk++;
/*     */     }
/* 396 */     if ((sp.getOrderList() != null) && (sp.getOrderList().size() > 0)) {
/* 397 */       sql.append(" order by ");
/* 398 */       for (OrderObject obj2 : sp.getOrderList()) {
/* 399 */         if (OrderObject.isValidate(obj2)) {
/* 400 */           sql.append(obj2.getField());
/* 401 */           sql.append(" ");
/* 402 */           sql.append(obj2.getType());
/* 403 */           sql.append(",");
/*     */         }
/*     */       }
/* 406 */       sql = new StringBuffer(sql.substring(0, sql.length() - 1));
/*     */     }
/* 408 */     return sql.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\webFrame.jar
 * Qualified Name:     com.az.ww.dao.WebDAO
 * JD-Core Version:    0.6.2
 */