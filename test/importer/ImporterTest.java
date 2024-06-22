package importer;

import java.io.IOException;
import java.util.List;

import org.apache.http.auth.InvalidCredentialsException;
import org.junit.Test;

import files.handball.HandballFileHandler;
import files.icehockey.IceHockeyFileHandler;
import importer.handball.HandballImporter;
import importer.icehockey.IceHockeyImporter;
import model.Player;
import model.handball.HandballAttributes;
import model.icehockey.IceHockeyAttributes;

public class ImporterTest
{
	@Test
	public void testImportHandballTeam() throws IOException, InvalidCredentialsException
	{
		try (Importer<HandballAttributes> importer = new HandballImporter(new HandballFileHandler()))
		{
			List<Player<HandballAttributes>> roster = importer.importPlayers(
				Importer.TEAM,
				Temp.USERNAME,
				Temp.PASSWORD);

			System.out.println(roster);
		}
	}
	
	@Test
	public void testImportIceHockeyTeam() throws IOException, InvalidCredentialsException
	{
		try (Importer<IceHockeyAttributes> importer = new IceHockeyImporter(new IceHockeyFileHandler()))
		{
			List<Player<IceHockeyAttributes>> roster = importer.importPlayers(
				Importer.MARKET_ON_TRANSFER,
				Temp.USERNAME,
				Temp.PASSWORD);

			System.out.println(roster);
		}
	}
}
