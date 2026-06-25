package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
public class Jdbc_App13 {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String dburl = "jdbc:mysql://localhost:3306/bankapp";
	private static final String username = "root";
	private static final String password = "Qwer1357!";
	private String sqlQuery1="select available_seats from MovieSeatAvailability where Movie_id=?";
	private String sqlQuery2="update MovieSeatAvailability set available_seats=available_seats-1 where Movie_id=?";
	private String sqlQuery3="insert into MovieBookingDetails values(?,?,?,?,?)";
	private String sqlQuery4="insert into Customer_Payment_details values(?,'Success')";
    private String sqlQuery5="update MovieBookingDetails set status='success' where customer_id=?";
    private String sqlQuery6="Delete from MovieBookingDetails where customer_id=?";
    private String sqlQuery7="update MovieSeatAvailability set available_seats=available_seats+1  where Movie_id=?";
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
	int number=1002;
     
     
     
	void meth1() {
		try {
			con.setAutoCommit(false);
			System.out.println("Enter Movie ID:");
			String movie_id=sc.nextLine();
			System.out.println("Enter Customer ID:");
			String customer_id=sc.nextLine();
			System.out.println("Enter Seat Number:");
			int seat_number=Integer.parseInt(sc.nextLine());
			bookTicket(movie_id,customer_id,seat_number);
			con.commit();
			System.out.println("Transaction Successful");
		}catch(Exception e) {
			 try {
				con.rollback();
			 } catch (SQLException e1) {
				e1.printStackTrace();
			 }
			e.printStackTrace();
		}
	}
	
	
	public void bookTicket(String MovieID ,String customerID , int seatNumber) {
		String booking_id="B"+number;
		try {
			PreparedStatement pstmt1=con.prepareStatement(sqlQuery1);
			pstmt1.setString(1,MovieID);
			ResultSet rs1=pstmt1.executeQuery();
			if(rs1.next()) {
				int availableseats=Integer.parseInt(rs1.getString(1));
				if(availableseats <=0) {
					System.out.println("Seats are not available!");
					return;
				}
				else {
					CheckAvailability(MovieID);
//					System.out.println("Seat available");
//					System.out.println("No of seats available:"+rs1.getString(1));
					PreparedStatement pstmt3=con.prepareStatement(sqlQuery2);
					pstmt3.setString(1, MovieID);
					int rowcount=pstmt3.executeUpdate();
					if(rowcount>0) {
						System.out.println("Seat locked");
						System.out.println();
						PreparedStatement pstmt4=con.prepareStatement(sqlQuery3);	
						pstmt4.setString(1, booking_id);
						number++;
						pstmt4.setString(2, MovieID);
						pstmt4.setString(3, customerID);
						pstmt4.setInt(4,seatNumber);
						pstmt4.setString(5, "Pending payment");
						int rowcount2=pstmt4.executeUpdate();
						if(rowcount2>0) {
							System.out.println("Waiting for payment");
							System.out.println();
							System.out.println("Ready to proceed:");
							System.out.println("1.YES");
							System.out.println("2.NO");
							int status=Integer.parseInt(sc.nextLine());
							
							switch(status) {
							case 1:PaymentProcess(customerID);
							       break;
							case 2:CancelTicket(customerID, MovieID);
							       break;
							default:System.out.println("Choose valid option");;
							}
						}
						Savepoint sp=con.setSavepoint();
					}else {
						System.out.println("Seat already booked!");
					}
					}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void CheckAvailability(String MovieID) {
		try {
			PreparedStatement pstmt2=con.prepareStatement(sqlQuery1);
			pstmt2.setString(1, MovieID);
			ResultSet rs2=pstmt2.executeQuery();
			if(rs2.next()) {
				System.out.println("No of seats available:"+rs2.getString(1)); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	
	public void PaymentProcess(String customerID) {
		try {
			PreparedStatement pstmt5=con.prepareStatement(sqlQuery4);
			pstmt5.setString(1, customerID);
			int rowcount=pstmt5.executeUpdate();
			if(rowcount>0) {
				System.out.println("Payment done , Ticket Confirmed");
				System.out.println("Thank you!");
				 PreparedStatement pstmt6 = con.prepareStatement(sqlQuery5);
		            pstmt6.setString(1, customerID);
		            int rowcount3 = pstmt6.executeUpdate();
		            if (rowcount3 > 0) {
		                System.out.println("Customer details inserted successfully.");
		            } else {
		                System.out.println("Failed to insert customer details.");
		            }
			}
			else {
				System.out.println("Ticket booking pending, please try again");
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void CancelTicket(String customerID, String MovieID) {
		try {
			PreparedStatement pstmt6=con.prepareStatement(sqlQuery6);
			pstmt6.setString(1, customerID);
			int rowcount4=pstmt6.executeUpdate();
			if(rowcount4>0) {
				System.out.println("Ticket Cancelled and Booking details are deleted");
				PreparedStatement pstmt7=con.prepareStatement(sqlQuery7);
				pstmt7.setString(1, MovieID);
				int rowcount5=pstmt7.executeUpdate();
				if(rowcount5>0) {
					System.out.println("You can leave the site");
				}
				else {
					System.out.println("Try again");
				}
				
			}else {
				System.err.println("Please try again later");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		Jdbc_App13 obj=new Jdbc_App13();
		obj.meth1();
	}
}
