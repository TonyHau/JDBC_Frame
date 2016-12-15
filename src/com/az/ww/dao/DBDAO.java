package com.az.ww.dao;

import com.az.ww.sqlUtil.OrderObject;
import com.az.ww.sqlUtil.QueryObject;
import com.az.ww.sqlUtil.SqlParam;
import com.az.ww.tools.DBConnPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 查询工具类
 * @author yuandl
 *
 */
public class DBDAO {
	/**
	 * 插入数据
	 * @param valueMap 要插入的值的Map<String, Object>
	 * @param tableName 要插入的表名
	 * @throws SQLException SQLException插入失败
	 */
	public static int insert(Map<String, Object> valueMap, String tableName) throws SQLException {
		int affectedCount=-1;
		Connection conn = null;
		PreparedStatement st = null;
		try {
			/**获取数据库连接池对象**/
			conn = DBConnPool.getInstance().getConnection();
			/**设置数据库操作不自动提交，以便于在出现异常时能够自动回滚**/
			conn.setAutoCommit(false);
			
			/**拼sql语句**/
			StringBuffer sbSql = new StringBuffer();
						sbSql.append("insert into ");
			sbSql.append(tableName);
			sbSql.append("(");
			int i = 0;
			String s1 = null;
			/**拼要插入的字段，就是map中的key**/
			Set<String> key = valueMap.keySet();
			for (Iterator<String> it = key.iterator(); it.hasNext();) {
				String s = (String) it.next();
				if (i == 0)
					s1 = s;
				else {
					s1 = s1 + "," + s;
				}
				if (i == valueMap.size() - 1) {
					sbSql.append(s);
				} else {
					sbSql.append(s);
					sbSql.append(",");
				}
				i++;
			}

			sbSql.append(") values(");

			/**拼要插入的字段的值，先用？代替**/
			for (int j = 0; j < i; j++) {
				if (j == 0)
					sbSql.append("?");
				else {
					sbSql.append(",?");
				}
			}

			sbSql.append(")");
			
			st = conn.prepareStatement(sbSql.toString());
			String[] s1Array = s1.split(",");
			for (int j = 0; j < i; j++) {
				st.setObject(j + 1, valueMap.get(s1Array[j]));
			}
			affectedCount=st.executeUpdate();
			conn.commit();
			
			
		} catch (Exception e) {
			System.out.println(e);
			try {
				conn.rollback();
			} catch (Exception localException1) {
			}
			if (st != null)
				try {
					st.close();
				} catch (SQLException localSQLException) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException localSQLException1) {
				}
		} finally {
			if (st != null)
				try {
					st.close();
				} catch (SQLException localSQLException2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException localSQLException3) {
				}
		}
		return affectedCount;
	}

	public static int update(Map<String, Object> valueMap, String tableName, Map<String, Object> conditionMap)
			throws SQLException {

		int affectedCount=-1;
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DBConnPool.getInstance().getConnection();
			conn.setAutoCommit(false);

			statement = conn.createStatement();

			StringBuffer sbSql = new StringBuffer();
			sbSql.append("update ");
			sbSql.append(tableName);
			sbSql.append(" set ");

			Set<String> key = valueMap.keySet();
			int i = 0;
			for (Iterator<String> it = key.iterator(); it.hasNext();) {
				String s = (String) it.next();
				sbSql.append(s);
				sbSql.append("='");

				sbSql.append(valueMap.get(s));
				if (i == valueMap.size() - 1)
					sbSql.append("'");
				else {
					sbSql.append("',");
				}
				i++;
			}

			sbSql.append(" where ");
			int j = 0;
			Set<String> keyC = conditionMap.keySet();
			for (Iterator<String> itC = keyC.iterator(); itC.hasNext();) {
				String sC = (String) itC.next();
				sbSql.append(sC);
				sbSql.append("='");
				sbSql.append(conditionMap.get(sC));
				sbSql.append("'");
				if (j != conditionMap.size() - 1) {
					sbSql.append(" and ");
				}
				j++;
			}

			System.out.println(sbSql.toString());
			affectedCount=	statement.executeUpdate(sbSql.toString());
			conn.commit();
		} catch (Exception e) {
			System.out.println(e);
			try {
				conn.rollback();
			} catch (Exception localException1) {
			}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException localSQLException) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException localSQLException1) {
				}
		} finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException localSQLException2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException localSQLException3) {
				}
		}
		return affectedCount;
	}

	public static int delete(String sqlDel) {
		int affectedCount=-1;
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DBConnPool.getInstance().getConnection();
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			affectedCount=statement.executeUpdate(sqlDel);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception localException1) {
			}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException localSQLException) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException localSQLException1) {
				}
		} finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException localSQLException2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException localSQLException3) {
				}
		}
		return affectedCount;
	}

	public static int delete(HashMap<String, String> conditionMap, String tableName) throws SQLException {
		int affectedCount=-1;
		Connection conn = null;
		Statement statement = null;
		try {
			conn = DBConnPool.getInstance().getConnection();
			conn.setAutoCommit(false);
			statement = conn.createStatement();

			StringBuffer sbSql = new StringBuffer();
			sbSql.append("delete from ");
			sbSql.append(tableName);
			sbSql.append(" where ");
			int j = 0;
			Set<String> keyC = conditionMap.keySet();
			for (Iterator<String> itC = keyC.iterator(); itC.hasNext();) {
				String sC = (String) itC.next();
				sbSql.append(sC);
				sbSql.append("='");
				sbSql.append((String) conditionMap.get(sC));
				sbSql.append("'");
				if (j != conditionMap.size() - 1) {
					sbSql.append(" and ");
				}
				j++;
			}

			affectedCount=statement.executeUpdate(sbSql.toString());
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception localException1) {
			}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException localSQLException) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException localSQLException1) {
				}
		} finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException localSQLException2) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException localSQLException3) {
				}
		}
		return affectedCount;
	}

	public static List<Map<String, Object>> query(String sql) throws Exception {
		System.out.println(sql);
		Connection conn = null;
		ResultSetMetaData rsmd = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String, Object>> valueList = null;
		try {
			conn = DBConnPool.getInstance().getConnection();
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();

			valueList = new ArrayList<Map<String, Object>>();
			while (rs.next()) {
				Map<String, Object> valueMap = new HashMap<String, Object>();

				if (rsmd != null) {
					int count = rsmd.getColumnCount();
					for (int i = 1; i <= count; i++) {
						valueMap.put(rsmd.getColumnName(i).toLowerCase(), rs.getString(rsmd.getColumnName(i)));
					}
				}
				valueList.add(valueMap);
			}
			conn.commit();
		} catch (Exception e) {
			System.out.println(e);

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return valueList;
	}

	public static List<Map<String, Object>> query(String tableName, SqlParam sp) throws Exception {
		String sql = getSql(tableName, sp);
		System.out.println("sql:" + sql);
		return query(sql);
	}

	private static String getSql(String tableName, SqlParam sp) {
		StringBuffer sql = new StringBuffer();
		if ((sp.getSearchField() == null) || ("".equals(sp.getSearchField()))) {
			sql.append("select * from ");
			sql.append(tableName);
		} else {
			sql.append("select ");
			sql.append(sp.getSearchField());
			sql.append(" from ");
			sql.append(tableName);
		}
		QueryObject obj;
		if ((sp.getQueryList() != null) && (sp.getQueryList().size() > 0)) {
			int kk = 0;
			for (Iterator localIterator = sp.getQueryList().iterator(); localIterator.hasNext();) {
				obj = (QueryObject) localIterator.next();
				if (QueryObject.isValidate(obj)) {
					if (kk == 0)
						sql.append(" where ");
					else {
						sql.append(obj.getPrepend());
					}
					sql.append(obj.getField());
					sql.append(" ");
					sql.append(obj.getCondition());
					sql.append(" ");
					if ("like".equals(obj.getCondition().toLowerCase())) {
						sql.append(" '");
						sql.append("%");
						sql.append(obj.getValue());
						sql.append("%");
						sql.append("'");
					} else if ("in".equals(obj.getCondition().toLowerCase())) {
						sql.append("(");
						sql.append(obj.getValue());
						sql.append(")");
					} else if ("not in".equals(obj.getCondition().toLowerCase())) {
						sql.append("(");
						sql.append(obj.getValue());
						sql.append(")");
					} else if (!"is not null".equals(obj.getCondition().toLowerCase())) {
						if (!"is null".equals(obj.getCondition().toLowerCase())) {
							sql.append(" '");
							sql.append(obj.getValue());
							sql.append("'");
						}
					}
				}
			}
			kk++;
		}
		if ((sp.getOrderList() != null) && (sp.getOrderList().size() > 0)) {
			sql.append(" order by ");
			for (OrderObject obj2 : sp.getOrderList()) {
				if (OrderObject.isValidate(obj2)) {
					sql.append(obj2.getField());
					sql.append(" ");
					sql.append(obj2.getType());
					sql.append(",");
				}
			}
			sql = new StringBuffer(sql.substring(0, sql.length() - 1));
		}
		return sql.toString();
	}
}
