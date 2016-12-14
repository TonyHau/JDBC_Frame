import java.util.List;
import java.util.Map;

import com.az.ww.dao.WebDAO;

public class JDBCTest {
public static void main(String[] args) {
	String sql="select * from emp_test";
	try {
		List<Map<String,Object>> datas=WebDAO.query(sql);
		System.out.println("datas="+datas);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
