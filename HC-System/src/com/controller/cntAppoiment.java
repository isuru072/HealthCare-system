package com.controller;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.java.appoiment;

import config.dbconnector;

public class cntAppoiment {
	
	Connection con = null;
		
	public cntAppoiment()
	{		 
		con = dbconnector.connecter();
	} 
	
	
	public List<appoiment>getAppoiment(){		
		
	   	 
	   	 List<appoiment> appoimentList = new ArrayList<>();
	   	 String sql = "select * from appointment";
	   	 try 
	   	   {
	   		 
	   		 	Statement st = (Statement)con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next())
				  {
					  appoiment appdata = new appoiment();
					  appdata.setId(rs.getInt(1));
					  appdata.setDname(rs.getString(2));
					  appdata.setPname(rs.getString(3));
					  appdata.setDate(rs.getDate(4));
					  appdata.setTime(rs.getTime(5));
					  
					  appoimentList.add(appdata);
				  }
				
			    } 
	   	 catch (Exception e) 
	   	  {
				
			   System.out.println(e);
			  }
	   	  
	   	 return appoimentList;
	    }
	    
}
