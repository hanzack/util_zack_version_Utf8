package permutationAndSort;

import java.util.ArrayList;
import java.util.List;

public class permutationOfString {
	
	
	/**
	 * ���ַ����������ַ�����������ϡ�    ���磬 abcde     �᷵��һ��list   a b c d e ab bc cd de abc bcd cde abcd bcde abcde
	 * ֻ�������ڵġ� �������������������Ľ��зִʣ���һ�仰�������֣���Щ���ȿ������Ϊһ���ʡ� 
	 * @param str
	 * @return
	 */
	public static List<String> getSubNeighbourStringArr(String str){
		List<String> list111=new ArrayList<String>();
		boolean lastWord=true;
	//  ������1���֣�Ȼ���������ڵģ�Ȼ��3�����ڵġ���������  ���ѭ���������涨�ַ������ȵġ����ǳ���Ϊ1���ǳ���Ϊ2
		for (int i = 1; i <=str.length(); i++) {
			int j=0;//�ӵ�0��λ�ÿ�ʼ
			while (lastWord) {
				int x=i+j;
				if (x==str.length()+1) {
					break;    //����Ѿ��������һ���ˣ���˵��������ȵ��ַ����Ѿ�����ϡ�
				}else{
					list111.add(str.substring(j,x));  //��ȡ�ӵ�ǰλ�ÿ�ʼ Ȼ����� һ�����ȵ��ַ�����
				}
				j=j+1;
			}
		}
		return list111;
	}
}
