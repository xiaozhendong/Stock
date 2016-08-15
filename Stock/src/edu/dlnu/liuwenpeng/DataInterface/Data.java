package edu.dlnu.liuwenpeng.DataInterface;

import java.util.*;


/**
 * 获取数据信息的接口
 * @author Administrator
 */
public interface Data extends Iterable<DataItem> {
	
	/**
	 * 更新Data对象
	 */
	public void update();
	
	/**
	 * 获取所需要的DataItem
	 * @param s 保存证卷代码的List
	 * @return
	 */
	public DataView gets(List<String> s);
	
	/**
	 * 通过索引获取DataItem
	 * @param index
	 * @return
	 */
	public DataItem get(int index);
	
	/**
	 * 设置排序字段
	 */
	public void setSortItem(String name);
	
	/**
	 * 获取Data的长度
	 * @return
	 */
	public int size();
	
	/**
	 * Data转换为Arrays
	 * @return
	 */
	public DataItem[] to_Array();
	
	/**
	 * 
	 */
	public List<DataItem> to_List();
	
	/**
	 * 获取当前数据的Stream
	 * @return
	 */
	// public Stream<DataItem> stream();
	// public List<DataItem> sort();

}

/**
 * 获取历史信息的接口
 * @author Administrator
 */
interface History extends Data {
	/**
	 * 指定所需获取的时间
	 * @param date
	 */
	public void setDate(int date);
}

