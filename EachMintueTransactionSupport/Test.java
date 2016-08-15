package edu.dlnu.liuwenpeng.EachMintueTransactionSupport;

import javax.swing.JFrame;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;

import tets.HQBase;
import edu.dlnu.liuwenpeng.DataInterface.Data;
import edu.dlnu.liuwenpeng.StockData.TransactionData;



public class Test {

	public Test() {
		HQBase.Connect("211.100.49.196", 7709);
		Data data=TransactionData.Init("000008");
		EachMinuteTransactionChart eachMinuteTransactionChart=new EachMinuteTransactionChart();
	JFreeChart chart=	eachMinuteTransactionChart.createChart(data);
		ChartFrame jFrame;
		jFrame=new ChartFrame("¹ÉÆ±", chart);
		 jFrame.setSize(1000,600 );
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int i = 0; i < 100; i++) {
			long start=System.currentTimeMillis();
			data.update();
			eachMinuteTransactionChart.UpdateChart(data);
			/*if (i>=3&&i<=7) {
				Data data2=TransactionData.Init("000007");
				eachMinuteTransactionChart.UpdateChart(data2);
			}
			if (i>=9&&i<=14) {
				Data data2=TransactionData.Init("000009");
				eachMinuteTransactionChart.UpdateChart(data2);
			}*/
			//System.out.println("h");
			long end=System.currentTimeMillis();
			//System.out.println(end-start);
		
		}
		HQBase.Disconnect();
	}

	public static void main(String args[]) 
	{
		new Test();
		
	}

}
