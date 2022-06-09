package IBarBehavior;
import IBarBehavior.*;
import MyGraphic.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class SetNameBehavior implements IBarBehavior
{
	@Override
	public void perform(MyGraphic graphics, Integer depth)
	{
		JFrame f =new JFrame("Change Object Name.");
		f.setSize(400,300);
		String input = JOptionPane.showInputDialog("Change Object Name");
		if(input != null)
		{
			MyShape[] ToBeChangeNameShape = graphics.getSelectedShape();
			if(ToBeChangeNameShape != null && ToBeChangeNameShape.length ==1)
				ToBeChangeNameShape[0].setName(input);
		}
		
	}
}