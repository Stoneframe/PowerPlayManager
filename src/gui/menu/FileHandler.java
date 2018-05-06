package gui.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.Attributes;
import model.Player;
import model.Roster;
import model.Side;
import model.handball.HandballAttributes;
import model.handball.HandballPlayer;

public class FileHandler
{
	public static <A extends Attributes> void saveRosterToFile(
			File file,
			Roster<A> roster)
	{
		System.out.println(roster.toJson());
		FileWriter writer;
		try
		{
			writer = new FileWriter(file);
			writer.write(roster.toJson());
			writer.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static <A extends Attributes> Roster<A> loadRosterFromFile(File file, Roster<A> roster)
	{
		try
		{
//			Roster<HandballAttributes> fileRoster = new Roster<>();
			List<Player<A>> fileRoster = new LinkedList<>();
			JsonReader reader = new JsonReader(new FileReader(file));
			JsonElement jelement = new JsonParser().parse(reader);
			JsonObject  jobject = jelement.getAsJsonObject();
			JsonArray jarray = jobject.getAsJsonArray("players");
			for (int i=0; i<jarray.size(); i++) {
				JsonObject player = jarray.get(i).getAsJsonObject();
				String name = player.get("name").getAsString();
				int age = player.get("age").getAsInt();
				int cl = player.get("cl").getAsInt();
				String side = player.get("side").getAsString();
				System.out.println("name: " + name + " age: " + age + " " + cl + " " + side);
				//XXX
				JsonObject attr = player.get("attributes").getAsJsonObject();
				HandballAttributes attributes = parseHandballAttributes(attr);
				fileRoster.add((Player<A>) new HandballPlayer(name, age, cl, Side.UNKNOWN, new HandballAttributes(), 0.01));
			}
			
//			System.out.println("loaded roster:");
//			System.out.println(fileRoster.toJson());
			roster.addAll(fileRoster);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static HandballAttributes parseHandballAttributes(JsonObject attr)
	{
		HandballAttributes attributes = new HandballAttributes();
		attributes.setGoa(attr.get("goa").getAsInt());
		//XXX
//		int age = player.get("age").getAsInt();
		int goa;
		int fip;
		int sho;
		int blk;
		int pas;
		int tec;
		int spe;
		int agr;

		int qGoa;
		int qFip;
		int qSho;
		int qBlk;
		int qPas;
		int qTec;
		int qSpe;
		int qAgr;
		return null;
	}

}
