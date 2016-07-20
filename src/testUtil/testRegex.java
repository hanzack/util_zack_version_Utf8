package testUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class testRegex {

	
	
	
	@Test 
	public void testRegexHref(){
		
		String frompath="W:\\zackjob\\hacker\\nlp_workspace_files\\测试正则表达式的链接.txt";
		
		String abcString=ReadAndWriteFileFromDirectory.readFileAndReturnContent(frompath);
		//<link rel="dns-prefetch" href="https://s0.ssl.qhimg.com"/>
		//<a(.*)href\\s*=\\s*(\"([^\">]*)\"|[^\\s>])(.*)>
//		String abcString="<a class=\"play-img\" href=\"/html/DQ221848.html\" title=\"谋杀似水年华/在线点播/迅雷下载\" target=\"_blank\"><link rel=\"dns-prefetch\" href=\"https://s0.ssl.qhimg.com\"/>";
//		String abcString="<a class=\"play-img\" href=\"/html/DQ221848.html\" title=\"谋杀似水年华/在线点播/迅雷下载\" target=\"_blank\">";
//		String abcString="<a klsk>";
		//获取链接 这个是最新的好用的
		String linkString="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
		//下面这个是有问题的，会把很多标签当成一个处理。
//		String linkString="<a(.*)href\\s*=\\s*(\"([^\">]*)\"|[^\\s>])(.*)>";
		Pattern pattern=Pattern.compile(linkString);
		Matcher matcher=pattern.matcher(abcString);
		int count=0;
		while(matcher.find()) {
			count++;
			System.out.println(matcher.group(3)+"-----from-----"+matcher.group());			
		}
		System.out.println("======"+count);
	}
	
}
