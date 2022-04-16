package MyShape;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class MyShape
{
	protected Point[] Corners = new Point[4]; // TopLeft, TopRight, DownRight, DownLeft
	protected final int width = 100;
	protected final int height = (int)(width*1.5);
	protected Point[] Connectors = new Point[4]; //East, south, west, north
	protected String name;
	protected boolean IsNameShow = false;
	protected boolean IsConnectorShow = false;
	final int offset = 5;
	public MyShape(Point SP)
	{
		this.Corners[0] = SP;
		this.Corners[1] = new Point(SP.x, SP.y + width);
		this.Corners[2] = new Point(SP.x + width, SP.y);
		this.Corners[3] = new Point(SP.x + width, SP.y + height);
		
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
	protected void DrawShape(Graphics g)
	{
		;
	}
	
	protected void DrawCorners(Graphics g)
	{
		g.setColor(Color.BLACK);
		for(int i  =0 ;i < 4; i ++)
		{				
			g.fillOval(this.Connectors[i].x, this.Connectors[i].y, 10, 10);
		}
	}
	
}