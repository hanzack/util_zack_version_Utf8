package testUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class testRegex {

	
	
	
	@Test 
	public void testRegexHref(){
		
		String frompath="W:\\zackjob\\hacker\\nlp_workspace_files\\����������ʽ������.txt";
		
		String abcString=ReadAndWriteFileFromDirectory.readFileAndReturnContent(frompath);
		//<link rel="dns-prefetch" href="https://s0.ssl.qhimg.com"/>
		//<a(.*)href\\s*=\\s*(\"([^\">]*)\"|[^\\s>])(.*)>
//		String abcString="<a class=\"play-img\" href=\"/html/DQ221848.html\" title=\"ıɱ��ˮ�껪/���ߵ㲥/Ѹ������\" target=\"_blank\"><link rel=\"dns-prefetch\" href=\"https://s0.ssl.qhimg.com\"/>";
//		String abcString="<a class=\"play-img\" href=\"/html/DQ221848.html\" title=\"ıɱ��ˮ�껪/���ߵ㲥/Ѹ������\" target=\"_blank\">";
//		String abcString="<a klsk>";
		//��ȡ���� ��������µĺ��õ�
		String linkString="<(a|link)[^<>]*href=(\"?)([^><\"\\s]*)(\"?)([^<>]*)>";
		//���������������ģ���Ѻܶ��ǩ����һ������
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
