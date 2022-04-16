package MyMenuBar;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class MyMenuBar extends MenuBar implements ActionListener
{
	private int NUM_ITMES = 3;
	private String MenuName = "Edit";
	private String[] ItemName  = {"group","ungroup", "ChangeObjName"};
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
	
	
	public void actionPerformed(ActionEvent e){}
}