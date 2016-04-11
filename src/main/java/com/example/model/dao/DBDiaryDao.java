package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

import org.neo4j.cypher.internal.compiler.v2_1.perty.docbuilders.simpleDocBuilder;

import com.example.model.Diary;
import com.example.model.Note;
import com.example.model.User;
import com.example.model.db.DBManager;

public class DBDiaryDao {

	private static DBDiaryDao instance;
	private DBManager manager;
	
	private DBDiaryDao(){
		manager=DBManager.getInstance();
	}
	
	public static DBDiaryDao getInstance(){
		if(instance == null)
			instance = new DBDiaryDao();
		return instance;
	}
	
	public TreeMap<Long,Diary> getAllDiaries(User user){
		TreeMap<Long,Diary> diaries=new TreeMap<Long,Diary>();
		String query="SELECT diary_id, name FROM diary.diaries where user_email = ?;";
		if(user != null){
			try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
				ps.setString(1, user.getEmail());
				ResultSet rs=ps.executeQuery();
				if(rs==null){
					System.out.println(3);
					return diaries;
				}
				
				while(rs.next()){
					Diary diary= new Diary(rs.getString("name"), rs.getLong("diary_id"), user);
					diaries.put(diary.getId(),diary);
				}
			}
			catch(SQLException e){
				System.out.println("Problem with getAllDiaries()!");
			}
		}
		return diaries;
	}
	
	public boolean addDiary(User x, String diaryName){
		String query="INSERT INTO diary.diaries (name,user_email) VALUES (?,?);";
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
		ps.setString(1, diaryName);
		ps.setString(2, x.getEmail());
		ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("Problem with addDiary()!");
			return false;
		}
		return true;	
	}
	
	public boolean deleteDiary(Long diaryID){
		String query="DELETE FROM diary.diaries WHERE diary_id = ?;";
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setLong(1, diaryID);
			ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("Problem with deleteDiary()!");
			return false;
		}
		return true;
	}
}
