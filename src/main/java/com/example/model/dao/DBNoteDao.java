package com.example.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.TreeMap;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.example.model.Diary;
import com.example.model.Note;
import com.example.model.db.DBManager;

public class DBNoteDao {

	private static DBNoteDao instance;
	private DBManager manager;
	
	private DBNoteDao(){
		manager=DBManager.getInstance();
	}
	
	public static DBNoteDao getInstance(){
		if(instance == null)
			instance = new DBNoteDao();
		return instance;
	}
	
	public TreeMap<String,Note> getAllNotes(Diary diary){
		TreeMap<String,Note> notes= new TreeMap<String,Note>();
		String query="SELECT note_id, title, content, date_time, status FROM diary.note WHERE diary_id = ?;";
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setLong(1, diary.getId());
			ResultSet rs=ps.executeQuery();
			
			if(rs==null){
				System.out.println("NULL");
				return notes;
			}
			
			while(rs.next()){
				Note note=new Note(rs.getString("title"), rs.getString("content"), rs.getString("status"), rs.getDate("date_time"),rs.getLong("note_id"));
				notes.put(note.getTitle(), note);
			}
		}catch(SQLException e){
			System.out.println("Problem with getAllNotes()!");
		}
		return notes;
	}
	
	public boolean addNote(Diary diary, Note note){
		String query="INSERT INTO diary.note (title, content, date_time, status, diary_id) VALUES (?,?,?,?,?);";
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setString(1, note.getTitle());
			ps.setString(2, note.getContent());
			ps.setTimestamp(3, new Timestamp(note.getDateTime().getTime()));
			ps.setString(4, note.getStatus());
			ps.setLong(5,diary.getId());
			ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("Problem with addNote()!");
			return false;
		}
		return true;
	}
	
	public boolean deleteNote(Note note){
		String query="DELETE FROM diary.note WHERE note_id = ?;";
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setLong(1, note.getId());
			ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("Problem with deleteNote!");
			return false;
		}
		return true;
	}
}
