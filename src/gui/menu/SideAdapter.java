package gui.menu;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import model.Side;

public final class SideAdapter
	extends TypeAdapter<Side>
{
	@Override
	public Side read(JsonReader reader) throws IOException
	{
		String s = reader.nextString();
		
		return Side.parse(s);
	}

	@Override
	public void write(JsonWriter writer, Side side) throws IOException
	{
		writer.value(side.toString());
	}
}
