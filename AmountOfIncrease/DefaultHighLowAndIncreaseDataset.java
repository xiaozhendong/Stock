package edu.dlnu.liuwenpeng.AmountOfIncrease;

import java.util.Arrays;
import java.util.Date;

import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.util.PublicCloneable;

public class DefaultHighLowAndIncreaseDataset extends AbstractXYDataset implements OHLCDataset, PublicCloneable{

	private static final long serialVersionUID = 201608032044L;
	private Comparable seriesKey;  
	  /** Storage for the dates. */
	  private Date[] date;
	  /** Storage for the high values. */
	  private Number[] high;   
	  /** Storage for the low values. */
	  private Number[] low;  
	  /** Storage for the open values. */
	  private Number[] open;
	  /** Storage for the close values. */
	  private Number[] close; 
	  /** Storage for the volume values. */
	  private Number[] volume;
	  /** Storage for the volumeofbusiness values. */
	  private Number[] volumeofbusiness;
	  /** Storage for the gains values. */
	  private Number[] gains;
	  /** Storage for the QRR values. */
	  private Number[] QRR;
	  /** Storage for the amplitude values. */
	  private Number[] amplitude;
	  
	  
	public DefaultHighLowAndIncreaseDataset(Comparable seriesKey, Date[] date, double[] high, double[] low, double[] open, double[] close,
			double[] volume,double[] volumeofbusiness,double[] gains,double[] QRR,double[] amplitude) {
		if (seriesKey == null) {
			throw new IllegalArgumentException("Null 'series' argument.");
		}
		if (date == null) {
			throw new IllegalArgumentException("Null 'date' argument.");
		}
		 this.seriesKey = seriesKey;
		 this.date = date;
		 this.high = createNumberArray(high);
		 this.low = createNumberArray(low);
		 this.open = createNumberArray(open);
		 this.close = createNumberArray(close);
		 this.volume = createNumberArray(volume);
		 this.volumeofbusiness=createNumberArray(volumeofbusiness);
		 this.gains=createNumberArray(gains);
		 this.QRR=createNumberArray(QRR);
		 this.amplitude=createNumberArray(amplitude);
	}

	@Override
	public int getItemCount(int arg0) {
		// TODO Auto-generated method stub
		return this.date.length;
	}

	@Override
	public Number getX(int series, int item) {
		// TODO Auto-generated method stub
		return new Long(this.date[item].getTime());
	}
	
	public Date getXDate(int series, int item) {
		 return this.date[item];
		
	}

	@Override
	public Number getY(int series, int item) {
		// TODO Auto-generated method stub
		  return getClose(series, item);
	}

	@Override
	public Number getClose(int series, int item) {
		// TODO Auto-generated method stub
		return this.close[item];
	}

	@Override
	public double getCloseValue(int series, int item) {
		// TODO Auto-generated method stub
		double result = Double.NaN;
		Number close = getClose(series, item);
		if (close != null) {
			result = close.doubleValue();
		}
		return result;
	}

	@Override
	public Number getHigh(int series, int item) {
		// TODO Auto-generated method stub
		return this.high[item];

	}

	@Override
	public double getHighValue(int series, int item) {
		// TODO Auto-generated method stub
		double result = Double.NaN;
		Number high = getHigh(series, item);
		if (high != null) {
			result = high.doubleValue();
		}
		return result;
	}

	@Override
	public Number getLow(int series, int item) {
		// TODO Auto-generated method stub
		 return this.low[item];
	}

	@Override
	public double getLowValue(int series, int item) {
		// TODO Auto-generated method stub
		double result = Double.NaN;
		Number low = getLow(series, item);
		if (low != null) {
			result = low.doubleValue();
		}
		return result;
	}

	@Override
	public Number getOpen(int series, int item) {
		// TODO Auto-generated method stub
		 return this.open[item];
	}

	@Override
	public double getOpenValue(int series, int item) {
		// TODO Auto-generated method stub
		double result = Double.NaN;
		Number open = getOpen(series, item);
		if (open != null) {
			result = open.doubleValue();
		}
		return result;
	}

	@Override
	public Number getVolume(int series, int item) {
		// TODO Auto-generated method stub
		 return this.volume[item];
	}

	@Override
	public double getVolumeValue(int series, int item) {
		// TODO Auto-generated method stub
		double result = Double.NaN;
		Number volume = getVolume(series, item);
		if (volume != null) {
			result = volume.doubleValue();
		}
		return result;
	}

	
	
	
	public Number getVolumeofbusiness(int series, int item) {
		return this.volumeofbusiness[item];
	}

	
	public double getVolumeofbusinessValue(int series, int item) {
		double result = Double.NaN;
		Number volumeofbusiness = getVolumeofbusiness(series, item);
		if (volumeofbusiness != null) {
			result = volumeofbusiness.doubleValue();
		}
		return result;
	}

	
	public Number getGains(int series, int item) {
		return this.gains[item];
	}

	
	public double getGainsValue(int series, int item) {
		double result = Double.NaN;
		Number gains = getGains(series, item);
		if (gains != null) {
			result = gains.doubleValue();
		}
		return result;
	}

	public Number getQRR(int series, int item) {
		return this.QRR[item];
	}

	public double getQRRValue(int series, int item) {
		double result = Double.NaN;
		Number QRR = getGains(series, item);
		if (QRR != null) {
			result =QRR.doubleValue();
		}
		return result;
	}

	public Number getAmplitude(int series, int item) {
		return this.amplitude[item];
	}
	
	public double getAmplitudeValue(int series, int item) {
		double result = Double.NaN;
		Number amplitude = getGains(series, item);
		if (amplitude!= null) {
			result =amplitude.doubleValue();
		}
		return result;
	}

	@Override
	public Comparable getSeriesKey(int series) {
		// TODO Auto-generated method stub
		return this.seriesKey;
	}
	
	
	@Override
	public int getSeriesCount() {
		// TODO Auto-generated method stub
		 return 1;
	}
	/* Constructs an array of Number objects from an array of doubles.
    *
    * @param data  the double values to convert (<code>null</code> not
    *     permitted).
    *
    * @return The data as an array of Number objects.
    */       
	public static Number[] createNumberArray(double[] data) {
		Number[] result = new Number[data.length];
		for (int i = 0; i < data.length; i++) {
			result[i] = new Double(data[i]);
		}
		return result;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof DefaultHighLowAndIncreaseDataset)) {
			return false;
		}
		DefaultHighLowAndIncreaseDataset that = (DefaultHighLowAndIncreaseDataset) obj;
		if (!this.seriesKey.equals(that.seriesKey)) {
			return false;
		}
		if (!Arrays.equals(this.date, that.date)) {
			return false;
		}
		if (!Arrays.equals(this.open, that.open)) {
			return false;
		}
		if (!Arrays.equals(this.high, that.high)) {
			return false;
		}
		if (!Arrays.equals(this.low, that.low)) {
			return false;
		}
		if (!Arrays.equals(this.close, that.close)) {
			return false;
		}
		if (!Arrays.equals(this.volume, that.volume)) {
			return false;
		}
		if (!Arrays.equals(this.volumeofbusiness, that.volumeofbusiness)) {
			return false;
		}
		if (!Arrays.equals(this.amplitude, that.amplitude)) {
			return false;
		}
		if (!Arrays.equals(this.QRR, that.QRR)) {
			return false;
		}
		if (!Arrays.equals(this.gains, that.gains)) {
			return false;
		}
		return true;
	}

	
}
