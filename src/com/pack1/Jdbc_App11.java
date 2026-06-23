package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class Jdbc_App11 {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String dburl = "jdbc:mysql://localhost:3306/bankapp";
	private static final String username="root";
	private static final String password="Qwer1357!";
	private String sqlQuery1="Update trainseatavailability set AVAILABLE_SEATS=AVAILABLE_SEATS-1 where TRAIN_ID=? and JOURNEY_DATE=? and CLASS=? and AVAILABLE_SEATS>0";
	static Scanner sc=new Scanner(System.in);
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
	void meth1() {
		Connection con=connect();
		try {
			con.setAutoCommit(false);
			PreparedStatement pstmt1=con.prepareStatement(sqlQuery1);
			System.out.println("Enter Train Id:");
			String tid=sc.nextLine();
			System.out.println("Enter Journey Date:");
			String tdate=sc.nextLine();
			System.out.println("Enter Class:");
			String tclass=sc.nextLine();
			pstmt1.setString(1, tid);
			pstmt1.setString(2, tdate);
			pstmt1.setString(3, tclass);
			int rowcount=pstmt1.executeUpdate();
			if(rowcount>0) {
				System.out.println("Train Seat confirmed !");
				con.commit();
			}
			else {
				throw new RuntimeException("No Seats Available!");
			}
		}
		catch(Exception e) {
			e.printStackTrace();		}
	}
	public static void main(String[] args) {
		Jdbc_App11 obj=new Jdbc_App11();
		obj.meth1();
	}
}
