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
	@Override
	public void Draw(Graphics g)
	{
		//Draw class object 
		int part =super.height /3 ;
		g.drawRect(super.Corners[0].x, super.Corners[0].y, super.width,super.height);
		g.drawLine(super.Corners[0].x, super.Corners[0].y + part, super.Corners[0].x + super.width, super.Corners[0].y + part);
		g.drawLine(super.Corners[0].x, super.Corners[0].y + part*2, super.Corners[0].x + super.width, super.Corners[0].y + part*2);
		
		//Draw Ports if needs 
		if(super.IsPortShow)
		{
			for(Port p : super.ports)
			{
				p.drawPort(g);
			}
		}
		
		//Draw object name
		g.drawString(super.name,super.Corners[0].x+50,super.Corners[0].y+20);
	}
	
	
}