package Mode;
import Mode.*;
import MyGraphic.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class ClassMode extends Mode
{
	public ClassMode(MyGraphic gg)
	{
		super(gg);
	}
	public void ClickAction(Point CurrentPoint, int depth_counter)
	{
		super.graphics.clearSelectedShapes();
		super.graphics.addG(new class_shape(CurrentPoint, depth_counter));
	}
	public void DragAction(Point CurrentPoint)
	{
		
	}
	public void PressedAction(Point CurrentPoint)
	{
		super.graphics.clearSelectedShapes();
	}
	public void ReleaseAction(Point CurrentPoint)
	{
		
	}
}