package com.example.model;

import java.util.TreeMap;

public class User {

	private String firstName;
	private String lastName;
	private String nickname;
	private String email;
	private String password;
	private String selfDescription;
	private String photo;
	private TreeMap <Long, Diary> diaries;
	
	public User(String firstName, String lastName, String nickname, String email, String password, String description, String photo){//use this constructor when want to visualize profile
		setFirstName(firstName);
		setLastName(lastName);
		setNickname(nickname);
		setEmail(email);
		setPassword(password);		
		setSelfDescription(description);
		setPhoto(photo);
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
		if(desc!=null){
			this.selfDescription = desc;}
	}
	
	public String getSelfDescription(){
		return this.selfDescription;
	}

	public void setPhoto(String photo){
		if(photo!=null){
			this.photo = photo;
		}
	}
	
	public String getPhoto(){
		return this.photo;
	}
	public void setFirstName(String firstName) {
		if(firstName!=null){
		this.firstName = firstName;}
	}

	public void setLastName(String lastName) {
		if(firstName!=null){
		this.lastName = lastName;}
	}

	public void setNickname(String nickname) {
		if(firstName!=null){
		this.nickname = nickname;}
	}

	public void setEmail(String email) {
		if(firstName!=null){
		this.email = email;}
	}

	public void setPassword(String password) {
		if(firstName!=null){
		this.password = password;}
	}

	public TreeMap<Long, Diary> getDiaries() {
		return diaries;
	}

	public void setDiaries(TreeMap<Long, Diary> diaries) {
		if(diaries!=null){
		this.diaries = diaries;}
	}
	
	
}
