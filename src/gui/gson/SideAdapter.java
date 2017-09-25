package gui.gson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import model.Side;

public final class SideAdapter
	extends TypeAdapter<Side>
{

	public SideAdapter()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public Side read(JsonReader reader) throws IOException
	{
		String s = reader.nextString();
		// FIXME switch?
		if (Side.LEFT.toString().equals(s))
		{
			return Side.LEFT;
		}
		else if (Side.RIGHT.toString().equals(s))
		{
			return Side.RIGHT;
		}
		else if (Side.UNIVERSAL.toString().equals(s))
		{
			return Side.UNIVERSAL;
		}
		return Side.UNKNOWN;
	}

	@Override
	public void write(JsonWriter writer, Side side) throws IOException
	{
		writer.value(side.toString());
	}
}
