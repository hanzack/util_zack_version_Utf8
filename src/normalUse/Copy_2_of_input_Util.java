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

	//  判断输入的字符串是否是一个数字
	/** is the string a number?
	 * @param temp
	 * @return
	 */
	public static boolean isNumber(String temp){
		return temp.matches("\\d+");
	}
	
	
	
	/** 判断字符串是否为空
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
	 * 如果你传入了  a=null    和 k      那么返回k
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
	
	
	
	
	
	

	//判断企业级邮箱------------------------------------------
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
	//判断邮箱的准确性。
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
	 
	 
	 /**去掉字符串中的所有空格
	 * @param abc
	 * @return
	 */
	public static String removeSpace(String abc) {
		String def=abc.replaceAll("\\s", "");
		 return def;
		
	}
	 
	 
	 
	 /**
	 * 判断是否是一个数字公式
	 * true:
	 * 1**2  //乘号仅限两个	
	 * 1--2   //减号无限
	 * 1++2   //加号无限
	 * 1/2	  //除号仅限一个。
	 * 1+1
	 * 1++1
	 * 1+---2
	 * 1+2+-3
	 * 1+2*(-3)当乘法
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
	 * 原则：
	 * 如果第一个符号是=
	 *    则只允许输入 +-* / 加上数字 和小数点
	 * 
	 * 
	 * 1.括号的个数必须是          正括号=反括号
	 * 2. *前面不能是/
	 * 3. 数字前面允许无数+-号
	 * 4. 允许两个数字中间放两个乘号 **
	 * 5. 允许1/-2
	 * 6. 括号的前面不能是括号 ()
	 * 7. 不能三个以上的*
	 * 8.  不能两个以上的 /
	 * 9.
	 * 10.   所有符号前面都不能是/
	 * 11. 正括号前面不能是数字 3(-2)   
	 * 12. 反括号后面不能是数字
	 * 13. /后面不能是0
	 * 14. 小数点的前后必须是个数字。
	 * 15. 任意两个符号之间如果是数字则最多只允许有一个小数点。
	 * 
	 * 例子：=+--+++(987.6*---++6.0-(-6.78)-3)*---++--+(2**3)+(33)+67  返回true
	 *     =+--+++(987.6*---++6.0-(-)-3)*---++--+(2**3)+(33)+67    返回false
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
				//+-允许出现0-多次，    然后后面数字出现1-多次， 然后可以接小数点，点后面接数字1-多次，这种组合必须出现0-1次，然后()里面接的是符号， 其中
				// 乘号允许出现0-2次， 除号只允许出现一次， 或者+-号允许出现很多次。     然后后面再接一个小数。		
				//允许的输入++-27.8981**+---12/23*-121.567
				//   ++-232.2-*+3.5 这个是错误的
				// 这个不允许输入空格
				String regSin="[\\+\\-]*[\\d]+(\\.\\d+)?((([\\*]{0,2}|[\\/])?([\\+\\-])*)(\\d+)(\\.\\d+)?)*";//第一个可以是+-号允许出现多次或者零次
				if (testReg(formulation)) {
					return true;
				}else{return false;}
				
				
			}else{
				//with (  )
				//先数正反括号的次数是否对等。
				//   反括号的前面不能是正括号，必须是一个数字或者是反括号.   且反括号的位置不能是第一个
				//  在出现反括号后要保证反括号的个数小于等于在它之前出现的正括号的个数。
				int countX=0,countY=0;
				for (int i = 0; i < formulation.length(); i++) {
					char thisone=formulation.charAt(i);
					if (thisone==')') {
						countY++;
						
						if(i<2){
							return false;
						}else{
							//这里判断)  前面是否出现的(  的个数大于等于它。 如果不是则一定是错误的表达式。
							if (!testBrackets(i, formulation)) {
								return false;
							}
							//反括号后面不能是数字:   )23
							if (i+1<formulation.length()) {
								char nextOne=formulation.charAt(i+1);
								if((nextOne+"").matches("[\\d]")){  //这里判断是否是数字或者是反括号
									return false;
								}
							}
							char prevone=formulation.charAt(i-1);
							if(!(prevone+"").matches("[\\d\\)]")){  //这里判断是否是数字或者是反括号
								return false;
							}
						}
					}else{
						if (thisone=='(') {
							//正括号前面不能是反括号  例如：     )(         
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
	 * @return  here is to judge whether the number of （   is larger or eaual to )
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
	
	
	/**判断是否是一个 不带括号的合理公式
	 * @param testString
	 * @return
	 */
	public static boolean testReg(String testString) {
		String regSin="[\\+\\-]*[\\d]+(\\.\\d+)?((([\\*]{0,2}|[\\/])?([\\+\\-])*)(\\d+)(\\.\\d+)?)*";//第一个可以是+-号允许出现多次或者零次
		return testString.matches(regSin);
	}
	
	
	public static String removeBrackets(String abcTest){
		String finalString="";
		finalString=abcTest.replaceAll("[\\(\\)]", "");
		
	return finalString;	
	}
	
	
	
	
	
	
	
	
	
	
	
	 
	 
	//合法用户名         userName
	public static boolean isUserName(String username) {
		return username.matches("[a-zA-z][a-zA-Z0-9_]{2,15}$");
	}
	 
	 
	// whether it is a number or not  return not number
	public static boolean notNumber(String testUtil) {
		return testUtil.matches("\\D{1,50}");
	}
	 
	 
	 
	 
	 
	 
	 
}

























