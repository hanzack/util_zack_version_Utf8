package testUtil;

import java.util.HashMap;
import java.util.Map;

import normalUse.input_Util;

import org.junit.Test;

public class testMap {
	
	
	
	
	@Test
	public void testMap(){
		Map<String, Integer> strMap=new HashMap<String, Integer>();
		strMap.put("abc", 1);
		strMap.put("def", 1);
		strMap.put("gui", 1);
		String word="abc";
		System.out.println(strMap.get(word));
		if (strMap.containsKey(word)) {
			strMap.put(word, (strMap.get(word)+1));
		}
		System.out.println(strMap.get(word));
	}

}
