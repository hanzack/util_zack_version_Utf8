package normalUse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import testUtil.testUtil;

/**
 * @author Zack
 *
 */
public class Copy_2_of_input_Util {

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
	public static String toCertainString(String stringabc, String targetAbc) {
		if (stringabc==null||stringabc.trim().endsWith("")) {
			return targetAbc;
		}
		return stringabc;
	}
	
	
	
	
	
	
	public static boolean checkAtoZ(String abc){
		
		boolean flag=abc.matches("[A-Za-z]+$");
		return flag;
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
	 
	 
	 /**ȥ���ַ����е����пո�
	 * @param abc
	 * @return
	 */
	public static String removeSpace(String abc) {
		String def=abc.replaceAll("\\s", "");
		 return def;
		
	}
	 
	 
	 
	 /**
	 * �ж��Ƿ���һ�����ֹ�ʽ
	 * true:
	 * 1**2  //�˺Ž�������	
	 * 1--2   //��������
	 * 1++2   //�Ӻ�����
	 * 1/2	  //���Ž���һ����
	 * 1+1
	 * 1++1
	 * 1+---2
	 * 1+2+-3
	 * 1+2*(-3)���˷�
	 * 1+2+-3+6
	 * 1+2+(-3)
	 * (1+2+3(3+4))
	 * 
	 * 
	 * 1+*2
	 * 1-*3
	 * 1/*2
	 * 1*  /2
	 * (1--2
	 * 
	 * 
	 * ԭ��
	 * �����һ��������=
	 *    ��ֻ�������� +-* / �������� ��С����
	 * 
	 * 
	 * 1.���ŵĸ���������          ������=������
	 * 2. *ǰ�治����/
	 * 3. ����ǰ����������+-��
	 * 4. �������������м�������˺� **
	 * 5. ����1/-2
	 * 6. ���ŵ�ǰ�治�������� ()
	 * 7. �����������ϵ�*
	 * 8.  �����������ϵ� /
	 * 9.
	 * 10.   ���з���ǰ�涼������/
	 * 11. ������ǰ�治�������� 3(-2)   
	 * 12. �����ź��治��������
	 * 13. /���治����0
	 * 14. С�����ǰ������Ǹ����֡�
	 * 15. ������������֮����������������ֻ������һ��С���㡣
	 * 
	 * ���ӣ�=+--+++(987.6*---++6.0-(-6.78)-3)*---++--+(2**3)+(33)+67  ����true
	 *     =+--+++(987.6*---++6.0-(-)-3)*---++--+(2**3)+(33)+67    ����false
	 * 
	 */
	 
	 
	public static boolean isFomulation(String formulation) {
		
		
		// check whether the first is "="
		String reg="^=[\\d\\s\\*\\+\\-\\/()\\[\\]\\.]*";
		Pattern thisPattern = Pattern.compile(reg);  
        Matcher thisMatcher = thisPattern.matcher(formulation);  
        if (!thisMatcher.matches()) {
        	// here is for with equal
			return false;
		}else{
			// here is for with an equal
			// to find out whether with or without an bracket [ or ] or ( or )
			formulation=formulation.replaceAll("^=", "");
			
			
			if (formulation.matches("[\\d\\s\\*\\+\\-\\/\\.]*")) {
				// without (  )
				//+-�������0-��Σ�    Ȼ��������ֳ���1-��Σ� Ȼ����Խ�С���㣬����������1-��Σ�������ϱ������0-1�Σ�Ȼ��()����ӵ��Ƿ��ţ� ����
				// �˺��������0-2�Σ� ����ֻ�������һ�Σ� ����+-��������ֺܶ�Ρ�     Ȼ������ٽ�һ��С����		
				//���������++-27.8981**+---12/23*-121.567
				//   ++-232.2-*+3.5 ����Ǵ����
				// �������������ո�
				String regSin="[\\+\\-]*[\\d]+(\\.\\d+)?((([\\*]{0,2}|[\\/])?([\\+\\-])*)(\\d+)(\\.\\d+)?)*";//��һ��������+-��������ֶ�λ������
				if (testReg(formulation)) {
					return true;
				}else{return false;}
				
				
			}else{
				//with (  )
				//�����������ŵĴ����Ƿ�Եȡ�
				//   �����ŵ�ǰ�治���������ţ�������һ�����ֻ����Ƿ�����.   �ҷ����ŵ�λ�ò����ǵ�һ��
				//  �ڳ��ַ����ź�Ҫ��֤�����ŵĸ���С�ڵ�������֮ǰ���ֵ������ŵĸ�����
				int countX=0,countY=0;
				for (int i = 0; i < formulation.length(); i++) {
					char thisone=formulation.charAt(i);
					if (thisone==')') {
						countY++;
						
						if(i<2){
							return false;
						}else{
							//�����ж�)  ǰ���Ƿ���ֵ�(  �ĸ������ڵ������� ���������һ���Ǵ���ı��ʽ��
							if (!testBrackets(i, formulation)) {
								return false;
							}
							//�����ź��治��������:   )23
							if (i+1<formulation.length()) {
								char nextOne=formulation.charAt(i+1);
								if((nextOne+"").matches("[\\d]")){  //�����ж��Ƿ������ֻ����Ƿ�����
									return false;
								}
							}
							char prevone=formulation.charAt(i-1);
							if(!(prevone+"").matches("[\\d\\)]")){  //�����ж��Ƿ������ֻ����Ƿ�����
								return false;
							}
						}
					}else{
						if (thisone=='(') {
							//������ǰ�治���Ƿ�����  ���磺     )(         
							countX++;
							if (i>=1) {
								char prevone=formulation.charAt(i-1);
								if ((prevone+"").matches("\\)")) {
									return false;
								}
							}
							
						}
					}
					
				}// for loop ends here.
				if (!(countX==countY)) {
					return false;
				}else{
					String fin1=removeBrackets(formulation);
					if (testReg(fin1)) {
						return true;
					}else {
						return false;
					}
					
				}
				
				
			}
		}
	}
	 
	
	
	/**
	 * @return  here is to judge whether the number of ��   is larger or eaual to )
	 */
	public static boolean testBrackets(int index, String testString) {
		int countX=0, countY=0;
		for (int i = 0; i < index; i++) {
			if (testString.charAt(i)=='(') {
				countX++;
			}else if (testString.charAt(i)==')') {
				countY++;
			}
		}
		if(countX>=countY){
			return true;
		}
		return false;    //if the number of ( is less than the number of ) , so it means, it is invalid.
	}
	
	
	/**�ж��Ƿ���һ�� �������ŵĺ���ʽ
	 * @param testString
	 * @return
	 */
	public static boolean testReg(String testString) {
		String regSin="[\\+\\-]*[\\d]+(\\.\\d+)?((([\\*]{0,2}|[\\/])?([\\+\\-])*)(\\d+)(\\.\\d+)?)*";//��һ��������+-��������ֶ�λ������
		return testString.matches(regSin);
	}
	
	
	public static String removeBrackets(String abcTest){
		String finalString="";
		finalString=abcTest.replaceAll("[\\(\\)]", "");
		
	return finalString;	
	}
	
	
	
	
	
	
	
	
	
	
	
	 
	 
	//�Ϸ��û���         userName
	public static boolean isUserName(String username) {
		return username.matches("[a-zA-z][a-zA-Z0-9_]{2,15}$");
	}
	 
	 
	// whether it is a number or not  return not number
	public static boolean notNumber(String testUtil) {
		return testUtil.matches("\\D{1,50}");
	}
	 
	 
	 
	 
	 
	 
	 
}

























