package gui.menu.handball;

import com.google.gson.Gson;

import gui.menu.FileHandler;
import model.Player;
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
}
