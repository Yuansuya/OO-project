package Mode;
import Mode.*;
import MyGraphic.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class ClassMode extends Mode
{
	public ClassMode(GroupGraphic gg)
	{
		super(gg);
	}
	public void ClickAction(Point CurrentPoint)
	{
		super.graphics.clearSelectedShapes();
		super.graphics.addG(new class_shape(CurrentPoint));
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