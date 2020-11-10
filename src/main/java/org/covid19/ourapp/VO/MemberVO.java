package org.covid19.ourapp.VO;

public class MemberVO {
  
	private String firstName;
	private String lastName;
	private String nickName;
	private String email;
	private String password;
	
	public MemberVO() {
		
	}




	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




	@Override
	public String toString() {
		return "MemberVO [firstName=" + firstName + ", lastName=" + lastName + ", nickName=" + nickName + ", email="
				+ email + ", password=" + password + "]";
	}
	
}
