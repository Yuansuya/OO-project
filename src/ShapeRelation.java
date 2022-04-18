package MyShape;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyShape.*;
import MyLine.*;
public class ShapeRelation
{
	MyShape StartShape ;
	MyShape EndShape ;
	int StartConnector; //0: East, 1: West, 2: South, 3: North
	int EndConnector; //0: East, 1: West, 2: South, 3: North
	private MyLine ConnectionLine;	
	
	
	public ShapeRelation(MyShape _StartShape, MyShape _EndShape, int _StartDir, int _EndDir, MyLine _ConnectionLine)
	{
		this.StartShape = _StartShape;
		this.EndShape = _EndShape;
		this.StartConnector = _StartDir;
		this.EndConnector = _EndDir;		
		this.ConnectionLine = _ConnectionLine;
	}
	
	public MyLine getLine()
	{
		return this.ConnectionLine;
	}
}