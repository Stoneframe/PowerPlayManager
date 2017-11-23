package gui.player;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import evaluators.AttributeEvaluator;
import model.Attributes;
import util.PropertyChangedEvent;
import util.PropertyChangedListener;

public abstract class TrainingPanel<A extends Attributes>
	extends JPanel
	implements
		PropertyChangedListener
{
	private static final long serialVersionUID = -5619394098750253039L;

	private JComboBox<AttributeEvaluator<A>> positionComboBox;
	private JTextField nextAttributeTextField;

	private AttributeEvaluator<A> selectedEvaluator;
	private A attributes;

	protected TrainingPanel(List<AttributeEvaluator<A>> attributeEvaluators)
	{
		positionComboBox = new JComboBox<>();
		positionComboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				update();
			}
		});

		nextAttributeTextField = new JTextField(10);

		attributeEvaluators.forEach(e -> positionComboBox.addItem(e));

		setLayout(new BorderLayout());

		add(positionComboBox, BorderLayout.WEST);
		add(nextAttributeTextField, BorderLayout.EAST);
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

		update();
	}

	private void update()
	{
		selectedEvaluator = positionComboBox.getItemAt(positionComboBox.getSelectedIndex());

		nextAttributeTextField.setText(
			attributes != null ? selectedEvaluator.getNextTraining(attributes) : "");
	}

	@Override
	public void propertyChanged(Object source, PropertyChangedEvent event)
	{
		update();
	}
}
