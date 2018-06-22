package gui.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import parsers.SideParser;

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
		System.out.println(roster.toJson(sport));
		FileWriter writer;
		try
		{
			writer = new FileWriter(file);
			writer.write(roster.toJson(sport));
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
				double training = player.get("training").getAsDouble();
				Player newPlayer = new HandballPlayer(name,
													  age, 
													  cl, 
													  SideParser.parseSide(side), 
													  attributes, 
													  training);
				fileRoster.add(newPlayer); 
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
		attributes.setQGoa(attr.get("qGoa").getAsInt());
		attributes.setFip(attr.get("fip").getAsInt());
		attributes.setQFip(attr.get("qFip").getAsInt());
		attributes.setSho(attr.get("sho").getAsInt());
		attributes.setQSho(attr.get("qSho").getAsInt());
		attributes.setBlk(attr.get("blk").getAsInt());
		attributes.setQBlk(attr.get("qBlk").getAsInt());
		attributes.setPas(attr.get("pas").getAsInt());
		attributes.setQPas(attr.get("qPas").getAsInt());
		attributes.setTec(attr.get("tec").getAsInt());
		attributes.setQTec(attr.get("qTec").getAsInt());
		attributes.setSpe(attr.get("spe").getAsInt());
		attributes.setQSpe(attr.get("qSpe").getAsInt());
		attributes.setAgr(attr.get("agr").getAsInt());
		attributes.setQAgr(attr.get("qAgr").getAsInt());
		
		return attributes;
	}

}
