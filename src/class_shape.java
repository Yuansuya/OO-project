package MyShape;
import javax.swing.*;
import java.awt.*;
import MyShape.*;
public class class_shape extends MyShape
{
	private int shape_h =90;
	private int shape_w =90;
	public class_shape(Point SP)
	{
		super(SP);
		super.EndPoint = new Point(SP.x + shape_h, SP.y + shape_w);
		super.DirPoints[0] = new Point(SP.x + shape_h / 2 - super.offset, SP.y - super.offset);
		super.DirPoints[1] = new Point(SP.x + shape_h - super.offset, SP.y + shape_w /2 - super.offset);
		super.DirPoints[2] = new Point(SP.x + shape_h / 2- super.offset, SP.y + shape_w- super.offset);
		super.DirPoints[3] = new Point(SP.x - super.offset, SP.y + shape_w /2 - super.offset);		
	}
	public void DrawShape(Graphics g)
	{
		int part =shape_h /3 ;
		g.drawRect(super.StartPoint.x, super.StartPoint.y, shape_h,shape_w);
		g.drawLine(super.StartPoint.x, super.StartPoint.y + part, super.StartPoint.x + shape_w, super.StartPoint.y + part);
		g.drawLine(super.StartPoint.x, super.StartPoint.y + part*2, super.StartPoint.x + shape_w, super.StartPoint.y + part*2);
	}
	
	
}