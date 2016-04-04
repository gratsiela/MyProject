package com.example.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	public boolean addDiary(User x, String diaryName){
		String query="INSERT INTO diary.belejnik (name,user_email) VALUES (?,?);";
		
		try{
			PreparedStatement ps=manager.getConnection().prepareStatement(query);
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
