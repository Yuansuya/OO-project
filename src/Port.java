package Port;
import javax.swing.*;
import java.awt.*;

public class Port
{
	private Point position ;
	private int offset = 5;
	
	public Port(Point p)
	{
		this.position = p ;
	}
	
	public void drawPort(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(this.position.x - this.offset, this.position.y - this.offset, 10, 10);
	}
	
	public Point getPosition()
	{
		return this.position; 
	}
	
	public void revisePosition(int newPos_x, int newPos_y)
	{
		this.position.x = newPos_x;
		this.position.y = newPos_y;
	}
	
	
}