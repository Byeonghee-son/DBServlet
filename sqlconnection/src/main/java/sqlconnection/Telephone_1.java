package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class Telephone_1 {
	

	private void insertContactWithPreparedStatement(int cid, int seq, String number, String type, Connection con) {
		String sql = "insert into phone (cid, seq, number, type) values(?,?,?,?)";
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setInt(2, seq);
			ps.setString(3, number);
			ps.setString(4, type);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private Connection connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Telephone", "scott", "tiger");
			System.out.println("데이터베이스가 연결되었습니다.");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void closeDB(Connection con) {
		try {
			con.close();
			System.out.println("데이터베이스가 닫혔습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Telephone_1 tp = new Telephone_1();
		Connection con = tp.connectDB();
		Random rand = new Random();
        
        String[] cates = {"cellular", "home", "workplace", "fax", "etc"};
        try {
        	int totcnt = 1000000;
        	int cid = 1;
            int seq = 1;
            for (int i = 1; i <= totcnt; i++) {
                
                String number = String.format("010%08d", rand.nextInt(100000000));
                String type = cates[rand.nextInt(5)];
                tp.insertContactWithPreparedStatement(cid, seq, number, type, con);
                seq++;
                if (seq > rand.nextInt(6)) {
                    cid++;
                    seq = 1;
                }
                System.out.println(String.format("%.2f:%d/%d", i * 100 / (double) totcnt, i, totcnt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tp.closeDB(con);
        }
    }
}