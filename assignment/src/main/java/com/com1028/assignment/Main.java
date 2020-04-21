package com.com1028.assignment;

public class Main {

  public static void main(String[] args) {
	System.out.println(Order.getOrdersOver5000());
    System.out.println(Customer.getAmountPaidByEachCustomer());
    System.out.println(SalesRep.getRevenueForEachSalesRep());
	DatabaseConnection.closeConnection();
  }
}