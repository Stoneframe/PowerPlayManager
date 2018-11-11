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
		goaTextField.setText(attributes != null ? toString(attributes.getGoa()) : "");
		defTextField.setText(attributes != null ? toString(attributes.getDef()) : "");
		midTextField.setText(attributes != null ? toString(attributes.getMid()) : "");
		offTextField.setText(attributes != null ? toString(attributes.getOff()) : "");
		shoTextField.setText(attributes != null ? toString(attributes.getSho()) : "");
		pasTextField.setText(attributes != null ? toString(attributes.getPas()) : "");
		tecTextField.setText(attributes != null ? toString(attributes.getTec()) : "");
		speTextField.setText(attributes != null ? toString(attributes.getSpe()) : "");
		heaTextField.setText(attributes != null ? toString(attributes.getHea()) : "");
		totTextField.setText(attributes != null ? toString(attributes.getTotalRating()) : "");

		goaQTextField.setText(attributes != null ? toString(attributes.getQGoa()) : "");
		defQTextField.setText(attributes != null ? toString(attributes.getQDef()) : "");
		midQTextField.setText(attributes != null ? toString(attributes.getQMid()) : "");
		offQTextField.setText(attributes != null ? toString(attributes.getQOff()) : "");
		shoQTextField.setText(attributes != null ? toString(attributes.getQSho()) : "");
		pasQTextField.setText(attributes != null ? toString(attributes.getQPas()) : "");
		tecQTextField.setText(attributes != null ? toString(attributes.getQTec()) : "");
		speQTextField.setText(attributes != null ? toString(attributes.getQSpe()) : "");
		heaQTextField.setText(attributes != null ? toString(attributes.getQHea()) : "");
		avgQTextField.setText(attributes != null ? toString(attributes.getAverageQuality()) : "");
	}
}
