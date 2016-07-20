package normalUse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;


/**
 * @author Zack
 * for:  this util is made by Zack, anyone could use this file as long as make the reference
 *    Email:hanzack@163.com
 *
 */
/**
 * @author Miao
 * 
 * 目录：
 * 1. 返回倒的集合-----reverseList(ArrayList<String> list)  
 * 2. 返回倒的字符串-------reverseString(String oriString)
 * 3. 判断两个集合里面包含的是否一一相等。  equalListString(String... abc)
 * 4.  判断两个或者多个字符串是否完全相等      equalArrayString(String... abc)
 * 5. 给定两个字符串，找出共有的连续相等的最大部分。如果有多个最大的就都加入list里面去。 getLongPalindramic   kkabcd  kkaxck  中最大的连续相等的部分是kk
 * 6. 给定两个字符串中，找出所有公共部分，依次找到共有的加入list中去。
 * 
 *
 */
public class Copy_3_of_input_Util {
	
	
	
	
	@Test
	public void test123(){
		System.out.println(getLongPalindramic("xxcabcdkfjexx", "ccabcdfkjdexx"));
		System.out.println(getLongPalindramic("xpabayy", "cabakoy"));
	}
	
	/**
	 * 给定两个字符串，获取其中最长的共有子字符串。  给定   kxabcdkl   与kcabcd的最长部分是abcd
	 * 从字符串中获取    其中最长子字符串。
	 * 
	 */
	public static String getLongPalindramic(String abcd,String dcba){
//		String dcba=reverseString(abcd);
		String shortString="";
		String longString="";
		if (abcd.length()>dcba.length()) {
			shortString=dcba;
			longString=abcd;
		}else{
			shortString=abcd;
			longString=dcba;
		}
		
		ArrayList<Character> maxChar=new ArrayList<>(); 
		ArrayList<Character> tempChar=new ArrayList<>(); 
		for(int k=0;k<shortString.length();k++){
			tempChar=new ArrayList<>(); 
			if (shortString.charAt(k)==longString.charAt(k)) {//判断两个字符串的第k个字母是否一样， 如果一样，就进入下一层循环。如果不一样就继续判断下一个字母
//				tempChar.add(abcd.charAt(k));
				for(int j=k;j<shortString.length();j++){//当上一层一个字母一样了， 就循环剩下的字母是否会相等。连续字母
					if(shortString.charAt(j)==longString.charAt(j)){
						tempChar.add(shortString.charAt(j));
						k=j;
					}else{
						break;
					}
				}
				if(tempChar.size()>maxChar.size()){//如果临时大于最大， 则赋值
					maxChar=new ArrayList<>();
					for (int i = 0; i < tempChar.size(); i++) {
						maxChar.add(tempChar.get(i));
					}
				}
			}
			
		}
		String max="";
		for (Character abc:maxChar) {
			max=max+(abc+"");
		}
		return max;
	}
	
	
	
	/**
	 * 判断多个list包含的东西是否一样
	 */
	public static boolean equalListString(ArrayList<String>...arrayLists){
		//首先判断集合长度是否一样
		int length=arrayLists[0].size();
		for (ArrayList<String> list:arrayLists) {
			if (list.size()!=length) {
				return false;
			}
		}
		for(int k=0;k<length;k++){
			String current=arrayLists[0].get(k);
			for (ArrayList<String> temp:arrayLists) {
				if (!temp.get(k).equals(current)) {
					return false;
				}
			}
		}
		return true;
		}
	
	
	//倒存字符串-----------------------------
	//接受一个集合返回一个倒着存的数组。 例如    a,b,c,d   返回   d,c,b,a
	/**
	 * @param list
	 * @return    倒存集合
	 */
	public static ArrayList<String> reverseList(ArrayList<String> list){
	    ArrayList<String> newList=new ArrayList<String>();
	    for (int i = list.size()-1; i >=0; i--) {
			newList.add(list.get(i));
		}
     		return newList;
	}
	
	
	
	/**
	 * @return
	 * 传入一个字符串，返回倒过来的字符串。         abcd   返回      dcba
	 * 倒叙数组，倒叙字符串。     
	 */
	public static String reverseString(String oriString){
		char[] abc=oriString.toCharArray();
		char[] cba=new char[abc.length];
		int count=0;
		for (int i = abc.length-1; i>=0; i--) {
			cba[count++]=abc[i];
		}
		String cbaString=new String(cba);
		return cbaString;
	}
	
	
	
	
	
