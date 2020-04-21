package com.com1028.assignment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Payment {
  
  private int customerNumber = 0;
  private double amount = 0;
  
  public Payment(int customerNumber, double amount) {
	super();
	this.customerNumber = customerNumber;
	this.amount = amount;
  }
  
  public static List<Payment> getPayments() {
	List<Payment> payments = new ArrayList<Payment>();

	try {
	  DatabaseConnection conn = DatabaseConnection.getInstance();
	  ResultSet paymentsTable = conn.useTable("payments");

	  while (paymentsTable.next()) {
		int customerNumber = paymentsTable.getInt("customerNumber");
		double amount = paymentsTable.getDouble("amount");

		payments.add(new Payment(customerNumber, amount));
	  }

	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return payments;
  }

  public int getCustomerNumber() {
    return this.customerNumber;
  }

  public double getAmount() {
    return this.amount;
  }
}
