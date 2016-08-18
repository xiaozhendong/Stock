package edu.dlnu.liuwenpeng.DataInterface;

public class DataHelp {
	static public String format(String f) {
		return String.format("%.2f", Float.valueOf(f));
	}
	
	static public String format(Double f) {
		return String.format("%.2f", f);
	}
}