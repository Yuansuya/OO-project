package MyPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import MyObserver.*;
import MyPanel.*;
import MyShape.*;
import MyLine.*;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, MyObserver
{
	private ButtonPanel BP;   // for Observable
	private int CurrentButtonMode = -1;
	java.util.List<MyShape> ShapeList ;
	java.util.List<MyLine> LineList ;
	
	private static Point LastPressedPoint =null ;
	private static MyShape LastPressedShape = null ;
	private static MyShape ReleasedShape = null ;
	
	private static boolean SelectOrDrag = false; // false for select, true for drag
	private static MyShape DraggedShape = null;
	
	public DrawPanel(Point StartPoint,int WidthSize,int HeightSize,ButtonPanel bp) {
		this.setLayout(null);
		this.setLocation(StartPoint.x, StartPoint.y);
		this.setSize(WidthSize, HeightSize);
		this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.BP = bp;
		ShapeList = new ArrayList<>();
		LineList = new ArrayList<>();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.black);
		for(MyShape sp : ShapeList)
		{
			sp.DrawShape(g);
		}
		for(MyLine ln :LineList)
		{
			//Adjust the position to suit the shape position dynamiclly every sigle time
			ln.setPosition();
			ln.DrawLine(g);
		}
	}
	
	//MouseListener implementations mousePressed, mouseClicked, mouseEntered, mouseExited, mouseReleased
	public void mousePressed(MouseEvent me) 
	{
		Point CurrentPoint = me.getPoint();
		switch(CurrentButtonMode)
		{
			case 0:
				//catch the single shape
				for(MyShape sp : ShapeList)
				{
					if(sp.IsMouseInShape(CurrentPoint) != null)
					{
						sp.setConnectorShow(true);
						SelectOrDrag =true;
					}						
					else
						sp.setConnectorShow(false);
				}	
				
				//catch a bouch of shapes
				if(SelectOrDrag == false)
				{
					LastPressedPoint = CurrentPoint;
				}
				
				break;
			case 1:
			case 2:
			case 3:
				for(MyShape sp : ShapeList)
					sp.setConnectorShow(false);
				LastPressedPoint = CurrentPoint;
				break;
		}
	}
	public void mouseClicked(MouseEvent me) {
		Point CurrentPoint = me.getPoint();
		for(MyShape sp : ShapeList)
			sp.setConnectorShow(false);
			
		
		switch(CurrentButtonMode)
		{
			case 0:
				for(MyShape sp : ShapeList)
				{
					if(sp.IsMouseInShape(CurrentPoint) != null)
					{
						sp.setConnectorShow(true);
						break;
					}
				}
				break;
			case 4:
				ShapeList.add(new class_shape(CurrentPoint));
				break;
			case 5:
				ShapeList.add(new UseCase_shape(CurrentPoint));
				break;
		}
		repaint();
	}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) 
	{
		Point CurrentPoint = me.getPoint();
		MyLine line =null;
		switch(CurrentButtonMode)
		{
			case 0:
				DraggedShape = null;
				
				if(LastPressedPoint != null)
				{
					Point TopLeft = new Point(Math.min(LastPressedPoint.x, CurrentPoint.x),Math.min(LastPressedPoint.y, CurrentPoint.y));
					Point DownRight = new Point(Math.max(LastPressedPoint.x, CurrentPoint.x),Math.max(LastPressedPoint.y, CurrentPoint.y));
					for(MyShape sp : ShapeList)
					{
						Point[] corners = sp.getCorners();
						if(corners[0].x >= TopLeft.x && corners[0].y >= TopLeft.y && corners[2].x <= DownRight.x && corners[2].y <= DownRight.y)
							sp.setConnectorShow(true);
					}
				}
				break;
			case 1:
				if(IsPriliegedLine(LastPressedPoint, CurrentPoint))
				{
					int Num_StartConnector = LastPressedShape.AlignAtConntector(LastPressedPoint);
					int Num_EndConnector = ReleasedShape.AlignAtConntector(CurrentPoint);
					line = new AssociationLine(LastPressedShape, ReleasedShape, Num_StartConnector, Num_EndConnector);
				}
				break;
			case 2:
				if(IsPriliegedLine(LastPressedPoint, CurrentPoint))
				{
					int Num_StartConnector = LastPressedShape.AlignAtConntector(LastPressedPoint);
					int Num_EndConnector = ReleasedShape.AlignAtConntector(CurrentPoint);
					line = new GeneralizationLine(LastPressedShape, ReleasedShape, Num_StartConnector, Num_EndConnector);
				}
				break;
			case 3:
				if(IsPriliegedLine(LastPressedPoint, CurrentPoint))
				{
					int Num_StartConnector = LastPressedShape.AlignAtConntector(LastPressedPoint);
					int Num_EndConnector = ReleasedShape.AlignAtConntector(CurrentPoint);
					line = new CompositionLine(LastPressedShape, ReleasedShape, Num_StartConnector, Num_EndConnector);
				}
				break;
		}
		if(line != null)
			LineList.add(line);
		
		LastPressedShape = null;
		LastPressedPoint = null;
		ReleasedShape = null;
		SelectOrDrag = false;
		repaint();
		
	}
	
	//MouseMotionListener implementations mouseDragged, mouseMoved
	public void mouseDragged(MouseEvent me) 
	{
		
		Point CurrentPoint = me.getPoint();
		switch(CurrentButtonMode)
		{
			case 0:	
				if(SelectOrDrag == true)
				{
					if(DraggedShape == null)
					{
						for(MyShape sp : ShapeList)
						{
							DraggedShape = sp.IsMouseInShape(CurrentPoint);
							if(DraggedShape != null)
								break;
						}
					}
					else
					{
						DraggedShape.setCornersLocation(CurrentPoint);
						DraggedShape.setConnectorsLocation(CurrentPoint);
						// System.out.println("asdb  " + DraggedShape.getConnectors()[0]);
						
					}
				}
				
				break;
		}
		repaint();
		
	}
	public void mouseMoved(MouseEvent me){}
	
	
	//MyObserver implementations update()
	public void update()
	{
		CurrentButtonMode = BP.GetCurrentMode();
		System.out.println(CurrentButtonMode);
	}
	
	private boolean IsPriliegedLine(Point LastPressedPoint, Point CurrentPoint)
	{
		for(MyShape sp : ShapeList)
		{
			if(LastPressedShape == null)
				LastPressedShape = sp.IsMouseInShape(LastPressedPoint);
			if(ReleasedShape == null)
				ReleasedShape = sp.IsMouseInShape(CurrentPoint); 
			if(LastPressedShape != null && ReleasedShape != null && LastPressedShape != ReleasedShape)
				return true;		
		}
		return false;
	}
}