package MyGraphic;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.*;

public class MyLine extends MyGraphic
{
	protected MyShape StartShape;
	protected MyShape EndShape;
	protected int StartConnector;
	protected int EndConnector;
	protected Point StartPoint;
	protected Point EndPoint;
	protected final int offset = 0;
	
	protected final double phi = Math.PI/6;  // 30 degrees barb angle
	protected final int barb = 20;   //barb length
	public MyLine()
	{
		;
	}
	public MyLine(MyShape StartShape, MyShape EndShape, int StartConnector, int EndConnector)
	{
		this.StartShape = StartShape;
		this.EndShape = EndShape;
		this.StartConnector = StartConnector;
		this.EndConnector = EndConnector;
		setPosition();
	}
	protected void setPosition()
	{
		this.StartPoint =this.StartShape.Connectors[this.StartConnector];
		this.EndPoint = this.EndShape.Connectors[this.EndConnector];
	}
	public void Draw(Graphics g)
	{
		;
	}
	
}