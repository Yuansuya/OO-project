package MyShape;
import javax.swing.*;
import java.awt.*;
import MyShape.*;
public class UseCase_shape extends MyShape
{
	public UseCase_shape(Point SP)
	{
		super(SP);	
	}
	public void DrawShape(Graphics g)
	{
		g.drawOval(super.Corners[0].x, super.Corners[0].y, super.width, super.height); 
		
		if(super.IsConnectorShow)
		{
			super.DrawConnectors(g);
		}
	}
	
	
}