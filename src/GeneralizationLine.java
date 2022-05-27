package MyGraphic;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;
import java.awt.geom.*;
import Port.Port;
public class GeneralizationLine extends MyLine
{
	public GeneralizationLine(Port sp, Port ep)
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
		
		double theta = Math.atan2((double)(endPoint_pos.y - startPoint_pos.y), (double)(endPoint_pos.x - startPoint_pos.x));
		drawArrow(g2, theta, (double)endPoint_pos.x, (double)endPoint_pos.y, startPoint_pos);
	}
	private void drawArrow(Graphics2D g2, double theta, double x0, double y0, Point startPoint_pos)
	{
		double x1 = x0 - super.barb * Math.cos(theta + super.phi);
        double y1 = y0 - super.barb * Math.sin(theta + super.phi);
        g2.draw(new Line2D.Double(x0, y0, x1, y1));
        double x2 = x0 - super.barb * Math.cos(theta - super.phi);
        double y2 = y0 - super.barb * Math.sin(theta - super.phi);
        g2.draw(new Line2D.Double(x0, y0, x2, y2));
		
		g2.draw(new Line2D.Double(x1, y1, x2, y2));  //close triangle
		
		g2.draw(new Line2D.Double(startPoint_pos.x, startPoint_pos.y, (x1+x2)/2,(y1+y2)/2 ));  // draw line 
	}	
	
}