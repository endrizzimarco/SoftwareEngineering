package com.com1028.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbutils.DbUtils;

public class DatabaseConnection {
  	
  private static DatabaseConnection instance = null;
  private static Connection connection = null;
  private static Statement statement = null;
  private static ResultSet rs = null;
  private final static String username = "root";
  private final static String password = "";
  private final static String db = "jdbc:mysql://localhost:3306/classicmodels?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

  private DatabaseConnection() throws SQLException {
	try {
	  DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
	  connection = DriverManager.getConnection(db, username, password);
	}
	catch(Exception e) {
	  System.out.println("Failed to get connection");
	}
  }
	
  protected ResultSet useTable(String tableName) throws SQLException{
	String query = "select * from " + tableName; 
	statement = connection.createStatement();
	rs = statement.executeQuery(query);
	return rs;
  }
	
  public Connection getConnection() {
    return connection;
  }
	
  public static DatabaseConnection getInstance() throws SQLException {
	if (instance == null) {
	  instance = new DatabaseConnection();
	} 
	else if (instance.getConnection().isClosed()) {
	  instance = new DatabaseConnection();
	}
	return instance;
	}
	
  public static void closeConnection() {
	try {
	  DbUtils.close(connection);
	  DbUtils.close(statement);
	  DbUtils.close(rs);
	} catch (SQLException e) {
	  System.out.println("Failed to close connection");
	}
  }
}
