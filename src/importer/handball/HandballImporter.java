package importer.handball;

import files.FileHandler;
import importer.Importer;
import model.handball.HandballAttributes;

public class HandballImporter
	extends Importer<HandballAttributes>
{
	public HandballImporter(FileHandler<HandballAttributes> fileHandler)
	{
		super(
			fileHandler,
			new HandballLineUpParser(),
			new HandballTrainingParser(),
			new HandballMarketParser());
	}

	@Override
	protected String getSport()
	{
		return "Handball";
	}
}
