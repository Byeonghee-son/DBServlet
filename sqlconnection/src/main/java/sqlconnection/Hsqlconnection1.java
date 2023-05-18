package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Hsqlconnection1 {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	private void insertData(String dno, String dname, int budget) {
		String sql = "insert into dept(dno,dname,budget) values (?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dno);
			ps.setString(2, dname);
			ps.setInt(3, budget);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("데이터베이스가 입력되었습니다.");
	}

	private boolean connectDB() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/telephone", "soctt", "tiger");
			System.out.println("데이터베이스가 연결되었습니다.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void closeDB() {
		try {
			con.close();
			System.out.println("데이터베이스가 닫혔습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void selectAll() {
		try {
			st = con.createStatement();
			rs = st.executeQuery("select dno, dname, budget from dept");
			while (rs.next()) {
				System.out.println(
						String.format("%s,%s,%d", rs.getString("dno"), rs.getString("dname"), rs.getInt("budget")));
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

	public static void main(String[] args) {
		Hsqlconnection1 tt = new Hsqlconnection1();
		if (tt.connectDB()) {
//			for(int i = 1; i < 101; i++) {
//				tt.insertData("D"+i, "dname"+i, i*100);	
//			}
//			tt.selectAll();
			tt.closeDB();
		}
	}
}
