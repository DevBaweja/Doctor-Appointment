import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StateTable {

	public static void main(String[] args) {
	System.out.println();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Connection icon = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate("create database if not exists state");
			stmt.execute("use state");
			// Tables exists from cities.sql
			ResultSet rs = stmt.executeQuery("select distinct city_state from cities");

			Statement istmt = icon.createStatement();
			istmt.execute("use states");
			istmt.executeUpdate("create table if not exists _statestb(state_name varchar(100) primary key)");
			
			while(rs.next())
			{
				PreparedStatement ipre = icon.prepareStatement("insert into _statestb values(?)");
				ipre.setString(1, rs.getString("city_state"));
				System.out.println(rs.getString("city_state"));
				ipre.executeUpdate();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}