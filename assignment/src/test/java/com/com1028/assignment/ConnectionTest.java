package com.com1028.assignment;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class ConnectionTest {

  @Test
  // test function closeConnection() is working as intended
  public void closeConnectionTest() {
	try {
	  DatabaseConnection conn = DatabaseConnection.getInstance();
	  String query = "SELECT * FROM offices";
	  Statement s = conn.getConnection().createStatement();
	  ResultSet rs = s.executeQuery(query);
	  
	  assertFalse(conn.getConnection().isClosed());
	  assertFalse(s.isClosed());
	  assertFalse(rs.isClosed());
	  
	  DatabaseConnection.closeConnection();
	  
	  assertTrue(rs.isClosed());
	  assertTrue(s.isClosed());
	  assertTrue(conn.getConnection().isClosed());
  }
    catch (SQLException e) {
	  e.printStackTrace();
    }
  }
  
  // test getInstance() is returning the same connection
  @Test 
  public void singletonConnectionTest() {
	try {
	  DatabaseConnection conn1 = DatabaseConnection.getInstance();
	  DatabaseConnection conn2 = DatabaseConnection.getInstance();
	  
	  assertEquals(conn1, conn2);
	} 
	catch (SQLException e) {
	  e.printStackTrace();
	}
  }
}
