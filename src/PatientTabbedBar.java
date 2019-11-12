import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PatientTabbedBar extends JFrame implements ActionListener{

	// This is JFrame called in PatientLoginHome // see logout also dispose
                JTabbedPane Jtp;
                JPanel logout;
                JButton btlogout;
                static String userp;
                
                public PatientTabbedBar()
                {
                	userp = PatientLoginHome.userp; 

                	userlogout();

                	setVisible(true);
                	setSize(new Dimension(3000, 1000));
                	
                setLayout(new BorderLayout());
                Jtp = new JTabbedPane();
                Jtp.addTab("Home", new PatientWelcome());
                Jtp.addTab("Search Doctor", new Patient_SearchDoctor());
                Jtp.addTab("About Me",new PatientInfo());
                Jtp.addTab("Manage Password", new Patient_ChangePassword());
                Jtp.addTab("Log Out", logout);
                Jtp.addTab("Contact Us", new ContactUs());
                
                
                Jtp.setBackground(Color.black);
                Jtp.setForeground(Color.white);
                
                Jtp.setBackgroundAt(3, Color.red);
                Jtp.setBackgroundAt(4,Color.red);
                add(Jtp);
                }
                public void userlogout()
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
				int ans = JOptionPane.showConfirmDialog(null, "Do you really want to logout ,Patient "+userp, "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								if(ans == JOptionPane.YES_OPTION)
									this.dispose(); // when it is frame
				
					}
				}
}


