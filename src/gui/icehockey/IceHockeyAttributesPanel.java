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
		goaTextField.setText(attributes != null ? toString(attributes.getGoa()) : "");
		defTextField.setText(attributes != null ? toString(attributes.getDef()) : "");
		offTextField.setText(attributes != null ? toString(attributes.getOff()) : "");
		shoTextField.setText(attributes != null ? toString(attributes.getSho()) : "");
		pasTextField.setText(attributes != null ? toString(attributes.getPas()) : "");
		tecTextField.setText(attributes != null ? toString(attributes.getTec()) : "");
		agrTextField.setText(attributes != null ? toString(attributes.getAgr()) : "");
		totTextField.setText(attributes != null ? toString(attributes.getTotalRating()) : "");

		goaQTextField.setText(attributes != null ? toString(attributes.getQGoa()) : "");
		defQTextField.setText(attributes != null ? toString(attributes.getQDef()) : "");
		offQTextField.setText(attributes != null ? toString(attributes.getQOff()) : "");
		shoQTextField.setText(attributes != null ? toString(attributes.getQSho()) : "");
		pasQTextField.setText(attributes != null ? toString(attributes.getQPas()) : "");
		tecQTextField.setText(attributes != null ? toString(attributes.getQTec()) : "");
		agrQTextField.setText(attributes != null ? toString(attributes.getQAgr()) : "");
		avgQTextField.setText(attributes != null ? toString(attributes.getAverageQuality()) : "");
	}
}
