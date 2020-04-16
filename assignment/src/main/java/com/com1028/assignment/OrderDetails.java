package com.com1028.assignment;

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

  /**
   * @return the orderNumber
   */
  public int getOrderNumber() {
    return this.orderNumber;
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
