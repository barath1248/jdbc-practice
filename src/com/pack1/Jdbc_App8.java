package com.pack1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.sun.tools.javac.Main;
public class Jdbc_App8 {
	 private static final String driver="com.mysql.cj.jdbc.Driver";
	 private static final String dburl="jdbc:mysql://localhost:3306/bankapp";
	 private static final String username="root";
	 private static final String password="Qwer1357!";
	 static Scanner sc=new Scanner(System.in);
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
	 
	 void meth2(){
		 try {
			 Connection con=connect();
			 System.out.println("Employee data with id,firstName , salary:");
			 System.out.println("------------------------");
			 String sql="select eid , efname , esal from employee";
			 PreparedStatement pstmt=con.prepareStatement(sql);
			 ResultSet rs=pstmt.executeQuery();
			 while(rs.next()) {
			  System.out.println(rs.getString(1)+" "+rs.getString(2)+"  "+rs.getInt(3));				 
			 }
			 String sql2="select eid , efname , esal from employee";
			 PreparedStatement pstmt2=con.prepareStatement(sql2,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			 System.out.println("Enter id to update the salary:");
			 String eId=sc.nextLine();
			 System.out.println("Enter id to update the salary:");
			 int Salary=Integer.parseInt(sc.nextLine());
			 ResultSet rs2=pstmt2.executeQuery();
			 while(rs2.next()) {
				    if(rs2.getString("eid").equals(eId)) {
				        rs2.updateInt("esal", Salary);
				        rs2.updateRow();
				        System.out.println("Salary Updated!");
				        break;
				    }
				}
			 
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 public static void main(String[] args) {
		new Jdbc_App8().meth2();
	}
}
