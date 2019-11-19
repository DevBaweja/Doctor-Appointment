import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class Doctor_ChangePassword extends JPanel implements ActionListener
    {

        private final JLabel lbuser;
        private final JLabel lboldpass;
        private final JLabel lbnewpass;
        private final JLabel lbconfpass;
        private final JTextField txuser;
        private final JPasswordField txoldpss;
        private final JPasswordField txnewpass;
        private final JPasswordField txconfpass;
        private final JPanel P;
        private final JButton btsubmit;
        private final JButton btrefresh;
        private final String userd;

        Doctor_ChangePassword()
            {
                userd = DoctorTabbedBar.userd;

                setVisible(true);
                setLayout(new BorderLayout());
                setSize(700, 700);

                lbuser = new JLabel("Doctor Username");
                lboldpass = new JLabel("Old Password");
                lbnewpass = new JLabel("New Password");
                lbconfpass = new JLabel("Confirm Password");

                txuser = new JTextField();
                txuser.setEnabled(false);
                txuser.setText(userd);
                txoldpss = new JPasswordField();
                txnewpass = new JPasswordField();
                txconfpass = new JPasswordField();

                btsubmit = new JButton("Submit");
                btrefresh = new JButton("Refresh");

                P = new JPanel();

                P.add(lbuser);
                P.add(txuser);

                P.add(lboldpass);
                P.add(txoldpss);

                P.add(lbnewpass);
                P.add(txnewpass);

                P.add(lbconfpass);
                P.add(txconfpass);

                P.add(btsubmit);
                P.add(btrefresh);

                P.setLayout(new GridLayout(5, 2, 20, 20));

                btsubmit.addActionListener(this);
                btrefresh.addActionListener(this);

                add(P);
                style();

            }

        private void style()

            {

                Color c1 = new Color(20, 110, 140);
                Font f = new Font("comic sans", Font.ITALIC + Font.BOLD, 40);
                Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
                Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                Border loweredbevel = BorderFactory.createLoweredBevelBorder();
                Border raisedbevel = BorderFactory.createRaisedBevelBorder();
                Border h = BorderFactory.createTitledBorder(loweredbevel, ":: Change Password ::", TitledBorder.CENTER, TitledBorder.TOP, f, Color.red);
                Border k = BorderFactory.createMatteBorder(0, 10, 0, 0, Color.red);
                P.setBorder(BorderFactory.createCompoundBorder(h, k));

                Font f1 = new Font("Arial", Font.BOLD, 20);
                lbuser.setFont(f1);
                lboldpass.setFont(f1);
                lbnewpass.setFont(f1);
                lbconfpass.setFont(f1);

                txuser.setFont(f1);
                txnewpass.setFont(f1);
                txoldpss.setFont(f1);
                txconfpass.setFont(f1);

                btsubmit.setFont(f1);
                btrefresh.setFont(f1);

                btsubmit.setBackground(Color.green);
                btrefresh.setBackground(Color.red);

                lbuser.setForeground(c1);
                lboldpass.setForeground(c1);
                lbnewpass.setForeground(c1);
                lbconfpass.setForeground(c1);

            }

        @Override
        public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                Object src = e.getSource();
                if (src == btsubmit)
                    {
                        String newpass = new String(txnewpass.getPassword());

                        if (!newpass.equals(txconfpass.getPassword()))
                            {
                                JOptionPane.showMessageDialog(null, "Both Password must be same", "Different Password", JOptionPane.ERROR_MESSAGE);
                                txnewpass.requestFocus();
                            } else
                            {

                                checkpass();
                            }

                    } else if (src == btrefresh)
                    {
                        txoldpss.setText("");
                        txnewpass.setText("");
                        txconfpass.setText("");
                        txoldpss.requestFocus();
                    }

            }

        private void checkpass()
            {
                try
                    {


                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("create database if not exists ManagementDb");
                        stmt.execute("Use ManagementDb");
                        stmt.executeUpdate("create table if not exists  DoctorTb( username varchar(100),"
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
                                + "spec varchar(100),"
                                + "qual varchar(100),"
                                + "lang varchar(100),"
                                + "hname varchar(100),"
                                + "hloc varchar(100),"
                                + "cname varchar(100),"
                                + "cloc varchar(100),"
                                + "primary key(username))");

                        PreparedStatement pres = con.prepareStatement("select count(*) from DoctorTb where username=? and password=?");
                        pres.setString(1, txuser.getText());
                        String olpass = new String(txoldpss.getPassword());
                        pres.setString(2, olpass);

                        ResultSet rs = pres.executeQuery();
                        rs.next();
                        int c = rs.getInt(1);

                        if (c == 1)
                            {
                                // want to change pass
                                int ans = JOptionPane.showConfirmDialog(null, "Do you really want to change your password", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if (ans == JOptionPane.YES_OPTION)
                                    {
                                        PreparedStatement prstmt = con.prepareStatement("update DoctorTb set password=? where username=?");
                                        String newpass = new String(txnewpass.getPassword());
                                        prstmt.setString(1, newpass);
                                        prstmt.setString(2, userd);
                                        prstmt.executeUpdate();
                                        JOptionPane.showMessageDialog(null, "Password Changed", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                                        txoldpss.setText("");
                                        txnewpass.setText("");
                                        txconfpass.setText("");
                                    }
                            } else
                            {
                                JOptionPane.showMessageDialog(null, "Password is Incorrect", "Invalid Password", JOptionPane.ERROR_MESSAGE);
                                txoldpss.requestFocus();
                            }
                        con.close();

                    } catch (ClassNotFoundException | SQLException ae)
                    {
                        // TODO Auto-generated catch block
                        ae.printStackTrace();
                    }

            }

    }

