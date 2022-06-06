package IBarBehavior;
import IBarBehavior.*;
import MyGraphic.*;
public class UnGroupBehavior implements IBarBehavior
{
	@Override
	public void perform(MyGraphic graphics, Integer depth)
	{
		MyShape[] Selected = graphics.getSelectedShape();
		if(Selected != null && Selected.length == 1 )
		{
			if(Selected[0].removeGroup() == true)
			{
				graphics.removeG(Selected[0]);
			}
		}
	}
}