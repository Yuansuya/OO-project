package MyGraphic;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;
// import IBehavior.*;
public class GroupGraphic extends MyGraphic
{
	java.util.List<MyGraphic> MyGraphics ;
	
	public GroupGraphic()
	{
		MyGraphics = AllShapes.getInstance().getGraphics();
	}
	
	public void Draw(Graphics g)
	{
		//composite pattern : draw all graphics
		for(MyGraphic gp : MyGraphics)
			gp.Draw(g);
	}
	public void addG(MyGraphic o)
	{
		MyGraphics.add(o);
		System.out.println("number of Myshape " + MyGraphics.size());
	}
	public void removeG(MyGraphic o)
	{
		MyGraphics.remove(o);
		System.out.println("number of Myshape " + MyGraphics.size());
	}
	public MyShape GetShapeUnderTheMouse(Point p)
	{
		if(this.MyGraphics == null)
			return null ;
		MyShape return_shape = null ;	
		for(MyGraphic gp : this.MyGraphics)
		{
			if(gp.IsShapeUnderTheMouse(p) == true && gp.getGrouped() == false)
			{
				//first shape
				if(return_shape == null)
				{
					return_shape = (MyShape)gp;
				}
				else if(return_shape.getDepth() < gp.getDepth())
				{
					return_shape = (MyShape)gp;
				}
			}				
		}

		return return_shape;
	}
	public void clearSelectedShapes()
	{
		if(this.MyGraphics != null )
		{
			for(MyGraphic gp : this.MyGraphics)
			{
				gp.setPortShow(false);
			}
		}
	}
	
	public void setPortShowUnderTheArea(Point TopLeft, Point DownRight)
	{
		if(this.MyGraphics != null )
		{
			for(MyGraphic gp : this.MyGraphics)
			{
				if(gp.IsShapeInArea(TopLeft, DownRight))
					gp.setPortShow(true);
			}
		}
		
	}	
	
	public MyShape[] IsPriliegedLine(Point LastPressedPoint, Point CurrentPoint)
	{
		MyShape LastPressedShape =null;
		MyShape ReleasedShape =null;
		if(this.MyGraphics != null )
		{
			for(MyGraphic gp : this.MyGraphics)
			{
				if(gp.getGrouped())
					continue; 
				if(LastPressedShape == null && gp.IsShapeUnderTheMouse(LastPressedPoint) == true)
					LastPressedShape = (MyShape)gp ;
				if(ReleasedShape == null && gp.IsShapeUnderTheMouse(CurrentPoint) == true)
					ReleasedShape = (MyShape)gp; 
				if(LastPressedShape != null && ReleasedShape != null && LastPressedShape != ReleasedShape )
					return new MyShape[]{LastPressedShape, ReleasedShape};		
			}
		}
		
		return null;
	}
	
	public MyShape[] getSelectedShape()
	{
		if(MyGraphics == null)
			return null ;
		java.util.List<MyShape> shapes = new ArrayList<>();
	
		for(MyGraphic gp : this.MyGraphics)
		{
			if(gp.getPortShow() )
				shapes.add((MyShape)gp);
		}
		if(shapes.size() == 0)
			return null;
		else
			return shapes.toArray(new MyShape[shapes.size()]);
	}
}