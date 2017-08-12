package gui.handball;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import builders.handball.HandballFormationTemplate;
import evaluators.handball.HandballPlayerEvaluator;

public class HandballFormationTemplatePanel extends JPanel
{
	private static final long serialVersionUID = -1572635059590322744L;

	private JLabel nameLabel;
	private JLabel leftWingLabel;
	private JLabel rightWingLabel;
	private JLabel pivotLabel;
	private JLabel leftBackLabel;
	private JLabel centerBackLabel;
	private JLabel rightBackLabel;

	private JTextField nameTextField;
	private JComboBox<HandballPlayerEvaluator> leftWingComboBox;
	private JComboBox<HandballPlayerEvaluator> rightWingComboBox;
	private JComboBox<HandballPlayerEvaluator> pivotComboBox;
	private JComboBox<HandballPlayerEvaluator> leftBackComboBox;
	private JComboBox<HandballPlayerEvaluator> centerBackComboBox;
	private JComboBox<HandballPlayerEvaluator> rightBackComboBox;

	public HandballFormationTemplatePanel(
			List<HandballPlayerEvaluator> evaluators)
	{
		nameLabel = new JLabel("Name:");
		leftWingLabel = new JLabel("Left Wing:");
		rightWingLabel = new JLabel("Right Wing:");
		pivotLabel = new JLabel("Pivot:");
		leftBackLabel = new JLabel("Left Back:");
		centerBackLabel = new JLabel("Center Back:");
		rightBackLabel = new JLabel("Right Back:");

		nameTextField = new JTextField(15);

		leftWingComboBox = new JComboBox<HandballPlayerEvaluator>();
		rightWingComboBox = new JComboBox<HandballPlayerEvaluator>();
		pivotComboBox = new JComboBox<HandballPlayerEvaluator>();
		leftBackComboBox = new JComboBox<HandballPlayerEvaluator>();
		centerBackComboBox = new JComboBox<HandballPlayerEvaluator>();
		rightBackComboBox = new JComboBox<HandballPlayerEvaluator>();

		evaluators.forEach(e -> leftWingComboBox.addItem(e));
		evaluators.forEach(e -> rightWingComboBox.addItem(e));
		evaluators.forEach(e -> pivotComboBox.addItem(e));
		evaluators.forEach(e -> leftBackComboBox.addItem(e));
		evaluators.forEach(e -> centerBackComboBox.addItem(e));
		evaluators.forEach(e -> rightBackComboBox.addItem(e));

		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder("Formation Template"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new GridLayout(7, 2));

		add(nameLabel);
		add(nameTextField);

		add(leftWingLabel);
		add(leftWingComboBox);

		add(rightWingLabel);
		add(rightWingComboBox);

		add(pivotLabel);
		add(pivotComboBox);

		add(leftBackLabel);
		add(leftBackComboBox);

		add(centerBackLabel);
		add(centerBackComboBox);

		add(rightBackLabel);
		add(rightBackComboBox);
	}

	public HandballFormationTemplate getFormationTemplate()
	{
		return new HandballFormationTemplate(
				nameTextField.getText(),
				(HandballPlayerEvaluator) pivotComboBox.getSelectedItem(),
				(HandballPlayerEvaluator) leftWingComboBox.getSelectedItem(),
				(HandballPlayerEvaluator) rightWingComboBox.getSelectedItem(),
				(HandballPlayerEvaluator) centerBackComboBox.getSelectedItem(),
				(HandballPlayerEvaluator) leftBackComboBox.getSelectedItem(),
				(HandballPlayerEvaluator) rightBackComboBox.getSelectedItem());
	}

	public void setFormationTemplate(HandballFormationTemplate template)
	{
		if (template != null)
		{
			nameTextField.setText(template.getName());
			pivotComboBox.setSelectedItem(template.getPivotEvaluator());
			leftWingComboBox.setSelectedItem(template.getLeftWingEvaluator());
			rightWingComboBox.setSelectedItem(template.getRightWingEvaluator());
			centerBackComboBox
					.setSelectedItem(template.getCenterBackEvaluator());
			leftBackComboBox.setSelectedItem(template.getLeftBackEvaluator());
			rightBackComboBox.setSelectedItem(template.getRightBackEvaluator());
		}
		else
		{
			nameTextField.setText("");
			pivotComboBox.setSelectedIndex(0);
			leftWingComboBox.setSelectedIndex(0);
			rightWingComboBox.setSelectedIndex(0);
			centerBackComboBox.setSelectedIndex(0);
			leftBackComboBox.setSelectedIndex(0);
			rightBackComboBox.setSelectedIndex(0);
		}
	}
}
