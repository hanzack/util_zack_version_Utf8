package html_Doc_Excel_TxT_Zack_Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import Primary_data_type_zack_util.String_handle_Zack_Util;
import Primary_data_type_zack_util.createID_Unique;
import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class Html_Handle_files {

	

	/**
	 * 这个方法只会获取html中title，因为内部调用了  getLocalHtmlContentByParserReturnStr
	 * 这里是一个local parser 利用htmlparser解析本地文件夹里面的文件. 并且把解析出来的内容写入到一个新的指定的文件中去
	 * @param fromPath    "W:\\zackjob\\Algorithm_structure_util\\Heritrix_1.144\\jobs\\SohuNews-20160313000256928\\mirror\\news.sohu.com\\"
	 * @param toPath
	 * @param postFix   需要从目录中得到以哪些后缀结尾的文件， .html,还是.txt 如果给空则全部文件都获取。 
	 * @param newFileName   需要给写入的文件一个名字
	 * @param targetFix  目标文件存储成什么格式。
	 */
	public static void getAllFilesTitleFrmDirWrtNewFile(String fromPath,String toPath,String postFix,String newFileName, String targetFix){
		//首先制定好路径的问题。
		String fileUrl="";
		if (String_handle_Zack_Util.isEmpty(fromPath)) {
			fileUrl="W:\\zackjob\\Algorithm_structure_util\\Heritrix_1.144\\jobs\\SohuNews-20160313000256928\\mirror\\news.sohu.com\\";
		}else {			fileUrl=fromPath;		}

		if (String_handle_Zack_Util.isEmpty(toPath)) {
			toPath="W://zackjob//";
		}
		if (String_handle_Zack_Util.isEmpty(postFix)) {
			//此处无用
			postFix=".txt";
		}
		if (String_handle_Zack_Util.isEmpty(newFileName)) {
			newFileName=createID_Unique.getIDByTimeCalender();
		}
		if (String_handle_Zack_Util.isEmpty(targetFix)) {
			targetFix="txt";
		}
		
		//解析文件夹下面的所有路径.递归解析。 先获取所有文件的绝对路径. 此处获取所有文件的list
//		System.out.println("11111-----------------------------------");
		List<String> allFileList=ReadAndWriteFileFromDirectory.getAllFilesPath(fileUrl,postFix);
		for (String path: allFileList) {
			String content=ReadAndWriteFileFromDirectory.readFileAndReturnContent(path);
			String list11 = Html_Handle_files.getLocalHtmlContentByParserReturnStr(content)+"\r\n";
			
			//对标题进行文件的写入
			ReadAndWriteFileFromDirectory.writeStringToFile(list11, toPath, newFileName, targetFix);
			
		}
		
		System.out.println("finish writing!");
		
	}
	

	/** 
	 * 传入html内容的字符串
	 * 获取html文件标题， 返回title字符串。
	 * 利用parser来解析本地html文件， 例如通过标签获取html文件的标题。 根据标签返回某一个内容，此处返回title.
	 * @param content
	 */
	public static String getLocalHtmlContentByParserReturnStr(String content){
		String title="";
		String encodingString=getEncodingFromHtml(null, content); //获取该网页的解码标准
		try {
			Parser parser=Parser.createParser(content, encodingString);
			//这是要过滤标签。 
			//<div class="article-content">他是一个很厉害的人</div>   <h1 itemprop="headline">习近平：中国梦归根到底是人民的梦</h1>
//			NodeFilter title_Filter=new AndFilter(new TagNameFilter("title"),new HasAttributeFilter("class","article-content"));
			NodeFilter title_Filter=new AndFilter(new TagNameFilter("h1"),new HasAttributeFilter("itemprop","headline"));
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
		return title;
	} 
	

	
	
	/** 
	 * 传入html内容的字符串
	 * 获取html内容多个指定内容，返回的是HashMap  title:访问美国各州。
	 * 利用parser来解析本地html文件， 例如通过标签获取html文件的标题,时间， 主题内容等等。 根据标签返回某一个内容，此处返回title.
	 * @param content
	 */
	public static Map<String, String> getLocalHtmlContentByParserReturnMap(String content){
		String title="wrong";
		String timeStr="wrong";
		String contentString="wrong";
		String encoding1=getEncodingFromHtml(null, content);  //获取该网页的解码标准
		try {
			Parser parser=Parser.createParser(content, encoding1);
			//这是要过滤标签  此处为title。 -----------------------------------------
			NodeFilter title_Filter=new AndFilter(new TagNameFilter("h1"),new HasAttributeFilter("itemprop","headline"));
			//先获取list
			NodeList nodeList=parser.parse(title_Filter);
			Node title_node=nodeList.elementAt(0);
			//得到文章标题
			if (title_node!=null) {
				 title=title_node.toPlainTextString();
			}
			parser.reset();
			
			//接下来是提取新闻的发布时间 time    ----------------------------------------
			NodeFilter time_Filter=new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","time"));
			//先获取list
			NodeList time_nodeList=parser.parse(time_Filter);
			Node time_node=time_nodeList.elementAt(0);
			//得到文章标题
			if (time_node!=null) {
				timeStr=time_node.toPlainTextString();
			}
			parser.reset();
			
			
			//接下来是提取新闻的发布的新闻内容    --------------<div class="text clear" id="contentText">--------------------------
			NodeFilter content_Filter=new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("id","contentText"));
			//先获取list
			NodeList content_nodeList=parser.parse(content_Filter);
			Node content_node=content_nodeList.elementAt(0);
			//得到文章标题
			if (content_node!=null) {
				content=content_node.toPlainTextString();
			}
			parser.reset();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//接下来建立一个map把上面的获取的内容都添加进来。
		Map<String, String> tagMap=new HashMap<String, String>();
		tagMap.put("title", title);
		tagMap.put("time", timeStr);
		tagMap.put("content", content);
		
		return tagMap;
	} 
	
	
	/**
	 * 
	 * 给定一个html 文件地址， 获取html的encoding。 利用正则表达式。
	 * @param fromHtmlFilePath    例如    W://zackjob//abc.html
	 * @param contentAll         也可以直接传入html的内容，返回encoding， 也可以设置为null 或者空
	 * @return
	 * 通过url获取encoding请看另外一个包的文件
	 */
	public static String getEncodingFromHtml(String fromHtmlFilePath, String contentAll){
		String encodingString="utf-8";
		String reg="<meta(.*)\\s*charset=(.*)/>";
		String content="";
		//如果传入的内容为空则，去从路径获取内容，否则直接使用内容，而不用访问路径。
		if (String_handle_Zack_Util.isEmpty(contentAll)) {
			if (String_handle_Zack_Util.isEmpty(fromHtmlFilePath)) {
				return "wrong!";
			}else{
				content=ReadAndWriteFileFromDirectory.readFileAndReturnContent(fromHtmlFilePath);
			}
		}else{
			content=contentAll;
		}
		
		Pattern pattern=Pattern.compile(reg);
		Matcher matcher=pattern.matcher(content);
		if (matcher.find()) {
			encodingString=matcher.group(2);
		}
//		System.out.println("before"+encodingString);
		encodingString=encodingString.replace("\"", "");
//		System.out.println("after"+encodingString.contains("\""));
		return encodingString;
	}
	
}
