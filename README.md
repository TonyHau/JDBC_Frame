#JDBC�������ǳ�������õ����ݿ����Ӳ������

#1.�����Ӧ��jar��
#2.����Ŀ���½�һ��config�ļ�������jdbc-mysql.properties��jdbc-oracle.properties
#3.ֱ��ʹ��

```java
List<Map<String,Object>> datas=WebDAO.query(sql);
		System.out.println("datas="+datas);
```