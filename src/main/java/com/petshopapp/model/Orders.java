package com.petshopapp.model;

import java.sql.Date;
public class Orders {
	
	private int orderId;
	private Customers customer = new Customers();
	private double totalprice;
	private String orderStatus;
	private Date orderDate;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Customers getCustomer() {
		return customer;
	}
	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", customer=" + customer + ", Totalprice=" + totalprice + ", orderStatus="
				+ orderStatus + ", orderDate=" + orderDate + "]";
	}
	public Orders() {
		super();
	}
	public Orders(int orderId, Customers customer, double totalprice, String orderStatus, Date orderDate) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.totalprice = totalprice;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
	}
		
}
