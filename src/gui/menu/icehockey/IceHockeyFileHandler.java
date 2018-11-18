package gui.menu.icehockey;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import gui.menu.FileHandler;
import model.Player;
import model.Roster;
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

	@Override
	protected Type getRosterType()
	{
		return new TypeToken<Roster<IceHockeyAttributes>>()
		{
		}.getType();
	}
}
