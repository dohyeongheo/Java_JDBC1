import java.security.Identity;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ex_Select {

	public static void main(String[] args) {

		// ���۷������� �⺻ �ʱⰪ -> null��
		ResultSet rs = null;
		PreparedStatement psmt = null;
		Connection conn = null;

		// 1. ����̹� �ε� (class.forName)
		// - try / catch �� �ۼ�

		Scanner sc = new Scanner(System.in);

		System.out.print("ã����� ���̵� : ");
		String search_id = sc.next();
		System.out.print("ã����� ��й�ȣ : ");
		String search_pw = sc.next();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. �����ͺ��̽� �����ϱ�
			// db ���� url, id, pw �ۼ�
			// �ɺθ��� conn ��ü �����ϱ�
			// conn Ȱ���Ͽ� ���� �Ѱ��ֱ� (DriverManager -> getConnection())
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";

			conn = DriverManager.getConnection(url, db_id, db_pw);
			if (conn != null) {
				System.out.println("�����ͺ��̽� ���� ����");
			}

			// 3. sql�� �ۼ�
			// memberInfo ���̺� �ִ� ��� ���� ����ϱ�
//			String sql_select = "select * from memberInfo";

			// id�� test�̸鼭 pw�� 123�� ȸ���� ��ü ���� ��������
			String sql_select = "select * from memberInfo where id = ? and pw = ?";
			psmt = conn.prepareStatement(sql_select);

			psmt.setString(1, search_id);
			psmt.setString(2, search_pw);

			// executeUpdate(); -> ����, ����, ����
			// - ���� ���� ���̺��� ���� ������ ��ġ�� ��
			// executeQuery(); -> ��ȸ
			// - ���� ���İ� �ٲ��� �ʴ� ��

			// ��ȯ���� ResultSet ��ü�� ���ƿ´�. -> ���̺� ���·� ���ƿ��� ��
			rs = psmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				int age = rs.getInt(4);
//				System.out.println("����� Ȯ��");
				System.out.println(id + " / " + pw + " / " + name + " / " + age);

			}

			// conn�� ��� pepared.statement ���� ����

		} catch (ClassNotFoundException e) {
			System.out.println("����̹� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB �α��� ����");
			e.printStackTrace();
		}

		// try catch�� ���� �������� �ѹ� ��ü �ݾ��ֱ�
		// 4. ���� ����

		finally {
			// ������ ����, ���� �ʴ�, ������ �����ؾ� �ϴ� �κ�!
			// ��ü�� ���� ������ �������� �ٽ� �ݾ��ֱ�
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
	}
}
