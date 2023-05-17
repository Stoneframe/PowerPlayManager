package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gui.menu.PlayerExclusionStrategy;
import gui.menu.SideAdapter;
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

	public LocalDateTime getFileModifiedDate(Path path)
	{
		try
		{
			FileTime modifiedTime = Files.getLastModifiedTime(path);

			return LocalDateTime.ofInstant(modifiedTime.toInstant(), ZoneId.systemDefault());
		}
		catch (IOException ex)
		{
			return LocalDateTime.MIN;
		}
	}

	public void savePlayersToFile(File file, Roster<A> roster)
	{
		file.getParentFile().mkdirs();

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

	public void loadPlayersFromFile(File file, Roster<A> roster)
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			String line;
			while ((line = reader.readLine()) != null)
			{
				roster.add(convertPlayer(gson, line));
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
