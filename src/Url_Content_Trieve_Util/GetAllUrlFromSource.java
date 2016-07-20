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
	 * ����һ���ļ���ַ��Ȼ���ȡ����ļ��������������url���ӷ��뵽list��ȥ�����ҷ���
	 * 	//�ж������ַ�Ƿ������� ��������� "http://www.baidu.com/abc.html" ������
	 *		//����"/abc.html"����Ϊ�ǲ������� ���Ǳ����������Զ���ȫ�� ���ǻ�ȡԭ����ַ��
	 *��������Զ���ȫ�ġ� ֻ�ǻ��urlҳ����  �� ����url��ַ��Ȼ����뵽list��ȥ
	 * @param urlString  ����һ������ url��ַ�� ���磺 www.baidu.com
	 */
	public static List<String> getAllURLsFromFilePathFix(String filePath,String completeUrl){
		if (!completeUrl.startsWith("http")) {
			completeUrl="http://"+completeUrl;
		}
		if (String_handle_Zack_Util.isEmpty(filePath)) {
			System.out.println("you did not input filePath");
		}
		List<String> urlList=new ArrayList<String>();
		//���Ȼ�ȡ��ҳ������(writeHtmlConsole ��������Ѿ���ȡ����ҳ�Ľ��뷽ʽ��) 
		String contentStr=ReadAndWriteFileFromDirectory.readFileAndReturnContent(filePath);
		String htmlContent=contentStr;
		
		//������ȡ���ӵ�������ʽ��
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
			//�ж������ַ�Ƿ������� ��������� "http://www.baidu.com/abc.html" ������
			urlList.add(tempUrl);
//			System.out.println("this url: "+tempUrl);
		}
		System.out.println("finish");
		return urlList;
	}
	
	
	
	/**
	 * ����һ���ļ���ַ��Ȼ���ȡ����ļ��������������url���ӷ��뵽list��ȥ�����ҷ���
	 * 	//�ж������ַ�Ƿ������� ��������� "http://www.baidu.com/abc.html" ������
	 *		//����"/abc.html"����Ϊ�ǲ������� ���Ǳ����������Զ���ȫ�� ���ǻ�ȡԭ����ַ��
	 *��������Զ���ȫ�ġ� ֻ�ǻ��urlҳ����  �� ����url��ַ��Ȼ����뵽list��ȥ
	 * @param urlString  ����һ������ url��ַ�� ���磺 www.baidu.com
	 */
	public static List<String> getAllURLsFromFilePath(String filePath){
		if (String_handle_Zack_Util.isEmpty(filePath)) {
			System.out.println("you did not input filePath");
		}
		
		List<String> urlList=new ArrayList<String>();
		//���Ȼ�ȡ��ҳ������(writeHtmlConsole ��������Ѿ���ȡ����ҳ�Ľ��뷽ʽ��) 
		String contentStr=ReadAndWriteFileFromDirectory.readFileAndReturnContent(filePath);
		String htmlContent=contentStr;
		
		//������ȡ���ӵ�������ʽ��
		String hrefReg="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
		Pattern pattern=Pattern.compile(hrefReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			String tempUrl=matcher.group(3);
			//�ж������ַ�Ƿ������� ��������� "http://www.baidu.com/abc.html" ������
			urlList.add(tempUrl);
//			System.out.println("this url: "+tempUrl);
		}
		System.out.println("finish");
		return urlList;
	}
	
	
	
	
	
	
	
	
	/**
	 * ����һ���ַ�����Ȼ���ȡ���ҳ������������url���ӷ��뵽list��ȥ��
	 * 	//�ж������ַ�Ƿ������� ��������� "http://www.baidu.com/abc.html" ������
	 *		//����"/abc.html"����Ϊ�ǲ������� ���Ǳ����������Զ���ȫ�� ���ǻ�ȡԭ����ַ��
	 *��������Զ���ȫ�ġ� ֻ�ǻ��urlҳ����  �� ����url��ַ��Ȼ����뵽list��ȥ
	 * @param urlString  ����һ������ url��ַ�� ���磺 www.baidu.com
	 */
	public static List<String> getAllURLsfromString(String contentStr){
		List<String> urlList=new ArrayList<String>();
		//
		String htmlContent=contentStr;
		
		//������ȡ���ӵ�������ʽ��
		String hrefReg="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
		Pattern pattern=Pattern.compile(hrefReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			String tempUrl=matcher.group(3);
			//�ж������ַ�Ƿ������� ��������� "http://www.baidu.com/abc.html" ������
			urlList.add(tempUrl);
//			System.out.println("this url: "+tempUrl);
		}
		System.out.println("finish");
		return urlList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * ����һ��url��ַ��Ȼ���ȡ���ҳ������������url���ӷ��뵽list��ȥ��
	 * 	//�ж������ַ�Ƿ������� ��������� "http://www.baidu.com/abc.html" ������
	 *		//����"/abc.html"����Ϊ�ǲ������� ���Ǳ����������Զ���ȫ�� ���ǻ�ȡԭ����ַ��
	 *��������Զ���ȫ�ġ� ֻ�ǻ��urlҳ����  �� ����url��ַ��Ȼ����뵽list��ȥ
	 * @param urlString  ����һ������ url��ַ�� ���磺 www.baidu.com
	 */
	public static List<String> getAllURLsfromUrlNoFix(String urlString){
		//�����ַû�����Э��Ļ����Զ�����httpЭ�飬 
		if (!(urlString.startsWith("http://")|urlString.startsWith("ftp://"))) {
			urlString="http://"+urlString;
		}
		
		List<String> urlList=new ArrayList<String>();
		//���Ȼ�ȡ��ҳ������(writeHtmlConsole ��������Ѿ���ȡ����ҳ�Ľ��뷽ʽ��) 
		String htmlContent=getContentFromUrl_Utils.writeHtmlToConsole(urlString);
		
		//������ȡ���ӵ�������ʽ��
		String hrefReg="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
		Pattern pattern=Pattern.compile(hrefReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			String tempUrl=matcher.group(3);
			//�ж������ַ�Ƿ������� ��������� "http://www.baidu.com/abc.html" ������
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
	 * ����һ��url�������URL����url
	 * ����  http://www.baidu.com/abc/12121.html ��᷵��http://www.baidu.com
	 * 
	 * @param urlString
	 * @return
	 */
	public static String getMainUrlFromUrl(String urlString){
		String mainUrlString="";
		String tempString1="((http[s]?://)?(www.)?[^/]+)";
		Pattern pattern123=Pattern.compile(tempString1);
		Matcher matcher123=pattern123.matcher(urlString);
		//��ȡ��ַ�����Ҫ ��ַ��  http://www.yingshidaquan.cc/html/dianying.html
		//�����ַ��ȡ http://www.yingshidaquan.cc
		if (matcher123.find()) {
			mainUrlString=matcher123.group()+"/";
		}
		return mainUrlString;
	}
	
	
	//�����Զ���ȫ�ġ�
	/**
	 * ����һ��url��ַ��Ȼ���ȡ���ҳ������������url���ӷ��뵽list��ȥ��
	 * 	//�ж������ַ�Ƿ������� ��������� "http://www.baidu.com/abc.html" ������
	 *		//����"/abc.html"����Ϊ�ǲ������� ���Ǳ����������Զ���ȫ�� ���ǻ�ȡԭ����ַ��
	 *������Զ���ȫ�ġ� ֻ�ǻ��urlҳ����  �� ����url��ַ��Ȼ����뵽list��ȥ
	 * @param urlString  ����һ������ url��ַ�� ���磺 www.baidu.com
	 */
	public static List<String> getAllURLsfromUrlFix(String urlString){
		//�����ַû�����Э��Ļ����Զ�����httpЭ�飬 
				if (!(urlString.startsWith("http://")|urlString.startsWith("ftp://"))) {
					urlString="http://"+urlString;
				}
				String mainUrlString=getMainUrlFromUrl(urlString);
				List<String> urlList=new ArrayList<String>();
				//���Ȼ�ȡ��ҳ������(writeHtmlConsole ��������Ѿ���ȡ����ҳ�Ľ��뷽ʽ��) 
				String htmlContent=getContentFromUrl_Utils.writeHtmlToConsole(urlString);
				//������ȡ���ӵ�������ʽ��
				String hrefReg="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
				Pattern pattern=Pattern.compile(hrefReg);
				Matcher matcher=pattern.matcher(htmlContent);
				while (matcher.find()) {
					String tempUrl=matcher.group(3);
					//ȥ����ȡ��url�е�����˫���ź͵�����
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
					//�ж������ַ�Ƿ������� ��������� "http://www.baidu.com/abc.html" ������
					urlList.add(tempUrl);
				}
				System.out.println("finish");
				return urlList;
			}
		
	
	
	
}
