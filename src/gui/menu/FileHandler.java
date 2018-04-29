package gui.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import gui.gson.SideAdapter;
import model.Attributes;
import model.Roster;
import model.Side;

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
		
//		GsonBuilder builder = new GsonBuilder();
//		builder.registerTypeAdapter(Side.class, new SideAdapter());
//		Gson gson = builder.create();
//		
//		String json = gson.toJson(roster);
//		System.out.println(json);
		
//		Gson gson = new Gson();
//        String json = gson.toJson(roster);
//        System.out.println(json);
		

//		try
//		{
			// FIXME use JsonWriter?
//			FileWriter writer = new FileWriter(file);
//			FileOutputStream writer = new FileOutputStream(System.out);
//			String gsonString = gson.toJson(roster);
//			System.out.println(gsonString);
			//writer.write(gson.toJson(roster));
//			writer.close();
//		}
//		catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public static <A extends Attributes> Roster<A> loadRosterFromFile(File file)
	{
		try
		{
			JsonReader reader = new JsonReader(new FileReader(file));
			JsonElement jelement = new JsonParser().parse("{\"players\":[{\"name\":\"Sebastian Lilja\",\"age\":23}]}");
			jelement = new JsonParser().parse("{\"players\":[{\"name\":\"Sebastian Lilja\",\"age\":23},{\"name\":\"Oliver Gustavsson\",\"age\":23},{\"name\":\"Martin Kristiansson\",\"age\":22},{\"name\":\"Jim Söderqvist\",\"age\":23}]}");
			jelement = new JsonParser().parse(reader);
			JsonObject  jobject = jelement.getAsJsonObject();
			JsonArray jarray = jobject.getAsJsonArray("players");
			for (int i=0; i<jarray.size(); i++) {
				JsonObject player = jarray.get(i).getAsJsonObject();
				String name = player.get("name").getAsString();
				int age = player.get("age").getAsInt();
				System.out.println("name: " + name + " age: " + age);
			}
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//		GsonBuilder builder = new GsonBuilder();
//		builder.registerTypeAdapter(Side.class, new SideAdapter());
//		Gson gson = builder.create();
//		try
//		{
//			JsonReader reader = new JsonReader(new FileReader(file));
//			Roster<A> roster = gson.fromJson(reader, Roster.class);
//			return roster;
//		}
//		catch (FileNotFoundException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}

}
