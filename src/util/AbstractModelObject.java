package util;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractModelObject
{
	private List<PropertyChangedListener> propertyChangedListeners;

	public AbstractModelObject()
	{
		propertyChangedListeners = new LinkedList<PropertyChangedListener>();
	}

	public void addPropertyChangedListener(PropertyChangedListener listener)
	{
		propertyChangedListeners.add(listener);
	}

	public void removePropertyChangedListener(PropertyChangedListener listener)
	{
		propertyChangedListeners.remove(listener);
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
		for (PropertyChangedListener listener : propertyChangedListeners)
		{
			listener.propertyChanged(this, event);
		}
	}
}
