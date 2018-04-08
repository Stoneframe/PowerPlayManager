package gui.icehockey;

import javax.swing.JTextField;

import gui.player.AttributesPanel;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyAttributesPanel
	extends AttributesPanel<IceHockeyAttributes>
{
	private static final long serialVersionUID = 927418734854336851L;

	private JTextField goaTextField;
	private JTextField goaQTextField;
	private JTextField defTextField;
	private JTextField defQTextField;
	private JTextField offTextField;
	private JTextField offQTextField;
	private JTextField shoTextField;
	private JTextField shoQTextField;
	private JTextField pasTextField;
	private JTextField pasQTextField;
	private JTextField tecTextField;
	private JTextField tecQTextField;
	private JTextField agrTextField;
	private JTextField agrQTextField;
	private JTextField totTextField;
	private JTextField avgQTextField;

	public IceHockeyAttributesPanel()
	{
		goaTextField = new JTextField(TEXTFIELD_COLUMNS);
		goaTextField.setEditable(false);

		goaQTextField = new JTextField(TEXTFIELD_COLUMNS);
		goaQTextField.setEditable(false);

		defTextField = new JTextField(TEXTFIELD_COLUMNS);
		defTextField.setEditable(false);

		defQTextField = new JTextField(TEXTFIELD_COLUMNS);
		defQTextField.setEditable(false);

		offTextField = new JTextField(TEXTFIELD_COLUMNS);
		offTextField.setEditable(false);

		offQTextField = new JTextField(TEXTFIELD_COLUMNS);
		offQTextField.setEditable(false);

		shoTextField = new JTextField(TEXTFIELD_COLUMNS);
		shoTextField.setEditable(false);

		shoQTextField = new JTextField(TEXTFIELD_COLUMNS);
		shoQTextField.setEditable(false);

		pasTextField = new JTextField(TEXTFIELD_COLUMNS);
		pasTextField.setEditable(false);

		pasQTextField = new JTextField(TEXTFIELD_COLUMNS);
		pasQTextField.setEditable(false);

		tecTextField = new JTextField(TEXTFIELD_COLUMNS);
		tecTextField.setEditable(false);

		tecQTextField = new JTextField(TEXTFIELD_COLUMNS);
		tecQTextField.setEditable(false);

		agrTextField = new JTextField(TEXTFIELD_COLUMNS);
		agrTextField.setEditable(false);

		agrQTextField = new JTextField(TEXTFIELD_COLUMNS);
		agrQTextField.setEditable(false);

		totTextField = new JTextField(TEXTFIELD_COLUMNS);
		totTextField.setEditable(false);

		avgQTextField = new JTextField(TEXTFIELD_COLUMNS);
		avgQTextField.setEditable(false);

		addRow("Goa:", goaTextField, goaQTextField);
		addRow("Def:", defTextField, defQTextField);
		addRow("Off:", offTextField, offQTextField);
		addRow("Sho:", shoTextField, shoQTextField);
		addRow("Pas:", pasTextField, pasQTextField);
		addRow("Tec:", tecTextField, tecQTextField);
		addRow("Agr:", agrTextField, agrQTextField);
		addRow("Tot:", totTextField, avgQTextField);
	}

	@Override
	protected void update()
	{
		goaTextField.setText(intToString(attributes::getGoa));
		goaQTextField.setText(intToString(attributes::getQGoa));
		defTextField.setText(intToString(attributes::getDef));
		defQTextField.setText(intToString(attributes::getQDef));
		offTextField.setText(intToString(attributes::getOff));
		offQTextField.setText(intToString(attributes::getQOff));
		shoTextField.setText(intToString(attributes::getSho));
		shoQTextField.setText(intToString(attributes::getQSho));
		pasTextField.setText(intToString(attributes::getPas));
		pasQTextField.setText(intToString(attributes::getQPas));
		tecTextField.setText(intToString(attributes::getTec));
		tecQTextField.setText(intToString(attributes::getQTec));
		agrTextField.setText(intToString(attributes::getAgr));
		agrQTextField.setText(intToString(attributes::getQAgr));
		totTextField.setText(intToString(attributes::getTotalRating));
		avgQTextField.setText(doubleToString(attributes::getAverageQuality));
	}
}
