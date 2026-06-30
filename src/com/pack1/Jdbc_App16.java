package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

import java.util.Scanner;
public class Jdbc_App16 {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String dburl = "jdbc:mysql://localhost:3306/bankapp";
	private static final String username = "root";
	private static final String password = "Qwer1357!";
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
			System.out.println("Implementing batch processing");
			Statement stmt=con.createStatement();
			System.out.println("Enter no.of queries you need:");
			int no_of_queries=Integer.parseInt(sc.nextLine());
			for (int i=0;i<no_of_queries;i+=1) {
				System.out.println("Enter query:");
				stmt.addBatch(sc.nextLine());
				
			}
			System.out.println("All Queries added to batch");
			int arr[]=stmt.executeBatch();
			System.out.println("arr:"+Arrays.toString(arr));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_App16().meth1();
	}
}

/*
 Enter database queries , it will automatically executes those queries
 */
