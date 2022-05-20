package MyMenuBar;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import observer_pattern.*;
import java.util.*;
public class MyMenuBar extends MenuBar implements ActionListener, Subject
{
	private int NUM_ITMES = 3;
	private String MenuName = "Edit";
	private String[] ItemName  = {"group","ungroup", "ChangeObjName"};
	private java.util.List<observer> observers = new ArrayList<>();
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
				NotifyObserver();
				break;
			case "1":
				CurrentBarMode =1;
				NotifyObserver();
				break;
				
			case "2":
				CurrentBarMode =2;
				NotifyObserver();
				break;
		}
	}
	
	public int GetCurrentMode()
	{
		return this.CurrentBarMode;
	}
	public void addObserver(observer ob)
	{
		this.observers.add(ob);
	}
	public void removeObserver(observer ob)
	{
		this.observers.remove(ob);
	}
	
	public void NotifyObserver()
	{
		for(observer ob : observers)
		{
			ob.updateBarState();
		}
	}
}