package Url_Content_Trieve_Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;
import Primary_data_type_zack_util.String_handle_Zack_Util;
import Url_Content_Trieve_Util.getContentFromUrl_Utils;

public class GetAllUrlFromSource {

	/**
	 * 传入一个文件地址，然后读取这个文件，把里面的所有url链接放入到list中去。并且返回
	 * 	//判断这个网址是否完整。 例如如果是 "http://www.baidu.com/abc.html" 则完整
	 *		//若是"/abc.html"则认为是不完整， 但是本方法不会自动补全， 而是获取原生地址。
	 *不会进行自动补全的。 只是获得url页面里  的 所有url地址，然后加入到list中去
	 * @param urlString  传入一个网络 url地址： 例如： www.baidu.com
	 */
	public static List<String> getAllURLsFromFilePathFix(String filePath,String completeUrl){
		if (!completeUrl.startsWith("http")) {
			completeUrl="http://"+completeUrl;
		}
		if (String_handle_Zack_Util.isEmpty(filePath)) {
			System.out.println("you did not input filePath");
		}
		List<String> urlList=new ArrayList<String>();
		//首先获取网页的内容(writeHtmlConsole 这个方法已经获取了网页的解码方式。) 
		String contentStr=ReadAndWriteFileFromDirectory.readFileAndReturnContent(filePath);
		String htmlContent=contentStr;
		
		//建立获取链接的正则表达式。
		String hrefReg="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
		Pattern pattern=Pattern.compile(hrefReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			String tempUrl=matcher.group(3);
			if (tempUrl.startsWith("/")) {
				tempUrl=completeUrl+tempUrl;
			}else if (tempUrl.startsWith("www")) {
				tempUrl="http://"+tempUrl;
			}
			//判断这个网址是否完整。 例如如果是 "http://www.baidu.com/abc.html" 则完整
			urlList.add(tempUrl);
//			System.out.println("this url: "+tempUrl);
		}
		System.out.println("finish");
		return urlList;
	}
	
	
	
