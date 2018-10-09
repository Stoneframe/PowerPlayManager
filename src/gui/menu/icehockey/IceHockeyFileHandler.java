package gui.menu.icehockey;

import com.google.gson.Gson;

import gui.menu.FileHandler;
import model.Player;
import model.icehockey.IceHockeyAttributes;
import model.icehockey.IceHockeyPlayer;

public class IceHockeyFileHandler
	extends FileHandler<IceHockeyAttributes>
{
	@Override
	protected String convertPlayer(Gson gson, Player<IceHockeyAttributes> player)
	{
		return gson.toJson(player, IceHockeyPlayer.class);
	}

	@Override
	protected Player<IceHockeyAttributes> convertPlayer(Gson gson, String json)
	{
		return gson.fromJson(json, IceHockeyPlayer.class);
	}
}
