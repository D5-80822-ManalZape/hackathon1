package com.sunbeam;

import java.util.Date;

public class Users {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String mobile;
	private Date birth;
	
	public Users() {
		// TODO Auto-generated constructor stub
	}

	public Users(int id, String firstName, String lastName, String email, String password, String mobile, Date date) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.birth = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName.length()==0) {
			return;
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName.length()==0) {
			return;
		}
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email.length()==0) {
			return;
		}
		this.email = email;
	}

	public String getPassword() {
		
		return password;
	}

	public void setPassword(String password) {
		if (password.length()==0) {
			return;
		}
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		if (mobile.length()==0 && mobile.length()==10) {
			return;
		}
		this.mobile = mobile;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", mobile=" + mobile + ", birth=" + birth + "]";
	}
	
	
}
