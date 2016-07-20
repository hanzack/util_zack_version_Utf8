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
		
	
	/** 用正则表达式文件匹配文件内容    把符合的内容加入到list中并返回
	 * 给定一个正则表达式的准则的文件， 每一行是一个正则表达式。 然后传入文章，匹配出对应的内容, 并将内容返回。
	 * @param regexFilePath 一个写着正则表达式规则的文件
	 * @param sourceStr   需要匹配的文章内容
	 * @return
	 */
	public static List<String> getContentByRegexFileReturnList(String regexFilePath, String content){
		List<String> resultList=new ArrayList<>();
		if (String_handle_Zack_Util.isEmpty(regexFilePath)||String_handle_Zack_Util.isEmpty(content)) {
//			System.out.println("11");
			return resultList;
		}
		
		File file=new File(regexFilePath);
		//循环读取该文件的每一行的准则
		try {
			FileReader fReader=new FileReader(file);
			BufferedReader bf=new BufferedReader(fReader);
			String tempLine=""; //记录每一行的规则
			while ((tempLine=bf.readLine())!=null) {
				//获取了每一行的规则，用整篇文章去匹配。
				 Pattern pattern=Pattern.compile(tempLine);
				 Matcher matcher=pattern.matcher(content);
				 while (matcher.find()) {
					 String tempContentString=matcher.group();
//					System.out.println(tempContentString);
					 resultList.add(tempContentString);
//					 result=result+tempContentString+" ";
				}
			}
			//关闭读入流
			bf.close();
			fReader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	
	
	
	
	
	
	/** 用正则表达式文件匹配文件内容    把符合的内容加入到list中并返回
	 * 给定一个正则表达式的准则的文件， 每一行是一个正则表达式。 然后传入文章，匹配出对应的内容, 并将内容返回。
	 * @param regexFilePath 一个写着正则表达式规则的文件
	 * @param sourceStr   需要匹配的文章内容
	 * @return
	 */
	public static String getContentByRegexFileReturnStr(String regexFilePath, String content){
		String result="";
		if (String_handle_Zack_Util.isEmpty(regexFilePath)||String_handle_Zack_Util.isEmpty(content)) {
//			System.out.println("11");
			return result;
		}
		
		File file=new File(regexFilePath);
		//循环读取该文件的每一行的准则
		try {
			FileReader fReader=new FileReader(file);
			BufferedReader bf=new BufferedReader(fReader);
			String tempLine=""; //记录每一行的规则
			while ((tempLine=bf.readLine())!=null) {
				//获取了每一行的规则，用整篇文章去匹配。
				 Pattern pattern=Pattern.compile(tempLine);
				 Matcher matcher=pattern.matcher(content);
				 while (matcher.find()) {
					 String tempContentString=matcher.group();
//					System.out.println(tempContentString);
//					 resultList.add(tempContentString);
					 result=result+tempContentString+" ";
				}
			}
			//关闭读入流
			bf.close();
			fReader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** 用正则表达式文件匹配文件内容
	 * 给定一个正则表达式的准则的文件， 每一行是一个正则表达式。 然后传入文章，匹配出对应的内容, 并将内容写入指定文件中去。
	 * @param regexFilePath 一个写着正则表达式规则的文件
	 * @param sourceStr   需要匹配的文章
	 * @param splitMark   该变来那个在这里没有作用
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
		//循环读取该文件的每一行的准则
		try {
			FileReader fReader=new FileReader(file);
			BufferedReader bf=new BufferedReader(fReader);
			String tempLine=""; //记录每一行的规则
			while ((tempLine=bf.readLine())!=null) {
				//获取了每一行的规则，用整篇文章去匹配。
				 Pattern pattern=Pattern.compile(tempLine);
				 Matcher matcher=pattern.matcher(sourceStr);
				 while (matcher.find()) {
					 String tempContentString=matcher.group();
//					System.out.println(tempContentString);
					 result=result+tempContentString+" ";
				}
			}
			//关闭读入流
			bf.close();
			fReader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	
	
	/** 用正则表达式文件匹配文件内容
	 * 给定一个正则表达式的准则的文件， 每一行是一个正则表达式。 然后传入文章，匹配出对应的内容, 并将内容写入指定文件中去。
	 * @param regexFilePath 一个写着正则表达式规则的文件
	 * @param sourceStr   需要匹配的文章
	 * @param splitMark   把结果按照分割符号进行分割保存 splitMark   把结果按照分割符号进行分割保存, 写入到文件中。默认是匹配到一个写入文件，然后以换行作为分隔符号
	 * @param toPath       需要把匹配的结果存入到哪里去    可以不指定
	 * @param newFileName  需要存入的文件名	   可以不指定
	 * @param fileSurfix   文件的后缀名。 可以不指定
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
		//循环读取该文件的每一行的准则
		try {
			FileReader fReader=new FileReader(file);
			BufferedReader bf=new BufferedReader(fReader);
			String tempLine=""; //记录每一行的规则
			while ((tempLine=bf.readLine())!=null) {
				//获取了每一行的规则，用整篇文章去匹配。
				 Pattern pattern=Pattern.compile(tempLine);
				 Matcher matcher=pattern.matcher(sourceStr);
				 while (matcher.find()) {
					 String tempContentString=matcher.group()+splitMark;
					System.out.println(tempContentString);
					ReadAndWriteFileFromDirectory.writeStringToFile(tempContentString, toPath, newFileName, fileSurfix);
				}
			}
			//关闭读入流
			bf.close();
			fReader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	

	/**
	 * 输入一个字符串， 去掉里面连续重复出现的字符。 里如输入aabbcccdd， 返回abcd.
	 * 输入  aabbbcccbbbddef   返回的是abcbdef   注意这里面还是有两个b
	 */
	public static String removeContinueRepeatLetter(String temp){
		String abcString=temp.replaceAll("(\\w)(\\1)*","$1");
//		System.out.println();
		return abcString;
	}
	
	
	/**
	 * @param temp
	 * @return     去掉所有重复的字符串。
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
	 
		
		/**   传入一个正则表达式, 传入文章, 找到文章里符合正则表达式的第一个内容.
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
		
		
		/**是否是一个字母
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
		
		
		 
		/**  判断是否是用户名
		 * @param username
		 * @return
		 */
		public static boolean isUserName(String username) {
			return username.matches("[a-zA-z][a-zA-Z0-9_]{2,15}$");
		}
	
		
		
		/**
		 * 传入html文件，找到里面符合的链接 
		 * 例如传入<link rel="dns-prefetch" href="https://s0.ssl.qhimg.com"/>
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
//			String abcString="<a class=\"play-img\" href=\"/html/DQ221848.html\" title=\"谋杀似水年华/在线点播/迅雷下载\" target=\"_blank\"><link rel=\"dns-prefetch\" href=\"https://s0.ssl.qhimg.com\"/>";
//			String abcString="<a class=\"play-img\" href=\"/html/DQ221848.html\" title=\"谋杀似水年华/在线点播/迅雷下载\" target=\"_blank\">";
//			String abcString="<a klsk>";
			//获取链接 这个是最新的好用的
			String linkString="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
			//下面这个是有问题的，会把很多标签当成一个处理。
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
