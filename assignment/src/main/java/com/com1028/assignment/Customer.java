package com.com1028.assignment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Customer {
  
  private int customerNumber = 0;
  private String customerName = null;
  private int salesRepEmployeeNumber = 0;
  // list containing all Payment made by a Customer
  private List<Payment> payments = null;
  // list containing all Customer objects
  private static List<Customer> customers = new ArrayList<Customer>();

  public Customer(int customerNumber, String customerName, int salesRepEmployeeNumber) {
	super();
	this.customerNumber = customerNumber;
	this.customerName = customerName;
	this.salesRepEmployeeNumber = salesRepEmployeeNumber;
	this.payments = new ArrayList<Payment>();
  }
  
  // returns and sets customers list
  public static List<Customer> getCustomers() {
	// if loop already ran, return end product
	if (!customers.isEmpty()) {
	  return customers;
	}
	// treemap containing customerNumber as key and a Customer object as value
	Map<Integer, Customer> customersMap = new TreeMap<Integer, Customer>();
	
 	try {
 	  // create Customer objects and add them to customersMap along with customerNumber
 	  DatabaseConnection conn = DatabaseConnection.getInstance();
 	  ResultSet customersTable = conn.useTable("customers");
 	  
	  while (customersTable.next()) {
		int customerNumber = customersTable.getInt("customerNumber");
		String customerName = customersTable.getString("customerName");
		int salesRepNumber = customersTable.getInt("salesRepEmployeeNumber");
		
		customersMap.put(customerNumber, (new Customer (customerNumber, customerName, salesRepNumber)));
	  }
	  // loop through all Payments and add them to payments if the customerNumber matches
	  for (Payment payment : Payment.getPayments()) {
		Customer tmpCustomer = customersMap.get(payment.getCustomerNumber());
		tmpCustomer.addPayment(payment);
	  }
	  
	} catch (SQLException e) {
	  e.printStackTrace();
	}
 	return customers = new ArrayList<Customer>(customersMap.values());
  }
  
  public void addPayment(Payment payment) {
	this.payments.add(payment);
  }

  public int getSalesRepEmployeeNumber() {
    return this.salesRepEmployeeNumber;
  }
  
  // returns total amount paid by this customer
  public double getCustomerTotal() {
	double customerTotal= 0;
	
	for (Payment payment : this.payments) {
	  customerTotal += payment.getAmount();
	}
    return customerTotal;
  }
  
  // returns a string containing the total for every customer
  public static String getAmountPaidByEachCustomer() {
	StringBuilder builder = new StringBuilder("Customer N°\tCustomer Name\t\t\t   Total Paid($)\n");
	for (Customer customer : Customer.getCustomers()) {
	  builder.append(customer.toString());
	}
	return builder.toString();
  }
  
  @Override 
  public String toString() {
	return String.format(Locale.UK, "%-16s%-35s%.2f\n", this.customerNumber, this.customerName, this.getCustomerTotal());
  }
}