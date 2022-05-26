package MyGraphic;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;
// import IBehavior.*;
public class GroupGraphic
{
	java.util.List<MyGraphic> MyGraphics = new ArrayList<>();
	java.util.List<MyShape> MyShapes = new ArrayList<>();
	
	// graphicBehavior bevior ;
	// List<MyGraphic> shapes ;
	
	// public GroupGraphic()
	// {
		// shapes = AllShapes.getInstance().getGraphics();
		// bevior = new GroupBehavior();
	// }
	
	public void Draw(Graphics g)
	{
		for(MyGraphic gp : MyGraphics)
			gp.Draw(g);
	}
	public void addG(MyGraphic o)
	{
		MyGraphics.add(o);
		if(o instanceof MyShape)
			MyShapes.add((MyShape)o);
		
		System.out.println("number of Myshape " + MyShapes.size());
	}
	public void removeG(MyGraphic o)
	{
		MyGraphics.remove(o);
		if(o instanceof MyShape)
			MyShapes.remove(o);
		System.out.println("number of Myshape " + MyShapes.size());
	}
	public MyShape[] GetShapesUnderTheMouse(Point p)
	{
		if(MyShapes == null)
			return null;
		
		java.util.List<MyShape> shapes = new ArrayList<>();
		
		for(MyShape sp : this.MyShapes)
		{
			if(sp.IsMouseInShape(p) != null && sp.getGrouped() == false)
				shapes.add(sp);
		}
	
		if(shapes.size() == 0)
			return null;
		else
			return shapes.toArray(new MyShape[shapes.size()]);
	}
	public void clearSelectedShapes()
	{
		if(this.MyShapes != null )
		{
			for(MyShape sp : this.MyShapes)
			{
				sp.setPortShow(false);
			}
		}
	}
	
	public void setConnShowUnderTheArea(Point TopLeft, Point DownRight)
	{
		if(this.MyShapes != null )
		{
			for(MyShape sp : this.MyShapes)
			{
				if(sp.IsShapeInArea(TopLeft, DownRight))
					sp.setPortShow(true);
			}
		}
		
	}	
	
	public MyShape[] IsPriliegedLine(Point LastPressedPoint, Point CurrentPoint)
	{
		MyShape LastPressedShape =null;
		MyShape ReleasedShape =null;
		if(this.MyShapes != null )
		{
			for(MyShape sp : this.MyShapes)
			{
				if(sp.getGrouped() || sp instanceof Group_shape)
					continue; 
				if(LastPressedShape == null)
					LastPressedShape = sp.IsMouseInShape(LastPressedPoint);
				if(ReleasedShape == null)
					ReleasedShape = sp.IsMouseInShape(CurrentPoint); 
				if(LastPressedShape != null && ReleasedShape != null && LastPressedShape != ReleasedShape )
					return new MyShape[]{LastPressedShape, ReleasedShape};		
			}
		}
		
		return null;
	}
	
	public MyShape[] getSelectedShape()
	{
		if(MyShapes == null)
			return null ;
		java.util.List<MyShape> shapes = new ArrayList<>();
	
		for(MyShape sp : this.MyShapes)
		{
			if(sp.getPortShow() )
				shapes.add(sp);
		}
		if(shapes.size() == 0)
			return null;
		else
			return shapes.toArray(new MyShape[shapes.size()]);
	}
}