import MyShape.*;
import MyLine.*;
import MyPanel.*;
import MyMenuBar.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
public class MainFrame 
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(800, 600);
		/*Panels and MenuBar add begin*/
		
		ButtonPanel buttonpanel = new ButtonPanel(new Point(10, 10), 100, 550);
		frame.add(buttonpanel);
		DrawPanel drawpanel = new DrawPanel(new Point(150,10), 600, 550,buttonpanel);
		frame.add(drawpanel);
		MyMenuBar mb = new MyMenuBar();
		frame.setMenuBar(mb);
		/*Panels and MenuBar add end */
		
		buttonpanel.addObserver(drawpanel);
		
		
		frame.setVisible(true);
	}
}