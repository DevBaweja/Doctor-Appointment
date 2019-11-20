import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AdminLoginHome extends JPanel implements ActionListener
    {

        public static String usera = "";

        private final JLabel lbadmin;
        private final JLabel lbpass;
        private final JTextField txadmin;
        private final JPasswordField txpass;
        private final JButton btsubmit;
        private final JButton btcancel;
        private final JPanel p1;
        private final JPanel p2;
        private final JPanel p3;
        private final JPanel p4;
        private final JPanel p5;
        private JButton btregister;

        AdminLoginHome()
            {
                setVisible(true);
                setSize(800, 800);
                p1 = new JPanel();
                p2 = new JPanel();
                p2.setLayout(new GridLayout(1, 2));
                p3 = new JPanel();
                p3.setLayout(new GridLayout(1, 2));
                p4 = new JPanel();
                p4.setLayout(new GridLayout(1, 2));
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
        public void actionPerformed(ActionEvent e)
            {

                Object src = e.getSource();
                if (src == btcancel)
                    {
                        System.exit(-1);
                    } else if (src == btsubmit)
                    {
                        String pass = new String(txpass.getPassword());
                        if (txadmin.getText().equals("admin") && pass.equals("admin"))
                            {
                                JOptionPane.showMessageDialog(null, "You are now Login as " + txadmin.getText(), "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                                refresh();
                                new AdminTabbedBar();
                            } else
                            {
                                JOptionPane.showMessageDialog(null, txadmin.getText() + " does not exists.", "Incorrect Username or Password", JOptionPane.ERROR_MESSAGE);
                                refresh();
                                txadmin.requestFocus();
                            }
                    } else if (src == btregister)
                    {
                        // If multiple admin is being placed in database
                    }
            }

        private void refresh()
            {
                txadmin.setText("");
                txpass.setText("");
            }

        private void style()
            {

                Font f = new Font("comic sans", Font.ITALIC + Font.BOLD, 40);
                Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
                Border loweredbevel = BorderFactory.createLoweredBevelBorder();
                Border h = BorderFactory.createTitledBorder(loweredbevel, ":: ADMIN LOGIN ::", TitledBorder.CENTER, TitledBorder.TOP, f, Color.red);
                Border k = BorderFactory.createMatteBorder(0, 10, 0, 0, Color.red);
                setBorder(BorderFactory.createCompoundBorder(h, k));

                btsubmit.setBackground(Color.green);
                btcancel.setBackground(Color.red);
                // btregister.setBackground(Color.green);

                //Foreground color
                Color c = new Color(20, 110, 140);
                lbadmin.setForeground(c);
                lbpass.setForeground(c);
                // Font
                Font f1 = new Font("Comic Sans MS", Font.BOLD, 30);
                lbadmin.setFont(f1);
                lbpass.setFont(f1);
                btsubmit.setFont(f1);
                btcancel.setFont(f1);


                Font f2 = new Font("Arial", Font.BOLD, 20);
                txadmin.setFont(f2);
                txpass.setFont(f2);
            }

        private void print()
            {
                GridLayout g = new GridLayout(5, 1, 70, 70);
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
