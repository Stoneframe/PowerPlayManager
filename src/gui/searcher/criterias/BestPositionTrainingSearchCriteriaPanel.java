package gui.searcher.criterias;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.JLabel;

import evaluators.PlayerEvaluator;
import gui.searcher.SearchCriteriaPanel;
import gui.util.PpmComboBox;
import model.Attributes;
import searcher.SearchCriteria;
import searcher.criterias.BestPositionSearchCriteria;

public class BestPositionTrainingSearchCriteriaPanel<A extends Attributes>
	extends SearchCriteriaPanel<A>
{
	private static final long serialVersionUID = -1917983273654561735L;

	private final JLabel positionLabel;

	private final PpmComboBox<String> positionComboBox;

	public BestPositionTrainingSearchCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<SearchCriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		List<String> positions = playerEvaluator.getAttributeEvaluators(true)
			.stream()
			.map(a -> a.getName())
			.collect(Collectors.toList());

		positionLabel = new JLabel("Position:");
		positionComboBox = new PpmComboBox<String>(positions, -1);

		centerPanel.add(positionLabel);
		centerPanel.add(positionComboBox);
	}

	@Override
	public String getName()
	{
		return BestPositionSearchCriteria.NAME;
	}

	@Override
	public SearchCriteria<A> getCriteria()
	{
		return new BestPositionSearchCriteria<>(playerEvaluator, positionComboBox.getSelection());
	}

	@Override
	public void clear()
	{
		positionComboBox.setSelectedIndex(-1);
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		BestPositionSearchCriteria<A> bestPositionSearchCriteria =
			(BestPositionSearchCriteria<A>)searchCritera;

		positionComboBox.setSelectedItem(bestPositionSearchCriteria.getPositionName());
	}
}
