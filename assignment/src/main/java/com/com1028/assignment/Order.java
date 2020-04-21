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
  private List<OrderDetails> products = null;
  private static List<Order> orders = new ArrayList<Order>();

  public Order(int orderNumber, int customerNumber) {
	super();
	this.orderNumber = orderNumber;
	this.customerNumber = customerNumber;
	this.products = new ArrayList<OrderDetails>();
  }

  public static List<Order> getOrders() {
	if (!orders.isEmpty()) {
	  return orders;
	}
	Map<Integer, Order> ordersMap = new TreeMap<Integer, Order>();

	try {
	  DatabaseConnection conn = DatabaseConnection.getInstance();
	  ResultSet ordersTable = conn.useTable("orders");

	  while (ordersTable.next()) {
		int orderNumber = ordersTable.getInt("orderNumber");
		int customerNumber = ordersTable.getInt("customerNumber");

		ordersMap.put(orderNumber, new Order(orderNumber, customerNumber));
	  }
	  for (OrderDetails product : OrderDetails.getOrderDetails()) {
		Order tmpOrder = ordersMap.get(product.getOrderNumber());
		tmpOrder.addProducts(product);
	  }
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return orders = new ArrayList<Order>(ordersMap.values());
  }

  public static String getOrdersOver5000() {
	StringBuilder builder = new StringBuilder("Order N°\tCustomer N°\tOrder Total\n");
	for (Order order : Order.getOrders()) {
	  if (order.getOrderTotal() > 5000) {
		builder.append(order.toString());
	  }
	}
	return builder.toString();
  }

  /**
   * @param product in an order
   * 
   */
  public void addProducts(OrderDetails product) {
	this.products.add(product);
  }

  /**
   * @return the orderNumber
   */
  public int getOrderNumber() {
	return this.orderNumber;
  }

  /**
   * @return the total
   */
  public double getOrderTotal() {
	double orderTotal = 0;

	for (OrderDetails product : products) {
	  orderTotal += product.getQuantityOrder() * product.getPriceEach();
	}
	return orderTotal;
  }

  /**
   * @return the customerNumber
   */
  public int getCustomerNumber() {
	return this.customerNumber;
  }

  @Override
  public String toString() {
	return String.format(Locale.UK, "%-16s%-16s%.2f\n", this.orderNumber, this.customerNumber, this.getOrderTotal());
  }
}
