package com.wipro.sales.bean;

public class Sales {

private	String salesID;
private	String productID;
	private int quantitySold;
	private double salesPricePerUnit;
	private java.sql.Date salesDate;
	
	public String getSalesID() {
		return salesID;
	}
	public void setSalesID(String salesID) {
		this.salesID = salesID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public int getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
	public double getSalesPricePerUnit() {
		return salesPricePerUnit;
	}
	public void setSalesPricePerUnit(double salesPricePerUnit) {
		this.salesPricePerUnit = salesPricePerUnit;
	}
	public java.sql.Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(java.sql.Date salesDate) {
		this.salesDate = salesDate;
	}

}
