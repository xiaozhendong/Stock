package edu.dlnu.liuwenpeng.StockData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.dlnu.liuwenpeng.DataInterface.DataHelp;
import edu.dlnu.liuwenpeng.DataInterface.DataItem;
import edu.dlnu.liuwenpeng.DataInterface.DataItemAbstract;


public class TransactionData extends edu.dlnu.liuwenpeng.DataInterface.DataAbstract {

	private static Map<String, TransactionData> t_Data = new HashMap<>();

	private TransactionData() {
		// TODO Auto-generated constructor stub
	}

	public static TransactionData Init(String code) {
		// TODO Auto-generated constructor stub
		TransactionData t_ = t_Data.get(code);
		if (t_ == null) {
			t_ = new TransactionData();
			t_Data.put(code, t_);
			t_.code = code;
			t_._init();
		}
		return t_;
	}

	@Override
	public synchronized void update() {
		// TODO Auto-generated method stub
		String d_ = HQBase.GetTransactionData(0, code, 60);
		String[] d_l = d_.split("\n");

		time = d_l[d_l.length - 1].split("\t")[0];

		if (get(size() - 1).get("time").equals(time)) {
			_update_the_minute(d_l, -1);
		} else {
			_update_new_minute(d_l);
		}
	}

	private void _init() {
		List<String> d_ = HQBase.GetAllTransactionData(0, code);
		
		for (int j = d_.size() - 1; j != -1; --j) {
			String[] d_l = d_.get(j).split("\n");
			String time = "";
			int _i_num = 1;
			for (int i = 1; i != d_l.length; ++i) {
				String[] is_ = d_l[i].split("\t");
	
				if (isEmpty()) {
					_the_minute_add(is_);
				} else if (!time.equals(is_[0])) {
					_price_average(get(size() - 1), _i_num);
					_i_num = 1;
					
					_the_minute_add(is_);
					time = is_[0];
				} else if (i == d_l.length - 1) {
					_price_average(get(size() - 1), _i_num);
					_i_num = 1;
					
					time = is_[0];
				} else {
					_the_minute_set(is_, get(size() - 1));
					++_i_num;
				}
			}
		}
	}

	private void _update_the_minute(String[] d_l, int index) {
		int _i_num = 1;
		int flag = 1;
		
		for (int i = d_l.length + index; i != 0; --i) {
			String[] is_ = d_l[i].split("\t");
			if (!time.equals(is_[0])) {
				_price_average(get(size() - flag), _i_num);
				_i_num = 1;  flag++;
				if (flag > 2) break;
			}
			_the_minute_set(is_, get(size() - flag));
			++_i_num;
		}
	}

	private void _update_new_minute(String[] d_l) {
		String[] s_ = d_l[d_l.length - 1].split("\t");
		_the_minute_add(s_);
		
		_update_the_minute(d_l, -2);
	}

	private void _the_minute_add(String[] is_) {
		List<String> n_ = new ArrayList<>();
		
		n_.add(code); // code
		n_.add(is_[0]); // time
		n_.add(is_[1]); // price
		n_.add(is_[2]); // amount
		n_.add(is_[3]); // num
		n_.add(is_[4]); // bs
		add(new TransactionDataItem(n_));
	}

	private void _the_minute_set(String[] is_, DataItem dItem) {		
		double price = Double.valueOf(dItem.get("price"));
		int amount = Integer.valueOf(dItem.get("amount"));
		int num = Integer.valueOf(dItem.get("num"));

		double n_price = Double.valueOf(is_[1]);
		int n_amount = Integer.valueOf(is_[2]);
		int n_num = Integer.valueOf(is_[3]);

		dItem.set(2, Double.toString(price + n_price));
		dItem.set(3, Integer.toString(amount + n_amount));
		dItem.set(4, Integer.toString(num + n_num));
	}
	
	private void _price_average(DataItem dItem, int _i_num) {
		double price = Double.valueOf(dItem.get("price"));
		dItem.set(2, DataHelp.format(price / _i_num));
	}

	private String code;
	private String time;
}

class TransactionDataItem extends DataItemAbstract {

	/**
	 * 0|code:代码, 1|time: 时间, 2|price: 价格, 3|amount: 现量, 5|num: 笔数, 5|bs: 买卖
	 */
	public TransactionDataItem(List<String> d_) {
		// TODO Auto-generated constructor stub
		data = d_;
		name_ = Arrays.asList("code", "time", "price", "amount", "num", "bs");
	}
}