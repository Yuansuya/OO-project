package Mode;
import Mode.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;
public class AssociationMode extends Mode
{
	private Point LastPressedPoint =null ;
	public AssociationMode(GroupGraphic gg)
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
		LastPressedPoint = CurrentPoint ;
	}
	public void ReleaseAction(Point CurrentPoint)
	{
		MyShape[] GoodShape = super.graphics.IsPriliegedLine(LastPressedPoint, CurrentPoint);
		if(GoodShape != null)
		{
			int Num_StartConnector = GoodShape[0].AlignAtConntector(LastPressedPoint);
			int Num_EndConnector = GoodShape[1].AlignAtConntector(CurrentPoint);
			super.graphics.addG(new AssociationLine(GoodShape[0], GoodShape[1], Num_StartConnector, Num_EndConnector));
		}
	}
}