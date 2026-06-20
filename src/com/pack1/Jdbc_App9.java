package com.pack1;

import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.CachedRowSet;

public class Jdbc_App9 {
	private static final String dburl = "jdbc:mysql://localhost:3306/bankapp";
	private static final String username = "root";
	private static final String password = "Qwer1357!";
	private static final String sql="select * from employee";
	private static final String sqlQuery2="select eid,efname,esal from employee";
	static Scanner sc = new Scanner(System.in);
	
	void meth1(){
		System.out.println("Choose an option");
		System.out.println("1.JdbcRowSet");
		System.out.println("2.CachedRowSet");
		Integer choice=Integer.parseInt(sc.nextLine());
		try {
			RowSetFactory rsf=RowSetProvider.newFactory();
			switch(choice) {
			case 1: 
				JdbcRowSet jrs=rsf.createJdbcRowSet();
				jrs.setUrl(dburl);
				jrs.setUsername(username);
				jrs.setPassword(password);
				jrs.setCommand(sql);
				jrs.execute();
				while(jrs.next()) {
					System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getInt(4));
				}
				System.out.println("---------------------");
				jrs.afterLast();
				while(jrs.previous()) {
					System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getInt(4));
				}
				System.out.println("---------------------");

				jrs.first();
				jrs.absolute(3);
				System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getInt(4));
				System.out.println("---------------------");
				
				jrs.last();
				System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getInt(4));
				System.out.println("---------------------");
				
				jrs.first();
				jrs.relative(2);
				System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getInt(4));
				System.out.println("---------------------");
				break;
			case 2:
				 CachedRowSet crs=rsf.createCachedRowSet();
				 crs.setUrl(dburl);
					crs.setUsername(username);
					crs.setPassword(password);
					crs.setCommand(sqlQuery2);
					crs.execute();
					while(crs.next()) {
						String eid=crs.getString(1);
						if(eid.equals("103")) {
							crs.updateInt("esal", 32000);
							crs.updateRow();
						}
					}
					crs.acceptChanges();
					crs.absolute(4);
					System.out.println(crs.getString(1)+" "+crs.getString(2)+" "+crs.getInt(3)); 
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_App9().meth1();
	}
}
