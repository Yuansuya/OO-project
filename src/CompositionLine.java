package MyGraphic;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;
import java.awt.geom.*;
public class CompositionLine extends MyLine
{
	public CompositionLine(MyShape StartShape, MyShape EndShape, int StartConnector, int EndConnector)
	{
		super(StartShape, EndShape, StartConnector, EndConnector);
	}
	
	public void Draw(Graphics g)
	{
		super.setPosition();
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(Color.BLACK);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
		double theta = Math.atan2((double)(super.EndPoint.y+super.offset - super.StartPoint.y+super.offset), (double)(super.EndPoint.x+super.offset - super.StartPoint.x+super.offset));
		drawArrow(g2, theta, (double)super.EndPoint.x+super.offset, (double)super.EndPoint.y+super.offset);
	}
	private void drawArrow(Graphics2D g2, double theta, double x0, double y0)
	{
		double x1 = x0 - super.barb * Math.cos(theta + super.phi);
        double y1 = y0 - super.barb * Math.sin(theta + super.phi);
        g2.draw(new Line2D.Double(x0, y0, x1, y1));
        double x2 = x0 - super.barb * Math.cos(theta - super.phi);
        double y2 = y0 - super.barb * Math.sin(theta - super.phi);
        g2.draw(new Line2D.Double(x0, y0, x2, y2));
		
		double middle_x = (x1+x2)/2;
		double middle_y = (y1+y2)/2;
		
		double x3 = x0 + 2*(middle_x-x0);
		double y3 = y0 + 2*(middle_y-y0);
		
		g2.draw(new Line2D.Double(x1, y1, x3, y3));
		g2.draw(new Line2D.Double(x2, y2, x3, y3));
		
		
		g2.draw(new Line2D.Double(super.StartPoint.x+super.offset, super.StartPoint.y+super.offset, x3, y3 ));  // draw line 
	}	
	
}