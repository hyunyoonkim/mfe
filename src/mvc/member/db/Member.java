package mvc.member.db;

import java.util.Date;

/*  데이터 빈 클래스 작성
 *  게시판에서 사용되는 정보들을 데이터 빈이라는 하나의 객체에 저장하게 되고
 *  이 객체를 전달하면 각 정보를 하나씩 전달할 필요가 없으며
 *  한꺼번에 모든 정보가 전달된다.
 *  이런 클래스를 DTO(Data Transfer Object), VO(Value Object)라고 한다.
 *  DB에서 만들었던 컬럼명과 동일하게 프로퍼티들을 생성한다.
 * */
public class Member {
	private String id;
	private String password;
	private String name;
	private String address;
	private String phone_number;
	private String preference;
	private String gender;
	
	public String getAddress() {
		return address;
	}
	public String getPreference() {
		return preference;
	}
	public void setPreference(String preference) {
		this.preference = preference;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
