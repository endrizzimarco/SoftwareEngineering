package com.com1028.assignment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetails {

  private int orderNumber = 0;
  private int quantityOrder = 0;
  private double priceEach = 0;
  // list containing all OrderDetails objects
  private static List<OrderDetails> products = new ArrayList<OrderDetails>();

  public OrderDetails(int orderNumber, int quantityOrder, double priceEach) {
	super();
	this.orderNumber = orderNumber;
	this.quantityOrder = quantityOrder;
	this.priceEach = priceEach;
  }

  // returns and sets products list
  public static List<OrderDetails> getOrderDetails() {
	// if loop already ran, return end product
	if (!products.isEmpty()) {
	  return products;
	}
	try {
	  // create OrderDetails objects and add them to list products
	  DatabaseConnection conn = DatabaseConnection.getInstance();
	  ResultSet orderDetailsTable = conn.useTable("orderdetails");

	  while (orderDetailsTable.next()) {
		int orderNumber = orderDetailsTable.getInt("orderNumber");
		int quantityOrdered = orderDetailsTable.getInt("quantityOrdered");
		double priceEach = orderDetailsTable.getDouble("priceEach");

		products.add(new OrderDetails(orderNumber, quantityOrdered, priceEach));
	  }

	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return products;
  }

  public int getOrderNumber() {
	return orderNumber;
  }

  public int getQuantityOrder() {
	return this.quantityOrder;
  }

  public double getPriceEach() {
	return this.priceEach;
  }
}
