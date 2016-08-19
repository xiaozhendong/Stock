package edu.dlnu.liuwenpeng.EachTransactionSupport;

import java.awt.Color;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import edu.dlnu.liuwenpeng.Chart.StockChart;
import edu.dlnu.liuwenpeng.DataInterface.Data;
import edu.dlnu.liuwenpeng.DataInterface.DataItem;
import edu.dlnu.liuwenpeng.render.BarRenderer;
import edu.dlnu.liuwenpeng.render.LineAndShapeRenderer;

/**
 * @author mr.xiao
 *
 */
public class EachTransactionChart implements StockChart {
	private  JFreeChart chart;
	private double maxprice;
	private double minprice;
	private final NumberAxis pricerangeAxis = new NumberAxis();
	private final NumberAxis rangeAxis = new NumberAxis();
	public DefaultCategoryDataset createPriceDataset(Data data) {
		DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		Integer i=0;
		minprice=Double.parseDouble(data.get(0).get("price"));
		maxprice=Double.parseDouble(data.get(0).get("price"));
		for (DataItem dataItem :data) {	
		
			Double price=Double.parseDouble(dataItem.get("price"));
			
		    dataset.addValue(price,"1",i.toString());
		    if (maxprice<price) {
				maxprice=price;
			}
		    if (minprice>price) {
				minprice=price;
			}
		    i=i+1;
		}
		return dataset;
	}
	public DefaultCategoryDataset createNumDataset(Data data) {
		DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		Integer i=0;
		for (DataItem dataItem :data) {	
			Double num=Double.parseDouble(dataItem.get("num"));
			if (dataItem.get("bs").equals("S")) {
				num=num*(-1);
			}
			dataset.addValue(num,"1",i.toString());
		    i=i+1;
		}
		
		return dataset;
		
	}
	
	
	
	
	@Override
	public JFreeChart createChart(Data data) {
			DefaultCategoryDataset dataset=createNumDataset(data);
			DefaultCategoryDataset priceDataset=createPriceDataset(data);
	        
	        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        rangeAxis.setAutoRangeIncludesZero(true);
	        rangeAxis.setAutoRange(true); 
	        rangeAxis.setTickLabelPaint(Color.white);
	        final CategoryAxis categoryAxis=new CategoryAxis();
	        categoryAxis.setTickLabelsVisible(false);
	        categoryAxis.setUpperMargin(0.01);
	        categoryAxis.setLowerMargin(0.01);
	        categoryAxis.setTickLabelPaint(Color.white);
	        
	      
	        pricerangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        pricerangeAxis.setAutoRangeIncludesZero(true);
	        pricerangeAxis.setRange(minprice*0.99,maxprice*1.01);
	        pricerangeAxis.setTickUnit(new NumberTickUnit((maxprice * 1.01-minprice * 0.99)/2.0));
	        pricerangeAxis.setTickLabelPaint(Color.white);
	        
	        final BarRenderer renderer = new BarRenderer();
	       
	        final LineAndShapeRenderer linerenderer = new LineAndShapeRenderer();
	        linerenderer.setSeriesShapesVisible(0, false);
	        
	        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, rangeAxis,     
	                renderer);   
	        CategoryPlot pricepriceplot = new CategoryPlot(priceDataset, categoryAxis, pricerangeAxis,     
	                linerenderer);
	        pricepriceplot.setBackgroundPaint(Color.black);
	        
	       plot.setBackgroundPaint(Color.black);
	        plot.setRangeGridlinePaint(Color.lightGray);
	        
	        CombinedDomainCategoryPlot combineXY = new CombinedDomainCategoryPlot(categoryAxis);
	        combineXY.add(pricepriceplot,1);
	        combineXY.add(plot, 3);
	         chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT,    
	                combineXY, false); 
	         chart.setBackgroundPaint(Color.black);
	      
		return chart;
	}

	@Override
	public void UpdateChart(Data data) {
	
		@SuppressWarnings("rawtypes")
		java.util.List ls=((CombinedDomainCategoryPlot) chart.getPlot()).getSubplots();
		CategoryPlot pricecategoryPlot2=(CategoryPlot) ls.get(0);
		CategoryPlot categoryPlot2=(CategoryPlot) ls.get(1);
		pricecategoryPlot2.setDataset(createPriceDataset(data));
		categoryPlot2.setDataset(createNumDataset(data));
		pricerangeAxis.setRange(minprice*0.99,maxprice*1.01); 
		pricerangeAxis.setTickUnit(new NumberTickUnit((maxprice * 1.01-minprice * 0.99)/2.0));
	}

	@Override
	public void Clear() {
		
	}
	
}
