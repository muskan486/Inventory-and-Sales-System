package com.wipro.sales.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.util.DBUtil;

public class SalesDao {
	
	Connection c=DBUtil.getDBConnection();
	 PreparedStatement ps;
	 //Sales s=new Sales();
	
public int insertSales(Sales sales)
	{
	//java.sql.Date sqlDate=new java.sql.Date(sales.getSalesDate().getTime());
		 int t=0;
		  try
		  {
		   ps=c.prepareStatement("insert into JAVAWEB.tbl_sales values(?,?,?,?,?)");
		   
		   ps.setString(1,sales.getSalesID());
		   ps.setDate(2,(Date) sales.getSalesDate());
		   ps.setString(3,sales.getProductID());
		   ps.setInt(4, sales.getQuantitySold());
		   ps.setDouble(5,sales.getSalesPricePerUnit());
		   
		   t=ps.executeUpdate();
		   
		  }
		  catch(SQLException e)
		  {
		   //e.printStackTrace();
			  System.out.print(e);
		  }
		return t;
		
}
	
public String generateSalesID(java.util.Date salesDate)
	{
	try {
		java.sql.Date dateValue=new java.sql.Date(salesDate.getTime());
		int nextID_from_seq=0;
		Calendar cal=Calendar.getInstance();
		cal.setTime(dateValue);
		int year=cal.get(Calendar.YEAR);
		
	
ps=c.prepareStatement("select JAVAWEB.seq_sales_id.nextval from dual");
	        ResultSet rs=	ps.executeQuery();
	        
			if(rs.next())
			
				//return ""+d+rs.getInt(1);
				nextID_from_seq=rs.getInt(1);
				String salesId=""+(year%100)+nextID_from_seq;
				return salesId;
			
			
}catch(Exception e)
	{
	System.out.print(e);
	}
	return null;
}
public ArrayList<SalesReport> getSalesReport()
	{
		
		ArrayList<SalesReport> m=new ArrayList<SalesReport>();
		try {
			SalesReport sl=new SalesReport();
			ps=c.prepareStatement("select * from JAVAWEB.V_SALES_REPORT");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				sl.setSalesID(rs.getString(1));
				sl.setProductID(rs.getString(3));
				sl.setSalesDate(rs.getDate(2));
				sl.setProductName(rs.getString(4));
				sl.setQuantitySold(rs.getInt(5));
				sl.setSalesPricePerUnit(rs.getInt(7));
				sl.setProductUnitPrice(rs.getInt(6));
				sl.setProfitAmount(rs.getInt(8));
				m.add(sl);
				
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return m;
		
	}

}
