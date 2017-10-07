package model;

public abstract class Side
{
	public static final Side UNKNOWN = new Unknown();
	public static final Side LEFT = new Left();
	public static final Side UNIVERSAL = new Universal();
	public static final Side RIGHT = new Right();

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract String toString();

	private static class Unknown
		extends Side
	{
		@Override
		public boolean equals(Object obj)
		{
			return obj == UNKNOWN;
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
		public boolean equals(Object obj)
		{
			return obj == LEFT || obj == UNIVERSAL;
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
		public boolean equals(Object obj)
		{
			return obj instanceof Side;
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
		public boolean equals(Object obj)
		{
			return obj == RIGHT || obj == UNIVERSAL;
		}

		@Override
		public String toString()
		{
			return "Right";
		}
	}
}
