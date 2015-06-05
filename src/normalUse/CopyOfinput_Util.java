package normalUse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zack
 *
 */
public class CopyOfinput_Util {

	//  �ж�������ַ����Ƿ���һ������
	/** is the string a number?
	 * @param temp
	 * @return
	 */
	public static boolean isNumber(String temp){
		return temp.matches("\\d+");
	}
	
	
	
	/** �ж��ַ����Ƿ�Ϊ��
	 * to check whether the string is empty or not
	 * @param stringabc
	 * @return
	 */
	public static boolean isEmpty(String stringabc) {
		
		if (stringabc.trim().equals("")||stringabc==null) {
			return true;
			
		}
		return false;
	}
	
	
	/** when the string is null, turn this string into empty
	 * @param stringabc
	 * @return
	 */
	public String toEmptyString(String stringabc) {
		
		if (stringabc==null) {
			stringabc="";
		}
		return stringabc;
	}
	
	
	
	
	
	
	/**
	 * turn the stringabc, into a targetAbc.  
	 * ����㴫����  a=null    �� k      ��ô����k
	 * @param stringabc
	 * @param targetAbc
	 * @return
	 */
	public String toCertainString(String stringabc, String targetAbc) {
		if (stringabc==null||stringabc.trim().endsWith("")) {
			return targetAbc;
		}
		return stringabc;
	}
	
	
	
	
	
	
	
	
	

	//�ж���ҵ������------------------------------------------
	 public static String checkEmail(String theEmail) {
		   
		   String emailString=theEmail.trim();
			if(emailString!=null&&(!emailString.equals("")))
			{
				if(!validateEmail(emailString)){
					return "your email address is not validated";
				}
			}
			return "good";
	   }
	//�ж������׼ȷ�ԡ�
	 public static boolean validateEmail(String email){ 
//		 this regular expression is referenced from "http://blog.csdn.net/fatherican/article/details/8853062"
	        String thisRegex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
	        Pattern thisPattern = Pattern.compile(thisRegex);  
	        Matcher thisMatcher = thisPattern.matcher(email);  
//	        if this is not a validated email then return false else return true
	        if(thisMatcher.matches()){  
	            return true;
	        }else{  
	        	return false; 
	        }  
	    }  
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
