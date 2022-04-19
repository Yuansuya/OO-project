package MyPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import MyObserver.*;


public class ButtonPanel extends JPanel implements ActionListener, MyObserver
{
	private java.util.List<MySubscriber> subscribers = new ArrayList<>();
	
	
	private int LastMode = -1;
	private int CurrentMode = -1;
	
	//introduce button
	private JButton[] buttons;
	private int NUM_BUTTONS = 6;      //number of button
	private	int next_button_offset = 90; // next button position
	private	int button_size = 70;        // button size
	private String[] icons_path = {"../images/0.png","../images/1.png","../images/2.png","../images/3.png","../images/4.png","../images/5.png"};
	private	String[] button_name = {"select","association","generalization","composition","class","use"};
	
	public ButtonPanel(Point StartPoint,int WidthSize,int HeightSize)
	{
		this.setLayout(null);
		this.setLocation(StartPoint.x, StartPoint.y);
		this.setSize(WidthSize, HeightSize);
		this.setBackground(Color.GRAY);
		int[] address = {StartPoint.x, StartPoint.y,button_size,button_size};
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
		
		NotifySubscriber();
	}
	
	public int GetCurrentMode()
	{
		return this.CurrentMode;
	}
	
	
	/*ButtonState implementations begin*/
	public void addSubscriber(MySubscriber ob)
	{
		this.subscribers.add(ob);
	}
	public void removeSubscriber(MySubscriber ob)
	{
		this.subscribers.remove(ob);
	}
	public void NotifySubscriber()
	{
		for(MySubscriber ob : subscribers)
		{
			ob.updateButtonState();
		}
	}
	/*ButtonState implementations end*/
}