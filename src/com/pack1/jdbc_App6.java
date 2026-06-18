package com.pack1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;
public class Jdbc_App6 {
	 private static final String driver="com.mysql.cj.jdbc.Driver";
	 private static final String dburl="jdbc:mysql://localhost:3306/bankapp";
	 private static final String username="root";
	 private static final String password="your_password";
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
		 Connection con=Jdbc_App6.connect();
		 String sql1="insert into patient values(?,?,?,?)";
		 String sql2="select * from patient where pid=?";
		 String sql3="update  patient set pcontact=? where pid=?";
		 String sql4="Delete from patient where pid=?";
		 try {
			 PreparedStatement pstmt1=con.prepareStatement(sql1);
			 PreparedStatement pstmt2=con.prepareStatement("select * from patient");
			 PreparedStatement pstmt3=con.prepareStatement(sql2);
			 PreparedStatement pstmt4=con.prepareStatement(sql3);
			 PreparedStatement pstmt5=con.prepareStatement(sql4);
			 outer:while(true) {
				 System.out.println("Welcome to patient database operations\nChoose one operation:");
				 System.out.println("1.Add patient data\n2.View Patient data\n3.Retrive patient data\n4.Update patient data\n5.Delete patient data\n6.Exit");
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
				        pstmt1.setString(1,id);
				        pstmt1.setString(2,name);
				        pstmt1.setInt(3, Age);
				        pstmt1.setLong(4, num);
				        int rowCount=pstmt1.executeUpdate();
				         if(rowCount>0) {
				        	 System.out.println("Data entered successfully !");
				        }
				         else {
				        	  throw new RuntimeException("Check once ! Data not entered.");
				         }
					    break;
				 case 2:System.out.println("Patient data");
				        ResultSet rs=pstmt2.executeQuery();
				        while(rs.next()) {
				        	System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getLong(4));
				        }
					    break;
				 case 3:System.out.println("Retriveing patient data");
				        System.out.println("Enter id :");
				        String id1=sc.nextLine();
				        pstmt3.setString(1, id1);
				        ResultSet rs3=pstmt3.executeQuery();
				        if(rs3.next()) {
				        	System.out.println(rs3.getString(1)+" "+rs3.getString(2)+" "+rs3.getInt(3)+" "+rs3.getLong(4));
				        }else {
				        	System.out.println("ID is invalid, please enter valid ID");
				        }
				        break;  
				 case 4:System.out.println("Updating patient data");
				        System.out.println("Enter id :");
			            String id2=sc.nextLine();
			            System.out.println("Enter pcontact :");
				        Long num3=Long.parseLong(sc.nextLine());
				        pstmt4.setLong(1,num3);
				        pstmt4.setString(2,id2);
				        int rowcount2=pstmt4.executeUpdate();
				        if(rowcount2>0) {		        	
				        	System.out.println("Data updated");
				        }
				        else {
				        System.out.println("ID is invalid, please enter valid ID");
				        }
				        break;
				 case 5:System.out.println("Deleting patient data");
				        System.out.println("Enter id :");
		                String id3=sc.nextLine();
		                pstmt5.setString(1,id3);
		                int rowcount3=pstmt5.executeUpdate();
		                if(rowcount3>0) {
		                	System.out.println("Data Deleted");
		                }
		                else {
		                	System.out.println("ID is invalid, please enter valid ID");
		                }
					    break;
				 case 6:System.out.println("Thank you!");
				        con.close();
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
		new Jdbc_App6().Operations();
	}
}
 
