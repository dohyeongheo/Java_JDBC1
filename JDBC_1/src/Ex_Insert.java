import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ex_Insert {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("[1]ȸ������ [2]�α��� [3]�������� [4]Ż�� [5]����");
			int choice = sc.nextInt();

			/// [1]
			if (choice == 1) {
				System.out.print("���̵� : ");
				String id = sc.next();

				System.out.print("��й�ȣ : ");
				String pw = sc.next();

				System.out.print("�̸� : ");
				String name = sc.next();

				System.out.print("���� : ");
				int age = sc.nextInt();

				// java���� �Է¹��� �������� Database�� �Ѱ��ֱ�

				// ������ ����Ǿ�� �ϴ� �۾�! -> JDBC�� ����ϱ� ���� ��! ��! �ʿ��� �ܰ�
				// JDBC�� �����ϰ��� �ϴ� javaProject�� ojdbc6.jar ���� �߰��ϱ�
				// ������Ʈ ���� ������ ��ư -> build path -> Configure Build Path
				// -> Libraries �� - add external jars - ojdbc6.jar

				// 1. ����̹� �����ε� -> try / catch �����ϱ�
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");

					// 2. DataBase ������ ���� �α��� ���� �����ϱ�
//			DriverManager.getConnection(������ �����ͺ��̽� �ּ�, ���� id , ���� pw)
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					String db_id = "hr";
					String db_pw = "hr";

					Connection conn = DriverManager.getConnection(url, db_id, db_pw);

					// conn�� int������ �ǵ�����
					if (conn != null) {
						System.out.println("�����ͺ��̽� ���� ����");
					}

					// 3. sql�� ����
					// ����ǥ�� ������ ���̺� ������ ������ ������ �����ؾ� ��
					String sql = "insert into memberInfo values(?, ?, ?, ?)";

//					����ǥ�� �νļ���
//					String sql = "insert into memberInfo values(?, '123', ?, ?)";
//					psmt.setString(1, id);
//					psmt.setString(, pw);
//					psmt.setString(2, name);
//					psmt.setInt(3, age);

					// conn�� ��� parepared.statement ���� ����
					PreparedStatement psmt = conn.prepareStatement(sql);

					// ����ڰ� �Է��� ����� sql���� �������ֱ�

					// db�� �ڷ��� ���¿� set�� ���°� �����ؾ���

					psmt.setString(1, id);
					psmt.setString(2, pw);
					psmt.setString(3, name);
					psmt.setInt(4, age);

					int cnt = psmt.executeUpdate(); // sql���� ������ ���� ��Ű�� ���� ���

					if (cnt > 0) {
						System.out.println("ȸ�� ���� ����!");
					}

					// 4. ���� ��ü�鿡 ���ؼ� ���� �����ϱ�
					// psmt�� ���� ���� conn�� �� ������ ����
					if (psmt != null) {
						psmt.close();
					}
					if (conn != null) {
						conn.close();
					}

				} catch (ClassNotFoundException e) {
					// class.forName()�� ���� catch��
					System.out.println("����̹� ����");
					e.printStackTrace();

				} catch (SQLException e) {
					// getConnection()�� ���� catch��
					System.out.println("DB �α��� ����");
					e.printStackTrace();
				}
			}

		}
	}

}
