package edu.dlnu.liuwenpeng.EachMintueTransactionSupport;

import javax.swing.JFrame;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;

import edu.dlnu.liuwenpeng.DataInterface.Data;
import edu.dlnu.liuwenpeng.StockData.HQBase;
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
		for (int i = 0; i < 1000; i++) {
			long start=System.currentTimeMillis();
			data.update();
			if (i<10) {
				System.out.println(i);
				
				eachMinuteTransactionChart.UpdateChart(data);
			}
				if (i>=10) {
					Data data1=TransactionData.Init("000009");	
					if (i==10) {
							eachMinuteTransactionChart.Clear();
					}
					eachMinuteTransactionChart.UpdateChart(data1);
				}
		
			
			/*if (i>=9&&i<=14) {
				//data.update();
				Data data2=TransactionData.Init("000009");
				eachMinuteTransactionChart.UpdateChart(data2);
			}*/
			//System.out.println("h");
			try {
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
