package Jdbc_응용;

import java.awt.Choice;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// 사용자가 사용할 수 있는 view 설계하기
		// MemberDAO 접근을 위한 객체 생성

		MemberDAO dao = new MemberDAO();

		// 결과값 확인을 위한 변수 선언
		int result = 0;

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("[1]가입 [2]조회 [3]수정 [4]탈퇴 [5]종료 >> ");
			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("=== 가입 ===");

				System.out.print("가입 아이디 : ");
				String id = sc.next();
				System.out.print("가입 비밀번호 : ");
				String pw = sc.next();
				System.out.print("가입 이름 : ");
				String name = sc.next();
				System.out.print("가입 나이 : ");
				int age = sc.nextInt();

				MemberDTO dto = new MemberDTO(id, pw, name, age);

				// JDBC가 정리된 클래스(MemberDAO)로부터 메소드 호출
				result = dao.insert(dto);

				if (result > 0) {
					System.out.println("회원가입 성공");
				} else {
					System.out.println("회원가입 실패");
				}

			} else if (choice == 2) {
				System.out.println("=== 조회 ===");
				dao.selectAll();

			} else if (choice == 3) {
				System.out.println("=== 수정 ===");

			} else if (choice == 4) {
				System.out.println("=== 탈퇴 ===");

			} else if (choice == 5) {
				System.out.println("=== 프로그램 종료 ===");
				break;

			}
		}

	}

}
