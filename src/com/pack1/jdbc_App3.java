package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_App3 {

	public String driver = "com.mysql.cj.jdbc.Driver";
	public String dburl = "jdbc:mysql://localhost:3306/bankapp";
	public String username = "root";
	public String password = "your_password";

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

	public void emp_data(String name) {

		try {
			Connection con = connect();
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from employee");

			boolean found = false;

			while (rs.next()) {

				if (name.equalsIgnoreCase(rs.getString(3))) {

					System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));

					found = true;
					break;
				}
			}

			if (!found) {
				System.out.println("Employee " + name + " record  is not available");
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		System.out.println("Database Connected");
		System.out.println("Ready with employee table");
		System.out.println("Enter employee name:");

		String name = scn.nextLine();

		new Jdbc_App3().emp_data(name);

		scn.close();
	}
}
