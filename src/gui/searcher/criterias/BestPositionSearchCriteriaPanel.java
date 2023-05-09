package gui.searcher.criterias;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import evaluators.PlayerEvaluator;
import gui.searcher.ComboBoxManager;
import gui.searcher.SearchCriteriaPanel;
import model.Attributes;
import searcher.SearchCriteria;
import searcher.criterias.BestPositionRatingSearchCriteria;

public class BestPositionSearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = -7080025368411881611L;

	private final ComboBoxManager<String> position;

	public BestPositionSearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		List<String> positions = playerEvaluator.getAttributeEvaluators(false)
			.stream()
			.map(a -> a.getName())
			.collect(Collectors.toList());

		position = addComboBox("Position", positions, -1);
	}

	@Override
	public String getName()
	{
		return BestPositionRatingSearchCriteria.NAME;
	}

	@Override
	public SearchCriteria<A> getCriteria()
	{
		return new BestPositionRatingSearchCriteria<>(playerEvaluator, position.get());
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		position.set(((BestPositionRatingSearchCriteria<A>)searchCritera).getPositionName());
	}
}
