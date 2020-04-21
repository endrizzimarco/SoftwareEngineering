package com.com1028.assignment;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import org.junit.Test;

public class Requirement3Test {

  @Test
  public void test() {
	try {
	  DatabaseConnection conn = DatabaseConnection.getInstance();
	  String query = "select employeeNumber, firstName, lastName, SUM(payments.amount) AS totalRevenue FROM employees LEFT JOIN customers ON customers.salesRepEmployeeNumber=employees.employeeNumber LEFT JOIN payments on payments.customerNumber=customers.customerNumber WHERE jobTitle = \"Sales Rep\" GROUP BY employeeNumber;";
	  Statement s = conn.getConnection().createStatement();
	  ResultSet rs = s.executeQuery(query);
	
      StringBuilder builder = new StringBuilder("SalesRep N°\tName\t    Surname\t   Revenue\n");
    
	  while (rs.next()) {
	    String row = String.format(Locale.UK, "%-16s%-12s%-15s%.2f\n", rs.getInt("employeeNumber"), rs.getString("firstName"), rs.getString("lastName"), rs.getDouble("totalRevenue"));
	    builder.append(row);
	  }
	  assertEquals(builder.toString(), SalesRep.getRevenueForEachSalesRep());
	} 
	catch (SQLException e) {
	  e.printStackTrace();
	}
	finally {
	  DatabaseConnection.closeConnection();
	}
  }

}
