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
	
	public TreeMap<String,Diary> getAllDiaries(User x){
		TreeMap<String,Diary> diaries=new TreeMap<String,Diary>();
		String query="SELECT diary_id, name FROM diary.diaries where user_email = ?;";
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setString(1, x.getEmail());
			ResultSet rs=ps.executeQuery();
			
			if(rs==null){
				return diaries;
			}
			
			while(rs.next()){
				Diary diary= new Diary(rs.getString("name"), x, rs.getLong("diary_id"));
				diaries.put(diary.getName(),diary);
			}
		}
		catch(SQLException e){
			System.out.println("Problem with getAllDiaries()!");
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
