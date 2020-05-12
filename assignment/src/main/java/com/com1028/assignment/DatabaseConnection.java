package com.com1028.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbutils.DbUtils;

/*
 * This is a singleton class allowing the same connection 
 * to be used for the whole program instead of opening
 * and closing multiple connections
 */
public class DatabaseConnection {
  	
  private static DatabaseConnection instance = null;
  private static Connection connection = null;
  private static Statement statement = null;
  private static ResultSet rs = null;
  private final static String USERNAME = "root";
  private final static String PASSWORD = "";
  private final static String DB = "jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

  private DatabaseConnection() throws SQLException {
	try {
	  DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
	  connection = DriverManager.getConnection(DB, USERNAME, PASSWORD);
	}
	catch(Exception e) {
	  System.out.println("Failed to get connection");
	}
  }
	
  // returns a table from the database
  protected ResultSet useTable(String tableName) throws SQLException{
	String query = "select * from " + tableName; 
	statement = connection.createStatement();
	rs = statement.executeQuery(query);
	return rs;
  }
	
  public Connection getConnection() {
    return connection;
  }
	
  // returns class instance and opens connection if needed
  public static DatabaseConnection getInstance() throws SQLException {
	if (instance == null) {
	  instance = new DatabaseConnection();
	} 
	else if (instance.getConnection().isClosed()) {
	  instance = new DatabaseConnection();
	}
	return instance;
	}
	
  // closes connection, statement, and resultSet
  public static void closeConnection() {
	try {
	  // same as: if (connection != null) { connnection.close() }
	  DbUtils.close(connection);
	  DbUtils.close(statement);
	  DbUtils.close(rs);
	} catch (SQLException e) {
	  System.out.println("Failed to close connection");
	}
  }
}
