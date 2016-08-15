package edu.dlnu.liuwenpeng.EachMintueTransactionSupport;

import java.util.Date;
import java.util.List;

import org.jfree.chart.axis.SegmentedTimeline;

import edu.dlnu.liuwenpeng.NewTime.Minute;

class StockTimeLine {

	private SegmentedTimeline timeline = new SegmentedTimeline(
			SegmentedTimeline.MINUTE_SEGMENT_SIZE,1440, 0);
	private StockDate stockDate = new StockDate();

	public StockTimeLine() {

	}

	/*
	 * 输入data数据内的日期 比对由其产生的连续日趋得到需要被删除的日期
	 */
	@SuppressWarnings("static-access")
	public void ExceptionDate(List<Minute> date) {
		timeline.setStartTime(timeline.firstMondayAfter1900());
		List<Minute> rangeDates = stockDate.CreateRangeDate(date);
		for (Minute date2 : rangeDates) {
			if (!date.contains(date2)) {
				timeline.addException(date2.getStart());
				
			}

		}

	}

	public Minute getMaxDate(List<Minute> date) {
		Minute maxDate = stockDate.getMaxdate(date);
		return stockDate.add(maxDate, 1);
	}

	public Minute getMinDate(List<Minute> date) {
		Minute minDate = stockDate.getMindate(date);
		return stockDate.add(minDate, -1);
	}

	/*
	 * 得到最终时间线
	 */
	public SegmentedTimeline finalTimeline() {
		return this.timeline;
	}

}
