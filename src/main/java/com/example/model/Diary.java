package com.example.model;

import java.util.TreeMap;

public class Diary {

	private String name;
	private TreeMap<Long,Note> notes;
	private long id;
	private User author;
	
	public Diary(String name, long id, User author) {
		setName(name);
		setId(id);
		setAuthor(author);
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
	
	public TreeMap<Long,Note> getNotes() {
		return notes;
	}
	
	public void setNotes(TreeMap<Long,Note> notes){
		if(notes!=null){
			this.notes=notes;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		if(id!=null){
		this.id = id;}
	}	
}
