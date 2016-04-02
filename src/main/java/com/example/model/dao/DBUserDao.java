package com.example.model.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
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

	public boolean addUser(User x){
		boolean success = true;
		String query = "INSERT INTO diary.user "
				+ "(user_email,first_name,last_name,nickname,pass)"
				+ "VALUES (?, ?, ?, ?, ?);";
	
		Connection con = manager.getInstance().getConnection();
		
		try(PreparedStatement st = manager.getConnection().prepareStatement(query);){
		//	con.setAutoCommit(false);
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "user", null);
			if (tables.next()) {
			  // Table exists
				System.out.println("table exists");
				st.setString(1, x.getEmail());
				st.setString(2, x.getFirstName());
				st.setString(3, x.getLastName());
				st.setString(4, x.getNickname());
				st.setString(5, x.getPassword());
				st.execute();
				System.out.println("Successfully added!");
			}
			else {
			  // Table does not exist
				Statement stmt = con.createStatement();
				String sql = "CREATE TABLE diary.user("+
						"user_email VARCHAR(50) PRIMARY KEY,"+
						"first_name VARCHAR(50) NOT NULL,"+
						"last_name VARCHAR(50) NOT NULL,"+
						"nickname VARCHAR(50) NOT NULL,"+
						"pass VARCHAR(30) NOT NULL,"+
						"self_description TEXT,"+
						"photo BLOB);";
				stmt.executeUpdate(sql);
				System.out.println("Created table user in given database...");
				st.setString(1, x.getEmail());
				st.setString(2, x.getFirstName());
				st.setString(3, x.getLastName());
				st.setString(4, x.getNickname());
				st.setString(5, x.getPassword());
				st.execute();
				System.out.println("Successfully added");
			}
		
		} catch (SQLException e) {
			success = false;
			System.out.println("Problem");
			e.printStackTrace();
		}

		return success;
	}

	public ArrayList<User> getAllUsers() throws SQLException {
		List<User> registeredUsers = new ArrayList();
		Connection con = manager.getConnection();
		DatabaseMetaData dbm = con.getMetaData();
		// check if "user" table is there
		ResultSet tables = dbm.getTables(null, null, "user", null);
		if (tables.next()) {
		  // Table exists
			try{
				String query = "SELECT user_email,first_name,last_name,nickname,pass FROM diary.user;";
				Statement st = con.createStatement();
				ResultSet result = st.executeQuery(query);
				
				if(result == null){
					st.close();				
					return (ArrayList<User>) registeredUsers;
				}
				while(result.next()){
					User u = new User(result.getString("first_name"), result.getString("last_name"),result.getString("nickname"),				
							result.getString("user_email"), result.getString("pass"));
						registeredUsers.add(u);
					}
				
				}catch(SQLException e){
					e.printStackTrace();
					System.out.println("Problem getUSers()!");
				}	
		}
		return (ArrayList<User>) registeredUsers;
	}
}

