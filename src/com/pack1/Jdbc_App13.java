package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc_App13 {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String dburl = "jdbc:mysql://localhost:3306/bankapp";
	private static final String username = "root";
	private static final String password = "Qwer1357!";
	private String sqlQuery1="select available_seats from MovieSeatAvailability where Movie_id=?";
	static Scanner sc = new Scanner(System.in);
     
	public Connection connect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(dburl, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	Connection con=connect();

	void meth1() {
		Connection con=connect();
		try {
			con.setAutoCommit(false);
			System.out.println("Enter Movie ID:");
			String movie_id=sc.nextLine();
			System.out.println("Enter Customer ID:");
			String customer_id=sc.nextLine();
			System.out.println("Enter Seat Number:");
			int seat_number=Integer.parseInt(sc.nextLine());
			bookTicket(movie_id,customer_id,seat_number);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void bookTicket(String MovieID ,String customerID , int seatNumber) {
		try {
			PreparedStatement pstmt1=con.prepareStatement(sqlQuery1);
			pstmt1.setString(1,MovieID);
			ResultSet rs1=pstmt1.executeQuery();
			if(rs1.next()) {
				int availableseats=Integer.parseInt(rs1.getString(1));
				if(availableseats <=0) {
					System.out.println("Seats are not available!");
				}
				else {
					System.out.println("Seats available");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Jdbc_App13 obj=new Jdbc_App13();
		obj.meth1();
	}
}
