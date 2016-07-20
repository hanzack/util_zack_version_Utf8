package yingshidaquan_String_Handle;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;
import html_Doc_Excel_TxT_Zack_Util.Html_Handle_files;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import normalUse.input_Util;
import normalUse.regular_Expression;
import Primary_data_type_zack_util.String_HANZI_Zack_util;
import Primary_data_type_zack_util.String_handle_Zack_Util;

public class extractContentFromHtml {

	
	
	/**��ȡ��Ӱ����
	 * ������������Ӱ�Ӵ�ȫ��ҳ��ȡ ��Ӱ���⡣
	 * ����html��ҳ����
	 */
	public static String getMovieTitleFromHtml(String htmlContent) {
		String title="";
//		System.out.println(htmlContent);
		String getMovieTitleReg="<div class=\"info\"><h1>([^<>]*)(���߲���)</h1>";
		Pattern pattern=Pattern.compile(getMovieTitleReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			title=matcher.group(1);
//			System.out.println("title: "+title);
		}
		return title;
	}
	
	
	/**
	 * ��ȡ��Ӱ��Ϣ
	 * @param htmlContent
	 */
	public static String getMovieDetailFromHtml(String htmlContent) {
		String issueDate="";  //��ӳ����
		String classify="";//��Ӱ������
		String actors="";// ��Ա
		String issueCountry="";//���ݹ��ҵ���
		String issueDateReg="<span>��ӳ�����</span>([0-9]*)";
		
		//���
		Pattern pattern=Pattern.compile(issueDateReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			issueDate=matcher.group(1);
		}
		

		//����                 **********�д���   ��Ӱ���ڶ����͵�ʱ��ֻ�ܻ�ȡһ�����͵ġ�
//		���ͣ�</span><a href='/vod-show-id-3-mcid-49-p-1.html' target='_blank'>��Ц </a><a href='/vod-show-id-3-mcid-58-p-1.html' target='_blank'>���� </a> </li> <li><span>���ݣ�
		String classifyType="<span>���ͣ�[^<>]*</span>(<a[^<>]*>([^<>]*)</a>)*";
		Pattern patternlei=Pattern.compile(classifyType);
		Matcher matcherlei=patternlei.matcher(htmlContent);
		String totClass="";
		while (matcherlei.find()) {
			classify=matcherlei.group(2);
			totClass=totClass+classify;
		}
		
		
		// ��Ա
//		<li><span>���ݣ�</span>ˮ������,һ��ի����,��������</li>
		String actorsRec="<li><span>���ݣ�</span>([^<>]*)</li>";
		Pattern patternActor=Pattern.compile(actorsRec);
		Matcher matcherActor=patternActor.matcher(htmlContent);
		while (matcherActor.find()) {
			actors=matcherActor.group(1);
		}
		
		
		
		// ��ȡ����   
//		<span>������</span>�ձ�</div></li>
		String placeRec="<span>������</span>([^<>]*)</div></li>";
		Pattern patternCountry=Pattern.compile(placeRec);
		Matcher matcherCountry=patternCountry.matcher(htmlContent);
		while (matcherCountry.find()) {
			issueCountry=matcherCountry.group(1);
		}
		
		
		String detailString="��ݣ� "+issueDate+"	���ͣ� "+totClass+
				"	��Ա��"+ actors+"		������ "+issueCountry;
		
		return detailString;
	}
	

	/**
	 * ��ȡ��Ӱ����   *********************  ����Ҫ�ѵ÷�ת�������ֲ���
	 * @param htmlContent
	 * @return
	 */
	public static String getMovieScoreFromHtml(String htmlContent) {
		String movieScore="";  //��Ӱ����
		String movieScoreReg="<span id=\"Goldnum\" class=\"Goldnum\">([0-9][/.][0-9])</span>";
		Pattern pattern=Pattern.compile(movieScoreReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			movieScore=matcher.group(1);
		}
		
		return movieScore;
	}
	
	
	
