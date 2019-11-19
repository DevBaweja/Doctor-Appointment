import javax.swing.*;
import java.awt.*;

public class DemoMain extends JApplet
    {

        private JTabbedPane jtp;

        public void init()
            {
                jtp = new JTabbedPane();
                jtp.setBackground(Color.blue);
                setSize(new Dimension(1000, 800));
                setSize(1000, 800);
                setPreferredSize(new Dimension(1000, 800));

                //  jtp.addTab("Login", new MainImagesCLO());
                jtp.addTab("Admin Login", new AdminLoginHome());
                jtp.addTab("Patient Login", new PatientLoginHome());
                jtp.addTab("Doctor Login", new DoctorLoginHome());
                //	jtp.addTab("Contact Us", new ContactUs());


                style();
                add(jtp);
            }

        private void style()
            {
                jtp.setBackground(Color.black);
                jtp.setForeground(Color.white);
            }
    }
