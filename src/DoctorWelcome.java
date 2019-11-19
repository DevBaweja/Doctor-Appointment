import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

class DoctorWelcome extends JPanel
    {

        private final JLabel lbwelcome;
        private JLabel lbpic;

        DoctorWelcome()
            {
                String userd = DoctorTabbedBar.userd;
                setVisible(true);
                JLabel lbimg = new JLabel(new ImageIcon("medical-appointment.jpg"));
                lbwelcome = new JLabel("Welcome to online Doctor Appointment, Doctor " + userd);

                JPanel p1 = new JPanel();
                JPanel p2 = new JPanel();
                JPanel p3 = new JPanel();

                p1.add(lbimg);
                p2.add(lbwelcome);


                add(p1, BorderLayout.CENTER);
                add(p2, BorderLayout.SOUTH);
                add(p3, BorderLayout.NORTH);

                style();
            }

        private void style()
            {
                Color c1 = new Color(20, 110, 140);
                Font f1 = new Font("comic sans", Font.ITALIC + Font.BOLD, 20);
                Font f2 = new Font("comic sans", Font.ITALIC + Font.BOLD, 40);
                Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
                Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                Border loweredbevel = BorderFactory.createLoweredBevelBorder();
                Border raisedbevel = BorderFactory.createRaisedBevelBorder();
                Border h = BorderFactory.createTitledBorder(loweredbevel, ":: ABOUT ME ::", TitledBorder.CENTER, TitledBorder.TOP, f2, Color.red);
                Border k = BorderFactory.createMatteBorder(0, 10, 0, 0, Color.red);
                //p1.setBorder(BorderFactory.createCompoundBorder(h, k));

                lbwelcome.setFont(f2);


            }
    }


