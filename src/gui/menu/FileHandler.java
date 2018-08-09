package gui.menu;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Attributes;
import model.Player;
import model.Roster;
import parsers.SideParser;

public abstract class FileHandler<A extends Attributes>
{
	public void saveRosterToFile(File file, Roster<A> roster)
	{
		try (FileWriter writer = new FileWriter(file))
		{
			writer.write(roster.toJson());
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

	public Roster<A> loadRosterFromFile(File file, Roster<A> roster)
	{
		try (JsonReader reader = new JsonReader(new FileReader(file)))
		{
			JsonElement jelement = new JsonParser().parse(reader);
			JsonObject jobject = jelement.getAsJsonObject();
			JsonArray jarray = jobject.getAsJsonArray("players");

			List<Player<A>> fileRoster = new LinkedList<>();

			for (int i = 0; i < jarray.size(); i++)
			{
				JsonObject player = jarray.get(i).getAsJsonObject();
				JsonObject attr = player.get("attributes").getAsJsonObject();

				A attributes = parseAttributes(attr);

				Player<A> newPlayer = new Player<>(
						player.get("name").getAsString(),
						player.get("age").getAsInt(),
						player.get("cl").getAsInt(),
						SideParser.parseSide(player.get("side").getAsString()),
						attributes,
						player.get("experience").getAsInt(),
						player.get("chemistry").getAsInt(),
						player.get("energy").getAsInt(),
						player.get("training").getAsDouble());

				fileRoster.add(newPlayer);
			}

			roster.addAll(fileRoster);
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(
				null,
				"Cannot open file",
				"File Error",
				JOptionPane.WARNING_MESSAGE);
		}

		return null;
	}

	protected abstract A parseAttributes(JsonObject attr);
}
