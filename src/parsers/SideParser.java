package parsers;

import model.Side;

public class SideParser
{
	public static Side parseSide(String side)
	{
		side = side.trim();
		if (side.equals("Universal") || side.equals("U"))
		{
			return Side.UNIVERSAL;
		}
		else if (side.equals("Vänster") || side.equals("Left") || side.equals("L"))
		{
			return Side.LEFT;
		}
		else if (side.equals("Höger") || side.equals("Right") || side.equals("R"))
		{
			return Side.RIGHT;
		}
		else
		{
			return Side.UNKNOWN;
		}
	}
}
