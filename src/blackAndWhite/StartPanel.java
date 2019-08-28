package blackAndWhite;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel{
	private JLabel title;
	private JButton butStart;
	private JLabel Start;
	
	public StartPanel() {
		setSize(1200, 800);
		setLocation(0, 0);
		setVisible(true);
		setLayout(null);
		
		title = new JLabel("°yZA CHESS°z");
		title.setSize(1200, 200);
		title.setLocation(0, 100);
		add(title);
		title.setFont(new Font("º–∑¢≈È", Font.BOLD, 165));
		title.setForeground(Color.white);
		
		butStart = new JButton("GameStart");
		butStart.setSize(200, 100);
		butStart.setLocation(500, 360);
		add(butStart);
		
		ImageIcon startIcon = new ImageIcon("res/pic/back.jpg");
		Start = new JLabel(startIcon);
		Start.setSize(1200, 800);
		Start.setLocation(0, 0);
	    add(Start);
		Start.setVisible(true);
	}
	
	public JButton getStartButton() {
		return butStart;
	}
	

	
}
