package observer_pattern;
import observer_pattern.*;
public interface Subject
{
	void addObserver(observer ob);
	void removeObserver(observer ob);
	void NotifyObserver();
}