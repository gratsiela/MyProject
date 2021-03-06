package com.example.model;

import java.util.Date;

public class Note {
	private String title;
	private String content;
	private String status;
	private Date dateTime;
	private Long id;
	private User author;
	
	public Note(String title, String content, String status,Date dateTime,Long id,User author){// when take note from the database
		this.title=title;
		this.content=content;
		this.status=status;
		this.dateTime=dateTime;
		this.id = id;
		this.author=author;
	}
	
	public void setContent(String content){
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

	public String getStatus() {
		return status;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public long getId() {
		return id;
	}

	public void setTitle(String title) {
		if(title!=null){
		this.title = title;}
		else this.title="";
	}

	public void setStatus(String status) {
		if(status!=null){
		this.status = status;}
		else this.status="private";
	}

	public void setDateTime(Date dateTime) {
		if(dateTime!=null){
		this.dateTime = dateTime;}
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}
}
