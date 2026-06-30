package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Jdbc_App17 {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String dburl = "jdbc:mysql://localhost:3306/bankapp";
	private static final String username = "root";
	private static final String password = "Qwer1357!";
	private String sqlQuery1="select * from food";
	static Scanner sc=new Scanner(System.in);
	public Connection connect() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(dburl, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	void meth1() {
		Connection con=connect();
		try {
			System.out.println("--->Displaying menu items:");
			PreparedStatement pstmt1=con.prepareStatement(sqlQuery1);
			ResultSet rs1=pstmt1.executeQuery();
			System.out.printf("%-10s %-20s %-10s%n", "Food_ID", "Food_Name", "Price");
			while(rs1.next()) {
			    System.out.printf("%-10d %-20s %-10d%n",
			            rs1.getInt(1),
			            rs1.getString(2),
			            rs1.getInt(3));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_App17().meth1();
	}
}
