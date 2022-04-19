package MyMenuBar;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import MyObserver.*;
import java.util.*;
public class MyMenuBar extends MenuBar implements ActionListener, MyObserver
{
	private int NUM_ITMES = 3;
	private String MenuName = "Edit";
	private String[] ItemName  = {"group","ungroup", "ChangeObjName"};
	private java.util.List<MySubscriber> subscribers = new ArrayList<>();
	private int CurrentBarMode = -1;
	public MyMenuBar()
	{
		Menu menuFile = new Menu(MenuName);
		MenuItem[] items = new MenuItem[NUM_ITMES];
		for(int i = 0 ; i < NUM_ITMES ; i++)
		{
			items[i] = new MenuItem(ItemName[i]);
			items[i].setActionCommand("" + i);
			items[i].addActionListener(this);
			menuFile.add(items[i]);
		}
		this.add(menuFile); 
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case "0":
				CurrentBarMode =0;
				NotifySubscriber();
				break;
			case "1":
				CurrentBarMode =1;
				NotifySubscriber();
				break;
				
			case "2":
				CurrentBarMode =2;
				NotifySubscriber();
				break;
		}
	}
	
	public int GetCurrentMode()
	{
		return this.CurrentBarMode;
	}
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
			ob.updateBarState();
		}
	}
}