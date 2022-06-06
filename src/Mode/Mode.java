package Mode;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;
public abstract class Mode
{
	protected GroupGraphic graphics ;
	public Mode(MyGraphic gg)
	{
		this.graphics = (GroupGraphic)gg ;
		System.out.println("Test");
	}
	public abstract void ClickAction(Point CurrentPoint,int depth_counter);
	public abstract void DragAction(Point CurrentPoint);
	public abstract void PressedAction(Point CurrentPoint);
	public abstract void ReleaseAction(Point CurrentPoint);

}