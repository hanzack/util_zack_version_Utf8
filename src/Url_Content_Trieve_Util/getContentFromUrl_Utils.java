package Url_Content_Trieve_Util;

import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import Primary_data_type_zack_util.String_handle_Zack_Util;
import constant_table_Zack_Util.Constant_table_util;

/**
 * @author The author is Zack email: hanzack@163.com
 * 
 * 这是一个工具类
 * this class is to read html contents
 */


public class getContentFromUrl_Utils {
	
	
	   /**
	    * 获取网址的编码方式
	 * @param url
	 * @return
	 */
	public static String getCode(URL url) {
		   String result="";
		   
		   try {
			   CodepageDetectorProxy detector=CodepageDetectorProxy.getInstance();
			   detector.add(JChardetFacade.getInstance());
			   Charset charset=detector.detectCodepage(url);
			   result=charset.name();
		} catch (Exception e) {
			// TODO: handle exception
		}
		   
		   
		   return result;
		   
	   }
	
	
	
	
		/**
		 * 本方法只是读取网页内容出来，直接显示在控制台，而不写入到txt中。
		 * @param url 给定网址
		 * @param encoding    获取网页的编码方式， 例如gb2312或者gbk   utf-8等等
		 * @return
		 */
		public static String writeHtmlToConsole(String addressUrl){
			
			String resultContent="";
			if (String_handle_Zack_Util.isEmpty(addressUrl)) {return "";}
			try {
				//第一步，先建立url
				URL url1=new URL(addressUrl);
				String encoding=getCode(url1);
				//第二步建立一个input流的reader，用来接收url的流
				InputStreamReader bReader=new InputStreamReader(url1.openStream(),encoding);
				//第三步， 建立一个缓冲区区读取inputstreamReader.
				BufferedReader brBufferedReader=new BufferedReader(bReader);
				String temp="";
				//一行一行的读取。
				while ((temp=brBufferedReader.readLine())!=null) {
					resultContent+=temp+Constant_table_util.HUICHEHUANHANG;//加上回车换行
				}
				brBufferedReader.close();
//				System.out.println(resultContent);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return resultContent;
		}
		
		
		
		/**		这个方法，把html内容写入到txt文件中去。
		 * @param addressUrl
		 * @param encoding
		 * @param targetDirectory :  希望文件存放的地址 例如:E:\\abc.txt
		 * @return
		 */
		public static String writeHtmlToTxt(String addressUrl, String targetDirectory){
			String resultContent="";
			if (String_handle_Zack_Util.isEmpty(addressUrl)) {return "";}
			try {
				//第一步，先建立url
				URL url1=new URL(addressUrl);
				String encoding=getCode(url1);
				//第二步建立一个input流的reader，用来接收url的流
				InputStreamReader bReader=new InputStreamReader(url1.openStream(),encoding);
				//第三步， 建立一个缓冲区区读取inputstreamReader.
				BufferedReader brBufferedReader=new BufferedReader(bReader);
				String temp="";
				
				 //3. 准备写入到文件中去
		        String pathname=targetDirectory;
		        FileWriter writer=new FileWriter(new File(pathname));
		         
				//一行一行的读取。
				while ((temp=brBufferedReader.readLine())!=null) {
					String line1=temp+Constant_table_util.HUICHEHUANHANG;
					writer.write(line1);
					resultContent+=temp+Constant_table_util.HUICHEHUANHANG;//加上回车换行
				}
//				System.out.println(resultContent);
				writer.close();
				brBufferedReader.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return resultContent;
		}
		
		
		
		
		/**给定网址  利用parser
		 * 从网址解析html并获取标签内容。 
		 * 根据一个网址获取指定标签里面的内容。    <h1>哈哈哈</h1>    可以获取哈哈哈
		 * @param addressUrl    给定一个网址
		 * @return
		 */
		public static String getHtmlContentByParser(String addressUrl) {
			String result="";
			if (String_handle_Zack_Util.isEmpty(addressUrl)) {
				return result;
			}
			try {
				//获取一个网页的解码
				URL url=new URL(addressUrl);
				String htmlEncoding=Url_Content_Trieve_Util.getEncodingFromURL_Zack.getCode(url);
				
				Parser parser=new Parser(addressUrl);
				parser.setEncoding(htmlEncoding);
				//这是要过滤标签。 
				//<div class="article-content">他是一个很厉害的人</div>
				NodeFilter nodeFilter=new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","article-content"));
				//先获取list
				NodeList nodeList=parser.parse(nodeFilter);
				parser.reset();
				Node node=nodeList.elementAt(0);
				
				result=node.toPlainTextString();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		

		
		
		
		
		/**
		 * 传入一个url获取页面内容， 写入指定文件中.并返回页面内容。注意这里的请求是Get请求。
		 * @param urlString
		 * @param newName：w://abc//123.txt
		 * @return
		 */
		public static String downloadPageContentFromURL(String urlString, String newNamePath){
			URL url=null;
			HttpURLConnection httpConnection=null;
			 String totContentString="";
			try {
				
				url=new URL(urlString);
				httpConnection=(HttpURLConnection) url.openConnection();
				//设置链接超时， 如果超过五秒
				httpConnection.setConnectTimeout(5000);
				//设置读取数据超时为30秒
				httpConnection.setReadTimeout(30000);
				httpConnection.setRequestMethod("GET");
				//设置http协议头 这里可以配置很多东西， 例如连接方式，浏览器类型等等
				httpConnection.setRequestProperty("connection", "Keep-Alive");
//				httpConnection.setRequestProperty("Content-Type", value);
				
				//判断这个文件夹是否存在， 不存在则建立。
				File page=new File(newNamePath);
//				File parentFile=page.getParentFile();		
//				if (parentFile!=null && !parentFile.exists()) {
//					parentFile.mkdirs();//如果这个目录不存在，就建立目录。会建立很多层的文件夹
//				}
				
				//返回是否获得内容的code
				String resultStatus=httpConnection.getResponseMessage();
				System.out.println("resultStatus:"+resultStatus);
				if ("OK".equals(resultStatus)) {//如果可以获取内容
					//则建立写入流
					InputStream istream=httpConnection.getInputStream();
					FileOutputStream outputStream=new FileOutputStream(page);
					
					//获取编码
					String charset=getEncodingFromURL_Zack.getCharsetFormUrl(urlString);
					  // 将字节流封装成字符流  . 一个是读入流， 一个是写入流。
	               BufferedReader br = new BufferedReader(new InputStreamReader(istream, charset));  
	                BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(outputStream, charset)); 
	             // 将输入流读入到临时变量中，再写入到文件  
	                String inputLine="";
	               
	                while ((inputLine = br.readLine()) != null) {  
	                	totContentString=totContentString+inputLine+"\r\n";
	                    // System.out.println(inputLine);  
	                }  
	                bw.write(totContentString); 
	                br.close();  
	                bw.close();  
	                System.err.println("下载完毕!");  
				}else{
					// System.err.println("页面下载失败，失败页面的连接地址为：\n" + urlStr + "\n");  
	                return "something wrong";  
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return totContentString;
		}
		
}
