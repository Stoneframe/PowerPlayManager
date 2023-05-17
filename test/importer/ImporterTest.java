package importer;

import java.io.IOException;
import java.util.List;

import org.apache.http.auth.InvalidCredentialsException;
import org.junit.Test;

import files.handball.HandballFileHandler;
import importer.handball.HandballImporter;
import model.Player;
import model.handball.HandballAttributes;

public class ImporterTest
{
	@Test
	public void testImportTeam() throws IOException, InvalidCredentialsException
	{
		try (Importer<HandballAttributes> importer = getHandballImporter())
		{
			List<Player<HandballAttributes>> roster = importer.importPlayers(
				Importer.MARKET,
				Temp.USERNAME,
				Temp.PASSWORD);

			System.out.println(roster);
		}
	}

	private Importer<HandballAttributes> getHandballImporter()
	{
		return new HandballImporter(new HandballFileHandler());
	}
}
