package util;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractModelCollection
{
	private List<CollectionChangedListeners> collectionChangedListeners;

	public AbstractModelCollection()
	{
		collectionChangedListeners =
				new LinkedList<CollectionChangedListeners>();
	}

	public void addCollectionChangedListener(
			CollectionChangedListeners listeners)
	{
		collectionChangedListeners.add(listeners);
	}

	public void removeCollectionChangedListener(
			CollectionChangedListeners listeners)
	{
		collectionChangedListeners.remove(listeners);
	}

	protected void fireCollectionChanged(
			int action,
			int indexChanged,
			Object objectChanged)
	{
		for (CollectionChangedListeners listeners : collectionChangedListeners)
		{
			listeners.collectionChanged(
				this,
				new CollectionChangedEvent(
						this,
						action,
						indexChanged,
						objectChanged));
		}
	}
}
