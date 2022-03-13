package Jdbc_����;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

	// view(Main)�� Model(MemberDTO)�� �������ִ� �߰��ٸ�
	// Controller

	// DAO -> DATABASE ACCESS OBJECT
	// JDBC ���� �޼ҵ� ����

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int cnt = 0;

	// �����ε�, db������ ���� �޼ҵ�
	public void getCon() {
		try {
			// 1. �����ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB ����
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";

			conn = DriverManager.getConnection(url, db_id, db_pw);

			if (conn != null) {
				System.out.println("���� ����");
			}

		} catch (Exception e) {
			System.out.println("���� ����");
			e.printStackTrace();

		}

		// 3.
		// 4.
	}

	// ���� ���Ḧ ���� �޼ҵ�
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// ȸ�������� ���� �޼ҵ� ����
	public int insert(MemberDTO dto) {

		getCon();

		String sql = "insert into memberInfo values(?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getName());
			psmt.setInt(4, dto.getAge());

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;

	}

	// ��üȸ�� ��ȸ�� ���� �޼ҵ� ����

	public void selectAll() {

		getCon();

		String sql = "select * from memberInfo";

		try {
			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				int age = rs.getInt(4);

				System.out.println(id + " / " + pw + " / " + name + " / " + age);

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			close();
		}

	}
}
