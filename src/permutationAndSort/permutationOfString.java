package permutationAndSort;

import java.util.ArrayList;
import java.util.List;

public class permutationOfString {
	
	
	/**
	 * 对字符的相邻子字符串求排列组合。    例如， abcde     会返回一个list   a b c d e ab bc cd de abc bcd cde abcd bcde abcde
	 * 只排列相邻的。 本方法可以用来对中文进行分词，看一句话，从左到又，哪些长度可以组成为一个词。 
	 * @param str
	 * @return
	 */
	public static List<String> getSubNeighbourStringArr(String str){
		List<String> list111=new ArrayList<String>();
		boolean lastWord=true;
	//  首先是1个字，然后两个相邻的，然后3个相邻的。。。。。  这个循环是用来规定字符串长度的。先是长度为1再是长度为2
		for (int i = 1; i <=str.length(); i++) {
			int j=0;//从第0号位置开始
			while (lastWord) {
				int x=i+j;
				if (x==str.length()+1) {
					break;    //如果已经数到最后一个了，则说明这个长度的字符串已经数完毕。
				}else{
					list111.add(str.substring(j,x));  //获取从当前位置开始 然后输出 一定长度的字符串。
				}
				j=j+1;
			}
		}
		return list111;
	}
}
