package com.com1028.assignment;

import java.util.ArrayList;
import java.util.List;

public class Order {
  
  private int orderNumber = 0;
  private int customerNumber = 0;
  private double orderTotal = 0;
  private List<OrderDetails> products = null;
  
  
  public Order(int orderNumber, int customerNumber) {
	super();
	this.orderNumber = orderNumber;
	this.customerNumber = customerNumber;
	this.products = new ArrayList<OrderDetails>();
	
	for(OrderDetails product : products) {
	  this.orderTotal += product.getQuantityOrder() * product.getPriceEach();
	}
  }

  /**
   * @return the orderNumber
   */
  public int getOrderNumber() {
    return this.orderNumber;
  }

  /**
   * @return the customerNumber
   */
  public int getCustomerNumber() {
    return this.customerNumber;
  }

  /**
   * @return the total
   */
  public double getOrderTotal() {
    return this.orderTotal;
  }
  
  /**
   * @param product in an order
   * 
   */
  public void addProducts(OrderDetails product) {
	this.products.add(product);
  }
  
  @Override
  public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Order Number:" + this.getOrderNumber());
	builder.append("\nCustomer Number: " + this.getCustomerNumber());
	builder.append("\nOrder Total:" + this.getOrderTotal());
	builder.append("\n");
	return builder.toString();
	}
}
