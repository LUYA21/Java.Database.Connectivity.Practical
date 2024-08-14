package javaDatabase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
	            // Connection URL for Microsoft Access database
	            String dbUrl = "jdbc:ucanaccess://Students.accdb";

	            // Establish a connection
	            Connection connection = DriverManager.getConnection(dbUrl);

	            // Perform database operations (CRUD) here
	            
	            try {
				    // Retrieve all data from the Students table
				    String selectSQL = "SELECT * FROM Students";
				    try (Statement statement = connection.createStatement();
				         ResultSet resultSet = statement.executeQuery(selectSQL)) {
				    	 // Print column headers
			            System.out.printf("%-10s %-20s %-20s %-15s %-10s%n", "StudentID", "First Name", "Last Name", "Date Of Birth", "Major");
			            System.out.println("=============================================");

			            // Print data rows
			            while (resultSet.next()) {
			                int studentId = resultSet.getInt("student_id");
			                String firstName = resultSet.getString("first_name");
			                String lastName = resultSet.getString("last_name");
			                String date = resultSet.getString("date_of_birth");
			                String major = resultSet.getString("major");
			                

			                // Format and print the data
			                System.out.printf("%-10s %-20s %-20s %-15s %-10s%n", studentId, firstName, lastName, date, major);
			            }

				            // Display retrieved data
				            
				        }
				    
				} catch (SQLException e) {
				    e.printStackTrace();
				}
	           
	            
	            try {
				    // Insert a new record
				    String insertSQL = "INSERT INTO Students (student_id, first_name, last_name, date_of_birth, major) VALUES (?, ?, ?, ?, ?)";
				    try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {
				    	
				        insertStatement.setInt(1, 123456789); // Your student ID
				        insertStatement.setString(2, "YourFirstName");
				        insertStatement.setString(3, "YourLastName");
						insertStatement.setString(4, "YourDOB" );
				        insertStatement.setString(5, "YourMajor");
			            insertStatement.executeUpdate();
				        System.out.println("Record inserted successfully.");
				    }
				} catch (SQLException e) {
				    e.printStackTrace();
				}
			 try {
				    // Update an existing record by student_id
				    String updateSQL = "UPDATE Students SET last_name = ? WHERE student_id = ?";
				    try (PreparedStatement updateStatement = connection.prepareStatement(updateSQL)) {
				        updateStatement.setString(1, "Ndlozi");
				        updateStatement.setInt(2, 202117299);
				        updateStatement.executeUpdate();
				        System.out.println("Record updated successfully.");
				    }
				} catch (SQLException e) {
				    e.printStackTrace();
				}
			 try {
				    // Delete a record by student_name
				    String deleteSQL = "DELETE FROM Students WHERE first_name = ?";
				    try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL)) {
				        deleteStatement.setString(1, "Nontobeko");
				        deleteStatement.executeUpdate();
				        System.out.println("Record deleted successfully.");
				    }
				} catch (SQLException e) {
				    e.printStackTrace();
				}
	            // Close the connection when done
	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 
		
	


		
		
		
		

		
		
	}

}
