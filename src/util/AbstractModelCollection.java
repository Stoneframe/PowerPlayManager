package util;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractModelCollection
{
	private transient List<CollectionChangedListener> listeners;

	public void addCollectionChangedListener(CollectionChangedListener listener)
	{
		if (listeners == null) listeners = new LinkedList<>();

		listeners.add(listener);
	}

	public void removeCollectionChangedListener(CollectionChangedListener listener)
	{
		if (listeners == null) listeners = new LinkedList<>();

		listeners.remove(listener);
	}

	protected void fireCollectionChanged(
		int action,
		int indexChanged,
		Object objectChanged)
	{
		if (listeners == null) listeners = new LinkedList<>();

		for (CollectionChangedListener listener : listeners)
		{
			listener.collectionChanged(
				this,
				new CollectionChangedEvent(
					this,
					action,
					indexChanged,
					objectChanged));
		}
	}
}
