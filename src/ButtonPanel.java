import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;


public class ButtonPanel extends JPanel implements ActionListener
{
	private int LastMode = -1;
	private int CurrentMode = -1;
	
	
	//introduce button
	private JButton[] buttons;
	private int NUM_BUTTONS = 6;      //number of button
	private	int next_button_offset = 90; // next button position
	private	int button_size = 70;        // button size
	private	int ori_button_pos = 20;     // original position
	private String[] icons_path = {"./images/0.png","./images/1.png","./images/2.png","./images/3.png","./images/4.png","./images/5.png"};
	private	String[] button_name = {"select","association","generalization","composition","class","use"};
	
	public ButtonPanel()
	{
		this.setLayout(null);
		int[] address = {ori_button_pos,ori_button_pos,button_size,button_size};
		buttons = new JButton[6];
		for(int i = 0; i < NUM_BUTTONS ; ++i)
		{
			buttons[i] = new JButton(new ImageIcon(icons_path[i]));
			buttons[i].setBackground(Color.WHITE);
			buttons[i].setBounds(address[0],address[1],address[2],address[3]);
			buttons[i].setActionCommand("" + i);
			buttons[i].addActionListener(this);
			address[1] += next_button_offset;				
			this.add(buttons[i]);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(LastMode >= 0)
		{
			LastMode = CurrentMode ;
			buttons[LastMode].setBackground(Color.WHITE);
		}
		switch(e.getActionCommand())
		{
			case "0":
				CurrentMode = 0;
				buttons[0].setBackground(Color.BLACK);
				break;
			case "1":
				CurrentMode = 1;
				buttons[1].setBackground(Color.BLACK);
				break;
			case "2":
				CurrentMode = 2;
				buttons[2].setBackground(Color.BLACK);
				break;
			case "3":
				CurrentMode = 3;
				buttons[3].setBackground(Color.BLACK);
				break;
			case "4":
				CurrentMode = 4;
				buttons[4].setBackground(Color.BLACK);
				break;
			case "5":
				CurrentMode = 5;
				buttons[5].setBackground(Color.BLACK);
				break;
		}
		if(LastMode == -1)
			LastMode = CurrentMode;
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(800, 600);
		frame.add(new ButtonPanel());
		frame.setVisible(true);
	}
	
	public int GetCurrentMode()
	{
		return this.CurrentMode;
	}
	
	
}