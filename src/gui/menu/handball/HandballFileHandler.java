package gui.menu.handball;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import gui.menu.FileHandler;
import model.Player;
import model.Roster;
import model.handball.HandballAttributes;
import model.handball.HandballPlayer;

public class HandballFileHandler
	extends FileHandler<HandballAttributes>
{
	@Override
	protected String convertPlayer(Gson gson, Player<HandballAttributes> player)
	{
		return gson.toJson(player, HandballPlayer.class);
	}

	@Override
	protected Player<HandballAttributes> convertPlayer(Gson gson, String json)
	{
		return gson.fromJson(json, HandballPlayer.class);
	}

	@Override
	protected Type getRosterType()
	{
		return new TypeToken<Roster<HandballAttributes>>()
		{
		}.getType();
	}
}
