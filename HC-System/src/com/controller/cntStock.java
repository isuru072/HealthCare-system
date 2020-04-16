package com.controller;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.java.stock;

import config.dbconnector;

public class cntStock {

	Connection con = null;
	
	public cntStock()
	{		 
		con = dbconnector.connecter();
	} 
	
	
	public List<stock>getStock(){		
		
	   	 
	   	 List<stock> stockList = new ArrayList<>();
	   	 String sql = "select * from stock";
	   	 try 
	   	   {
	   		 
	   		 	Statement st = (Statement)con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next())
				  {
						stock appdata = new stock();
					  appdata.setId(rs.getInt(1));
					  appdata.setMname(rs.getString(2));
					  appdata.setDescription(rs.getString(3));
					  appdata.setAmount(rs.getInt(4));
					  appdata.setDate(rs.getDate(5));
					  appdata.setTime(rs.getTime(6));
					  appdata.setPrice(rs.getInt(7));
					  
					  stockList.add(appdata);
				  }
				
			    } 
	   	 catch (Exception e) 
	   	  {
				
			   System.out.println(e);
			  }
	   	  
	   	 return stockList;
	    }
	    
}

