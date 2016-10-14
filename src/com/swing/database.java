package com.swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextArea;


public class database {
	String[] columnNames = {"CustID","FName","MName","LName"};
	String[][] vals=new String[100][4];
	JTextArea data=new JTextArea();
	
	public database(){
		System.out.println("-------- Oracle JDBC Connection Testing ------");

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		System.out.println("Oracle JDBC Driver Registered!");
		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.19.226.14:1521:ora11gr2", "legacy",
					"legacy");
			Statement stmt=connection.createStatement();
			ResultSet rs= stmt.executeQuery("select * from customer");
			int i=0;
			while(rs.next())
			{
				String custid=rs.getString("cust_no");
				String firstname=rs.getString("first_name");
				String middlename=rs.getString("middle_name");
				String lastname=rs.getString("last_name");
				vals[i][0]=custid;
				vals[i][1]=firstname;
				vals[i][2]=middlename;
				vals[i++][3]=lastname;
				data.append("CUSTID:" + custid);
				data.append("\nFIRST NAME:" + firstname);
				data.append("\nMIDDLE NAME:" + middlename);
				data.append("\nLAST NAME:" + lastname);
				data.append("\n--------------------------------------\n");
			}

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			try{
				connection.close();
				if(connection.isClosed())
					System.out.println("connection closed!!!");
			}catch(SQLException ex)
			{
				System.out.println("Failed to close connection!");

			}
		}
	}
}

