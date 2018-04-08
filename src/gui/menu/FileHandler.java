package gui.menu;

import gui.MainFrame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import parsers.SideParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Attributes;
import model.Player;
import model.Roster;
import model.handball.HandballAttributes;
import model.handball.HandballPlayer;

public class FileHandler
{
	public static <A extends Attributes> void saveRosterToFile(
			File file,
			Roster<A> roster,
			String sport)
	{
		FileWriter writer;
		try
		{
			writer = new FileWriter(file);
			writer.write(roster.toJson(sport));
			writer.close();
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(
				null,
				"Cannot write to file",
				"File Error",
				JOptionPane.WARNING_MESSAGE);
		}
	}

	public static <A extends Attributes> Roster<A> loadRosterFromFile(
			File file,
			Roster<A> roster)
	{
		try
		{
			List<Player<A>> fileRoster = new LinkedList<>();
			JsonReader reader = new JsonReader(new FileReader(file));
			JsonElement jelement = new JsonParser().parse(reader);
			JsonObject jobject = jelement.getAsJsonObject();
			String sport = jobject.get("sport").getAsString();
			JsonArray jarray = jobject.getAsJsonArray("players");
			for (int i = 0; i < jarray.size(); i++)
			{
				JsonObject player = jarray.get(i).getAsJsonObject();
				String name = player.get("name").getAsString();
				int age = player.get("age").getAsInt();
				int cl = player.get("cl").getAsInt();
				String side = player.get("side").getAsString();
				double training = player.get("training").getAsDouble();
				JsonObject attr = player.get("attributes").getAsJsonObject();
				if (sport.equals(MainFrame.HANDBALL_TITLE))
				{
					HandballAttributes attributes = parseHandballAttributes(attr);
					Player newPlayer = new HandballPlayer(name,
							age,
							cl,
							SideParser.parseSide(side),
							attributes,
							0, 0, 100,
							training);
					fileRoster.add(newPlayer);
				}
			}

			roster.addAll(fileRoster);
		}
		catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(
				null,
				"Cannot open file",
				"File Error",
				JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	private static HandballAttributes parseHandballAttributes(JsonObject attr)
	{
		Gson gson = new GsonBuilder().create();
		HandballAttributes attributes = gson.fromJson(
			attr,
			HandballAttributes.class);

		return attributes;
	}
}
