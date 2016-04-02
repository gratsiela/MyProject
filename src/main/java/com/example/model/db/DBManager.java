package com.example.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	private static DBManager uniqueInstance;

	public static final String DB_NAME = "diary";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
	private static final String DB_USER = "root";
	private static final String DB_PASS = "*Gugche8";

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
			ResultSet rs = stmt
					.executeQuery("SELECT schema_name FROM information_schema.schemata WHERE schema_name = 'diary';");

			if (!rs.next()) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			System.out.println("Schema \"diary\" does not exist in the DB!");
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
