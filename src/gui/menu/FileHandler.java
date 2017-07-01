package gui.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import model.Roster;
import model.Side;
import model.SideAdapter;

public class FileHandler
{
	public static void saveRosterToFile(File file, Roster roster)
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

	public static Roster loadRosterFromFile(File file)
	{
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Side.class, new SideAdapter());
		Gson gson = builder.create();
		try
		{
			JsonReader reader = new JsonReader(new FileReader(file));
			Roster roster = gson.fromJson(reader, Roster.class);
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
