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
			() -> other.getGoa(),
			(v) -> this.setGoa(v));

		mergeAttribute(
			() -> this.getDef(),
			() -> other.getDef(),
			(v) -> this.setDef(v));

		mergeAttribute(
			() -> this.getOff(),
			() -> other.getOff(),
			(v) -> this.setOff(v));

		mergeAttribute(
			() -> this.getSho(),
			() -> other.getSho(),
			(v) -> this.setSho(v));

		mergeAttribute(
			() -> this.getPas(),
			() -> other.getPas(),
			(v) -> this.setPas(v));

		mergeAttribute(
			() -> this.getTec(),
			() -> other.getTec(),
			(v) -> this.setTec(v));

		mergeAttribute(
			() -> this.getAgr(),
			() -> other.getAgr(),
			(v) -> this.setAgr(v));

		mergeAttribute(
			() -> this.getQGoa(),
			() -> other.getQGoa(),
			(v) -> this.setQGoa(v));

		mergeAttribute(
			() -> this.getQDef(),
			() -> other.getQDef(),
			(v) -> this.setQDef(v));

		mergeAttribute(
			() -> this.getQOff(),
			() -> other.getQOff(),
			(v) -> this.setQOff(v));

		mergeAttribute(
			() -> this.getQSho(),
			() -> other.getQSho(),
			(v) -> this.setQSho(v));

		mergeAttribute(
			() -> this.getQPas(),
			() -> other.getQPas(),
			(v) -> this.setQPas(v));

		mergeAttribute(
			() -> this.getQTec(),
			() -> other.getQTec(),
			(v) -> this.setQTec(v));

		mergeAttribute(
			() -> this.getQAgr(),
			() -> other.getQAgr(),
			(v) -> this.setQAgr(v));
	}

	@Override
	public String toJson()
	{
		// TODO Auto-generated method stub
		return "not implemented";
	}
}
