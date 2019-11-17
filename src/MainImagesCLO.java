
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


class MainImagesCLO extends JPanel implements ActionListener{

		private JPanel p1;
	private JPanel pmain;
		private JButton bnext;
	private JButton bprev;
		private CardLayout clo;
		
		private JLabel lb1;
	JLabel lb2;
	private JLabel lb3;
		private JPanel Pimage1;
	private JPanel Pimage2;
	private JPanel Pimage3;
		
		
		public MainImagesCLO()
		{
			p1=new JPanel();
			clo=new CardLayout();
			bnext = new JButton("-->");
			bprev=new JButton("<--");
			bnext.addActionListener(this);
			bprev.addActionListener(this);
			
			//body
			setLayout(new BorderLayout());
			
			// p2 = new JPanel();
			//p2.add(new JLabel("ONLINE DOCTOR APPOINTMENT"));
			//add(p2,BorderLayout.NORTH);
			
			p1.add(bprev);
			p1.add(bnext);
			add(p1,BorderLayout.SOUTH);
			
// if panel in the card layout are not lenghty then do need to create another panel class
// make panel in the function itself and use it
			
				// For CardLayout
				Pimage1 = new JPanel();
				lb1 = new JLabel();
				lb1.setIcon(new ImageIcon("medical-appointment.png"));

				Pimage1.add(lb1);
				
				
				Pimage2 = new JPanel();
				JLabel lb2;
				lb2=new JLabel();
				lb2.setIcon(new ImageIcon("calendar.jpg"));
					
				Pimage2.add(lb2);
				
				Pimage3 = new JPanel();
				lb3=new JLabel();
				lb3.setIcon(new ImageIcon("contact-us.jpg"));
				
				Pimage3.add(lb3);
				
				
				
				pmain=new JPanel();
				pmain.setSize(new Dimension(200,200));
				pmain.setPreferredSize(new Dimension(200, 200));
				
				pmain.setLayout(clo);
				pmain.setBackground(Color.DARK_GRAY);
				pmain.add("image1", Pimage1);
				pmain.add("image2", Pimage2);
				pmain.add("image3", Pimage3);
				
				
				add(pmain,BorderLayout.CENTER);

			

		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			Object src=ae.getSource();
			
			if(src==bnext)
			{
				clo.next(pmain);
			}
			else if(src==bprev)
			{
				clo.previous(pmain);
			}
			
		}
	}

