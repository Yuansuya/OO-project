package MyGraphic;
import MyGraphic.*;
import java.util.*;
public class AllShapes
{
	private static AllShapes instance;
	private java.util.List<MyGraphic> graphics ;
	private AllShapes()
	{
		graphics = new ArrayList<>();
	}
	
	public static AllShapes getInstance()
	{
		if(instance == null)
			instance = new AllShapes();
		return instance;
	}
	
	
	public java.util.List<MyGraphic> getGraphics()
	{
		return this.graphics; 
	}
	
}