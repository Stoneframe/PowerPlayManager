package model.handball;

import model.Attributes;

public class HandballAttributes
	extends Attributes
{
	int goa;
	int fip;
	int sho;
	int blk;
	int pas;
	int tec;
	int spe;
	int agr;

	int qGoa;
	int qFip;
	int qSho;
	int qBlk;
	int qPas;
	int qTec;
	int qSpe;
	int qAgr;

	public int getGoa()
	{
		return goa;
	}

	public void setGoa(int goa)
	{
		this.goa = goa;
		firePropertyChanged("Goa", goa);
	}

	public int getFip()
	{
		return fip;
	}

	public void setFip(int fip)
	{
		this.fip = fip;
		firePropertyChanged("Fip", fip);
	}

	public int getSho()
	{
		return sho;
	}

	public void setSho(int sho)
	{
		this.sho = sho;
		firePropertyChanged("Sho", sho);
	}

	public int getBlk()
	{
		return blk;
	}

	public void setBlk(int blk)
	{
		this.blk = blk;
		firePropertyChanged("Blk", blk);
	}

	public int getPas()
	{
		return pas;
	}

	public void setPas(int pas)
	{
		this.pas = pas;
		firePropertyChanged("Pas", pas);
	}

	public int getTec()
	{
		return tec;
	}

	public void setTec(int tec)
	{
		this.tec = tec;
		firePropertyChanged("Tec", tec);
	}

	public int getSpe()
	{
		return spe;
	}

	public void setSpe(int spe)
	{
		this.spe = spe;
		firePropertyChanged("Spe", spe);
	}

	public int getAgr()
	{
		return agr;
	}

	public void setAgr(int agr)
	{
		this.agr = agr;
		firePropertyChanged("Agr", agr);
	}

	@Override
	public int getTotalRating()
	{
		return goa + fip + sho + blk + pas + tec + spe + agr;
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

	public int getQFip()
	{
		return qFip;
	}

	public void setQFip(int qFip)
	{
		this.qFip = qFip;
		firePropertyChanged("QFip", qFip);
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

	public int getQBlk()
	{
		return qBlk;
	}

	public void setQBlk(int qBlk)
	{
		this.qBlk = qBlk;
		firePropertyChanged("QBlk", qBlk);
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
		return (qGoa + qFip + qSho + qBlk + qPas + qTec + qSpe + qAgr) / 8d;
	}

	@Override
	public void merge(Attributes attributes)
	{
		if (!(attributes instanceof HandballAttributes)) return;

		HandballAttributes other = (HandballAttributes)attributes;

		mergeAttribute(() -> this.getGoa(), (v) -> this.setGoa(v), () -> other.getGoa());
		mergeAttribute(() -> this.getFip(), (v) -> this.setFip(v), () -> other.getFip());
		mergeAttribute(() -> this.getSho(), (v) -> this.setSho(v), () -> other.getSho());
		mergeAttribute(() -> this.getBlk(), (v) -> this.setBlk(v), () -> other.getBlk());
		mergeAttribute(() -> this.getPas(), (v) -> this.setPas(v), () -> other.getPas());
		mergeAttribute(() -> this.getTec(), (v) -> this.setTec(v), () -> other.getTec());
		mergeAttribute(() -> this.getSpe(), (v) -> this.setSpe(v), () -> other.getSpe());
		mergeAttribute(() -> this.getAgr(), (v) -> this.setAgr(v), () -> other.getAgr());
		mergeAttribute(() -> this.getQGoa(), (v) -> this.setQGoa(v), () -> other.getQGoa());
		mergeAttribute(() -> this.getQFip(), (v) -> this.setQFip(v), () -> other.getQFip());
		mergeAttribute(() -> this.getQSho(), (v) -> this.setQSho(v), () -> other.getQSho());
		mergeAttribute(() -> this.getQBlk(), (v) -> this.setQBlk(v), () -> other.getQBlk());
		mergeAttribute(() -> this.getQPas(), (v) -> this.setQPas(v), () -> other.getQPas());
		mergeAttribute(() -> this.getQTec(), (v) -> this.setQTec(v), () -> other.getQTec());
		mergeAttribute(() -> this.getQSpe(), (v) -> this.setQSpe(v), () -> other.getQSpe());
		mergeAttribute(() -> this.getQAgr(), (v) -> this.setQAgr(v), () -> other.getQAgr());
	}

//	@Override
//	public Attributes copy()
//	{
//		HandballAttributes clone = new HandballAttributes();
//		
//		clone.goa = goa;
//		clone.fip = fip;
//		clone.sho = sho;
//		clone.blk = blk;
//		clone.pas = pas;
//		clone.tec = tec;
//		clone.spe = spe;
//		clone.agr = agr;
//		
//		clone.qGoa = qGoa;
//		clone.qFip = qFip;
//		clone.qSho = qSho;
//		clone.qBlk = qBlk;
//		clone.qPas = qPas;
//		clone.qTec = qTec;
//		clone.qSpe = qSpe;
//		clone.qAgr = qAgr;
//		
//		return clone;
//	}

	@Override
	public String toString()
	{
		return String.format(
			"\tGoa: %d(%d)\tFip: %d(%d)\tSho: %d(%d)\tBlk: %d(%d)\tPas: %d(%d)\tTec: %d(%d)\tSpe: %d(%d)\tAgr: %d(%d)\n",
			goa,
			qGoa,
			fip,
			qFip,
			sho,
			qSho,
			blk,
			qBlk,
			pas,
			qPas,
			tec,
			qTec,
			spe,
			qSpe,
			agr,
			qAgr);
	}

	@Override
	public HandballAttributes copy()
	{
		HandballAttributes clone = new HandballAttributes();
		
		clone.goa = goa;
		clone.fip = fip;
		clone.sho = sho;
		clone.blk = blk;
		clone.pas = pas;
		clone.tec = tec;
		clone.spe = spe;
		clone.agr = agr;
		
		clone.qGoa = qGoa;
		clone.qFip = qFip;
		clone.qSho = qSho;
		clone.qBlk = qBlk;
		clone.qPas = qPas;
		clone.qTec = qTec;
		clone.qSpe = qSpe;
		clone.qAgr = qAgr;
		
		return clone;
	}
}
