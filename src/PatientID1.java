
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PatientID1 extends JDialog implements ActionListener,ItemListener,FocusListener,KeyListener,ChangeListener,DocumentListener{
              
              
              JLabel lbhead;
              JLabel lbuser,lbpass;
              JLabel lbgender,lbdob,lbaddress;
              JLabel lbmobile,lbconfirmpass,lbpastrecord,lbdisease;
              JLabel lbemail,lbMaterialstatus,lblocation;
              JLabel lbcity,lbstate;
              JButton btregister,btrefresh,btclose;
              JTextField txuser,txmobile,txpassrecords,txemail,txlocation;
              JPasswordField txpass,txconfirmpass;
              JComboBox cbcity,cbstate;
              JTextArea taddress;
              JPanel P;
              JPanel Pgender,Pdob,Pbutton1,Pbutton2,Pdisease;
              JPanel Pdis[];
              JCheckBox ch[];
              JCheckBox chshowpass;
              JPanel Pbutton,Pclass ;
              JPanel Pemail,PMaterial,Plocation,Pcity,Pstate,Ppass;
              JButton bnext,bprevious;
              JRadioButton ckmale,ckfemale,ckhidden;
              ButtonGroup gr;
              JComboBox chyy,chmm,chdd;
              JComboBox cMs;
              CardLayout CLO;
              String trial,trial1;     
              String place ;
              public PatientID1()
              {
                           
                           setVisible(true);
                           setSize(new Dimension(1400, 1000));
                           setPreferredSize(new Dimension(1400, 1000));
                           trial ="";
                           trial1 ="";
                           place =  "Example123@gmail.com";
                           String need = "*";  
                         
                           lbuser = new JLabel("User Name"+need);
                           lbpass = new JLabel("New Password"+need);
                           lbconfirmpass = new JLabel("Confirm New Password"+need);
                           lbemail = new JLabel("Email ID"+need);
                           lbMaterialstatus= new JLabel("Material Status"+need);
                           lblocation= new JLabel("Location"+need);
                           lbcity= new JLabel("City"+need);
                           lbstate=new JLabel("State"+need);
                           lbdisease = new JLabel("Related Disease");
                           lbgender = new JLabel("Gender"+need);
                           
                           gr = new ButtonGroup();
                           ckmale = new JRadioButton("Male");
                           ckfemale = new JRadioButton("Female");
                           ckhidden = new JRadioButton("Hidden"); // dont add this
                           
                           gr.add(ckmale);
                           gr.add(ckfemale);
                           gr.add(ckhidden);
                           
                           
                           lbdob =  new JLabel("DOB"+need);
                           
                           chyy = new JComboBox();
                           chmm = new JComboBox();
                           chdd = new JComboBox();
                           
                           cMs=new JComboBox();
                           cMs.addItem("Select status");
                           cMs.addItem("Single");
                           cMs.addItem("Married");
                           
                           lbaddress = new JLabel("Address"+need);
                           lbmobile = new JLabel("Personal Number"+need);

              
                           lbpastrecord = new JLabel("Past Records");
                           
                           btregister = new JButton("Register");
                           btrefresh= new JButton("Refresh");
                           btclose = new JButton("Close");
                           bnext = new JButton("Next");
                           bprevious = new JButton("Previous");
                           
                           txuser = new JTextField();   
                           txpass = new JPasswordField(200);
                           //txpass.setEchoChar('*');
                           chshowpass = new JCheckBox("Show Password");
                           txconfirmpass = new JPasswordField();
                           //  txconfirmpass.setEnabled(false);
                           //  no need of txconfirmpass.setEchoChar('*');
                           txmobile = new JTextField(); 
                           txpassrecords = new JTextField();
                           txemail = new JTextField();
                           txemail.setText(place);
                           txlocation= new JTextField();
                           cbcity= new JComboBox();
                           cbcity.addItem("Select City"); 
                           cbstate= new JComboBox();
                           cbstate.addItem("Select State");
          
                            fillcbstate();
                               
                           taddress = new JTextArea();
                           P = new JPanel();
              
                           print();
                           
                           
                           chyy.addItemListener(this);
                           chmm.addItemListener(this);
                           cbstate.addItemListener(this);
                           chshowpass.addItemListener(this);
                           
                           btregister.addActionListener(this);
                           btrefresh.addActionListener(this);
                           btclose.addActionListener(this);
                           bnext.addActionListener(this);
                           bprevious.addActionListener(this);
                           
                           txpass.addFocusListener(this);
                           txconfirmpass.addFocusListener(this);
                           txuser.addFocusListener(this);
                           txemail.addFocusListener(this);
                           txmobile.addKeyListener(this);
                           txuser.addKeyListener(this);
                        //  txmobile.getDocument().addDocumentListener(this);
              }
              
              public void fillcbstate()
              {
                              try {
                                                      
                                                     
                                                      Class.forName("com.mysql.jdbc.Driver");
                                                      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
                                                      Statement stmt = con.createStatement();
                                                      stmt.executeUpdate("create database if not exists state");
                                                      stmt.execute("Use state");
                                                      // table exist already
                                                      
                                                      ResultSet rs = stmt.executeQuery("select distinct city_state from cities"); 
                                                      
                                                      while(rs.next())
                                                      {
                                                                    cbstate.addItem(""+rs.getString("city_state"));
                                                      }
                                                      con.close(); 
                                                                                  
                                         } catch (ClassNotFoundException ae) {
                                                      // TODO Auto-generated catch block
                                                      ae.printStackTrace();
                                         } catch (SQLException ae) {
                                                      // TODO Auto-generated catch block
                                                      ae.printStackTrace();
                                         }
              }
              
              public void print()
              {
                           chyy.addItem("Year");
                           for(int i=1981;i<=2000;i++)
                           chyy.addItem(i+"");
                           
                           chmm.addItem("Month");
                           for(int i=1;i<=12;i++)
                                         chmm.addItem(i+"");
                           
                           chdd.addItem("Date");
                           
                           // according to month and year date must change
              
                           
                           
                           Pgender = new JPanel();
                           Pgender.setLayout(new GridLayout(1, 2));
                           Pdob = new JPanel();
                           Pdob.setLayout(new GridLayout(1, 3));
                           Pbutton1 = new JPanel();
                           Pbutton1.setLayout(new GridLayout(1,1));
                           Pbutton2 = new JPanel();
                           Pbutton2.setLayout(new GridLayout(1, 2));
                           
                           Pgender.add(ckmale);
                           Pgender.add(ckfemale);
                           // dont add this hidden check box
                           
                           Pdob.add(chyy);
                           Pdob.add(chmm);
                           Pdob.add(chdd);
                           
                           
                           Pbutton1.add(btregister);
                           Pbutton2.add(btrefresh);
                           Pbutton2.add(btclose);
              
                           
                           Pcity = new JPanel();
                           Pcity.setLayout(new GridLayout(1,2));
                           Pcity.add(lbcity);
                           Pcity.add(cbcity);
                           
                           Pstate = new JPanel();
                           Pstate.setLayout(new GridLayout(1,2));
                           Pstate.add(lbstate);
                           Pstate.add(cbstate);
                           
                           Ppass = new JPanel();
                           Ppass.setLayout(new GridLayout(1, 2));
                           Ppass.add(txpass);
                           Ppass.add(chshowpass);
                                        
                           Pdis = new JPanel[3];
                           ch = new JCheckBox[9];
                           
                           for(int i=0;i<Pdis.length;i++)
                           {
                               Pdis[i] = new JPanel();
                               Pdis[i].setLayout(new GridLayout(1, 3));
                           }
       String disname[] = {"Diabetes","High Blood Pressure","Respiratory Diseases","Heart Diseases","Digestive Diseases","High Cholesterol ","Stroke","Cancer","Alzheimer’s disease"};

       for(int i=0;i<ch.length;i++)
    	   ch[i] = new JCheckBox(disname[i]);
       
				             Pdis[0].add(ch[0]);
				             Pdis[0].add(ch[1]);
				             Pdis[0].add(ch[2]);
				                                        
                             Pdis[1].add(ch[3]);
                             Pdis[1].add(ch[4]);
                             Pdis[1].add(ch[5]);

                             Pdis[2].add(ch[6]);
                             Pdis[2].add(ch[7]);
                             Pdis[2].add(ch[8]);
                                        
                                        
                           
                           Pdisease = new JPanel();
                           Pdisease.setLayout(new BorderLayout());
                           
                           Pbutton = new JPanel();
                           Pbutton.add(bnext);
                           Pbutton.add(bprevious);
                           Pdisease.add(Pbutton,BorderLayout.NORTH);
                           
                           Pclass = new JPanel();
                           CLO = new CardLayout();
                           Pclass.setLayout(CLO);
                           Pclass.add("Type1", Pdis[0]); // adding panels
                           Pclass.add("Type2", Pdis[1]);
                           Pclass.add("Type3",Pdis[2]);
                           Pdisease.add(Pclass,BorderLayout.CENTER);
                           
                           
                          P.setLayout(new GridLayout(14,2));
                           
                           P.add(lbuser);
                           P.add(txuser);
                           P.add(lbpass);
                           P.add(Ppass);
                           P.add(lbconfirmpass);
                           P.add(txconfirmpass);
                           P.add(lbmobile);
                           P.add(txmobile);
                           P.add(lbemail);
                           P.add(txemail);
                           P.add(lbgender);
                           P.add(Pgender);
                           P.add(lbMaterialstatus);
                           P.add(cMs);
                           P.add(lbdob);
                           P.add(Pdob);
                           P.add(lbaddress);
                           P.add(taddress);
                           P.add(lblocation);
                           P.add(txlocation);
                           P.add(Pstate);
                           P.add(Pcity);
                           P.add(lbdisease);
                           P.add(Pdisease);
                           P.add(lbpastrecord);
                           P.add(txpassrecords);
                           P.add(Pbutton1);
                           P.add(Pbutton2);
                           
                           setLayout(new BorderLayout());
                           add(P,BorderLayout.CENTER); // default is border layout
                           Font f  = new Font("comic sans",Font.ITALIC+Font.BOLD , 30); 
                           Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
                           Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
                           Border loweredbevel = BorderFactory.createLoweredBevelBorder();
                           Border raisedbevel = BorderFactory.createRaisedBevelBorder();
                           Border h = BorderFactory.createTitledBorder(loweredbevel,":: PATIENT SIGNUP ::", TitledBorder.CENTER , TitledBorder.TOP ,f,Color.red);
                           Border k = BorderFactory.createMatteBorder(0,10,0,0,Color.red);
                           P.setBorder(BorderFactory.createCompoundBorder(h, k));
                           P.setSize(new Dimension(800,800));
                           P.setPreferredSize(new Dimension(800,800));
              }

              @Override
              public void itemStateChanged(ItemEvent ie) {
                           
                           
                           Object src = ie.getSource();
                          
                           if(src == cbstate)
                           {
                             cbcity.removeAllItems();
                                                      cbcity.addItem("Select City");
                                        
                                                      
                                        String s = cbstate.getSelectedItem().toString();
                                        
                                           try {
                                                                    
                                                                   
                                                                    Class.forName("com.mysql.jdbc.Driver");
                                                                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
                                                                    Statement stmt = con.createStatement();
                                                                    stmt.executeUpdate("create database if not exists state");
                                                                    stmt.execute("Use state");
                                                                    // table exist already
                                                                    
                                                                    PreparedStatement pstmt = con.prepareStatement("select distinct city_name from cities where city_state=?");
                                                                    pstmt.setString(1, s);
                                                                    ResultSet rs = pstmt.executeQuery(); 
                                                                    
                                                                    while(rs.next())
                                                                    {
                                                                                 cbcity.addItem(""+rs.getString("city_name"));
                                                                    }
                                                                    con.close(); 
                                                                                               
                                                      } catch (ClassNotFoundException ae) {
                                                                    // TODO Auto-generated catch block
                                                                    ae.printStackTrace();
                                                      } catch (SQLException ae) {
                                                                    // TODO Auto-generated catch block
                                                                    ae.printStackTrace();
                                                      }
                           }
                          
              // yymmdd 
                          else 
                            if(chyy.getSelectedIndex() != 0 && chmm.getSelectedIndex() != 0)
                           {
                           
                                         
                            int yy = Integer.parseInt( chyy.getSelectedItem().toString() );
                           int mm = Integer.parseInt( chmm.getSelectedItem().toString() );
                           
                            // every time it come here we will remove previous entered item 
                            // and then it will add automatically
                           
                          // for(int i=1;i<chdd.getItemCount();i++)
                          // chdd.remove(1);
                                         
                           chdd.removeAllItems();
                           chdd.addItem("Date");
                           
                            int days = 0;
                           
                            if(mm == 2)
                                         {
                                         if(yy%4 ==0)
                                         
                                                        days = 29;
                                         else
                                                       days = 28;
                                         }
                           else 
                                                      { 
                                                                    if(mm == 4 || mm == 6 || mm == 9 || mm == 11)
                                                                                 days = 30;
                                                                   else 
                                                                                  days = 31;
                                                     }
              
                                         for(int i=1 ; i<=days ;i++)
                                                       chdd.addItem(i+"");
              
                           }
                           
                           if(chshowpass.isSelected())
                           {
                             txpass.setEchoChar((char)0);
                           }
                           else
                           {
                             txpass.setEchoChar('*');
                           }

}

              @Override
              public void actionPerformed(ActionEvent ae) {
                           
                           Object src = ae.getSource();
                           // in refresh radio JButton will remain as it
                           // making hidden radio JButton
                           
                           if(src == btregister)
                           {
                              
                                        if( txuser.getText().equals("") 
	                                                                      || txpass.getText().equals("") 
	                                                                      || txconfirmpass.getText().equals("")
	                                                                      || txemail.getText().equals("") 
	                                                                      || txmobile.getText().equals("")
				                                                        || !(ckmale.isSelected() || ckfemale.isSelected())
				                                                        || txlocation.getText().equals("")
				                                                        ||  taddress.getText().equals("")
				                                                        || cMs.getSelectedIndex() == 0 
				                                                        || chyy.getSelectedIndex() == 0
				                                                        || chmm.getSelectedIndex() == 0
				                                                        || chdd.getSelectedIndex() == 0
				                                                        || cbstate.getSelectedIndex() == 0
				                                                        || cbcity.getSelectedIndex() == 0
				                                                        || txmobile.getText().length() != 10
				                                                       
                                                        )
                                        	// no related disease and past records
                             {
                                           JOptionPane.showMessageDialog(null, "Kindly fill all the required entries of Form", "Unfilled Form", JOptionPane.ERROR_MESSAGE);
                             }

                             else 
                             	{  
                            	   try {
                       				
                       				
           							Class.forName("com.mysql.jdbc.Driver");
           							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
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
           							
           							PreparedStatement pres = con.prepareStatement("select count(*) from PatientTb where username=?" );
           							pres.setString(1, txuser.getText());
           							
           							ResultSet rs = pres.executeQuery();
           							rs.next();
           							int c = rs.getInt(1);
           							
           							if(c==0)
           							{
           								PreparedStatement pre = con.prepareStatement("insert into PatientTb (username,password,email,phone,gender,material,dob,location,address,city,state,disease,past) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
           			      				
          								pre.setString(1,txuser.getText());
          								pre.setString(2, txpass.getText());
          								pre.setString(3, txemail.getText());
          								pre.setString(4, txmobile.getText());
          								
          								if(ckmale.isSelected())
          								pre.setInt(5,1);
          								else 
          								if(ckfemale.isSelected())
          								pre.setInt(5,0);
          								// 1 for male
          								// 0 for female
          								
          								pre.setInt(6,cMs.getSelectedIndex());
          								// 1 for single
          								// 2 for married 
          	
          								
          								int yy = Integer.parseInt(chyy.getSelectedItem().toString());
          								int mm = Integer.parseInt(chmm.getSelectedItem().toString());
          								int dd = Integer.parseInt(chdd.getSelectedItem().toString());
          								Date dob = new Date(yy-1900, mm-1 , dd);
          										/*
          										 * year the year minus 1900; must be 0 to 8099. (Note that8099 is 9999 minus 1900.)
          										 * month 0 to 11
          										 * day 1 to 31
          										 */
          								pre.setDate(7, dob);
          						
          								pre.setString(8, txlocation.getText());
          								pre.setString(9, taddress.getText());
          								pre.setString(10, cbcity.getSelectedItem().toString());
          								pre.setString(11, cbstate.getSelectedItem().toString());
          								
          								
          								String res ="";
          								for(int i=0;i<ch.length;i++)
          								{
              								if(ch[i].isSelected())
              									res += "1";
              								else res+="0";
          								}
          								pre.setString(12, res);
          								pre.setString(13, txpassrecords.getText());
          							

          								pre.executeUpdate();
          								System.out.println("Insertion Sucessfully");
          								
          								
          								//this.dispose();
          								// using jdialog box
           							}
           							else if(c==1)
           							{
           	                     	   JOptionPane.showMessageDialog(null,txuser.getText()+" already exists.Try other username","Incorrect Username",JOptionPane.ERROR_MESSAGE);
           	                     	   txuser.requestFocus();
           							}
           							
           							con.close(); 
           									
           						} catch (ClassNotFoundException e) {
           							// TODO Auto-generated catch block
           							e.printStackTrace();
           						} catch (SQLException e) {
           							// TODO Auto-generated catch block
           							e.printStackTrace();
           						}
                 	   }
                   }
                  else if(src == btrefresh)
                   {
                                  txuser.setText("");
                                  txpass.setText("");
                                  
                                  //Radio JButton
                                  ckhidden.setSelected(true);
                                  // if it is true then other will automatically will get off
                                  
                                  chyy.setSelectedIndex(0);
                                  chmm.setSelectedIndex(0);
                                  chdd.setSelectedIndex(0);
                                  taddress.setText("");
                                  txconfirmpass.setText("");
                                  
                                  
                                  for(int i=0;i<ch.length;i++)
                                  ch[i].setSelected(false);
                                  
                                  chshowpass.setSelected(false);
                                  txmobile.setText("");
                                  txpassrecords.setText("");
                                  cbcity.setSelectedIndex(0);
                                  cbstate.setSelectedIndex(0);
                                  txlocation.setText("");
                                  txemail.setText("Example123@gmail.com");
                                  cMs.setSelectedIndex(0);
                                  
                    }
                    else if(src == bnext)
                    {
                                  CLO.next(Pclass);
                    }
                    else if(src == bprevious)
                    {
                                  CLO.previous(Pclass);
                    } else if(src == btclose)
                    {
                 	   this.dispose();
                    }
                     
       }
       @Override
       public void focusGained(FocusEvent e) {
                    
                    Object src = e.getSource();
                    
             
                     if(src == txemail)
                    {
                    	if(txemail.getText().equalsIgnoreCase(place))
                    	txemail.setText("");
                    }
       }

       @Override
       public void focusLost(FocusEvent e) {
                    Object src = e.getSource();
                    
                    if( src == txconfirmpass )
                    {
                                  int b = (txpass.getText()).compareTo( txconfirmpass.getText());
                                  // b will be zero if they are same
                                  if( b != 0 )
                                  {
                                 	 JOptionPane.showMessageDialog(null, "Both Passwords Must Be Same","Incorrect Password",JOptionPane.ERROR_MESSAGE);
                                 	 chshowpass.setSelected(true);;
                                 	 txconfirmpass.requestFocus();
                                  }
                                  else 
                                	  chshowpass.setSelected(false);
                    } 
                    else if(src == txuser)
                    {
                    		checkuser();
                    		
                    }
                    else if(src == txpass)
                    {
                    	if(!txpass.getText().equals(txconfirmpass.getText()))
                    	txconfirmpass.requestFocus();
                    }
       }

		public void checkuser()
		{
      	   try {
				
				
							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
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
							
							PreparedStatement pres = con.prepareStatement("select count(*) from PatientTb where username=?" );
							pres.setString(1, txuser.getText());
							
							ResultSet rs = pres.executeQuery();
							rs.next();
							int c = rs.getInt(1);
							
							if(c!=0)
							{
	                     	   JOptionPane.showMessageDialog(null,txuser.getText()+" already exists.Try other username","Incorrect Username",JOptionPane.ERROR_MESSAGE);
	                     	   txuser.requestFocus();
							}
							
							con.close(); 
									
						} catch (ClassNotFoundException ae) {
							// TODO Auto-generated catch block
							ae.printStackTrace();
						} catch (SQLException ae) {
							// TODO Auto-generated catch block
							ae.printStackTrace();
						}
		}
		 @Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			Object src = e.getSource();
			//System.out.println(code);
			
			if(src == txmobile)
			{
			if(txmobile.getText().length() <=10)
			{
						int code = e.getKeyCode();	
						if(!(code >= KeyEvent.VK_0 && code <= KeyEvent.VK_9) 
								&& code != KeyEvent.VK_BACK_SPACE 
								&& code != KeyEvent.VK_LEFT 
								&& code != KeyEvent.VK_RIGHT 
								&& code != KeyEvent.VK_DELETE)
							{	
								//e.consume();
								txmobile.setText(trial);
									getToolkit().beep();
							
							}
						trial=txmobile.getText();
						}
					else 
					{
						txmobile.setText(trial);
						getToolkit().beep();
						trial=txmobile.getText();
					}
			}
			else if(src == txuser)
			{
				int code = e.getKeyCode();	
				if(!(code >= KeyEvent.VK_A && code <= KeyEvent.VK_Z)
						&& code != KeyEvent.VK_BACK_SPACE 
						&& code != KeyEvent.VK_LEFT 
						&& code != KeyEvent.VK_RIGHT 
						&& code != KeyEvent.VK_DELETE)
					{	
						//e.consume();
						txuser.setText(trial1);
							getToolkit().beep();
					
					}
				trial1=txuser.getText();
			}
}
			
			
		

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
	
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}

                     
}





