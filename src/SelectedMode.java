package Mode;
import Mode.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*; 
public class SelectedMode extends Mode
{
	
	private boolean SelectOrDragMode = false; // false for select, true for drag
	private MyShape BeDraggedShape = null;
	private Point LastPressedPoint =null ;
	
	public SelectedMode(MyGraphic gg)
	{
		super(gg);
	}
	public void ClickAction(Point CurrentPoint)
	{
		super.graphics.clearSelectedShapes();
		MyShape[] SelectedShape = super.graphics.GetShapesUnderTheMouse(CurrentPoint);
		if(SelectedShape != null)
		{
			for(MyShape sp : SelectedShape)
			{
				sp.setPortShow(true);
			}
		}
	}
	public void DragAction(Point CurrentPoint)
	{
		if(SelectOrDragMode == true)
		{
			int offset_x = CurrentPoint.x - LastPressedPoint.x ;
			int offset_y = CurrentPoint.y - LastPressedPoint.y ;
			BeDraggedShape.move(offset_x, offset_y);
			LastPressedPoint = CurrentPoint ; 
		}		
	}
	public void PressedAction(Point CurrentPoint)
	{
		super.graphics.clearSelectedShapes();
		
		MyShape[] SelectedShape = super.graphics.GetShapesUnderTheMouse(CurrentPoint);
		if(SelectedShape == null)
		{
			// Selected many shape mode 
			SelectOrDragMode = false;					
		}
		else
		{
			// Drag a shape mode 
			SelectOrDragMode = true;
			BeDraggedShape = SelectedShape[0];
		}
		LastPressedPoint = CurrentPoint;
	}
	public void ReleaseAction(Point CurrentPoint)
	{
		if(SelectOrDragMode == false)
		{
			// Select many shapes mode
			Point TopLeft = new Point(Math.min(LastPressedPoint.x, CurrentPoint.x),Math.min(LastPressedPoint.y, CurrentPoint.y));
			Point DownRight = new Point(Math.max(LastPressedPoint.x, CurrentPoint.x),Math.max(LastPressedPoint.y, CurrentPoint.y));
			super.graphics.setPortShowUnderTheArea(TopLeft, DownRight);
		}
		else
		{
			// Drag mode end
			BeDraggedShape = null ; 
			SelectOrDragMode = false;
		}
	}
}