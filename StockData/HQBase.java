package edu.dlnu.liuwenpeng.StockData;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.*;


/**
 * 行情请求接口的基本封装类
 * */
public class HQBase {
	public interface TdxHqLibrary extends Library {
		void TdxHq_Disconnect();
		boolean  TdxHq_Connect(String IP, int Port, byte[] Result, byte[] ErrInfo);
		boolean  TdxHq_GetSecurityBars(byte Category, byte Market, String Zqdm, short Start, ShortByReference Count, byte[] Result, byte[] ErrInfo);
		boolean  TdxHq_GetIndexBars(byte Category, byte Market, String Zqdm, short Start, ShortByReference Count, byte[] Result, byte[] ErrInfo);
		boolean  TdxHq_GetMinuteTimeData(byte Market, String Zqdm, byte[] Result, byte[] ErrInfo);
		boolean  TdxHq_GetHistoryMinuteTimeData(byte Market, String Zqdm, int date,byte[] Result, byte[] ErrInfo);
		boolean  TdxHq_GetTransactionData(byte Market,String Zqdm, short Start, ShortByReference Count, byte[] Result, byte[] ErrInfo);
		boolean  TdxHq_GetHistoryTransactionData(byte Market, String Zqdm, short Start, ShortByReference Count, int date, byte[] Result, byte[] ErrInfo);
		boolean  TdxHq_GetSecurityQuotes(byte[] Market, String[] Zqdm, ShortByReference Count, byte[] Result, byte[] ErrInfo);
		boolean  TdxHq_GetCompanyInfoCategory(byte Market, String Zqdm, byte[] Result, byte[] ErrInfo);
		boolean  TdxHq_GetCompanyInfoContent(byte Market, String Zqdm, String FileName, int Start, int Length, byte[] Result, byte[] ErrInfo);
		boolean  TdxHq_GetXDXRInfo(byte Market, String Zqdm, byte[] Result, byte[] ErrInfo);
		boolean  TdxHq_GetFinanceInfo(byte Market, String Zqdm, byte[] Result, byte[] ErrInfo);
        boolean  TdxHq_GetSecurityCount(byte Market, ShortByReference Result, byte[] ErrInfo);
        boolean  TdxHq_GetSecurityList(byte Market, short Start, ShortByReference  Count, byte[] Result, byte[] ErrInfo);
	}
	
	private static TdxHqLibrary _library = (TdxHqLibrary) Native.loadLibrary("TdxHqApi", TdxHqLibrary.class);
	
	private static byte[] Result = new byte[1024 * 1024];
	private static byte[] ErrInfo = new byte[256];
	private static short Start = 0;
	
	public static void setStart(short _Start) {
		Start = _Start;
	}
	
	/**
	 * 连接行情服务器
	 */
	public static void Connect(String IP, int Port) {
		if (!_library.TdxHq_Connect(IP, Port, Result, ErrInfo)) {
			System.err.println("connect error");
			System.exit(-1);
		}
	}
	
	/**
	 * 断开连接
	 */
	public static void Disconnect() {
		_library.TdxHq_Disconnect();
		System.out.println("disconnect");
	}
	
	
	/**
	 * 获取市场内证卷代码数量
	 * @param Market 市场 0为深圳 1为上海
	 */
	public static short GetSecurityCount(int Market) {
		ShortByReference Count = new ShortByReference();
		
		if (!_library.TdxHq_GetSecurityCount((byte)Market, Count, ErrInfo)) {
			System.err.println("get count error");
			System.exit(-1);
		}
		return Count.getValue();
	}
	
	/**
	 * 获取指定市场的所有代码
	 * @param Market 市场代码
	 * @return
	 */
	public static List<String> GetSecurityList(int Market) {
		ShortByReference Count = new ShortByReference();
		short nums = GetSecurityCount(Market);
		List<String> ret = new ArrayList<>();
		while ( Start != nums ) {
			if (!_library.TdxHq_GetSecurityList((byte)Market, Start, Count, Result, ErrInfo)) {
				System.err.println("get list error");
				System.exit(-1);
			}
			ret.add(Native.toString(Result, "GBK"));
			Start += Count.getValue();
		}
		Start = 0;
		return ret;
	}
	
	/**
	 * 获取股票k线
	 * @param Category K线种类, 0->5分钟K线    1->15分钟K线    2->30分钟K线  3->1小时K线    4->日K线  5->周K线  6->月K线  7->1分钟  8->1分钟K线  9->日K线  10->季K线  11->年K线
	 * @param Market 市场代码
	 * @param Zqdm 证卷代码
	 * @param _Count 获取数量
	 * @return
	 */
	public static String GetSecurityBars(int Category, int Market, String Zqdm, int _Count) {
		ShortByReference Count = new ShortByReference((short)_Count);
		if (!_library.TdxHq_GetSecurityBars((byte)Category, (byte)Market, Zqdm, Start, Count, Result, ErrInfo)) {
			System.err.println("get security bars error");
			System.exit(-1);
		}
		return Native.toString(Result, "GBK");
	}
	
