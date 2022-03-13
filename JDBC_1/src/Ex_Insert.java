import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ex_Insert {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("[1]회원가입 [2]로그인 [3]정보수정 [4]탈퇴 [5]종료");
			int choice = sc.nextInt();

			/// [1]
			if (choice == 1) {
				System.out.print("아이디 : ");
				String id = sc.next();

				System.out.print("비밀번호 : ");
				String pw = sc.next();

				System.out.print("이름 : ");
				String name = sc.next();

				System.out.print("나이 : ");
				int age = sc.nextInt();

				// java에서 입력받은 정보들을 Database로 넘겨주기

				// 사전에 수행되어야 하는 작업! -> JDBC를 사용하기 위해 꼭! 꼭! 필요한 단계
				// JDBC를 연결하고자 하는 javaProject에 ojdbc6.jar 파일 추가하기
				// 프로젝트 파일 오른쪽 버튼 -> build path -> Configure Build Path
				// -> Libraries 탭 - add external jars - ojdbc6.jar

				// 1. 드라이버 동적로딩 -> try / catch 진행하기
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");

					// 2. DataBase 접근을 위한 로그인 정보 전송하기
//			DriverManager.getConnection(접속할 데이터베이스 주소, 접근 id , 접근 pw)
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					String db_id = "hr";
					String db_pw = "hr";

					Connection conn = DriverManager.getConnection(url, db_id, db_pw);

					// conn을 int형으로 되돌려줌
					if (conn != null) {
						System.out.println("데이터베이스 연결 성공");
					}

					// 3. sql문 전송
					// 물음표의 갯수는 테이블 생성시 지정한 갯수와 동일해야 함
					String sql = "insert into memberInfo values(?, ?, ?, ?)";

//					물음표의 인식순서
//					String sql = "insert into memberInfo values(?, '123', ?, ?)";
//					psmt.setString(1, id);
//					psmt.setString(, pw);
//					psmt.setString(2, name);
//					psmt.setInt(3, age);

					// conn이 들고갈 parepared.statement 가방 생성
					PreparedStatement psmt = conn.prepareStatement(sql);

					// 사용자가 입력한 내용들 sql문에 연결해주기

					// db내 자료의 형태와 set의 형태가 동일해야함

					psmt.setString(1, id);
					psmt.setString(2, pw);
					psmt.setString(3, name);
					psmt.setInt(4, age);

					int cnt = psmt.executeUpdate(); // sql문을 실제로 실행 시키기 위한 명령

					if (cnt > 0) {
						System.out.println("회원 가입 성공!");
					}

					// 4. 사용된 객체들에 대해서 연결 종료하기
					// psmt가 먼저 종료 conn이 그 다음에 종료
					if (psmt != null) {
						psmt.close();
					}
					if (conn != null) {
						conn.close();
					}

				} catch (ClassNotFoundException e) {
					// class.forName()에 관한 catch문
					System.out.println("드라이버 오류");
					e.printStackTrace();

				} catch (SQLException e) {
					// getConnection()에 관한 catch문
					System.out.println("DB 로그인 오류");
					e.printStackTrace();
				}
			}

		}
	}

}
