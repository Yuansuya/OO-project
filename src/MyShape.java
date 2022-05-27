package MyGraphic;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;
import Port.Port ;
// import IBehavior.*;
public class MyShape extends MyGraphic 
{
	protected Point[] Corners = new Point[4]; // TopLeft, TopRight, DownRight, DownLeft
	protected int height = 100;
	protected int width= (int)(height*1.5);
	protected Port[] ports = new Port[4]; //East, south, west, north
	protected String name = "";
	protected boolean IsNameShow = false;
	protected boolean IsPortShow = false; 
	protected int depth;
	private final int offset = 5;
	private boolean Grouped = false ;
	// private graphicBehavior ;
	public MyShape()
	{
		// graphicBehavior = new ShapeBehavior(this);
	}
	public MyShape(Point SP)
	{
		this.Corners[0] = SP;
		this.Corners[1] = new Point(SP.x + width, SP.y);
		this.Corners[2] = new Point(SP.x + width, SP.y + height);
		this.Corners[3] = new Point(SP.x, SP.y + height);
		
		this.ports[0] = new Port(new Point(SP.x + width/2, SP.y));
		this.ports[1] = new Port(new Point(SP.x + width, SP.y + height/2));
		this.ports[2] = new Port(new Point(SP.x + width/2, SP.y + height));
		this.ports[3] = new Port(new Point(SP.x, SP.y + height/2));
		
		// graphicBehavior = new ShapeBehavior(this);
	}


	public void Draw(Graphics g)
	{
		;
	}

	
	public void setPortShow(boolean b )
	{
		if(!Grouped)
			this.IsPortShow =b ;
	}
	
	public boolean getPortShow()
	{
		return this.IsPortShow ;
	}
	
	public boolean IsShapeInArea(Point TopLeft, Point DownRight)
	{
		if(Corners[0].x >= TopLeft.x && Corners[0].y >= TopLeft.y && Corners[2].x <= DownRight.x && Corners[2].y <= DownRight.y)
			return true;
		else
			return false;
	}
	
	// @param CurrentPoint refers to where the mouse point now
	// output Myshape : return the shape which be pointed if has
														  // else null
	public MyShape getShapeInTheMouse(Point CurrentPoint)
	{
		int x_axis = CurrentPoint.x - this.Corners[0].x;
		if(x_axis < 0 || x_axis > this.width)
			return null;
		int y_axis = CurrentPoint.y - this.Corners[0].y;
		if(y_axis < 0 || y_axis > this.height)
			return null;
		return this;
	}
	
	// Output is the nearest port with input current point
	public Port AlignMouseAtPort(Point CurrentPoint)
	{
		
		int PORTNUM = -1 ;
		double distance = 99999;
		for(int i = 0 ; i < 4 ; ++i )
		{
			Point port_pos = ports[i].getPosition();
			double compareDIS = calculateDistanceBetweenPoints(CurrentPoint.x, CurrentPoint.y, port_pos.x, port_pos.y);
			if(distance > compareDIS)
			{
				distance = compareDIS;
				PORTNUM = i ;
			}
		}
		return this.ports[PORTNUM];
		
	}
	private double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) 
	{       
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
	

	
	// public Point[] getCorners()
	// {
		// return this.Corners;
	// }
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void move(int offset_x, int offset_y)
	{
		// this.Corners[0] = new Point(Corners[0].x += offset_x, Corners[0].y += offset_y);
		// this.Corners[1] = new Point(Corners[1].x += offset_x, Corners[1].y += offset_y);
		// this.Corners[2] = new Point(Corners[2].x += offset_x, Corners[2].y += offset_y);
		// this.Corners[3] = new Point(Corners[3].x += offset_x, Corners[3].y += offset_y);
		this.revisePosition(offset_x, offset_y);
		
		this.ports[0].revisePosition(ports[0].getPosition().x +=offset_x, ports[0].getPosition().y += offset_y);
		this.ports[1].revisePosition(ports[1].getPosition().x +=offset_x, ports[1].getPosition().y += offset_y);
		this.ports[2].revisePosition(ports[2].getPosition().x +=offset_x, ports[2].getPosition().y += offset_y);
		this.ports[3].revisePosition(ports[3].getPosition().x +=offset_x, ports[3].getPosition().y += offset_y);
	}
	
	
	protected void revisePosition(int offset_x, int offset_y)
	{
		for(int i = 0 ; i <4 ; ++i)
		{
			this.Corners[i].x += offset_x;
			this.Corners[i].y += offset_y;
		}
	}
	public void setGrouped(boolean b)
	{
		this.Grouped = b ; 
	}
	public boolean getGrouped()
	{
		return this.Grouped; 
	}
	
	// @return int[4] = {min_x, min_y, max_x, max_y}
	public int[] getBorders()
	{
		return new int[]{this.Corners[0].x, this.Corners[0].y, this.Corners[2].x, this.Corners[2].y};
	}
	//return true refers to OK that means it's a Group_shape,false refers to NOT OK that means it's not a Group_shape
	public boolean removeGroup()
	{
		return false;
	}
}