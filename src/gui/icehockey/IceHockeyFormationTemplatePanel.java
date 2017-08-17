package gui.icehockey;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import builders.formation.icehockey.IceHockeyFormationTemplate;
import evaluators.AttributeEvaluator;
import evaluators.icehockey.IceHockeyAttributeEvaluator;
import gui.formation.FormationTemplatePanel;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyFormationTemplatePanel
		extends FormationTemplatePanel<IceHockeyFormationTemplate>
{
	private static final long serialVersionUID = 6358768840962999641L;

	private JLabel nameLabel;
	private JLabel leftWingLabel;
	private JLabel centerLabel;
	private JLabel rightWingLabel;
	private JLabel leftBackLabel;
	private JLabel rightBackLabel;

	private JTextField nameTextField;
	private JComboBox<AttributeEvaluator<IceHockeyAttributes>> leftWingComboBox;
	private JComboBox<AttributeEvaluator<IceHockeyAttributes>> centerComboBox;
	private JComboBox<AttributeEvaluator<IceHockeyAttributes>> rightWingComboBox;
	private JComboBox<AttributeEvaluator<IceHockeyAttributes>> leftBackComboBox;
	private JComboBox<AttributeEvaluator<IceHockeyAttributes>> rightBackComboBox;

	public IceHockeyFormationTemplatePanel(List<AttributeEvaluator<IceHockeyAttributes>> evaluators)
	{
		nameLabel = new JLabel("Name:");
		leftWingLabel = new JLabel("Left Wing:");
		centerLabel = new JLabel("Center:");
		rightWingLabel = new JLabel("Right Wing:");
		leftBackLabel = new JLabel("Left Back:");
		rightBackLabel = new JLabel("Right Back:");

		nameTextField = new JTextField(15);

		leftWingComboBox = new JComboBox<AttributeEvaluator<IceHockeyAttributes>>();
		centerComboBox = new JComboBox<AttributeEvaluator<IceHockeyAttributes>>();
		rightWingComboBox = new JComboBox<AttributeEvaluator<IceHockeyAttributes>>();
		leftBackComboBox = new JComboBox<AttributeEvaluator<IceHockeyAttributes>>();
		rightBackComboBox = new JComboBox<AttributeEvaluator<IceHockeyAttributes>>();

		evaluators.forEach(e -> leftWingComboBox.addItem(e));
		evaluators.forEach(e -> centerComboBox.addItem(e));
		evaluators.forEach(e -> rightWingComboBox.addItem(e));
		evaluators.forEach(e -> leftBackComboBox.addItem(e));
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

		add(centerLabel);
		add(centerComboBox);

		add(rightWingLabel);
		add(rightWingComboBox);

		add(leftBackLabel);
		add(leftBackComboBox);

		add(rightBackLabel);
		add(rightBackComboBox);
	}

	@Override
	public IceHockeyFormationTemplate getFormationTemplate()
	{
		return new IceHockeyFormationTemplate(
				nameTextField.getText(),
				(IceHockeyAttributeEvaluator) leftWingComboBox.getSelectedItem(),
				(IceHockeyAttributeEvaluator) centerComboBox.getSelectedItem(),
				(IceHockeyAttributeEvaluator) rightWingComboBox.getSelectedItem(),
				(IceHockeyAttributeEvaluator) leftBackComboBox.getSelectedItem(),
				(IceHockeyAttributeEvaluator) rightBackComboBox.getSelectedItem());
	}

	@Override
	public void setFormationTemplate(IceHockeyFormationTemplate template)
	{
		if (template != null)
		{
			nameTextField.setText(template.getName());
			leftWingComboBox.setSelectedItem(template.getLeftWingEvaluator());
			centerComboBox.setSelectedItem(template.getCenterEvaluator());
			rightWingComboBox.setSelectedItem(template.getRightWingEvaluator());
			leftBackComboBox.setSelectedItem(template.getLeftBackEvaluator());
			rightBackComboBox.setSelectedItem(template.getRightBackEvaluator());
		}
		else
		{
			nameTextField.setText("");
			leftWingComboBox.setSelectedIndex(0);
			centerComboBox.setSelectedIndex(0);
			rightWingComboBox.setSelectedIndex(0);
			leftBackComboBox.setSelectedIndex(0);
			rightBackComboBox.setSelectedIndex(0);
		}
	}
}
