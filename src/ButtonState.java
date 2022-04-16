package MyObserver;
import MyObserver.*;
public interface ButtonState
{
	void addObserver(MyObserver ob);
	void removeObserver(MyObserver ob);
	void NotifyObservers();
}