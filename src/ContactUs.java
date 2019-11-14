import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ContactUs extends JPanel{

                JPanel p1,p2,p3;
                JLabel lbimg,lbaddress,lbemail,lbcontact;
                public ContactUs()
                {
                	setVisible(true);
                                setLayout(new BorderLayout());
                                lbimg= new JLabel(new ImageIcon("img.jpg"));
                                lbaddress=new JLabel("Address : "+ "3-D-32 JNV Colony,Bikaner,Rajasthan");
                                lbemail=new JLabel("mail us at:  "+"  onlinedoct24@gmail.com");
                                lbcontact = new JLabel(":: Contact Us ::");
                                
                                p1=new JPanel();
                                p2=new JPanel();
                                p3=new JPanel();
                                
                                p1.add(lbimg);
                                p2.add(lbaddress);
                                p2.add(lbemail);
                                p3.add(lbcontact);
                                
                                //p1.setSize(new Dimension(400,600));
                                //p1.setPreferredSize(new Dimension(400,600));
                                
                                add(p3,BorderLayout.NORTH);
                                add(p1,BorderLayout.CENTER);
                                add(p2,BorderLayout.SOUTH);
                
                                style();  
                }
                                
                
                public void style()
                                {
                                
                                                Color c1= new Color(20,110,140);
                        Font f  = new Font("comic sans",Font.ITALIC+Font.BOLD , 40);
                        Font f1  = new Font("Arial",Font.BOLD ,20);
                        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
                        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
                        Border h = BorderFactory.createTitledBorder(loweredbevel,":: Change Password ::", TitledBorder.CENTER , TitledBorder.TOP ,f,Color.red);
                        Border k = BorderFactory.createMatteBorder(0,10,0,0,Color.red);

                        lbaddress.setFont(f);
                        lbemail.setFont(f);
                        lbcontact.setFont(f);
                        
                        lbaddress.setForeground(c1);
                        lbemail.setForeground(c1);
                        lbcontact.setForeground(Color.red);
                }
}

