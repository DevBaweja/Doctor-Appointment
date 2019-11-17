import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FeedBackStars extends JApplet implements ActionListener{

	private JButton[] bt;
	private JPanel p;
	public void init()
	{
		setVisible(true);
		setSize(700,700);
		setLayout(new BorderLayout());
		
		p = new JPanel();
		p.setLayout(new GridLayout(1,5));
		
		bt = new JButton[5]; // creating ref
		for(int i=0 ; i<bt.length;i++)
		{
			
			bt[i] = new JButton(new ImageIcon("download.jpg")); // Memory Allocation
			// bt[i] = new Button( Integer.toString(i+1));
		}
		
		for(int i=0;i< bt.length;i++)
		{
			bt[i].setRolloverIcon(new ImageIcon("download.jpg"));
			p.add(bt[i]);
		}
		
		add(p,BorderLayout.NORTH);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
