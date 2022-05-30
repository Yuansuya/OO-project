package MyGraphic;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import Port.Port;
public abstract class MyGraphic
{
	public abstract void Draw(Graphics g);
	
	//GroupGraphic methods
	public void addG(MyGraphic o){}
	public void removeG(MyGraphic o){}
	public MyShape GetShapeUnderTheMouse(Point p)
	{
		return null;
	}
	public void clearSelectedShapes(){}
	public void setPortShowUnderTheArea(Point TopLeft, Point DownRight){}
	public MyGraphic[] IsPriliegedLine(Point LastPressedPoint, Point CurrentPoint)
	{
		return null;
	}
	public MyShape[] getSelectedShape()
	{
		return null;
	}
	
	
	//MyShape methods
	public void setPortShow(boolean b ){}
	public boolean getPortShow()
	{
		return false;
	}
	public boolean IsShapeInArea(Point TopLeft, Point DownRight)
	{
		return false;
	}
	public boolean IsShapeUnderTheMouse(Point CurrentPoint)
	{
		return false;
	}
	public Port AlignMouseAtPort(Point CurrentPoint)
	{
		return null;
	}
	public void setName(String name){}
	public void setGrouped(boolean b){}
	public boolean getGrouped()
	{
		return false;
	}
	public int[] getBorders()
	{
		return null;
	}
	public int getDepth()
	{
		return -1 ;
	}
}