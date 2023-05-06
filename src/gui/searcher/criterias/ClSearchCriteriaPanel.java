package gui.searcher.criterias;

import java.util.function.Consumer;

import evaluators.PlayerEvaluator;
import gui.searcher.NumFieldManager;
import gui.searcher.SearchCriteriaPanel;
import model.Attributes;
import searcher.SearchCriteria;
import searcher.criterias.ClSearchCriteria;

public class ClSearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = 6426953700282529580L;

	private final NumFieldManager minCl;
	private final NumFieldManager maxCl;

	public ClSearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		minCl = addNumField("Min CL", 4, Integer.MIN_VALUE);
		maxCl = addNumField("Max CL", 4, Integer.MAX_VALUE);
	}

	@Override
	public String getName()
	{
		return ClSearchCriteria.NAME;
	}

	@Override
	public ClSearchCriteria<A> getCriteria()
	{
		return new ClSearchCriteria<A>(playerEvaluator, minCl.get(), maxCl.get());
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		minCl.set(((ClSearchCriteria<A>)searchCritera).getMin());
		maxCl.set(((ClSearchCriteria<A>)searchCritera).getMax());
	}
}
