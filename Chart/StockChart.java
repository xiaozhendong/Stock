package edu.dlnu.liuwenpeng.Chart;

import org.jfree.chart.JFreeChart;
import edu.dlnu.liuwenpeng.DataInterface.Data;

public interface StockChart {

public JFreeChart createChart(Data data);

public void UpdateChart(Data data);

public void Clear();
}
