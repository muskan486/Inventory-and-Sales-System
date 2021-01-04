package com.wipro.sales.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.wipro.sales.bean.Product;
import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.dao.SalesDao;
import com.wipro.sales.dao.StockDao;
import com.wipro.sales.util.DBUtil;

public class Administrative {
	Connection c=DBUtil.getDBConnection();
	PreparedStatement ps;
	SalesDao l=new SalesDao();
	StockDao k=new StockDao();
	public String insertStock(Product stockobj) throws Exception
	{
		int check=0;
		if(stockobj!=null && stockobj.getProductName().length()>=2)
		{
		String h=k.generateProductID(stockobj.getProductName());
		stockobj.setProductID(h);
		System.out.println(h);
		check=k.insertStock(stockobj);
		}
		
		if(check==1)
		{
			return "Inserted Successfully";
		}
		else
		{
		return "Data not valid for insertion";
		}
		
	}
		
		
		
	
	
public  String deleteStock(String ProductID)
{
	if(k.deleteStock(ProductID)==1)
	{
		System.out.print("1");
		return "Deleted Successfully";
	}
	else
	{
		System.out.print("0");
		return "record cannot be deleted";
	}
}

public String insertSales(Sales salesobj) throws Exception
{
	int qty=0,check=0,checkUpdate=0;
	java.sql.Date date;
	java.sql.Date currentDate;
	String salesid;
	if(salesobj==null)
	{
		return "Object not valid for insertion";
	}
	if(salesobj.getProductID()==null)
	{
		return "Unknown Product for sales";
	}
	 ps=c.prepareStatement("select quantity_on_hand from JAVAWEB.tbl_stock where product_id = ?");
	 ps.setString(1, salesobj.getProductID());
	 ResultSet rs=ps.executeQuery();
	 if(rs.next())
		 qty=rs.getInt(1);
	if(qty<salesobj.getQuantitySold())
	{
		return "Not enough stock on hand for sale";
	}
	date=new java.sql.Date(salesobj.getSalesDate().getTime());
	currentDate=new java.sql.Date(new java.util.Date().getTime());
	
	if(date.compareTo(currentDate)>0)
	{
		return "Invalid date";
	}
	salesid=l.generateSalesID(salesobj.getSalesDate());
	salesobj.setSalesID(salesid);
check=	l.insertSales(salesobj);
	
	if(check!=-1)
	{
		checkUpdate=k.updateStock(salesobj.getProductID(),salesobj.getQuantitySold());
	}
	else {
	 return "error in sales insertion";
	}
	if(check!=-1 && checkUpdate!=-1)
	{
	//if(check!=-1 && checkupdate!=-1)
	    return "Sales Completed";
	
	}else
	{
		return "Error";
	}
}

	
public ArrayList<SalesReport> getSalesReport()
{
	return l.getSalesReport();
}

}
