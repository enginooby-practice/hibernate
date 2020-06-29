package com.cpulover.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
		String user = "cpulover";
		String password = "cpulover";
		try {
			Connection myConn = DriverManager.getConnection(url, user, password);
			System.out.println("Successfully!");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
