package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConexion() {
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_instituto?serverTimezone=America/Lima","root","mysql");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
}
