package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

public class Jdbc_App12 {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String dburl = "jdbc:mysql://localhost:3306/bankapp";
	private static final String username = "root";
	private static final String password = "Qwer1357!";
	private String sqlQuery1="update bank_account set balance=balance-? where acc_holder_name='Alice'";
	private String sqlQuery2="update bank_account set balance=balance+? where acc_holder_name='Bob'";
	static Scanner sc = new Scanner(System.in);

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
			con.setAutoCommit(false);
			System.out.println("Enter ammount:");
			int amt=Integer.parseInt(sc.nextLine());
			if(!(amt<1000 || amt>=500)) {
				System.out.println("Amount must be in range of 500-1000");
				return;
			}
			PreparedStatement pstmt1=con.prepareStatement(sqlQuery1);
			pstmt1.setInt(1,amt);
			int rowcount1=pstmt1.executeUpdate();
			if(rowcount1>0) {
				System.out.println("Amount"+amt+"debited successfully! Waiting to add in another account.");
			}
			else {
				System.out.println("Process failed!");
				con.rollback();
				return;
			}
			Savepoint sp = con.setSavepoint();
			PreparedStatement pstmt2=con.prepareStatement(sqlQuery2);
			pstmt2.setInt(1, amt);
			int rowcount2=pstmt2.executeUpdate();
			if(rowcount2>0) {
				System.out.println("Amount"+amt+"added successfully!");
				con.commit();
			}
			else {
				System.out.println("Amount not credited!");
				con.rollback(sp);
			}

		}
		catch(Exception e) {
			e.printStackTrace();	
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		Jdbc_App12 obj=new Jdbc_App12();
				obj.meth1();
	}
}
