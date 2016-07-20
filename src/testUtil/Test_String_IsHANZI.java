package testUtil;

import static org.junit.Assert.*;

import org.junit.Test;

import Primary_data_type_zack_util.String_HANZI_Zack_util;

public class Test_String_IsHANZI {

	@Test
	public void test_isHanzi(){
		boolean condition=String_HANZI_Zack_util.isHanzi("����һ��good person");
		assertTrue("Wrong", condition);
		assertTrue("Wrong", String_HANZI_Zack_util.isHanzi("����"));
		assertTrue("Wrong", String_HANZI_Zack_util.isHanzi("a good����"));
		assertFalse("Wrong", String_HANZI_Zack_util.isHanzi("a good"));
	}
	
	
	@Test
	public void test_getAllHANZI(){
		String content="just go����һ��very����2����1";
		String res=String_HANZI_Zack_util.getAllHANZI(content);
		System.out.println(res);
		
	}
	
	
	@Test
	public void test_containStrInHANZI(){
		String content="jx��һ���ر�Ư����������СŮ��������һֻ��";
		String sampleHANZI="Ư��";
		
		boolean abc=String_HANZI_Zack_util.containStrInHANZI(content, "��","����","СŮ��");
		System.out.println(abc);
	}
	
	
}
