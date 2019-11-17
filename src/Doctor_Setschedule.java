import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.DayOfWeek;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

class Doctor_Setschedule extends JPanel implements ItemListener,ActionListener{


	// This will not check whether it will present or not
	// it will be executed only once 
	
	private String userd;
	private JCheckBox[] ch;
	private JButton btsetschedule;
	private JPanel P;
	private JPanel Pmain;
	private String k="";
	private JComboBox cbms;
	private JComboBox cbme;
	private JComboBox cbes;
	private JComboBox cbee;
	private JLabel lbms;
	private JLabel lbme;
	private JLabel lbes;
	private JLabel lbee;
	private JLabel lbset;
	private JPanel pset;
	private JPanel pm;
	private JPanel pe;
	private JPanel pbtn;
	// ms morning start
	// me morning end
	// es evening start
	// ee evening end
	
	public Doctor_Setschedule()
	{
		userd = DoctorTabbedBar.userd;
		
		setVisible(true);
		setSize(700,700);
		setLayout(new BorderLayout());
		btsetschedule = new JButton("Set Schedule");
		btsetschedule.setEnabled(false);
		P = new JPanel();
		P.setLayout(new GridLayout(1,7));
		ch = new JCheckBox[7];
		String d[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		for(int i=0;i<ch.length;i++)
		{
			ch[i] = new JCheckBox(d[i]); 
			P.add(ch[i]);
			ch[i].addItemListener(this);
		}
		add(P,BorderLayout.NORTH);
		
		cbms = new JComboBox();
		cbme = new JComboBox();
		cbes = new JComboBox();
		cbee = new JComboBox();

		//cbms.addItem("Morning Start Time");
		//cbme.addItem("Morning End Time");
		//cbes.addItem("Evening Start Time");
		//cbee.addItem("Evening End Time");
		
		
		
		// morning hours from 6:00 AM to 1:00 PM 
		for(int i=6;i<=12;i++) 
		{	
			if(i<=9)
			{
				cbms.addItem("0"+i+":00 AM");
				if(i==9)
					cbme.addItem((i+1)+":00 AM");
				else
					cbme.addItem("0"+(i+1)+":00 AM");
			}
			else 
			{
				cbms.addItem(i+":00 AM");
				if(i==12)
					cbme.addItem("01:00 PM");
					else
					cbme.addItem((i+1)+":00 AM");
			}
		}
		cbme.setSelectedItem("01:00 PM");
			
		// morning end in item state changed
		
		// evening hours from 2:00 PM to 10:00 PM
		for(int i=2;i<=9;i++) 
		{
			cbes.addItem("0"+i+":00 PM");
			if(i==9)
				cbee.addItem((i+1)+":00 PM");
			else
			cbee.addItem("0"+(i+1)+":00 PM");
		}
		// evening end in item state changed
		cbee.setSelectedItem("10:00 PM");	
		cbms.addItemListener(this);
		cbes.addItemListener(this);
		
		lbms = new JLabel("Morning Start");
		lbme = new JLabel("Morning End");
		lbes = new JLabel("Evening Start");
		lbee = new JLabel("Evening End");
		
		lbset = new JLabel("Select Working Days");
		pset = new JPanel();
		pset.add(lbset);
		
		pm =  new JPanel();
		pm.setLayout(new GridLayout(1, 4));
		pm.add(lbms);
		pm.add(cbms);
		pm.add(lbme);
		pm.add(cbme);
		
		pe =  new JPanel();
		pe.setLayout(new GridLayout(1, 4));
		pe.add(lbes);
		pe.add(cbes);
		pe.add(lbee);
		pe.add(cbee);
		pbtn = new JPanel();
		pbtn.setLayout(new FlowLayout());
		pbtn.add(btsetschedule);
		
		Pmain = new JPanel(); 
		Pmain.setLayout(new GridLayout(5, 1,100,100));
		Pmain.add(pset);
		Pmain.add(P);
		Pmain.add(pm);
		Pmain.add(pe);
		Pmain.add(pbtn);
		add(Pmain,BorderLayout.CENTER);
		
	btsetschedule.addActionListener(this);
	style();
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		for(int j=0;j<ch.length;j++)
		{
			if(src == ch[j])
			{
				k="";
				
				for(int i=0;i<ch.length;i++)
				{
					if(ch[i].isSelected())
						k += "1";
					else
						k += "0";
				}
				btsetschedule.setEnabled(true);
			}
			
		}
		
		if(src == cbms) // morning start will fill morning end
		{
			cbme.removeAllItems();
			
			int m = cbms.getSelectedIndex();
			for(int j=m+1;j<cbms.getItemCount();j++)
				cbme.addItem(cbms.getItemAt(j));
			
			cbme.setEnabled(true);
				
				cbme.addItem("01:00 PM");
				cbme.setSelectedItem("01:00 PM");
		}
		else if(src == cbes) // evening start will fill evening end
		{
			cbee.removeAllItems();
		
			int m = cbes.getSelectedIndex();
			for(int j=m+1;j<cbes.getItemCount();j++)
				cbee.addItem(cbes.getItemAt(j));
			
			cbee.addItem("10:00 PM");
			cbee.setSelectedItem("10:00 PM");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
				if(src == btsetschedule)
				{
					
					if(!(k.equals("0000000"))) 
					{
						setdoctorinfotable();
						// if we dont set this button disable
						// if once pressed anything then k = 0000000 otherwise k = ""  !(k.equals("0000000") || k.equals(""))
						insertform();
						enable(false);
						/*this.remove(Pmain);
						this.remove(P);
						remove(this);*/
						//DoctorTabbedBar.Jtp.removeAll();
						//JOptionPane.showMessageDialog(null,);
					//JOptionPane.showMessageDialog(null,DoctorTabbedBar.class.getName());
					//	this.removeAll();
						this.getParent().remove(1);
						JOptionPane.showMessageDialog(null, "Invalid", "", JOptionPane.PLAIN_MESSAGE);
						validate();
						revalidate();
						
						//new DoctorTabbedBar();
						
						// logout button, doctortabbedbar.displose and again open 'refresh'
					}
					else 
						JOptionPane.showMessageDialog(null,"Please fill all the required information.","Form Incomplete", JOptionPane.ERROR_MESSAGE);
				}
	}
	
	private void insertform()
	{
		try {
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("create database if not exists ManagementDb");
			stmt.execute("Use ManagementDb");
			stmt.executeUpdate("create table if not exists  DoctorscheduleTb(username varchar(100),days  varchar(100),morningtimestart varchar(100),morningtimeend varchar(100),eveningtimestart varchar(100),eveningtimeend varchar(100),primary key(username))");
			
			
			PreparedStatement pstmt = con.prepareStatement("insert into DoctorscheduleTb values(?,?,?,?,?,?)");
			pstmt.setString(1, userd);
			pstmt.setString(2,k);
			pstmt.setString(3,cbms.getSelectedItem().toString());
			pstmt.setString(4,cbme.getSelectedItem().toString());
			pstmt.setString(5,cbes.getSelectedItem().toString());
			pstmt.setString(6, cbee.getSelectedItem().toString());
			pstmt.executeUpdate();

					
		} catch (ClassNotFoundException ce) {
			// TODO Auto-generated catch block
			ce.printStackTrace();
		} catch (SQLException ce) {
			// TODO Auto-generated catch block
			ce.printStackTrace();
		}
	}
	
	private void style()
	{
		Color c1= new Color(20,110,140);
		   Font f2  = new Font("comic sans",Font.ITALIC+Font.BOLD , 40);	   
		   Font f1  = new Font("comic sans",Font.ITALIC+Font.BOLD ,30);
		   Font f  = new Font("comic sans",Font.ITALIC+Font.BOLD ,20); 
        Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border h = BorderFactory.createTitledBorder(loweredbevel,":: DOCTOR SCHEDULE ::", TitledBorder.CENTER , TitledBorder.TOP ,f2,Color.red);
        Border k = BorderFactory.createMatteBorder(0,10,0,0,Color.red);
        Pmain.setBorder(BorderFactory.createCompoundBorder(h, k));
        
        lbset.setFont(f1);
		lbms.setFont(f);
		lbme.setFont(f);
		lbes.setFont(f);
		lbee.setFont(f);

		lbset.setForeground(c1);
		lbms.setForeground(c1);
		lbme.setForeground(c1);
		lbes.setForeground(c1);
		lbee.setForeground(c1);
		btsetschedule.setForeground(c1);
	}
	public void enable(boolean status)
	{
		cbms.setEnabled(status);
		cbme.setEnabled(status);
		cbes.setEnabled(status);
		cbee.setEnabled(status);
		for(int i=0;i<ch.length;i++)
			ch[i].setEnabled(status);
		btsetschedule.enable(status);
	}
	private void setdoctorinfotable()
	{
	try {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
		Statement stmt = con.createStatement();
		stmt.executeUpdate("create database if not exists ManagementDb");
		stmt.execute("Use ManagementDb");
		stmt.executeUpdate("create table if not exists DoctorFinalTb(username varchar(100),patient int,appointment int,rating float)");
		
		PreparedStatement precount =  con.prepareStatement("select count(*) from DoctorFinalTb where username=?");
		precount.setString(1, userd);
		ResultSet rcount = precount.executeQuery();
		rcount.next();
		int c = rcount.getInt(1);
		
		// it is needes as doctor have not set schedule but if patient have search that doctor it will create from there
		if(c==0) // insert 
		{
				PreparedStatement pinsert = con.prepareStatement("insert into DoctorFinalTb values(?,?,?,?)");
						pinsert.setString(1,userd);
						pinsert.setInt(2,0); // initial patient
						pinsert.setInt(3,0); // initial appointement
						pinsert.setFloat(4,-1); // intial rating
						pinsert.execute(); 
		}
	} catch (ClassNotFoundException ce) {
		// TODO Auto-generated catch block
		ce.printStackTrace();
	} catch (SQLException ce) {
		// TODO Auto-generated catch block
		ce.printStackTrace();
	}
}
}
