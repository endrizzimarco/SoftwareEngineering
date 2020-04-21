package com.com1028.assignment;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import org.junit.Test;

public class Requirement1Test {

  @Test
  public void test() {
	try {
	  DatabaseConnection conn = DatabaseConnection.getInstance();
	  String query = "SELECT orders.orderNumber, customerNumber, SUM(quantityOrdered*priceEach) AS total FROM orderdetails INNER JOIN orders ON orders.orderNumber=orderdetails.orderNumber GROUP BY orderNumber HAVING Total > 5000";
	  Statement s = conn.getConnection().createStatement();
	  ResultSet rs = s.executeQuery(query);
	
      StringBuilder builder = new StringBuilder("Order N°\tCustomer N°\tOrder Total\n");
    
	  while (rs.next()) {
	    String row = String.format(Locale.UK, "%-16s%-16s%.2f\n", rs.getInt("orderNumber"), rs.getInt("customerNumber"), rs.getDouble("total"));
	    builder.append(row);
	  }
	  assertEquals(builder.toString(), Order.getOrdersOver5000());
	} 
	catch (SQLException e) {
	  e.printStackTrace();
	}
	finally {
	  DatabaseConnection.closeConnection();
	}
  }
}