	/**
	 * ��ȡ��Ӱ����
	 * @param htmlContent
	 * @return
	 */
	public static String getMoviePlotFromHtml(String content) {
		String title="";
		String encodingString=Html_Handle_files.getEncodingFromHtml(null, content); //��ȡ����ҳ�Ľ����׼
		try {
			Parser parser=Parser.createParser(content, encodingString);
			//����Ҫ���˱�ǩ�� 
			//<div class="article-content">����һ������������</div>   <h1 itemprop="headline">ϰ��ƽ���й��ι���������������</h1>
//			NodeFilter title_Filter=new AndFilter(new TagNameFilter("title"),new HasAttributeFilter("class","article-content"));
			NodeFilter title_Filter=new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","endtext"));
			//��ȡ���µ����ݡ�
//			NodeFilter title_Filter=new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("itemprop","articleBody"));
			//�Ȼ�ȡlist
			NodeList nodeList=parser.parse(title_Filter);
			Node title_node=nodeList.elementAt(0);
			if (title_node!=null) {
				 title=title_node.toPlainTextString();
			}
			parser.reset();
		} catch (Exception e) {
			// TODO: handle exception
		}
		title=title.replace("Ӱ�Ӵ�ȫ��", "");
		title=title.replace("Ϊ���ṩ", "");
		title=title.replace("Ѹ������", "");
		title=title.replace("���ߵ㲥", "");
		return title;
	} 
	
	
	
	
	

	
	/**
	 * ���ļ���������е�Ӱ������ȡȻ��д���ļ�
	 * @param frompath  ��Ӱ��ҳhtml�ļ��洢���ļ���  W:\\zackjob\\hacker\\nlp_workspace_files\\movie_yingshidaquan\\movieHtml\\
	 * @param topath1       ��Ҫ����Щhtml������txt��ֻ��ȡ��Ӱ�����;��飩��д�뵽�ĸ��ļ���   W:\\zackjob\\hacker\\nlp_workspace_files\\movie_yingshidaquan\\movie_0\\
	 */
	public static void ChangeHtmlTotxt1(String frompath,String topath1){
		String fromPath=frompath;
		String toPath=topath1;
		List<String> fileList=ReadAndWriteFileFromDirectory.getAllFilesPathNew(fromPath, "html",new ArrayList<String>());
		for (String file:fileList) {
			String content=ReadAndWriteFileFromDirectory.readFileWithEncodingAndReturnContent(file, "UTF-8");
			
			ChangeHtmlTotxt(file,toPath);
			
			
		}
		
		System.out.println("-------------------------------����˶����е�Ӱ�Ļ�ȡ---------------------------------");
		
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 * ��һ��html �ļ�������ȡ�� ���������Ӱ�Ӵ�ȫ��ĵ�Ӱ��html���ݽ�����ȡ ���� ��Ӱ���� ���ݣ� ���飬���ֵȵȡ�
	 * �Ե�Ӱ����txt�ļ���������
	 * @param frompathFile   ĳ��html�ľ���·��   w://abc.txt
	 * @param toFilePath     w://
	 */
	public static void ChangeHtmlTotxt(String frompathFile,String toFilePath){
		String wholeContent="";
		String titleString="";
		String detailString="";
		String moviescore="";
		String movieplotString="";
			String content=ReadAndWriteFileFromDirectory.readFileWithEncodingAndReturnContent(frompathFile, "UTF-8");
			titleString=extractContentFromHtml.getMovieTitleFromHtml(content);
			detailString=extractContentFromHtml.getMovieDetailFromHtml(content);
			moviescore=extractContentFromHtml.getMovieScoreFromHtml(content);
			movieplotString=extractContentFromHtml.getMoviePlotFromHtml(content);
//			System.out.println(titleString+"---"+detailString+"---"+moviescore);
			wholeContent=titleString+"\r\n"+moviescore+"\r\n"+detailString+"\r\n"+movieplotString+"\r\n";
		String oldFilenameString=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(frompathFile);
		String newFileName=titleString.replace("/", "_")+"_"+oldFilenameString;
//		System.out.println(newFileName+"\r\n"+wholeContent);
		
		
		String tofilePathString=toFilePath+newFileName+".txt";
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(wholeContent, tofilePathString);
		System.out.println("finish extracting--"+frompathFile+"and finish writing!");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
