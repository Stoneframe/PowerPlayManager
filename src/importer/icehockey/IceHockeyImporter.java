package importer.icehockey;

import files.FileHandler;
import importer.Importer;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyImporter
	extends Importer<IceHockeyAttributes>
{
	public IceHockeyImporter(FileHandler<IceHockeyAttributes> fileHandler)
	{
		super(fileHandler, new IceHockeyLineParser(), null, null);
	}

	@Override
	protected String getSport()
	{
		return "Ice Hockey";
	}
}
