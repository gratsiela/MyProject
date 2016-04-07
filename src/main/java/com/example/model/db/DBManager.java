package com.example.model.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	private static DBManager uniqueInstance;

	public static final String DB_NAME = "diary";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";
	private static final String DB_USER = "root";
//	private static final String DB_PASS = "*Gugche8";
	private static final String DB_PASS = "559642";
	private Connection connection;

	private DBManager() {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			System.out.println("Connected!");
		} catch (ClassNotFoundException e) {
			System.out.println("Problem with driver loading!");
		} catch (SQLException e) {
			System.out.println("Problem with connection!");
		}
		
		createDB();
	}

	private void createDB() {
		
		try (Statement stmt = connection.createStatement()) {
			connection.setAutoCommit(false);
			String sql = "CREATE DATABASE IF NOT EXISTS "+DB_NAME+";";
			stmt.executeUpdate(sql);
			System.out.println("Database made!");
			sql = "USE "+DB_NAME+";";
			stmt.executeQuery(sql);
			//Create users
			PreparedStatement prstmt = connection.prepareStatement("SHOW TABLES LIKE 'users'");
			ResultSet tables = prstmt.executeQuery();
			if (!tables.next()) {
				sql = "CREATE TABLE diary.users("+
						"user_email VARCHAR(50) PRIMARY KEY,"+
						"first_name VARCHAR(50) NOT NULL,"+
						"last_name VARCHAR(50) NOT NULL,"+					
						"nickname VARCHAR(50) NOT NULL,"+
						"pass VARCHAR(40) NOT NULL,"+
						"self_description TEXT,"+
						"photo TEXT);";
				stmt.executeUpdate(sql);
				System.out.println("Created table user in given database...");
			}
	
			//Create diaries
			prstmt = connection.prepareStatement("SHOW TABLES LIKE 'diaries'");
			tables = prstmt.executeQuery();
			if (!tables.next()) {
				sql = "CREATE TABLE diary.diaries("+
					  "diary_id BIGINT AUTO_INCREMENT PRIMARY KEY,"+
					  "name VARCHAR(50) NOT NULL,"+
					  "user_email VARCHAR(50),"+
					  "FOREIGN KEY(user_email) REFERENCES diary.users(user_email) ON DELETE CASCADE ON UPDATE CASCADE);";
				stmt.executeUpdate(sql);
				System.out.println("Created table diaries in given database...");
			}

			//Create notes
			prstmt = connection.prepareStatement("SHOW TABLES LIKE 'notes'");
			tables = prstmt.executeQuery();
			if (!tables.next()) {
				sql = "CREATE TABLE diary.notes("+
						"note_id BIGINT AUTO_INCREMENT PRIMARY KEY,"+
						"title VARCHAR(50) NOT NULL,"+
						"content MEDIUMTEXT,"+
						"date_time TIMESTAMP NOT NULL,"+
						"status ENUM('Private', 'Public', 'Friends'),"+
						"diary_id BIGINT,"+
						"FOREIGN KEY(diary_id) REFERENCES diary.diaries(diary_id) ON DELETE CASCADE ON UPDATE CASCADE);";
				stmt.executeUpdate(sql);
				System.out.println("Created table notes in given database...");
			}

			//Create friendsTable
			prstmt = connection.prepareStatement("SHOW TABLES LIKE 'friends'");
			tables = prstmt.executeQuery();
			if (!tables.next()) {
				sql = "CREATE TABLE diary.friends("+
						"email1 VARCHAR(50),"+
						"email2 VARCHAR(50),"+
						"PRIMARY KEY (email1, email2),"+
						"FOREIGN KEY (email1) REFERENCES diary.users(user_email) ON DELETE CASCADE ON UPDATE CASCADE,"+
						"FOREIGN KEY (email2) REFERENCES diary.users(user_email) ON DELETE CASCADE ON UPDATE CASCADE);";
				stmt.executeUpdate(sql);
				System.out.println("Created table friends in given database...");
			}
			connection.commit();
			
		}catch(SQLException e){
			System.out.println("Problem with creating the database!");
			e.printStackTrace();
		}
		finally{
			try {
				connection.setAutoCommit(true);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static synchronized DBManager getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new DBManager();
		return uniqueInstance;
	}

	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Problem with connection.close()!");
		}
	}

}
