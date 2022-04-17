package MyShape;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class MyShape
{
	protected Point[] Corners = new Point[4]; // TopLeft, TopRight, DownRight, DownLeft
	protected final int height = 100;
	protected final int width= (int)(height*1.5);
	protected Point[] Connectors = new Point[4]; //East, south, west, north
	protected String name;
	protected boolean IsNameShow = false;
	protected boolean IsConnectorShow = false;
	protected int depth ;
	private final int offset = 5;
	public MyShape(Point SP)
	{
		this.Corners[0] = SP;
		this.Corners[1] = new Point(SP.x + width, SP.y);
		this.Corners[2] = new Point(SP.x + width, SP.y + height);
		this.Corners[3] = new Point(SP.x, SP.y + height);
		
		this.Connectors[0] = new Point(SP.x + width/2, SP.y);
		this.Connectors[1] = new Point(SP.x + width, SP.y + height/2);
		this.Connectors[2] = new Point(SP.x + width/2, SP.y + height);
		this.Connectors[3] = new Point(SP.x, SP.y + height/2);
	}
	//for group 
	public MyShape(MyShape[] shapes)
	{
		int top=0;
		int down=0;
		int left=0;
		int right=0;
		for(MyShape sh: shapes)
		{
			if(top > sh.Corners[0].y)
				top = sh.Corners[0].y ;
			if(left > sh.Corners[0].x)
				left =sh.Corners[0].y ;
			if(right < sh.Corners[2].x)
				right = sh.Corners[2].x ;
			if(down < sh.Corners[2].x)
				down = sh.Corners[2].x ;
		}
		
		this.Corners[0] = new Point(top, left);
		this.Corners[1] = new Point(top, right);
		this.Corners[2] = new Point(down, right);
		this.Corners[3] = new Point(down, left);
	}
	public void DrawShape(Graphics g)
	{
		;
	}
	
	public void DrawConnectors(Graphics g)
	{
		g.setColor(Color.BLACK);
		for(int i  =0 ;i < 4; i ++)
		{				
			g.fillOval(this.Connectors[i].x - this.offset, this.Connectors[i].y - this.offset, 10, 10);
		}
	}
	
	public void setConnectorShow(boolean b)
	{
		this.IsConnectorShow = b;
	}
	
	public boolean getConnectorShow(MyShape s)
	{
		return IsConnectorShow;
	}
	
	// @param CurrentPoint refers to where the mouse point now
	// output Myshape : return the shape which be pointed if has
	// 													  else null
	public MyShape IsMouseInShape(Point CurrentPoint)
	{
		int x_axis = CurrentPoint.x - this.Corners[0].x;
		if(x_axis < 0 || x_axis > this.width)
			return null;
		int y_axis = CurrentPoint.y - this.Corners[0].y;
		if(y_axis < 0 || y_axis > this.height)
			return null;
		return this;
	}
	
	//Output is the nubmer of which connector will be connect
	public int AlignAtConntector(Point CurrentPoint)
	{
		int x = CurrentPoint.x;
		int y = CurrentPoint.y;
		Map<Double, Integer> distanceTable = new HashMap<>();
		double Distance_east =  calculateDistanceBetweenPoints(x, y, this.Connectors[0].x, this.Connectors[0].y);
		double Distance_south =  calculateDistanceBetweenPoints(x, y, this.Connectors[1].x, this.Connectors[1].y);
		double Distance_west =  calculateDistanceBetweenPoints(x, y, this.Connectors[2].x, this.Connectors[2].y);
		double Distance_north =  calculateDistanceBetweenPoints(x, y, this.Connectors[3].x, this.Connectors[3].y);
		distanceTable.put(Distance_east, 0);
		distanceTable.put(Distance_south, 1);
		distanceTable.put(Distance_west, 2);
		distanceTable.put(Distance_north, 3);
		
		double min_dis = Math.min(Math.min(Math.min(Distance_east, Distance_south), Distance_west), Distance_north);
		
		return distanceTable.get(min_dis);
	}
	private double calculateDistanceBetweenPoints(double x1, double y1, double x2, double y2) 
	{       
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}
	
	
	public Point[] getConnectors()
	{
		return this.Connectors;
	}
	
	
}