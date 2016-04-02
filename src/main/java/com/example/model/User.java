package com.example.model;

public class User {

	private String firstName;
	private String lastName;
	private String nickname;
	private String email;
	private String password;
	private String selfDescription;
	
	
	public User(String firstName, String lastName, String nickname, String email, String password) {// use this constructor to register
		this.firstName=firstName;
		this.lastName=lastName;
		this.nickname=nickname;
		this.email=email;
		this.password=password;
	}
	
	public User(String firstName, String lastName, String nickname, String email, String password, String description){//use this constructor when want to visualize profile
		this(firstName, lastName, nickname, email, password);
		setSelfDescription(description);
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
	
	public void setSelfDescription(String desc){
		if(desc!=null)
			this.selfDescription = desc;
		else
			this.selfDescription = "";
	}
	
	public String getSelfDescription(){
		return this.selfDescription;
	}
}
