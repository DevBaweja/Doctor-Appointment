import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CitiesAndState extends JApplet implements ItemListener{

	private JComboBox cbstate;
	private JComboBox cbcities;
	private JTextField tx;
	
	public void init()
	{
		setLayout(new FlowLayout());
		setSize(1000,800);
		setSize(new Dimension(1000, 800));
		
		tx = new JTextField();
		tx.setColumns(10);
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
    	   cbcities.addItemListener(this);
    	   add(tx);
    	   tx.setEnabled(false);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src == cbstate) // Fill cities
		{
			cbcities.removeItemListener(this);
			cbcities.removeAllItems();
			cbcities.addItem("Select City");	
			fillCities();
			cbcities.addItemListener(this);
		}
		else if(src == cbcities)
		{
			fillIndex();
		}
	}

	private void fillIndex() {
		// TODO Auto-generated method stub
		String c = cbcities.getSelectedItem().toString();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("create database if not exists state");
			stmt.execute("Use state");
			// table exist already
			
			PreparedStatement pstmt = con.prepareStatement("select city_id from cities where city_name=?");
			pstmt.setString(1, c);
			ResultSet rs = pstmt.executeQuery(); 
			
			while(rs.next())
			{
				tx.setText(rs.getString("city_id"));
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

	private void fillCities() {
		// TODO Auto-generated method stub
			String s = cbstate.getSelectedItem().toString();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
					Statement stmt = con.createStatement();
					stmt.executeUpdate("create database if not exists state");
					stmt.execute("Use state");
					// table exist already
					
					PreparedStatement pstmt = con.prepareStatement("select city_name from cities where city_state=?");
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