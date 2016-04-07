package com.example.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.example.model.Diary;
import com.example.model.Note;
import com.example.model.User;
import com.example.model.db.DBManager;

import scala.reflect.internal.annotations.compileTimeOnly;

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
	
	public TreeMap<Long,Note> getAllNotes(User user,Diary diary){
		TreeMap<Long,Note> notes= new TreeMap<Long,Note>();
		String query="SELECT note_id, title, content, date_time, status FROM diary.notes WHERE diary_id = ?;";
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setLong(1, diary.getId());
			ResultSet rs=ps.executeQuery();
			
			if(rs==null){
				return notes;
			}
			
			while(rs.next()){
				Note note=new Note(rs.getString("title"), rs.getString("content"), rs.getString("status"), rs.getDate("date_time"),rs.getLong("note_id"), user);
				notes.put(note.getId(), note);
			}
		}catch(SQLException e){
			System.out.println("Problem with getAllNotes()!");
		}
		return notes;
	}
	
	public boolean addNote(Diary diary, Note note){
		String query="INSERT INTO diary.notes (title, content, date_time, status, diary_id) VALUES (?,?,?,?,?);";
		
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
		String query="DELETE FROM diary.notes WHERE note_id = ?;";
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setLong(1, note.getId());
			ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("Problem with deleteNote!");
			return false;
		}
		return true;
	}
	
	public TreeMap<Long,Note> getAllPublicNotes(User signedUser){
		TreeMap<Long,Note> allPublicNotes=new TreeMap<Long,Note>(new Comparator<Long>(){
			
			@Override
			public int compare(Long arg0, Long arg1) {
				return (arg1.compareTo(arg0));
			}});
		
		String query="SELECT notes.note_id, notes.title, notes.content, notes.date_time, notes.status, users.user_email, users.first_name, users.last_name, users.nickname, users.pass, users.self_description, users.photo FROM diary.notes JOIN  diary.diaries ON (notes.diary_id=diaries.diary_id) JOIN diary.users ON (diaries.user_email=users.user_email) WHERE notes.status = ? AND users.user_email != ?;";
	
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setString(1, "public");
			ps.setString(2, signedUser.getEmail());
			ResultSet rs=ps.executeQuery();
			
			if(rs==null){
				return allPublicNotes;
			}
			
			User otherUser=null;
			while(rs.next()){
				otherUser=new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("nickname"), rs.getString("user_email"), rs.getString("pass"), rs.getString("self_description"), rs.getString("photo"));
				Note note=new Note(rs.getString("title"), rs.getString("content"), rs.getString("status"), rs.getDate("date_time"), rs.getLong("note_id"),otherUser);
				allPublicNotes.put(note.getId(), note);
			}
		}catch(SQLException e){
			System.out.println("Problem with getAllPublicNotes()!");
			e.getMessage();
		}
		return allPublicNotes;
	}
	
//	public Note getPublicNote(Long noteID){
//		String query =" SELECT notes.note_id, notes.title, notes.content, notes.date_time, notes.status, users.user_email, users.first_name, users.last_name, users.nickname, users.pass, users.self_description, users.photo FROM diary.notes JOIN  diary.diaries ON (notes.diary_id=diaries.diary_id) JOIN diary.users ON (diaries.user_email=users.user_email) WHERE notes.note_id = ?;";
//		User user=null;
//		Note note=null;
//		try(PreparedStatement ps= manager.getConnection().prepareStatement(query)){
//			ps.setLong(1,noteID);
//			ResultSet rs=ps.executeQuery();
//			
//			if(rs==null){
//				return note;
//			}
//			
//			while (rs.next()){
//				user=new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("nickname"), rs.getString("email"), rs.getString("pass"), rs.getString("self_description"), rs.getString("photo"));
//				note=new Note(rs.getString("title"), rs.getString("content"), rs.getString("status"), rs.getDate("date_time"), rs.getLong("note_id"),user);
//				return note;
//			}
//		}catch(SQLException e){
//			System.out.println("Problem with getPublicNote()!");
//		}
//		return note;
//	}
	
	public TreeMap<Long,Note> getFollowedUsersPublicNotes(User user){
		TreeMap<Long,Note> followedUsersPublicNotes=new TreeMap<Long,Note>(new Comparator<Long>(){

			@Override
			public int compare(Long arg0, Long arg1) {
				return (arg1.compareTo(arg0));
			}});
		
		String query="SELECT notes.note_id, notes.title, notes.content, notes.date_time, notes.status, users.user_email, users.first_name, users.last_name, users.nickname, users.pass, users.self_description, users.photo FROM diary.notes JOIN  diary.diaries ON (notes.diary_id=diaries.diary_id) JOIN diary.users ON (diaries.user_email=users.user_email) WHERE notes.status = ? AND users.user_email != ?;";
	
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setString(1, "public");
			ps.setString(2, user.getEmail());
			ResultSet rs=ps.executeQuery();
			if(rs==null){
				return followedUsersPublicNotes;
			}
			while(rs.next()){
				User followedUser=new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("nickname"), rs.getString("email"), rs.getString("pass"), rs.getString("self_description"), rs.getString("photo"));
				Note note=new Note(rs.getString("title"), rs.getString("content"), rs.getString("status"), rs.getDate("date_time"), rs.getLong("note_id"), followedUser);
				followedUsersPublicNotes.put(note.getId(), note);
			}
		}catch(SQLException e){
			System.out.println("Problem with getFollowedUsersPublicNotes()!");
		}
		return followedUsersPublicNotes;
	}
}
