package testUtil;
import static org.junit.Assert.*;

import org.junit.Test;

import normalUse.input_Util_without_Pi;
import testUtil.*;
import utilAssignment.input_Util;

public class testUtil {

	public static void main(String[] args) {
		isFomuTestUtilTest();
	}
	
	@Test
	public  void testExpression() {
		boolean flag=input_Util_without_Pi.checkAtoZ("abc1");
		assertFalse("this is false",flag);
		assertTrue("this is True",input_Util_without_Pi.checkAtoZ("def"));
	}
	
	
	@Test
	public void goTest(){
		System.out.println(normalUse.input_Util.isNumber("abc"));
	}
	
	
	@Test
	public static void isFomuTestUtilTest(){
//		String teString=input_Util.removeSpace("=abc()def");
//		
//		input_Util.isFomulation(teString);
//		System.out.println("finish");
//		
//		System.out.println(" ".matches("[0-9]"));
		
		
//		System.out.println("a1b2A_".matches("\\w*"));
//		System.out.println("k".matches("[^abc]"));
//		System.out.println("909+-12".matches("[\\d\\s\\*\\+\\-\\/\\[\\]\\.]*"));
//		System.out.println("++*".matches("[\\+\\-]*[\\d]+[\\.\\d+]?([\\*]{0,2}|[\\/]|[\\+\\-]+"));
//		System.out.println("3".matches("[\\+\\-]*[\\d]+(\\.\\d+)?((([\\*]{0,2}|[\\/])?([\\+\\-])*)(\\d+)(\\.\\d+)?)*"));
		
//		System.out.println("++9.42".matches("[\\+\\-]*[\\d]+(\\.\\d+)?"));
		
//		String teString="abc3209_a";
//		System.out.println("=(2+3 )/9*0".matches("^=[\\d\\*\\s\\+\\-\\/()\\[\\]\\.]*"));

//		System.out.println("aabbbccccccddd".replaceAll("(\\w)\\1","#"));
		
		
//		System.out.println("abc.java".split("."));
//		String teString="abc3209_a";
//		boolean abc=input_Util.isUserName(teString);
//		System.out.println(abc);
		
		
//		String abc="dasf lkj jlakjfdl lksa lk";
//		String def=abc.replaceAll("\\s","");
//		System.out.println(def);
		
//		System.out.println("1011221101".matches("^(.?)(.?)(.?)(.?)(.?)(.?)(.?)(.?)(.?).?\\9\\8\\7\\6\\5\\4\\3\\2\\1$"));
		
		
//		System.out.println("12347777".matches("[\\d]{3}[\\d](\\d){2}\\1"));
//	     char abc='(';	
//		System.out.println((abc+"").matches("[\\d\\)]"));
//		System.out.println(("bcd").matches("[\\w&&[^a]]"));
		
		
//		System.out.println(("b(c)d").replaceAll("[\\(\\)]", ""));
		
//		String formulation="=(3+2)**(6+7)/6/907+((-78)/32)+(---78/32)";
		String formulation="=-3.43+(3-*+++--+3)";
		boolean ppp=input_Util_without_Pi.isFomulation(formulation);
		System.out.println(ppp);
		
		
//		formulation=formulation.replaceAll("^=", "");
//		System.out.println(formulation);
		
		
	}
	
}
