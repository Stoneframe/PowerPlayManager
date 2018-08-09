package model.football;

import model.Attributes;

public class FootballAttributes
	extends Attributes
{
	private int goa;
	private int def;
	private int mid;
	private int off;
	private int sho;
	private int pas;
	private int tec;
	private int spe;
	private int hea;

	private int qGoa;
	private int qDef;
	private int qMid;
	private int qOff;
	private int qSho;
	private int qPas;
	private int qTec;
	private int qSpe;
	private int qHea;

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

	public int getMid()
	{
		return mid;
	}

	public void setMid(int mid)
	{
		this.mid = mid;
		firePropertyChanged("Mid", mid);
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

	public int getSpe()
	{
		return spe;
	}

	public void setSpe(int spe)
	{
		this.spe = spe;
		firePropertyChanged("Spe", spe);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getHea()
	{
		return hea;
	}

	public void setHea(int hea)
	{
		this.hea = hea;
		firePropertyChanged("Hea", hea);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	@Override
	public int getTotalRating()
	{
		return goa + def + mid + off + sho + pas + tec + spe + hea;
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

	public int getQMid()
	{
		return qMid;
	}

	public void setQMid(int qMid)
	{
		this.qMid = qMid;
		firePropertyChanged("QMid", qMid);
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

	public int getQSpe()
	{
		return qSpe;
	}

	public void setQSpe(int qSpe)
	{
		this.qSpe = qSpe;
		firePropertyChanged("QSpe", qSpe);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQHea()
	{
		return qHea;
	}

	public void setQHea(int qHea)
	{
		this.qHea = qHea;
		firePropertyChanged("QHea", qHea);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	@Override
	public double getAverageQuality()
	{
		return (qGoa + qDef + qMid + qOff + qSho + qPas + qTec + qSpe + qHea) / 9d;
	}

	@Override
	public void merge(Attributes attributes)
	{
		if (!(attributes instanceof FootballAttributes)) return;

		FootballAttributes other = (FootballAttributes)attributes;

		mergeAttribute(this::getGoa, other::getGoa, this::setGoa);
		mergeAttribute(this::getDef, other::getDef, this::setDef);
		mergeAttribute(this::getMid, other::getMid, this::setMid);
		mergeAttribute(this::getOff, other::getOff, this::setOff);
		mergeAttribute(this::getSho, other::getSho, this::setSho);
		mergeAttribute(this::getPas, other::getPas, this::setPas);
		mergeAttribute(this::getTec, other::getTec, this::setTec);
		mergeAttribute(this::getSpe, other::getSpe, this::setSpe);
		mergeAttribute(this::getHea, other::getHea, this::setHea);
		mergeAttribute(this::getQGoa, other::getQGoa, this::setQGoa);
		mergeAttribute(this::getQDef, other::getQDef, this::setQDef);
		mergeAttribute(this::getQMid, other::getQMid, this::setQMid);
		mergeAttribute(this::getQOff, other::getQOff, this::setQOff);
		mergeAttribute(this::getQSho, other::getQSho, this::setQSho);
		mergeAttribute(this::getQPas, other::getQPas, this::setQPas);
		mergeAttribute(this::getQTec, other::getQTec, this::setQTec);
		mergeAttribute(this::getQSpe, other::getQSpe, this::setQSpe);
		mergeAttribute(this::getQHea, other::getQHea, this::setQHea);
	}
}
