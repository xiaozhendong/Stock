package edu.dlnu.liuwenpeng.StockData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.dlnu.liuwenpeng.DataInterface.DataHelp;
import edu.dlnu.liuwenpeng.DataInterface.DataItem;
import edu.dlnu.liuwenpeng.DataInterface.DataItemAbstract;


public  class KData extends edu.dlnu.liuwenpeng.DataInterface.DataAbstract {
	private static Map<String, KData> k_Data = new HashMap<>();
	
	private KData() {  }
	
	/**
	 * KData工厂方法
	 * @param Category K线种类, 0->5分钟K线    1->15分钟K线    2->30分钟K线  3->1小时K线    4->日K线  5->周K线  6->月K线  7->1分钟  8->1分钟K线  9->日K线  10->季K线  11->年K线
	 * @param code 证卷代码
	 * @return
	 */
	public static KData Init(int Category, String code) {
		assert(Category >= 0 && Category < 12);//断言  相当于if判断语句
		KData k_ = k_Data.get(code);
		if (k_ == null) {
			k_ = new KData();
			k_Data.put(code, k_);
		}
		k_.Category = Category;
		k_.setCode(code);
		k_._init();
		
		return k_;
	}
	
	/**
	 * 设置所需获取数量
	 * @param _Count
	 * @return
	 */
	public KData setCount(int _Count) {
		Count = _Count;
		return this;
	}
	
	@Override
	public synchronized void update() {
		// TODO Auto-generated method stub
		String d_ = HQBase.GetSecurityBars(Category, market, code, Count);
		String[] d_l = d_.split("\n");  
		for (int i = 1; i != d_l.length; ++i) {
			String[] is_ = d_l[i].split("\t");
			DataItem n_ = get(i - 1);
			n_.set(1, is_[0]); // time
			n_.set(3, DataHelp.format(is_[2])); // close
			n_.set(4, DataHelp.format(is_[3])); // highest
			n_.set(5, DataHelp.format(is_[4])); // lowest
			if (is_[5].length() > 3)
				n_.set(6, DataHelp.format(is_[5].substring(0, is_[5].length() - 2))); // volume
			else 
				n_.set(6, DataHelp.format("0")); // volume
			n_.set(7, DataHelp.format(is_[6])); // turnover
		}
	}

	private void setCode(String code) {
		this.code = code;
	}
	
	private void _init() {
		String d_ = HQBase.GetSecurityBars(Category, market, code, Count);
		String[] d_l = d_.split("\n");  
		for (int i = 1; i != d_l.length; ++i) {
			List<String> n_ = new ArrayList<>();
			String[] is_ = d_l[i].split("\t");
			n_.add(code); // code
			n_.add(is_[0]); // time
			n_.add(DataHelp.format(is_[1])); // open
			n_.add(DataHelp.format(is_[2])); // close
			n_.add(DataHelp.format(is_[3])); // highest
			n_.add(DataHelp.format(is_[4])); // lowest
			if (is_[5].length() > 3)
				n_.add(DataHelp.format(is_[5].substring(0, is_[5].length() - 2))); // volume
			else 
				n_.add(DataHelp.format("0")); // volume
			n_.add(DataHelp.format(is_[6])); // turnover
			add(new KDataItem(n_));
		}
	}
	
	private String code;
	
	private int Category = 0;
	private int market = 0;
	private int Count = 30;
}


class KDataItem extends DataItemAbstract {
	public KDataItem(List<String> d_) {
		// TODO Auto-generated constructor stub
		data = d_;
		name_ = Arrays.asList("code", "time",  "open", "close", "highest", "lowest", "turnover", "volume");
	}
}
