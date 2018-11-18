package gui.menu.football;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import gui.menu.FileHandler;
import model.Player;
import model.Roster;
import model.football.FootballAttributes;
import model.football.FootballPlayer;

public class FootballFileHandler
	extends FileHandler<FootballAttributes>
{
	@Override
	protected String convertPlayer(Gson gson, Player<FootballAttributes> player)
	{
		return gson.toJson(player, FootballPlayer.class);
	}

	@Override
	protected Player<FootballAttributes> convertPlayer(Gson gson, String json)
	{
		return gson.fromJson(json, FootballPlayer.class);
	}

	@Override
	protected Type getRosterType()
	{
		return new TypeToken<Roster<FootballAttributes>>()
		{
		}.getType();
	}
}
