package com.pack1;
import java.sql.DriverManager;
import java.sql.Types;
//import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.CallableStatement;
public class Jdbc_APP10 {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String dburl = "jdbc:mysql://localhost:3306/bankapp";
	private static final String username="root";
	private static final String password="Qwer1357!";
	static Scanner sc=new Scanner(System.in);
	
	public static  Connection connect() {
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
			CallableStatement cstmt=con.prepareCall("{call INSERTEMPDATA(?,?,?,?,?)}");
			System.out.println("Enter Employee ID:");
			String eid=sc.nextLine();
			System.out.println("Enter Employee Name:");
			String ename=sc.nextLine();
			System.out.println("Enter Employee Desgination:");
			String edesg=sc.nextLine();
			System.out.println("Enter Employee Basic Salary:");
			int ebsal=Integer.parseInt(sc.nextLine());
			float etotsal=(float)(ebsal+(0.035*ebsal)+(0.15*ebsal));
			
			cstmt.setString(1, eid);
			cstmt.setString(2, ename);
			cstmt.setString(3, edesg);
			cstmt.setInt(4, ebsal);
			cstmt.setFloat(5, etotsal);
			cstmt.execute();
			System.out.println("Data Entered!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	void meth2() {
		Connection con=connect();
		try {
			CallableStatement cstmt=con.prepareCall("{call RetriveEmpData(?,?,?,?,?)}");
			System.out.println("Enter Employee ID:");
			String id=sc.nextLine();
			cstmt.setString(1,id);
			cstmt.registerOutParameter(2,Types.VARCHAR);
			cstmt.registerOutParameter(3,Types.VARCHAR);
			cstmt.registerOutParameter(4,Types.INTEGER);
			cstmt.registerOutParameter(5,Types.FLOAT);
			cstmt.execute();
			System.out.println("Employee Data:");
			System.out.println("Employee id :"+ id);
			System.out.println("Employee Name:"+ cstmt.getString(2));
			System.out.println("Employee Designation:"+ cstmt.getString(3));
			System.out.println("Employee Basic salary:"+ cstmt.getInt(4));
			System.out.println("Employee Total salary:"+ cstmt.getFloat(5));
		}
		catch(Exception e) {
		  
			//e.printStackTrace();
			System.out.println("Record is not found");
		}
	}
	void meth3() {
		Connection con=connect();
		try {
			CallableStatement cstmt=con.prepareCall("{? = call RetrieveTotalSal(?)}");
			System.out.println("Enter Employee ID:");
			String id=sc.nextLine();
			cstmt.setString(2, id);
			cstmt.registerOutParameter(1,Types.INTEGER);
			cstmt.execute();
			System.out.println("Employee id:"+ id);
			System.out.println("Employee Total Salary:"+cstmt.getInt(1));
		}
		catch(Exception e) {
			System.out.println("Record is not found");
		}
	}
	public static void main(String[] args) {
		Jdbc_APP10 obj=new Jdbc_APP10();
		//obj.meth1();
		//obj.meth2();
		obj.meth3();
	}
}
