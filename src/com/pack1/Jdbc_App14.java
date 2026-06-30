package com.pack1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
public class Jdbc_App14 {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String dburl = "jdbc:mysql://localhost:3306/bankapp";
	private static final String username = "root";
	private static final String password = "Qwer1357!";
	private String query1="select * from employee";
	
	
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
	
	
	void get_Database_Data() {
		Connection con=connect();
		try {
			DatabaseMetaData dbmd=con.getMetaData();
			 System.out.println("Database Name : " + dbmd.getDatabaseProductName());
		        System.out.println("Database Version : " + dbmd.getDatabaseProductVersion());
		        System.out.println("Driver Name : " + dbmd.getDriverName());
		        System.out.println("Driver Version : " + dbmd.getDriverVersion());
		        System.out.println("User Name : " + dbmd.getUserName());
                System.out.println();
		        con.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	void get_ResultSet_Data() {
		Connection con=connect();
		try {
			PreparedStatement pstmt=con.prepareStatement(query1);
			ResultSet rs=pstmt.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			System.out.println();
			 System.out.println("Number of Columns : " + rsmd.getColumnCount());
		        for(int i=1;i<=rsmd.getColumnCount();i++) {
		            System.out.println("Column Name : " + rsmd.getColumnName(i));
		            System.out.println("Data Type : " + rsmd.getColumnTypeName(i));
		            System.out.println("Column Size : " + rsmd.getColumnDisplaySize(i));
		            System.out.println();
		        }
		        con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void get_Parameter_Data() {
		Connection con=connect();
		try {
			System.out.println();
			PreparedStatement ps =
					con.prepareStatement("select * from employee where EID=? and EFNAME=?");
					ParameterMetaData pmd = ps.getParameterMetaData();
					 System.out.println("Total Parameters : " + pmd.getParameterCount());
					 con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Jdbc_App14 obj=new Jdbc_App14();
		obj.get_Database_Data();
		obj.get_ResultSet_Data();
		obj.get_Parameter_Data();
	}
}
