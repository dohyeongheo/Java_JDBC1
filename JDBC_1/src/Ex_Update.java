import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ex_Update {

	public static void main(String[] args) {

		// 사용자로부터 필요한 정보 입력받기
		Scanner sc = new Scanner(System.in);
		System.out.print("아이디 : ");
		String id = sc.next();

		System.out.print("변경할 비밀번호 : ");
		String pw = sc.next();

		// JDBC 사용시 필요한 객체 생성하기 -> import 작업 필수
		Connection conn = null;
		PreparedStatement psmt = null;

		// 1. 동적로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			// Data source explorer 창에서 new oracle 우클릭 properties
			// connection : jdbc:oracle:thin:@localhost:1521:xe
			String db_id = "hr";
			String db_pw = "hr";

			conn = DriverManager.getConnection(url, db_id, db_pw);

			// 3. SQL문 작성
			// 아이디가 pbk인 회원의 비밀번호를 0128 수정하기
			String sql = "update memberInfo set pw = ? where id = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, pw);
			psmt.setString(2, id);

			psmt.executeUpdate();

			int cnt = psmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("정보 수정 완료");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("동적 로딩 오류");
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("db / sql 오류");
			e.printStackTrace();
		}
		// 4. 연결종료 -> conn, psmt
		finally {
			try {
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

	}
}
