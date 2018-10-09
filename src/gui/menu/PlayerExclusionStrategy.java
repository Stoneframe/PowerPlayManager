package gui.menu;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class PlayerExclusionStrategy
	implements
		ExclusionStrategy
{
	@Override
	public boolean shouldSkipClass(Class<?> cls)
	{
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes attr)
	{
		return attr.getName().equals("training");
	}
}
