package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbc_App7 {
	 private static final String driver="com.mysql.cj.jdbc.Driver";
	 private static final String dburl="jdbc:mysql://localhost:3306/bankapp";
	 private static final String username="root";
	 private static final String password="Qwer1357!";
	 void meth1() {
		 System.out.println(ResultSet.TYPE_FORWARD_ONLY); //1003
		 System.out.println(ResultSet.TYPE_SCROLL_INSENSITIVE); //1004
		 System.out.println(ResultSet.TYPE_SCROLL_SENSITIVE); //1005
		 System.out.println(); 
		 System.out.println(ResultSet.CONCUR_READ_ONLY); //1006
		 System.out.println(ResultSet.CONCUR_UPDATABLE); //1007
	 }
	 public static Connection connect() {
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
	 void meth2() {
		 try {
			 Connection con=connect();
			 Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			 ResultSet rs=stmt.executeQuery("select * from employee");
			 while(rs.next()) {
				 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			 }
			 System.out.println("--------------");
			 rs.last();
				 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
                 System.out.println("--------------");
			 
			 rs.absolute(3);
			 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
              System.out.println("----------------");
             
             rs.relative(-1);
			 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
             System.out.println("----------------");

			 rs.first();
			 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
             System.out.println("----------------");

             rs.last();
             rs.previous();
			 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
             System.out.println("----------------");


		 }catch(Exception e) {
			 e.printStackTrace();
		 }	 
	 }
	 void meth3(){
		 try {
			 Connection con=connect();
			 Statement stmt2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			 ResultSet rs2=stmt2.executeQuery("select eid , efname , esal from employee");
			 while(rs2.next()) {
				 String eId=rs2.getString(1);
				 if(eId.equals("202")) {
					 System.out.println("Updating the row:");
					 rs2.updateInt("esal", 55000);
					 rs2.updateRow();
				 }
			 }
			 System.out.println("Data updated!");
			 Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			 ResultSet rs3=stmt.executeQuery("select * from employee");
			 rs3.absolute(3);
			 System.out.println(rs3.getString(2)+"--->"+rs3.getInt(4));
			 rs3.absolute(6);
			 System.out.println(rs3.getString(2)+"--->"+rs3.getInt(4));
			 con.close();
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
	 public static void main(String[] args) {
		new Jdbc_App7().meth2();
		new Jdbc_App7().meth3();
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
