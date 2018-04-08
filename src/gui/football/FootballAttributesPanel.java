package gui.football;

import javax.swing.JTextField;

import gui.player.AttributesPanel;
import model.football.FootballAttributes;

public class FootballAttributesPanel
	extends AttributesPanel<FootballAttributes>
{
	private static final long serialVersionUID = -4623262476204460582L;

	private JTextField goaTextField;
	private JTextField goaQTextField;
	private JTextField defTextField;
	private JTextField defQTextField;
	private JTextField midTextField;
	private JTextField midQTextField;
	private JTextField offTextField;
	private JTextField offQTextField;
	private JTextField shoTextField;
	private JTextField shoQTextField;
	private JTextField pasTextField;
	private JTextField pasQTextField;
	private JTextField tecTextField;
	private JTextField tecQTextField;
	private JTextField speTextField;
	private JTextField speQTextField;
	private JTextField heaTextField;
	private JTextField heaQTextField;
	private JTextField totTextField;
	private JTextField avgQTextField;

	public FootballAttributesPanel()
	{
		goaTextField = new JTextField(TEXTFIELD_COLUMNS);
		goaTextField.setEditable(false);

		goaQTextField = new JTextField(TEXTFIELD_COLUMNS);
		goaQTextField.setEditable(false);

		defTextField = new JTextField(TEXTFIELD_COLUMNS);
		defTextField.setEditable(false);

		defQTextField = new JTextField(TEXTFIELD_COLUMNS);
		defQTextField.setEditable(false);

		midTextField = new JTextField(TEXTFIELD_COLUMNS);
		midTextField.setEditable(false);

		midQTextField = new JTextField(TEXTFIELD_COLUMNS);
		midQTextField.setEditable(false);

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

		speTextField = new JTextField(TEXTFIELD_COLUMNS);
		speTextField.setEditable(false);

		speQTextField = new JTextField(TEXTFIELD_COLUMNS);
		speQTextField.setEditable(false);

		heaTextField = new JTextField(TEXTFIELD_COLUMNS);
		heaTextField.setEditable(false);

		heaQTextField = new JTextField(TEXTFIELD_COLUMNS);
		heaQTextField.setEditable(false);

		totTextField = new JTextField(TEXTFIELD_COLUMNS);
		totTextField.setEditable(false);

		avgQTextField = new JTextField(TEXTFIELD_COLUMNS);
		avgQTextField.setEditable(false);

		addRow("Goa:", goaTextField, goaQTextField);
		addRow("Def:", defTextField, defQTextField);
		addRow("Mid:", midTextField, midQTextField);
		addRow("Off:", offTextField, offQTextField);
		addRow("Sho:", shoTextField, shoQTextField);
		addRow("Pas:", pasTextField, pasQTextField);
		addRow("Tec:", tecTextField, tecQTextField);
		addRow("Spe:", speTextField, speQTextField);
		addRow("Hea:", heaTextField, heaQTextField);
		addRow("Tot:", totTextField, avgQTextField);
	}

	@Override
	protected void update()
	{
		goaTextField.setText(intToString(attributes::getGoa));
		goaQTextField.setText(intToString(attributes::getQGoa));
		defTextField.setText(intToString(attributes::getDef));
		defQTextField.setText(intToString(attributes::getQDef));
		midTextField.setText(intToString(attributes::getOff));
		midQTextField.setText(intToString(attributes::getQOff));
		offTextField.setText(intToString(attributes::getOff));
		offQTextField.setText(intToString(attributes::getQOff));
		shoTextField.setText(intToString(attributes::getSho));
		shoQTextField.setText(intToString(attributes::getQSho));
		pasTextField.setText(intToString(attributes::getPas));
		pasQTextField.setText(intToString(attributes::getQPas));
		tecTextField.setText(intToString(attributes::getTec));
		tecQTextField.setText(intToString(attributes::getQTec));
		speTextField.setText(intToString(attributes::getSpe));
		speQTextField.setText(intToString(attributes::getQSpe));
		heaTextField.setText(intToString(attributes::getHea));
		heaQTextField.setText(intToString(attributes::getQHea));
		totTextField.setText(intToString(attributes::getTotalRating));
		avgQTextField.setText(doubleToString(attributes::getAverageQuality));
	}
}
