import MyShape.*;
import MyLine.*;
import MyPanel.*;
import MyMenuBar.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import MyGraphic.GroupGraphic;
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
		
		GroupGraphic Grphics = new GroupGraphic();
		
		
		ButtonPanel buttonpanel = new ButtonPanel(new Point(10, 10), 100, 550, Grphics);
		MyMenuBar mb = new MyMenuBar();
		DrawPanel drawpanel = new DrawPanel(new Point(150,10), 600, 550,buttonpanel, mb, Grphics);
		
		frame.add(buttonpanel);
		frame.add(drawpanel);
		frame.setMenuBar(mb);
		/*Panels and MenuBar add end */
		
		buttonpanel.addObserver(drawpanel);
		mb.addObserver(drawpanel);
		
		frame.setVisible(true);
	}
}