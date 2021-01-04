package com.wipro.sales.util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBUtil {
	public static Connection getDBConnection()
	{
		 Connection con;
	      con=null;
		  try
		  {
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","oracle");
		  }
		  
		  catch(Exception e)
		  {
		   System.out.println(e);
		  }
		  return con;
		 }
}
