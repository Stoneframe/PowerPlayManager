package builders.formation;

public abstract class FormationTemplate
{
	private String name;
	private int nbrOfRequiredPlayers;

	protected FormationTemplate(String name, int nbrOfRequiredPlayers)
	{
		this.name = name;
		this.nbrOfRequiredPlayers = nbrOfRequiredPlayers;
	}

	public String getName()
	{
		return name;
	}

	public int getNumberOfRequiredPlayers()
	{
		return nbrOfRequiredPlayers;
	}

	@Override
	public String toString()
	{
		return getName();
	}
}