	/**
	 * 传入一个文件地址，然后读取这个文件，把里面的所有url链接放入到list中去。并且返回
	 * 	//判断这个网址是否完整。 例如如果是 "http://www.baidu.com/abc.html" 则完整
	 *		//若是"/abc.html"则认为是不完整， 但是本方法不会自动补全， 而是获取原生地址。
	 *不会进行自动补全的。 只是获得url页面里  的 所有url地址，然后加入到list中去
	 * @param urlString  传入一个网络 url地址： 例如： www.baidu.com
	 */
	public static List<String> getAllURLsFromFilePath(String filePath){
		if (String_handle_Zack_Util.isEmpty(filePath)) {
			System.out.println("you did not input filePath");
		}
		
		List<String> urlList=new ArrayList<String>();
		//首先获取网页的内容(writeHtmlConsole 这个方法已经获取了网页的解码方式。) 
		String contentStr=ReadAndWriteFileFromDirectory.readFileAndReturnContent(filePath);
		String htmlContent=contentStr;
		
		//建立获取链接的正则表达式。
		String hrefReg="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
		Pattern pattern=Pattern.compile(hrefReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			String tempUrl=matcher.group(3);
			//判断这个网址是否完整。 例如如果是 "http://www.baidu.com/abc.html" 则完整
			urlList.add(tempUrl);
//			System.out.println("this url: "+tempUrl);
		}
		System.out.println("finish");
		return urlList;
	}
	
	
	
	
	
	
	
	
	/**
	 * 传入一个字符串，然后读取这个页面把里面的所有url链接放入到list中去。
	 * 	//判断这个网址是否完整。 例如如果是 "http://www.baidu.com/abc.html" 则完整
	 *		//若是"/abc.html"则认为是不完整， 但是本方法不会自动补全， 而是获取原生地址。
	 *不会进行自动补全的。 只是获得url页面里  的 所有url地址，然后加入到list中去
	 * @param urlString  传入一个网络 url地址： 例如： www.baidu.com
	 */
	public static List<String> getAllURLsfromString(String contentStr){
		List<String> urlList=new ArrayList<String>();
		//
		String htmlContent=contentStr;
		
		//建立获取链接的正则表达式。
		String hrefReg="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
		Pattern pattern=Pattern.compile(hrefReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			String tempUrl=matcher.group(3);
			//判断这个网址是否完整。 例如如果是 "http://www.baidu.com/abc.html" 则完整
			urlList.add(tempUrl);
//			System.out.println("this url: "+tempUrl);
		}
		System.out.println("finish");
		return urlList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 传入一个url地址，然后读取这个页面把里面的所有url链接放入到list中去。
	 * 	//判断这个网址是否完整。 例如如果是 "http://www.baidu.com/abc.html" 则完整
	 *		//若是"/abc.html"则认为是不完整， 但是本方法不会自动补全， 而是获取原生地址。
	 *不会进行自动补全的。 只是获得url页面里  的 所有url地址，然后加入到list中去
	 * @param urlString  传入一个网络 url地址： 例如： www.baidu.com
	 */
	public static List<String> getAllURLsfromUrlNoFix(String urlString){
		//如果网址没有添加协议的话，自动加上http协议， 
		if (!(urlString.startsWith("http://")|urlString.startsWith("ftp://"))) {
			urlString="http://"+urlString;
		}
		
		List<String> urlList=new ArrayList<String>();
		//首先获取网页的内容(writeHtmlConsole 这个方法已经获取了网页的解码方式。) 
		String htmlContent=getContentFromUrl_Utils.writeHtmlToConsole(urlString);
		
		//建立获取链接的正则表达式。
		String hrefReg="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
		Pattern pattern=Pattern.compile(hrefReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			String tempUrl=matcher.group(3);
			//判断这个网址是否完整。 例如如果是 "http://www.baidu.com/abc.html" 则完整
			urlList.add(tempUrl);
//			System.out.println("this url: "+tempUrl);
		}
		System.out.println("finish");
		return urlList;
	}
	
	
	
	@Test
	public void testGetUrl(){
		String mainUrlString="";
//		String input="https://www.youtube.com/watch?v=PIb6DIi_bj8&oref=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DPIb6DIi_bj8&has_verified=1";
//		String input="http://www.yingshidaquan.cc/html/dianying.html";
		String input="www.baidu.com/";
		String tempString1="((http[s]?://)?(www.)?[^/]+)";
		Pattern pattern=Pattern.compile(tempString1);
		Matcher matcher=pattern.matcher(input);
		if (matcher.find()) {
			mainUrlString=matcher.group();
		}
		System.out.println("Main: "+mainUrlString);
	}
	
	
	@Test
	public void testGetUrlw(){
		String urlString="www.baidu.com";
		List<String> list1=getAllURLsfromUrlNoFix(urlString);
		for (String string : list1) {
			System.out.println(string);
		}
	}
	
	
	
	/**
	 * 给定一个url返回这个URL的主url
	 * 例如  http://www.baidu.com/abc/12121.html 则会返回http://www.baidu.com
	 * 
	 * @param urlString
	 * @return
	 */
	public static String getMainUrlFromUrl(String urlString){
		String mainUrlString="";
		String tempString1="((http[s]?://)?(www.)?[^/]+)";
		Pattern pattern123=Pattern.compile(tempString1);
		Matcher matcher123=pattern123.matcher(urlString);
		//获取网址里的主要 网址。  http://www.yingshidaquan.cc/html/dianying.html
		//这个网址截取 http://www.yingshidaquan.cc
		if (matcher123.find()) {
			mainUrlString=matcher123.group()+"/";
		}
		return mainUrlString;
	}
	
	
	//可以自动补全的。
	/**
	 * 传入一个url地址，然后读取这个页面把里面的所有url链接放入到list中去。
	 * 	//判断这个网址是否完整。 例如如果是 "http://www.baidu.com/abc.html" 则完整
	 *		//若是"/abc.html"则认为是不完整， 但是本方法不会自动补全， 而是获取原生地址。
	 *会进行自动补全的。 只是获得url页面里  的 所有url地址，然后加入到list中去
	 * @param urlString  传入一个网络 url地址： 例如： www.baidu.com
	 */
	public static List<String> getAllURLsfromUrlFix(String urlString){
		//如果网址没有添加协议的话，自动加上http协议， 
				if (!(urlString.startsWith("http://")|urlString.startsWith("ftp://"))) {
					urlString="http://"+urlString;
				}
				String mainUrlString=getMainUrlFromUrl(urlString);
				List<String> urlList=new ArrayList<String>();
				//首先获取网页的内容(writeHtmlConsole 这个方法已经获取了网页的解码方式。) 
				String htmlContent=getContentFromUrl_Utils.writeHtmlToConsole(urlString);
				//建立获取链接的正则表达式。
				String hrefReg="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
				Pattern pattern=Pattern.compile(hrefReg);
				Matcher matcher=pattern.matcher(htmlContent);
				while (matcher.find()) {
					String tempUrl=matcher.group(3);
					//去掉截取的url中的所有双引号和单引号
					tempUrl=tempUrl.replaceAll("\'|\"", "");
					if (!(tempUrl.startsWith("http://")||tempUrl.startsWith("www."))) {
						if (tempUrl.startsWith("/")) {
							String mainUrlString1=mainUrlString.replaceFirst("/$", "");
//							System.out.println("old----------:  "+tempUrl);
							tempUrl=mainUrlString1+tempUrl;
//							System.out.println("new----------:  "+tempUrl);
						}else{
							tempUrl=mainUrlString+tempUrl;
						}
					}
					//判断这个网址是否完整。 例如如果是 "http://www.baidu.com/abc.html" 则完整
					urlList.add(tempUrl);
				}
				System.out.println("finish");
				return urlList;
			}
		
	
	
	
}
