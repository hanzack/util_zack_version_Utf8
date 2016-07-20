package testUtil;

import md5_Zack_util.MD5Utils;

import org.junit.Test;

public class test_Md5 {

	
	@Test
	public void test(){
		String wordMd5=new MD5Utils().getMD5ofStr("÷–º‰»À");
		System.out.println(wordMd5);
	}
	
}
