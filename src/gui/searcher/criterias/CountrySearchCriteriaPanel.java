package gui.searcher.criterias;

import java.util.Arrays;
import java.util.function.Consumer;

import evaluators.PlayerEvaluator;
import gui.searcher.ComboBoxManager;
import gui.searcher.SearchCriteriaPanel;
import model.Attributes;
import model.Countries;
import searcher.SearchCriteria;
import searcher.criterias.CountrySearchCriteria;

public class CountrySearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = -6513082538803839364L;

	private final ComboBoxManager<String> country;

	public CountrySearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		country = addComboBox("Country", Arrays.asList(Countries.LIST), -1);
	}

	@Override
	public String getName()
	{
		return CountrySearchCriteria.NAME;
	}

	@Override
	public SearchCriteria<A> getCriteria()
	{
		return new CountrySearchCriteria<>(playerEvaluator, country.get());
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		country.set(((CountrySearchCriteria<A>)searchCritera).getCountry());
	}
}
