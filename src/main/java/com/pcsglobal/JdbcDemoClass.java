/* JDBC Steps
 * ==========
 * 1. import	---> java.sql.*
 * 2. Load and register  ---> load "com.mysql.jdbc.Driver" into Kernel Register --> MySQL-Connector in lib folder
 * 3. Create Connection  ---> Create the object of Connection
 * 4. Create a statement ---> Create the object of Statement
 * 5. execute the query --> 
 * 6. process the results
 * 7. Close
 */


package com.pcsglobal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemoClass 
{

	public static void main(String[] args) throws Exception, SQLException 
	{
		String url = "jdbc:mysql://localhost:3306/jsp";
		String uname = "root";
		String pass = "password";
		
		// Prepared statement dynamic query variables
		int rollno = 105;
		String name = "Malini";
		int marks = 87;
		
		// Single Fetch (DQL)
		// String query = "select name from students where rollno = 103";
		
		// Multiple Fetch (DQL)
		String query = "select * from students";  

		/* Connection & Statement Objects */
		Class.forName("com.mysql.cj.jdbc.Driver");    // Class forName load and register the driver
		Connection con = DriverManager.getConnection(url, uname, pass); 
		// Connection is an Interface, getConnection is a static method of class DriverManager. 
		// Interface can be only be used as a reference but object cannot be instantiated 
		Statement st = con.createStatement(); 
		// Statement is an interface hence we use createStatement() method of Connection object to create statement object
		
		// Insert (DML) -> try catch block to eliminate the error for Primary key constraint violation
		try {
			String insert = "insert into students values (104, \"Naveen\", 98)";
			String insert1 = "insert into students values (?,?,?)";  // for preparedStatement object
			PreparedStatement pst = con.prepareStatement(insert1);
			// PreparedStatement is to compose a query with unknowns ? to avoid the use of "" and "+" and "variables" 
			pst.setInt(1, rollno);
			pst.setString(2, name);
			pst.setInt(3, marks);
			
			// int count = st.executeUpdate(insert); // DML -> executeUpdate returns an int a count of rows affected 
			int count1 = pst.executeUpdate();   // DML -> executeUpdate without argument for preparedStatement 
			
			/* Insert -> does not need fetch ResultSet */
			System.out.println("Count of row(s) affected: "+count1);
			
		} catch(SQLException se) 
		{ 
			se.getMessage(); 
		}
		
		ResultSet rs = st.executeQuery(query); // DQL
	
		/* --- Single Fetch (DQL) ----
		rs.next();  // move to the first record from the column header	
		String name = rs.getString("name");
		System.out.println(name);
		*/
		
		/* --- Multiple Fetch (DQL) */
		System.out.println("Roll No.    Name        Marks");
		System.out.println("=============================");
		String userData = "";
		
		
		/* After insert let's display all the records including the one inserted... */
		while(rs.next())
		{
			/*
			int rollno = rs.getInt(1);
			String name = rs.getString(2);
			int marks = rs.getInt(3);
			System.out.println(rollno + "\t    " + name + "\t " + marks );
			*/
			
			// OR
			userData = (rs.getInt(1)+ "\t    " + rs.getString(2) + "\t " + rs.getInt(3));
			System.out.println(userData);
		} 
	
		st.close();
		con.close();
	}

}
