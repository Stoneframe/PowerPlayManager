package formation;

public abstract class FormationTemplate
{
	private String name;

	protected FormationTemplate(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return getName();
	}
}
