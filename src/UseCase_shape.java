package MyGraphic;
import javax.swing.*;
import java.awt.*;
import MyGraphic.*;
import Port.Port ;
public class UseCase_shape extends MyShape
{
	public UseCase_shape(Point SP)
	{
		super(SP);	
	}
	public void Draw(Graphics g)
	{
		g.drawOval(super.Corners[0].x, super.Corners[0].y, super.width, super.height); 
		
		if(super.IsPortShow)
		{
			for(Port p : super.ports)
			{
				p.drawPort(g);
			}
		}
		
		g.drawString(super.name,super.Corners[0].x+50,super.Corners[0].y+20);
	}
	
	
}