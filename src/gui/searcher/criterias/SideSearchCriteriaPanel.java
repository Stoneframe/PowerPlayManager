package gui.searcher.criterias;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import evaluators.PlayerEvaluator;
import gui.searcher.ComboBoxManager;
import gui.searcher.SearchCriteriaPanel;
import model.Attributes;
import model.Side;
import searcher.SearchCriteria;
import searcher.criterias.SideSearchCriteria;

public class SideSearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = 1617949699372903670L;

	private final ComboBoxManager<String> side;

	public SideSearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		List<String> sides = Arrays.asList(
			Side.UNIVERSAL.toString(),
			Side.LEFT.toString(),
			Side.RIGHT.toString());

		side = addComboBox("Side", sides, 0);
	}

	@Override
	public String getName()
	{
		return SideSearchCriteria.NAME;
	}

	@Override
	public SearchCriteria<A> getCriteria()
	{
		return new SideSearchCriteria<>(playerEvaluator, side.get());
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		side.set(((SideSearchCriteria<A>)searchCritera).getSide());
	}
}
