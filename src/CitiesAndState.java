import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class CitiesAndState extends JApplet implements ItemListener{

	JComboBox cbstate,cbcities;
	
	public void init()
	{
		cbstate = new JComboBox();
		
		cbstate.addItem("Select State");
 	    cbcities = new JComboBox();
        cbcities.addItem("Select City");  
 	    
    	   try {
				
				setLayout(new FlowLayout());
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt = con.createStatement();
				stmt.executeUpdate("create database if not exists state");
				stmt.execute("Use state");
				// table exist already
				
				ResultSet rs = stmt.executeQuery("select distinct city_state from cities"); 
				
				while(rs.next())
				{
					cbstate.addItem(""+rs.getString("city_state"));
				}
				con.close(); 
						
			} catch (ClassNotFoundException ae) {
				// TODO Auto-generated catch block
				ae.printStackTrace();
			} catch (SQLException ae) {
				// TODO Auto-generated catch block
				ae.printStackTrace();
			}

    	   add(cbstate);
    	   add(cbcities);
    	   cbstate.addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
			cbcities.removeAllItems();
			cbcities.addItem("Select City");
		
			
		String s = cbstate.getSelectedItem().toString();
		
		   try {
				
				setLayout(new FlowLayout());
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt = con.createStatement();
				stmt.executeUpdate("create database if not exists state");
				stmt.execute("Use state");
				// table exist already
				
				PreparedStatement pstmt = con.prepareStatement("select distinct city_name from cities where city_state=?");
				pstmt.setString(1, s);
				ResultSet rs = pstmt.executeQuery(); 
				
				while(rs.next())
				{
					cbcities.addItem(""+rs.getString("city_name"));
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
