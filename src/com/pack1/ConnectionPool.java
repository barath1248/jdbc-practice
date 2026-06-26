package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class ConnectionPool {
	private  String driver ;
	private  String dburl ;
	private  String username;
	private  String password;
	Vector<Connection> v=new Vector<Connection>();
	public  ConnectionPool(String driver ,String dburl , String username , String password) {
		this.driver=driver;
		this.dburl=dburl;
		this.username=username;
		this.password=password;
	}
	
	void con_Intitialization(){
		System.out.println("Creating 5 connections");
		System.out.println("Before conncection:"+v.size());
		Connection con=null;
		while(v.size()<5) {
			try {
				Class.forName(driver);
				 con=DriverManager.getConnection(dburl,username,password);
				 v.add(con);
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		for(Object data: v) {
			System.out.println(data);
		}
		System.out.println(v.size()+" Objects created");
	}
	
	Connection con_Acquisition() {
		System.out.println("Assigning a connection");
		Connection con=v.get(0);
		v.remove(0);
		return con;
	}
	
	void con_Return(Connection con) {
		System.out.println("\n Returning the connection");
		v.addElement(con);
		for(Object data: v) {
			System.out.println(data);
		}
	}
}
