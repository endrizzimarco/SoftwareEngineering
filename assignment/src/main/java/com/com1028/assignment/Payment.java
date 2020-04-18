package com.com1028.assignment;

public class Payment {
  
  private int customerNumber = 0;
  private double amount = 0;
  
  public Payment(int customerNumber, double amount) {
	super();
	this.customerNumber = customerNumber;
	this.amount = amount;
  }

  /**
   * @return the customerNumber
   */
  public int getCustomerNumber() {
    return this.customerNumber;
  }

  /**
   * @return the amount
   */
  public double getAmount() {
    return this.amount;
  }
  
}
