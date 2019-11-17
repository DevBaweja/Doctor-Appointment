
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

class DoctorTabbedBar extends JDialog implements ActionListener{

	// This is JFrame called in DoctorLoginHome // see logout also dispose
                private static JTabbedPane Jtp;
                private int c;
                private JPanel logout;
                private JButton btlogout;
                static String userd;
                
                public DoctorTabbedBar()
                {
                 	userd = DoctorLoginHome.userd; // taking username from previous login form
                	
                	
                	setVisible(true);
                	setSize(new Dimension(3000, 1000));
                setLayout(new BorderLayout());
                Jtp = new JTabbedPane();
                
                userlogout();
                Jtp.addTab("Home", new DoctorWelcome());
                if(checkschedule())
                	{
		                 Jtp.addTab("MyAppointment", new DoctorMyAppointment());
                	//Jtp.addTab("MyAppointment", new TodayDate());
		                Jtp.addTab("About Me",new DoctorInfo());
		                Jtp.addTab("Manage Password", new Doctor_ChangePassword());
		                Jtp.addTab("Manage Schedule", new Doctor_Manageschedule());
		                Jtp.addTab("Log Out", logout);
		                Jtp.addTab("Contact Us", new ContactUs());
		                
		                Jtp.setBackgroundAt(3, Color.red);
		                Jtp.setBackgroundAt(4, Color.red);
		                Jtp.setBackgroundAt(5, Color.red);
                	}
                else
                	
                	{
	                	Jtp.addTab("Set Schedule", new Doctor_Setschedule());
	                	Jtp.addTab("Log Out", logout);
	                	Jtp.addTab("Contact Us", new ContactUs());
	                	
	                	Jtp.setBackgroundAt(1, Color.red);
	                	Jtp.setBackgroundAt(2,Color.red);
                	}

                
                
                Jtp.setBackground(Color.black);
                Jtp.setForeground(Color.white);
                
              //  Jtp.setBackgroundAt(3, Color.red);
                add(Jtp);
              
                }
                
           private boolean checkschedule()
             {
            	 try {
     				
     				
     				Class.forName("com.mysql.jdbc.Driver");
     				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
     				Statement stmt = con.createStatement();
     				stmt.executeUpdate("create database if not exists ManagementDb");
     				stmt.execute("Use ManagementDb");
     				stmt.executeUpdate("create table if not exists  DoctorscheduleTb(username varchar(100),days  varchar(100),morningtimestart varchar(100),morningtimeend varchar(100),eveningtimestart varchar(100),eveningtimeend varchar(100),primary key(username))");
     				
     				
     				PreparedStatement pstmt = con.prepareStatement("select count(*) from DoctorscheduleTb where username=?");
     				pstmt.setString(1, userd);
     				ResultSet rs = pstmt.executeQuery();
     				
     				rs.next();
     				c = rs.getInt(1);
     						
     			} catch (ClassNotFoundException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			} catch (SQLException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     			}
  				 
  				if(c!=0) // it will c=1 for home and c=0 for setschedule
  					return true; // home
  				else
  					return false;  // setschedule 
  				// it will open home in case exceptional c > 1 which is not possible as it will be executed once
             }
           
           private void userlogout()
           {
	           	logout = new JPanel();
	           	btlogout = new JButton("Log out");
	           	logout.add(btlogout);
	           	btlogout.addActionListener(this);
           }
           @Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object src = e.getSource();
				if(src == btlogout)
				{
			int ans = JOptionPane.showConfirmDialog(null, "Do you really want to logout ,Doctor "+userd, "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(ans == JOptionPane.YES_OPTION)
								this.dispose(); //when it is frame
				userd=""; // it will remove that patientuser
				}
			}
}


