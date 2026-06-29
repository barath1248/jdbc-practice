package com.pack1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Jdbc_App15 {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String dburl = "jdbc:mysql://localhost:3306/bankapp";
	private static final String username = "root";
	private static final String password = "Qwer1357!";
	private String sqlQuery1="insert into mydata values(?,?)";
	private String sqlQuery2="select PIC_DATA from mydata where ID=?";
	private String sqlQuery3="insert into mydata2 values(?,?)";
	private String sqlQuery4="select FILE_DATA from mydata2 where ID=?";


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
	
	void StorePicData() {
		Connection con=connect();
		try {
			PreparedStatement pstmt1=con.prepareStatement(sqlQuery1);
			FileInputStream fis=new FileInputStream("C:/Users/BHARATH/OneDrive/Pictures/Wallpaper.jpg");
			pstmt1.setString(1,"101");
			pstmt1.setBlob(2, fis,fis.available());
			int rowcount=pstmt1.executeUpdate();
			if(rowcount<0) {
				throw new RuntimeException("Error occured");
			}
			System.out.println("Image inserted");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void RetrievePicData() {
		Connection con=connect();
		try {
			PreparedStatement pstmt2=con.prepareStatement(sqlQuery2);
			pstmt2.setString(1, "101");
			ResultSet rs=pstmt2.executeQuery();
			if(rs.next()) {
				Blob b=rs.getBlob(1);
				byte[] arr=b.getBytes(1,(int) b.length());
				FileOutputStream fos=new FileOutputStream("D:/Downloads/Wallpaper1.png");
				fos.write(arr);
				fos.close();
			}
			System.out.println("Data retrieved");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void StoreFileData() {
		Connection con=connect();
		try {
			PreparedStatement pstmt3=con.prepareStatement(sqlQuery3);
			pstmt3.setString(1,"101");
			FileReader fr = new FileReader("D:/Advance Java.txt");
			pstmt3.setCharacterStream(2, fr);
			int rowcount=pstmt3.executeUpdate();
			if(rowcount<0) {
				throw new RuntimeException("Error occured");
			}
			System.out.println("File inserted");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	void RetrieveFileData(){
		Connection con=connect();
		try {
			PreparedStatement pstmt4=con.prepareStatement(sqlQuery4);
			pstmt4.setString(1,"101");
			ResultSet rs2=pstmt4.executeQuery();
			if(rs2.next()) {
				Clob c=rs2.getClob(1);
				Reader data=c.getCharacterStream();
			    BufferedReader br = new BufferedReader(data);
				FileWriter fw=new FileWriter("D:/output.txt");
				String line;
				 while ((line = br.readLine()) != null) {
		                fw.write(line+" ");
		            }
				 fw.close();
		            br.close();

		            System.out.println("File retrieved successfully.");
		        } else {
		            System.out.println("Record not found.");
		        }
			 rs2.close();
		        pstmt4.close();
		        con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Jdbc_App15 obj=new Jdbc_App15();
		obj.StorePicData();
		//obj.RetrievePicData();
		//obj.StoreFileData();
		//obj.RetrieveFileData();
	}
}

//Database schema
//mydata table -> ID          VARCHAR(10)
//                PIC_DATA    BLOB

//mydata2 table-> ID          VARCHAR(10)
//                FILE_DATA   LONGCLOB
