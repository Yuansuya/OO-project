package MyGraphic;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;
import java.awt.geom.*;
import Port.Port;
public class AssociationLine extends MyLine
{
	public AssociationLine(Port sp, Port ep)
	{
		super(sp, ep);
	}
	
	public void Draw(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(Color.BLACK);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
							
		Point startPoint_pos = getPortPosition(startPort);
		Point endPoint_pos = getPortPosition(endPort);
							
		g.drawLine(startPoint_pos.x, startPoint_pos.y, endPoint_pos.x, endPoint_pos.y);
		double theta = Math.atan2((double)(endPoint_pos.y - startPoint_pos.y), (double)(endPoint_pos.x - startPoint_pos.x));
		drawArrow(g2, theta, (double)endPoint_pos.x, (double)endPoint_pos.y);
	}
	private void drawArrow(Graphics2D g2, double theta, double x0, double y0)
	{
		double x = x0 - super.barb * Math.cos(theta + super.phi);
        double y = y0 - super.barb * Math.sin(theta + super.phi);
        g2.draw(new Line2D.Double(x0, y0, x, y));
        x = x0 - super.barb * Math.cos(theta - super.phi);
        y = y0 - super.barb * Math.sin(theta - super.phi);
        g2.draw(new Line2D.Double(x0, y0, x, y));
	}	
	
}