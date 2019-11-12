import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatesDatabase {

	// Make it main in case of creating states database
	public StatesDatabase() {
		// TODO Auto-generated constructor stub
    	   try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement istmt = con.createStatement();
				istmt.executeUpdate("create database if not exists state");
				istmt.execute("Use state");
				// table exist already
				ResultSet irs = istmt.executeQuery("select distinct city_state from cities");
				int c = 0;
				while(irs.next())
				{
					c++;
				}
				String originalstates[] = new String[c];
				String manipulatestates[]  = new String[c];
				
				irs = istmt.executeQuery("select distinct city_state from cities");
				int i=0;
				while(irs.next())
				{
					String state_nm = irs.getString("city_state");
					originalstates[i] = state_nm;
					state_nm = state_nm.replace(" ","_");
					state_nm = state_nm.replace("&", "and");
					manipulatestates[i] = state_nm;
					i++;
				}
				
				for (int j = 0; j < c; j++) 
					{
						System.out.println(originalstates[j]);
						System.out.println(manipulatestates[j]);
					}

				
				for (int j = 0; j < c; j++) 
				{
					PreparedStatement fpstmt = con.prepareStatement("select distinct city_name from cities where city_state=?");
					fpstmt.setString(1, originalstates[j]);
					
					ResultSet frs =  fpstmt.executeQuery(); // Cities of particular state
					// No Need of state database
					Statement fstmt = con.createStatement();
					fstmt.executeUpdate("create database if not exists states");
					fstmt.execute("Use states");
					fstmt.executeUpdate("create table if not exists "+manipulatestates[j]+"Tb(cities_names varchar(150) primary key)");
					while(frs.next()) // Every city of particular state
						{
							PreparedStatement finalpstmt = con.prepareStatement("insert into "+manipulatestates[j]+"Tb(cities_names) values(?)");
							finalpstmt.setString(1, frs.getString("city_name")); // Using previous result set
							finalpstmt.executeUpdate();
						}
					// Every iteration one city is inserted to that particular state table
					
					istmt.execute("Use state"); // Again reverting previous database
				}
								
				con.close(); 
						
			} catch (ClassNotFoundException ae) {
				// TODO Auto-generated catch block
				ae.printStackTrace();
			} catch (SQLException ae) {
				// TODO Auto-generated catch block
				ae.printStackTrace();
			}

	}
}