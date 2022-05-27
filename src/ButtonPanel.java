package MyPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;
import MyButton.*;
import Mode.*;
import observer_pattern.*;

public class ButtonPanel extends JPanel implements ActionListener, Subject
{	
	
	private java.util.List<observer> observers = new ArrayList<>();
	//introduce button
	private MyButton[] buttons;
	private int NUM_BUTTONS = 6;      //number of button
	private	int next_button_offset = 90; // next button position
	private	int button_size = 70;        // button size
	private String[] icons_path = {"../images/0.png","../images/1.png","../images/2.png","../images/3.png","../images/4.png","../images/5.png"};
	private	String[] button_name = {"select","association","generalization","composition","class","use"};
	private Mode[] modes ;
	private Integer currentModeID = -1; 
	public ButtonPanel(Point StartPoint,int WidthSize,int HeightSize, MyGraphic gg)
	{
		this.setLayout(null);
		this.setLocation(StartPoint.x, StartPoint.y);
		this.setSize(WidthSize, HeightSize);
		this.setBackground(Color.GRAY);
		modes = new Mode[]{new SelectedMode(gg), new AssociationMode(gg), new GeneralizationMode(gg), new CompositeMode(gg), new ClassMode(gg), new UseCaseMode(gg)};
		int[] address = {StartPoint.x, StartPoint.y,button_size,button_size};
		buttons = new MyButton[6];
		for(int i = 0; i < NUM_BUTTONS ; ++i)
		{
			buttons[i] = new MyButton(new ImageIcon(icons_path[i]), modes[i]);
			buttons[i].setBackground(Color.WHITE);
			buttons[i].setBounds(address[0],address[1],address[2],address[3]);
			buttons[i].setActionCommand("" + i);
			buttons[i].addActionListener(this);
			address[1] += next_button_offset;				
			this.add(buttons[i]);
		}
	}
	//Just set the button color 
	public void actionPerformed(ActionEvent e)
	{
		currentModeID = Integer.valueOf(e.getActionCommand().trim());
		this.clearButtonsColor();
		buttons[currentModeID].setColor(Color.BLACK);
		NotifyObserver();
		
	}
	public MyButton GetButtonInProgress()
	{
		return this.buttons[currentModeID];
	}
	private void clearButtonsColor()
	{
		for(MyButton bt : buttons)
		{
			bt.setColor(Color.WHITE);
		}
	}
	
	//observer pattern implement
	public void addObserver(observer ob)
	{
		observers.add(ob);
	}
	public void removeObserver(observer ob)
	{
		observers.remove(ob);
	}
	public void NotifyObserver()
	{
		for(observer o : observers)
		{
			o.updateButtonState();
		}
	}
	//observer pattern end
	
}