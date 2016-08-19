package edu.dlnu.liuwenpeng.EachMintueTransactionSupport;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;

import edu.dlnu.liuwenpeng.Chart.StockChart;
import edu.dlnu.liuwenpeng.DataInterface.Data;
import edu.dlnu.liuwenpeng.DataInterface.DataItem;
import edu.dlnu.liuwenpeng.NewTime.Minute;
import edu.dlnu.liuwenpeng.Time.TimeSeries;
import edu.dlnu.liuwenpeng.Time.TimeSeriesCollection;
import edu.dlnu.liuwenpeng.render.NewXYBarRenderer;
import edu.dlnu.liuwenpeng.render.XYLineAndShapeRenderer;

/**
 * @author xiaozhendong
 *
 */
public class EachMinuteTransactionChart implements StockChart {
	private TimeSeriesCollection NumtimeSeriesCollection = new TimeSeriesCollection();
	private TimeSeriesCollection PricetimeSeriesCollection = new TimeSeriesCollection();
	private TimeSeries Numseries = new TimeSeries("");
	private TimeSeries Priceseries = new TimeSeries("");
	private Date date = new Date();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
	private StockTimeLine timeLine2 = new StockTimeLine();
	private String nowdate = dateFormat.format(date);
	private List<Minute> timeList = new LinkedList<>();
	private Double maxprice;
	private Double minprice;
	private java.util.List<Double> ls = new LinkedList<>();
	private JFreeChart chart;
	private NumberAxis y2Axis = new NumberAxis();
	private DateAxis x1Axis = new DateAxis();

	private TimeSeriesCollection CreateNumTimeCollection(Data data) {
		double num = 0;
		for (DataItem date1 : data) {
			String time = nowdate + " " + date1.get("time");

			String bsString = date1.get("bs");

			if (bsString.equals("S")) {
				num = -1 * (Double.parseDouble(date1.get("num")));
			} else {
				num = Double.parseDouble(date1.get("num"));
			}
			Minute minute = Minute.parseMinute(time);
			timeList.add(minute);
			Numseries.addOrUpdate(minute, num);

		}
		timeLine2.ExceptionDate(timeList);

		NumtimeSeriesCollection.addSeries(Numseries);
		timeList.clear();
		return NumtimeSeriesCollection;

	}

	private TimeSeriesCollection CreatePriceTimeCollection(Data data) {

		minprice = Double.parseDouble(data.get(0).get("price"));
		maxprice = Double.parseDouble(data.get(0).get("price"));
		for (DataItem date1 : data) {
			String time = nowdate + " " + date1.get("time");
			Minute minute = Minute.parseMinute(time);
			Double price = Double.parseDouble(date1.get("price"));
			ls.add(price);

			if (maxprice < price) {
				maxprice = price;
			}
			if (minprice > price) {
				minprice = price;
			}
			Priceseries.addOrUpdate(minute, price);
		}

		PricetimeSeriesCollection.addSeries(Priceseries);
		return PricetimeSeriesCollection;

	}

	@Override
	public JFreeChart createChart(Data data) {

		TimeSeriesCollection numtimeSeriesCollection = CreateNumTimeCollection(data);
		TimeSeriesCollection priceTimeSeriesCollection = CreatePriceTimeCollection(data);
		NewXYBarRenderer xyBarRenderer = new NewXYBarRenderer();
		xyBarRenderer.setMargin(0.5);

		x1Axis.setAutoRange(true);
		x1Axis.setTimeline(timeLine2.finalTimeline());
		x1Axis.setTickLabelPaint(Color.white);
		x1Axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
		x1Axis.setTickUnit(new DateTickUnit(DateTickUnit.MINUTE, 30));
		x1Axis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
		NumberAxis y1Axis = new NumberAxis();
		y1Axis.setTickLabelPaint(Color.white);
		XYPlot plot2 = new XYPlot(numtimeSeriesCollection, x1Axis, y1Axis,
				xyBarRenderer);
		plot2.setBackgroundPaint(Color.black);

		y2Axis.setRange(minprice * 0.99, maxprice * 1.01);
		y2Axis.setTickUnit(new NumberTickUnit((maxprice * 1.01-minprice * 0.99)/3.0));
		y2Axis.setTickLabelPaint(Color.white);
		XYLineAndShapeRenderer xyLineAndShapeRenderer = new XYLineAndShapeRenderer();
		xyLineAndShapeRenderer.setSeriesShapesVisible(0, false);
		XYPlot plot3 = new XYPlot(priceTimeSeriesCollection, x1Axis, y2Axis,
				xyLineAndShapeRenderer);
		CombinedDomainXYPlot combineXY = new CombinedDomainXYPlot(x1Axis);
		combineXY.add(plot3, 1);
		combineXY.add(plot2, 3);
		plot3.setBackgroundPaint(Color.black);

		chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, combineXY,
				false);
		chart.setBackgroundPaint(Color.black);
		return chart;
	}

	@Override
	public void UpdateChart(Data data) {

		List<Minute> newtimeList = new LinkedList<>();

		minprice = Double.parseDouble(data.get(0).get("price"));
		maxprice = Double.parseDouble(data.get(0).get("price"));
		double num = 0;
		DataItem date1;
		for (int i = 0; i < data.size() - 1; i++) {
			date1 = data.get(i);
			String time = nowdate + " " + date1.get("time");
			String bsString = date1.get("bs");
			if (bsString.equals("S")) {
				num = -1 * (Double.parseDouble(date1.get("num")));
			} else {
				num = Double.parseDouble(date1.get("num"));
			}
			Minute minute = Minute.parseMinute(time);
			Double price = Double.parseDouble(date1.get("price"));
			if (maxprice < price) {
				maxprice = price;
			}
			if (minprice > price) {
				minprice = price;
			}
			newtimeList.add(minute);
			Numseries.addOrUpdate(minute, num);
			Priceseries.addOrUpdate(minute, price);
		}
		timeLine2.ExceptionDate(newtimeList);
		y2Axis.setTickUnit(new NumberTickUnit((maxprice * 1.01-minprice * 0.99)/2.0));
		y2Axis.setRange(minprice * 0.99, maxprice * 1.01);
		x1Axis.setTimeline(timeLine2.finalTimeline());
		/*
		 * java.util.List ls = ((CombinedDomainXYPlot)
		 * chart.getPlot()).getSubplots(); XYPlot pricecategoryPlot2 = (XYPlot)
		 * ls.get(0); XYLineAndShapeRenderer
		 * xyLineAndShapeRenderer=pricecategoryPlot2.getRenderer();
		 * //xyLineAndShapeRenderer XYPlot categoryPlot2 = (XYPlot) ls.get(1);
		 * XYDataset xyDataset=CreatePriceTimeCollection(data); XYDataset
		 * xyNunDataset=CreateNumTimeCollection(data);
		 * 
		 * pricecategoryPlot2.setDataset(xyDataset);
		 * categoryPlot2.setDataset(xyNunDataset);
		 * 
		 * y2Axis.setRange(minprice * 0.99, maxprice * 1.01);
		 * x1Axis.setTimeline(timeLine2.finalTimeline());
		 */
	}

	@Override
	public void Clear() {
		/*
		 * try { Thread.sleep(1000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		Numseries.clear();
		Priceseries.clear();

	}

}
