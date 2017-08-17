package evaluators.icehockey;

import evaluators.AttributeEvaluator;
import model.icehockey.IceHockeyAttributes;

public class IceHockeyAttributeEvaluator extends AttributeEvaluator<IceHockeyAttributes>
{
	private double goa;
	private double def;
	private double off;
	private double sho;
	private double pas;
	private double tec;
	private double agr;

	public IceHockeyAttributeEvaluator(
			String name,
			double goa,
			double def,
			double off,
			double sho,
			double pas,
			double tec,
			double agr)
	{
		super(name);
		this.goa = goa;
		this.def = def;
		this.off = off;
		this.sho = sho;
		this.pas = pas;
		this.tec = tec;
		this.agr = agr;
	}

	@Override
	public double getRating(IceHockeyAttributes attributes)
	{
		// @formatter:off
		return (goa * attributes.getGoa() +
				def * attributes.getDef() +
				off * attributes.getOff() +
				sho * attributes.getSho() +
				pas * attributes.getPas() +
				tec * attributes.getTec() +
				agr * attributes.getAgr())
				/ (goa + def + off + sho + pas + tec + agr);
		// @formatter:on
	}

	@Override
	public double getQuality(IceHockeyAttributes attributes)
	{
		// @formatter:off
		return (goa + def + off + sho + pas + tec + agr) /
				(goa / attributes.getQGoa() +
				 def / attributes.getQDef() +
				 off / attributes.getQOff() +
				 sho / attributes.getQSho()	+
				 pas / attributes.getQPas() +
				 tec / attributes.getQTec() +
				 agr / attributes.getQAgr());
		// @formatter:on
	}
}
