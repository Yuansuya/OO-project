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
	
	Point LastPressedPoint =null ;
	MyShape LastPressedShape = null ;
	MyShape DraggedShape = null;
	
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
			case 1:
			case 2:
			case 3:
				for(MyShape sp : ShapeList)
				{
					//if the mouse pressed in the shape 
					if( sp.IsMouseInShape(CurrentPoint) != null)
					{
						LastPressedPoint = CurrentPoint;
						LastPressedShape = sp;
						break;
					}
				}
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
		MyShape ReleasedShape = null;
		switch(CurrentButtonMode)
		{
			case 0:
				DraggedShape = null;
				// System.out.println("Relaease Dragged");
				break;
			case 1:
				if(LastPressedShape != null)
				{
					for(MyShape sp : ShapeList)
					{
						//if mouse release in the shape 
						ReleasedShape = sp.IsMouseInShape(CurrentPoint); 
						if( ReleasedShape != null && LastPressedShape != ReleasedShape)
						{
							int Num_StartConnector = LastPressedShape.AlignAtConntector(LastPressedPoint);
							int Num_EndConnector = ReleasedShape.AlignAtConntector(CurrentPoint);
							line = new AssociationLine(LastPressedShape, ReleasedShape, Num_StartConnector, Num_EndConnector);
							break;
						}
					}
				}
				break;
			case 2:
				if(LastPressedShape != null)
				{
					for(MyShape sp : ShapeList)
					{
						//if mouse release in the shape 
						ReleasedShape = sp.IsMouseInShape(CurrentPoint); 
						if( ReleasedShape != null && LastPressedShape != ReleasedShape)
						{
							int Num_StartConnector = LastPressedShape.AlignAtConntector(LastPressedPoint);
							int Num_EndConnector = ReleasedShape.AlignAtConntector(CurrentPoint);
							line = new AssociationLine(LastPressedShape, ReleasedShape, Num_StartConnector, Num_EndConnector);
							break;
						}
					}
				}
				break;
			case 3:
				if(LastPressedShape != null)
				{
					for(MyShape sp : ShapeList)
					{			
						//if mouse release in the shape
						ReleasedShape = sp.IsMouseInShape(CurrentPoint); 
						if( ReleasedShape != null && LastPressedShape != ReleasedShape)
						{
							int Num_StartConnector = LastPressedShape.AlignAtConntector(LastPressedPoint);
							int Num_EndConnector = ReleasedShape.AlignAtConntector(CurrentPoint);
							line = new AssociationLine(LastPressedShape, ReleasedShape, Num_StartConnector, Num_EndConnector);
							break;
						}
					}
				}
				break;
		}
		if(line != null)
			LineList.add(line);
		
		LastPressedShape = null;
		LastPressedPoint = null;
		
		repaint();
		
	}
	
	//MouseMotionListener implementations mouseDragged, mouseMoved
	public void mouseDragged(MouseEvent me) 
	{
		
		Point CurrentPoint = me.getPoint();
		switch(CurrentButtonMode)
		{
			case 0:	
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
				 
				break;
		}
		repaint();
		
	}
	public void mouseMoved(MouseEvent me){}
	
	
	//MyObserver implementations update
	public void update()
	{
		CurrentButtonMode = BP.GetCurrentMode();
		System.out.println(CurrentButtonMode);
		

	}
}