package gui.util;

public class IntegerUtil
{
	public static int parseInt(String str, int def)
	{
		try
		{
			return Integer.parseInt(str);
		}
		catch (Exception e)
		{
			return def;
		}
	}
}