	/**
	 * 获取指数k线
	 * @param Category K线种类, 0->5分钟K线    1->15分钟K线    2->30分钟K线  3->1小时K线    4->日K线  5->周K线  6->月K线  7->1分钟  8->1分钟K线  9->日K线  10->季K线  11->年K线
	 * @param Market 市场代码
	 * @param Zqdm 证卷代码
	 * @param _Count 获取数量
	 * @return
	 */
	public static String GetIndexBars(int Category, int Market, String Zqdm, int _Count) {
		ShortByReference Count = new ShortByReference((short)_Count);

		if (!_library.TdxHq_GetIndexBars((byte)Category, (byte)Market, Zqdm, Start, Count, Result, ErrInfo)) {
			System.err.println("get security bars error");
			System.exit(-1);
		}
		return Native.toString(Result, "GBK");
	}
	
	/**
	 * 获取分时图数据
	 * @param Market 市场代码
	 * @param Zqdm 证卷代码
	 * @return
	 */
	public static String GetMinuteTimeData(int Market, String Zqdm) {
		if (!_library.TdxHq_GetMinuteTimeData((byte)Market, Zqdm, Result, ErrInfo)) {
			System.err.println("get minute time error");
			System.exit(-1);
		}
		return Native.toString(Result, "GBK");
	}
	
	/**
	 * 获取历史分时图数据
	 * @param Market 市场代码
	 * @param Zqdm 证卷代码
	 * @param date 日期,比如2014年1月1日为整数20140101
	 * @return
	 */
	public static String GetHistoryMinuteTimeData(int Market, String Zqdm, int date) {
		if (!_library.TdxHq_GetHistoryMinuteTimeData((byte)Market, Zqdm, date, Result, ErrInfo)) {
			System.err.println("get history minute time error");
			System.exit(-1);
		}
		return Native.toString(Result, "GBK");
	}
	
	/**
	 * 获取分时成交数据
	 * @param Market 市场代码
	 * @param Zqdm 证卷代码
	 * @param _Count 获取数量
	 * @return
	 */
	public  static String GetTransactionData(int Market, String Zqdm, int _Count) {
		ShortByReference Count = new ShortByReference((short)_Count);

		if (!_library.TdxHq_GetTransactionData((byte)Market, Zqdm, Start, Count, Result, ErrInfo)) {
			System.err.println("get transaction data error");
			System.exit(-1);
		}
		return Native.toString(Result, "GBK");
	}
	
	public static List<String> GetAllTransactionData(int Market, String Zqdm) {
		ShortByReference Count = new ShortByReference((short)2000);
		
		List<String> sBuffer = new ArrayList<>();
		short start = 0;
		while (Count.getValue() == 2000) {
			if (!_library.TdxHq_GetTransactionData((byte)Market, Zqdm, start, Count, Result, ErrInfo)) {
				System.err.println("get transaction data error");
				System.exit(-1);
			}
			String str = Native.toString(Result, "GBK");
			sBuffer.add(str);
			start += 2000;
		}
		return sBuffer;
	}
	
	/**
	 * 获取历史分时成交数据
	 * @param Market 市场代码
	 * @param Zqdm 证卷代码
	 * @param date 日期,比如2014年1月1日为整数20140101
	 * @param _Count 获取的数量
	 * @return
	 */
	public static String GetHistoryTransactionData(int Market, String Zqdm, int _Count, int date) {
		ShortByReference Count = new ShortByReference((short)_Count);
		
		if (!_library.TdxHq_GetHistoryTransactionData((byte)Market, Zqdm, Start, Count, date, Result, ErrInfo)) {
			System.err.println("get history transaction data error");
			System.exit(-1);
		}
		return Native.toString(Result, "GBK");
	}
	
	/**
	 * 获取五档报价
	 * @param Market 市场代码,   0->深圳     1->上海, 第i个元素表示第i个证券的市场代码
	 * @param Zqdm 证券代码, Count个证券代码组成的数组
	 * @return
	 */
	public static String GetSecurityQuotes(String[] Zqdm) {
		ShortByReference Count = new ShortByReference((short)Zqdm.length);
		byte[] Market = { 0, 1 }; 
		
		if (!_library.TdxHq_GetSecurityQuotes(Market, Zqdm, Count, Result, ErrInfo)) {
			System.err.println("get security quote error");
			System.exit(-1);
		}
		return Native.toString(Result, "GBK");
	}
	
	/*
	 * 其他待定。。。
	 */
/*	
	public static void main(String[] args) {
		Connect("121.14.110.200", 443);
		System.out.println(GetTransactionData(0, "000001",  10));
		System.out.println("Hello, World!");
		Disconnect();
	}*/
}