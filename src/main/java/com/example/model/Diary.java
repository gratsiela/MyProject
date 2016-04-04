package com.example.model;

import java.util.TreeSet;

public class Diary {

	private String name;
	private User author;
	private TreeSet<Note> notes;
	private Long id;
	
	public Diary(String name, User author, long id) {
		this.name=name;
		this.author=author;
		notes=new TreeSet<>();
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name!=null){
		this.name = name;}
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		if(author!=null){
		this.author = author;}
	}

	public TreeSet<Note> getNotes() {
		return notes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if(id!=null){
		this.id = id;}
	}
	
	
	
}
