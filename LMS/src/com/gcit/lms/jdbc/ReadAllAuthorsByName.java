package com.gcit.lms.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ReadAllAuthorsByName {
	
	public static String  driver= "com.mysql.cj.jdbc.Driver";
	public static String  url= "jdbc:mysql://localhost/library?useSSL=false";
	public static String  userName= "root";
	public static String  password= "root";

	public static void main(String[] args) {
		String query = "";
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Authors Name to get his Info");
		String authorName=scan.nextLine();
		
		try {
			//1> Register Drviers
			Class.forName(driver);
			
			//2> Create Connection
			Connection conn = DriverManager.getConnection(url, userName, password);
			
			//3> Create Statement Object
			Statement stmt = conn.createStatement();
			
			query = "select * from tbl_author where authorName like '%"+authorName+"%'";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				System.out.println("Author ID: "+rs.getInt("authorId"));
				System.out.println("Author Name: "+rs.getString("authorName"));
				System.out.println("-----------------------------------------");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
