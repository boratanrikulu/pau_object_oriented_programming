/**
 * @author boratanrikulu
 * If you have any question about the project, you can contact me at http://boratanrikulu.me/contact
 */
package flighttickets.DataBaseProcesses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnecter {
	
	public Connection connection = null;

	public DataBaseConnecter() {
		String url = "jdbc:mysql://" + DataBaseInfo.host + ":" + DataBaseInfo.port + "/" + DataBaseInfo.name + "?useUnicode=true&characterEncoding=utf8";
		
		try { // loads driver
			 Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver is not found.");
		}
		
		try { // makes connection
			connection = DriverManager.getConnection(url, DataBaseInfo.username, DataBaseInfo.password);
		} catch (SQLException ex) {
			System.out.println("Connection is failed.");
		}
	}

	public Connection getConnection() {
		return this.connection;
	}
}
