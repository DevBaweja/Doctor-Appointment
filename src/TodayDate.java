
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;

public class TodayDate extends JPanel implements ActionListener {


	String userd;
	int c= 0;
	JPanel P;
	JTable tb;
	JScrollPane jsp;
	JPanel Pbtn;
	JButton bttoday,btall;
	boolean condition ;
	
	public TodayDate() {
		
		userd = DoctorTabbedBar.userd;
		condition = false;
		// convert them into label
		String need = "*";
		P = new JPanel();
	

		
		Pbtn = new JPanel();
		bttoday = new JButton("Today");
		btall = new JButton("All");
		Pbtn.add(bttoday);
		Pbtn.add(btall);
		
		P.add(Pbtn,BorderLayout.NORTH);
		
		
		bttoday.addActionListener(this);
		btall.addActionListener(this);
		
		Object []col = { "Patient Name","Appointment Date","Appointment Day","Slot","Timimg","Previous Visits","Rating" };
		try {
			Class.forName("com.mysql.jdbc.Driver"); // imported external jar mysql JConnector
				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				
				// server and default mysql port address , username , password
				
				Statement stmt = con.createStatement();
				
				stmt.executeUpdate("create database if not exists ManagementDb");
					stmt.execute("Use ManagementDb");
					
					stmt.executeUpdate("create table if not exists  dr"+userd+"Tb( patient varchar(100),"
							+ "dob date ,"
							+ "day varchar(100),"
							+ "slot varchar(100),"
							+ "timestart varchar(100),"
							+ "timeend varchar(100),"
							+ "frequency int,"
							+ "rating varchar(100),"
							+ "primary key(patient))");
					
					
					// finding if he has already booked an appointment before
					 PreparedStatement prstmt;
				      if(condition)
				     
				    	 prstmt = con.prepareStatement("select count(*) from dr"+userd+"Tb");
						else 
						{
							LocalDate localDate = LocalDate.now();
						      String today = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);
						      prstmt = con.prepareStatement("select count(*) from dr"+userd+"Tb where dob = ? ");
							prstmt.setString(1, today);
						}
			    	  ResultSet rs = prstmt.executeQuery(); 
							rs.next();	
							c = rs.getInt(1);
					

				// it will give all the patient and one patient is only once no use od distint
				//System.out.println("Number of records: "+ c);
		} 
		
		catch (SQLException e) {
				e.printStackTrace();
			}
				
		 catch (ClassNotFoundException e) {
			e.printStackTrace();
		 	}
		
		Object [][]row = new Object[c][7];  
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); // imported external jar mysql JConnector


				
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				
				// server and default mysql port address , username , password
				
				Statement stmt = con.createStatement();
				
				stmt.executeUpdate("create database if not exists ManagementDb");
					stmt.execute("Use ManagementDb");
					
					stmt.executeUpdate("create table if not exists  dr"+userd+"Tb( patient varchar(100),"
							+ "dob date ,"
							+ "day varchar(100),"
							+ "slot varchar(100),"
							+ "timestart varchar(100),"
							+ "timeend varchar(100),"
							+ "frequency int,"
							+ "rating varchar(100),"
							+ "primary key(patient))");
					

					// finding if he has already booked an appointment before
					 PreparedStatement prstmt;
				      if(condition)
				      {
				    	  prstmt = con.prepareStatement("select * from dr"+userd+"Tb");

				      }
						else 
						{
							LocalDate localDate = LocalDate.now();
						      String today = DateTimeFormatter.ofPattern("yyy-MM-dd").format(localDate);
							prstmt = con.prepareStatement("select * from dr"+userd+"Tb where dob = ? ");
							prstmt.setString(1, today);
	
						}
			    	  ResultSet rs = prstmt.executeQuery(); 
						rs.next();	
						c = rs.getInt(1);

			      
				for(int i=0;i<c && rs.next();i++)
				{
					row [i][0] = rs.getString("patient");
					row[i][1] = rs.getDate("dob");
					row[i][2] = rs.getString("day");
					row[i][3] = rs.getString("slot");
					
					row[i][4] = rs.getString("timestart")+" - "+rs.getString("timestart");
					
					row[i][5] = rs.getInt("frequency");
					row[i][6] = rs.getString("rating");
				
				}
				
				con.close();

		} 
		
		catch (SQLException e) {
				e.printStackTrace();
			}
				
		 catch (ClassNotFoundException e) {
			e.printStackTrace();
		 	}
		
		
		
		tb = new JTable(row,col);
		jsp = new JScrollPane(tb);
		P.setLayout(new BorderLayout());
		P.add(jsp,BorderLayout.CENTER);
		setLayout(new BorderLayout());
		add(P,BorderLayout.CENTER);

		style();
		validate();
	}
	public void style()
	{
	     Font f  = new Font("comic sans",Font.ITALIC+Font.BOLD , 40); 
	        Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
	        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	        Border h = BorderFactory.createTitledBorder(loweredbevel,":: PATIENTS ::", TitledBorder.CENTER , TitledBorder.TOP ,f,Color.red);
	        Border k = BorderFactory.createMatteBorder(0,10,0,0,Color.red);
	        P.setBorder(BorderFactory.createCompoundBorder(h, k));
	        
	        Color c1= new Color(20,110,140);
        	Font f1  = new Font(null,Font.BOLD ,20);
        	JTableHeader header = tb.getTableHeader();
        	header.setBackground(c1);
        	header.setFont(f1);
        	header.setForeground(Color.white);
        	 header.setPreferredSize( new Dimension(getSize().width,100));
        	((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        	
        	tb.setForeground(c1);
        	Font f2  = new Font("comic sans", Font.BOLD ,12);
        	tb.setFont(f2);
        	//JTable.CENTER_ALIGNMENT
        	tb.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        	
        	
        	TableColumn column = null;
            for (int i = 0; i < 7; i++) {
                column = tb.getColumnModel().getColumn(i);
                if (i == 1 || i==5 ) {
                    column.setPreferredWidth(100);
                } else {
                    column.setPreferredWidth(50);
                }
                
            TableRow rows = null;
            for (int r = 0; r< c; r++)
            {
            	tb.setRowHeight(r,50);
            }
            tb.setEnabled(false);
            }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		
		if(src == btall)
		{
			condition = false;
		}
		else if(src == bttoday)
		{
			
			condition = true;
		}
	}
}


