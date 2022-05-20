package MyGraphic;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;

public class Group_shape extends MyShape
{
	MyShape[] shapes ; 	
	private int offset = 10;
	public Group_shape(MyShape[] shapes)
	{
		int[] MaxAndMinBorders = new int[]{9999, 9999, 0, 0};//{min_x, min_y, max_x, max_y}
		this.shapes = shapes;
		
		for(int i = 0 ; i < shapes.length ; ++i)
		{
			this.shapes[i].setConnectorShow(false);
			this.shapes[i].setGrouped(true);
			int[] borders = this.shapes[i].getBorders();
			for(int j =0 ; j < 4 ;++j)
			{
				if(j < 2)
				{
					MaxAndMinBorders[j] = Math.min(MaxAndMinBorders[j], borders[j]);
				}
				else
				{
					MaxAndMinBorders[j] = Math.max(MaxAndMinBorders[j], borders[j]);
				}
			}	
		}
		MaxAndMinBorders[0] -= offset ;
		MaxAndMinBorders[1] -= offset ;
		MaxAndMinBorders[2] += offset ;
		MaxAndMinBorders[3] += offset ;
		super.Corners[0] = new Point(MaxAndMinBorders[0], MaxAndMinBorders[1]);
		super.Corners[1] = new Point(MaxAndMinBorders[2], MaxAndMinBorders[1]);
		super.Corners[2] = new Point(MaxAndMinBorders[2], MaxAndMinBorders[3]);
		super.Corners[3] = new Point(MaxAndMinBorders[0], MaxAndMinBorders[3]);

		super.width = MaxAndMinBorders[2] - MaxAndMinBorders[0] ;
		super.height = MaxAndMinBorders[3] - MaxAndMinBorders[1]; 

	}
	
	
	public void Draw(Graphics g)
	{
		for(MyShape sp : shapes)
		{
			sp.Draw(g);
		}
		DrawCorners(g);
	}
	
	private void DrawCorners(Graphics g)
	{
		g.drawRect(super.Corners[0].x, super.Corners[0].y, super.width, super.height);
	}
	
	public void move(int offset_x, int offset_y)
	{
		super.Corners[0] = new Point(super.Corners[0].x += offset_x, super.Corners[0].y += offset_y);
		super.Corners[1] = new Point(super.Corners[1].x += offset_x, super.Corners[1].y += offset_y);
		super.Corners[2] = new Point(super.Corners[2].x += offset_x, super.Corners[2].y += offset_y);
		super.Corners[3] = new Point(super.Corners[3].x += offset_x, super.Corners[3].y += offset_y);
		
		for(MyShape sp : shapes)
		{
			sp.move(offset_x, offset_y);
		}
	}
	
	public void removeGroup()
	{
		for(int i= 0 ; i< this.shapes.length ; ++i)
		{
			shapes[i].setGrouped(false);
		}

	}
	
}