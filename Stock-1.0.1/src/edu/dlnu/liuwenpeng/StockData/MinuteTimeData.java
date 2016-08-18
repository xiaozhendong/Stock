package edu.dlnu.liuwenpeng.StockData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.dlnu.liuwenpeng.DataInterface.DataHelp;
import edu.dlnu.liuwenpeng.DataInterface.DataItemAbstract;

public class MinuteTimeData extends edu.dlnu.liuwenpeng.DataInterface.DataAbstract {
	private static Map<String, MinuteTimeData> m_Data = new HashMap<>();

	private MinuteTimeData() {

	}

	public static MinuteTimeData Init(String code) {
		// TODO Auto-generated constructor stub
		MinuteTimeData m_ = m_Data.get(code);
		if (m_ == null) {
			m_ = new MinuteTimeData();
			m_Data.put(code, m_);
			m_.code = code;
			m_._init();
		}
		return m_;
	}

	@Override
	public synchronized void update() {
		// TODO Auto-generated method stub
		clear();
		_init();
	}

	private void _init() {
		List<String> str_l = HQBase.GetAllTransactionData(0, code);

		for (int j = str_l.size() - 1; j != -1; --j) {
			String[] d_l = str_l.get(j).split("\n");
			for (int i = 1; i != d_l.length; ++i) {
				List<String> n_ = new ArrayList<>();
				String[] is_ = d_l[i].split("\t");
				n_.add(code); // code
				n_.add(is_[0]);
				n_.add(DataHelp.format(is_[1]));
				n_.add(is_[2]);
				n_.add(is_[3]);
				n_.add(is_[4]);
				add(new MinuteTimeDataItem(n_));
			}
		}
	}

	private String code;
}

class MinuteTimeDataItem extends DataItemAbstract {


	/**
	 * 0|code:代码, 1|time: 时间, 2|price: 价格, 3|amount: 现量, 4|num: 笔数, 5|bs: 买卖
	 */
	public MinuteTimeDataItem(List<String> d_) {
		// TODO Auto-generated constructor stub
		data = d_;
		name_ = Arrays.asList("code", "time", "price", "amount", "num", "bs");
	}
}