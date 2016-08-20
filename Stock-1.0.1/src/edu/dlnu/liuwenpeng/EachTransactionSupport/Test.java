package edu.dlnu.liuwenpeng.EachTransactionSupport;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;

import edu.dlnu.liuwenpeng.StockData.HQBase;
import edu.dlnu.liuwenpeng.DataInterface.Data;
import edu.dlnu.liuwenpeng.DataInterface.DataItem;
import edu.dlnu.liuwenpeng.StockData.MinuteTimeData;
import edu.dlnu.liuwenpeng.StockData.TransactionData;

public class Test {

	public Test() {
		HQBase.Connect("211.100.49.196", 7709);
		Data data=MinuteTimeData.Init("000008");
		
		
/*	    for (DataItem dataItem : data) {
			System.out.println(dataItem.get("bs"));
		}*/
		//long starts=System.currentTimeMillis();
		EachTransactionChart eachTransactionChart=new EachTransactionChart();
		//long ends=System.currentTimeMillis();
		//System.out.println(ends-starts);
		//eachTransactionChart.createDataset(data);
		  ChartFrame jFrame;
		
		JFreeChart chart =eachTransactionChart.createChart(data);
		jFrame=new ChartFrame("¹ÉÆ±", chart);
		 jFrame.setSize(1000,600 );
		jFrame.setVisible(true);
		Data data2=MinuteTimeData.Init("000007");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int i = 0; i < 100; i++) {
			//long start=System.currentTimeMillis();
			data.update();
			data2.update();
			try {
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i<5) {
				eachTransactionChart.UpdateChart(data);
			}else{
				eachTransactionChart.UpdateChart(data2);
			}
			
			//long end=System.currentTimeMillis();
			//System.out.println(end-start);
			
			try {
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//if (i>=3&&i<=7) {
				
				
			}
		
		//}
		HQBase.Disconnect();
	}
	
	public static void main(String args[]) 
	{
		new Test();
		
	}
}

