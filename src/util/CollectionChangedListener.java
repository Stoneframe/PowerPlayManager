package util;

import java.util.EventListener;

public interface CollectionChangedListener
	extends
		EventListener
{
	public void collectionChanged(Object source, CollectionChangedEvent event);
}
