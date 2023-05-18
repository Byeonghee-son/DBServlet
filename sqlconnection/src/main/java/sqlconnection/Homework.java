package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

class QueryExe {
	int num;
	String text;

	QueryExe(int num, String text) {
		this.num = num;
		this.text = text;
	}

	int getNum() {
		return num;
	}

	String getText() {
		return text;
	}
}

public class Homework {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	private boolean connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "scott", "tiger");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void closeDB() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void exe01() {
		try {
			String sql = "SELECT jname FROM j WHERE city = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "London");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(String.format("%s", rs.getString("jname")));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void exe02() {
		try {
			String sql = "Select sname from s,spj where s.sno = spj.sno and jno = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "j1");
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(String.format("%s", rs.getString("sname")));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void exe03() {
		try {
			String sql = "Select sno,pno,qty from spj where  qty between ? and ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, 300);
			ps.setInt(2, 750);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out
						.println(String.format("%s,%s,%d", rs.getString("sno"), rs.getString("pno"), rs.getInt("qty")));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void exe04() {
		try {
			String sql = "Select distinct city , color from p";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(String.format("%s,%s", rs.getString("city"), rs.getString("color")));
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void exe05() {
		try {
			String sql = "select s.sno, p.pno, j.jno, s.city from s, p, j, spj where s.sno = spj.sno and p.pno = spj.pno and j.jno = spj.jno and s.city = p.city and p.city = j.city";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(String.format("%s,%s,%s,%s", rs.getString("sno"), rs.getString("pno"),
						rs.getString("jno"), rs.getString("city")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void exe06() {
		try {
			String sql = "select s.sno, p.pno, j.jno from s, p, j, spj where s.sno = spj.sno and p.pno = spj.pno and j.jno = spj.jno and s.city <> p.city and p.city <> j.city";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(
						String.format("%s,%s,%s", rs.getString("sno"), rs.getString("pno"), rs.getString("jno")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private void exe07() {
		try {
			String sql = "select p.pno, pname from s, p, spj where s.sno = spj.sno and p.pno = spj.pno and s.city = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "London");
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(
						String.format("%s,%s", rs.getString("pno"), rs.getString("pname")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private void exe08() {
		try {
			String sql = "select p.pno, pname from s, p, j, spj where s.sno = spj.sno and p.pno = spj.pno and j.jno = spj.jno and s.city = ? and j.city = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "London");
			ps.setString(2, "London");
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(
						String.format("%s,%s", rs.getString("pno"), rs.getString("pname")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private void exe09() {
		try {
			String sql = "select distinct s.city, j.city from s, j, spj where s.sno = spj.sno and j.jno = spj.jno and s.city <> j.city";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(
						String.format("%s,%s", rs.getString("s.city"), rs.getString("j.city")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private void exe10() {
		try {
			String sql = "select distinct p.pno, pname from s, p, j, spj where s.sno = spj.sno and j.jno = spj.jno and p.pno = spj.pno and s.city = j.city";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(
						String.format("%s,%s", rs.getString("p.pno"), rs.getString("pname")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private void exe11() {
		try {
			String sql = "select distinct j.jno, jname from s, j, spj where s.sno = spj.sno and j.jno = spj.jno and s.city <> j.city and s";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(String.format("%s,%s", rs.getString("j.jno"), rs.getString("jname")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void exe12() {
		try {
			String sql = "select spjx.pno, spjy.pno from spj spjx, spj spjy where spjx.sno = spjy.sno and spjx.pno > spjy.pno";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(String.format("%s,%s", rs.getString("spjx.pno"), rs.getString("spjy.pno")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void exe13() {
		try {
			String sql = "select count(jno) from spj where sno = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "s1");
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(String.format("%d", rs.getInt("count(jno)")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void exe14() {
		try {
			String sql = "select sum(qty) from spj where sno = ? and pno = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "s1");
			ps.setString(2, "p1");
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(String.format("%d", rs.getInt("sum(qty)")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void executeQuery(int num) {
		switch (num) {
		case 1: exe01(); break;
		case 2: exe02(); break;
		case 3: exe03(); break;
		case 4: exe04(); break;
		case 5: exe05(); break;
		case 6: exe06(); break;
		case 7: exe07(); break;
		case 8: exe08(); break;
		case 9: exe09(); break;
		case 10: exe10(); break;
		case 11: exe11(); break;
		case 12: exe12(); break;
		case 13: exe13(); break;
		case 14: exe14(); break;
		default:
			System.out.println("잘못된 선택입니다.");
			break;
		}
	}

	public static void main(String[] args) {
		Homework hw = new Homework();
		ArrayList<QueryExe> list = new ArrayList<>();

		list.add(new QueryExe(1, "London에 있는 프로젝트 이름을 찾아라."));
		list.add(new QueryExe(2, "프로젝트 j1에 참여하는 공급자의 이름을 찾아라."));
		list.add(new QueryExe(3, "공급 수량이 300이상 750이하인 모든 공급의 sno,pno,qty를 찾아라."));
		list.add(new QueryExe(4, "부품의 color와 city의 모든 쌍을 찾아라. 같은 쌍은 한번만 검색되어야 한다."));
		list.add(new QueryExe(5, "같은 도시에 있는 공급자, 부품, 프로젝트의 모든 sno,pno,jno쌍을 찾아라. 찾아진 sno,pno,jno의 city들은 모두 같아야한다"));
		list.add(new QueryExe(6, "같은 도시에 있는 않는 공급자, 부품, 프로젝트의 모든 sno,pno,jno쌍을 찾아라. 찾아진 sno,pno,jno의 city들은 같은것이 없어야 한다."));
		list.add(new QueryExe(7, "London에 있는 공급자에 의해 공급된 부품의 번호, 이름을 찾아라."));
		list.add(new QueryExe(8, "London에 있는 공급자가 London의 프로젝트에 공급한 부품의 번호, 이름을 찾아라."));
		list.add(new QueryExe(9, "한 도시에 있는 공급자가 다른 도시에 있는 프로젝트에 공급할 때 공급자 도시, 프로젝트 도시 쌍을 모두 구하라"));
		list.add(new QueryExe(10, "한 도시에 있는 공급자가 같은 도시에 있는 프로젝트에 공급하는 부품의 부품번호와 이름을 찾아라"));
		list.add(new QueryExe(11, "같은 도시에 있지 않은, 적어도 한 명 이상의 공급자들에 의해 공급되고 있는 프로젝트의 번호와 이름을 찾아라"));
		list.add(new QueryExe(12, "어떤 공급자가 2개 이상의 부품을 공급할 때 공급된 부품 sno의 리스트를 찾아라. 부품이 1개 공급한 경우는 제외한다."));
		list.add(new QueryExe(13, "공급자 s1이 공급한 프로젝트 개수를 찾아라."));
		list.add(new QueryExe(14, "공급자 s1이 공급한 부품 p1의 전체 수량을 찾아라."));
		
		
		Scanner sc = new Scanner(System.in);
		while (true) {

			for (QueryExe qe : list) {
				System.out.println(String.format("%d. %s", qe.getNum(), qe.getText()));
			}

			System.out.print("선택<0:exit>:");
			int sel = sc.nextInt();
			if (sel == 0)
				break;

			hw.connectDB();
			hw.executeQuery(sel);
			hw.closeDB();
		}
	}

}
