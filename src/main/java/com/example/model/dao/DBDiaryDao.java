package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.TreeSet;

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
	
	public TreeSet<Diary> getAllDiaries(User x){
		TreeSet<Diary> diaries=new TreeSet<Diary>(new Comparator<Diary>() {
			
			@Override
			public int compare(Diary d1, Diary d2) {
				return d1.getName().compareTo(d2.getName());
			}

		});
		String query="SELECT name, diary_id FROM diary.belejnik where user_email = ?;";
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			ps.setString(1, x.getEmail());
			ResultSet rs=ps.executeQuery();
			
			if(rs==null){
				return diaries;
			}
			
			while(rs.next()){
				Diary diary= new Diary(rs.getString("name"), x, rs.getLong("diary_id"));
				diaries.add(diary);
			}
		}
		catch(SQLException e){
			System.out.println("Problem with getAllDiaries()!");
		}
		return diaries;
	}
	
	public boolean addDiary(User x, String diaryName){
		String query="INSERT INTO diary.belejnik (name,user_email) VALUES (?,?);";
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
			System.out.println(1);
		ps.setString(1, diaryName);
		System.out.println(2);
		ps.setString(2, x.getEmail());
		System.out.println(3);
		ps.execute();
		System.out.println(4);
		}catch(SQLException e){
			System.out.println("Problem with addDiary()!");
			return false;
		}
		return true;	
	}
}
