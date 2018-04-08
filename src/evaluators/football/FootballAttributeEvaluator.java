package evaluators.football;

import java.util.Arrays;
import java.util.List;

import evaluators.MicroAttributeEvaluator;
import javafx.util.Pair;
import model.football.FootballAttributes;

public class FootballAttributeEvaluator
	extends MicroAttributeEvaluator<FootballAttributes>
{
	protected static final int PRIMARY = 100;
	protected static final int HIGH = 75;
	protected static final int MEDIUM = 50;
	protected static final int LOW = 25;

	private double goa;
	private double def;
	private double mid;
	private double off;
	private double sho;
	private double pas;
	private double tec;
	private double spe;
	private double hea;

	public FootballAttributeEvaluator(
			String name,
			double goa,
			double def,
			double mid,
			double off,
			double sho,
			double pas,
			double tec,
			double spe,
			double hea)
	{
		super(name);
		this.goa = goa;
		this.def = def;
		this.mid = mid;
		this.off = off;
		this.sho = sho;
		this.pas = pas;
		this.tec = tec;
		this.spe = spe;
		this.hea = hea;
	}

	@Override
	public double getQuality(FootballAttributes attributes)
	{
		// @formatter:off
		return (goa + def + mid + off + sho + pas + tec + spe + hea) /
				(goa / attributes.getQGoa() +
				 def / attributes.getQDef() +
				 mid / attributes.getQMid() +
				 off / attributes.getQOff() +
				 sho / attributes.getQSho()	+
				 pas / attributes.getQPas() +
				 tec / attributes.getQTec() +
				 spe / attributes.getQSpe() +
				 hea / attributes.getQHea());
		// @formatter:on
	}

	@Override
	protected List<Pair<String, Double>> createPairs(FootballAttributes attributes)
	{
		return Arrays.asList(
			createPair("Goalkeeping", attributes.getGoa(), goa),
			createPair("Defence", attributes.getDef(), def),
			createPair("Offence", attributes.getOff(), off),
			createPair("Shooting", attributes.getSho(), sho),
			createPair("Passing", attributes.getPas(), pas),
			createPair("Technique", attributes.getTec(), tec),
			createPair("Speed", attributes.getSpe(), spe),
			createPair("Heading", attributes.getHea(), hea));
	}

	@Override
	protected double attributeSum()
	{
		return goa + def + mid + off + sho + pas + tec + spe + hea;
	}
}
