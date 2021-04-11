package util;

import java.util.EventObject;

public class PropertyChangedEvent
	extends EventObject
{
	private static final long serialVersionUID = -2598466606625997422L;

	private String propertyName;
	private Object propertyValue;

	public PropertyChangedEvent(
		Object source,
		String propertyName,
		Object propertyValue)
	{
		super(source);

		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}

	public String getPropertyName()
	{
		return propertyName;
	}

	public Object getPropertyValue()
	{
		return propertyValue;
	}
}
