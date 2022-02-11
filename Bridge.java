
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Bridge {
	private String serverLocation = "jdbc:sqlserver://10.0.0.42";
	private String username = "sa";
	private String password = "Scoast1362$";
	private String database = "PlannerDatabase";

	public Bridge(String serverLocation, String username, String password, String database) {
		this.serverLocation = serverLocation;
		this.username = username;
		this.password = password;
		this.database = database;
	}

	public Bridge() {
		
	}

	// Does insert statements
	public void SQLstatementInsert(String input) {
		Properties p = new Properties();
		p.setProperty("database", database);
		p.setProperty("user", username);
		p.setProperty("password", password);
		try (Connection c = DriverManager.getConnection(serverLocation, p); Statement s = c.createStatement();) {
			s.execute(input);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Fail1");
		}
	}

	// Does select statements
	public String SQLstatementSelect(String input, String a) throws SQLException {
		ResultSet temp = null;
		String value = "";
		Properties p = new Properties();
		p.setProperty("database", database);
		p.setProperty("user", username);
		p.setProperty("password", password);
		try (Connection c = DriverManager.getConnection(serverLocation, p); Statement s = c.createStatement();) {
			temp = s.executeQuery(input);
			while (temp.next()) {
				value = temp.getString(a);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Fail2");
		}
		return value;

	}

	// Does update and delete statements
	public void SQLstatementUpdate(String input) {
		Properties p = new Properties();
		p.setProperty("database", database);
		p.setProperty("user", username);
		p.setProperty("password", password);
		try (Connection c = DriverManager.getConnection(serverLocation, p); Statement s = c.createStatement();) {
			s.executeUpdate(input);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Fail3");
		}
	}
}