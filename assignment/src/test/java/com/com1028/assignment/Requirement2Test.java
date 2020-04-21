package com.com1028.assignment;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import org.junit.Test;

public class Requirement2Test {

  @Test
  public void test() {
	try {
	  DatabaseConnection conn = DatabaseConnection.getInstance();
	  String query = "SELECT customers.customerNumber, customerName, SUM(amount) AS amountTotal FROM customers LEFT JOIN payments ON customers.customerNumber=payments.customerNumber GROUP BY customerNumber";
	  Statement s = conn.getConnection().createStatement();
	  ResultSet rs = s.executeQuery(query);
	
      StringBuilder builder = new StringBuilder("Customer N°\tCustomer Name\t\t\t   Total Paid($)\n");
    
	  while (rs.next()) {
	    String row = String.format(Locale.UK, "%-16s%-35s%.2f\n", rs.getInt("customerNumber"), rs.getString("customerName"), rs.getDouble("amountTotal"));
	    builder.append(row);
	  }
	  assertEquals(builder.toString(), Customer.getAmountPaidByEachCustomer());
	}
	catch (SQLException e) {
	  e.printStackTrace();
	}
	finally {
	  DatabaseConnection.closeConnection();
	}
  }
}
