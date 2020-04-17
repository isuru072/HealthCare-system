package com.controller;

import java.sql.*;
import com.java.stock;


import config.dbconnector;

public class cntStock {

	Connection con = null;
	
	public cntStock()
	{		 
		con = dbconnector.connecter();
	} 
	
	

public String insertstock(stock Stock)
	 {
		String query = " insert into stock(`id`,`mname`,`description`,`amount`,`price`)"
				  + " values (?,?, ?, ?, ?)";
		  
	 String output;
		try {	
				PreparedStatement preparedStatement = con.prepareStatement(query); 
				preparedStatement.setInt(1, Stock.getId());
				preparedStatement.setString(2, Stock.getMname());
				preparedStatement.setString(3, Stock.getDescription());
				preparedStatement.setInt(4,  Stock.getAmount());
				preparedStatement.setInt(5, Stock.getPrice());
				preparedStatement.execute();
				 con.close();
			  output = "Inserted successfully";
			
		} catch (SQLException e) {
		    output = "Error while inserting the stock.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}



public String readstock()
{
String output = "";
try
{

if (con == null)
{return "Error while connecting to the database for reading."; }
// Prepare the html table to be displayed
output = "<table border=\"1\"><tr><th>sto.ID</th><th>Medicine Name</th><th>Description</th><th>Amount</th><th>Price</th><th>Update</th><th>Remove</th></tr>";
String query = "select * from stock";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
	 int id = rs.getInt("id");
	 String mname = rs.getString("mname");
	 String description = rs.getString("description");
	 int amount = rs.getInt("amount");
	 int price = rs.getInt("price");
	 
// Add into the html table
	 output += "<tr><td>" + id + "</td>";
	 output += "<td>" + mname + "</td>";
	 output += "<td>" + description + "</td>";
	 output += "<td>" + amount + "</td>";
	 output += "<td>" + price + "</td>";
// buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"items.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
			 + "<input name=\"id\" type=\"hidden\" value=\"" + id
			 + "\">" + "</form></td></tr>";
}
con.close();
// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the Stocks.";
System.err.println(e.getMessage());
}
return output;
}




public String updatestock(stock Stock){

String query = "UPDATE Stock SET mname=?,description=?,amount=?,price=? WHERE id=?";
String output = "";
try
{

if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
PreparedStatement preparedStatement = con.prepareStatement(query);

	preparedStatement.setInt(5, Stock.getId());
	preparedStatement.setString(1, Stock.getMname());
	preparedStatement.setString(2, Stock.getDescription());
	preparedStatement.setInt(3,  Stock.getAmount());
	preparedStatement.setInt(4, Stock.getPrice());
	
	
	preparedStatement.execute();
	con.close();
	output = "Updated successfully";
}
catch (Exception e)
{
output = "Error while updating the stock.";
System.err.println(e.getMessage());
}
return output;
}





public String deletestock(stock Stock){
	
	String query = "delete from Stock where id=?";
	String output;
	
	
	try {
		
if (con == null){
	 return "Error while connecting to the database for deleting."; }
// create a prepared statement

PreparedStatement preparedStatement = con.prepareStatement(query);
// binding values
preparedStatement.setInt(1,Stock.getId() );
// execute the statement
if(preparedStatement.execute()) {
	 output = "Deleted successfully";
}else {
	 output = "id not found";
}
	
con.close();

}catch (Exception e){
output = "Error while deleting the stock.";
System.err.println(e.getMessage());
}
return output;
}





}


	

	

