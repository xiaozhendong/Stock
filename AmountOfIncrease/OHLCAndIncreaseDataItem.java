package edu.dlnu.liuwenpeng.AmountOfIncrease;

import java.io.Serializable;
import java.util.Date;

public class OHLCAndIncreaseDataItem implements Comparable<OHLCAndIncreaseDataItem>, Serializable {

	private static final long serialVersionUID = 7753817154431169901L;
	/**
	 * 24|gains: ÕÇ·ù, 25|amplitude: Õñ·ù, 26|QRR: Á¿±È
	 *
	 */
	private Date date;
	private Double gains;
	private Double QRR;
	private Double amplitude;
	/** The open value. */
	private Number open;
	/** The high value. */
	private Number high;
	/** The low value. */
	private Number low;
	/** The close value. */
	private Number close;
	/** The trading volume (number of shares, contracts or whatever). */
	private Number tradingvolume;

	private Number volumeofbusiness;

	// private Number turnoverrate;

	public OHLCAndIncreaseDataItem(Date date, double open, double high,
			double low, double close, double tradingvolume,
			double volumeofbusiness, double gains, double QRR, double amplitude) {
		if (date == null) {
			throw new IllegalArgumentException("Null 'date' argument.");
		}
		this.amplitude = new Double(amplitude);
		this.gains = new Double(gains);
		this.QRR = new Double(QRR);
		this.date = date;
		this.open = new Double(open);
		this.high = new Double(high);
		this.low = new Double(low);
		this.close = new Double(close);
		this.tradingvolume = new Double(tradingvolume);
		this.volumeofbusiness = new Double(volumeofbusiness);
	}

	/**
	 * 104 * Returns the date that the data item relates to. 105 * 106 * @return
	 * The date (never <code>null</code>). 107
	 */

	/**
	 * 113 * Returns the open value. 114 * 115 * @return The open value. 116
	 */
	public Number getOpen() {
		return this.open;
	}

	/**
	 * 122 * Returns the high value. 123 * 124 * @return The high value. 125
	 */
	public Number getHigh() {
		return this.high;
	}

	/**
	 * 131 * Returns the low value. 132 * 133 * @return The low value. 134
	 */
	public Number getLow() {
		return this.low;
	}

	/**
	 * 140 * Returns the close value. 141 * 142 * @return The close value. 143
	 */
	public Number getClose() {
		return this.close;
	}

	/**
	 * 149 * Returns the volume. 150 * 151 * @return The volume. 152
	 */
	public Number getTradingVolume() {
		return this.tradingvolume;
	}
	
	public Number getVolumeofbusiness() {
		return volumeofbusiness;
	}
	

	public Number getGains() {
		return gains;
	}

	public Number getQRR() {
		return QRR;
	}

	public Number getAmplitude() {
		return amplitude;
	}

	public Date getDate() {
		// TODO Auto-generated method stub
		return this.date;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof OHLCAndIncreaseDataItem)) {
			return false;
		}

		OHLCAndIncreaseDataItem that = (OHLCAndIncreaseDataItem) obj;
		if (!this.amplitude.equals(that.amplitude)) {
			return false;
		}
		if (!this.QRR.equals(that.QRR)) {
			return false;
		}
		if (!this.gains.equals(that.gains)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(OHLCAndIncreaseDataItem arg0) {
		if (arg0 instanceof OHLCAndIncreaseDataItem) {
			OHLCAndIncreaseDataItem item = arg0;
			return this.date.compareTo(item.date);
		} else {
			throw new ClassCastException("IncreaseDataItem.compareTo().");
		}
	}

	

	

}
