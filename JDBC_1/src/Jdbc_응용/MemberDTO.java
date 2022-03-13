package Jdbc_응용;

public class MemberDTO {

	// MVC >> 데이터를 관리하기 위한 Model - VO (Value Object)
	// DTO >> Data Transfer Object

	// Model == VO == DTO >> 공통점 : 데이터를 관리한다
	// 설계도의 역할을 수행

	private String id;
	private String pw;
	private String name;
	private int age;

	public MemberDTO(String id, String pw, String name, int age) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
