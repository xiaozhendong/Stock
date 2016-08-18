package edu.dlnu.liuwenpeng.KLineSupport;

import java.util.Date;
import java.util.List;

import org.jfree.chart.axis.SegmentedTimeline;

/**
 * It a class of kline timeline
 * @author mr.xiao
 * @see org.jfree.chart.axis.Timeline
 * @see edu.dlnu.liuwenpeng.KLineSupport.StockDate
 */
class StockTimeLine {

	private SegmentedTimeline timeline = new SegmentedTimeline(
			SegmentedTimeline.DAY_SEGMENT_SIZE, 7, 0);
	private StockDate stockDate = new StockDate();

	public StockTimeLine() {

	}


	/**
	 * Add the timeline of exception
	 * @param date A list of date
	 */
	@SuppressWarnings("static-access")
	public void ExceptionDate(List<Date> date) {
		timeline.setStartTime(timeline.firstMondayAfter1900());
		List<Date> rangeDates = stockDate.CreateRangeDate(date);
		for (Date date2 : rangeDates) {
			if (!date.contains(date2)) {

				timeline.addException(date2);
			}

		}

	}

	/**
	 * The method used to get the max date in the date list
	 * @param date
	 * @return the max Date
	 * @see java.util.Date
	 */
	public Date getMaxDate(List<Date> date) {
		Date maxDate = stockDate.getMaxdate(date);
		return stockDate.add(maxDate, 1);
	}
	
	/**
	 * The method used to get the min date in the date list
	 * @param date
	 * @return the min Date
	 * @see java.util.Date
	 */
	public Date getMinDate(List<Date> date) {
		Date minDate = stockDate.getMindate(date);
		return stockDate.add(minDate, -1);
	}

	
	/**
	 * It must used after ExceptionDate method
	 * @return SegmentedTimeline
	 * @see  org.jfree.chart.axis.SegmentedTimeline
	 * @see edu.dlnu.liuwenpeng.KLineSupport.StockTimeLine
	 */
	public SegmentedTimeline finalTimeline() {
		return this.timeline;
	}

}
