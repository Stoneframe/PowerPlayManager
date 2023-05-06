package searcher;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import evaluators.PlayerEvaluator;
import model.Attributes;
import warper.PlayerWarper;

public class SearchTemplateStorage<A extends Attributes>
{
	private final String name;
	private final PlayerEvaluator<A> playerEvaluator;
	private final PlayerWarper<A> playerWarper;

	private List<SearchTemplate<A>> templates = new LinkedList<>();

	public SearchTemplateStorage(
		String name,
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper)
	{
		this.name = name;

		this.playerEvaluator = playerEvaluator;
		this.playerWarper = playerWarper;
	}

	public List<SearchTemplate<A>> getTemplates()
	{
		load();

		return Collections.unmodifiableList(templates);
	}

	public void add(SearchTemplate<A> template)
	{
		templates.removeIf(t -> t.getName().equals(template.getName()));
		templates.add(template);

		save();
	}

	public void remove(String templateName)
	{
		templates.removeIf(t -> t.getName().equals(templateName));

		save();
	}

	public SearchTemplate<A> get(String name)
	{
		return templates.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
	}

	public boolean contains(String name)
	{
		return templates.stream().anyMatch(t -> t.getName().equals(name));
	}

	@SuppressWarnings("unchecked")
	private void load()
	{
		try
		{
			Path path = Paths.get(System.getenv("APPDATA"), "PPM Assistant", name);

			Gson gson = getGson();

			templates.clear();

			List<String> lines = Files.readAllLines(path);

			for (String json : lines)
			{
				SearchTemplate<A> template =
					gson.fromJson(json, new SearchTemplate<A>(null, null).getClass());

				templates.add(template);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void save()
	{
		try
		{
			Path path = Paths.get(System.getenv("APPDATA"), "PPM Assistant", name);

			Gson gson = getGson();

			List<String> lines = new LinkedList<>();

			for (SearchTemplate<A> template : templates)
			{
				lines.add(gson.toJson(template));
			}

			Files.write(path, lines);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private Gson getGson()
	{
		return new GsonBuilder()
			.registerTypeAdapter(PlayerEvaluator.class, new PlayerEvaluatorAdapter(playerEvaluator))
			.registerTypeAdapter(PlayerWarper.class, new PlayerWarperAdapter(playerWarper))
			.registerTypeAdapter(SearchCriteria.class, new PropertyBasedInterfaceMarshal())
			.create();
	}

	private class PlayerEvaluatorAdapter
		extends TypeAdapter<PlayerEvaluator<A>>
	{
		private PlayerEvaluator<A> playerEvaluator;

		public PlayerEvaluatorAdapter(PlayerEvaluator<A> playerEvaluator)
		{
			this.playerEvaluator = playerEvaluator;
		}

		@Override
		public PlayerEvaluator<A> read(JsonReader arg0) throws IOException
		{
			return playerEvaluator;
		}

		@Override
		public void write(JsonWriter arg0, PlayerEvaluator<A> arg1) throws IOException
		{

		}
	}

	private class PlayerWarperAdapter
		extends TypeAdapter<PlayerWarper<A>>
	{
		private PlayerWarper<A> playerWarper;

		public PlayerWarperAdapter(PlayerWarper<A> playerEvaluator)
		{
			this.playerWarper = playerEvaluator;
		}

		@Override
		public PlayerWarper<A> read(JsonReader arg0) throws IOException
		{
			return playerWarper;
		}

		@Override
		public void write(JsonWriter arg0, PlayerWarper<A> arg1) throws IOException
		{

		}
	}

	public class PropertyBasedInterfaceMarshal
		implements
			JsonSerializer<Object>,
			JsonDeserializer<Object>
	{
		private static final String CLASS_META_KEY = "CLASS_META_KEY";

		@Override
		public Object deserialize(
			JsonElement jsonElement,
			Type type,
			JsonDeserializationContext jsonDeserializationContext)
				throws JsonParseException
		{
			JsonObject jsonObj = jsonElement.getAsJsonObject();
			String className = jsonObj.get(CLASS_META_KEY).getAsString();
			try
			{
				Class<?> clz = Class.forName(className);
				return jsonDeserializationContext.deserialize(jsonElement, clz);
			}
			catch (ClassNotFoundException e)
			{
				throw new JsonParseException(e);
			}
		}

		@Override
		public JsonElement serialize(
			Object object,
			Type type,
			JsonSerializationContext jsonSerializationContext)
		{
			JsonElement jsonEle = jsonSerializationContext.serialize(object, object.getClass());
			jsonEle.getAsJsonObject()
				.addProperty(
					CLASS_META_KEY,
					object.getClass().getCanonicalName());
			return jsonEle;
		}
	}
}
