import java.awt.*;
import javax.swing.*;
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
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class DoctorID1 extends JDialog implements ActionListener, ItemListener, FocusListener, KeyListener {

	JLabel lbuser, lbpass;
	JLabel lbgender, lbdob, lbaddress;
	JLabel lbmobile, lbconfirmpass;
	JLabel lbemail, lbMaterialstatus, lblocation;
	JLabel lbcity, lbstate;
	JLabel lbspecilization, lbqualification, lblanguage;
	JLabel lbHospitalName, lbHospitalLocation, lbclinicname, lbcliniclocation;
	JButton btregister, btrefresh, btclose;
	JTextField txuser, txmobile, txemail, txlocation;
	JComboBox<String> cbcity, cbstate;
	JTextField txHospitalName, txHospitalLocation, txclinicname, txcliniclocation;
	JComboBox<String> chspecilization, chqualification;
	JTextArea taddress;
	JPasswordField txpass, txconfirmpass;
	JPanel P, Pgender, Pdob, Pbutton1, Pbutton2, PLang, Phos, Phosl, Pclinic, Pclinicl, Ppass;
	JPanel Pemail, PMaterial, Plocation, Pcity, Pstate;
	JRadioButton ckmale, ckfemale, ckhidden;
	JCheckBox chenglish, chhindi, chothers, chshowpass;
	ButtonGroup gr;
	JComboBox<String> chyy, chmm, chdd;
	JComboBox<String> cMs;
	CardLayout CLO;
	String trialmobile,trialuser;
	String place;
	GridLayout g11, g12, g13, goverall;
	
	public DoctorID1() {

		setVisible(true);
		setSize(new Dimension(1400, 1000));
		setPreferredSize(new Dimension(1400, 1000));
		setSize(1400, 1000);
		
		g11 = new GridLayout(1,1);
		g12 = new GridLayout(1,2);
		g13 = new GridLayout(1,3);
		
		trialmobile = "";
		trialuser = "";
		place = "Example123@gmail.com";
		String need = "*";
		lbuser = new JLabel("User Name" + need);
		lbpass = new JLabel("New Password" + need);
		lbconfirmpass = new JLabel("Confirm New Password" + need);
		lbemail = new JLabel("Email ID" + need);
		lbMaterialstatus = new JLabel("Material Status" + need);
		lblocation = new JLabel("Location" + need);
		lbcity = new JLabel("City" + need);
		lbstate = new JLabel("State" + need);
		lbgender = new JLabel("Gender" + need);

		lbspecilization = new JLabel("Specilization" + need);
		lbqualification = new JLabel("Qualification" + need);
		lblanguage = new JLabel("Language" + need);

		lbHospitalName = new JLabel("Hospital Name" + need);
		lbHospitalLocation = new JLabel("Hospital Location" + need);
		lbclinicname = new JLabel("Clinic Name");
		lbcliniclocation = new JLabel("Clinic Location");

		gr = new ButtonGroup();
		ckmale = new JRadioButton("Male");
		ckfemale = new JRadioButton("Female");
		ckhidden = new JRadioButton("Hidden"); // Don't add this
		// Used in refreshing of radio button

		gr.add(ckmale);
		gr.add(ckfemale);
		gr.add(ckhidden);

		chenglish = new JCheckBox("English");
		chenglish.setSelected(true);
		chhindi = new JCheckBox("Hindi");
		chhindi.setSelected(true);
		chothers = new JCheckBox("Others"); // Don't add this
		// Used in refreshing of radio button

		lbdob = new JLabel("DOB" + need);

		chyy = new JComboBox<String>();
		chmm = new JComboBox<String>();
		chdd = new JComboBox<String>();

		cMs = new JComboBox<String>();
		cMs.addItem("Select status");
		cMs.addItem("Single");
		cMs.addItem("Married");

		lbaddress = new JLabel("Address" + need);
		lbmobile = new JLabel("Personal Number" + need);

		btregister = new JButton("Register");
		btrefresh = new JButton("Refresh");
		btclose = new JButton("Close");

		txuser = new JTextField();
		txpass = new JPasswordField();
		// txpass.setEchoChar('*');
		// Used in case of Applet only
		txconfirmpass = new JPasswordField();
		// txconfirmpass.setEnabled(false);
		// txconfirmpass.setEchoChar('*');
		chshowpass = new JCheckBox("Show Password");
		txmobile = new JTextField();
		txemail = new JTextField();
		txemail.setText(place);
		txlocation = new JTextField();

		cbcity = new JComboBox<String>();
		cbcity.addItem("Select City");
		cbstate = new JComboBox<String>();
		cbstate.addItem("Select State");

		CreatingCombo.fillcbstate(cbstate);

		taddress = new JTextArea();
		chspecilization = new JComboBox<String>();
		chspecilization.addItem("Select Specilization");
		CreatingCombo.fillSpecilization(chspecilization);
		chqualification = new JComboBox<String>();
		chqualification.addItem("Select Qualification");
		CreatingCombo.fillQualification(chqualification);
		txHospitalName = new JTextField();
		txHospitalLocation = new JTextField();
		txclinicname = new JTextField();
		txcliniclocation = new JTextField();

		print();

		chyy.addItemListener(this);
		chmm.addItemListener(this);
		cbstate.addItemListener(this);
		chshowpass.addItemListener(this);
		btregister.addActionListener(this);
		btrefresh.addActionListener(this);
		btclose.addActionListener(this);

		txmobile.addKeyListener(this);
		txemail.addFocusListener(this);
		txpass.addFocusListener(this);
		txconfirmpass.addFocusListener(this);
		txuser.addFocusListener(this);
		txuser.addKeyListener(this);

	}

	public void print() {
		chyy.addItem("Year");
		for (int i = 1981; i <= 2000; i++)
			chyy.addItem(i + "");

		chmm.addItem("Month");
		for (int i = 1; i <= 12; i++)
			chmm.addItem(i + "");

		chdd.addItem("Date");

		// according to month and year date must change

		Pgender = new JPanel();
		Pgender.setLayout(g12);
		Pdob = new JPanel();
		Pdob.setLayout(g13);
		Pbutton1 = new JPanel();
		Pbutton1.setLayout(g11);
		Pbutton2 = new JPanel();
		Pbutton2.setLayout(g12);

		Pgender.add(ckmale);
		Pgender.add(ckfemale);
		// Don't add this hidden check box

		PLang = new JPanel();
		PLang.setLayout(g13);
		PLang.add(chenglish);
		PLang.add(chhindi);
		PLang.add(chothers);

		Pdob.add(chyy);
		Pdob.add(chmm);
		Pdob.add(chdd);

		Ppass = new JPanel();
		Ppass.setLayout(g12);
		Ppass.add(txpass);
		Ppass.add(chshowpass);

		Pbutton1.add(btregister);
		Pbutton2.add(btrefresh);
		Pbutton2.add(btclose);

		Pcity = new JPanel();
		Pcity.setLayout(g12);
		Pcity.add(lbcity);
		Pcity.add(cbcity);

		Pstate = new JPanel();
		Pstate.setLayout(g12);
		Pstate.add(lbstate);
		Pstate.add(cbstate);

		Phos = new JPanel();
		Phos.setLayout(g12);
		Phos.add(lbHospitalName);
		Phos.add(txHospitalName);

		Phosl = new JPanel();
		Phosl.setLayout(g12);
		Phosl.add(lbHospitalLocation);
		Phosl.add(txHospitalLocation);

		Pclinic = new JPanel();
		Pclinic.setLayout(g12);
		Pclinic.add(lbclinicname);
		Pclinic.add(txclinicname);

		Pclinicl = new JPanel();
		Pclinicl.setLayout(g12);
		Pclinicl.add(lbcliniclocation);
		Pclinicl.add(txcliniclocation);

		goverall = new GridLayout(17,2);
		P = new JPanel();
		P.setLayout(goverall);
		P.add(lbuser);
		P.add(txuser);
		P.add(lbpass);
		P.add(Ppass);
		P.add(lbconfirmpass);
		P.add(txconfirmpass);
		P.add(lbmobile);
		P.add(txmobile);
		P.add(lbemail);
		P.add(txemail);
		P.add(lbgender);
		P.add(Pgender);
		P.add(lbMaterialstatus);
		P.add(cMs);
		P.add(lbdob);
		P.add(Pdob);
		P.add(lbaddress);
		P.add(taddress);
		P.add(lblocation);
		P.add(txlocation);
		P.add(Pstate);
		P.add(Pcity);
		P.add(lbspecilization);
		P.add(chspecilization);
		P.add(lbqualification);
		P.add(chqualification);
		P.add(lblanguage);
		P.add(PLang);
		P.add(Phos);
		P.add(Phosl);
		P.add(Pclinic);
		P.add(Pclinicl);
		P.add(Pbutton1);
		P.add(Pbutton2);

		setLayout(new BorderLayout());
		add(P, BorderLayout.CENTER); // default of JApplet
		Font f = new Font("comic sans", Font.ITALIC + Font.BOLD, 30);
		@SuppressWarnings("unused")
		Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		@SuppressWarnings("unused")
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		@SuppressWarnings("unused")
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border h = BorderFactory.createTitledBorder(loweredbevel, ":: DOCTOR SIGNUP ::", TitledBorder.CENTER,
				TitledBorder.TOP, f, Color.red);
		Border k = BorderFactory.createMatteBorder(0, 10, 0, 0, Color.red);
		P.setBorder(BorderFactory.createCompoundBorder(h, k));
		P.setSize(new Dimension(1000, 800));
		P.setPreferredSize(new Dimension(1000, 800));

	}

	@Override
	public void itemStateChanged(ItemEvent ie) {

		Object src = ie.getSource();

		if (src == cbstate) {
			cbcity.removeAllItems();
			cbcity.addItem("Select City");
			String s = cbstate.getSelectedItem().toString();
			s = s.replace(" ", "_");
			s = s.replace("&", "and");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
				Statement stmt = con.createStatement();
				stmt.executeUpdate("create database if not exists states");
				stmt.execute("Use states");
				// table exist already

				ResultSet rs = stmt.executeQuery("select cities_names from " + s + "tb");
				while (rs.next()) {
					cbcity.addItem("" + rs.getString("cities_names"));
				}
				con.close();

			} catch (ClassNotFoundException ae) {
				// TODO Auto-generated catch block
				ae.printStackTrace();
			} catch (SQLException ae) {
				// TODO Auto-generated catch block
				ae.printStackTrace();
			}
		} else
		// yymmdd
		if (chyy.getSelectedIndex() != 0 && chmm.getSelectedIndex() != 0) {

			int yy = Integer.parseInt(chyy.getSelectedItem().toString());
			int mm = Integer.parseInt(chmm.getSelectedItem().toString());

			// every time it come here we will remove previous entered item
			// and then it will add automatically
			
			chdd.removeAllItems();
			chdd.addItem("Date");

			int days = 0;

			if (mm == 2) {
				if (yy % 4 == 0)

					days = 29;
				else
					days = 28;
			} else {
				if (mm == 4 || mm == 6 || mm == 9 || mm == 11)
					days = 30;
				else
					days = 31;
			}

			for (int i = 1; i <= days; i++)
				chdd.addItem(i + "");

		}
		if (chshowpass.isSelected()) {
			txpass.setEchoChar((char) 0);
		} else {
			txpass.setEchoChar('*');
		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		Object src = ae.getSource();
		// in refresh radio JButton will remain as it
		// making hidden radio JButton

		if (src == btregister) {
			String pass = new String(txpass.getPassword());
			String confirmpass = new String(txconfirmpass.getPassword());
			if (txuser.getText().equals("") || pass.equals("") || confirmpass.equals("")
					|| txemail.getText().equals("") || txmobile.getText().equals("")
					|| !(ckmale.isSelected() || ckfemale.isSelected()) || txlocation.getText().equals("")
					|| taddress.getText().equals("") || chspecilization.getSelectedIndex() == 0
					|| chqualification.getSelectedIndex() == 0 || txHospitalName.getText().equals("")
					|| txHospitalLocation.getText().equals("") || cMs.getSelectedIndex() == 0
					|| chyy.getSelectedIndex() == 0 || chmm.getSelectedIndex() == 0 || chdd.getSelectedIndex() == 0
					|| cbstate.getSelectedIndex() == 0 || cbcity.getSelectedIndex() == 0
					|| !(chenglish.isSelected() || chhindi.isSelected() || chothers.isSelected())
					|| txmobile.getText().length() != 10)
				// No need of clinic name and clinic location
				JOptionPane.showMessageDialog(null, "Kindly fill all the required entries of Form", "Unfilled Form",
						JOptionPane.ERROR_MESSAGE);
			else 
				createDoctor();
			
		} else if (src == btrefresh) {
			txuser.setText("");
			txpass.setText("");

			// Radio JButton
			ckhidden.setSelected(true);
			// if it is true then other will automatically will get off

			chyy.setSelectedIndex(0);
			chmm.setSelectedIndex(0);
			chdd.setSelectedIndex(0);
			taddress.setText("");
			txconfirmpass.setText("");

			txmobile.setText("");
			cbcity.setSelectedIndex(0);
			cbstate.removeItemListener(this);
			cbstate.setSelectedIndex(0);
			cbstate.addItemListener(this);
			txlocation.setText("");
			txemail.setText("");
			cMs.setSelectedIndex(0);
			chspecilization.setSelectedIndex(0);
			chqualification.setSelectedIndex(0);
			txHospitalName.setText("");
			txHospitalLocation.setText("");
			txclinicname.setText("");
			txcliniclocation.setText("");
			chenglish.setSelected(true);
			chhindi.setSelected(true);
			chothers.setSelected(false);

		} else if (src == btclose) {
			this.dispose();
		}
	}

	public void createDoctor() {
		// TODO Auto-generated method stub
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("create database if not exists ManagementDb");
			stmt.execute("Use ManagementDb");
			stmt.executeUpdate("create table if not exists  DoctorTb( username varchar(100),"
					+ "password varchar(100)," + "email varchar(100)," + "phone varchar(100)," + "gender int,"
					+ "material int," + "dob date ," + "location varchar(100)," + "address varchar(100),"
					+ "city varchar(100)," + "state varchar(100)," + "spec varchar(100)," + "qual varchar(100),"
					+ "lang varchar(100)," + "hname varchar(100)," + "hloc varchar(100),"
					+ "cname varchar(100)," + "cloc varchar(100)," + "primary key(username))");

			PreparedStatement pres = con.prepareStatement("select count(*) from DoctorTb where username=?");
			pres.setString(1, txuser.getText());

			ResultSet rs = pres.executeQuery();
			rs.next();
			int c = rs.getInt(1);

			if (c == 0) {
				PreparedStatement pre = con.prepareStatement(
						"insert into DoctorTb (username,password,email,phone,gender,material,dob,location,address,city,state,spec,qual,lang,hname,hloc,cname,cloc) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				pre.setString(1, txuser.getText());
				String pass = new String(txpass.getPassword());
				pre.setString(2, pass);
				pre.setString(3, txemail.getText());
				pre.setString(4, txmobile.getText());

				if (ckmale.isSelected())
					pre.setInt(5, 1);
				else if (ckfemale.isSelected())
					pre.setInt(5, 0);
				// 0 for female
				// 1 for male

				pre.setInt(6, cMs.getSelectedIndex());
				// 1 for single
				// 2 for married

				int yy = Integer.parseInt(chyy.getSelectedItem().toString());
				int mm = Integer.parseInt(chmm.getSelectedItem().toString());
				int dd = Integer.parseInt(chdd.getSelectedItem().toString());
				@SuppressWarnings("deprecation")
				Date dob = new Date(yy - 1900, mm - 1, dd);
				/*
				 * year the year minus 1900; must be 0 to 8099. (Note that8099 is 9999 minus
				 * 1900.) month 0 to 11 day 1 to 31
				 */

				pre.setDate(7, dob);

				pre.setString(8, txlocation.getText());
				pre.setString(9, taddress.getText());
				pre.setString(10, cbcity.getSelectedItem().toString());
				pre.setString(11, cbstate.getSelectedItem().toString());

				pre.setString(12, chspecilization.getSelectedItem().toString());
				pre.setString(13, chqualification.getSelectedItem().toString());

				String lan = "";
				lan += chenglish.isSelected() ? "1" : "0";
				lan += chhindi.isSelected() ? "1" : "0";
				lan += chothers.isSelected() ? "1" : "0";

				pre.setString(14, lan);

				pre.setString(15, txHospitalName.getText());
				pre.setString(16, txHospitalLocation.getText());
				pre.setString(17, txclinicname.getText());
				pre.setString(18, txcliniclocation.getText());

				pre.executeUpdate();
				System.out.println("Insertion Sucessfully");
				// this.dispose();
				
			} else if (c == 1) {
				JOptionPane.showMessageDialog(null, txuser.getText() + " already exists.Try other username",
						"Incorrect Username", JOptionPane.ERROR_MESSAGE);
				txuser.requestFocus();
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
	

	@Override
	public void focusGained(FocusEvent e) {

		Object src = e.getSource();

		if (src == txemail) {
			if (txemail.getText().equalsIgnoreCase(place))
				txemail.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object src = e.getSource();
		String pass = new String(txpass.getPassword());
		String confirmpass = new String(txconfirmpass.getPassword());
		if (src == txconfirmpass) {
			int b = pass.compareTo(confirmpass);
			// b will be zero if they are same
			if (b != 0) {
				JOptionPane.showMessageDialog(null, "Both Passwords Must Be Same", "Incorrect Password",
						JOptionPane.ERROR_MESSAGE);
				chshowpass.setSelected(true);
				;
				txconfirmpass.requestFocus();
			} else
				chshowpass.setSelected(false);
		} else if (src == txuser) {
			checkuser();

		} else if (src == txpass) {
			if (!pass.equals(confirmpass))
				txconfirmpass.requestFocus();
		}
	}

	public void checkuser() {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
			Statement stmt = con.createStatement();
			stmt.executeUpdate("create database if not exists ManagementDb");
			stmt.execute("Use ManagementDb");
			stmt.executeUpdate("create table if not exists  DoctorTb( username varchar(100)," + "password varchar(100),"
					+ "email varchar(100)," + "phone varchar(100)," + "gender int," + "material int," + "dob date ,"
					+ "location varchar(100)," + "address varchar(100)," + "city varchar(100)," + "state varchar(100),"
					+ "spec varchar(100)," + "qual varchar(100)," + "lang varchar(100)," + "hname varchar(100),"
					+ "hloc varchar(100)," + "cname varchar(100)," + "cloc varchar(100)," + "primary key(username))");

			PreparedStatement pres = con.prepareStatement("select count(*) from DoctorTb where username=?");
			pres.setString(1, txuser.getText());

			ResultSet rs = pres.executeQuery();
			rs.next();
			int c = rs.getInt(1);

			if (c != 0) {

				JOptionPane.showMessageDialog(null, txuser.getText() + " already exists.Try other username",
						"Incorrect Username", JOptionPane.ERROR_MESSAGE);
				txuser.requestFocus();
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
		Object src = e.getSource();
		// System.out.println(code);
		if (src == txmobile) {
			if (txmobile.getText().length() <= 10) {
				int code = e.getKeyCode();
				if (!(code >= KeyEvent.VK_0 && code <= KeyEvent.VK_9) && code != KeyEvent.VK_BACK_SPACE
						&& code != KeyEvent.VK_LEFT && code != KeyEvent.VK_RIGHT && code != KeyEvent.VK_DELETE) {
					// e.consume();
					txmobile.setText(trialmobile);
					getToolkit().beep();

				}
				trialmobile = txmobile.getText();
			} else {
				txmobile.setText(trialmobile);
				getToolkit().beep();
				trialmobile = txmobile.getText();
			}
		} else if (src == txuser) {
			int code = e.getKeyCode();
			if (!(code >= KeyEvent.VK_A && code <= KeyEvent.VK_Z) && code != KeyEvent.VK_BACK_SPACE
					&& code != KeyEvent.VK_LEFT && code != KeyEvent.VK_RIGHT && code != KeyEvent.VK_DELETE) {
				// e.consume();
				txuser.setText(trialuser);
				getToolkit().beep();

			}
			trialuser = txuser.getText();
		}
	}
}
