import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class AdminLoginHome  extends JPanel implements ActionListener{
	
	public static String userp = "";
	
	JLabel lbadmin,lbpass;
	JTextField txadmin;
	JPasswordField txpass;
	JButton btsubmit,btcancel,btregister;
	JPanel p1,p2,p3,p4,p5;
	JPanel pmain; 
	
	public AdminLoginHome()
	{

		setVisible(true);
		setSize(800,800);
		p1 = new JPanel();
		
		p2 = new JPanel();
		p2.setLayout(new GridLayout(1,2));
		p3 = new JPanel();
		p3.setLayout(new GridLayout(1,2));
		p4 = new JPanel();
		p4.setLayout(new GridLayout(1,2));
		p5 = new JPanel();
	
		lbadmin = new JLabel("Admin UserName");
		lbpass = new JLabel("Password");

		
		txadmin = new JTextField(20);
		txpass = new JPasswordField(20);

		
		btsubmit = new JButton("Submit");
		btcancel = new JButton("Cancel");
		// btregister = new JButton("Create New Account");
		
		style();
		
		print();
		
		btsubmit.addActionListener(this);
		btcancel.addActionListener(this);
	//	btregister.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object src = e.getSource();
		if(src == btcancel)
		{
			System.exit(-1);
		}
		else if(src == btsubmit)
		{
			if(txadmin.getText().equals("admin") && txpass.getText().equals("admin"))
				{
					JOptionPane.showMessageDialog(null,"You are now Login as "+txadmin.getText(),"Login Successful",JOptionPane.INFORMATION_MESSAGE);
					refresh();
					 new AdminTabbedBar();
				}
			else
			{
				JOptionPane.showMessageDialog(null,txadmin.getText()+" does not exists.","Incorrect Username or Password",JOptionPane.ERROR_MESSAGE);
				refresh();
              	   txadmin.requestFocus();
			}
			
		}
		else if(src == btregister)
		{
			// new adminID1();
		} 
	}
	public void refresh()
	{
		txadmin.setText("");
		txpass.setText("");
	}
	
	
	public void style()
	{
	
        Font f  = new Font("comic sans",Font.ITALIC+Font.BOLD , 40); 
        Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border h = BorderFactory.createTitledBorder(loweredbevel,":: ADMIN LOGIN ::", TitledBorder.CENTER , TitledBorder.TOP ,f,Color.red);
        Border k = BorderFactory.createMatteBorder(0,10,0,0,Color.red);
        setBorder(BorderFactory.createCompoundBorder(h, k));
		
		btsubmit.setBackground(Color.green);
		btcancel.setBackground(Color.red);
		// btregister.setBackground(Color.green);
		
		//Foreground color
		Color c = new Color(20,110,140);
		lbadmin.setForeground(c);
		lbpass.setForeground(c);
		// Font
		Font f1 = new Font("Comic Sans MS",Font.BOLD,30);
		lbadmin.setFont(f1);
		lbpass.setFont(f1);
		btsubmit.setFont(f1);
		btcancel.setFont(f1);
		
		
		Font f2 = new Font("Arial",Font.BOLD,20);
		txadmin.setFont(f2);
		txpass.setFont(f2);
	}
	
	public void print()
	{
		GridLayout g = new GridLayout(5,1,70,70); 
		setLayout(g); 
	
		add(p1);
		
		p2.add(lbadmin);
		p2.add(txadmin);
		
		p3.add(lbpass);
		p3.add(txpass);
		
		p4.add(btsubmit);
		p4.add(btcancel);
		
		// p5.add(btregister);
		
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		
	}
	
}
