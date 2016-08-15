package edu.dlnu.liuwenpeng.Chart;

import org.jfree.chart.JFreeChart;
import edu.dlnu.liuwenpeng.DataInterface.Data;

/**
 * the interface of Chart
 * @author mr.xiao
 *
 */
public interface StockChart {

/**
 * Create a chart
 * @see edu.dlnu.liuwenpeng.Chart.StockChart
 * @see org.jfree.chart.JFreeChart
 * @see edu.dlnu.liuwenpeng.DataInterface.Data
 * @param edu.dlnu.liuwenpeng.DataInterface.Data data
 * @return  a chart
 */
public JFreeChart createChart(Data data);

/**
 * Update a chart
 * @see edu.dlnu.liuwenpeng.Chart.StockChart
 * @see org.jfree.chart.JFreeChart
 * @see edu.dlnu.liuwenpeng.DataInterface.Data
 * @param edu.dlnu.liuwenpeng.DataInterface.Data data
 * @return  a chart
 */
public void UpdateChart(Data data);

/**
 *it unsurly has fuction now 
 */
public void Clear();
}
