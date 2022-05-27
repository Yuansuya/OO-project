package MyGraphic;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;

public class Group_shape extends MyShape
{
	private MyShape[] group_shapes ; 	
	private int offset = 10; //frame all group shpaes by move outward offest 
	public Group_shape(MyShape[] shapes)
	{
		
		this.group_shapes = shapes;
		joinGroup();
		
		int[] MaxAndMinBorders = calculateBorder();//{min_x, min_y, max_x, max_y}
		super.Corners[0] = new Point(MaxAndMinBorders[0], MaxAndMinBorders[1]);
		super.Corners[1] = new Point(MaxAndMinBorders[2], MaxAndMinBorders[1]);
		super.Corners[2] = new Point(MaxAndMinBorders[2], MaxAndMinBorders[3]);
		super.Corners[3] = new Point(MaxAndMinBorders[0], MaxAndMinBorders[3]);

		super.width = MaxAndMinBorders[2] - MaxAndMinBorders[0] ;
		super.height = MaxAndMinBorders[3] - MaxAndMinBorders[1]; 

	}
	
	private void joinGroup()
	{
		for(MyShape shapes : this.group_shapes)
		{
			shapes.setPortShow(false);
			shapes.setGrouped(true);
		}
	}
	private int[] calculateBorder()
	{
		int[] MaxAndMinBorders = new int[]{9999, 9999, 0, 0};
		for(int i = 0 ; i < this.group_shapes.length ; ++i)
		{
			int[] borders = this.group_shapes[i].getBorders();
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
		
		return MaxAndMinBorders;
	}
	
	@Override
	public void Draw(Graphics g)
	{
		//draw shapes in the group
		for(MyShape sp : group_shapes)
		{
			sp.Draw(g);
		}
		
		//Draw Border by corners
		g.drawRect(super.Corners[0].x, super.Corners[0].y, super.width, super.height);
	}
	
	
	@Override
	public void move(int offset_x, int offset_y)
	{
		//move the Group_shape's border
		super.revisePosition(offset_x, offset_y);

		//move shapes in the gorup
		for(MyShape sp : group_shapes)
		{
			sp.move(offset_x, offset_y);
		}
	}
	
	//return true refers to OK
	@Override
	public boolean removeGroup()
	{
		for(int i= 0 ; i< this.group_shapes.length ; ++i)
		{
			group_shapes[i].setGrouped(false);
		}
		return true;
	}
	
}