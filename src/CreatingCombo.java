import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

public class CreatingCombo {

    public static void fillcbstate(JComboBox<String> cbstate)
    {
                    try {
                                            
                                           
                                            Class.forName("com.mysql.jdbc.Driver");
                                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
                                            Statement stmt = con.createStatement();
                                            stmt.executeUpdate("create database if not exists states");
                                            stmt.execute("Use states");
                                            // table exist already
                                            
                                            ResultSet rs = stmt.executeQuery("select state_name from _statestb"); 
                                            
                                            while(rs.next())
                                            {
                                                          cbstate.addItem(""+rs.getString("state_name"));
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
    
    public static void fillSpecilization(JComboBox<String> chspecilization)
    {
  	   try {
  		
 			
 			Class.forName("com.mysql.jdbc.Driver");
 			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
 			Statement stmt = con.createStatement();
 			stmt.executeUpdate("create database if not exists ManagementDb");
 			stmt.execute("Use ManagementDb");
 			stmt.executeUpdate("create table if not exists  DoctorspecTb(specilisation varchar(150))");

			ResultSet rs = stmt.executeQuery("select distinct specilisation from DoctorspecTb"); 
			
			while(rs.next())
			{
				chspecilization.addItem(""+rs.getString("specilisation"));
			}
        	 
 					con.close();
 				} catch (ClassNotFoundException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				} catch (SQLException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();

 				}  
    }
    
    public static void fillQualification(JComboBox<String> chqualification)
    {  
 	   try {
 		
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("create database if not exists ManagementDb");
			stmt.execute("Use ManagementDb");
			stmt.executeUpdate("create table if not exists  DoctorqualTb(qualification varchar(150))");

			ResultSet rs = stmt.executeQuery("select distinct qualification from DoctorqualTb"); 
			
			while(rs.next())
			{
				chqualification.addItem(""+rs.getString("qualification"));
			}
       	 
					con.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
    }
}
