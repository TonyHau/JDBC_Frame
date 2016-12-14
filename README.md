#JDBC轻量级非常方便好用的数据库连接操作框架

#1.添加相应的jar包
#2.再项目中新建一个config文件夹配置jdbc-mysql.properties或jdbc-oracle.properties
#3.直接使用

```java
List<Map<String,Object>> datas=WebDAO.query(sql);
		System.out.println("datas="+datas);
```