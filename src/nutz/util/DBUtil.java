package nutz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtil {

	private static String driverClassName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/nutz";
	private static String user = "root";
	private static String password = "mysqladmin";
	
	public static Connection makeConnection() {
		Connection conn = null;
		
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static DataSource makeDataSource() {  
		BasicDataSource dataSource = new BasicDataSource();  
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);  
		dataSource.setUsername(user);  
		dataSource.setPassword(password); 
		
		return dataSource;  
	}
	
}
