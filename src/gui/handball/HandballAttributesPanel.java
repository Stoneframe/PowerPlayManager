package gui.handball;

import javax.swing.JTextField;

import gui.player.AttributesPanel;
import model.handball.HandballAttributes;

public class HandballAttributesPanel
	extends AttributesPanel<HandballAttributes>
{
	private static final long serialVersionUID = -550607296797083079L;

	private JTextField goaTextField;
	private JTextField goaQTextField;
	private JTextField fipTextField;
	private JTextField fipQTextField;
	private JTextField shoTextField;
	private JTextField shoQTextField;
	private JTextField blkTextField;
	private JTextField blkQTextField;
	private JTextField pasTextField;
	private JTextField pasQTextField;
	private JTextField tecTextField;
	private JTextField tecQTextField;
	private JTextField speTextField;
	private JTextField speQTextField;
	private JTextField agrTextField;
	private JTextField agrQTextField;
	private JTextField totTextField;
	private JTextField avgQTextField;

	public HandballAttributesPanel()
	{
		goaTextField = new JTextField(TEXTFIELD_COLUMNS);
		goaTextField.setEditable(false);

		goaQTextField = new JTextField(TEXTFIELD_COLUMNS);
		goaQTextField.setEditable(false);

		fipTextField = new JTextField(TEXTFIELD_COLUMNS);
		fipTextField.setEditable(false);

		fipQTextField = new JTextField(TEXTFIELD_COLUMNS);
		fipQTextField.setEditable(false);

		shoTextField = new JTextField(TEXTFIELD_COLUMNS);
		shoTextField.setEditable(false);

		shoQTextField = new JTextField(TEXTFIELD_COLUMNS);
		shoQTextField.setEditable(false);

		blkTextField = new JTextField(TEXTFIELD_COLUMNS);
		blkTextField.setEditable(false);

		blkQTextField = new JTextField(TEXTFIELD_COLUMNS);
		blkQTextField.setEditable(false);

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

		agrTextField = new JTextField(TEXTFIELD_COLUMNS);
		agrTextField.setEditable(false);

		agrQTextField = new JTextField(TEXTFIELD_COLUMNS);
		agrQTextField.setEditable(false);

		totTextField = new JTextField(TEXTFIELD_COLUMNS);
		totTextField.setEditable(false);

		avgQTextField = new JTextField(TEXTFIELD_COLUMNS);
		avgQTextField.setEditable(false);

		addRow("Goa:", goaTextField, goaQTextField);
		addRow("Fip:", fipTextField, fipQTextField);
		addRow("Sho:", shoTextField, shoQTextField);
		addRow("Blk:", blkTextField, blkQTextField);
		addRow("Pas:", pasTextField, pasQTextField);
		addRow("Tec:", tecTextField, tecQTextField);
		addRow("Spe:", speTextField, speQTextField);
		addRow("Agr:", agrTextField, agrQTextField);
		addRow("Tot:", totTextField, avgQTextField);
	}

	@Override
	protected void update()
	{
		goaTextField.setText(attributes != null ? toString(attributes.getGoa()) : "");
		fipTextField.setText(attributes != null ? toString(attributes.getFip()) : "");
		shoTextField.setText(attributes != null ? toString(attributes.getSho()) : "");
		blkTextField.setText(attributes != null ? toString(attributes.getBlk()) : "");
		pasTextField.setText(attributes != null ? toString(attributes.getPas()) : "");
		tecTextField.setText(attributes != null ? toString(attributes.getTec()) : "");
		speTextField.setText(attributes != null ? toString(attributes.getSpe()) : "");
		agrTextField.setText(attributes != null ? toString(attributes.getAgr()) : "");
		totTextField.setText(attributes != null ? toString(attributes.getTotalRating()) : "");

		goaQTextField.setText(attributes != null ? toString(attributes.getQGoa()) : "");
		fipQTextField.setText(attributes != null ? toString(attributes.getQFip()) : "");
		shoQTextField.setText(attributes != null ? toString(attributes.getQSho()) : "");
		blkQTextField.setText(attributes != null ? toString(attributes.getQBlk()) : "");
		pasQTextField.setText(attributes != null ? toString(attributes.getQPas()) : "");
		tecQTextField.setText(attributes != null ? toString(attributes.getQTec()) : "");
		speQTextField.setText(attributes != null ? toString(attributes.getQSpe()) : "");
		agrQTextField.setText(attributes != null ? toString(attributes.getQAgr()) : "");
		avgQTextField.setText(attributes != null ? toString(attributes.getAverageQuality()) : "");
	}
}
