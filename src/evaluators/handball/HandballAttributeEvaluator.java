package evaluators.handball;

import evaluators.AttributeEvaluator;
import model.handball.HandballAttributes;

public class HandballAttributeEvaluator extends AttributeEvaluator<HandballAttributes>
{
	private double goa;
	private double fip;
	private double sho;
	private double blk;
	private double pas;
	private double tec;
	private double spe;
	private double agr;

	public HandballAttributeEvaluator(
			String name,
			double goa,
			double fip,
			double sho,
			double blk,
			double pas,
			double tec,
			double spe,
			double agr)
	{
		super(name);
		this.goa = goa;
		this.fip = fip;
		this.sho = sho;
		this.blk = blk;
		this.pas = pas;
		this.tec = tec;
		this.spe = spe;
		this.agr = agr;
	}

	@Override
	public double getRating(HandballAttributes attributes)
	{
		// @formatter:off
		return (goa * attributes.getGoa() +
				fip * attributes.getFip() +
				sho * attributes.getSho() +
				blk * attributes.getBlk() +
				pas * attributes.getPas() +
				tec * attributes.getTec() +
				spe * attributes.getSpe() +
				agr * attributes.getAgr())
				/ (goa + fip + sho + blk + pas + tec + spe + agr);
		// @formatter:on
	}

	@Override
	public double getQuality(HandballAttributes attributes)
	{
		// @formatter:off
		return (goa + fip + sho + blk + pas + tec + spe + agr) /
				(goa / attributes.getQGoa() +
				 fip / attributes.getQFip() +
				 sho / attributes.getQSho()	+
				 blk / attributes.getQBlk() +
				 pas / attributes.getQPas() +
				 tec / attributes.getQTec() +
				 spe / attributes.getQSpe() +
				 agr / attributes.getQAgr());
		// @formatter:on
	}
}
