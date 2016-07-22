package regex_String_File_Zack_util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Primary_data_type_zack_util.String_handle_Zack_Util;
import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class regex_All_util {
		
    
    
    
    /**
     * Given a text String, given a regex rule, to find all the text matches the rule, 
     * and then replace the original symbol by a new symbol in each of the string found.
     * 
     * e.g:  text="abcde 657.32 89.73 haha"   regString="[0-9]+[\\.][0-9]+"    originSym="."; newSym="$"
     * The  return is :   "abcde 657$32 89$73 haha"
     * 
     * @param text: the text you want to handle
     * @param regString: reg rule like: "[0-9]+[\\.][0-9]+"
     * @param originSym: originSym
     * @param newSym:    newSym
     * return: result String
     */
    public static String replaceMarkInStringAccordingReg(String text,String regString,String originSym,String newSym ){
        Pattern pattern=Pattern.compile(regString);
        Matcher matcher=pattern.matcher(text);
        while(matcher.find()){
            String originFind=matcher.group();
            String tempFind=matcher.group().replace(originSym, newSym);
            text=text.replace(originFind, tempFind);
        }
        return text;
    }
    
	
	/** ��������ʽ�ļ�ƥ���ļ�����    �ѷ��ϵ����ݼ��뵽list�в�����
	 * ����һ��������ʽ��׼����ļ��� ÿһ����һ��������ʽ�� Ȼ�������£�ƥ�����Ӧ������, �������ݷ��ء�
	 * @param regexFilePath һ��д��������ʽ������ļ�
	 * @param sourceStr   ��Ҫƥ�����������
	 * @return
	 */
	public static List<String> getContentByRegexFileReturnList(String regexFilePath, String content){
		List<String> resultList=new ArrayList<>();
		if (String_handle_Zack_Util.isEmpty(regexFilePath)||String_handle_Zack_Util.isEmpty(content)) {
//			System.out.println("11");
			return resultList;
		}
		
		File file=new File(regexFilePath);
		//ѭ����ȡ���ļ���ÿһ�е�׼��
		try {
			FileReader fReader=new FileReader(file);
			BufferedReader bf=new BufferedReader(fReader);
			String tempLine=""; //��¼ÿһ�еĹ���
			while ((tempLine=bf.readLine())!=null) {
				//��ȡ��ÿһ�еĹ�������ƪ����ȥƥ�䡣
				 Pattern pattern=Pattern.compile(tempLine);
				 Matcher matcher=pattern.matcher(content);
				 while (matcher.find()) {
					 String tempContentString=matcher.group();
//					System.out.println(tempContentString);
					 resultList.add(tempContentString);
//					 result=result+tempContentString+" ";
				}
			}
			//�رն�����
			bf.close();
			fReader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	
	
	
	
	
	
	/** ��������ʽ�ļ�ƥ���ļ�����    �ѷ��ϵ����ݼ��뵽list�в�����
	 * ����һ��������ʽ��׼����ļ��� ÿһ����һ��������ʽ�� Ȼ�������£�ƥ�����Ӧ������, �������ݷ��ء�
	 * @param regexFilePath һ��д��������ʽ������ļ�
	 * @param sourceStr   ��Ҫƥ�����������
	 * @return
	 */
	public static String getContentByRegexFileReturnStr(String regexFilePath, String content){
		String result="";
		if (String_handle_Zack_Util.isEmpty(regexFilePath)||String_handle_Zack_Util.isEmpty(content)) {
//			System.out.println("11");
			return result;
		}
		
		File file=new File(regexFilePath);
		//ѭ����ȡ���ļ���ÿһ�е�׼��
		try {
			FileReader fReader=new FileReader(file);
			BufferedReader bf=new BufferedReader(fReader);
			String tempLine=""; //��¼ÿһ�еĹ���
			while ((tempLine=bf.readLine())!=null) {
				//��ȡ��ÿһ�еĹ�������ƪ����ȥƥ�䡣
				 Pattern pattern=Pattern.compile(tempLine);
				 Matcher matcher=pattern.matcher(content);
				 while (matcher.find()) {
					 String tempContentString=matcher.group();
//					System.out.println(tempContentString);
//					 resultList.add(tempContentString);
					 result=result+tempContentString+" ";
				}
			}
			//�رն�����
			bf.close();
			fReader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** ��������ʽ�ļ�ƥ���ļ�����
	 * ����һ��������ʽ��׼����ļ��� ÿһ����һ��������ʽ�� Ȼ�������£�ƥ�����Ӧ������, ��������д��ָ���ļ���ȥ��
	 * @param regexFilePath һ��д��������ʽ������ļ�
	 * @param sourceStr   ��Ҫƥ�������
	 * @param splitMark   �ñ����Ǹ�������û������
	 * @return
	 */
	public static String getContentByRegexFileReturnStr(String regexFilePath, String sourceStr,String splitMark){
		String result="";
		if (String_handle_Zack_Util.isEmpty(regexFilePath)||String_handle_Zack_Util.isEmpty(sourceStr)) {
//			System.out.println("11");
			return result;
		}
		if (String_handle_Zack_Util.isEmpty(splitMark)) {
			splitMark="\r\n";
		}
		
		File file=new File(regexFilePath);
		//ѭ����ȡ���ļ���ÿһ�е�׼��
		try {
			FileReader fReader=new FileReader(file);
			BufferedReader bf=new BufferedReader(fReader);
			String tempLine=""; //��¼ÿһ�еĹ���
			while ((tempLine=bf.readLine())!=null) {
				//��ȡ��ÿһ�еĹ�������ƪ����ȥƥ�䡣
				 Pattern pattern=Pattern.compile(tempLine);
				 Matcher matcher=pattern.matcher(sourceStr);
				 while (matcher.find()) {
					 String tempContentString=matcher.group();
//					System.out.println(tempContentString);
					 result=result+tempContentString+" ";
				}
			}
			//�رն�����
			bf.close();
			fReader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	
	/** ��������ʽ�ļ�ƥ���ļ�����
	 * ����һ��������ʽ��׼����ļ��� ÿһ����һ��������ʽ�� Ȼ�������£�ƥ�����Ӧ������, ��������д��ָ���ļ���ȥ��
	 * @param regexFilePath һ��д��������ʽ������ļ�
	 * @param sourceStr   ��Ҫƥ�������
	 * @param splitMark   �ѽ�����շָ���Ž��зָ�� splitMark   �ѽ�����շָ���Ž��зָ��, д�뵽�ļ��С�Ĭ����ƥ�䵽һ��д���ļ���Ȼ���Ի�����Ϊ�ָ�����
	 * @param toPath       ��Ҫ��ƥ��Ľ�����뵽����ȥ    ���Բ�ָ��
	 * @param newFileName  ��Ҫ������ļ���	   ���Բ�ָ��
	 * @param fileSurfix   �ļ��ĺ�׺���� ���Բ�ָ��
	 * @return
	 */
	public static String getContentByRegexFileWrtToFile(String regexFilePath, String sourceStr,String splitMark, String toPath,String newFileName, String fileSurfix){
		String result="";
		if (String_handle_Zack_Util.isEmpty(regexFilePath)||String_handle_Zack_Util.isEmpty(sourceStr)) {
//			System.out.println("11");
			return result;
		}
		if (String_handle_Zack_Util.isEmpty(splitMark)) {
			splitMark="\r\n";
		}
		
		File file=new File(regexFilePath);
		//ѭ����ȡ���ļ���ÿһ�е�׼��
		try {
			FileReader fReader=new FileReader(file);
			BufferedReader bf=new BufferedReader(fReader);
			String tempLine=""; //��¼ÿһ�еĹ���
			while ((tempLine=bf.readLine())!=null) {
				//��ȡ��ÿһ�еĹ�������ƪ����ȥƥ�䡣
				 Pattern pattern=Pattern.compile(tempLine);
				 Matcher matcher=pattern.matcher(sourceStr);
				 while (matcher.find()) {
					 String tempContentString=matcher.group()+splitMark;
					System.out.println(tempContentString);
					ReadAndWriteFileFromDirectory.writeStringToFile(tempContentString, toPath, newFileName, fileSurfix);
				}
			}
			//�رն�����
			bf.close();
			fReader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	

	/**
	 * ����һ���ַ����� ȥ�����������ظ����ֵ��ַ��� ��������aabbcccdd�� ����abcd.
	 * ����  aabbbcccbbbddef   ���ص���abcbdef   ע�������滹��������b
	 */
	public static String removeContinueRepeatLetter(String temp){
		String abcString=temp.replaceAll("(\\w)(\\1)*","$1");
//		System.out.println();
		return abcString;
	}
	
	
	/**
	 * @param temp
	 * @return     ȥ�������ظ����ַ�����
	 */
	public static String removeAllRepeatLetter(String temp){
		String abcString="";
		
		return abcString;
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
	 
		
		/**   ����һ��������ʽ, ��������, �ҵ����������������ʽ�ĵ�һ������.
		 * @param regexString
		 * @param sourceStr
		 * @return
		 */
		public static String getContentByRegex(String regexString, String sourceStr){
//			System.err.println("--------"+regexString);
//			System.out.println("---------------------------------------------------");
//			System.err.println(sourceStr);
			
			String result="";
			if (String_handle_Zack_Util.isEmpty(regexString)||String_handle_Zack_Util.isEmpty(sourceStr)) {
				return result;
			}
			Pattern pattern=Pattern.compile(regexString);
			Matcher matcher=pattern.matcher(sourceStr);
			if (matcher.find()) {
				result=matcher.group();
			}
			return result;
		}
		
		
		/**�Ƿ���һ����ĸ
		 * @param temp
		 * @return
		 */
		public static boolean isLetter(String temp){
			temp.matches("\\d+([\\.][\\d]+)?");
			return temp.matches("[a-z]");
		}

		/** is the string a number?
		 * @param temp
		 * @return
		 */
		public static boolean isNumber(String temp){
			temp.matches("\\d+([\\.][\\d]+)?");
			return temp.matches("[\\+\\-]?\\d+([\\.][\\d]+)?");
		}
		
		
		 
		/**  �ж��Ƿ����û���
		 * @param username
		 * @return
		 */
		public static boolean isUserName(String username) {
			return username.matches("[a-zA-z][a-zA-Z0-9_]{2,15}$");
		}
	
		
		
		/**
		 * ����html�ļ����ҵ�������ϵ����� 
		 * ���紫��<link rel="dns-prefetch" href="https://s0.ssl.qhimg.com"/>
		 * @param content
		 */
		public static String getUrlFromHtmlContentsTag(String content){
			String result="";
			String abcString="";
			if (String_handle_Zack_Util.isEmpty(content)) {
				return "No input";
			}else {
				abcString=content;
			}
			//<link rel="dns-prefetch" href="https://s0.ssl.qhimg.com"/>
			//<a(.*)href\\s*=\\s*(\"([^\">]*)\"|[^\\s>])(.*)>
//			String abcString="<a class=\"play-img\" href=\"/html/DQ221848.html\" title=\"ıɱ��ˮ�껪/���ߵ㲥/Ѹ������\" target=\"_blank\"><link rel=\"dns-prefetch\" href=\"https://s0.ssl.qhimg.com\"/>";
//			String abcString="<a class=\"play-img\" href=\"/html/DQ221848.html\" title=\"ıɱ��ˮ�껪/���ߵ㲥/Ѹ������\" target=\"_blank\">";
//			String abcString="<a klsk>";
			//��ȡ���� ��������µĺ��õ�
			String linkString="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
			//���������������ģ���Ѻܶ��ǩ����һ������
//			String linkString="<a(.*)href\\s*=\\s*(\"([^\">]*)\"|[^\\s>])(.*)>";
			Pattern pattern=Pattern.compile(linkString);
			Matcher matcher=pattern.matcher(abcString);
			int count=0;
			while(matcher.find()) {
				count++;
//				System.out.println(matcher.group(3)+"-----from-----"+matcher.group());			
				result=result+matcher.group(3)+"\r\n";
			}
			System.out.println(result);
			System.out.println("======"+count);
			return result;
		}
		
		
		
}
