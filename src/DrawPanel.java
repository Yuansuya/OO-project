package MyPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import observer_pattern.*;
import MyPanel.*;
import MyGraphic.*;
import MyMenuBar.*;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, observer
{
	private ButtonPanel BP;   // for Observable
	private MyMenuBar MB;   // for Observable
	private int CurrentButtonMode = -1;
	private int CurrentBarMode = -1;
	private GroupGraphic GPs = new GroupGraphic();
	
	private static Point LastPressedPoint =null ;
	private static MyShape LastPressedShape = null ;
	private static MyShape ReleasedShape = null ;
	
	private static boolean SelectOrDragMode = false; // false for select, true for drag
	private static MyShape BeDraggedShape = null;
	
	public DrawPanel(Point StartPoint,int WidthSize,int HeightSize,ButtonPanel bp, MyMenuBar mb) {
		this.setLayout(null);
		this.setLocation(StartPoint.x, StartPoint.y);
		this.setSize(WidthSize, HeightSize);
		this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.BP = bp;
		this.MB = mb;
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.black);
		GPs.Draw(g);
	}
	
	//MouseListener implementations mousePressed, mouseClicked, mouseEntered, mouseExited, mouseReleased
	public void mousePressed(MouseEvent me) 
	{
		System.out.println("Pressed");
		Point CurrentPoint = me.getPoint();
		GPs.clearSelectedShapes();
		switch(CurrentButtonMode)
		{
			case 0:
				//catch the single shape
				MyShape[] SelectedShape = GPs.GetShapesUnderTheMouse(CurrentPoint);
				if(SelectedShape == null)
				{
					//Selected many shape mode 
					SelectOrDragMode = false;					
				}
				else
				{
					//Drag a shape mode 
					SelectOrDragMode = true;
					BeDraggedShape = SelectedShape[0];
				}
				LastPressedPoint = CurrentPoint;
				break;
			case 1:
			case 2:
			case 3:
				LastPressedPoint = CurrentPoint;
				break;
		}
	}
	public void mouseClicked(MouseEvent me) {
		Point CurrentPoint = me.getPoint();
		// for(MyShape sp : ShapeList)
			// sp.setConnectorShow(false);
		GPs.clearSelectedShapes();			
		
		switch(CurrentButtonMode)
		{
			case 0:
				MyShape[] SelectedShape = GPs.GetShapesUnderTheMouse(CurrentPoint);
				if(SelectedShape != null)
				{
					for(MyShape sp : SelectedShape)
					{
						sp.setConnectorShow(true);
					}
				}
				
				break;
			case 4:
				GPs.addG(new class_shape(CurrentPoint));
				break;
			case 5:
				GPs.addG(new UseCase_shape(CurrentPoint));
				break;
		}
		repaint();
	}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) 
	{
		System.out.println("Released");
		Point CurrentPoint = me.getPoint();
		MyLine line =null;
		MyShape[] GoodShape=  null ;
		switch(CurrentButtonMode)
		{
			case 0:
				if(SelectOrDragMode == false)
				{
					//Select many shapes mode
					Point TopLeft = new Point(Math.min(LastPressedPoint.x, CurrentPoint.x),Math.min(LastPressedPoint.y, CurrentPoint.y));
					Point DownRight = new Point(Math.max(LastPressedPoint.x, CurrentPoint.x),Math.max(LastPressedPoint.y, CurrentPoint.y));
					GPs.setConnShowUnderTheArea(TopLeft, DownRight);
				}
				else
				{
					//Drag mode end
					BeDraggedShape = null ; 
					SelectOrDragMode = false;
				}
				break;
			case 1:
				GoodShape = GPs.IsPriliegedLine(LastPressedPoint, CurrentPoint);
				if(GoodShape != null)
				{
					int Num_StartConnector = GoodShape[0].AlignAtConntector(LastPressedPoint);
					int Num_EndConnector = GoodShape[1].AlignAtConntector(CurrentPoint);
					line = new AssociationLine(GoodShape[0], GoodShape[1], Num_StartConnector, Num_EndConnector);
				}
				break;
			case 2:
				GoodShape = GPs.IsPriliegedLine(LastPressedPoint, CurrentPoint);
				if(GoodShape != null)
				{
					int Num_StartConnector = GoodShape[0].AlignAtConntector(LastPressedPoint);
					int Num_EndConnector = GoodShape[1].AlignAtConntector(CurrentPoint);
					line = new GeneralizationLine(GoodShape[0], GoodShape[1], Num_StartConnector, Num_EndConnector);
				}

				break;
			case 3:
				GoodShape = GPs.IsPriliegedLine(LastPressedPoint, CurrentPoint);
				if(GoodShape != null)
				{
					int Num_StartConnector = GoodShape[0].AlignAtConntector(LastPressedPoint);
					int Num_EndConnector = GoodShape[1].AlignAtConntector(CurrentPoint);
					line = new CompositionLine(GoodShape[0], GoodShape[1], Num_StartConnector, Num_EndConnector);
				}

				break;
		}
		if(line != null)
			GPs.addG(line);
		

		repaint();
		
	}
	
	//MouseMotionListener implementations mouseDragged, mouseMoved
	public void mouseDragged(MouseEvent me) 
	{
		System.out.println("Dragged");
		Point CurrentPoint = me.getPoint();
		switch(CurrentButtonMode)
		{
			case 0:	
			    if(SelectOrDragMode == true)
				{
					int offset_x = CurrentPoint.x - LastPressedPoint.x ;
					int offset_y = CurrentPoint.y - LastPressedPoint.y ;
					BeDraggedShape.move(offset_x, offset_y);
					LastPressedPoint = CurrentPoint ; 
				}				
				break;
		}
		repaint();
		
	}
	public void mouseMoved(MouseEvent me){}
	
	
	//MyObserver implementations update()
	public void updateButtonState()
	{
		CurrentButtonMode = BP.GetCurrentMode();
		System.out.println("Button Mode : "  +CurrentButtonMode);
	}
	public void updateBarState()
	{
		CurrentBarMode = MB.GetCurrentMode();
		System.out.println("Bar Mode : "  +CurrentBarMode);
		
		switch(CurrentBarMode)
		{
			case 0:
				GroupAction();
				break;
			case 1:
				UngroupAction();
				break;
			case 2:
				JFrame f =new JFrame("Change Object Name.");
				f.setSize(400,300);
				String input = JOptionPane.showInputDialog("Change Object Name");
				
				MyShape[] ToBeChangeNameShape = GPs.getSelectedShape();
				if(ToBeChangeNameShape != null && ToBeChangeNameShape.length ==1)
					ToBeChangeNameShape[0].setName(input);

				break;
		}
		repaint();
	}
	
	private void GroupAction()
	{
		MyShape[] Selected = GPs.getSelectedShape();
		if(Selected != null && Selected.length > 1 )
		{
			GPs.addG(new Group_shape(Selected));
		}
	}
	
	private void UngroupAction()
	{
		
		MyShape[] Selected = GPs.getSelectedShape();
		if(Selected != null && Selected.length == 1 && Selected[0] instanceof Group_shape)
		{
			((Group_shape)Selected[0]).removeGroup();
			GPs.removeG(Selected[0]);
		}
		
			
	}

}