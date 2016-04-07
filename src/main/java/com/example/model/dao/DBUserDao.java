package com.example.model.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SQLOutputImpl;
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
		String query = "INSERT INTO diary.users "
				+ "(user_email,first_name,last_name,nickname,pass)"
				+ "VALUES (?, ?, ?, ?, SHA1(?));";
	
		Connection con = manager.getInstance().getConnection();
		
		try(PreparedStatement st = manager.getConnection().prepareStatement(query);){
				st.setString(1, x.getEmail());
				st.setString(2, x.getFirstName());
				st.setString(3, x.getLastName());
				st.setString(4, x.getNickname());
				st.setString(5, x.getPassword());
				st.execute();
				System.out.println("Successfully added!");
		
		} catch (SQLException e) {
			success = false;
			System.out.println("Problem");
			e.printStackTrace();
		}

		return success;
	}

	public ArrayList<User> getAllUsers() throws SQLException {
		List<User> registeredUsers = new ArrayList<User>();
		Connection con = manager.getConnection();
		PreparedStatement prstmt = con.prepareStatement("SHOW TABLES LIKE 'users'");

		// check if "user" table is there

		ResultSet tables = prstmt.executeQuery();
		if (tables.next()) {
		  // Table exists
			try{
				String query = "SELECT user_email,first_name,last_name,nickname,pass, self_description, photo FROM diary.users;";
				Statement st = con.createStatement();
				ResultSet result = st.executeQuery(query);
				
				if(result == null){
					System.out.println("22222222222");
					st.close();				
					return (ArrayList<User>) registeredUsers;
				}
				while(result.next()){
					User u = new User(result.getString("first_name"), result.getString("last_name"),result.getString("nickname"),				

							result.getString("user_email"), result.getString("pass"),result.getString("self_description"), result.getString("photo"));

						registeredUsers.add(u);
					}
				
				}catch(SQLException e){
					e.printStackTrace();
					System.out.println("Problem getUsers()!");
				}	
		}
		return (ArrayList<User>) registeredUsers;
	}
	
	//upload profilePicture method()
	
	//update profilePicture method()
	public	boolean updateProfilePicture(String userEmail, String location){
		String query = "update diary.users set photo = ? where user_email = ?;";
		Connection con = null;
		try{
			
			con = manager.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, location);
			stmt.setString(2, userEmail);
			stmt.executeUpdate();
		
			stmt.close();
		
			return true;	
		}catch(SQLException e) {
			System.out.println("Picture uploading failed!");

			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
		
	}
	
	public String getProfilePicture(User x){
		String query = "SELECT photo FROM diary.users WHERE user_email = ?;";
		try{
			Connection con=manager.getConnection();
			PreparedStatement stmt=con.prepareStatement(query);
			stmt.setString(1, x.getEmail());
			ResultSet rs=stmt.executeQuery();
			if(rs == null){
				stmt.close();				
				return null;
			}
			rs.next();
			String photoURL=rs.getString("photo");
			return photoURL;
			}catch(SQLException e){
				System.out.println("Problem with getting photo");
				return null;
			}
	}
	//UPDATES METHODS
	
	//update firstName
public	boolean updateProfile(User x, String newFirstName,String newLastName, String newNickname, String newSelfDescription){
		String query = "update diary.users set first_name = ?, last_name =?, nickname = ?,self_description = ? where user_email = ?;";
		try{
			Connection con = manager.getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, newFirstName);
			stmt.setString(2, newLastName);
			stmt.setString(3, newNickname);
			stmt.setString(4, newSelfDescription);
			stmt.setString(5, x.getEmail());
			stmt.executeUpdate();
			stmt.close();
			return true;
		}catch(SQLException e){
			System.err.println("Problem with the first name update");
			// con.rollback();
			return false;
		}
	}
//update password
public	boolean updatePassword(User x, String newPassword){
	String query = "update diary.users set pass = SHA1(?) where user_email = ?;";
	try{
		Connection con = manager.getConnection();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, newPassword);
		stmt.setString(2, x.getEmail());
		stmt.executeUpdate();
		stmt.close();
		return true;
	}catch(SQLException e){
		System.err.println("Problem with the password update");
		// con.rollback();
		return false;
	}
}

public String getPassword(String email){
	String query="SELECT pass from diary.users where user_email = ?;";
	try{
	Connection con=manager.getConnection();
	PreparedStatement stmt=con.prepareStatement(query);
	stmt.setString(1, email);
	ResultSet rs=stmt.executeQuery();
	if(rs == null){
		stmt.close();				
		return null;
	}
	rs.next();
	String password=rs.getString("pass");
	return password;
	}catch(SQLException e){
		System.out.println("Problem with getting password");
		return null;
	}
}
}

