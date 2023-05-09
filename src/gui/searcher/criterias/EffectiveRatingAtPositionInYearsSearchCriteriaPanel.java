package gui.searcher.criterias;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import evaluators.PlayerEvaluator;
import gui.searcher.ComboBoxManager;
import gui.searcher.NumFieldManager;
import gui.searcher.SearchCriteriaPanel;
import model.Attributes;
import searcher.SearchCriteria;
import searcher.criterias.EffectiveRatingAtPositionInYearsSearchCriteria;
import warper.PlayerWarper;

public class EffectiveRatingAtPositionInYearsSearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = 4870000333100249235L;

	private final ComboBoxManager<String> position;
	private final NumFieldManager minRating;
	private final NumFieldManager maxRating;
	private final NumFieldManager years;

	private final PlayerWarper<A> playerWarper;

	public EffectiveRatingAtPositionInYearsSearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		this.playerWarper = playerWarper;

		List<String> positions = playerEvaluator.getAttributeEvaluators(false)
			.stream()
			.map(a -> a.getName())
			.collect(Collectors.toList());

		position = addComboBox("Position", positions, -1);
		minRating = addNumField("Min rating", 6, Integer.MIN_VALUE);
		maxRating = addNumField("Max rating", 6, Integer.MAX_VALUE);
		years = addNumField("Years", 3, 0);
	}

	@Override
	public String getName()
	{
		return EffectiveRatingAtPositionInYearsSearchCriteria.NAME;
	}

	@Override
	public SearchCriteria<A> getCriteria()
	{
		return new EffectiveRatingAtPositionInYearsSearchCriteria<>(
			position.get(),
			minRating.get(),
			maxRating.get(),
			years.get(),
			playerEvaluator,
			playerWarper);
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		position.set(((EffectiveRatingAtPositionInYearsSearchCriteria<A>)searchCritera).getPositionName());
		minRating.set(((EffectiveRatingAtPositionInYearsSearchCriteria<A>)searchCritera).getMin());
		maxRating.set(((EffectiveRatingAtPositionInYearsSearchCriteria<A>)searchCritera).getMax());
		years.set(((EffectiveRatingAtPositionInYearsSearchCriteria<A>)searchCritera).getYears());
	}
}
