// DAO demo for JDBC part 2
// Here we have only one controlling class JdbcDaoDemo
// which calls the DAO class for JDBC functions
// The Student is an entity class having the student attributes and getters and setters

package com.pcsglobal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.pcsglobal.Students;

public class JdbcDaoDemo2 
{

	public static void main(String[] args) 
	{
		StudentDao2 dao = new StudentDao2();
		Students s2 = new Students();
		s2.rollno = 108;
		s2.name = "Nirmalendu";
		s2.marks = 90;
		
		dao.connect();
		int count = dao.addStudent(s2);
		System.out.println("No of row/s affected : "+count);
	}
}

class StudentDao2
{
	Connection con = null;
	public void connect()
	{
		
		try {
			// JDBC connection here
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/jsp", "root", "password");
			
		} catch(Exception ex) { System.out.println(ex); }
	}
	
	public int addStudent(Students s2) 
	{
		int count = 0;
		try { 
			String query = "insert into students values (?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, s2.rollno);
			pst.setString(2, s2.name);
			pst.setInt(3, s2.marks);
			count = pst.executeUpdate();
			
		} catch(Exception ex) { System.out.println(ex); }
		return count;
		
	}
}