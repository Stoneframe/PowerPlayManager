package gui.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import gson.SideAdapter;
import model.Attributes;
import model.Roster;
import model.Side;

public class FileHandler
{
	public static <A extends Attributes> void saveRosterToFile(
			File file,
			Roster<A> roster)
	{
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Side.class, new SideAdapter());
		Gson gson = builder.create();

		try
		{
			// FIXME use JsonWriter?
			FileWriter writer = new FileWriter(file);
			writer.write(gson.toJson(roster));
			writer.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static <A extends Attributes> Roster<A> loadRosterFromFile(File file)
	{
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Side.class, new SideAdapter());
		Gson gson = builder.create();
		try
		{
			JsonReader reader = new JsonReader(new FileReader(file));
			Roster<A> roster = gson.fromJson(reader, Roster.class);
			return roster;
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
