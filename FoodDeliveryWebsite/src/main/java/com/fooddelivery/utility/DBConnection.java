package com.fooddelivery.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null) return connection;

        String url = "jdbc:postgresql://dpg-d5dm1o95pdvs73dd0gl0-a.oregon-postgres.render.com:5432/online_food_delivery_amrl";
        String username = "online_food_delivery_amrl_user";
        String password = "A7cbFeNW9evxa36O09SZo3cAdjpfmxqa";

        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL Driver Registered!");

            // Try to connect
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to DB successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error!");
            e.printStackTrace();
        }

        if (connection == null) {
            System.err.println("Failed to make connection!");
        }

        return connection;
    }
}


/*package com.fooddelivery.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL="jdbc:mysql://localhost:3306/OnlineFoodDelivery";
	private static  final String USERNAME="root";
	private static final String PASSWORD="root";
	
	public final static Connection getConnection(){
		Connection connection =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			 connection =DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return connection;
	}
	public static void main(String[] args) {
		

	}

}*/

