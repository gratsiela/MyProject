package com.example.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.User;
import com.example.model.db.DBManager;

public class DBUserDao{
	 
	private static DBUserDao instance;
	private DBManager manager;
	
	private DBUserDao(){
		manager = DBManager.getInstance();
	}
	
	public static DBUserDao getInstance(){
		if(instance == null)
			instance = new DBUserDao();
		return instance;
	}

	public boolean addUser(User x) {
		boolean success = true;
		String query = "INSERT INTO user "
				+ "(firstName,lastName,nickname,email,password)"
				+ "VALUES (?, ?, ?, ?, ?);";
		try(PreparedStatement st = manager.getConnection().prepareStatement(query);){
			
			st.setString(1, x.getFirstName());
			st.setString(2, x.getLastName());
			st.setString(3, x.getNickname());
			st.setString(4, x.getEmail());
			st.setString(5, x.getPassword());
			st.execute();
		} catch (SQLException e) {
			success = false;
		}

		return success;
	}

	public ArrayList<User> getAllUsers() {
		List<User> registeredUsers = new ArrayList();
		
		try{
		String query = "SELECT firstName,lastName,nickname,email,password "
				+ "FROM diary.user;";
		Statement st = manager.getConnection().createStatement();
		ResultSet result = st.executeQuery(query);
		
		if(result == null){
			st.close();				
			return (ArrayList<User>) registeredUsers;
		}
		while(result.next()){
			User u = new User(result.getString("firstName"), result.getString("lastName"),	result.getString("nickname")	,				
					result.getString("email"), result.getString("password"));
				registeredUsers.add(u);
			}
		
		}catch(SQLException e){
			System.out.println("Problem getUSers()!");
		}
		return (ArrayList<User>) registeredUsers;
	}
}

