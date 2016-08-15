package edu.dlnu.liuwenpeng.AmountOfIncrease;

import java.util.Arrays;
import java.util.Date;

import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.util.PublicCloneable;

public class DefaultOHLCAndIncreaseDataset extends AbstractXYDataset implements
		OHLCDataset, PublicCloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 201608031643L;

	/** The series key. */
	private Comparable key;

	/** Storage for the data items. */
	private OHLCAndIncreaseDataItem[] data;

	/**
	 * 067 * Creates a new dataset. 068 * 069 * @param key the series key. 070 * @param
	 * data the data items. 071
	 */
	public DefaultOHLCAndIncreaseDataset(Comparable key,
			OHLCAndIncreaseDataItem[] data) {
		this.key = key;
		this.data = data;
	}

	/**
	 * 078 * Returns the series key. 079 * 080 * @param series the series index
	 * (ignored). 081 * 082 * @return The series key. 083
	 */
	public Comparable getSeriesKey(int series) {
		return this.key;
	}

	/**
	 * 089 * Returns the x-value for a data item. 090 * 091 * @param series the
	 * series index (ignored). 092 * @param item the item index (zero-based).
	 * 093 * 094 * @return The x-value. 095
	 */
	public Number getX(int series, int item) {
		return new Long(this.data[item].getDate().getTime());
	}

	/**
	 * 101 * Returns the x-value for a data item as a date. 102 * 103 * @param
	 * series the series index (ignored). 104 * @param item the item index
	 * (zero-based). 105 * 106 * @return The x-value as a date. 107
	 */
	public Date getXDate(int series, int item) {
		return this.data[item].getDate();
	}

	/**
	 * 113 * Returns the y-value. 114 * 115 * @param series the series index
	 * (ignored). 116 * @param item the item index (zero-based). 117 * 118 * @return
	 * The y value. 119
	 */
	public Number getY(int series, int item) {
		return getClose(series, item);
	}

	/**
	 * 125 * Returns the high value. 126 * 127 * @param series the series index
	 * (ignored). 128 * @param item the item index (zero-based). 129 * 130 * @return
	 * The high value. 131
	 */
	public Number getHigh(int series, int item) {
		return this.data[item].getHigh();
	}

	/**
	 * 137 * Returns the high-value (as a double primitive) for an item within a
	 * 138 * series. 139 * 140 * @param series the series (zero-based index).
	 * 141 * @param item the item (zero-based index). 142 * 143 * @return The
	 * high-value. 144
	 */
	public double getHighValue(int series, int item) {
		double result = Double.NaN;
		Number high = getHigh(series, item);
		if (high != null) {
			result = high.doubleValue();
		}
		return result;
	}

	/**
	 * 155 * Returns the low value. 156 * 157 * @param series the series index
	 * (ignored). 158 * @param item the item index (zero-based). 159 * 160 * @return
	 * The low value. 161
	 */
	public Number getLow(int series, int item) {
		return this.data[item].getLow();
	}

	/**
	 * 167 * Returns the low-value (as a double primitive) for an item within a
	 * 168 * series. 169 * 170 * @param series the series (zero-based index).
	 * 171 * @param item the item (zero-based index). 172 * 173 * @return The
	 * low-value. 174
	 */
	public double getLowValue(int series, int item) {
		double result = Double.NaN;
		Number low = getLow(series, item);
		if (low != null) {
			result = low.doubleValue();
		}
		return result;
	}

	/**
	 * 185 * Returns the open value. 186 * 187 * @param series the series index
	 * (ignored). 188 * @param item the item index (zero-based). 189 * 190 * @return
	 * The open value. 191
	 */
	public Number getOpen(int series, int item) {
		return this.data[item].getOpen();
	}

	/**
	 * 197 * Returns the open-value (as a double primitive) for an item within a
	 * 198 * series. 199 * 200 * @param series the series (zero-based index).
	 * 201 * @param item the item (zero-based index). 202 * 203 * @return The
	 * open-value. 204
	 */
	public double getOpenValue(int series, int item) {
		double result = Double.NaN;
		Number open = getOpen(series, item);
		if (open != null) {
			result = open.doubleValue();
		}
		return result;
	}

	/**
	 * 215 * Returns the close value. 216 * 217 * @param series the series index
	 * (ignored). 218 * @param item the item index (zero-based). 219 * 220 * @return
	 * The close value. 221
	 */
	public Number getClose(int series, int item) {
		return this.data[item].getClose();
	}

	/**
	 * 227 * Returns the close-value (as a double primitive) for an item within
	 * a 228 * series. 229 * 230 * @param series the series (zero-based index).
	 * 231 * @param item the item (zero-based index). 232 * 233 * @return The
	 * close-value. 234
	 */
	public double getCloseValue(int series, int item) {
		double result = Double.NaN;
		Number close = getClose(series, item);
		if (close != null) {
			result = close.doubleValue();
		}
		return result;
	}

	/**
	 * 245 * Returns the trading volume. 246 * 247 * @param series the series
	 * index (ignored). 248 * @param item the item index (zero-based). 249 * 250
	 * * @return The trading volume. 251
	 */
	public Number getVolume(int series, int item) {
		return this.data[item].getTradingVolume();
	}

	/**
	 * 257 * Returns the volume-value (as a double primitive) for an item within
	 * a 258 * series. 259 * 260 * @param series the series (zero-based index).
	 * 261 * @param item the item (zero-based index). 262 * 263 * @return The
	 * volume-value. 264
	 */
	public double getVolumeValue(int series, int item) {
		double result = Double.NaN;
		Number volume = getVolume(series, item);
		if (volume != null) {
			result = volume.doubleValue();
		}
		return result;
	}

	public Number getVolumeofbusiness(int series, int item) {
		return this.data[item].getVolumeofbusiness();
	}

	public Number getVolumeofbusinessValue(int series, int item) {
		double result = Double.NaN;
		Number volumeofbusiness = getVolumeofbusiness(series, item);
		if (volumeofbusiness != null) {
			result = volumeofbusiness.doubleValue();

		}
		return result;
	}

	public Number getAmplitude(int series, int item) {
		return this.data[item].getAmplitude();

	}

	public double getAmplitudeValue(int series, int item) {
		double result = Double.NaN;
		Number amplitude = getAmplitude(series, item);
		if (amplitude != null) {
			result = amplitude.doubleValue();
		}
		return result;
	}

	public Number getQRR(int series, int item) {
		return this.data[item].getQRR();

	}

	public Number getQRRValue(int series, int item) {
		double result = Double.NaN;
		Number qrr = getQRR(series, item);
		if (qrr != null) {
			result = qrr.doubleValue();
		}
		return result;

	}

	public Number getGains(int series, int item) {
		return this.data[item].getGains();

	}

	public Number getGainsValue(int series, int item) {
		double result = Double.NaN;
		Number gains = getQRR(series, item);
		if (gains != null) {
			result = gains.doubleValue();
		}
		return result;

	}

	/**
	 * 275 * Returns the series count. 276 * 277 * @return 1. 278
	 */
	public int getSeriesCount() {
		return 1;
	}

	/**
	 * 284 * Returns the item count for the specified series. 285 * 286 * @param
	 * series the series index (ignored). 287 * 288 * @return The item count.
	 * 289
	 */
	public int getItemCount(int series) {
		return this.data.length;
	}

	/**
	 * 295 * Sorts the data into ascending order by date. 296
	 */
	public void sortDataByDate() {
		Arrays.sort(this.data);
	}

	/**
	 * 302 * Tests this instance for equality with an arbitrary object. 303 *
	 * 304 * @param obj the object (<code>null</code> permitted). 305 * 306 * @return
	 * A boolean. 307
	 */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DefaultOHLCAndIncreaseDataset)) {
			return false;
		}
		DefaultOHLCAndIncreaseDataset that = (DefaultOHLCAndIncreaseDataset) obj;
		if (!this.key.equals(that.key)) {
			return false;
		}
		if (!Arrays.equals(this.data, that.data)) {
			return false;
		}
		return true;
	}

	/**
	 * 326 * Returns an independent copy of this dataset. 327 * 328 * @return A
	 * clone.
	 */
	public Object clone() throws CloneNotSupportedException {
		DefaultOHLCAndIncreaseDataset clone = (DefaultOHLCAndIncreaseDataset) super
				.clone();
		clone.data = new OHLCAndIncreaseDataItem[this.data.length];
		System.arraycopy(this.data, 0, clone.data, 0, this.data.length);
		return clone;
	}

}
