package com.pack1;

import java.sql.DriverManager;
import java.sql.Connection;


public class jdbc_App1 {
 
	 public String driver="com.mysql.cj.jdbc.Driver";
	 public String dburl="jdbc:mysql://localhost:3306/bankapp";
	 public String username="root";
	 public String password="Qwer1357!";
	 
	 public void connect() {
		 System.out.println("connecting to database");
		 try {
			 Class.forName(driver);
			 Connection con=DriverManager.getConnection(dburl,username , password);
			 System.out.println("connection created");
			 con.close();
			 
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	   public static void main(String[] args) {
		jdbc_App1 obj=new jdbc_App1();
		obj.connect();
	}
}
