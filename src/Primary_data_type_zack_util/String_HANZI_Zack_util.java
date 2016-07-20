package Primary_data_type_zack_util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class String_HANZI_Zack_util {

	
	/**
	 * �ж��ַ����Ƿ�������
	 * �ж��Ƿ������ġ�   �����ַ��������Ƿ������ġ�
	 * @param str
	 * @return
	 */
	public static boolean isHanzi(String str){
	       boolean temp = false;
	       Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
	       Matcher m=p.matcher(str); 
	       if(m.find()){ 
	           temp =  true;
	       }
	       return temp;
	   }
	
	
	/**
	 * ����һ���ַ��������������������������ֵ�����,��Ȼ����Ҳ������
	 * @param content
	 * @return
	 */
	public static String getAllHANZI(String content){
		String result="";
		String regex="[\u4e00-\u9fa5]|[0-9]";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(content);
		while (matcher.find()) {
			result=result+matcher.group();
		}
		return result;
		
	}
	
	
	/**
	 * ����һ�λ�����ĳ�������ӣ��ж���λ������Ƿ������Щ�����е�һ����  ������ܵ��Ǳ䳤������ 
	 * ����   content= ����һ�������������֪����      sampleHANZI=���ˣ� ������Ư����
	 * ��ʱ��Ӧ�÷���true�� ��Ϊ�������ˣ�����
	 * @param content
	 * @param str
	 * @return
	 */
	public static boolean containStrInHANZI(String content,String... sampleHANZI){
		
		boolean contains=false;
		
		for (int i = 0; i < sampleHANZI.length; i++) {
			if (content.contains(sampleHANZI[i])) {
				contains=true;
				break;
			}
		}
		return contains;
	}
	
	
	
	
	
	
}
