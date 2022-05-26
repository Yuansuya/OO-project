package Mode;
import Mode.*;
import MyGraphic.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import Port.Port;
public class CompositeMode extends Mode
{
	private Point LastPressedPoint =null ;
	public CompositeMode(GroupGraphic gg)
	{
		super(gg);
	}
	public void ClickAction(Point CurrentPoint)
	{
		
	}
	public void DragAction(Point CurrentPoint)
	{
		
	}
	public void PressedAction(Point CurrentPoint)
	{
		super.graphics.clearSelectedShapes();
		LastPressedPoint = CurrentPoint;
	}
	public void ReleaseAction(Point CurrentPoint)
	{
		MyShape[] GoodShape = super.graphics.IsPriliegedLine(LastPressedPoint, CurrentPoint);
		if(GoodShape != null)
		{
			Port startPort = GoodShape[0].AlignAtPort(LastPressedPoint);
			Port endPort = GoodShape[1].AlignAtPort(CurrentPoint);
			super.graphics.addG(new CompositionLine(startPort, endPort));
		}
	}
}