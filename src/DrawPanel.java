package MyPanel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import observer_pattern.*;
import MyPanel.*;
import MyGraphic.*;
import MyMenuBar.*;
import MyButton.*;
public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, observer
{
	private ButtonPanel BP;   // for Observable
	private MyMenuBar MB;   // for Observable
	private MyButton CurrentButton = null;
	private int CurrentBarMode = -1;
	private MyGraphic GroupGraphics ;
	
	public DrawPanel(Point StartPoint,int WidthSize,int HeightSize,ButtonPanel bp, MyMenuBar mb, MyGraphic gg ) {
		this.setLayout(null);
		this.setLocation(StartPoint.x, StartPoint.y);
		this.setSize(WidthSize, HeightSize);
		this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.BP = bp;
		this.MB = mb;
		this.GroupGraphics = gg; 
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.black);
		GroupGraphics.Draw(g);
	}
	
	//MouseListener implementations mousePressed, mouseClicked, mouseEntered, mouseExited, mouseReleased
	public void mousePressed(MouseEvent me) 
	{
		System.out.println("Pressed");
		Point CurrentPoint = me.getPoint();
		
		
		if(CurrentButton != null)
		{
			CurrentButton.getMode().PressedAction(CurrentPoint);
		}
		
	}
	public void mouseClicked(MouseEvent me) {
		Point CurrentPoint = me.getPoint();
		// GPs.clearSelectedShapes();			
		if(CurrentButton != null)
		{
			CurrentButton.getMode().ClickAction(CurrentPoint);
		}
		
		repaint();
	}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mouseReleased(MouseEvent me) 
	{
		System.out.println("Released");
		Point CurrentPoint = me.getPoint();
		if(CurrentButton != null)
		{
			CurrentButton.getMode().ReleaseAction(CurrentPoint);
		}
		repaint();
		
	}
	
	//MouseMotionListener implementations mouseDragged, mouseMoved
	public void mouseDragged(MouseEvent me) 
	{
		System.out.println("Dragged");
		Point CurrentPoint = me.getPoint();
		if(CurrentButton != null)
		{
			CurrentButton.getMode().DragAction(CurrentPoint);
		}
		repaint();
		
	}
	public void mouseMoved(MouseEvent me){}
	
	
	//MyObserver implementations update()
	public void updateButtonState()
	{
		CurrentButton = BP.GetButtonInProgress();
		// System.out.println("Button Mode : "  +CurrentButtonMode);
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
				
				MyShape[] ToBeChangeNameShape = GroupGraphics.getSelectedShape();
				if(ToBeChangeNameShape != null && ToBeChangeNameShape.length ==1)
					ToBeChangeNameShape[0].setName(input);

				break;
		}
		repaint();
	}
	
	private void GroupAction()
	{
		MyShape[] Selected = GroupGraphics.getSelectedShape();
		if(Selected != null && Selected.length > 1 )
		{
			GroupGraphics.addG(new Group_shape(Selected));
		}
	}
	
	private void UngroupAction()
	{
		
		MyShape[] Selected = GroupGraphics.getSelectedShape();
		if(Selected != null && Selected.length == 1 )
		{
			if(Selected[0].removeGroup() == true)
			{
				GroupGraphics.removeG(Selected[0]);
			}
		}
	}

}