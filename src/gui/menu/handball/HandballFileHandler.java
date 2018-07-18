package gui.menu.handball;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import gui.menu.FileHandler;
import model.handball.HandballAttributes;

public class HandballFileHandler
	extends FileHandler<HandballAttributes>
{
	@Override
	protected HandballAttributes parseAttributes(JsonObject attr)
	{
		Gson gson = new GsonBuilder().create();

		return gson.fromJson(attr, HandballAttributes.class);
	}
}
