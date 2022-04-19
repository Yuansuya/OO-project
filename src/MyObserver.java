package MyObserver;
import MyObserver.*;
public interface MyObserver
{
	void addSubscriber(MySubscriber ob);
	void removeSubscriber(MySubscriber ob);
	void NotifySubscriber();
}