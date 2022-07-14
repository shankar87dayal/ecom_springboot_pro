package com.ecom.payload;

import java.util.Date;

import javax.validation.constraints.NotEmpty;


public class OrderDto {

	
    private int orderId;
    
	@NotEmpty
	private String orderStatus;
	
	private String paymentStatus;
	
	
	private Date orderCreated;
	
	private double totalAmount;
	
	private String billingAddress;
	
	private Date orderDelivered;

	public OrderDto(int orderId, String orderStatus, String paymentStatus, Date orderCreated, double totalAmount,
			String billingAddress, Date orderDelivered) {
		super();
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.orderCreated = orderCreated;
		this.totalAmount = totalAmount;
		this.billingAddress = billingAddress;
		this.orderDelivered = orderDelivered;
	}

	public OrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Date getOrderDelivered() {
		return orderDelivered;
	}

	public void setOrderDelivered(Date orderDelivered) {
		this.orderDelivered = orderDelivered;
	}
	
	
}
