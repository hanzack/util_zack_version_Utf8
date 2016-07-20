package normalUse;

import org.junit.Test;

public class regular_Expression {

	
	/**
	 * 输入一个字符串， 去掉里面连续重复出现的字符。 里如输入aabbcccdd， 返回abcd.
	 * 输入  aabbbcccbbbddef   返回的是abcbdef   注意这里面还是有两个b
	 */
	public static String removeContinueRepeatLetter(String temp){
		String abcString=temp.replaceAll("(\\w)(\\1)*","$1");
//		System.out.println();
		return abcString;
	}
	
	
	/**
	 * @param temp
	 * @return     去掉所有重复的字符串。
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
