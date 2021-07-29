// DAO demo for JDBC
// Here we have only one controlling class JdbcDaoDemo
// which calls the DAO class for JDBC functions
// The Student is an entity class having the student attributes and getters and setters

package com.pcsglobal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcDaoDemo 
{

	public static void main(String[] args) 
	{
		StudentDao dao = new StudentDao();
		Students s1 = dao.getStudent(103);
		System.out.println(s1.name + " ||  " + s1.marks);
		System.out.println(s1);
	}
}

class Students
{
	int rollno;
	String name;
	int marks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Students [rollno=" + rollno + ", name=" + name + ", marks=" + marks + "]";
	}
	
}

class StudentDao
{
	@SuppressWarnings(value = { })
	public Students getStudent(int rollno)
	{
		Students s = new Students();
		try {		
			s.rollno = rollno;
			String query = "select name, marks from students where rollno = "+rollno;
			// JDBC connection here
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jsp", "root", "password");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			String name = rs.getString(1);
			int marks = rs.getInt(2);
			s.name = name;
			s.marks = marks;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return s;
	}
}