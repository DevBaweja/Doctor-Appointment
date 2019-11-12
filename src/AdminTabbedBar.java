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

public class AdminTabbedBar extends JFrame implements ActionListener{

	// This is JFrame called in AdminLoginHome
                JTabbedPane Jtp;
                JPanel logout;
                JButton btlogout;
                
                public AdminTabbedBar()
                {
                	setVisible(true);
                	setLayout(new BorderLayout());
                    setSize(new Dimension(3000, 1000));

                    userlogout();
                Jtp = new JTabbedPane();
                Jtp.addTab(" Home ", new AdminWelcome());
                Jtp.addTab("See Doctor", new AdminShowAllDoctor());
                Jtp.addTab("See Patients",new AdminShowAllPatient());
                Jtp.addTab("Log Out", logout);
                Jtp.addTab(" Contact Us ", new ContactUs());
                
                
                Jtp.setBackground(Color.black);
                Jtp.setForeground(Color.white);
                
                Jtp.setBackgroundAt(3, Color.red);
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
				int ans = JOptionPane.showConfirmDialog(null, "Do you really want to logout ,Admin ", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								if(ans == JOptionPane.YES_OPTION)
									this.dispose(); // when it is frame
								
					}
                }
         
}


