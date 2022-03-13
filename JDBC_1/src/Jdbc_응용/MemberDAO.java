package Jdbc_응용;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

	// view(Main)와 Model(MemberDTO)를 연결해주는 중간다리
	// Controller

	// DAO -> DATABASE ACCESS OBJECT
	// JDBC 연결 메소드 생성

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int cnt = 0;

	// 동적로딩, db연결을 위한 메소드
	public void getCon() {
		try {
			// 1. 동적로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";

			conn = DriverManager.getConnection(url, db_id, db_pw);

			if (conn != null) {
				System.out.println("연결 성공");
			}

		} catch (Exception e) {
			System.out.println("연결 오류");
			e.printStackTrace();

		}

		// 3.
		// 4.
	}

	// 연결 종료를 위한 메소드
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

	// 회원가입을 위한 메소드 생성
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

	// 전체회원 조회를 위한 메소드 생성

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
