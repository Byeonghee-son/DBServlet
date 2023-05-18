package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Hsqlconnection {
	public static void main(String[] args) {
		Connection con = null;
		try {
			String driver ="org.h2.Driver";
			String url = "jdbc:h2:tcp://localhost/~/telephone";
			String username = "soctt";
			String password = "tiger";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			
				System.out.println("Connection Succes");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
