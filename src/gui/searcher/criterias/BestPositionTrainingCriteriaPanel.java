package gui.searcher.criterias;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.JLabel;

import evaluators.PlayerEvaluator;
import gui.searcher.CriteriaPanel;
import gui.util.PpmComboBox;
import model.Attributes;
import searcher.SearchCriteria;
import searcher.criterias.BestPositionSearchCriteria;

public class BestPositionTrainingCriteriaPanel<A extends Attributes>
	extends CriteriaPanel<A>
{
	private static final long serialVersionUID = -1917983273654561735L;

	private final JLabel positionLabel;

	private final PpmComboBox<String> positionComboBox;

	public BestPositionTrainingCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<CriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);

		List<String> positions = playerEvaluator.getAttributeEvaluators(true)
			.stream()
			.map(a -> a.getName())
			.collect(Collectors.toList());

		positionLabel = new JLabel("Position:");
		positionComboBox = new PpmComboBox<String>(positions, -1);

		add(positionLabel);
		add(positionComboBox);
	}

	public BestPositionTrainingCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		Consumer<CriteriaPanel<A>> removeCallback,
		BestPositionSearchCriteria<A> searchCriteria)
	{
		this(playerEvaluator, removeCallback);

		update(searchCriteria);
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
