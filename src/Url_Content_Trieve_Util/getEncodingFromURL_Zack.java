package Url_Content_Trieve_Util;

import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author The author is Zack email: hanzack@163.com
 * 
 */


	/**
	 * 
	 * 获取网页编码---使用cpdetector
	 * @author Miao
	 *
	 */
	public class getEncodingFromURL_Zack {
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
	     * 从url地址获取编码格式 
	     *  
	     * @param url 
	     */  
	    public static String getCharsetFormUrl(String urlString) {  
	        InputStream in = null;  
	        int chByte = 0;  
	        URL url = null;  
	        HttpURLConnection httpConn = null;  
	        String contents = null;  
	        String charset = "utf-8";  
	        int len = 0;  
	        // 差不多这么大已经可以读到编码格式了  
	        byte[] b = new byte[1024];  
	        try {  
	            url = new URL(urlString);  
	            httpConn = (HttpURLConnection) url.openConnection();  
	            HttpURLConnection.setFollowRedirects(true);  
	            httpConn.setRequestMethod("GET");  
	            httpConn.setRequestProperty("User-Agent",  
	                    "Mozilla/4.0(compatible; MSIE 6.0; Windows 2000)");  
	            httpConn.setConnectTimeout(5000);  
	            httpConn.setReadTimeout(30000);  
	            // System.out.println(httpConn.getResponseMessage());  
	            in = httpConn.getInputStream();  
	  
	            // 用于标记内容  
	            chByte = in.read();  
	            while (chByte != -1) {  
	                chByte = in.read();  
	                b[len++] = (byte) chByte;  
	                if (len >= 1024) {  
	                    break;  
	                }  
	            }  
	            contents = new String(b);  
	            Pattern p = Pattern.compile(  
	                    "<meta[^>]*?charset=[\"]?(\\w+)[\\W]*?>",  
	                    Pattern.CASE_INSENSITIVE);  
	            Matcher m = p.matcher(contents);  
	            if (m.find()) {  
	                charset = m.group(1).trim();  
	            }  
	            in.close();  
	        } catch (MalformedURLException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            try {  
	            	if (in!=null) {
	            		in.close();  
					}
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
//	        System.out.println(charset);  
	        return charset;  
	    }  
		
}
