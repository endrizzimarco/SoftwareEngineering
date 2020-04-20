package com.com1028.assignment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetails {
  
  private int orderNumber = 0;
  private int quantityOrder = 0;
  private double priceEach = 0;
  

  public OrderDetails(int orderNumber, int quantityOrder, double priceEach) {
	super();
	this.orderNumber = orderNumber;
	this.quantityOrder = quantityOrder;
	this.priceEach = priceEach;
  }
  
  public static List<OrderDetails> getOrderDetails() {
	BaseQuery conn = new BaseQuery("root", "");
	List<OrderDetails> products = new ArrayList<OrderDetails>();
	
	try {
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


  /**
   * @return the orderNumber
   */
  public int getOrderNumber() {
    return orderNumber;
  }

  /**
   * @return the quantityOrder
   */
  public int getQuantityOrder() {
    return this.quantityOrder;
  }

  /**
   * @return the priceEach
   */
  public double getPriceEach() {
    return this.priceEach;
  }
  
}
