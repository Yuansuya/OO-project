package MyMenuBar;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import IBarBehavior.*;
public class MyMenuItem extends MenuItem 
{
	private IBarBehavior behavior ;
	public MyMenuItem()
	{
		super();
	}
	public MyMenuItem(String name)
	{
		super(name);
	}
	
	public void setBehavior(IBarBehavior b)
	{
		this.behavior = b ; 
	}
	public IBarBehavior getBehavior()
	{
		return this.behavior; 
	}
}