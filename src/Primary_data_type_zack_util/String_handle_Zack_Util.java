package Primary_data_type_zack_util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class String_handle_Zack_Util {
	
	public static void main(String[] args) {
		System.out.println("1111111111");
	}
	
	
	
	/**
	 * 把unicode 翻译成字符。  传入一个字符串， 把unicode翻译成字符串， 返回。  例如传入u5927\u5bb6\u807d\u97f3\u6a02\u7684  返回对应的中文字
	 * @param str
	 * @return
	 */
	public static String unicodeToString(String str) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))"); 
		Matcher matcher = pattern.matcher(str);
		char ch;
		while (matcher.find()) {
		ch = (char) Integer.parseInt(matcher.group(2), 16);
		str = str.replace(matcher.group(1), ch + ""); 
		}
		return str;
		}
	
	
	
	
	
	/**
	 *   判断是否是回文数，一个字符串， 判断是否回文数          "abcdefgfedcba"返回true。
	 */
	public static boolean checkPalindramic(String theString){
		String reverseString=reverseString(theString);
		if (reverseString.equals(theString)) {
			return true;
		}else {
			return false;
		}
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
	 * 给定两个字符串，获取其中最长的共有子字符串。  给定   kxabcdkl   与kcabcd的最长部分是abcd   注意本方法必须是一一对应的位置才匹配
	 * 所以再写一个方法  来            kcab   与xycab的共有应该是cab
	 * 注意这个方法不是返回最长的字符串。而是返回那些具有一定长度的字符串 的集合。 。 
	 * 
	 */
	public static ArrayList<String> getCommonString1(String abcd,String dcba){
//		String dcba=reverseString(abcd);
		String shortString="";
		String longString="";
		int maxLength=0;
		if (abcd.length()>dcba.length()) {
			shortString=dcba;
			longString=abcd;
		}else{
			shortString=abcd;
			longString=dcba;
		}
		ArrayList<String> allStrings=new ArrayList<>();
		ArrayList<Character> maxChar=new ArrayList<>(); 
//		ArrayList<Character> tempChar=new ArrayList<>(); 
		for (int i = 0; i < shortString.length(); i++) {//从第一个字母开始与另一个字符串每一个字母比对。
			for(int k=0;k<longString.length();k++){
				if(shortString.charAt(i)==longString.charAt(k)){
					int count=i;
					ArrayList<Character> tempChar=new ArrayList<>(); 
					for (int j = k; j < longString.length(); j++) {   //依次判断每一个字母是否相等， 相等就加入到list中去。 不相等就退出循环。
						if(shortString.charAt(count)==longString.charAt(j)){
							tempChar.add(longString.charAt(j));
						}else{
							k=j;
							break;
						}
						if (count<shortString.length()-1) {
							count=count+1;
							}
					}
					
//					System.out.println(tempChar.size()+"---size");
					//-------------------从这里开始处理临时list， 需要做的是转换成String 并且存储进String list中去----------------------
					if (tempChar.size()>=maxLength) {   //这里是判断需要新加的字符串是否比现有的最长的还长， 如果是向里面加入。 否则不加入
						String theone="";
						for(Character temp:tempChar){
							theone=theone+temp+"";
						}
						allStrings.add(theone);
						maxLength=maxStringLength(allStrings);
					}
					
				}
			}
		}
		
		return allStrings;
		}
	
	
	  /**
		 * @return   返回一个list中最长的字符串的长度。   
		 */
		public static int maxStringLength(ArrayList<String> list){
			int maxLength=0;
			for (String each:list) {
				if (each.length()>maxLength) {
					maxLength=each.length();
				}
			}  
			return maxLength;
			
		  }
	
		
		/**
		 * 给定两个字符串，获取其中最长的共有子字符串。
		 * 如果是有多个最长， 则全部加入到list中进行返回    getCommonLongestString1("xabcd", "yxabcyabcd");
		 * "xabcd", "yxabcyabcd"  这两个字符串会返回    xabc   与abcd的list
		 * 
		 */
		public static ArrayList<String> getCommonLongestString1(String abcd,String dcba){
//			String dcba=reverseString(abcd);
			String shortString="";
			String longString="";
			int maxLength=0;
			if (abcd.length()>dcba.length()) {
				shortString=dcba;
				longString=abcd;
			}else{
				shortString=abcd;
				longString=dcba;
			}
			String maxStringh1="";
			ArrayList<String> allStrings=new ArrayList<>();
			ArrayList<Character> maxChar=new ArrayList<>(); 
//			ArrayList<Character> tempChar=new ArrayList<>(); 
			for (int i = 0; i < shortString.length(); i++) {//从第一个字母开始与另一个字符串每一个字母比对。
				for(int k=0;k<longString.length();k++){
					if(shortString.charAt(i)==longString.charAt(k)){
						int count=i;
						ArrayList<Character> tempChar=new ArrayList<>(); 
						for (int j = k; j < longString.length(); j++) {   //依次判断每一个字母是否相等， 相等就加入到list中去。 不相等就退出循环。
							if(shortString.charAt(count)==longString.charAt(j)){
								tempChar.add(longString.charAt(j));
							}else{
								k=j;
								break;
							}
							if (count<shortString.length()-1) {
								count=count+1;
								}
						}
							String theone="";
						for(Character temp:tempChar){
							theone=theone+temp+"";
							}
						if (theone.length()>maxStringh1.length()) {
							maxStringh1=theone;
							allStrings=new ArrayList<>();
							allStrings.add(maxStringh1);
						}else if (theone.length()==maxStringh1.length()) {
							allStrings.add(theone);
						}
						
					}
				}
			}
			
			return allStrings;
			}
		
		/**
		 * 给定两个字符串，获取其中最长的共有子字符串。  给定   kxabcdkl   与kcabcd的最长部分是abcd   注意本方法必须是一一对应的位置才匹配
		 * 所以再写一个方法  来            kcab   与xycab的共有应该是cab
		 * 从字符串中获取    其中最长子字符串。
		 * 
		 */
		public static String getCommonString2(String abcd,String dcba){
//			String dcba=reverseString(abcd);
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
//					tempChar.add(abcd.charAt(k));
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
		 * @param s   =  ttat
		 * @param t    数t在ttat中出现的次数。
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
		
		
		/** 判断字符串是否为空
		 * to check whether the string is empty or not
		 * @param stringabc
		 * @return
		 */
		public static boolean isEmpty(String stringabc) {
			
			if (null==stringabc||"".equals(stringabc.trim())) {
				return true;
				
			}
			return false;
			
//			if (stringabc.trim().equals("")||stringabc==null) {
//				return true;
//				
//			}
//			return false;
		}
		
		
		/**
		 * 把字符串由null变成empty 
		 * when the string is null, turn this string into empty
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
		 * 重写的split方法， 但是保留拆分符号。  例如。   abc。def  按照 句号： 。进行拆分， 则分成  abc。 与def  这里保留了句号
		 * @param content
		 * @param regex
		 * @return
		 */
		public static String[] splitReserveMark(String content, String regex){
//			String []arr=null;
			Pattern pattern=Pattern.compile(regex);
			Matcher matcher=pattern.matcher(content);
			String []tempArr=content.split(regex);
			int count=0;
			while (matcher.find()) {
	            tempArr[count]=tempArr[count]+matcher.group();			
				count++;
			}
			return tempArr;
		}
		
		
		
		
		
		
		
		
//		
//		/**   传入一个正则表达式, 传入文章, 找到文章里符合正则表达式的第一个内容.
//		 * @param regexString
//		 * @param sourceStr
//		 * @return
//		 */
//		public static String getContentByRegex(String regexString, String sourceStr){
////			System.err.println("--------"+regexString);
////			System.out.println("---------------------------------------------------");
////			System.err.println(sourceStr);
//			
//			String result="";
//			if (isEmpty(regexString)||isEmpty(sourceStr)) {
//				return result;
//			}
//			Pattern pattern=Pattern.compile(regexString);
//			Matcher matcher=pattern.matcher(sourceStr);
//			if (matcher.find()) {
//				result=matcher.group();
//			}
//			return result;
//		}
//		
//		
//		/**是否是一个字母
//		 * @param temp
//		 * @return
//		 */
//		public static boolean isLetter(String temp){
//			temp.matches("\\d+([\\.][\\d]+)?");
//			return temp.matches("[a-z]");
//		}
//
//		/** is the string a number?
//		 * @param temp
//		 * @return
//		 */
//		public static boolean isNumber(String temp){
//			temp.matches("\\d+([\\.][\\d]+)?");
//			return temp.matches("[\\+\\-]?\\d+([\\.][\\d]+)?");
//		}
//		
//		
//		 
//		/**  判断是否是用户名
//		 * @param username
//		 * @return
//		 */
//		public static boolean isUserName(String username) {
//			return username.matches("[a-zA-z][a-zA-Z0-9_]{2,15}$");
//		}
		
		
		
		
}











