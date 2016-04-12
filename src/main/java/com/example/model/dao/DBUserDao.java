package com.example.model.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
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
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class DBUserDao{
	 
	private static DBUserDao instance;
	private static DBManager manager;
	
	private DBUserDao() throws ClassNotFoundException, SQLException{
		manager = DBManager.getInstance();
	}
	
	public static DBUserDao getInstance() throws ClassNotFoundException, SQLException{
		if(instance == null)
			instance = new DBUserDao();
		return instance;
	}

	public boolean addUser(User x) throws ClassNotFoundException, SQLException{
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
			String query = "SELECT user_email,first_name,last_name,nickname,pass, self_description, photo FROM diary.users;";
			
		  // Table exists
			try(Statement st = con.createStatement()){
					ResultSet result = st.executeQuery(query);
				
				if(result == null){
				//	st.close();				
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
	
	
	
	public static boolean uploadPicture(Part picture,String email) throws ClassNotFoundException, SQLException {
		Connection connection = DBManager.getInstance().getConnection();
		String sql = "UPDATE diary.users SET photo = ? WHERE user_email = ?;";
		InputStream inputStream=null;
		try(PreparedStatement stmt = connection.prepareStatement(sql)){
			inputStream = picture.getInputStream();
			if (inputStream != null) {
				stmt.setBlob(1, inputStream);
			}
				stmt.setString(2, email);
				int row = stmt.executeUpdate();
				if (row > 0) {
					System.out.println("File uploaded and saved into database!");
					return true;
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	public static String getProfilePicture(String email){
		String query = "SELECT photo FROM diary.users WHERE user_email = ?;";
		Connection con=manager.getConnection();
		String blob = null;
		try(PreparedStatement stmt=con.prepareStatement(query)){
			stmt.setString(1, email);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				Blob b = rs.getBlob("photo");
				if(b!=null){
					byte[] bdata = b.getBytes(1, (int)b.length());
					blob = Base64.encode(bdata);
				}
				//blob = java.util.Base64.getEncoder().encodeToString(bdata);
				;
			}
		}catch(SQLException e){
			System.out.println("Problem with getting picture");
		
		}
		System.out.println("Picture loaded");
		return blob;
	}
		
	//UPDATES METHODS
	
	//update firstName
public	boolean updateProfile(User x, String newFirstName,String newLastName, String newNickname, String newSelfDescription){
		String query = "update diary.users set first_name = ?, last_name =?, nickname = ?,self_description = ? where user_email = ?;";
		Connection con = manager.getConnection();
		try(PreparedStatement stmt = con.prepareStatement(query)){
			stmt.setString(1, newFirstName);
			stmt.setString(2, newLastName);
			stmt.setString(3, newNickname);
			stmt.setString(4, newSelfDescription);
			stmt.setString(5, x.getEmail());
			stmt.executeUpdate();
		
			return true;
		}catch(SQLException e){
			System.err.println("Problem with the first name update");
			// con.rollback();
			return false;
		}
	}
//update password
public	boolean updatePassword(String email, String newPassword){
	String query = "update diary.users set pass = SHA1(?) where user_email = ?;";
	try{
		Connection con = manager.getConnection();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, newPassword);
		stmt.setString(2, email);
		stmt.executeUpdate();
		stmt.close();
		return true;
	}catch(SQLException e){
		System.err.println("Problem with the password update");
		return false;
	}
}

public String getPassword(String email){
	String query="SELECT pass from diary.users where user_email = ?;";
	Connection con=manager.getConnection();
	try(PreparedStatement stmt=con.prepareStatement(query)){
		stmt.setString(1, email);
		ResultSet rs=stmt.executeQuery();
		if(rs == null){
		//	stmt.close();				
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

public boolean follow(User signedUser, User author){
	String query="INSERT INTO diary.friends (email1, email2) VALUES (?, ?);";
	
	Connection con= manager.getConnection();
	
	try(PreparedStatement ps= con.prepareStatement(query)){
		ps.setString(1, signedUser.getEmail());
		ps.setString(2, author.getEmail());
		ps.executeUpdate();
	}catch(SQLException e){
		System.out.println("Problem with follow()!");
	}
	return true;
}

public boolean unfollow(User signedUser, User author){
	String query="DELETE FROM diary.friends WHERE email1= ? and email2= ?;";
	
	Connection con= manager.getConnection();
	
	try(PreparedStatement ps= con.prepareStatement(query)){
		ps.setString(1, signedUser.getEmail());
		ps.setString(2, author.getEmail());
		ps.executeUpdate();
	}catch(SQLException e){
		System.out.println("Problem with unfollow()!");
	}
	return true;
}

public User getUser(String email) {
	String query="SELECT user_email,first_name,last_name,nickname,pass, self_description, photo FROM diary.users WHERE user_email = ?;";
	User user=null;;
	
	try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
		ps.setString(1, email);
		ResultSet rs=ps.executeQuery();

		if(rs==null){
			return user;
		}
		
		rs.next();
		user=new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("nickname"), rs.getString("user_email"), rs.getString("pass"), rs.getString("self_description"), rs.getString("photo"));
		
		
	}catch(SQLException e){
		System.out.println("Problem with getUser()!");
	}
	
	return user;
}

public boolean checkFollowing(User signedUser, User author) {
	String query = "SELECT email1 FROM friends WHERE email1 = ? and email2 = ?;";
	
	try(PreparedStatement ps=manager.getConnection().prepareStatement(query)){
		ps.setString(1, signedUser.getEmail());
		ps.setString(2, author.getEmail());
		ResultSet rs=ps.executeQuery();
		
		if(!rs.next()){
			System.out.println("false");
			return false;
		}
		System.out.println("true");
		return true;
	}
	catch(SQLException e){
		System.out.println("Problem with checkFollowing()?");
	}
	
	return false;
}


//upload profilePicture method()

	//update profilePicture method()
/*	public	boolean updateProfilePicture(String userEmail, String location){
		String query = "update diary.users set photo = ? where user_email = ?;";
		Connection con = manager.getConnection();
		try(PreparedStatement stmt = con.prepareStatement(query)){
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
		
	}*/
}

