package com.pack1;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Jdbc_App2 {
	 private String driver="com.mysql.cj.jdbc.Driver";
	 private String dburl="jdbc:mysql://localhost:3306/bankapp";
	 private String username="root";
	 private String password="Qwer1357!";
	 private String sqlQuery="select * from Employee";
	 
	 public Connection connect() {
		 Connection con=null;
		 try {
			 Class.forName(driver);
			  con=DriverManager.getConnection(dburl,username,password);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 return con;
	 }
	 public void get_empdata() {
		 try {
			 Connection con=connect();
			 Statement stmt=con.createStatement();
			 ResultSet rs=stmt.executeQuery(sqlQuery);
			 while(rs.next()) {
				 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			 }
			 
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 public static void main(String[] args) {
		new Jdbc_App2().get_empdata();
	}
}
/*
 * Database schema
 *  Employee table-> EID      VARCHAR(20)     PRIMARY KEY 
 *                   EFNAME   VARCHAR(20) 
 *                   ELNAME   VARCHAR(20) 
 *                   ESAL     INTEGER(10) 
 *                   EADDRESS VARCHAR(20)
 */
