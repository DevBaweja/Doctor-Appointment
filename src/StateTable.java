import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

class StateTable {

	public StateTable() {
	
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("create database if not exists state");
			stmt.execute("use state");
			// Tables exists from cities.sql
			ResultSet rscount = stmt.executeQuery("select count(distinct city_state) as count from cities");
			rscount.next();
			String original[] = new String[rscount.getInt("count")];
			
			ResultSet rs = stmt.executeQuery("select distinct city_state from cities");
			int i=0;
			while(rs.next())
			{
				original[i] = rs.getString("city_state");
				i++;
			}
			
			stmt.execute("use states");
			stmt.execute("create table if not exists _statestb(state_name varchar(100) primary key)");
			for (int j = 0; j < original.length; j++) 
			{
				PreparedStatement pre = con.prepareStatement("insert into _statestb values(?)");
				pre.setString(1, original[j]);
				System.out.println(original[j]);
				pre.executeUpdate();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}