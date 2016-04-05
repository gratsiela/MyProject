package com.example.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.example.model.Diary;
import com.example.model.Note;
import com.example.model.User;
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
	
	public TreeMap<String,Note> getAllNotes(User user,Diary diary){
		TreeMap<String,Note> notes= new TreeMap<String,Note>();
		String query="SELECT note_id, title, content, date_time, status FROM diary.note WHERE diary_id = ?;";
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setLong(1, diary.getId());
			ResultSet rs=ps.executeQuery();
			
			if(rs==null){
				return notes;
			}
			
			while(rs.next()){
				Note note=new Note(rs.getString("title"), rs.getString("content"), rs.getString("status"), rs.getDate("date_time"),rs.getLong("note_id"), user);
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
	
	public TreeSet<Note> getAllPublicNotes(User user){
		TreeSet<Note> allPublicNotes=new TreeSet<Note>(new Comparator<Note>() {

			@Override
			public int compare(Note o1, Note o2) {
				if(o1.getDateTime().compareTo(o2.getDateTime())==0){
					return o1.getTitle().compareTo(o2.getTitle());
				}
				return o1.getDateTime().compareTo(o2.getDateTime());
			}
		});
		String query="SELECT note.note_id, note.title, note.content, note.date_time, note.status, users.user_email, users.first_name, users.last_name,users.nickname,users.self_description FROM diary.note JOIN  diary.belejnik ON (note.diary_id=belejnik.diary_id) JOIN diary.users ON (belejnik.user_email=users.user_email) WHERE users.user_email != ?;";
	
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			System.out.println(1);
			ps.setString(1, user.getEmail());
			System.out.println(2);
			ResultSet rs=ps.executeQuery();
			System.out.println(3);
			if(rs==null){
				return allPublicNotes;
			}
			System.out.println(4);
			while(rs.next()){
				System.out.println(5);
				User author=new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("nickname"), rs.getString("user_email"), "", rs.getString("self_description"));
				System.out.println(6);
				Note note=new Note(rs.getString("title"), rs.getString("content"), rs.getString("status"), rs.getDate("date_time"), rs.getLong("note_id"), author);
				allPublicNotes.add(note);
			}
		}catch(SQLException e){
			System.out.println("Problem with getAllPublicNotes()!");
		}
		return allPublicNotes;
	}
}
