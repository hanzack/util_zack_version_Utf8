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
	 * ��unicode ������ַ���  ����һ���ַ����� ��unicode������ַ����� ���ء�  ���紫��u5927\u5bb6\u807d\u97f3\u6a02\u7684  ���ض�Ӧ��������
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
	 *   �ж��Ƿ��ǻ�������һ���ַ����� �ж��Ƿ������          "abcdefgfedcba"����true��
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
	 * ����һ���ַ��������ص��������ַ�����         abcd   ����      dcba
	 * �������飬�����ַ�����     
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
	 * ���������ַ�������ȡ������Ĺ������ַ�����  ����   kxabcdkl   ��kcabcd���������abcd   ע�Ȿ����������һһ��Ӧ��λ�ò�ƥ��
	 * ������дһ������  ��            kcab   ��xycab�Ĺ���Ӧ����cab
	 * ע������������Ƿ�������ַ��������Ƿ�����Щ����һ�����ȵ��ַ��� �ļ��ϡ� �� 
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
		for (int i = 0; i < shortString.length(); i++) {//�ӵ�һ����ĸ��ʼ����һ���ַ���ÿһ����ĸ�ȶԡ�
			for(int k=0;k<longString.length();k++){
				if(shortString.charAt(i)==longString.charAt(k)){
					int count=i;
					ArrayList<Character> tempChar=new ArrayList<>(); 
					for (int j = k; j < longString.length(); j++) {   //�����ж�ÿһ����ĸ�Ƿ���ȣ� ��Ⱦͼ��뵽list��ȥ�� ����Ⱦ��˳�ѭ����
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
					//-------------------�����￪ʼ������ʱlist�� ��Ҫ������ת����String ���Ҵ洢��String list��ȥ----------------------
					if (tempChar.size()>=maxLength) {   //�������ж���Ҫ�¼ӵ��ַ����Ƿ�����е���Ļ����� �������������롣 ���򲻼���
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
		 * @return   ����һ��list������ַ����ĳ��ȡ�   
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
		 * ���������ַ�������ȡ������Ĺ������ַ�����
		 * ������ж����� ��ȫ�����뵽list�н��з���    getCommonLongestString1("xabcd", "yxabcyabcd");
		 * "xabcd", "yxabcyabcd"  �������ַ����᷵��    xabc   ��abcd��list
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
			for (int i = 0; i < shortString.length(); i++) {//�ӵ�һ����ĸ��ʼ����һ���ַ���ÿһ����ĸ�ȶԡ�
				for(int k=0;k<longString.length();k++){
					if(shortString.charAt(i)==longString.charAt(k)){
						int count=i;
						ArrayList<Character> tempChar=new ArrayList<>(); 
						for (int j = k; j < longString.length(); j++) {   //�����ж�ÿһ����ĸ�Ƿ���ȣ� ��Ⱦͼ��뵽list��ȥ�� ����Ⱦ��˳�ѭ����
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
		 * ���������ַ�������ȡ������Ĺ������ַ�����  ����   kxabcdkl   ��kcabcd���������abcd   ע�Ȿ����������һһ��Ӧ��λ�ò�ƥ��
		 * ������дһ������  ��            kcab   ��xycab�Ĺ���Ӧ����cab
		 * ���ַ����л�ȡ    ��������ַ�����
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
				if (shortString.charAt(k)==longString.charAt(k)) {//�ж������ַ����ĵ�k����ĸ�Ƿ�һ���� ���һ�����ͽ�����һ��ѭ���������һ���ͼ����ж���һ����ĸ
//					tempChar.add(abcd.charAt(k));
					for(int j=k;j<shortString.length();j++){//����һ��һ����ĸһ���ˣ� ��ѭ��ʣ�µ���ĸ�Ƿ����ȡ�������ĸ
						if(shortString.charAt(j)==longString.charAt(j)){
							tempChar.add(shortString.charAt(j));
							k=j;
						}else{
							break;
						}
					}
					if(tempChar.size()>maxChar.size()){//�����ʱ������� ��ֵ
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
		 * @param t    ��t��ttat�г��ֵĴ�����
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
		 * ��һ���ַ������������ ���Ž��з��顣 �ŵ��ǰ�����������ݵ���һ�����塣
		 * ����:        max(1,2,3),5,6,7  ����ַ�������  "," ���飬 ���ֳ� max(1,2,3)     5    6     7  ����ĸ��顣
		 * @param formulation
		 * @param splitSymbol
		 * @return
		 */
		public static List<String> groupBy(String formulation,String splitSymbol){
			List<String> subList=new ArrayList<String>();
			int countComma=0;
			formulation=formulation+" ";//���ַ�����һ���ո����������������һ���ĺ��档��Ϊÿ�����Ŷ������ַ����档
			for(int i=0;i<formulation.length();i++)
			{
				if ((formulation.charAt(i)+"").equals(splitSymbol)||i==(formulation.length()-1)) {
					int bracketNum=0;//������+1,����������-1��  ֵΪ0��ʱ�� ���м���ַ����ӽ�list����ȥ
					for (int j=countComma;j <= i; j++) {
						if (formulation.charAt(j)=='(') {
							bracketNum++;
						}else if (formulation.charAt(j)==')') {
							bracketNum--;
						}
					}
					if (bracketNum==0) {//����м�û�����ţ����������ǳɶԳ��ֵ�����԰���������֮������ݵ�����һ���ַ������뵽list����ȥ
						subList.add(formulation.substring(countComma, i));
						countComma=i+1;
					}
				}
			}
			return subList;
		}
		
		
		/** �ж��ַ����Ƿ�Ϊ��
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
		 * ���ַ�����null���empty 
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
		 * ��д��split������ ���Ǳ�����ַ��š�  ���硣   abc��def  ���� ��ţ� �����в�֣� ��ֳ�  abc�� ��def  ���ﱣ���˾��
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
//		/**   ����һ��������ʽ, ��������, �ҵ����������������ʽ�ĵ�һ������.
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
//		/**�Ƿ���һ����ĸ
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
//		/**  �ж��Ƿ����û���
//		 * @param username
//		 * @return
//		 */
//		public static boolean isUserName(String username) {
//			return username.matches("[a-zA-z][a-zA-Z0-9_]{2,15}$");
//		}
		
		
		
		
}