		/**
		 * 定义一个变长参数
		 * @param list
		 * @return
		 */
//		public static boolean ListContainSame(ArrayList<Integer>...list){
//			//上来先判定集合的长度是否都相等
////			int length=list[0].size();    
////			for (Integer abc: list[0]) {
////				for(ArrayList<Integer> listEach:list){
//////					Object a=abc.remove(1);	
////				}	
////				
////			}
////			
////			return false;
//		}
	
	
	
		/**
		 *   获取随机数 的list
		 * 
		 */
		//	num: how many random numbers you want
		 //below: all number should be below "below"
		 // above 是说大于几
		 // 例如    10， 100，  0  就是随机获取10个小于100大于0的不重复随机数。
		 public static ArrayList<Integer> getRandomNumber(int num,int below, int above){
		  ArrayList<Integer> norepeat=new ArrayList<>();
		  while(true){
		   int number =new Random().nextInt(below)+above;
		   if (norepeat.contains(number)) {
		   continue;
		    }else{
		     norepeat.add(number);
		    }
		   if(norepeat.size()>=num){
		    break;
		   }
		  }
		  return norepeat;
		 }
			
			
			
			
			
	
	
	 /**
	 * @param s
	 * @param t    数t在s中出现的次数。
	 * @return
	 */
	public static int countTinS(String s, String t) {
	    	int result=0;
	    	for (int j = 0; j < t.length(); j++) {
	    		
	    		if(j+s.length()<=t.length()){
				if (t.substring(j, j+s.length()).equals(s)) {
					result++;
				}
	    		}
			}
	    	
	    	return result;
	    }
	/**
	 * 给一个字符串按照输入的 符号进行分组。 优点是把括号里的内容当成一个整体。
	 * 例如:        max(1,2,3),5,6,7  这个字符串按照  "," 分组， 则会分成 max(1,2,3)     5    6     7  这个四个组。
	 * @param formulation
	 * @param splitSymbol
	 * @return
	 */
	public static List<String> groupBy(String formulation,String splitSymbol){
		List<String> subList=new ArrayList<String>();
		int countComma=0;
		formulation=formulation+" ";//给字符串加一个空格，这样可以数到最后一个的后面。因为每个逗号都是在字符后面。
		for(int i=0;i<formulation.length();i++)
		{
			if ((formulation.charAt(i)+"").equals(splitSymbol)||i==(formulation.length()-1)) {
				int bracketNum=0;//正括号+1,遇到反括号-1，  值为0的时候， 把中间的字符串加进list里面去
				for (int j=countComma;j <= i; j++) {
					if (formulation.charAt(j)=='(') {
						bracketNum++;
					}else if (formulation.charAt(j)==')') {
						bracketNum--;
					}
				}
				if (bracketNum==0) {//如果中间没有括号，或者括号是成对出现的则可以把两个逗号之间的内容当成是一个字符串加入到list里面去
					subList.add(formulation.substring(countComma, i));
					countComma=i+1;
				}
			}
		}
		return subList;
	}
	
	
	


	/** is the string a number?
	 * @param temp
	 * @return
	 */
	public static boolean isNumber(String temp){
		temp.matches("\\d+([\\.][\\d]+)?");
		return temp.matches("[\\+\\-]?\\d+([\\.][\\d]+)?");
	}
	
	
	/** 
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
	
	

	//check email------------------------------------------
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
	 
	 
	 /**remove the space
	 * @param abc
	 * @return
	 */
	public static String removeSpace(String abc) {
		String def=abc.replaceAll("\\s", "");
		 return def;
		
	}
	 
	 
	 
