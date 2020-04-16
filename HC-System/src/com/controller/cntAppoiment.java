package com.controller;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.IntersectionType;

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
					  String date = (rs.getDate(4)).toString();
					  appdata.setDate(date);
					  
					  String time = (rs.getTime(5)).toString();
					  appdata.setTime(time);
					  
					  appoimentList.add(appdata);
				  }
				
			    } 
	   	 catch (Exception e) 
	   	  {
				
			   System.out.println(e);
			  }
	   	  
	   	 return appoimentList;
	    }


	public appoiment getAppoiment(int uid) {
		String sql = "select * from appointment where id="+uid;
	   	  appoiment p = new appoiment();
	   	 try 
	   	   {
				  Statement st = con.createStatement();
				  ResultSet rs = st.executeQuery(sql);
				  if(rs.next())
				  {
					  p.setId(rs.getInt(1));
					  p.setDname(rs.getString(2));
					  p.setPname(rs.getString(3));
					  
					  String date = (rs.getDate(4)).toString();					  
					  p.setDate(date);
					  String time = (rs.getTime(5)).toString();
					  p.setTime(time);
					 
					  
				  }
				
			    } 
	   	 catch (Exception e) 
	   	  {				
			   System.out.println(e);
			  } 
	   	 return p;
	}
	    
}
