package searcher;

import java.io.Serializable;
import java.util.List;

import model.Attributes;

public class SearchTemplate<A extends Attributes>
	implements
		Serializable
{
	private static final long serialVersionUID = 7459750922363076979L;

	private final String name;

	private final List<SearchCriteria<A>> criterias;

	public SearchTemplate(String name, List<SearchCriteria<A>> criterias)
	{
		this.name = name;
		this.criterias = criterias;
	}

	public String getName()
	{
		return name;
	}

	public List<SearchCriteria<A>> getCriterias()
	{
		return criterias;
	}

	@Override
	public String toString()
	{
		return getName();
	}
}
