package normalUse;

import org.junit.Test;

public class regular_Expression {

	
	/**
	 * ����һ���ַ����� ȥ�����������ظ����ֵ��ַ��� ��������aabbcccdd�� ����abcd.
	 * ����  aabbbcccbbbddef   ���ص���abcbdef   ע�������滹��������b
	 */
	public static String removeContinueRepeatLetter(String temp){
		String abcString=temp.replaceAll("(\\w)(\\1)*","$1");
//		System.out.println();
		return abcString;
	}
	
	
	/**
	 * @param temp
	 * @return     ȥ�������ظ����ַ�����
	 */
	public static String removeAllRepeatLetter(String temp){
		String abcString="";
		
		return abcString;
	}
	
	
	
	@Test
	public void test1(){
		removeContinueRepeatLetter("aabbbcccbbbddef");
	}
}
