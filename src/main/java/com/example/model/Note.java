package com.example.model;

import java.time.LocalDateTime;

public class Note {
	private String title;
	private String content;
	private enum Status{Private, Public, Friends};//start with upper case because private and public are keywords
	private Status status;
	private LocalDateTime dateTime;
	private Long id;
	
	public Note(String title, String content, Status status) {// when create note
		this.title = title;
		setContent(content);
		this.status = status;
		this.dateTime = LocalDateTime.now();
	}
	
	public Note(String title, String content, Status status, Long id){// when take note from the database
		this(title, content, status);
		this.id = id;
	}
	
	void setContent(String content){
		if(content!=null){
			this.content = content;
		}
		else
			this.content = "";
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Status getStatus() {
		return status;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public long getId() {
		return id;
	}

	public void setTitle(String title) {
		if(title!=null){
		this.title = title;}
	}

	public void setStatus(Status status) {
		if(status!=null){
		this.status = status;}
	}

	public void setDateTime(LocalDateTime dateTime) {
		if(dateTime!=null){
		this.dateTime = dateTime;}
	}

	public void setId(Long id) {
		if(id!=null){
		this.id = id;}
	}
	
	
	
}
