package model;

public abstract class Side
{
	public static final Side UNKNOWN = new Unknown();
	public static final Side LEFT = new Left();
	public static final Side UNIVERSAL = new Universal();
	public static final Side RIGHT = new Right();

	public static Side parse(String string)
	{
		if (Side.LEFT.toString().equals(string) || string.equalsIgnoreCase("L"))
		{
			return Side.LEFT;
		}
		else if (Side.RIGHT.toString().equals(string) || string.equalsIgnoreCase("R"))
		{
			return Side.RIGHT;
		}
		else if (Side.UNIVERSAL.toString().equals(string) || string.equalsIgnoreCase("U"))
		{
			return Side.UNIVERSAL;
		}
		else
		{
			return Side.UNKNOWN;
		}
	}

	public abstract boolean matches(Side side);

	@Override
	public abstract String toString();

	private static class Unknown
		extends Side
	{
		@Override
		public boolean matches(Side side)
		{
			return side == UNKNOWN;
		}

		@Override
		public String toString()
		{
			return "Unknown";
		}
	}

	private static class Left
		extends Side
	{
		@Override
		public boolean matches(Side side)
		{
			return side == LEFT || side == UNIVERSAL;
		}

		@Override
		public String toString()
		{
			return "Left";
		}
	}

	private static class Universal
		extends Side
	{
		@Override
		public boolean matches(Side side)
		{
			return true;
		}

		@Override
		public String toString()
		{
			return "Universal";
		}
	}

	private static class Right
		extends Side
	{
		@Override
		public boolean matches(Side side)
		{
			return side == RIGHT || side == UNIVERSAL;
		}

		@Override
		public String toString()
		{
			return "Right";
		}
	}
}
