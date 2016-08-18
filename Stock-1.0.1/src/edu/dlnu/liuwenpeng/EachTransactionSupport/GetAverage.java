package edu.dlnu.liuwenpeng.EachTransactionSupport;

import java.util.List;

/**
 * it's only used to get average of list data
 * @author mr.xiao
 *
 */
public class GetAverage {

	/**
	 * it's used to get average of list data
	 * @param List<Double> of data,and it must be double.
	 * @return the average of data
	 *
	 */
	public static Double getAverage(List<Double> ls) {
		double sum=0;
		for (Double iterable_element :ls) {
			sum=sum+iterable_element;
			
		}
		Double average=sum/(ls.size());
		return average;
		
	}

}
