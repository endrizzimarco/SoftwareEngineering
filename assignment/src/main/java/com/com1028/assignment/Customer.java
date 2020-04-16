package com.com1028.assignment;

import java.util.ArrayList;
import java.util.List;

public class Customer {
  
  private int customerNumber = 0;
  private String customerName = null;
  private int salesRepEmployeeNumber = 0;
  private double customerTotal = 0;
  private List<Order> orders = null;
  
  public Customer(int customerNumber, String customerName, int salesRepEmployeeNumber) {
	super();
	this.customerNumber = customerNumber;
	this.customerName = customerName;
	this.salesRepEmployeeNumber = salesRepEmployeeNumber;
	this.orders = new ArrayList<Order>();
	
	for (Order order : orders) {
	  this.customerTotal += order.getOrderTotal();
	}
  }

  /**
   * @return the customerNumber
   */
  public int getCustomerNumber() {
    return this.customerNumber;
  }

  /**
   * @return the customerName
   */
  public String getCustomerName() {
    return this.customerName;
  }

  /**
   * @return the salesRepEmployeeNumber
   */
  public int getSalesRepEmployeeNumber() {
    return this.salesRepEmployeeNumber;
  }

  /**
   * @return the customerTotal
   */
  public double getCustomerTotal() {
    return this.customerTotal;
  }
  
  
}
