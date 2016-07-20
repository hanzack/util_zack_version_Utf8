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
 * ����һ��������
 * this class is to read html contents
 */


public class getContentFromUrl_Utils {
	
	
	   /**
	    * ��ȡ��ַ�ı��뷽ʽ
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
		 * ������ֻ�Ƕ�ȡ��ҳ���ݳ�����ֱ����ʾ�ڿ���̨������д�뵽txt�С�
		 * @param url ������ַ
		 * @param encoding    ��ȡ��ҳ�ı��뷽ʽ�� ����gb2312����gbk   utf-8�ȵ�
		 * @return
		 */
		public static String writeHtmlToConsole(String addressUrl){
			
			String resultContent="";
			if (String_handle_Zack_Util.isEmpty(addressUrl)) {return "";}
			try {
				//��һ�����Ƚ���url
				URL url1=new URL(addressUrl);
				String encoding=getCode(url1);
				//�ڶ�������һ��input����reader����������url����
				InputStreamReader bReader=new InputStreamReader(url1.openStream(),encoding);
				//�������� ����һ������������ȡinputstreamReader.
				BufferedReader brBufferedReader=new BufferedReader(bReader);
				String temp="";
				//һ��һ�еĶ�ȡ��
				while ((temp=brBufferedReader.readLine())!=null) {
					resultContent+=temp+Constant_table_util.HUICHEHUANHANG;//���ϻس�����
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
		
		
		
		/**		�����������html����д�뵽txt�ļ���ȥ��
		 * @param addressUrl
		 * @param encoding
		 * @param targetDirectory :  ϣ���ļ���ŵĵ�ַ ����:E:\\abc.txt
		 * @return
		 */
		public static String writeHtmlToTxt(String addressUrl, String targetDirectory){
			String resultContent="";
			if (String_handle_Zack_Util.isEmpty(addressUrl)) {return "";}
			try {
				//��һ�����Ƚ���url
				URL url1=new URL(addressUrl);
				String encoding=getCode(url1);
				//�ڶ�������һ��input����reader����������url����
				InputStreamReader bReader=new InputStreamReader(url1.openStream(),encoding);
				//�������� ����һ������������ȡinputstreamReader.
				BufferedReader brBufferedReader=new BufferedReader(bReader);
				String temp="";
				
				 //3. ׼��д�뵽�ļ���ȥ
		        String pathname=targetDirectory;
		        FileWriter writer=new FileWriter(new File(pathname));
		         
				//һ��һ�еĶ�ȡ��
				while ((temp=brBufferedReader.readLine())!=null) {
					String line1=temp+Constant_table_util.HUICHEHUANHANG;
					writer.write(line1);
					resultContent+=temp+Constant_table_util.HUICHEHUANHANG;//���ϻس�����
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
		
		
		
		
		/**������ַ  ����parser
		 * ����ַ����html����ȡ��ǩ���ݡ� 
		 * ����һ����ַ��ȡָ����ǩ��������ݡ�    <h1>������</h1>    ���Ի�ȡ������
		 * @param addressUrl    ����һ����ַ
		 * @return
		 */
		public static String getHtmlContentByParser(String addressUrl) {
			String result="";
			if (String_handle_Zack_Util.isEmpty(addressUrl)) {
				return result;
			}
			try {
				//��ȡһ����ҳ�Ľ���
				URL url=new URL(addressUrl);
				String htmlEncoding=Url_Content_Trieve_Util.getEncodingFromURL_Zack.getCode(url);
				
				Parser parser=new Parser(addressUrl);
				parser.setEncoding(htmlEncoding);
				//����Ҫ���˱�ǩ�� 
				//<div class="article-content">����һ������������</div>
				NodeFilter nodeFilter=new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","article-content"));
				//�Ȼ�ȡlist
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
		 * ����һ��url��ȡҳ�����ݣ� д��ָ���ļ���.������ҳ�����ݡ�ע�������������Get����
		 * @param urlString
		 * @param newName��w://abc//123.txt
		 * @return
		 */
		public static String downloadPageContentFromURL(String urlString, String newNamePath){
			URL url=null;
			HttpURLConnection httpConnection=null;
			 String totContentString="";
			try {
				
				url=new URL(urlString);
				httpConnection=(HttpURLConnection) url.openConnection();
				//�������ӳ�ʱ�� �����������
				httpConnection.setConnectTimeout(5000);
				//���ö�ȡ���ݳ�ʱΪ30��
				httpConnection.setReadTimeout(30000);
				httpConnection.setRequestMethod("GET");
				//����httpЭ��ͷ ����������úܶණ���� �������ӷ�ʽ����������͵ȵ�
				httpConnection.setRequestProperty("connection", "Keep-Alive");
//				httpConnection.setRequestProperty("Content-Type", value);
				
				//�ж�����ļ����Ƿ���ڣ� ������������
				File page=new File(newNamePath);
//				File parentFile=page.getParentFile();		
//				if (parentFile!=null && !parentFile.exists()) {
//					parentFile.mkdirs();//������Ŀ¼�����ڣ��ͽ���Ŀ¼���Ὠ���ܶ����ļ���
//				}
				
				//�����Ƿ������ݵ�code
				String resultStatus=httpConnection.getResponseMessage();
				System.out.println("resultStatus:"+resultStatus);
				if ("OK".equals(resultStatus)) {//������Ի�ȡ����
					//����д����
					InputStream istream=httpConnection.getInputStream();
					FileOutputStream outputStream=new FileOutputStream(page);
					
					//��ȡ����
					String charset=getEncodingFromURL_Zack.getCharsetFormUrl(urlString);
					  // ���ֽ�����װ���ַ���  . һ���Ƕ������� һ����д������
	               BufferedReader br = new BufferedReader(new InputStreamReader(istream, charset));  
	                BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(outputStream, charset)); 
	             // �����������뵽��ʱ�����У���д�뵽�ļ�  
	                String inputLine="";
	               
	                while ((inputLine = br.readLine()) != null) {  
	                	totContentString=totContentString+inputLine+"\r\n";
	                    // System.out.println(inputLine);  
	                }  
	                bw.write(totContentString); 
	                br.close();  
	                bw.close();  
	                System.err.println("�������!");  
				}else{
					// System.err.println("ҳ������ʧ�ܣ�ʧ��ҳ������ӵ�ַΪ��\n" + urlStr + "\n");  
	                return "something wrong";  
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return totContentString;
		}
		
}
