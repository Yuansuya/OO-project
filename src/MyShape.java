package MyShape;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class MyShape
{
	protected Point StartPoint ;
	protected Point EndPoint;
	protected Point[] DirPoints ; //East, south, west, north
	protected String name;
	protected boolean IsNameShow = false;
	protected boolean IsDirShow = false;
	int offset = 5;
	public MyShape(Point SP)
	{
		this.StartPoint = SP;
		
	}
	//for group 
	// public MyShape(MyShape[] shapes)
	// {
		
	// }
	public void DrawShape(Graphics g)
	{
		;
	}
	
}