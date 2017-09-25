package parsers;

public class ParseException
	extends Exception
{
	private static final long serialVersionUID = -723352291226812735L;

	public ParseException(Exception e)
	{
		super(e);
	}
}
