package testUtil;

import html_Doc_Excel_TxT_Zack_Util.Html_Handle_files;

import java.util.Map;
import java.util.Set;

import org.junit.Test;
import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;
import Url_Content_Trieve_Util.getContentFromUrl_Utils;

public class Test_htmlUtil {
	
	/**
	 * ≤‚ ‘∂¡»°url
	 */
	@Test //≤‚ ‘url–¥»Î”Î∂¡»°
	public void testwriteHtmlToConsole(){ //test 
		String addressUrlString="http://toutiao.com/";
		String encodingString="utf-8";
		getContentFromUrl_Utils.writeHtmlToConsole(addressUrlString);
	}
	@Test
	public void testwriteHtmlToTxt(){ //test 
		String addressUrlString="http://www.anu.edu.au/";
		String encodingString="utf-8";
		getContentFromUrl_Utils.writeHtmlToTxt(addressUrlString, "E:/abc.txt");
	}
	
	
	@Test
	public void test_getLocalHtmlFileTitle(){
		String fileName="W:\\zackjob\\Algorithm_structure_util\\Heritrix_1.144\\jobs\\SohuNews-20160313000256928\\mirror\\news.sohu.com\\20150728\\n417670151.shtml";
		String contString=ReadAndWriteFileFromDirectory.readFileAndReturnContent(fileName);
//		System.out.println(contString);
		String tit=Html_Handle_files.getLocalHtmlContentByParserReturnStr(contString);
		System.out.println("result: "+tit);
	}
	
	
	@Test
	public void test_getLocalHtmlFileTitleReturnMap(){
		String fileName="W:\\zackjob\\Algorithm_structure_util\\Heritrix_1.144\\jobs\\SohuNews-20160313000256928\\mirror\\news.sohu.com\\20150728\\n417670151.shtml";
		String contString=ReadAndWriteFileFromDirectory.readFileAndReturnContent(fileName);
//		System.out.println(contString);
		Map<String, String> resMap=Html_Handle_files.getLocalHtmlContentByParserReturnMap(contString);
		
		Set<String> mapkeySet=resMap.keySet();
		for (String s:mapkeySet) {
			
			System.out.println(s+": "+resMap.get(s));
			
		}
	}
	
	
	@Test
	public void test_getEncodingFromHtml(){
		String pat="W:\\zackjob\\Algorithm_structure_util\\Heritrix_1.144\\jobs\\SohuNews-20160313000256928\\mirror\\news.sohu.com\\20150703\\n416102985.shtml";
		String contentall="<meta http-equiv=\"content-type\" content=\"text/html; charset=gb2312\" />";
//		String encString=Html_Handle_files.getEncodingFromHtml(null,contentall);
		String encString=Html_Handle_files.getEncodingFromHtml(pat,null);
		System.out.println(encString);
		
	}
	
	
}
