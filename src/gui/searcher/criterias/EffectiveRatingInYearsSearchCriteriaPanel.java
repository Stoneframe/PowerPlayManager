package gui.searcher.criterias;

import java.util.function.Consumer;

import evaluators.PlayerEvaluator;
import gui.searcher.NumFieldManager;
import gui.searcher.SearchCriteriaPanel;
import model.Attributes;
import searcher.SearchCriteria;
import searcher.criterias.EffectiveRatingInYearsSearchCriteria;
import warper.PlayerWarper;

public class EffectiveRatingInYearsSearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = 4870000333100249235L;

	private final NumFieldManager minRating;
	private final NumFieldManager maxRating;
	private final NumFieldManager years;

	private final PlayerWarper<A> playerWarper;

	public EffectiveRatingInYearsSearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		this.playerWarper = playerWarper;

		minRating = addNumField("Min rating", 6, Integer.MIN_VALUE);
		maxRating = addNumField("Max rating", 6, Integer.MAX_VALUE);
		years = addNumField("Years", 3, 0);
	}

	@Override
	public String getName()
	{
		return EffectiveRatingInYearsSearchCriteria.NAME;
	}

	@Override
	public SearchCriteria<A> getCriteria()
	{
		return new EffectiveRatingInYearsSearchCriteria<>(
			minRating.get(),
			maxRating.get(),
			years.get(),
			playerEvaluator,
			playerWarper);
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		minRating.set(((EffectiveRatingInYearsSearchCriteria<A>)searchCritera).getMin());
		maxRating.set(((EffectiveRatingInYearsSearchCriteria<A>)searchCritera).getMax());
		years.set(((EffectiveRatingInYearsSearchCriteria<A>)searchCritera).getYears());
	}
}
