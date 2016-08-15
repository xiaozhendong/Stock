package edu.dlnu.liuwenpeng.DataInterface;

import java.util.List;

/**
 * 存储单行数据的接口
 * @author Administrator
 */
public interface DataItem extends Comparable<DataItem> { 
	
	/**
	 * 通过索引获取数据
	 * @param index
	 * @return
	 */
	public String get(int index);
	
	/** 
	 * 通过字符串名称获取数据  
	 * @param name 
	 * @return
	 */
	public String get(String name);
	
	/**
	 * 设置新值
	 * @param name
	 * @param value
	 */
	public void set(String name, String value);
	
	/**
	 * 设置新值
	 * @param name
	 * @param value
	 */
	public void set(int index, String value);
	
	/**
	 * 获取所有字段
	 * @return
	 */
	public List<String> getAll();
}