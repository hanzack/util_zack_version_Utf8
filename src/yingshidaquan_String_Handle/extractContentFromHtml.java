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

	
	
	/**获取电影标题
	 * 本方法适用于影视大全网页提取 电影标题。
	 * 传入html网页内容
	 */
	public static String getMovieTitleFromHtml(String htmlContent) {
		String title="";
//		System.out.println(htmlContent);
		String getMovieTitleReg="<div class=\"info\"><h1>([^<>]*)(在线播放)</h1>";
		Pattern pattern=Pattern.compile(getMovieTitleReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			title=matcher.group(1);
//			System.out.println("title: "+title);
		}
		return title;
	}
	
	
	/**
	 * 获取电影信息
	 * @param htmlContent
	 */
	public static String getMovieDetailFromHtml(String htmlContent) {
		String issueDate="";  //上映日期
		String classify="";//电影的种类
		String actors="";// 演员
		String issueCountry="";//上演国家地区
		String issueDateReg="<span>上映年代：</span>([0-9]*)";
		
		//年份
		Pattern pattern=Pattern.compile(issueDateReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			issueDate=matcher.group(1);
		}
		

		//类型                 **********有错误   电影属于多类型的时候只能获取一个类型的。
//		类型：</span><a href='/vod-show-id-3-mcid-49-p-1.html' target='_blank'>搞笑 </a><a href='/vod-show-id-3-mcid-58-p-1.html' target='_blank'>亲子 </a> </li> <li><span>主演：
		String classifyType="<span>类型：[^<>]*</span>(<a[^<>]*>([^<>]*)</a>)*";
		Pattern patternlei=Pattern.compile(classifyType);
		Matcher matcherlei=patternlei.matcher(htmlContent);
		String totClass="";
		while (matcherlei.find()) {
			classify=matcherlei.group(2);
			totClass=totClass+classify;
		}
		
		
		// 演员
//		<li><span>主演：</span>水谷优子,一龙斋贞友,屋良有作</li>
		String actorsRec="<li><span>主演：</span>([^<>]*)</li>";
		Pattern patternActor=Pattern.compile(actorsRec);
		Matcher matcherActor=patternActor.matcher(htmlContent);
		while (matcherActor.find()) {
			actors=matcherActor.group(1);
		}
		
		
		
		// 获取地区   
//		<span>地区：</span>日本</div></li>
		String placeRec="<span>地区：</span>([^<>]*)</div></li>";
		Pattern patternCountry=Pattern.compile(placeRec);
		Matcher matcherCountry=patternCountry.matcher(htmlContent);
		while (matcherCountry.find()) {
			issueCountry=matcherCountry.group(1);
		}
		
		
		String detailString="年份： "+issueDate+"	类型： "+totClass+
				"	演员："+ actors+"		地区： "+issueCountry;
		
		return detailString;
	}
	

	/**
	 * 获取电影评分   *********************  这里要把得分转换成数字才行
	 * @param htmlContent
	 * @return
	 */
	public static String getMovieScoreFromHtml(String htmlContent) {
		String movieScore="";  //电影评分
		String movieScoreReg="<span id=\"Goldnum\" class=\"Goldnum\">([0-9][/.][0-9])</span>";
		Pattern pattern=Pattern.compile(movieScoreReg);
		Matcher matcher=pattern.matcher(htmlContent);
		while (matcher.find()) {
			movieScore=matcher.group(1);
		}
		
		return movieScore;
	}
	
	
	
	/**
	 * 获取电影剧情
	 * @param htmlContent
	 * @return
	 */
	public static String getMoviePlotFromHtml(String content) {
		String title="";
		String encodingString=Html_Handle_files.getEncodingFromHtml(null, content); //获取该网页的解码标准
		try {
			Parser parser=Parser.createParser(content, encodingString);
			//这是要过滤标签。 
			//<div class="article-content">他是一个很厉害的人</div>   <h1 itemprop="headline">习近平：中国梦归根到底是人民的梦</h1>
//			NodeFilter title_Filter=new AndFilter(new TagNameFilter("title"),new HasAttributeFilter("class","article-content"));
			NodeFilter title_Filter=new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","endtext"));
			//获取文章的内容。
//			NodeFilter title_Filter=new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("itemprop","articleBody"));
			//先获取list
			NodeList nodeList=parser.parse(title_Filter);
			Node title_node=nodeList.elementAt(0);
			if (title_node!=null) {
				 title=title_node.toPlainTextString();
			}
			parser.reset();
		} catch (Exception e) {
			// TODO: handle exception
		}
		title=title.replace("影视大全，", "");
		title=title.replace("为您提供", "");
		title=title.replace("迅雷下载", "");
		title=title.replace("在线点播", "");
		return title;
	} 
	
	
	
	
	

	
	/**
	 * 对文件夹里的所有电影进行提取然后写入文件
	 * @param frompath  电影网页html文件存储的文件夹  W:\\zackjob\\hacker\\nlp_workspace_files\\movie_yingshidaquan\\movieHtml\\
	 * @param topath1       需要把这些html解析成txt（只提取电影名，和剧情）后写入到哪个文件夹   W:\\zackjob\\hacker\\nlp_workspace_files\\movie_yingshidaquan\\movie_0\\
	 */
	public static void ChangeHtmlTotxt1(String frompath,String topath1){
		String fromPath=frompath;
		String toPath=topath1;
		List<String> fileList=ReadAndWriteFileFromDirectory.getAllFilesPathNew(fromPath, "html",new ArrayList<String>());
		for (String file:fileList) {
			String content=ReadAndWriteFileFromDirectory.readFileWithEncodingAndReturnContent(file, "UTF-8");
			
			ChangeHtmlTotxt(file,toPath);
			
			
		}
		
		System.out.println("-------------------------------完成了对所有电影的获取---------------------------------");
		
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 * 把一个html 文件进行提取， 这里是针对影视大全里的电影的html内容进行提取 出， 电影名， 主演， 剧情，评分等等。
	 * 以电影名给txt文件进行命名
	 * @param frompathFile   某个html的绝对路径   w://abc.txt
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
