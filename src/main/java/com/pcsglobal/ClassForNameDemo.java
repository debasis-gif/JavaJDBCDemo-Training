// Class.forName()

package com.pcsglobal;

import java.sql.DriverManager;
import java.sql.SQLException;

// import java.lang.*;

public class ClassForNameDemo 
{

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
	{
		/*
		 * when we run the code without anything in the main block, the result is
		 * nothing but when we have the code below and run then the result is
		 * "In Static block" "In Instance block"
		 */

		/* 
		 * Instantiate an object lr, static block is executed first followed by Instance
		 * block
		 * This is because before creating an object it will first load a class
		 * and while loading a class it will call the static method
		 * while creating the object it will call the instance
		 */
		// LoadAndRegister lr = new LoadAndRegister();

		/* So, to create an object we call both the static and instance method/block
		 * But if we only want to load the class without trying to create the object,
		 * calling the Class LoadAndRegister executing only the static block
		 * Output on run is "In Static block"
		*/
		Class.forName("com.pcsglobal.LoadAndRegister");   // here always mention the fully qualified path for the class

		/*
		 * Again if we want to create an object by creating the new instance we register the class
		 * The instance block is also invoked 
		 * the output is "In Static block", "In Instance block"
		 */
		Class.forName("com.pcsglobal.LoadAndRegister").newInstance();  // Instantiating the object
		
		/*
		 * Similarly, JDBC driver load and register is a 2 step process
		 */
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());   // Static block of DriverManager class is executed for loading
		/* Here internally Java has a DriverManager Class like this:-
		 * public class DriverManager {
		 *     static {
         * 			loadInitialDrivers();
         * 			println("JDBC DriverManager initialized");
         *    }
         * }
 		 */
		
		// The above is similar to the following for JDBC driver to be loaded and registered
		Class.forName("com.mysql.jdbc.Driver");
		
		/* Conclusion:-
		 * Class.forName() method loads the Driver class 
		 * which in turn will execute the Driver class static block to register the driver
		 * 
		 */
	}

}

// To understand what Class.forName() does we create a class LoadAndRegister with Static and Instance block

class LoadAndRegister 
{
	// static block is executed first when we try to load the class
	static 
	{
		System.out.println("In Static block");
	}

	// Instance block which is executed when we create an object of the class
	{
		System.out.println("In Instance block");
	}
}
