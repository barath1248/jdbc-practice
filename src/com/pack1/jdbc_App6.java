package com.pack1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
public class jdbc_App6 {
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
	 
	 void Operations(){
		 Connection con=jdbc_App6.connect();
		 String sql="insert into patient values(?,?,?,?)";
		 try {
			 PreparedStatement pstmt=con.prepareStatement(sql);
			 outer:while(true) {
				 System.out.println("Welcome to patient database operations");
				 System.out.println("1.Add patient data\n2.View Patient data/n3.Retrive patient data\n4.Update patient data\n5.Delete patient data\n6.Exit");
				 int choice=Integer.parseInt(sc.nextLine());
				 switch(choice) {
				 case 1:System.out.println("Adding patient data");
				        System.out.print("Enter patient id:");
				        String id=sc.nextLine();
				        System.out.print("Enter patient Name:");
				        String name=sc.nextLine();
				        System.out.print("Enter patient Age:");
				        int Age =Integer.parseInt(sc.nextLine());
				        System.out.print("Enter patient contact number:");
				        long num=Long.parseLong(sc.nextLine());
				        pstmt.setString(1,id);
				        pstmt.setString(2,name);
				        pstmt.setInt(3, Age);
				        pstmt.setLong(4, num);
				        int rowCount=pstmt.executeUpdate();
				         if(rowCount>0) {
				        	 System.out.println("Data enterd successfully !");
				        }
				         else {
				        	  throw new RuntimeException("Check once ! Data not entered.");
				         }
					    break;
				 case 2:System.out.println("Patient data");
					    break;
				 case 3:System.out.println("Retriveing patient data");
					    break;
				 case 4:System.out.println("Updating patient data");
					    break;
				 case 5:System.out.println("Deleting patient data");
					    break;
				 case 6:System.out.println("Thank you!");
					    break outer;
                 default:System.out.println("Invalid input");
				 }
			 }
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 public static void main(String[] args) {
		new jdbc_App6().Operations();
	}
}
 