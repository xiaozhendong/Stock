package edu.dlnu.liuwenpeng.DataInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * Data接口的抽象实现
 */
public abstract class DataAbstract implements Data {

	@Override
	public DataItem get(int index) {
		return itemList.get(index);
	};
	
	@Override
	public List<DataItem> to_List() {
		return itemList;
	}
	
	protected boolean add(DataItem e) {
		// TODO Auto-generated method stub
		return itemList.add(e);
	}
	
	protected void clear() {
		// TODO Auto-generated method stub
		itemList.clear();
	}
	
	protected List<DataItem> subList(int start, int end) {
		return itemList.subList(start, end);
	}
	
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return itemList.isEmpty();
	}
	
	@Override
	public DataView gets(List<String> s) {
		// TODO Auto-generated method stub
		List<DataItem> d_ =  new ArrayList<>();
		for (int i = 0; i != s.size(); ++i) {
			if (get(i).get("code").equals(s.get(i))) 
				d_.add(get(i));
		}
		return new DataView(d_);
	}
	
	/*@Override
	public Stream<DataItem> stream() {
		return itemList.stream();
	}*/
	
	@Override
	public Iterator<DataItem> iterator() {
		// TODO Auto-generated method stub
		return itemList.iterator();
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return itemList.size();
	}
	
	@Override
	public DataItem[] to_Array() {
		// TODO Auto-generated method stub
		return itemList.toArray(new DataItem[size()]);
	}
	
	@Override
	public void setSortItem(String name) {
		// CodeDataItem.sort_item = name;
	}
	
	protected void sort() {
		Collections.sort(itemList);
	}
	
	private List<DataItem> itemList = new ArrayList<>();
}

/**
 * DataItem抽象实现
 */


