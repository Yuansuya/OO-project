package IBarBehavior;
import IBarBehavior.*;
import MyGraphic.*;
public class GroupBehavior implements IBarBehavior
{
	@Override
	public void perform(MyGraphic graphics, Integer depth)
	{
		MyShape[] Selected = graphics.getSelectedShape();
		if(Selected != null && Selected.length > 1 )
		{
			graphics.addG(new Group_shape(Selected, depth));
		}
		depth++;
	}
}