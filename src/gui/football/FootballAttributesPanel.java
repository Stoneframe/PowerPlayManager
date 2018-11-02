package gui.football;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import gui.player.AttributesPanelInterface;
import gui.util.FXSimpleFormPanel;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import model.football.FootballAttributes;
import util.PropertyChangedEvent;

public class FootballAttributesPanel
	extends FXSimpleFormPanel
	implements
		AttributesPanelInterface<FootballAttributes>
{
	private static final Border BLACK_BORDER = new Border(
			new BorderStroke(
					Color.LIGHTBLUE,
					BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY,
					BorderWidths.DEFAULT));

	private static final Background WHITE_BACKGROUND =
			new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));

	private static final long serialVersionUID = -4623262476204460582L;

	private Label goaTextField;
	private Label goaQTextField;
	private TextField defTextField;
	private TextField defQTextField;
	private TextField midTextField;
	private TextField midQTextField;
	private TextField offTextField;
	private TextField offQTextField;
	private TextField shoTextField;
	private TextField shoQTextField;
	private TextField pasTextField;
	private TextField pasQTextField;
	private TextField tecTextField;
	private TextField tecQTextField;
	private TextField speTextField;
	private TextField speQTextField;
	private TextField heaTextField;
	private TextField heaQTextField;
	private Label totTextField;
	private Label avgQTextField;

	private FootballAttributes attributes;

	public FootballAttributesPanel()
	{
		goaTextField = new Label();
		goaTextField.setBackground(WHITE_BACKGROUND);
		goaTextField.setBorder(BLACK_BORDER);
		goaTextField.setMinWidth(60);

		goaQTextField = new Label();
		goaQTextField.setBackground(WHITE_BACKGROUND);
		goaQTextField.setBorder(BLACK_BORDER);
		goaQTextField.setMinWidth(60);

		// defTextField = new TextField();
		// defTextField.setEditable(false);
		//
		// defQTextField = new TextField();
		// defQTextField.setEditable(false);
		//
		// midTextField = new TextField();
		// midTextField.setEditable(false);
		//
		// midQTextField = new TextField();
		// midQTextField.setEditable(false);
		//
		// offTextField = new TextField();
		// offTextField.setEditable(false);
		//
		// offQTextField = new TextField();
		// offQTextField.setEditable(false);
		//
		// shoTextField = new TextField();
		// shoTextField.setEditable(false);
		//
		// shoQTextField = new TextField();
		// shoQTextField.setEditable(false);
		//
		// pasTextField = new TextField();
		// pasTextField.setEditable(false);
		//
		// pasQTextField = new TextField();
		// pasQTextField.setEditable(false);
		//
		// tecTextField = new TextField();
		// tecTextField.setEditable(false);
		//
		// tecQTextField = new TextField();
		// tecQTextField.setEditable(false);
		//
		// speTextField = new TextField();
		// speTextField.setEditable(false);
		//
		// speQTextField = new TextField();
		// speQTextField.setEditable(false);
		//
		// heaTextField = new TextField();
		// heaTextField.setEditable(false);
		//
		// heaQTextField = new TextField();
		// heaQTextField.setEditable(false);

		totTextField = new Label();
		totTextField.setBackground(WHITE_BACKGROUND);

		avgQTextField = new Label();
		avgQTextField.setBackground(WHITE_BACKGROUND);
		
//		setBorder(BorderFactory.createTitledBorder("Attributes"));

		Platform.runLater(new Runnable()
		{
			public void run()
			{
				addRow("Goa:", goaTextField, goaQTextField);
				// addRow("Def:", defTextField, defQTextField);
				// addRow("Mid:", midTextField, midQTextField);
				// addRow("Off:", offTextField, offQTextField);
				// addRow("Sho:", shoTextField, shoQTextField);
				// addRow("Pas:", pasTextField, pasQTextField);
				// addRow("Tec:", tecTextField, tecQTextField);
				// addRow("Spe:", speTextField, speQTextField);
				// addRow("Hea:", heaTextField, heaQTextField);
				addRow("Tot:", totTextField, avgQTextField);

				Scene scene = new Scene(gridPane);
				setScene(scene);
			}
		});
	}

	public void bind()
	{

	}

	protected void update()
	{
		// goaTextField.textProperty().bind(attributes.getGoa().ratingProperty().asString());
		// goaQTextField.textProperty().bind(attributes.getGoa().qualityProperty().asString());

		// defTextField
		// .textProperty()
		// .bindBidirectional(attributes.getDef().ratingProperty(), new
		// NumberStringConverter());
		// defQTextField
		// .textProperty()
		// .bindBidirectional(attributes.getDef().qualityProperty(), new
		// NumberStringConverter());

		// totTextField.textProperty().bind(attributes.totalRatingProperty().asString());
		// avgQTextField.textProperty().bind(attributes.averageQualityProperty().asString());

		// midTextField.setText(intToString(attributes::getMid));
		// midQTextField.setText(intToString(attributes::getQMid));
		// offTextField.setText(intToString(attributes::getOff));
		// offQTextField.setText(intToString(attributes::getQOff));
		// shoTextField.setText(intToString(attributes::getSho));
		// shoQTextField.setText(intToString(attributes::getQSho));
		// pasTextField.setText(intToString(attributes::getPas));
		// pasQTextField.setText(intToString(attributes::getQPas));
		// tecTextField.setText(intToString(attributes::getTec));
		// tecQTextField.setText(intToString(attributes::getQTec));
		// speTextField.setText(intToString(attributes::getSpe));
		// speQTextField.setText(intToString(attributes::getQSpe));
		// heaTextField.setText(intToString(attributes::getHea));
		// heaQTextField.setText(intToString(attributes::getQHea));
	}

	@Override
	public void bind(FootballAttributes attributes)
	{
		Platform.runLater(new Runnable()
		{
			public void run()
			{
				goaTextField.textProperty().unbind();
				goaQTextField.textProperty().unbind();

				totTextField.textProperty().unbind();
				avgQTextField.textProperty().unbind();

				if (attributes != null)
				{
					goaTextField.textProperty().bind(
						attributes.getGoa().ratingProperty().asString());
					goaQTextField.textProperty().bind(
						attributes.getGoa().qualityProperty().asString());

					totTextField.textProperty().bind(
						attributes.totalRatingProperty().asString());
					avgQTextField.textProperty().bind(
						attributes.averageQualityProperty().asString());
				}
			}
		});

	}

	@Override
	public void propertyChanged(Object source, PropertyChangedEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public JComponent getPanel()
	{
		return this;
	}
}
