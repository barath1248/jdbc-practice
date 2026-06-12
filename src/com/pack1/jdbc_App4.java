package com.pack1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class jdbc_App4 {
  private static final String driver="com.mysql.cj.jdbc.Driver";
  private static final String dburl="jdbc:mysql://localhost:3306/bankapp";
  private static final String username="root";
  private static final String password="Qwer1357!";
  private static String id;
  private static String Fname;
  private static String Lname;
  private static String sal;
  private static String loc;
  static Scanner sc=new Scanner(System.in);
  public static  Connection connect(){
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
  
  public void insert_employee() {
	  try {
		  Connection con=jdbc_App4.connect();
		  Statement stmt=con.createStatement();
	
		  System.out.println("Enter id:");
		   id=sc.nextLine();
		  System.out.println("Enter first name:");
		   Fname=sc.nextLine();
		  System.out.println("Enter last name:");
		   Lname=sc.nextLine();
		  System.out.println("Enter Salary :");
		   sal=sc.nextLine();
		  System.out.println("Enter Location:");
		   loc=sc.nextLine();
           String sqlQuery="insert into employee values("+id+",'"+Fname+"','"+Lname+"',"+sal+",'"+loc+"')";

		   System.out.println(sqlQuery);
		  int rowid=stmt.executeUpdate(sqlQuery);
		  
		  if(rowid>0) {
			  System.out.println("Data entered successfully !");
			  System.out.println("Do you want to see the data: Yes/No?");
			  char value=sc.nextLine().charAt(0);
			  switch(value) {
			  case 'y','Y': new jdbc_App2().get_empdata();
			                break;
			  case 'N','n': System.out.println("Thanks for inserting the data");
			  }
		  }
		  
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
  }
  public static void main(String[] args) {
	 new jdbc_App4().insert_employee();
}
  
}
