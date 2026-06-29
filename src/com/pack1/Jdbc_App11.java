package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Jdbc_App11 {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String dburl = "jdbc:mysql://localhost:3306/bankapp";
	private static final String username = "root";
	private static final String password = "Qwer1357!";
	private String sqlQuery1 = "Update trainseatavailability set AVAILABLE_SEATS=AVAILABLE_SEATS-1 where TRAIN_ID=? and JOURNEY_DATE=? and CLASS=? and AVAILABLE_SEATS>0";
	private String sqlQuery2 = "insert into bookingdetails values(?,?,?,?,?);";
	private String sqlQuery3 = "select payment_status from customerpayment where customer_id=?";
	private String sqlQuery4 = "update bookingdetails set STATUS='success' where customer_id=?";
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
		Connection con = connect();
		try {
			con.setAutoCommit(false);
			PreparedStatement pstmt1 = con.prepareStatement(sqlQuery1);
			System.out.println("Enter Train Id:");
			String tid = sc.nextLine();
			System.out.println("Enter Journey Date:");
			String tdate = sc.nextLine();
			System.out.println("Enter Class:");
			String tclass = sc.nextLine();
			pstmt1.setString(1, tid);
			pstmt1.setString(2, tdate);
			pstmt1.setString(3, tclass);
			int rowcount = pstmt1.executeUpdate();
			if (rowcount > 0) {
				System.out.println("Train Seat confirmation pending!");
			} else {
				throw new RuntimeException("No Seats Available!");
			}
	//		Savepoint sp = con.setSavepoint();
			PreparedStatement pstmt2 = con.prepareStatement(sqlQuery2);
			pstmt2.setString(1, "B101");
			pstmt2.setString(2, "12345");
			pstmt2.setString(3, "C123");
			pstmt2.setInt(4, 10);
			pstmt2.setString(5, "payment pending");
			int rowCount2 = pstmt2.executeUpdate();
			if (rowCount2 > 0) {
				System.out.println("Booking record created ...waiting for payment confirmation");
			} else {
				throw new RuntimeException("Booking Failed !");
			}
			PreparedStatement pstmt3 = con.prepareStatement(sqlQuery3);
			pstmt3.setString(1, "C123");
			ResultSet rs1 = pstmt3.executeQuery();
			if (rs1.next()) {
				if (rs1.getString(1).equals("success")) {
					PreparedStatement pstmt4 = con.prepareStatement(sqlQuery4);
					pstmt4.setString(1, "C123");
					int rowCount3 = pstmt4.executeUpdate();
					if (rowCount3 == 0) {
						throw new RuntimeException("Transaction Failed !");
					} else {
						System.out.println("Booking confirmed");
						System.out.println("Happy Journey");
						con.commit();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Jdbc_App11 obj = new Jdbc_App11();
		obj.meth1();
	}
}
