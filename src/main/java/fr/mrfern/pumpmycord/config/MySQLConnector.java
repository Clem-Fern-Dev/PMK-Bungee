package fr.mrfern.pumpmycord.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnector {

	private Connection connector;
	private boolean isOK;
	
	public MySQLConnector(String url, int port, String user, String mdp) {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword");
			connector = connection;
			isOK = true;
		} catch (SQLException e) {
			System.out.println("MySQL execption message : " + e.getMessage());
			System.out.println("MySQL execption error code : " + e.getErrorCode());
			System.out.println("MySQL execption SQLState : " + e.getSQLState());
		}
	}
	
	public void sendQuery(String command) {
		
	}
	
	public ResultSet sendQueryAndGetResult(String command) {
		
		
		
		return null;		
	}

	public Connection getConnector() {
		return connector;
	}

	public void setConnector(Connection connector) {
		this.connector = connector;
	}

	public boolean isIsOK() {
		return isOK;
	}

	public void setIsOK(boolean IsOK) {
		isOK = IsOK;
	}
	
}
