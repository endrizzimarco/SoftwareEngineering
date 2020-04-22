package com.com1028.assignment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class SalesRep {

  private int employeeNumber = 0;
  private String firstName = null;
  private String lastName = null;
  private List<Customer> customers = null;
  private static List<SalesRep> salesReps = new ArrayList<SalesRep>();

  public SalesRep(int employeeNumber, String firstName, String lastName) {
	this.employeeNumber = employeeNumber;
	this.firstName = firstName;
	this.lastName = lastName;
	this.customers = new ArrayList<Customer>();
  }

  public static List<SalesRep> getSalesReps() {
	if (!salesReps.isEmpty()) {
	  return salesReps;
	}
	Map<Integer, SalesRep> salesRepsMap = new TreeMap<Integer, SalesRep>();

	try {
	  DatabaseConnection conn = DatabaseConnection.getInstance();
	  ResultSet employeeTable = conn.useTable("employees");
	  while (employeeTable.next()) {
		int employeeNumber = employeeTable.getInt("employeeNumber");
		String firstName = employeeTable.getString("firstName");
		String lastName = employeeTable.getString("lastName");
		String jobTitle = employeeTable.getString("jobTitle");

		if (jobTitle.equals("Sales Rep")) {
		  salesRepsMap.put(employeeNumber, new SalesRep(employeeNumber, firstName, lastName));
		}
	  }
	  for (Customer customer : Customer.getCustomers()) {
		if (customer.getSalesRepEmployeeNumber() != 0) {
		  SalesRep tmpSalesRep = salesRepsMap.get(customer.getSalesRepEmployeeNumber());
		  tmpSalesRep.addCustomer(customer);
		}
	  }
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return salesReps = new ArrayList<SalesRep>(salesRepsMap.values());
  }

  public void addCustomer(Customer customer) {
	this.customers.add(customer);
  }

  public double getRevenueGenerated() {
	double revenueGenerated = 0;

	for (Customer customer : this.customers) {
	  revenueGenerated += customer.getCustomerTotal();
	}
	return revenueGenerated;
  }
  
  public static String getRevenueForEachSalesRep() {
	StringBuilder builder = new StringBuilder("SalesRep N°\tName\t    Surname\t   Revenue\n");
	for (SalesRep salesRep : SalesRep.getSalesReps()) {
	  builder.append(salesRep.toString());
	}
	return builder.toString();
  }

  @Override
  public String toString() {
	return String.format(Locale.UK, "%-16s%-12s%-15s%.2f\n", this.employeeNumber, this.firstName, this.lastName, this.getRevenueGenerated());
  }

}
