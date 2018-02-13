package gui.player;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import evaluators.AttributeEvaluator;
import evaluators.PlayerEvaluator;
import gui.util.PpmComboBox;
import model.Attributes;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public abstract class TrainingPlannerPanel<A extends Attributes>
	extends JPanel
	implements
		PropertyChangedListener
{
	private static final long serialVersionUID = -5619394098750253039L;

	private PpmComboBox<AttributeEvaluator<A>> positionComboBox;
	private JTextField nextAttributeTextField;

	private PlayerEvaluator<A> playerEvaluator;
	private A attributes;

	protected TrainingPlannerPanel(PlayerEvaluator<A> playerEvaluator)
	{
		this.playerEvaluator = playerEvaluator;

		positionComboBox = new PpmComboBox<>(playerEvaluator.getAttributeEvaluators(true));
		positionComboBox.addActionListener(e -> onAttributeEvaluatorSelected());

		nextAttributeTextField = new JTextField();
		nextAttributeTextField.setEditable(false);

		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder("Training Planner"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new GridLayout(1, 2, 10, 10));

		add(positionComboBox);
		add(nextAttributeTextField);
	}

	public void bind(A attributes)
	{
		if (this.attributes != null)
		{
			this.attributes.removePropertyChangedListener(this);
		}

		this.attributes = attributes;

		if (this.attributes != null)
		{
			this.attributes.addPropertyChangedListener(this);
		}

		onAttributesChanged();
	}

	private void onAttributesChanged()
	{
		AttributeEvaluator<A> attributeEvaluator = getEvaluatorForBestPosition();

		positionComboBox.setSelectedItem(attributeEvaluator);

		setNextAttributeToTrain(attributeEvaluator);
	}

	private void onAttributeEvaluatorSelected()
	{
		setNextAttributeToTrain(getSelectedEvaluator());
	}

	private AttributeEvaluator<A> getEvaluatorForBestPosition()
	{
		return attributes != null
				? playerEvaluator.getBestEvaluatorByQuality(attributes)
				: null;
	}

	private AttributeEvaluator<A> getSelectedEvaluator()
	{
		return positionComboBox.getSelection();
	}

	private void setNextAttributeToTrain(AttributeEvaluator<A> attributeEvaluator)
	{
		nextAttributeTextField.setText(
			attributes != null ? attributeEvaluator.getNextTraining(attributes) : "");
	}

	@Override
	public void propertyChanged(Object source, PropertyChangedEvent event)
	{
		onAttributesChanged();
	}
}
