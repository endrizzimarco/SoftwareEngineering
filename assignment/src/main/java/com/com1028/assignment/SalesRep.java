package com.com1028.assignment;

import java.util.ArrayList;
import java.util.List;

public class SalesRep {
  
  private int employeeNumber = 0;
  private String firstname = null;
  private String lastName = null;
  private double revenueGenerated = 0;
  private List<Customer> customers = null;
  
  public SalesRep(int employeeNumber, String firstname, String lastname) {
	this.employeeNumber = employeeNumber;
	this.firstname = firstname;
	this.lastName = lastname;
	this.customers = new ArrayList<Customer>();
	
	for (Customer customer : customers) {
	  this.revenueGenerated = customer.getCustomerTotal();
	}
  }

  /**
   * @return the employeeNumber
   */
  public int getEmployeeNumber() {
    return this.employeeNumber;
  }

  /**
   * @return the firstname
   */
  public String getFirstname() {
    return this.firstname;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * @return the revenueGenerated
   */
  public double getRevenueGenerated() {
    return this.revenueGenerated;
  }
 
}
