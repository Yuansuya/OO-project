import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyShape.*;
import MyLine.*;
public class ShapeRelation
{
	MyShape StartShape ;
	MyShape EndShape ;
	int StartDir; //0: East, 1: West, 2: South, 3: North
	int EndDir; //0: East, 1: West, 2: South, 3: North
	MyLine ConnectionLine;	
	
	
	public ShapeRelation(MyShape _StartShape, MyShape _EndShape, int _StartDir, int _EndDir, MyLine _ConnectionLine)
	{
		this.StartShape = _StartShape;
		this.EndShape = _EndShape;
		this.StartDir = _StartDir;
		this.EndDir = _EndDir;
		this.ConnectionLine = _ConnectionLine;
	}
}