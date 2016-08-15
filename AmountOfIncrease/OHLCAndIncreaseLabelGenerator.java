package edu.dlnu.liuwenpeng.AmountOfIncrease;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

import org.jfree.chart.HashUtilities;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.PublicCloneable;

public class OHLCAndIncreaseLabelGenerator implements XYItemLabelGenerator,
		XYToolTipGenerator, Cloneable, PublicCloneable, Serializable {
	private static final long serialVersionUID = 5617111754832211830L;
	private DateFormat dateFormatter;
	private NumberFormat numberFormatter;

	public OHLCAndIncreaseLabelGenerator() {
		this(DateFormat.getInstance(), NumberFormat.getInstance());
	}

	public OHLCAndIncreaseLabelGenerator(DateFormat dateFormatter,
			NumberFormat numberFormatter) {
		if (dateFormatter == null) {
			throw new IllegalArgumentException("Null 'dateFormatter' argument.");
		}
		if (numberFormatter == null) {
			throw new IllegalArgumentException(
					"Null 'numberFormatter' argument.");
		}
		this.dateFormatter = dateFormatter;
		this.numberFormatter = numberFormatter;
	}

	@Override
	public String generateLabel(XYDataset arg0, int arg1, int arg2) {

		return null;
	}

	@Override
	public String generateToolTip(XYDataset dataset, int series, int item) {
		DefaultOHLCAndIncreaseDataset  d = (DefaultOHLCAndIncreaseDataset) dataset;
		
		String result = null;
		Number open=d.getOpen(series, item);
		Number high=d.getHigh(series, item);
		Number low=d.getLow(series, item);
		Number close=d.getClose(series, item);
		Number volume=d.getVolume(series, item);
		Number volumeofbusiness=d.getVolumeofbusiness(series, item);
		Number amplitude = d.getAmplitude(series, item);
		Number gains = d.getGains(series, item);
		Number qrr = d.getQRR(series, item);
		
		
		Number x = d.getX(series, item);
		result = d.getSeriesKey(series).toString();
		 if (x != null) {
			                    Date date = new Date(x.longValue());
			                     result = result + "--> Date=" + this.dateFormatter.format(date);
			                     if (open != null) {
			                         result = result + " open=" 
			                                  + this.numberFormatter.format(open.doubleValue());
			                     }
			                     if (high!= null) {
			                         result = result + " high=" 
			                                  + this.numberFormatter.format(high.doubleValue());
			                     }
			                     if (low != null) {
			                         result = result + " low=" 
			                                  + this.numberFormatter.format(low.doubleValue())+"\n";
			                     }
			                     if (close != null) {
			                         result = result + " close=" 
			                                  + this.numberFormatter.format(close.doubleValue());
			                     }
			                     if (volume != null) {
			                         result = result + " volume=" 
			                                  + this.numberFormatter.format(volume.doubleValue());
			                     }
			                     if (volumeofbusiness != null) {
			                         result = result + " volumeofbusiness=" 
			                                  + this.numberFormatter.format(volumeofbusiness.doubleValue());
			                     }
			                     if (amplitude != null) {
			                         result = result + " amplitude=" 
			                                  + this.numberFormatter.format(amplitude.doubleValue())+"%";
			                     }
			                     if (gains!= null) {
			                         result = result + " Gains=" 
			                                  + this.numberFormatter.format(gains.doubleValue())+"%";
			                    }
			                     if (qrr!= null) {
			                         result = result + " QRR=" 
			                                  + this.numberFormatter.format(qrr.doubleValue())+"%";
			                     }
			             }
	     
		return result;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof OHLCAndIncreaseLabelGenerator)) {
			return false;
		}
		OHLCAndIncreaseLabelGenerator generator = (OHLCAndIncreaseLabelGenerator) obj;
		if (!this.dateFormatter.equals(generator.dateFormatter)) {
			return false;
		}
		if (!this.numberFormatter.equals(generator.numberFormatter)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a hash code for this instance.
	 * 
	 * @return A hash code.
	 */
	public int hashCode() {
		int result = 127;
		result = HashUtilities.hashCode(result, this.dateFormatter);
		result = HashUtilities.hashCode(result, this.numberFormatter);
		return result;
	}

	public Object clone() throws CloneNotSupportedException {

		OHLCAndIncreaseLabelGenerator clone = (OHLCAndIncreaseLabelGenerator) super
				.clone();

		if (this.dateFormatter != null) {
			clone.dateFormatter = (DateFormat) this.dateFormatter.clone();
		}
		if (this.numberFormatter != null) {
			clone.numberFormatter = (NumberFormat) this.numberFormatter.clone();
		}

		return clone;

	}
}
