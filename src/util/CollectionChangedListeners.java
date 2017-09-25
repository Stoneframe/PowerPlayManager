package util;

import java.util.EventListener;

public interface CollectionChangedListeners
	extends
		EventListener
{
	public void collectionChanged(Object source, CollectionChangedEvent event);
}
