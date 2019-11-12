
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PatientInfo extends JPanel implements ActionListener,ItemListener,FocusListener,KeyListener{

	JLabel lbuser,lbemail,lbmobile,lbgender,lbstatus,lbdob,lblocation,lbaddress,lbstate,lbcity,lbdisease,lbpast;
	JTextField txuser;
	JTextField txmobile,txpassrecords;
	JTextArea taddress;
	JTextField txemail,txlocation;
	JComboBox cbstate,cbcity;
	JPanel P,Phead,pbutton;   
	JPanel Pgender,Pdob;
	JButton btedit,btsubmit;
	JRadioButton ckmale,ckfemale,ckhidden;
	ButtonGroup gr;
	JComboBox chyy,chmm,chdd;
	JComboBox cMs;
	JPanel Pdisease;
	JPanel Pbut,Pclass ;
	JPanel Pdis[];
	JButton bnext,bprevious;
	JCheckBox ch[];
	CardLayout CLO;
	String trial;
	String userp;

	public PatientInfo()
	{

          userp = PatientTabbedBar.userp; // taking username from previous login form
		
		String need = "*";
		trial ="";
		setVisible(true);
		setSize(800,800);
		txuser = new JTextField("Patient Name");
		txuser.setEditable(false);

		txemail = new JTextField("Email ID");
		txlocation= new JTextField("Location");
		taddress = new JTextArea("Address");
		txmobile = new JTextField("Personal Number");
		txpassrecords = new JTextField("Past Records");

		gr = new ButtonGroup();
		ckmale = new JRadioButton("Male");
		ckfemale = new JRadioButton("Female");
		ckhidden = new JRadioButton("Hidden"); // dont add this

		gr.add(ckmale);
		gr.add(ckfemale);
		gr.add(ckhidden);
		chyy = new JComboBox();
		chmm = new JComboBox();
		chdd = new JComboBox();

		cMs=new JComboBox();
		cMs.addItem("Select status");
		cMs.addItem("Single");
		cMs.addItem("Married");

		cbcity= new JComboBox();
		cbcity.addItem("Select City"); 
		cbstate= new JComboBox();
		cbstate.addItem("Select State");
		fillcbstate();

		btedit=new JButton("Edit");
		btedit.addActionListener(this);

		btsubmit=new JButton("Submit");
		btsubmit.addActionListener(this);
		btsubmit.setEnabled(false);
		bnext = new JButton("Next");
		bprevious = new JButton("Previous");

		chyy.addItemListener(this);
		chmm.addItemListener(this);
		cbstate.addItemListener(this);
		bnext.addActionListener(this);
		bprevious.addActionListener(this);

		lbuser=new JLabel("userp NAME : ");
		lbemail=new JLabel("EMAIL : ");
		lbmobile=new JLabel("PERSONAL NO :");
		lbgender=new JLabel("GENDER : ");
		lbstatus=new JLabel("MATERIAL STATUS : ");
		lbdob=new JLabel("DOB : ");
		lblocation=new JLabel("LOCATION : ");
		lbaddress=new JLabel("ADDRESS : ");
		lbstate=new JLabel("STATE : ");
		lbcity=new JLabel("CITY : ");
		lbdisease=new JLabel("RELATED DISEASE : ");
		lbpast=new JLabel("PAST RECORDS : ");

        txmobile.addKeyListener(this);
		print();
		enable(false);
		fillform(); // overwriting 
		style();

	}

	public void fillcbstate()
	{
		try {
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
	}

	public void fillform()
	{
		try {


			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("create database if not exists ManagementDb");
			stmt.execute("Use ManagementDb");
			stmt.executeUpdate("create table if not exists  PatientTb( username varchar(100),"
					+ "password varchar(100),"
					+ "email varchar(100),"
					+ "phone varchar(100),"
					+ "gender int,"
					+ "material int,"
					+ "dob date ,"
					+ "location varchar(100),"
					+ "address varchar(100),"
					+ "city varchar(100),"
					+ "state varchar(100),"
					+ "disease varchar(100),"
					+ "past varchar(100),"
					+ "primary key(username))");

			PreparedStatement pstmt = con.prepareStatement("select * from PatientTb where username = ?");
			pstmt.setString(1, userp);
			ResultSet rs =  pstmt.executeQuery();
			rs.next();

			txuser.setText(rs.getString("username"));
			txemail.setText(rs.getString("email"));
			txmobile.setText(rs.getString("phone"));
			txlocation.setText(rs.getString("location"));
			taddress.setText(rs.getString("address"));
			txpassrecords.setText(rs.getString("past"));

			int g = rs.getInt("gender"); 
			if(g==1)
				ckmale.setSelected(true);
			else if(g==0)
				ckfemale.setSelected(true);

			cMs.setSelectedIndex(rs.getInt("material"));
			cbstate.setSelectedItem(rs.getString("state"));
			cbcity.setSelectedItem(rs.getString("city"));

			Date dob = rs.getDate("dob");
			int dd = dob.getDate();
			int mm = dob.getMonth();
			int yy = dob.getYear();

			chyy.setSelectedItem(Integer.toString(1900+yy));
			chmm.setSelectedItem(Integer.toString(1+mm));
			chdd.setSelectedItem(Integer.toString(dd));
			String d = rs.getString("disease");
			for(int i=0;i<ch.length;i++)
			{
				if(d.charAt(i) == '1')
					ch[i].setSelected(true);

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

	public void print()
	{

		P = new JPanel();
		Phead = new JPanel();

		chyy.addItem("Year");
		for(int i=1981;i<=2000;i++)
			chyy.addItem(i+"");

		chmm.addItem("Month");
		for(int i=1;i<=12;i++)
			chmm.addItem(i+"");

		chdd.addItem("Date");


		Pgender = new JPanel();
		Pgender.setLayout(new GridLayout(1, 2));
		Pdob = new JPanel();
		Pdob.setLayout(new GridLayout(1, 3));

		Pgender.add(ckmale);
		Pgender.add(ckfemale);
		// dont add this hidden check box

		Pdis = new JPanel[3];
		ch = new JCheckBox[9];

		for(int i=0;i<Pdis.length;i++)
		{
			Pdis[i] = new JPanel();
			Pdis[i].setLayout(new GridLayout(1, 3));
		}
		String disname[] = {"Diabetes","High Blood Pressure","Respiratory Diseases","Heart Diseases","Digestive Diseases","High Cholesterol ","Stroke","Cancer","Alzheimer’s disease"};

		for(int i=0;i<ch.length;i++)
			ch[i] = new JCheckBox(disname[i]);

		Pdis[0].add(ch[0]);
		Pdis[0].add(ch[1]);
		Pdis[0].add(ch[2]);

		Pdis[1].add(ch[3]);
		Pdis[1].add(ch[4]);
		Pdis[1].add(ch[5]);

		Pdis[2].add(ch[6]);
		Pdis[2].add(ch[7]);
		Pdis[2].add(ch[8]);



		Pdisease = new JPanel();
		Pdisease.setLayout(new BorderLayout());
		Pbut = new JPanel();
		Pbut.add(bnext);
		Pbut.add(bprevious);
		Pdisease.add(Pbut,BorderLayout.NORTH);

		Pclass = new JPanel();
		CLO = new CardLayout();
		Pclass.setLayout(CLO);
		Pclass.add("Type1", Pdis[0]); // adding panels
		Pclass.add("Type2", Pdis[1]);
		Pclass.add("Type3",Pdis[2]);
		Pdisease.add(Pclass,BorderLayout.CENTER);



		Pdob.add(chyy);
		Pdob.add(chmm);
		Pdob.add(chdd);
		P.setLayout(new GridLayout(12,2));
		P.add(lbuser);
		P.add(txuser);
		P.add(lbemail);
		P.add(txemail);
		P.add(lbmobile);
		P.add(txmobile);
		P.add(lbgender);
		P.add(Pgender);
		P.add(lbstatus);
		P.add(cMs);
		P.add(lbdob);
		P.add(Pdob);
		P.add(lblocation);
		P.add(txlocation);
		P.add(lbaddress);
		P.add(taddress);
		P.add(lbstate);
		P.add(cbstate);
		P.add(lbcity);
		P.add(cbcity);
		P.add(lbdisease);
		P.add(Pdisease);
		P.add(lbpast);
		P.add(txpassrecords);

		setLayout(new BorderLayout());
		add(P,BorderLayout.CENTER);
		pbutton = new JPanel(); 
		pbutton.add(btedit);
		pbutton.add(btsubmit);
		add(pbutton,BorderLayout.SOUTH);
		P.setBorder(BorderFactory.createTitledBorder(null, ":: PATIENT INFO ::"));
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		Object src= ae.getSource();
		if(src==btedit)
		{
			btsubmit.setEnabled(true);
			btedit.setEnabled(false);
			enable(true);
		}
		else if(src==btsubmit)
		{
			updateform();
	
		} else if(src == bnext)
		{
			CLO.next(Pclass);
		}
		else if(src == bprevious)
		{
			CLO.previous(Pclass);
		}

	}

	public void enable(boolean status)
	{
		txemail.setEnabled(status);
		txlocation.setEnabled(status);
		cMs.setEnabled(status);
		chyy.setEnabled(status);
		chmm.setEnabled(status);
		chdd.setEnabled(status);
		ckmale.setEnabled(status);
		ckfemale.setEnabled(status);
		cbcity.setEnabled(status);
		cbstate.setEnabled(status);
		taddress.setEnabled(status);
		txmobile.setEnabled(status);

		for(int i=0;i<ch.length;i++)
			ch[i].setEnabled(status);
		bnext.setEnabled(status);
		bprevious.setEnabled(status);
		txpassrecords.setEnabled(status);

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		int code = e.getKeyCode();	
		//System.out.println(code);
		if(txmobile.getText().length() <=10)
		{
			if(!(code >= KeyEvent.VK_0 && code <= KeyEvent.VK_9) 
					&& code != KeyEvent.VK_BACK_SPACE 
					&& code != KeyEvent.VK_LEFT 
					&& code != KeyEvent.VK_RIGHT 
					&& code != KeyEvent.VK_DELETE)
				{	
					//e.consume();
					txmobile.setText(trial);
						getToolkit().beep();
				
				}
			trial=txmobile.getText();
	}
		else 
		{
			txmobile.setText(trial);
			getToolkit().beep();
			trial=txmobile.getText();
		}
	
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void itemStateChanged(ItemEvent ie) {
		// TODO Auto-generated method stub
		Object src = ie.getSource();

		if(src == cbstate)
		{
			cbcity.removeAllItems();
			cbcity.addItem("Select City");


			String s = cbstate.getSelectedItem().toString();

			try {


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
					cbcity.addItem(""+rs.getString("city_name"));
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

		// yymmdd 
		else 
			if(chyy.getSelectedIndex() != 0 && chmm.getSelectedIndex() != 0)
			{


				int yy = Integer.parseInt( chyy.getSelectedItem().toString() );
				int mm = Integer.parseInt( chmm.getSelectedItem().toString() );

				// every time it come here we will remove previous entered item 
				// and then it will add automatically

				// for(int i=1;i<chdd.getItemCount();i++)
				// chdd.remove(1);

				chdd.removeAllItems();
				chdd.addItem("Date");

				int days = 0;

				if(mm == 2)
				{
					if(yy%4 ==0)

						days = 29;
					else
						days = 28;
				}
				else 
				{ 
					if(mm == 4 || mm == 6 || mm == 9 || mm == 11)
						days = 30;
					else 
						days = 31;
				}

				for(int i=1 ; i<=days ;i++)
					chdd.addItem(i+"");
			}  

	}
	public void updateform()
	{


		if( txuser.getText().equals("") 
				|| txemail.getText().equals("") 
				|| txmobile.getText().equals("")
				|| !(ckmale.isSelected() || ckfemale.isSelected())
				|| txlocation.getText().equals("")
				||  taddress.getText().equals("")
				|| cMs.getSelectedIndex() == 0 
				|| chyy.getSelectedIndex() == 0
				|| chmm.getSelectedIndex() == 0
				|| chdd.getSelectedIndex() == 0
				|| cbstate.getSelectedIndex() == 0
				|| cbcity.getSelectedIndex() == 0

				)
			// no related disease and past records
		{
			JOptionPane.showMessageDialog(null, "Kindly fill all the required entries of Form", "Unfilled Form", JOptionPane.ERROR_MESSAGE);
		}

		else 
		{  
			try {


				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt = con.createStatement();
				stmt.executeUpdate("create database if not exists ManagementDb");
				stmt.execute("Use ManagementDb");
				stmt.executeUpdate("create table if not exists  PatientTb( username varchar(100),"
						+ "password varchar(100),"
						+ "email varchar(100),"
						+ "phone varchar(100),"
						+ "gender int,"
						+ "material int,"
						+ "dob date ,"
						+ "location varchar(100),"
						+ "address varchar(100),"
						+ "city varchar(100),"
						+ "state varchar(100),"
						+ "disease varchar(100),"
						+ "past varchar(100),"
						+ "primary key(username))");

				PreparedStatement pre = con.prepareStatement("update PatientTb set email=?,phone=?,gender=?,material=?,dob=?,location=?,address=?,city=?,state=?,disease=?,past=? where username=?");

				pre.setString(1, txemail.getText());
				pre.setString(2, txmobile.getText());

				if(ckmale.isSelected())
					pre.setInt(3,1);
				else 
					if(ckfemale.isSelected())
						pre.setInt(3,0);
				// 1 for male
				// 0 for female

				pre.setInt(4,cMs.getSelectedIndex());
				// 1 for single
				// 2 for married 


				int yy = Integer.parseInt(chyy.getSelectedItem().toString());
				int mm = Integer.parseInt(chmm.getSelectedItem().toString());
				int dd = Integer.parseInt(chdd.getSelectedItem().toString());
				Date dob = new Date(yy-1900, mm-1 , dd);
				/*
				 * year the year minus 1900; must be 0 to 8099. (Note that8099 is 9999 minus 1900.)
				 * month 0 to 11
				 * day 1 to 31
				 */

				pre.setDate(5, dob);

				pre.setString(6, txlocation.getText());
				pre.setString(7, taddress.getText());
				pre.setString(8, cbcity.getSelectedItem().toString());
				pre.setString(9, cbstate.getSelectedItem().toString());


				String res ="";
				for(int i=0;i<ch.length;i++)
				{
					if(ch[i].isSelected())
						res += "1";
					else res+="0";
				}
				pre.setString(10, res);
				pre.setString(11, txpassrecords.getText());

				pre.setString(12, userp);

				pre.executeUpdate();
				JOptionPane.showMessageDialog(null, "You have successfully changed your details", "Insertion successful", JOptionPane.INFORMATION_MESSAGE);
				
				btsubmit.setEnabled(false);
				btedit.setEnabled(true);
				enable(false);
				
				btsubmit.setEnabled(false);
				btedit.setEnabled(true);
				enable(false);
				//this.dispose();
				// using jdialog box

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

	public void style()

	{

		Color c1= new Color(20,110,140);
		Font f1  = new Font("comic sans",Font.ITALIC+Font.BOLD , 20);
		Font f2  = new Font("comic sans",Font.ITALIC+Font.BOLD , 40);
		Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border h = BorderFactory.createTitledBorder(loweredbevel,":: ABOUT ME ::", TitledBorder.CENTER , TitledBorder.TOP ,f2,Color.red);
		Border k = BorderFactory.createMatteBorder(0,10,0,0,Color.red);
		P.setBorder(BorderFactory.createCompoundBorder(h, k));

		lbuser.setFont(f1);
		lbemail.setFont(f1);
		lbmobile.setFont(f1);
		lbgender.setFont(f1);
		lbstatus.setFont(f1);
		lbdob.setFont(f1);
		lblocation.setFont(f1);
		lbaddress.setFont(f1);
		lbstate.setFont(f1);
		lbcity.setFont(f1);
		lbdisease.setFont(f1);
		lbpast.setFont(f1);

		lbuser.setForeground(c1);
		lbemail.setForeground(c1);
		lbmobile.setForeground(c1);
		lbgender.setForeground(c1);
		lbstatus.setForeground(c1);
		lbdob.setForeground(c1);
		lblocation.setForeground(c1);
		lbaddress.setForeground(c1);
		lbstate.setForeground(c1);
		lbcity.setForeground(c1);
		lbdisease.setForeground(c1);
		lbpast.setForeground(c1);


	}
}


