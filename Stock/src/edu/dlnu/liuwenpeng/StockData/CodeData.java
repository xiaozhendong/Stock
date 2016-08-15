package edu.dlnu.liuwenpeng.StockData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




enum CodeType {
	A_SHARE, B_SHARE, CLOSE_FUND, S_FUND, ETF_FUND, LOFS_FUND, SMEB, GEB, OTHER;
	
	public static CodeType getCodeType(String code, int market) {
		if (code.startsWith("60") && market == 1)
			return CodeType.A_SHARE;
		else if (code.startsWith("000") && market == 0)
			return CodeType.A_SHARE;
		else if (code.startsWith("90") || code.startsWith("20"))
			return CodeType.B_SHARE;
		else if (code.startsWith("50") || code.startsWith("18"))
			return CodeType.CLOSE_FUND;
		else if (code.startsWith("002"))
			return CodeType.SMEB;
		else if (code.startsWith("300"))
			return CodeType.GEB;
		else if (code.startsWith("519"))
			return CodeType.S_FUND;
		else if (code.startsWith("51") || code.startsWith("15"))
			return CodeType.ETF_FUND;
		else if (code.startsWith("16"))
			return CodeType.LOFS_FUND;
		return CodeType.OTHER;
	}
}


/**
 * 代表证卷代码的类
 */
public class CodeData extends edu.dlnu.liuwenpeng.DataInterface.DataAbstract {
	private static CodeData codeData = null;
	
	public static CodeData Init() {
		if (codeData == null) {
			codeData = new CodeData();
			codeData.update();
		}
		
		return codeData;
	}
	
	private CodeData() {
		// TODO Auto-generated constructor stub
	}

	/*
	@Override
	public void init() {
		// TODO Auto-generated method stub
//		File file = new File("code.xml");
//		try {
//			if (!file.exists()) {
		if (isEmpty())
			update();
			} else {
				FileInputStream fis = new FileInputStream(file); 
		        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		        
				SAXReader saxReader = new SAXReader();
				System.out.println("ddd");
				Document doc = saxReader.read(isr);
				Element rElement = doc.getRootElement();
				@SuppressWarnings("unchecked")
				Iterator<Element> iterator = rElement.elements("code").iterator();
				while (iterator.hasNext()) {
					Element cElement = iterator.next();
					Element nElement = cElement.element("name");
					Element mElement = cElement.element("market");
					
					List<String> d_ = new ArrayList<>();
					d_.add(rElement.attributeValue("value"));
					d_.add(mElement.getText());
					d_.add(nElement.getText());
					add(new CodeDataItem(d_));
				}
			}
		} catch (Exception e) {
			System.out.println("test");
			System.err.println(e);
		}
	}*/

	@Override
	public synchronized void update() {
		// TODO Auto-generated method stub
		clear();
		_get(0);
		_get(1);
		sort();
	}
	
	@Override
	public void setSortItem(String name) {
		CodeDataItem.sort_item = name;
	}

	private void _get(int market) {
		List<String> _d_l = HQBase.GetSecurityList(market);
		
		for (String _d : _d_l) {
			String[] _l = _d.split("\n");
			for (int i = 1; i < _l.length; i++) {
				String[] s_ =  _l[i].split("\t");
				List<String> s_List = new ArrayList<>();
				if( CodeType.getCodeType(s_[0], market) == CodeType.A_SHARE 
						| CodeType.getCodeType(s_[0], market) == CodeType.GEB 
						| CodeType.getCodeType(s_[0],market) == CodeType.SMEB){
				s_List.add(s_[0]);
				s_List.add(String.valueOf(market));
				s_List.add(s_[2]);
				s_List.add(s_[5]);
				add(new CodeDataItem(s_List));
				}
			}
		}
	}
	
/*	private void _in_xml() {
			Document doc = DocumentHelper.createDocument();
			doc.addElement("root");
			
			for (DataItem d_ : this) {
				_add_element(doc, d_);
			}
			
			try {
				XMLWriter xmlWriter = new XMLWriter(new FileWriter("code.xml"));

				xmlWriter.write(doc);
				xmlWriter.flush();
				xmlWriter.close();
			} catch (Exception e) {
				System.out.println(e);
			}
	}
	
	private void _add_element(Document doc, DataItem d_) {
		Element rElement = doc.getRootElement();
		
		Element cElement = rElement.addElement("code");
		Element mElement = cElement.addElement("market");
		Element nElement = cElement.addElement("name");
		
		try {
			cElement.addAttribute("value", d_.get("code"));
			mElement.setText(d_.get("market"));
			nElement.setText(d_.get("name"));
		} catch (Exception e) {
			System.err.println(e);
		}
	}*/
}

/**
 * 代表一条证卷代码中所有的信息
 * code: 代码
 * name: 股票名
 * market: 市场
 */
class CodeDataItem extends edu.dlnu.liuwenpeng.DataInterface.DataItemAbstract {
	
	public CodeDataItem(List<String> _d) {
		// TODO Auto-generated constructor stub
		data = _d;
		name_= Arrays.asList("code", "market", "name","close");
	}
}