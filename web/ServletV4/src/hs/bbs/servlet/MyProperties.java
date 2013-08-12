package hs.bbs.servlet;

import hs.bbs.DEFINE;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class MyProperties {
	private String TAG = "MyProperties";
	private HashMap<String, Object> hm = new HashMap<>();
	private Properties properties=null;
	private FileReader fr = null;

	public MyProperties(String propertiesPath) {
		properties = new Properties();
		try {
			fr = new FileReader(propertiesPath);
			properties.load(fr);
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<String, Object> getHashMap() {
		Iterator<Object> iterator = properties.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String impl = properties.getProperty(key);
			Object obj = null;
			try {
				Class find_Class = Class.forName(impl);
				obj = find_Class.newInstance();
				DEFINE.printTAG(TAG, "key : " +key);

				DEFINE.printTAG(TAG, "value obj_name : " + obj.toString());
				hm.put(key, obj);
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException e) {
				// TODO Auto-generated catch block
				DEFINE.printTAG(TAG, e.toString());
				e.printStackTrace();
			}
		}
		return hm;
	}
}
