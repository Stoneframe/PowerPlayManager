package util;

import java.util.EventListener;

public interface PropertyChangedListener
	extends
		EventListener
{
	public void propertyChanged(Object source, PropertyChangedEvent event);
}
