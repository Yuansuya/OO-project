package MyLine;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class MyLine
{
	protected Point StartPoint;
	protected Point EndPoint;
	protected final int offset = 5;
	
	protected final double phi = Math.PI/6;  // 30 degrees barb angle
	protected final int barb = 20;   //barb length
	public MyLine()
	{
		;
	}
	public MyLine(Point SP, Point EP)
	{
		this.StartPoint =SP;
		this.EndPoint = EP;
	}
	
	public void DrawLine(Graphics g)
	{
		;
	}
	
}