package gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import model.FormationTemplate;
import model.PlayerEvaluator;

public class FormationTemplatePanel extends JPanel
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
	private JComboBox<PlayerEvaluator> leftWingComboBox;
	private JComboBox<PlayerEvaluator> rightWingComboBox;
	private JComboBox<PlayerEvaluator> pivotComboBox;
	private JComboBox<PlayerEvaluator> leftBackComboBox;
	private JComboBox<PlayerEvaluator> centerBackComboBox;
	private JComboBox<PlayerEvaluator> rightBackComboBox;

	public FormationTemplatePanel(List<PlayerEvaluator> evaluators)
	{
		nameLabel = new JLabel("Name:");
		leftWingLabel = new JLabel("Left Wing:");
		rightWingLabel = new JLabel("Right Wing:");
		pivotLabel = new JLabel("Pivot:");
		leftBackLabel = new JLabel("Left Back:");
		centerBackLabel = new JLabel("Center Back:");
		rightBackLabel = new JLabel("Right Back:");

		nameTextField = new JTextField(15);

		PlayerEvaluator[] evaluatorArray = evaluators
				.toArray(new PlayerEvaluator[0]);

		leftWingComboBox = new JComboBox<PlayerEvaluator>(evaluatorArray);
		rightWingComboBox = new JComboBox<PlayerEvaluator>(evaluatorArray);
		pivotComboBox = new JComboBox<PlayerEvaluator>(evaluatorArray);
		leftBackComboBox = new JComboBox<PlayerEvaluator>(evaluatorArray);
		centerBackComboBox = new JComboBox<PlayerEvaluator>(evaluatorArray);
		rightBackComboBox = new JComboBox<PlayerEvaluator>(evaluatorArray);

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

	public FormationTemplate getFormationTemplate()
	{
		return new FormationTemplate(
				nameTextField.getText(),
				(PlayerEvaluator) pivotComboBox.getSelectedItem(),
				(PlayerEvaluator) leftWingComboBox.getSelectedItem(),
				(PlayerEvaluator) rightWingComboBox.getSelectedItem(),
				(PlayerEvaluator) centerBackComboBox.getSelectedItem(),
				(PlayerEvaluator) leftBackComboBox.getSelectedItem(),
				(PlayerEvaluator) rightBackComboBox.getSelectedItem());
	}

	public void setFormationTemplate(FormationTemplate template)
	{
		if (template != null)
		{
			nameTextField.setText(template.getName());
			pivotComboBox.setSelectedItem(template.getPivotEvaluator());
			leftWingComboBox.setSelectedItem(template.getLeftWingEvaluator());
			rightWingComboBox.setSelectedItem(template.getRightWingEvaluator());
			centerBackComboBox.setSelectedItem(
				template.getCenterBackEvaluator());
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
