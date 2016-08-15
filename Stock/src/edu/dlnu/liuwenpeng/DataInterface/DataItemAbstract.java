package edu.dlnu.liuwenpeng.DataInterface;

import java.util.List;

public class DataItemAbstract implements edu.dlnu.liuwenpeng.DataInterface.DataItem {
	public static  String sort_item = "code";
	
	@Override
	public int compareTo(DataItem arg0) {
		// TODO Auto-generated method stub
		Double this_sort = Double.valueOf(get(sort_item));
		Double arg0_sort = Double.valueOf(arg0.get(sort_item));
		return this_sort.compareTo(arg0_sort);
	}
	
	@Override
	public String get(int index) {
		// TODO Auto-generated method stub
		return data.get(index);
	}
	
	@Override
	public String get(String name) {
		// TODO Auto-generated method stub
		return data.get(_at(name));
	}
	
	@Override
	public void set(String name, String value) {
		// TODO Auto-generated method stub
		data.set(_at(name), value);
	}
	
	@Override
	public void set(int index, String value) {
		// TODO Auto-generated method stub
		data.set(index, value);
	}
	
	@Override
	public List<String> getAll() {
		// TODO Auto-generated method stub
		return data;
	}
	
	private int _at(String name) {
		return name_.indexOf(name);
	}
	
	protected List<String> name_;
	protected List<String> data;
}
