package list_Set_Map_Handle_Zack_util;

import java.util.ArrayList;

public class list_handle_zack_util {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> abc=addIntToList(null,10,12);
		for (int i = 0; i < abc.size(); i++) {
			System.out.println(abc.get(i));
		}
	}
	
	
	/**快速生成带有数据的list---int型号
	 * 这个方法是用来快速往list里面加入一堆数字。 例如addIntToList(null,10,12);  10表示从0到10全部加入到list中去， 第二个数以后的是就是表示单独加这个数。 
	 * 此处addIntToList(null,10,12)  结果是  0 1 2 3 4 5 6 7 8 9 12 这些数加入到list中去。
	 * addIntToList(null,10,-65535) 表示只把0-10 加入list中。          如果是addIntToList(0,2,3,4,7,89) 表示只把2,3,4,7,89 加入到list中去
	 * 其中null表示你要往哪个list里面添加， 如果是null表示新建一个集合， 否则就是往给定的集合里面添加
	 * @param length1
	 * @param lotsNum
	 * @return
	 */
	public static ArrayList<Integer> addIntToList(ArrayList<Integer> intList, int length1, int... lotsNum){
		ArrayList<Integer> list;
		if (intList==null) {
			list=new ArrayList<>();
		}else {
			list=intList;
		}
	
		
		if (length1!=0) {
			for (int i = 0; i < length1; i++) {
				list.add(i);
			}
		}
		if (lotsNum!=null && lotsNum[0]!=-65535) {
			for (int x:lotsNum) {
				list.add(x);
			}
		}
		
		return list;
	}
	
	
	/**快速生成带有数据的list---double型号
	 * 这个方法是用来快速往list里面加入一堆数字。 例如addDoubleToList(null,10,12);  10表示从0到10全部加入到list中去， 第二个数以后的是就是表示单独加这个数。 
	 * 此处addDoubleToList(null,10,12)  结果是  0 1 2 3 4 5 6 7 8 9 12 这些数加入到list中去。
	 * addIntToList(null,10,-65535) 表示只把0-10 加入list中。          如果是addDoubleToList(0,2,3,4,7,89) 表示只把2,3,4,7,89 加入到list中去
	 * 其中null表示你要往哪个list里面添加， 如果是null表示新建一个集合， 否则就是往给定的集合里面添加
	 * @param intList
	 * @param length1
	 * @param lotsNum
	 * @return
	 */
	public static ArrayList<Double> addDoubleToList(ArrayList<Double> intList, int length1, Double... lotsNum){
		ArrayList<Double> list;
		if (intList==null) {
			list=new ArrayList<>();
		}else {
			list=intList;
		}
	
		
		if (length1!=0) {
			for (int i = 0; i < length1; i++) {
				list.add(i*1.0);
			}
		}
		if (lotsNum!=null && lotsNum[0]!=-65535) {
			for (Double x:lotsNum) {
				list.add(x);
			}
		}
		
		return list;
	}
	
	
	
	
	
	
	
}
