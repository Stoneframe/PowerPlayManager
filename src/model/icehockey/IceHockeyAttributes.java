package model.icehockey;

import model.Attributes;

public class IceHockeyAttributes
	extends Attributes
{
	private int goa;
	private int def;
	private int off;
	private int sho;
	private int pas;
	private int tec;
	private int agr;

	private int qGoa;
	private int qDef;
	private int qOff;
	private int qSho;
	private int qPas;
	private int qTec;
	private int qAgr;

	public int getGoa()
	{
		return goa;
	}

	public void setGoa(int goa)
	{
		this.goa = goa;
		firePropertyChanged("Goa", goa);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getDef()
	{
		return def;
	}

	public void setDef(int def)
	{
		this.def = def;
		firePropertyChanged("Def", def);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getOff()
	{
		return off;
	}

	public void setOff(int off)
	{
		this.off = off;
		firePropertyChanged("Off", off);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getSho()
	{
		return sho;
	}

	public void setSho(int sho)
	{
		this.sho = sho;
		firePropertyChanged("Sho", sho);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getPas()
	{
		return pas;
	}

	public void setPas(int pas)
	{
		this.pas = pas;
		firePropertyChanged("Pas", pas);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getTec()
	{
		return tec;
	}

	public void setTec(int tec)
	{
		this.tec = tec;
		firePropertyChanged("Tec", tec);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getAgr()
	{
		return agr;
	}

	public void setAgr(int agr)
	{
		this.agr = agr;
		firePropertyChanged("Agr", agr);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	@Override
	public int getTotalRating()
	{
		return goa + def + off + sho + pas + tec + agr;
	}

	public int getQGoa()
	{
		return qGoa;
	}

	public void setQGoa(int qGoa)
	{
		this.qGoa = qGoa;
		firePropertyChanged("QGoa", qGoa);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQDef()
	{
		return qDef;
	}

	public void setQDef(int qDef)
	{
		this.qDef = qDef;
		firePropertyChanged("QDef", qDef);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQOff()
	{
		return qOff;
	}

	public void setQOff(int qOff)
	{
		this.qOff = qOff;
		firePropertyChanged("QOff", qOff);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQSho()
	{
		return qSho;
	}

	public void setQSho(int qSho)
	{
		this.qSho = qSho;
		firePropertyChanged("QSho", qSho);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQPas()
	{
		return qPas;
	}

	public void setQPas(int qPas)
	{
		this.qPas = qPas;
		firePropertyChanged("QPas", qPas);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQTec()
	{
		return qTec;
	}

	public void setQTec(int qTec)
	{
		this.qTec = qTec;
		firePropertyChanged("QTec", qTec);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQAgr()
	{
		return qAgr;
	}

	public void setQAgr(int qAgr)
	{
		this.qAgr = qAgr;
		firePropertyChanged("QAgr", qAgr);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	@Override
	public double getAverageQuality()
	{
		return (qGoa + qDef + qOff + qSho + qPas + qTec + qAgr) / 7d;
	}

	@Override
	public void merge(Attributes attributes)
	{
		if (!(attributes instanceof IceHockeyAttributes)) return;

		IceHockeyAttributes other = (IceHockeyAttributes)attributes;

		mergeAttribute(
			() -> this.getGoa(),
			(v) -> this.setGoa(v),
			() -> other.getGoa());

		mergeAttribute(
			() -> this.getDef(),
			(v) -> this.setDef(v),
			() -> other.getDef());

		mergeAttribute(
			() -> this.getOff(),
			(v) -> this.setOff(v),
			() -> other.getOff());

		mergeAttribute(
			() -> this.getSho(),
			(v) -> this.setSho(v),
			() -> other.getSho());

		mergeAttribute(
			() -> this.getPas(),
			(v) -> this.setPas(v),
			() -> other.getPas());

		mergeAttribute(
			() -> this.getTec(),
			(v) -> this.setTec(v),
			() -> other.getTec());

		mergeAttribute(
			() -> this.getAgr(),
			(v) -> this.setAgr(v),
			() -> other.getAgr());

		mergeAttribute(
			() -> this.getQGoa(),
			(v) -> this.setQGoa(v),
			() -> other.getQGoa());

		mergeAttribute(
			() -> this.getQDef(),
			(v) -> this.setQDef(v),
			() -> other.getQDef());

		mergeAttribute(
			() -> this.getQOff(),
			(v) -> this.setQOff(v),
			() -> other.getQOff());

		mergeAttribute(
			() -> this.getQSho(),
			(v) -> this.setQSho(v),
			() -> other.getQSho());

		mergeAttribute(
			() -> this.getQPas(),
			(v) -> this.setQPas(v),
			() -> other.getQPas());

		mergeAttribute(
			() -> this.getQTec(),
			(v) -> this.setQTec(v),
			() -> other.getQTec());

		mergeAttribute(
			() -> this.getQAgr(),
			(v) -> this.setQAgr(v),
			() -> other.getQAgr());
	}
}
