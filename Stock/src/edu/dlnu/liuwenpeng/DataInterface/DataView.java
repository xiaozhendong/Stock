package edu.dlnu.liuwenpeng.DataInterface;

import java.util.Iterator;
import java.util.List;

public class DataView implements Iterable<DataItem> {
	public DataView(List<DataItem> dI) {
		// TODO Auto-generated constructor stub
		dataItem = dI;
	}
	
	public DataItem get(int index) {
		return dataItem.get(index);
	}
	
	public List<DataItem> toList() {
		return dataItem;
	}
	
	public Object[] toArray() {
		return dataItem.toArray();
	}
	
	@Override
	public Iterator<DataItem> iterator() {
		// TODO Auto-generated method stub
		return dataItem.iterator();
	}

	List<DataItem> dataItem;
}