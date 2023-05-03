package searcher.criterias;

import evaluators.PlayerEvaluator;
import model.Attributes;
import model.Player;
import searcher.SearchCriteria;

public class CountrySearchCriteria<A extends Attributes>
	extends SearchCriteria<A>
{
	public static final String NAME = "Country";

	private final String country;

	public CountrySearchCriteria(PlayerEvaluator<A> playerEvaluator, String country)
	{
		super(playerEvaluator);
		this.country = country;
	}

	@Override
	public String getName()
	{
		return NAME;
	}

	@Override
	public boolean check(Player<A> player)
	{
		return player.getCountry().equals(country);
	}

	public String getCountry()
	{
		return country;
	}
}
