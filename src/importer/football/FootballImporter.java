package importer.football;

import files.FileHandler;
import importer.Importer;
import model.football.FootballAttributes;

public class FootballImporter
	extends Importer<FootballAttributes>
{

	protected FootballImporter(FileHandler<FootballAttributes> fileHandler)
	{
		super(
			fileHandler,
			new FootballLineUpParser(),
			new FootballTraningParser(),
			new FootballMarketParser());
	}

	@Override
	protected String getSport()
	{
		return "Football";
	}

}
