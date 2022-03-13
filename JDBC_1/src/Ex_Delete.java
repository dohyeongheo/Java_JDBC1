import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ex_Delete {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement psmt = null;

		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 아이디 : ");
		String id = sc.next();
		System.out.print("삭제할 비밀번호 : ");
		String pw = sc.next();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";

			conn = DriverManager.getConnection(url, db_id, db_pw);

			String sql = "delete from memberInfo where id = ? and pw = ?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);
			psmt.setString(2, pw);

			int cnt = psmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("탈퇴 성공!");
			}

			// ClassNotFoundException가 아닌 exception으로 모든 오류 catch 가능
		} catch (Exception e) {
			System.out.println("오류가 발생했습니다!");
			e.printStackTrace();
		}

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
