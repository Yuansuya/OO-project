package MyButton;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import Mode.*;
public class MyButton extends JButton
{
	Mode mode ; 
	
	public MyButton()
	{
		super();
	}
	public MyButton(ImageIcon imageicon)
	{
		super(imageicon);
	}
	public MyButton(ImageIcon imageicon, Mode mode)
	{
		super(imageicon);
		this.mode = mode ;
	}
	
	public void setColor(Color c)
	{
		this.setBackground(c);
	}
	
	public Mode getMode()
	{
		return this.mode;
	}
}