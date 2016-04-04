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
		String query="INSERT INTO diaty.belejnik (name,user_email) VALUES (?,?);";
		Connection con=manager.getInstance().getConnection();
		
		try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
		ps.setString(1, diaryName);
		ps.setString(2, x.getEmail());
		ps.execute();
		}catch(SQLException e){
			System.out.println("Problem with addDiary()!");
			return false;
		}
		return true;	
	}
}
