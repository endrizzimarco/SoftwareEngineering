package com.com1028.assignment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
	
	BaseQuery conn = new BaseQuery("root", "");
 	List<Order> orders = new ArrayList<Order>();
	
	try {
	  ResultSet orderDetailsTable = conn.useTable("orderdetails");
	  ResultSet ordersTable = conn.useTable("orders");
	  
	  while (ordersTable.next()) {
		int orderNumber = ordersTable.getInt("orderNumber");
		int customerNum = ordersTable.getInt("customerNumber");
		
		orders.add(new Order(orderNumber, customerNum));
	  }

	  while (orderDetailsTable.next()) {
	    int orderNumber = orderDetailsTable.getInt("orderNumber");
	    int quantityOrdered = orderDetailsTable.getInt("quantityOrdered");
	    double priceEach = orderDetailsTable.getDouble("priceEach");
	    
	    for (Order order : orders) {
	      if (order.getOrderNumber() == orderNumber) {
	    	order.addProducts(new OrderDetails(orderNumber, quantityOrdered, priceEach));
	      }
	    }
	  }
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	for (Order order : orders) {
	  if (order.getOrderTotal() > 5000) {
		System.out.println(order.toString());  
	  }
	} 
  }
}