package fr.mrfern.pumpmycord.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnector {

	private Connection connector;
	
	private static String url;
	private static int port;
	private static String user,mdp;
	
	public MySQLConnector() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mariadb://"+ url  + ":" + port + "/DB?user=" + user + "&password=" + mdp);
			connector = connection;
		} catch (SQLException e) {
			System.out.println("MySQL execption message : " + e.getMessage());
			System.out.println("MySQL execption error code : " + e.getErrorCode());
			System.out.println("MySQL execption SQLState : " + e.getSQLState());
		}
	}
	
	public void sendUpdate(String command) {
		try {
			Statement statment = connector.createStatement();
			statment.executeUpdate(command);
			
			statment.close();
			connector.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet sendQueryAndGetResult(String command) {
		try {
			Statement statment = connector.createStatement();
			statment.executeUpdate(command);
			
			statment.close();
			connector.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;		
	}

	public Connection getConnector() {
		return connector;
	}

	public void setConnector(Connection connector) {
		this.connector = connector;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		MySQLConnector.url = url;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		MySQLConnector.port = port;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		MySQLConnector.user = user;
	}

	public static String getMdp() {
		return mdp;
	}

	public static void setMdp(String mdp) {
		MySQLConnector.mdp = mdp;
	}
	
}
