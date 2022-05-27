package MyMenuBar;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import observer_pattern.*;
import java.util.*;
import IBarBehavior.*;
public class MyMenuBar extends MenuBar implements ActionListener, Subject
{
	private int NUM_ITMES = 3;
	private String MenuName = "Edit";
	private String[] ItemName  = {"group","ungroup", "ChangeObjName"};
	private java.util.List<observer> observers = new ArrayList<>();
	private MyMenuItem[] items = new MyMenuItem[NUM_ITMES];
	private Integer currentModeID = -1; 
	private IBarBehavior[] behaviors ;
	
	public MyMenuBar()
	{
		Menu menuFile = new Menu(MenuName);
		behaviors = new IBarBehavior[]{new GroupBehavior(), new UnGroupBehavior(), new SetNameBehavior()};
		for(int i = 0 ; i < NUM_ITMES ; i++)
		{
			items[i] = new MyMenuItem(ItemName[i]);
			items[i].setActionCommand("" + i);
			items[i].addActionListener(this);
			items[i].setBehavior(behaviors[i]);
			menuFile.add(items[i]);
		}
		this.add(menuFile); 
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		currentModeID = Integer.valueOf(e.getActionCommand().trim());
		NotifyObserver();
	}
	
	public MyMenuItem GetMenuItemInProgress()
	{
		return this.items[currentModeID];
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