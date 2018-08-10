package gui.menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Attributes;
import model.Player;
import model.Roster;
import model.Side;

public abstract class FileHandler<A extends Attributes>
{
	private Gson gson;

	public FileHandler()
	{
		gson = new GsonBuilder()
			.setExclusionStrategies(new PlayerExclusionStrategy())
			.registerTypeAdapter(Side.class, new SideAdapter())
			.create();
	}

	public void saveRosterToFile(File file, Roster<A> roster)
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
		{
			for (Player<A> player : roster)
			{
				writer.write(convertPlayer(gson, player) + System.lineSeparator());
			}
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(
				null,
				"Cannot write to file",
				"File Error",
				JOptionPane.WARNING_MESSAGE);
		}
	}

	public void loadRosterFromFile(File file, Roster<A> roster)
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			String line;
			while ((line = reader.readLine()) != null)
			{
				Player<A> player = convertPlayer(gson, line);

				roster.add(player);
			}
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(
				null,
				"Cannot open file",
				"File Error",
				JOptionPane.WARNING_MESSAGE);
		}
	}

	protected abstract String convertPlayer(Gson gson, Player<A> player);

	protected abstract Player<A> convertPlayer(Gson gson, String json);
}
