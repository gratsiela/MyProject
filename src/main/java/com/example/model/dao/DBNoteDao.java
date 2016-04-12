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
	
	private DBNoteDao() throws ClassNotFoundException, SQLException{
		manager=DBManager.getInstance();
	}
	
	public static DBNoteDao getInstance() throws ClassNotFoundException, SQLException{
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
			java.sql.Date sqlDate=new java.sql.Date(note.getDateTime().getTime());
			ps.setDate(3, sqlDate);
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
	
	public boolean editNote(Note note, String title, String content, java.util.Date date, String status){
		String query="UPDATE notes SET title = ? , content = ?, date_time = ? , status = ? WHERE note_id = ?;";
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setString(1, title);
			ps.setString(2, content);
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			ps.setDate(3, sqlDate);
			ps.setString(4, status);
			ps.setLong(5, note.getId());
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Problem with editNote()!");
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
		
		String query="SELECT notes.note_id, notes.title, notes.content, notes.date_time, notes.status, users.user_email, users.first_name, users.last_name, users.nickname, users.pass, users.self_description, users.photo FROM diary.notes JOIN  diary.diaries ON (notes.diary_id=diaries.diary_id) JOIN diary.users ON (diaries.user_email=users.user_email) WHERE notes.status = ? AND users.user_email != ?;";;
	
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
		
		String query="SELECT note_id, title, content, date_time, status, users.user_email, first_name, last_name, nickname, pass, self_description, photo FROM diary.notes JOIN diary.diaries ON(notes.diary_id=diaries.diary_id) JOIN diary.users ON(diaries.user_email=users.user_email) JOIN diary.friends ON(users.user_email=friends.email2) WHERE notes.status= ? AND friends.email1= ?;";
	
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setString(1, "public");
			ps.setString(2, user.getEmail());
			ResultSet rs=ps.executeQuery();
			
			if(rs==null){
				return followedUsersPublicNotes;
			}
			
			while(rs.next()){
				User followedUser=new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("nickname"), rs.getString("user_email"), rs.getString("pass"), rs.getString("self_description"), rs.getString("photo"));
				Note note=new Note(rs.getString("title"), rs.getString("content"), rs.getString("status"), rs.getDate("date_time"), rs.getLong("note_id"), followedUser);
				followedUsersPublicNotes.put(note.getId(), note);
			}
		}catch(SQLException e){
			System.out.println("Problem with getFollowedUsersPublicNotes()!");
		}
		return followedUsersPublicNotes;
	}
	
	public TreeMap<Long,Note> search(User signedUser, String searchedWords){
		TreeMap<Long,Note> searchedPublicNotes=new TreeMap<Long,Note>(new Comparator<Long>(){
			
			@Override
			public int compare(Long arg0, Long arg1) {
				return (arg1.compareTo(arg0));
			}});
		
		String[] array =searchedWords.split("\\s+");
		
		String insert=new String();
		for(int i =1; i<array.length;i++){
			insert =insert +"? OR title LIKE ";
		}
		
		String query="SELECT notes.note_id, notes.title, notes.content, notes.date_time, notes.status, users.user_email, users.first_name, users.last_name, users.nickname, users.pass, users.self_description, users.photo FROM diary.notes JOIN  diary.diaries ON (notes.diary_id=diaries.diary_id) JOIN diary.users ON (diaries.user_email=users.user_email) WHERE ( title LIKE "+insert+" ?) AND notes.status= ? AND users.user_email != ?;";
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			for(int i=0;i<array.length;i++){
				ps.setString(i+1, array[i]);
			}
			ps.setString(array.length + 1, "public");
			ps.setString(array.length + 2,signedUser.getEmail());
			System.out.println(ps);
			ResultSet rs=ps.executeQuery();
			System.out.println(4);
			System.out.println(rs);
			
			if(rs==null){
				return searchedPublicNotes;
			}
			
			User otherUser=null;
			while(rs.next()){
				otherUser=new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("nickname"), rs.getString("user_email"), rs.getString("pass"), rs.getString("self_description"), rs.getString("photo"));
				Note note=new Note(rs.getString("title"), rs.getString("content"), rs.getString("status"), rs.getDate("date_time"), rs.getLong("note_id"),otherUser);
				searchedPublicNotes.put(note.getId(), note);
			}
		}catch(SQLException e){
			System.out.println("Problem with search()!");
			e.getMessage();
		}
		
		return searchedPublicNotes;
	}
}
