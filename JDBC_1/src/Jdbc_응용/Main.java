package Jdbc_����;

import java.awt.Choice;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// ����ڰ� ����� �� �ִ� view �����ϱ�
		// MemberDAO ������ ���� ��ü ����

		MemberDAO dao = new MemberDAO();

		// ����� Ȯ���� ���� ���� ����
		int result = 0;

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("[1]���� [2]��ȸ [3]���� [4]Ż�� [5]���� >> ");
			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("=== ���� ===");

				System.out.print("���� ���̵� : ");
				String id = sc.next();
				System.out.print("���� ��й�ȣ : ");
				String pw = sc.next();
				System.out.print("���� �̸� : ");
				String name = sc.next();
				System.out.print("���� ���� : ");
				int age = sc.nextInt();

				MemberDTO dto = new MemberDTO(id, pw, name, age);

				// JDBC�� ������ Ŭ����(MemberDAO)�κ��� �޼ҵ� ȣ��
				result = dao.insert(dto);

				if (result > 0) {
					System.out.println("ȸ������ ����");
				} else {
					System.out.println("ȸ������ ����");
				}

			} else if (choice == 2) {
				System.out.println("=== ��ȸ ===");
				dao.selectAll();

			} else if (choice == 3) {
				System.out.println("=== ���� ===");

			} else if (choice == 4) {
				System.out.println("=== Ż�� ===");

			} else if (choice == 5) {
				System.out.println("=== ���α׷� ���� ===");
				break;

			}
		}

	}

}
