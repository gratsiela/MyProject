package com.example.model;

import java.util.TreeMap;

public class Diary {

	private String name;
	private User author;
	private TreeMap<String,Note> notes;
	private Long id;
	
	public Diary(String name, User author, long id) {
		this.name=name;
		this.author=author;
		notes=new TreeMap<String,Note>();
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
	
	public TreeMap<String,Note> getNotes() {
		return notes;
	}
	
	public void setNotes(TreeMap<String,Note> notes){
		if(notes!=null){
			this.notes=notes;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if(id!=null){
		this.id = id;}
	}
	
	
	
}
