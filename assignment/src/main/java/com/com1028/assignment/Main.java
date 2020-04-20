package com.com1028.assignment;

public class Main {

  public static void main(String[] args) {
	System.out.println(Order.ordersOver5000());
	DatabaseConnection.closeConnection();
  }
}