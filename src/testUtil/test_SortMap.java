package testUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import list_Set_Map_Handle_Zack_util.mapHandleZackUtil;

import org.junit.Test;

public class test_SortMap {

	
	
	
	/**
	 * ≤‚ ‘map∞¥’’valueµƒ÷µ≈≈–Ú
	 */
	@Test
	public void test_sortMapHere(){
		Map<String, Integer> map=new HashMap<String, Integer>();

		map.put("cde", 6);
		map.put("cda", 9);
		map.put("cdc", 2);
		map.put("cdd1", 3);
		map.put("cd2", 61);
		map.put("ab", 1);
		map.put("cd", 0);
		
		Set<String> keys=map.keySet();
		for (String ke:keys) {
			System.out.println(ke+"---"+map.get(ke));
		}
		
		System.out.println("============================");
		Map<String, Integer> mapNew=mapHandleZackUtil.sortMapByMapValue(map,true);
		
		Set<String> keys1=mapNew.keySet();
		for (String ke:keys1) {
			System.out.println(ke+"---"+mapNew.get(ke));
		}
		
	}
	
	
	
	
	/**
	 * ≤‚ ‘map∞¥’’keyµƒ÷µ≈≈–Ú
	 */
	@Test
	public void test_sortMapByKeyHere(){
		Map<Integer, String> map=new HashMap<Integer, String>();

		map.put(31, "zad");
		map.put(62, "abc1");
		map.put(9, "ag");
		map.put(20, "wbb");
		map.put(8, "lkm");
		map.put(4, "lcd");
		map.put(7, "lj");
		
		Set<Integer> keys=map.keySet();
		for (Integer ke:keys) {
			System.out.println(ke+"---"+map.get(ke));
		}
		
		System.out.println("============================");
		Map<Integer, String> mapNew=mapHandleZackUtil.sortMapByMapValue(map, true);
		
		Set<Integer> keys1=mapNew.keySet();
		for (Integer ke:keys1) {
			System.out.println(ke+"---"+mapNew.get(ke));
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
