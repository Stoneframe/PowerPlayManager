package gui.handball;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import gui.player.AttributesPanel;
import model.handball.HandballAttributes;
import util.PropertyChangedListener;

public class HandballAttributesPanel extends AttributesPanel<HandballAttributes>
		implements
		PropertyChangedListener
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

		setBorder(
			new CompoundBorder(
					BorderFactory.createTitledBorder("Attributes"),
					BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new GridLayout(9, 3, 5, 5));

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
		goaTextField.setText(intToString(attributes::getGoa));
		goaQTextField.setText(intToString(attributes::getQGoa));
		fipTextField.setText(intToString(attributes::getFip));
		fipQTextField.setText(intToString(attributes::getQFip));
		shoTextField.setText(intToString(attributes::getSho));
		shoQTextField.setText(intToString(attributes::getQSho));
		blkTextField.setText(intToString(attributes::getBlk));
		blkQTextField.setText(intToString(attributes::getQBlk));
		pasTextField.setText(intToString(attributes::getPas));
		pasQTextField.setText(intToString(attributes::getQPas));
		tecTextField.setText(intToString(attributes::getTec));
		tecQTextField.setText(intToString(attributes::getQTec));
		speTextField.setText(intToString(attributes::getSpe));
		speQTextField.setText(intToString(attributes::getQSpe));
		agrTextField.setText(intToString(attributes::getAgr));
		agrQTextField.setText(intToString(attributes::getQAgr));
		totTextField.setText(intToString(attributes::getTotalRating));
		avgQTextField.setText(doubleToString(attributes::getAverageQuality));
	}
}
