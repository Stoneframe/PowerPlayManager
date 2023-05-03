package gui.searcher.criterias;

import java.util.Arrays;
import java.util.function.Consumer;

import javax.swing.JLabel;

import evaluators.PlayerEvaluator;
import gui.searcher.SearchCriteriaPanel;
import gui.util.PpmComboBox;
import model.Attributes;
import model.Countries;
import searcher.SearchCriteria;
import searcher.criterias.CountrySearchCriteria;

public class CountrySearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = -6513082538803839364L;

	private final JLabel countryLabel;

	private final PpmComboBox<String> countryComboBox;

	public CountrySearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		countryLabel = new JLabel("Country:");
		countryComboBox = new PpmComboBox<>(Arrays.asList(Countries.LIST), -1);

		centerPanel.add(countryLabel);
		centerPanel.add(countryComboBox);
	}

	@Override
	public String getName()
	{
		return CountrySearchCriteria.NAME;
	}

	@Override
	public SearchCriteria<A> getCriteria()
	{
		return new CountrySearchCriteria<>(playerEvaluator, countryComboBox.getSelection());
	}

	@Override
	public void clear()
	{
		countryComboBox.setSelectedItem(-1);
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		CountrySearchCriteria<A> countrySearchCriteria = (CountrySearchCriteria<A>)searchCritera;

		countryComboBox.setSelectedItem(countrySearchCriteria.getCountry());
	}
}
