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

		// 레퍼런스형의 기본 초기값 -> null값
		ResultSet rs = null;
		PreparedStatement psmt = null;
		Connection conn = null;

		// 1. 드라이버 로딩 (class.forName)
		// - try / catch 문 작성

		Scanner sc = new Scanner(System.in);

		System.out.print("찾고싶은 아이디 : ");
		String search_id = sc.next();
		System.out.print("찾고싶은 비밀번호 : ");
		String search_pw = sc.next();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 데이터베이스 연결하기
			// db 접속 url, id, pw 작성
			// 심부름꾼 conn 객체 생성하기
			// conn 활용하여 정보 넘겨주기 (DriverManager -> getConnection())
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";

			conn = DriverManager.getConnection(url, db_id, db_pw);
			if (conn != null) {
				System.out.println("데이터베이스 연결 성공");
			}

			// 3. sql문 작성
			// memberInfo 테이블에 있는 모든 정보 출력하기
//			String sql_select = "select * from memberInfo";

			// id가 test이면서 pw가 123인 회원의 전체 정보 가져오기
			String sql_select = "select * from memberInfo where id = ? and pw = ?";
			psmt = conn.prepareStatement(sql_select);

			psmt.setString(1, search_id);
			psmt.setString(2, search_pw);

			// executeUpdate(); -> 삽입, 삭제, 수정
			// - 수행 이후 테이블의 값에 영향을 끼치는 것
			// executeQuery(); -> 조회
			// - 수행 전후가 바뀌지 않는 것

			// 반환값은 ResultSet 객체로 돌아온다. -> 테이블 형태로 돌아오는 것
			rs = psmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				int age = rs.getInt(4);
//				System.out.println("결과값 확인");
				System.out.println(id + " / " + pw + " / " + name + " / " + age);

			}

			// conn이 들고갈 pepared.statement 가방 생성

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 오류");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 로그인 오류");
			e.printStackTrace();
		}

		// try catch문 이후 마지막에 한번 객체 닫아주기
		// 4. 연결 종료

		finally {
			// 에러가 나던, 나지 않던, 무조건 실행해야 하는 부분!
			// 객체가 열린 순서의 역순으로 다시 닫아주기
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
