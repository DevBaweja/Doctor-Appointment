import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PatientWelcome extends JPanel {

                JPanel p1,p2,p3;
                JLabel lbimg,lbwelcome,lbpic;
                String userp;
                
                public  PatientWelcome()
                {
                	 			userp = PatientTabbedBar.userp;
                                lbimg=new JLabel(new ImageIcon("medical-appointment.jpg"));
                                lbwelcome = new JLabel("Welcome to online Doctor Appointment, Patient "+userp);
                                
                                p1=new JPanel();
                                p2=new JPanel();
                                p3=new JPanel();
                                
                                p1.add(lbimg);
                                p2.add(lbwelcome);
                                
                                
                                add(p1,BorderLayout.CENTER);
                                add(p2,BorderLayout.SOUTH);
                                add(p3,BorderLayout.NORTH);
                                
                                style();
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
        //p1.setBorder(BorderFactory.createCompoundBorder(h, k));

        lbwelcome.setFont(f2);
                                

                }
}





