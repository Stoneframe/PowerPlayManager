package util;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractModelObject
{
	private transient List<PropertyChangedListener> listeners;

	public void addPropertyChangedListener(PropertyChangedListener listener)
	{
		if (listeners == null) listeners = new LinkedList<>();

		listeners.add(listener);
	}

	public void removePropertyChangedListener(PropertyChangedListener listener)
	{
		if (listeners == null) listeners = new LinkedList<>();

		listeners.remove(listener);
	}

	protected void firePropertyChanged(
		String propertyName,
		Object propertyValue)
	{
		firePropertyChanged(
			new PropertyChangedEvent(this, propertyName, propertyValue));
	}

	protected void firePropertyChanged(PropertyChangedEvent event)
	{
		if (listeners == null) listeners = new LinkedList<>();

		for (PropertyChangedListener listener : listeners)
		{
			listener.propertyChanged(this, event);
		}
	}
}