	 /**
	 * 
	 * true:
	 * 1**2  /
	 * 1--2   
	 * 1++2  
	 * 1/2	 
	 * 1+1
	 * 1++1.998
	 * 1+---2
	 * 1+2+-3
	 * 1+2*(-3)
	 * 1+2+-3+6
	 * 1+2+(-3)
	 * 
	 * 
	 * 
	 * false
	 * 1+*2
	 * 1-*3
	 * 1/*2
	 * 1*  /2
	 * (1--2
	 * ()
	 * 3(-9)
	 * -9+3.89)(
	 * (1+2+3(3+4))
	
	 * 
	 *：=+--+++(987.6*---++6.0-(-6.78)-3)*---++--+(2**3)+(33)+67  true
	 *     =+--+++(987.6*---++6.0-(-)-3)*---++--+(2**3)+(33)+67    false
	 *    =(3+2)**(6+7)/6/907+((-78)/32)+(--(-78)/32)           true
	 */
	 
	 
	public static boolean isFomulation(String formulation) {
		
		
		// check whether the first is "="
		String reg="^=([\\d\\s\\*\\+\\-\\/()\\[\\]\\.\\^]*(pi|PI|Pi|pI|e|E)*)*";
		Pattern thisPattern = Pattern.compile(reg);  
        Matcher thisMatcher = thisPattern.matcher(formulation);  
        if (!thisMatcher.matches()) {
        	// here is for with equal
			return false;
		}else{
			// here is for with an equal
			// to find out whether with or without an bracket [ or ] or ( or )
			formulation=formulation.replaceAll("^=", "");
			
			
			if (formulation.matches("[\\d\\s\\*\\+\\-\\/\\.\\^]*(pi|PI|Pi|pI|e|E)*")) {
				// without (  )
//				String regSin="[\\+\\-]*[\\d]+(\\.\\d+)?((([\\*]{0,2}|[\\/])?([\\+\\-])*)(\\d+)(\\.\\d+)?)*";//第一个可以是+-号允许出现多次或者零次
				if (testReg(formulation)) {
					return true;
				}else{return false;}
				
				
			}else{
				System.out.println("lallalalla");
				//with (  )
				int countX=0,countY=0;
				for (int i = 0; i < formulation.length(); i++) {
					char thisone=formulation.charAt(i);
					if (thisone==')') {
						countY++;
						
						if(i<2){
							return false;
						}else{
							if (!testBrackets(i, formulation)) {
								return false;
							}
							if (i+1<formulation.length()) {
								char nextOne=formulation.charAt(i+1);
								if((nextOne+"").matches("[\\d\\w]")){
									return false;
								}
							}
							char prevone=formulation.charAt(i-1);
							if(!(prevone+"").matches("[\\d\\w\\)]")){  
								return false;
							}
						}
					}else{
						if (thisone=='(') {
							countX++;
							if (i>=1) {
								char prevone=formulation.charAt(i-1);
								if ((prevone+"").matches("\\)")||(prevone+"").matches("[\\d\\w]")) {
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
	 
	
	
	
	


	 
	//   userName
	public static boolean isUserName(String username) {
		return username.matches("[a-zA-z][a-zA-Z0-9_]{2,15}$");
	}
	 
	 
	// whether it is a number or not  return not number
	public static boolean notNumber(String testUtil) {
		return testUtil.matches("\\D{1,50}");
	}
	 
	
	 
	//返回1   0 互换 但是不改变第一位的值  用于反码
	
	/**
	 * @param abc
	 * @return  conver 1 and 0 in binary files
	 */
	public static String zeroOneInverseFirst(String abc) {
		   char kk=abc.charAt(0);
		 String defString=abc.replaceAll("0", "2");
		 String defString1=defString.replaceAll("1", "0");
		 String defString2=defString1.replaceAll("2", "1");
		 String defString3=defString2.replaceAll("^[01]", (kk+""));
		 
		return defString3;
	}




	//返回1   0 互换
	 public static String zeroOneInverse(String abc) {
		 String defString=abc.replaceAll("0", "2");
		 String defString1=defString.replaceAll("1", "0");
		 String defString2=defString1.replaceAll("2", "1");
		 
		return defString2;
	}
	 
	 
	 /**  数字符串a在字符串b中出现的次数
	  * 
	  * 例如    ww    wwww    出现三次
	  *     cwcw    cwcwcw  出现两次
	 * @param s
	 * @param t
	 * @return
	 */
	public static int count1(String s, String t) {
	    	int result=0;
	    	for (int j = 0; j < t.length(); j++) {
	    		
	    		if(j+s.length()<=t.length()){
				if (t.substring(j, j+s.length()).equals(s)) {
					result++;
				}
	    		}
			}
	    	
	    	return result;
	    }
	 
	
	
	public static boolean testBrackets1(int index, String testString) {
//		System.out.println("11111111111111");
		int countX=0, countY=0;
		boolean temp=false;
		for (int i = 0; i < index; i++) {
			if (testString.charAt(i)=='(') {
				countX++;
			}else if (testString.charAt(i)==')') {
				countY++;
			}
		}
		if(countX>=countY){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @return  here is to judge whether the number is larger or eaual to ) before it occurs
	 *     sum(( )    when we have  ")"  the number of "("should be larger or equal the ")"
	 *     
	 *     besides, it also check the number of "("  and the number of ")" are equal
	 *      
	 */
	public static boolean testBrackets(int index, String testString) {
//		System.out.println("11111111111111");
		int countX=0, countY=0;
		boolean temp=false;
		for (int i = 0; i < index; i++) {
			if (testString.charAt(i)=='(') {
				countX++;
			}else if (testString.charAt(i)==')') {
				countY++;
			}
		}
		if(countX>=countY){
			temp=true;
		}else{
			return false;
		}
		
		int bracketX=0,bracketY=0;
		for (int i = 0; i < testString.length(); i++) {
			if ((testString.charAt(i)+"").equals("(")) {
				bracketX++;
			}else if ((testString.charAt(i)+"").equals(")")) {
				bracketY++;
			}
			
		}
//		System.out.println(bracketX+" "+bracketY);
		if(bracketX==bracketY){
			temp=true;
		}else{
//			System.out.println("22222222222222");
			return false;
			
		}
		return temp;    //if the number of ( is less than the number of ) , so it means, it is invalid.
	}
	
	
	/**
	 * @param testString
	 * @return
	 */
	public static boolean testReg(String testString) {
//		String regSin="[\\+\\-]*[\\d]+(\\.\\d+)?((([\\*]{0,2}|[\\/])?([\\+\\-])*)(\\d+)(\\.\\d+)?)*";
//		String regSin="[\\+\\-]*[\\d]+(\\.\\d+)?((([\\*]{0,2}|[\\/])?|[\\^]?([\\+\\-])*)(\\d+)(\\.\\d+)?)*";
//		String regSin="[\\+\\-]*((pi|PI|Pi|pI|e|E)|([\\d]+(\\.\\d+)?))(((([\\*]{0,2}|[\\/])?|[\\^]?)([\\+\\-])*)(((\\d+)(\\.\\d+)?)|(pi|PI|Pi|pI|e|E)))*";
		String regSin="[\\+\\-]*((pi|PI|Pi|pI|e|E|([a-zA-Z][1-5][\\d]?))|([\\d]+(\\.\\d+)?))(((([\\*]{0,2}|[\\/])?|[\\^]?)([\\+\\-])*)(((\\d+)(\\.\\d+)?)|(pi|PI|Pi|pI|e|E|([a-zA-Z][1-5][\\d]?))))*";
		
//		System.out.println("kkkkkk");
//		System.out.println(testString.matches(regSin)+"=:kkkkkkk");
		boolean flag=false;
		
		if (testString.matches(regSin)){
			flag=testPiA1(testString);
//			System.out.println(flag);
		}else{
			flag=false;
		}
		
		return flag;
	}
	
	
	
	/**
	 * @param finalString
	 * @return
	 * in front of Pi or A1, it should not be letter or digit
	 */
	public static boolean testPiA1(String finalString) {
		finalString=finalString.toLowerCase();
		for (int i = 0; i < finalString.length(); i++) {
			if((finalString.charAt(i)+"").matches("[a-zA-Z]")){
				if (i>0) {
//					System.out.println(finalString.charAt(i-1));
					if ((finalString.charAt(i-1)+"").matches("\\w")) {
//						System.out.println(finalString.substring(i-1,i+1));
						if(finalString.substring(i-1,i+1).equals("pi")){
							continue;
						}
						else {
							return false;
							}
					}
				}
			}
		}
		
		return true;
	}
	
	
	public static String removeBrackets(String abcTest){
		String finalString="";
		finalString=abcTest.replaceAll("[\\(\\)]", "");
		
	return finalString;	
	}
	
	
	public static boolean checkFunctionString(String formulation) {

//		the string should be start with "="
//		like =sum(A1,B1,C1)
//		like =avg(A1,B1,C1)
//		like =median(A1,B1,C1)
//		like =median(A1,B1,C1)
		boolean flag=false;
		formulation=removeSpace(formulation).toLowerCase();
		if(!(formulation.matches(".*\\)$"))){
			
			return false;
		}
		String reg="^=((sum\\()|(avg\\()|(median\\()|(max\\()|(min\\()|(count\\()|((pi)*(e)*)|((\\-)*(\\+)*\\d(\\.\\d+)?)|(\\-*\\+*\\*?\\/?\\^?)|([a-zA-Z](([\\d])|([1-5][\\d])))|(\\:)|(\\()|(\\,)|(\\)))+";
//		String reg="^=(sum\\( )+";
//		String reg="^=((sum\\()|(\\d)|(\\)))+";
		reg=reg+"";
		Pattern thisPattern = Pattern.compile(reg);  
        Matcher thisMatcher = thisPattern.matcher(formulation);
        for (int i = 0; i < formulation.length(); i++) {
        	if ((formulation.charAt(i)+"").equals(")")) {
        		if (testBrackets(i,formulation)) {
        			flag=true;
        		}else{          		
        			
        			return false;
        		}
			}
		}
        System.out.println("matcher:"+thisMatcher.matches());
        System.out.println(flag);
        System.out.println(checkBracket_Fun(formulation));
        if(thisMatcher.matches()&&flag&&checkBracket_Fun(formulation)){
        	return true;
        }else{
        	
        	return false;
        }
	}
	@Test
	public void test(){
		assertFalse(checkFunctionString( "=count(max(A11,),A13)") );
		assertTrue(checkFunctionString( "=count(max(A11,A12),A13)") );
		assertFalse(checkFunctionString( "=count(max(A11),count(),A13)") );
		assertFalse(checkFunctionString( "=count(max(A11),count(),A13)") );
		assertFalse(checkFunctionString( "count(max(A11),count(),A13)") );
		assertTrue(checkFunctionString( "=count(max(A11),count(A3),A3)") );
		
		assertTrue(checkFunctionString( "=count(max(A11),count(A3,Z50,z1),A3)") );
		assertFalse(checkFunctionString( "=count(max(A11),count(A3,Z50,z1)(,A3)") );
		assertTrue(checkFunctionString( "=count(max(A11),count(A3,Z50,z1),A2,A3)") );
		assertTrue(checkFunctionString( "=count(max(A11),count(A3,Z40,z1),(A2,A3))") );
		assertTrue(checkFunctionString( "=count(max(A11),(A2,A3))") );
		assertTrue(checkFunctionString( "=count(max(A11),count(A3,Z50,z1),A2,A3)") );
		assertTrue(checkFunctionString( "=count(max(A11),count(A3,Z50,z1,median(C27,D19)),A2,A3)") );
		assertTrue(checkFunctionString( "=Max(a2,a6)") );
		
		assertTrue(checkFunctionString( "=Max(-2.7876,-6.331)") );
		assertTrue(checkFunctionString( "=Max(-2.7876,-6.331,A3,(A3,-2.3))") );
		assertTrue(checkFunctionString( "=Max(-2.7876,-6.331,A3,(A3-2.3))") );
		assertTrue(checkFunctionString( "=Max(-2.7876,-6.331,A3,(A3*2.3))") );
		assertTrue(checkFunctionString( "=Max(-2.7876,-6.331,A3,(A3*---2.3))") );
		assertFalse(checkFunctionString( "=Max(-2.7876,-6.331,A3,(A3***-2.3))") );
		assertFalse(checkFunctionString( "=Max(-2.7876,-6.331,A3,(A3*-()--2.3))") );
		assertFalse(checkFunctionString( "=Max(-2.7876,A,(A3*-()--2.3))") );
		assertTrue(checkFunctionString( "=Max(-2.7876,-2.3+A1)") );
		assertTrue(checkFunctionString( "=Max(-2.7876,-2.3*-7)") );
		assertTrue(checkFunctionString( "=Max(-2.7876,-2.3^7)") );
		assertTrue(checkFunctionString( "=Max(-2.7876,-2.3^-7)") );
		assertTrue(checkFunctionString( "=Max(a1,a2)") );
//		goTest();
	}
	
	
	/**
	 * @param formulation
	 * to check  "=sum()"
	 */
	public static boolean checkBracket_Fun(String formulation) {
		
		formulation=removeSpace(formulation).toLowerCase();
		//1. 3 letters after = should be letters not digit.
//		//1. 在等号后面必须是至少有3个字母。
//		if(!formulation.substring(0, 4).matches("^=[a-zA-Z]{3}")){
////			System.out.println("AAAAAA");
//			return false;
//		}
		//1.  表达式里面包含count 之类的字符串
		System.out.println(formulation);
//		if(!(formulation.matches("^=\\.*(count\\(|max\\(|avg\\(|min\\(|sum\\()\\.*"))){
		if(!(formulation.matches("^=.*(count\\(|max\\(|avg\\(|min\\(|sum\\(|median\\().*"))){
			return false;
		}
		
		//2. 从字符串中删除 sum, count, min max 等字符，
		formulation=formulation.replaceAll("((sum)|(count)|(min)|(max)|(median)|(avg))*", "");
		//3. 任何一个数字后面只能是    数字，或者")"  或者是  ","  或者是冒号 ":" 或者是.
		for (int i = 0; i < formulation.length(); i++) {
			if ((formulation.charAt(i)+"").matches("[\\d]")&&i<formulation.length()-1) {
				if(!((formulation.charAt(i+1)+"").matches("[\\d\\^]")||(formulation.charAt(i+1)+"").matches("[\\,]")||(formulation.charAt(i+1)+"").matches("[\\:\\.\\+\\-\\*\\/]")||(formulation.charAt(i+1)+"").matches("[\\)]")||(formulation.charAt(i+1)+"").matches("[\\.]"))){
					return false;
				}
			}
			//4. 如果任何一个( 前面必须是  "(" 或者 "," 或者是 ":", 或者是 + - 。
			if ((formulation.charAt(i)+"").matches("[\\(]")&&i>0) {
				if(!((formulation.charAt(i-1)+"").matches("[\\,]")||(formulation.charAt(i-1)+"").matches("[\\(]")||(formulation.charAt(i-1)+"").matches("[\\=]")||(formulation.charAt(i-1)+"").matches("[\\+\\-]"))){
					System.out.println("test!!!!!!!!");
					return false;
				}
			}
			//5. 如果任何一个字母   前面必须是  "(" 或者 "," 或者是 ":" 或者是+ - 
			if ((formulation.charAt(i)+"").matches("[a-zA-Z]")&&i>0) {
				if(!((formulation.charAt(i-1)+"").matches("[\\,]")||(formulation.charAt(i-1)+"").matches("[\\(]")||(formulation.charAt(i-1)+"").matches("[\\:]")||(formulation.charAt(i-1)+"").matches("[\\+\\-]"))){
					return false;
				}
			}
			//6. 任何一个冒号前面必须是   数字     后面必须是 字母  或者 + -
			if ((formulation.charAt(i)+"").matches("[\\:]")&&i>0&&i<formulation.length()-1) {
				if(!((formulation.charAt(i-1)+"").matches("[\\d]"))){
					return false;
				}
				if(!((formulation.charAt(i+1)+"").matches("[a-zA-Z]")||(formulation.charAt(i+1)+"").matches("[\\+\\-]"))){
					return false;
				}
			}
			//7. 任何一个 ","  前面  ")" 或者是 数字，    后面必须是    字母 或者 +  - 或者 (
			if ((formulation.charAt(i)+"").matches("[\\,]")&&i>0) {
				if(!((formulation.charAt(i-1)+"").matches("[\\d]")||(formulation.charAt(i-1)+"").matches("[\\)]"))){
					return false;
				}
				if(!((formulation.charAt(i+1)+"").matches("[a-zA-Z]")||(formulation.charAt(i+1)+"").matches("[\\(]")||(formulation.charAt(i+1)+"").matches("[\\+\\-]"))){
//					System.out.println(formulation); //"=count(max(A11),count())")
					return false;
				}
			}
			//7.5 在 ) 前面只能 数字， 或者 )    
			if ((formulation.charAt(i)+"").matches("\\)")&&i>0) {
				if(!((formulation.charAt(i-1)+"").matches("[\\)]")||(formulation.charAt(i-1)+"").matches("[\\d]"))){
					return false;
				}
			}
			
			
			//7.6 在 * 或者  / 或者^  前面不能是 + -， 后面不能是*  /  ^
			if ((formulation.charAt(i)+"").matches("[\\*\\/\\^]")&&i>0&&i<formulation.length()-1) {
				if(((formulation.charAt(i-1)+"").matches("[\\+\\-\\,\\:]"))){
					return false;
				}
				if ((formulation.charAt(i+1)+"").matches("[\\*\\/\\^]")) {
					return false;
				}
			}
			
			
			
			
		}
//          8. the number of "="  must be 1		
		  Pattern p1=Pattern.compile("=");
		  Matcher m2=p1.matcher(formulation);
		  int num=0;
		  while (m2.find()) {
			  
			num++;
		  }
         if (num>1) {
        	 return false;
		}	
//         9.the number of ":" should be 1
          Pattern b1=Pattern.compile(":"); 
		  Matcher m1=b1.matcher(formulation);
		  int num1=0;
		  while (m1.find()) {
			num1++;
		  }
        if (num1>1) {
       	 return false;
		}	
        
		return true;
	}
	
	
	
	
	
	
	
	
	
	
}

























