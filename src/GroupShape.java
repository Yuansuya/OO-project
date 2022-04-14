package MyShape;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GroupShape extends MyShape
{
	protected java.util.List<MyShape> List_shape = new ArrayList<>();
	public GroupShape(MyShape[] shapes)
	{
		super(shapes);
		List_shape = Arrays.asList(shapes);
	}
	
	protected void DrawShape(Graphics g)
	{
		for(MyShape sh : this.List_shape)
			sh.DrawShape(g);
		for(int start = 0 ; start < 4 ; ++start )
		{
			int end = start +1 ;
			end %= 4;
			g.drawLine(super.Corners[start].x, super.Corners[start].y, super.Corners[end].x, super.Corners[end].y);
		}
		
	}
	
}