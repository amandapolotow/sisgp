package sisgp.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/sisgp";
		String user = "root";
		String password = "";
		
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url,user,password);
		
		return conn;
		
	}

}
