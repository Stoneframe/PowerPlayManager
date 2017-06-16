package gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import model.Attributes;

public class AttributePanel extends JPanel
{
	private static final long serialVersionUID = -550607296797083079L;

	private static final int TEXTFIELD_COLUMNS = 4;

	private JLabel goaLabel;
	private JLabel fipLabel;
	private JLabel shoLabel;
	private JLabel blkLabel;
	private JLabel pasLabel;
	private JLabel tecLabel;
	private JLabel speLabel;
	private JLabel agrLabel;

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

	public AttributePanel()
	{
		goaLabel = new JLabel("Goa:");
		fipLabel = new JLabel("Fip:");
		shoLabel = new JLabel("Sho:");
		blkLabel = new JLabel("Blk:");
		pasLabel = new JLabel("Pas:");
		tecLabel = new JLabel("Tec:");
		speLabel = new JLabel("Spe:");
		agrLabel = new JLabel("Agr:");

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

		setBorder(new CompoundBorder(
				BorderFactory.createTitledBorder("Attributes"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		setLayout(new GridLayout(8, 3, 5, 5));

		add(goaLabel);
		add(goaTextField);
		add(goaQTextField);

		add(fipLabel);
		add(fipTextField);
		add(fipQTextField);

		add(shoLabel);
		add(shoTextField);
		add(shoQTextField);

		add(blkLabel);
		add(blkTextField);
		add(blkQTextField);

		add(pasLabel);
		add(pasTextField);
		add(pasQTextField);

		add(tecLabel);
		add(tecTextField);
		add(tecQTextField);

		add(speLabel);
		add(speTextField);
		add(speQTextField);

		add(agrLabel);
		add(agrTextField);
		add(agrQTextField);
	}

	public void showAttributes(Attributes attributes)
	{
		if (attributes == null)
		{
			clear();
		}
		else
		{
			goaTextField.setText(Integer.toString(attributes.getGoa()));
			goaQTextField.setText(Integer.toString(attributes.getQGoa()));
			fipTextField.setText(Integer.toString(attributes.getFip()));
			fipQTextField.setText(Integer.toString(attributes.getQFip()));
			shoTextField.setText(Integer.toString(attributes.getSho()));
			shoQTextField.setText(Integer.toString(attributes.getQSho()));
			blkTextField.setText(Integer.toString(attributes.getBlk()));
			blkQTextField.setText(Integer.toString(attributes.getQBlk()));
			pasTextField.setText(Integer.toString(attributes.getPas()));
			pasQTextField.setText(Integer.toString(attributes.getQPas()));
			tecTextField.setText(Integer.toString(attributes.getTec()));
			tecQTextField.setText(Integer.toString(attributes.getQTec()));
			speTextField.setText(Integer.toString(attributes.getSpe()));
			speQTextField.setText(Integer.toString(attributes.getQSpe()));
			agrTextField.setText(Integer.toString(attributes.getAgr()));
			agrQTextField.setText(Integer.toString(attributes.getQAgr()));
		}
	}

	public void clear()
	{
		goaTextField.setText("");
		goaQTextField.setText("");
		fipTextField.setText("");
		fipQTextField.setText("");
		shoTextField.setText("");
		shoQTextField.setText("");
		blkTextField.setText("");
		blkQTextField.setText("");
		pasTextField.setText("");
		pasQTextField.setText("");
		tecTextField.setText("");
		tecQTextField.setText("");
		speTextField.setText("");
		speQTextField.setText("");
		agrTextField.setText("");
		agrQTextField.setText("");
	}
}
