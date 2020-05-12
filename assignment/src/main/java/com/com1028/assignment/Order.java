package com.com1028.assignment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Order {

  private int orderNumber = 0;
  private int customerNumber = 0;
  // list containing all products in an Order
  private List<OrderDetails> products = null;
  // list containing all Order objects
  private static List<Order> orders = new ArrayList<Order>();

  public Order(int orderNumber, int customerNumber) {
	super();
	this.orderNumber = orderNumber;
	this.customerNumber = customerNumber;
	this.products = new ArrayList<OrderDetails>();
  }

  // returns and sets orders list
  public static List<Order> getOrders() {
	// if loop already ran, return end product
	if (!orders.isEmpty()) {
	  return orders;
	}
	// treemap containing orderNumber as key and an Order object as value
	Map<Integer, Order> ordersMap = new TreeMap<Integer, Order>();

	try {
	  // create Order objects and add them to ordersMap along with orderNumber
	  DatabaseConnection conn = DatabaseConnection.getInstance();
	  ResultSet ordersTable = conn.useTable("orders");

	  while (ordersTable.next()) {
		int orderNumber = ordersTable.getInt("orderNumber");
		int customerNumber = ordersTable.getInt("customerNumber");

		ordersMap.put(orderNumber, new Order(orderNumber, customerNumber));
	  }
	  // loop through all OrderDetails and add them to products if the orderNumber matches
	  for (OrderDetails product : OrderDetails.getOrderDetails()) {
		Order tmpOrder = ordersMap.get(product.getOrderNumber());
		tmpOrder.addProducts(product);
	  }
	} catch (SQLException e) {
	  e.printStackTrace(); 
	}
	return orders = new ArrayList<Order>(ordersMap.values());
  }

  public void addProducts(OrderDetails product) {
	this.products.add(product);
  }
  
  public int getCustomerNumber() {
	return this.customerNumber;
  }

  // returns total for this order
  public double getOrderTotal() {
	double orderTotal = 0;

	for (OrderDetails product : products) {
	  orderTotal += product.getQuantityOrder() * product.getPriceEach();
	}
	return orderTotal;
  }
  
  // returns a string containing all order which total are over 5000
  public static String getOrdersOver5000() {
	StringBuilder builder = new StringBuilder("Order N°\tCustomer N°\tOrder Total\n");
	for (Order order : Order.getOrders()) {
	  if (order.getOrderTotal() > 5000) {
		builder.append(order.toString());
	  }
	}
	return builder.toString();
  }

  @Override
  public String toString() {
	return String.format(Locale.UK, "%-16s%-16s%.2f\n", this.orderNumber, this.customerNumber, this.getOrderTotal());
  }
}
