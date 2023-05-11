package gui.searcher.criterias;

import java.util.function.Consumer;

import evaluators.PlayerEvaluator;
import gui.searcher.NumFieldManager;
import gui.searcher.SearchCriteriaPanel;
import model.Attributes;
import searcher.SearchCriteria;
import searcher.criterias.MaximumEffectiveRatingSearchCriteria;
import warper.PlayerWarper;

public class MaximumEffectiveRatingSearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = -8580629570062053443L;

	private final PlayerWarper<A> playerWarper;

	private final NumFieldManager maxRating;

	public MaximumEffectiveRatingSearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		this.playerWarper = playerWarper;

		maxRating = addNumField("Max rating", 8, Integer.MAX_VALUE);
	}

	@Override
	public String getName()
	{
		return MaximumEffectiveRatingSearchCriteria.NAME;
	}

	@Override
	public SearchCriteria<A> getCriteria()
	{
		return new MaximumEffectiveRatingSearchCriteria<>(
			playerEvaluator,
			playerWarper,
			maxRating.get());
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		maxRating.set(((MaximumEffectiveRatingSearchCriteria<A>)searchCritera).getMaximumRating());
	}
}
