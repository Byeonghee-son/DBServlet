package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlconnection1 {
	public static void main(String[] args) {
		//변수를 밖으로 빼서 초기화  : 밑에 try나 catch에서 쓰기위해
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "scott", "tiger");

			st = con.createStatement();
			rs = st.executeQuery("Select sno, pno, jno, qty from spj order by sno");
			
			//커서 프로세싱
			while (rs.next()) {  //문자열은 %s 문자1개 %c int는 %d long은 %l float,double 은 %f
				System.out.println(String.format("%s,%s,%s,%d", rs.getString("sno"), rs.getString("pno"),
						rs.getString("jno"), rs.getInt("qty")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
