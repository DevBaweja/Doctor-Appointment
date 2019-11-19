import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class PatientLoginHome extends JPanel implements ActionListener
    {

        public static String userp = "";

        private final JLabel lbpatient;
        private final JLabel lbpass;
        private final JTextField txpatient;
        private final JPasswordField txpass;
        private final JButton btsubmit;
        private final JButton btcancel;
        private final JButton btregister;
        private final JPanel p1;
        private final JPanel p2;
        private final JPanel p3;
        private final JPanel p4;
        private final JPanel p5;
        JPanel pmain;

        PatientLoginHome()
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

                lbpatient = new JLabel("Patient UserName");
                lbpass = new JLabel("Password");


                txpatient = new JTextField(20);
                txpass = new JPasswordField(20);


                btsubmit = new JButton("Submit");
                btcancel = new JButton("Cancel");
                btregister = new JButton("Create New Account");

                style();

                print();

                btsubmit.addActionListener(this);
                btcancel.addActionListener(this);
                btregister.addActionListener(this);
            }


        @Override
        public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                Object src = e.getSource();
                if (src == btcancel)
                    {
                        System.exit(-1);
                    } else if (src == btsubmit)
                    {


                        try
                            {


                                Class.forName("com.mysql.jdbc.Driver");
                                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
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

                                PreparedStatement pres = con.prepareStatement("select count(*) from PatientTb where username=? and password=?");
                                pres.setString(1, txpatient.getText());
                                String pass = new String(txpass.getPassword());
                                pres.setString(2, pass);

                                ResultSet rs = pres.executeQuery();
                                rs.next();
                                int c = rs.getInt(1);

                                if (c == 1)
                                    {

                                        JOptionPane.showMessageDialog(null, "You are now Login as " + txpatient.getText(), "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                                        // Here Patient full page will be displayed txpatient as username
                                        userp = txpatient.getText(); // will go in tabs of PatientTabbedBar

                                        // remove that entry
                                        refresh();
                                        new PatientTabbedBar();
                                    } else
                                    {
                                        JOptionPane.showMessageDialog(null, txpatient.getText() + " does not exists.", "Incorrect", JOptionPane.ERROR_MESSAGE);
                                        refresh();
                                        txpatient.requestFocus();
                                    }
                                con.close();

                            } catch (ClassNotFoundException | SQLException ae)
                            {
                                // TODO Auto-generated catch block
                                ae.printStackTrace();
                            }


                    } else if (src == btregister)
                    {
                        new PatientID1();
                    }
            }

        private void refresh()
            {
                txpatient.setText("");
                txpass.setText("");
            }


        private void style()
            {

                Font f = new Font("comic sans", Font.ITALIC + Font.BOLD, 40);
                Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
                Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                Border loweredbevel = BorderFactory.createLoweredBevelBorder();
                Border raisedbevel = BorderFactory.createRaisedBevelBorder();
                Border h = BorderFactory.createTitledBorder(loweredbevel, ":: PATIENT LOGIN ::", TitledBorder.CENTER, TitledBorder.TOP, f, Color.red);
                Border k = BorderFactory.createMatteBorder(0, 10, 0, 0, Color.red);
                setBorder(BorderFactory.createCompoundBorder(h, k));

                btsubmit.setBackground(Color.green);
                btcancel.setBackground(Color.red);
                btregister.setBackground(Color.green);

                //Foreground color
                Color c = new Color(20, 110, 140);
                lbpatient.setForeground(c);
                lbpass.setForeground(c);
                // Font
                Font f1 = new Font("Comic Sans MS", Font.BOLD, 30);
                lbpatient.setFont(f1);
                lbpass.setFont(f1);
                btsubmit.setFont(f1);
                btcancel.setFont(f1);
                btregister.setFont(f1);

                Font f2 = new Font("Arial", Font.BOLD, 20);
                txpatient.setFont(f2);
                txpass.setFont(f2);
            }

        private void print()
            {
                GridLayout g = new GridLayout(5, 1, 70, 70);
                setLayout(g);

                add(p1);

                p2.add(lbpatient);
                p2.add(txpatient);

                p3.add(lbpass);
                p3.add(txpass);

                p4.add(btsubmit);
                p4.add(btcancel);

                p5.add(btregister);

                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);

            }


    }
