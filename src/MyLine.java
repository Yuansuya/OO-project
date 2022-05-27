package MyGraphic;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;
import Port.Port;
public class MyLine extends MyGraphic
{
	protected Port startPort ;
	protected Port endPort ;
	
	protected final double phi = Math.PI/6;  // 30 degrees barb angle
	protected final int barb = 20;   //barb length
	public MyLine()
	{
		;
	}
	public MyLine(Port sp, Port ep)
	{
		this.startPort = sp ;
		this.endPort = ep ;
	}
	public void Draw(Graphics g)
	{
		;
	}

	protected Point getPortPosition(Port p)
	{
		return p.getPosition();
	}
}