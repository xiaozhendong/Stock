package edu.dlnu.liuwenpeng.KLineSupport;

import java.util.List;

final class DataAxisRangeOfKLine {

	public DataAxisRangeOfKLine() {
		// TODO Auto-generated constructor stub
	}

	public Double getMaxValue(List<Double> high_data){
		Double maxdata=high_data.get(0);
		for (Double double1 : high_data) {
			if (double1>maxdata) {
				maxdata=double1;
			}
		}
		return maxdata;
		
	}
	
	public Double getMinValue(List<Double> low_data){
		Double mindata=low_data.get(0);
		for (Double double1 : low_data) {
			if (double1<mindata) {
				mindata=double1;
			}
		}
		return mindata;
		
	}
}
