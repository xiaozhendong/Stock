package edu.dlnu.liuwenpeng.EachTransactionSupport;

import java.util.List;

public class GetAverage {

	public static Double getAverage(List<Double> ls) {
		double sum=0;
		for (Double iterable_element :ls) {
			sum=sum+iterable_element;
			
		}
		Double average=sum/(ls.size());
		return average;
		
	}

}
