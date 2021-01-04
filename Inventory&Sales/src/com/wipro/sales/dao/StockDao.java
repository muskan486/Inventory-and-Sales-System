package com.wipro.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.wipro.sales.bean.Product;
import com.wipro.sales.util.DBUtil;

public class StockDao {
	
	Connection c=DBUtil.getDBConnection();
	PreparedStatement ps;
	Product o=new Product();
	
	
	
public int insertStock(Product sales)
{
	 int t=0;
	  try
	  {
	   ps=c.prepareStatement("insert into JAVAWEB.tbl_stock values(?,?,?,?,?)");
	   
	   ps.setString(1,sales.getProductID());
	   ps.setString(2,sales.getProductName());
	   ps.setInt(3, sales.getQuantityOnHand());
	   ps.setDouble(4,sales.getProductUnitPrice());
	   ps.setInt(5,sales.getReorderLevel());
	   
	   t=ps.executeUpdate();
	   
	  }
	  catch(SQLException e)
	  {
	   //e.printStackTrace();
	  }
	return t;
}
public String generateProductID(String productName)
{
try {
	
int nextID_from_seq=0;
String s=productName.substring(0,2);
		ps=c.prepareStatement("select JAVAWEB.seq_product_id.nextval from dual");
        ResultSet rs=	ps.executeQuery();
      
		
		if(rs.next())
		      nextID_from_seq=rs.getInt(1);
		       String productId=s+nextID_from_seq;
		       return productId;
}catch(Exception e)
{
	System.out.print(e);
}
			return null;
		
	
}

public int updateStock(String productID,int soldQty)
{
	int t=0;
	try {
		
	ps=c.prepareStatement("select quantity_on_hand from JAVAWEB.tbl_stock where product_id=?");
	ps.setString(1, productID);
	ResultSet rs=ps.executeQuery();
	int curQty=0;
	if(rs.next()) {
		curQty=rs.getInt(1);
	}
    
    ps=c.prepareStatement("update JAVAWEB.tbl_stock set quantity_on_hand =? where product_id = ?");
    ps.setInt(1, (curQty-soldQty));
    ps.setString(2, productID);
    t=ps.executeUpdate();

}catch(SQLException e)
	{
	System.out.print(e);
	}
	return t;
}
	

public Product getProduct(String productID)
{
	try {
		
	
	ps=c.prepareStatement("select * from JAVAWEB.tbl_stock where product_id = ? ");
	ps.setString(1, productID);
	ResultSet rs=ps.executeQuery();
	while(rs.next())
	{
		o.setProductID(rs.getString(1));
		o.setProductName(rs.getString(2));
		o.setProductUnitPrice(rs.getDouble(4));
		o.setQuantityOnHand(rs.getInt(3));
		o.setReorderLevel(rs.getInt(5));
		
	}
}catch(SQLException e)
	{
	e.printStackTrace();
	}
	return o;	
	
	
}

public int deleteStock(String productID)
{
	int t=0;
	try {
		
	
	ps=c.prepareStatement("delete from JAVAWEB.tbl_sales where product_id = ?");
	ps.setString(1, productID);
	t=ps.executeUpdate();
	ps=c.prepareStatement("delete from JAVAWEB.tbl_stock where product_id = ?");
	ps.setString(1, productID);
	t=ps.executeUpdate();
	return 1;
	
}catch(Exception e)
	{
	System.out.print(e);
	}
return 0;

 }
}
