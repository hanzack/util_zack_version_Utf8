package list_Set_Map_Handle_Zack_util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mapHandleZackUtil {

	/**
	 * 可以对字母进行排序， 也可以对数字进行排序。
	 * 传进来一个map   对value进行排序。 <String, Integer>
	 * @param map
	 * @param isAsc :  是否采用正序的,true 正序，  false倒叙
	 * @return
	 */
	public static Map sortMapByMapValue(Map map,final boolean isAsc){
		Map resultMap=new LinkedHashMap();
		//把map的keyset放进list里面去， 这里使用linkedlist
		List list=new LinkedList(map.entrySet());
		//调用顶级接口collections.sort
		//里面接受要排序的东西， 和比较器，告诉它按照怎样的规则排序， Comparator 是一个接口， 新建他的对象必须要实现里面的方法。 
		Collections.sort(list,new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			if (isAsc) {
				return ((Comparable)((Map.Entry)o1).getValue()).compareTo(((Map.Entry)o2).getValue());
			}else {
				return -((Comparable)((Map.Entry)o1).getValue()).compareTo(((Map.Entry)o2).getValue());
			}
		}
				
	});
	
		for (Iterator iter=list.iterator();iter.hasNext();) {
			Map.Entry entry=(Map.Entry)iter.next();
//			System.out.println(entry.getKey()+" : "+entry.getValue());
			
			///如果上面定义的是普通的hashmap,那么下面这样写是错误的。 这样直接加进新的map中是没有用的， 又会回到不排序的情况。 
			resultMap.put(entry.getKey(), entry.getValue());
			//所以要定义成 linkedHashMap
		
		}
		
		return resultMap;
	}
	
	
	
	/**
	 * 可以对字母进行排序， 也可以对数字进行排序。
	 * 传进来一个map   对key进行排序。 <String, Integer>
	 * @param map
	 * @param isAsc :  是否采用正序的,true 正序，  false倒叙
	 * @return
	 */
	public static Map sortMapByMapKey(Map map,final boolean isAsc){
		Map resultMap=new LinkedHashMap();
		//把map的keyset放进list里面去， 这里使用linkedlist
		List list=new LinkedList(map.entrySet());
		//调用顶级接口collections.sort
		//里面接受要排序的东西， 和比较器，告诉它按照怎样的规则排序， Comparator 是一个接口， 新建他的对象必须要实现里面的方法。 
		Collections.sort(list,new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub
			if (isAsc) {
				return ((Comparable)((Map.Entry)o1).getKey()).compareTo(((Map.Entry)o2).getKey());
			}else {
				return -((Comparable)((Map.Entry)o1).getKey()).compareTo(((Map.Entry)o2).getKey());
			}
		}
				
	});
	
		for (Iterator iter=list.iterator();iter.hasNext();) {
			Map.Entry entry=(Map.Entry)iter.next();
//			System.out.println(entry.getKey()+" : "+entry.getValue());
			
			///如果上面定义的是普通的hashmap,那么下面这样写是错误的。 这样直接加进新的map中是没有用的， 又会回到不排序的情况。 
			resultMap.put(entry.getKey(), entry.getValue());
			//所以要定义成 linkedHashMap
		}
		
		return resultMap;
	}
		
	
	

	//compare这个方法是传入两个对象， 如果返回1  说明，第一个对象要排到第二个对象前面。
    //  如果返回-1说明第二个对象排到第一个前面
//  至于什么情况下返回1 或者返回-1 由自己决定


/**
* 可以对字母进行排序， 也可以对数字进行排序。   注意这里我自己写了一个compareTwo方法用来比对大小。  而且这个类是继承了Comparable那个接口的。
* 传进来一个map   按照key的首字母进行排序。 <String, Integer>
* @param map
* @param isAsc :  是否采用正序的,true 正序，  false倒叙
* @return
*/
public static Map sortMapByMapKeyFirstLetter(Map map,final boolean isAsc){
	Map resultMap=new LinkedHashMap();
	//把map的keyset放进list里面去， 这里使用linkedlist
	List list=new LinkedList(map.entrySet());
	//调用顶级接口collections.sort
	//里面接受要排序的东西， 和比较器，告诉它按照怎样的规则排序， Comparator 是一个接口， 新建他的对象必须要实现里面的方法。 
	Collections.sort(list,new Comparator() {
	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		//获取第一个key的开头的数字部分       这里是对第一个对象的key  的 开头的数字部分进行取出。
		String firString=(((Map.Entry)o1).getKey()).toString();
		String regex="[\\d]+";
		Pattern pattern1=Pattern.compile(regex);
		Matcher matcher=pattern1.matcher(firString);
		if (matcher.find()) {
			firString=matcher.group();
		}
		int firstone=Integer.parseInt(firString);
		//获取第一个key的开头的数字部分     这里是对第二个对象的key  的 开头的数字部分进行取出。
		String SecString=(((Map.Entry)o2).getKey()).toString();
		String regex1="[\\d]+";
		Pattern pattern2=Pattern.compile(regex1);
		Matcher matcher2=pattern2.matcher(SecString);
		if (matcher2.find()) {
			SecString=matcher2.group();
		}
		int secondone=Integer.parseInt(SecString);
		
		if (isAsc) { // 如果采用正序
//			System.out.println((Integer) ((Comparable)(firString).compareTo(SecString))+" : "+firString+"---"+SecString);
			//这里是调用方法进行比较， 如果第一个对象的 key的数字部分大与   第二个对象的key的数字部分，返回1， 否则返回-1   。相等的话-1 或者1都一样
			return compareTwoObject(firstone, secondone);
		}else {
			return -compareTwoObject(firstone, secondone);
		}
	}
			
});
	for (Iterator iter=list.iterator();iter.hasNext();) {
		Map.Entry entry=(Map.Entry)iter.next();
//		System.out.println(entry.getKey()+" : "+entry.getValue());
		
		///如果上面定义的是普通的hashmap,那么下面这样写是错误的。 这样直接加进新的map中是没有用的， 又会回到不排序的情况。 
		resultMap.put(entry.getKey(), entry.getValue());
		//所以要定义成 linkedHashMap
	}
	System.out.println("=========================");
	return resultMap;
}


/**
* 比较两个数字大小进行返回
* @param a
* @param b
* @return
*/
public static int compareTwoObject(int a,int b ){
	
	return a>=b?1:-1;
}


	
	
	
	
	
}
