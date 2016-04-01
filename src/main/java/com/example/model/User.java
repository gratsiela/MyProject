package com.example.model;

public class User {

	private String firstName;
	private String lastName;
	private String nickname;
	private String email;
	private String password;
	
	public User(String firstName, String lastName, String nickname, String email, String password) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.nickname=nickname;
		this.email=email;
		this.password=password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
