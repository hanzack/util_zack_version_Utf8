package testUtil;

import static org.junit.Assert.*;

import org.junit.Test;

import Primary_data_type_zack_util.String_HANZI_Zack_util;

public class Test_String_IsHANZI {

	@Test
	public void test_isHanzi(){
		boolean condition=String_HANZI_Zack_util.isHanzi("他是一个good person");
		assertTrue("Wrong", condition);
		assertTrue("Wrong", String_HANZI_Zack_util.isHanzi("他是"));
		assertTrue("Wrong", String_HANZI_Zack_util.isHanzi("a good他是"));
		assertFalse("Wrong", String_HANZI_Zack_util.isHanzi("a good"));
	}
	
	
	@Test
	public void test_getAllHANZI(){
		String content="just go他是一个very厉害2的人1";
		String res=String_HANZI_Zack_util.getAllHANZI(content);
		System.out.println(res);
		
	}
	
	
	@Test
	public void test_containStrInHANZI(){
		String content="jx是一个特别漂亮，单纯的小女孩，她有一只够";
		String sampleHANZI="漂亮";
		
		boolean abc=String_HANZI_Zack_util.containStrInHANZI(content, "大方","美丽","小女孩");
		System.out.println(abc);
	}
	
	
}
