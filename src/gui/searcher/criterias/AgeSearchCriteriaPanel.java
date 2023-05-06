package gui.searcher.criterias;

import java.util.function.Consumer;

import evaluators.PlayerEvaluator;
import gui.searcher.NumFieldManager;
import gui.searcher.SearchCriteriaPanel;
import model.Attributes;
import searcher.SearchCriteria;
import searcher.criterias.AgeSearchCriteria;

public class AgeSearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = 6426953700282529580L;

	private NumFieldManager minAge;
	private NumFieldManager maxAge;

	public AgeSearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		minAge = addNumField("Min age", 4, Integer.MIN_VALUE);
		maxAge = addNumField("Max age", 4, Integer.MAX_VALUE);
	}

	@Override
	public String getName()
	{
		return AgeSearchCriteria.NAME;
	}

	@Override
	public AgeSearchCriteria<A> getCriteria()
	{
		return new AgeSearchCriteria<A>(playerEvaluator, minAge.get(), maxAge.get());
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		minAge.set(((AgeSearchCriteria<A>)searchCritera).getMin());
		maxAge.set(((AgeSearchCriteria<A>)searchCritera).getMax());
	}
}
