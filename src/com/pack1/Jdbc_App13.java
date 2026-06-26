package com.pack1;

import java.sql.Connection;

public class Jdbc_App13 {
	private  String driver = "com.mysql.cj.jdbc.Driver";
	private  String dburl = "jdbc:mysql://localhost:3306/bankapp";
	private  String username = "root";
	private  String password = "Qwer1357!";
	
	ConnectionPool cp=new ConnectionPool(driver,dburl,username,password);

	void meth1() {
		cp.con_Intitialization();
		System.out.println("\n------user1-------");
		Connection con1=cp.con_Acquisition();
		System.out.println("After user 1:"+cp.v.size());
		
		System.out.println("\n------user2-------");
		Connection con2=cp.con_Acquisition();
		System.out.println("After user 2:"+cp.v.size());
		
		System.out.println("\n------user3-------");
		Connection con3=cp.con_Acquisition();
		System.out.println("After user 3:"+cp.v.size());
		
		cp.con_Return(con1);
		cp.con_Return(con2);
		cp.con_Return(con3);
		
	}
	public static void main(String[] args) {
		Jdbc_App13 obj=new Jdbc_App13();
		obj.meth1();
	}
}
