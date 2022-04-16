package MyLine;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyLine.*;
import java.awt.geom.*;

public class AssociationLine extends MyLine
{
	public AssociationLine(Point SP, Point EP)
	{
		super(SP,EP);
	}
	
	public void DrawLine(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(Color.BLACK);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawLine(super.StartPoint.x+super.offset, super.StartPoint.y+super.offset, super.EndPoint.x+super.offset, super.EndPoint.y+super.offset);
		double theta = Math.atan2((double)(super.EndPoint.y+super.offset - super.StartPoint.y+super.offset), (double)(super.EndPoint.x+super.offset - super.StartPoint.x+super.offset));
		drawArrow(g2, theta, (double)super.EndPoint.x+super.offset, (double)super.EndPoint.y+super.offset);
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