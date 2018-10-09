package model.football;

import java.util.Arrays;

import model.Attribute;
import model.Attributes;

public class FootballAttributes
	extends Attributes
{
	public FootballAttributes()
	{
		super(
			Arrays.asList(
				new Attribute("Goa"),
				new Attribute("Def"),
				new Attribute("Mid"),
				new Attribute("Off"),
				new Attribute("Sho"),
				new Attribute("Pas"),
				new Attribute("Tec"),
				new Attribute("Spe"),
				new Attribute("Hea")));
	}

	public int getGoa()
	{
		return attributes.get(0).getRating();
	}

	public void setGoa(int goa)
	{
		attributes.get(0).setRating(goa);
		firePropertyChanged("Goa", goa);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getDef()
	{
		return attributes.get(1).getRating();
	}

	public void setDef(int def)
	{
		attributes.get(1).setRating(def);
		firePropertyChanged("Def", def);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getMid()
	{
		return attributes.get(2).getRating();
	}

	public void setMid(int mid)
	{
		attributes.get(2).setRating(mid);
		firePropertyChanged("Mid", mid);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getOff()
	{
		return attributes.get(3).getRating();
	}

	public void setOff(int off)
	{
		attributes.get(3).setRating(off);
		firePropertyChanged("Off", off);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getSho()
	{
		return attributes.get(4).getRating();
	}

	public void setSho(int sho)
	{
		attributes.get(4).setRating(sho);
		firePropertyChanged("Sho", sho);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getPas()
	{
		return attributes.get(5).getRating();
	}

	public void setPas(int pas)
	{
		attributes.get(5).setRating(pas);
		firePropertyChanged("Pas", pas);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getTec()
	{
		return attributes.get(6).getRating();
	}

	public void setTec(int tec)
	{
		attributes.get(6).setRating(tec);
		firePropertyChanged("Tec", tec);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getSpe()
	{
		return attributes.get(7).getRating();
	}

	public void setSpe(int spe)
	{
		attributes.get(7).setRating(spe);
		firePropertyChanged("Spe", spe);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getHea()
	{
		return attributes.get(8).getRating();
	}

	public void setHea(int hea)
	{
		attributes.get(8).setRating(hea);
		firePropertyChanged("Hea", hea);
		firePropertyChanged("TotalRating", getTotalRating());
	}

	public int getQGoa()
	{
		return attributes.get(0).getQuality();
	}

	public void setQGoa(int qGoa)
	{
		attributes.get(0).setQuality(qGoa);
		firePropertyChanged("QGoa", qGoa);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQDef()
	{
		return attributes.get(1).getQuality();
	}

	public void setQDef(int qDef)
	{
		attributes.get(1).setQuality(qDef);
		firePropertyChanged("QDef", qDef);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQMid()
	{
		return attributes.get(2).getQuality();
	}

	public void setQMid(int qMid)
	{
		attributes.get(2).setQuality(qMid);
		firePropertyChanged("QMid", qMid);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQOff()
	{
		return attributes.get(3).getQuality();
	}

	public void setQOff(int qOff)
	{
		attributes.get(3).setQuality(qOff);
		firePropertyChanged("QOff", qOff);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQSho()
	{
		return attributes.get(4).getQuality();
	}

	public void setQSho(int qSho)
	{
		attributes.get(4).setQuality(qSho);
		firePropertyChanged("QSho", qSho);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQPas()
	{
		return attributes.get(5).getQuality();
	}

	public void setQPas(int qPas)
	{
		attributes.get(5).setQuality(qPas);
		firePropertyChanged("QPas", qPas);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQTec()
	{
		return attributes.get(6).getQuality();
	}

	public void setQTec(int qTec)
	{
		attributes.get(6).setQuality(qTec);
		firePropertyChanged("QTec", qTec);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQSpe()
	{
		return attributes.get(7).getQuality();
	}

	public void setQSpe(int qSpe)
	{
		attributes.get(7).setQuality(qSpe);
		firePropertyChanged("QSpe", qSpe);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}

	public int getQHea()
	{
		return attributes.get(8).getQuality();
	}

	public void setQHea(int qHea)
	{
		attributes.get(8).setQuality(qHea);
		firePropertyChanged("QHea", qHea);
		firePropertyChanged("AverageQuality", getAverageQuality());
	}
}
