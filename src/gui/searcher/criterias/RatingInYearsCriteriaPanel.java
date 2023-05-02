package gui.searcher.criterias;

import java.util.function.Consumer;

import javax.swing.JLabel;
import javax.swing.JTextField;

import evaluators.PlayerEvaluator;
import gui.searcher.CriteriaPanel;
import gui.util.IntegerUtil;
import model.Attributes;
import searcher.SearchCriteria;
import searcher.criterias.RatingInYearsSearchCriteria;
import warper.PlayerWarper;

public class RatingInYearsCriteriaPanel<A extends Attributes>
	extends CriteriaPanel<A>
{
	private static final long serialVersionUID = 4870000333100249235L;

	private final JLabel minRatingLabel;
	private final JLabel maxRatingLabel;
	private final JLabel yearsLabel;

	private final JTextField minRatingTextField;
	private final JTextField maxRatingTextField;
	private final JTextField yearsTextField;

	private final PlayerWarper<A> playerWarper;

	public RatingInYearsCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		Consumer<CriteriaPanel<A>> removeCallback)
	{
		super(playerEvaluator, removeCallback);
		this.playerWarper = playerWarper;

		minRatingLabel = new JLabel("Min rating:");
		minRatingTextField = new JTextField(8);

		maxRatingLabel = new JLabel("Max rating:");
		maxRatingTextField = new JTextField(8);

		yearsLabel = new JLabel("In years:");
		yearsTextField = new JTextField(6);

		add(minRatingLabel);
		add(minRatingTextField);
		add(maxRatingLabel);
		add(maxRatingTextField);
		add(yearsLabel);
		add(yearsTextField);
	}

	public RatingInYearsCriteriaPanel(
		PlayerEvaluator<A> playerEvaluator,
		PlayerWarper<A> playerWarper,
		Consumer<CriteriaPanel<A>> removeCallback,
		RatingInYearsSearchCriteria<A> criteria)
	{
		this(playerEvaluator, playerWarper, removeCallback);

		update(criteria);
	}

	@Override
	public SearchCriteria<A> getCriteria()
	{
		return new RatingInYearsSearchCriteria<>(
			getMinRating(),
			getMaxRating(),
			getYears(),
			playerEvaluator,
			playerWarper);
	}

	@Override
	public void clear()
	{
		minRatingTextField.setText("");
		maxRatingTextField.setText("");
		yearsTextField.setText("");
	}

	@Override
	public void update(SearchCriteria<A> searchCritera)
	{
		RatingInYearsSearchCriteria<A> totalratingInYearsCriteria =
			(RatingInYearsSearchCriteria<A>)searchCritera;

		setMinRating(totalratingInYearsCriteria.getMin());
		setMaxRating(totalratingInYearsCriteria.getMax());
		setYears(totalratingInYearsCriteria.getYears());
	}

	private int getMinRating()
	{
		return IntegerUtil.parseInt(minRatingTextField.getText(), Integer.MIN_VALUE);
	}

	private void setMinRating(int Rating)
	{
		if (Rating == Integer.MIN_VALUE)
		{
			minRatingTextField.setText("");
			return;
		}

		minRatingTextField.setText(Integer.toString(Rating));
	}

	private int getMaxRating()
	{
		return IntegerUtil.parseInt(maxRatingTextField.getText(), Integer.MAX_VALUE);
	}

	private void setMaxRating(int Rating)
	{
		if (Rating == Integer.MAX_VALUE)
		{
			maxRatingTextField.setText("");
			return;
		}

		maxRatingTextField.setText(Integer.toString(Rating));
	}

	private int getYears()
	{
		return IntegerUtil.parseInt(yearsTextField.getText(), Integer.MAX_VALUE);
	}

	private void setYears(int Rating)
	{
		if (Rating == Integer.MAX_VALUE)
		{
			yearsTextField.setText("");
			return;
		}

		yearsTextField.setText(Integer.toString(Rating));
	}
}
