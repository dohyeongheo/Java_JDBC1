import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ex_Update {

	public static void main(String[] args) {

		// ����ڷκ��� �ʿ��� ���� �Է¹ޱ�
		Scanner sc = new Scanner(System.in);
		System.out.print("���̵� : ");
		String id = sc.next();

		System.out.print("������ ��й�ȣ : ");
		String pw = sc.next();

		// JDBC ���� �ʿ��� ��ü �����ϱ� -> import �۾� �ʼ�
		Connection conn = null;
		PreparedStatement psmt = null;

		// 1. �����ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB ����
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			// Data source explorer â���� new oracle ��Ŭ�� properties
			// connection : jdbc:oracle:thin:@localhost:1521:xe
			String db_id = "hr";
			String db_pw = "hr";

			conn = DriverManager.getConnection(url, db_id, db_pw);

			// 3. SQL�� �ۼ�
			// ���̵� pbk�� ȸ���� ��й�ȣ�� 0128 �����ϱ�
			String sql = "update memberInfo set pw = ? where id = ?";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, pw);
			psmt.setString(2, id);

			psmt.executeUpdate();

			int cnt = psmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("���� ���� �Ϸ�");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("���� �ε� ����");
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("db / sql ����");
			e.printStackTrace();
		}
		// 4. �������� -> conn, psmt
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
