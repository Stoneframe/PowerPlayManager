package util;

import java.util.EventObject;

public class CollectionChangedEvent
	extends EventObject
{
	private static final long serialVersionUID = -1145036641593124455L;

	public static final int ADDED = 0;
	public static final int REMOVED = 1;

	private int action;
	private int indexChanged;
	private Object objectChanged;

	public CollectionChangedEvent(
			Object source,
			int action,
			int indexChanged,
			Object objectChanged)
	{
		super(source);

		this.action = action;
		this.objectChanged = objectChanged;
		this.indexChanged = indexChanged;
	}

	public int getAction()
	{
		return action;
	}

	public int getIndexChanged()
	{
		return indexChanged;
	}

	public Object getObjectChanged()
	{
		return objectChanged;
	}
}
