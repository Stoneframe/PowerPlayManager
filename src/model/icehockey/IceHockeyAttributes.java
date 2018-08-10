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

		mergeAttributeRating(this::getGoa, other::getGoa, this::setGoa);
		mergeAttributeRating(this::getDef, other::getDef, this::setDef);
		mergeAttributeRating(this::getOff, other::getOff, this::setOff);
		mergeAttributeRating(this::getSho, other::getSho, this::setSho);
		mergeAttributeRating(this::getPas, other::getPas, this::setPas);
		mergeAttributeRating(this::getTec, other::getTec, this::setTec);
		mergeAttributeRating(this::getAgr, other::getAgr, this::setAgr);

		mergeAttributeQuality(this::getQGoa, other::getQGoa, this::setQGoa);
		mergeAttributeQuality(this::getQDef, other::getQDef, this::setQDef);
		mergeAttributeQuality(this::getQOff, other::getQOff, this::setQOff);
		mergeAttributeQuality(this::getQSho, other::getQSho, this::setQSho);
		mergeAttributeQuality(this::getQPas, other::getQPas, this::setQPas);
		mergeAttributeQuality(this::getQTec, other::getQTec, this::setQTec);
		mergeAttributeQuality(this::getQAgr, other::getQAgr, this::setQAgr);
	}
}
