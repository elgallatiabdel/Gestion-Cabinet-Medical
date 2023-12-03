package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
  private Connection con;
	private static Connect instance;

	private Connect() {
		try {
				con = DriverManager.getConnection("jdbc:mysql://localhost/GCM", "root", "");
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}

	public static Connect getCon() {
		if (instance == null) {
				instance = new Connect();
		}
		return instance;
	}

	public Connection getConnection() {
		return con ;
	}
}