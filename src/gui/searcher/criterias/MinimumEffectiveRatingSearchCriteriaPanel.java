package gui.searcher.criterias;

import java.util.function.Consumer;

import evaluators.PlayerEvaluator;
import gui.searcher.NumFieldManager;
import gui.searcher.SearchCriteriaPanel;
import model.Attributes;
import searcher.SearchCriteria;
import searcher.criterias.MinimumEffectiveRatingSearchCriteria;
import warper.PlayerWarper;

public class MinimumEffectiveRatingSearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = -8580629570062053443L;

	private final PlayerWarper<A> playerWarper;

	private final NumFieldManager minRating;

	public MinimumEffectiveRatingSearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		this.playerWarper = playerWarper;

		minRating = addNumField("Min rating", 8, Integer.MIN_VALUE);
	}

	@Override
	public String getName()
	{
		return MinimumEffectiveRatingSearchCriteria.NAME;
	}

	@Override
	public SearchCriteria<A> getCriteria()
	{
		return new MinimumEffectiveRatingSearchCriteria<>(
			playerEvaluator,
			playerWarper,
			minRating.get());
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		minRating.set(((MinimumEffectiveRatingSearchCriteria<A>)searchCritera).getMinumumRating());
	}
}
