package Mode;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.GroupGraphic;
public abstract class Mode
{
	protected GroupGraphic graphics ;
	public Mode(GroupGraphic gg)
	{
		this.graphics = gg ;
	}
	public abstract void ClickAction(Point CurrentPoint);
	public abstract void DragAction(Point CurrentPoint);
	public abstract void PressedAction(Point CurrentPoint);
	public  abstract void ReleaseAction(Point CurrentPoint);

}