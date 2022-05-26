package MyGraphic;
import javax.swing.*;
import java.awt.*;
import MyGraphic.*;
import Port.Port ;
public class class_shape extends MyShape
{
	public class_shape(Point SP)
	{
		super(SP);	
	}
	public void Draw(Graphics g)
	{
		int part =super.height /3 ;
		g.drawRect(super.Corners[0].x, super.Corners[0].y, super.width,super.height);
		g.drawLine(super.Corners[0].x, super.Corners[0].y + part, super.Corners[0].x + super.width, super.Corners[0].y + part);
		g.drawLine(super.Corners[0].x, super.Corners[0].y + part*2, super.Corners[0].x + super.width, super.Corners[0].y + part*2);
		
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