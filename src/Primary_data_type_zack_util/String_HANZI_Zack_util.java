package Primary_data_type_zack_util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class String_HANZI_Zack_util {

	
	/**
	 * 判断字符串是否含有中文
	 * 判断是否含有中文。   输入字符串返回是否含有中文。
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
	 * 给定一个字符串，返回里面所有属于中文字的内容,当然数字也包括。
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
	 * 给定一段话，和某几个汉子，判断这段话里面是否包含这些汉子中的一个。  这里接受的是变长参数。 
	 * 例如   content= 他是一个超级大好人你知道吗。      sampleHANZI=好人， 超级，漂亮。
	 * 这时候应该返回true， 因为包含好人，超级
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
