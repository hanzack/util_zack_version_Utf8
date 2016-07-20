package jsonProcessing;

import java.util.Iterator;
import java.util.List;

//import net.sf.json.JSONArray;




import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.processors.JsonValueProcessorMatcher;

import org.junit.Test;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class JsonHandleFiles {

	
	public static String result="";
	
	/**
	 * 传入一个json的File路径， 把它进行整理成更好的格式进行输出
	 * @param FilePath
	 */
	public  static void convertJsonFileInDocument(String FilePath,String encoding, String writeToFilePath) {
		if (Primary_data_type_zack_util.String_handle_Zack_Util.isEmpty(encoding)) {
			encoding ="UTF-8";
		}
		String fileString=ReadAndWriteFileFromDirectory.readFileWithEncodingAndReturnContent(FilePath, encoding);
//		System.out.println(fileString);
		convertJsonStrInDocument(fileString,0);
		//经过上面的方法的调用， result已经获取了， 现在写入文件夹中去。
		if (writeToFilePath.isEmpty()) {
			writeToFilePath=FilePath.replace(".html",".txt");
		}
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(result, writeToFilePath);
		
		//对结果文本进行清零， 以接受下一篇文章的数据
		result="";
	}
	
	
	
	
	/**
	 * 例子： 
	 * 传入：   {'id':1,'content':[{'company':'AA'},{'company':'BBB'},{'company':'CCC'}],'time':'1203'}
	 * 会打印出：(格式非常重要)
	 * id	1
	   content
			company	AA

			company	BBB

			company	CCC

	    time	1203
	 * 
	 * 
	 * 传入一个json数据， 然后进行更好的整理进行输出， 注意由于json数据本身非常复杂， 所以这里在获得数据以后。
	 * 变成数组， 数组里每一个元素是一个对象，
	 * 如果对象的key对应的value   包含{}开头， 那么再把它当成一个对象进行处理，  如果对应的value里面是[开头，也就是说这是一个数组，
	 * 把它当成数组进行处理， 然后获取里面的内容
	 * 递归判断是否数组或者对象。 
	 * 总而言之：  遇到{ 就是对象， 对象里面的元素遇到[]就是数组。。。。。
	 * 
	 * 举例  {"id":1,"content":[{"company":"AAA"},{"company":"BBB"}]}
	 * @param str
	 */
	public static void convertJsonStrInDocument(String str,int numberOfTAB){
//		System.out.println("str: "+str);
		//如果以{开头就转换成   对象
		if (str.startsWith("{")) {
			convertJsonStrToObject(str,numberOfTAB);
		}else if(str.startsWith("[")){
			convertJsonStrToArray(str,numberOfTAB);
		}else{
			//如果不是 {开头也不是[开头， 则直接输出
			result=result+"\t"+str+"\r\n";
			System.out.println("\t"+str);
//			printStrWithTAB(str, numberOfTAB);
		}
	}
	
	/**
	 * 传入的字符串必须是以{  开头的， 通过这个方法把它转换成一个对象，并判断对象的每一个key  对应的value，是否需要转换成对象或者转换成数组
	 * @param str
	 */
	public static void convertJsonStrToObject(String str,int numberForTAB){
		System.out.println();
		//字符串是以{开头， 现在判断是否已}结尾， 不是的话，补全
		if (!str.endsWith("}")) {
			str=str+"}";
		}
		JSONObject obj=JSONObject.fromObject(str);
//		System.out.println();
		for (Object key:obj.keySet()) {
//			System.out.print("\t"+key+":\t");
			printStrWithTAB(key.toString(), numberForTAB);
			convertJsonStrInDocument(obj.get(key).toString(),numberForTAB+1);
//			System.out.println();
		}
		
	}
	/**
	 * 如果传入的字符串是以[开头就通过本方法转换成数组
	 * @param str
	 */
	public static void convertJsonStrToArray(String str,int numberForTAB) {
		if (!str.endsWith("]")) {
			str=str+"]";
		}
//		System.out.println();
		JSONArray array=JSONArray.fromObject(str);
		for (int i = 0; i < array.size(); i++) {
			//获取了数组里面的每一个对象
			//注意： 每一个对象也可能不是{} 组成的，例如： ["股票期权","16薪","扁平化管理","免费两餐"] 对于这样的，每一个逗号隔开的就是普通字符串。所以要先进行判断
			if (!(array.get(i).toString().startsWith("[")||array.get(i).toString().startsWith("{"))) {
				result=result+"\t"+array.get(i).toString();
				System.out.print("\t"+array.get(i).toString());
			}else{
				convertJsonStrInDocument(array.get(i).toString(),numberForTAB+1);
			}
		}
		result=result+"\r\n";
		System.out.println();
	}
	
	
	/**
	 * 为了更好的进行格式化输出，我需要知道本次输出的内容， 前面需要多少个tab键。
	 * 传入需要输出的内容， 以及告诉我，这个内容前面需要输出多少个tab键
	 * @param str
	 * @param numberOfTAB
	 */
	public static void printStrWithTAB(String str,int numberOfTAB){
		for (int i = 0; i < numberOfTAB; i++) {
			result=result+"\t";
			System.out.print("\t");
		}
		System.out.print(str);
		result=result+str;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 传入一个字符串把json 数据变成一个json对象 。
	 * @param str
	 * @return
	 */
	public static JSONArray converStringToJsonArray(String str){
		JSONArray jsonArray=JSONArray.fromObject(str);
		
		return jsonArray;
	}
	
	/**
	 * 集合转化成jsonArray
	 * @param str
	 * @return
	 */
	public static JSONArray converStringToJsonArray(List<String> str){
		JSONArray jsonArray=JSONArray.fromObject(str);
		
		return jsonArray;
	}
	

	/**
	 * 集合转化成jsonArray
	 * @param str
	 * @return
	 */
	public static JSONArray converStringToJsonArray(Map<String,String> strMap){
		JSONArray jsonArray=JSONArray.fromObject(strMap);
		return jsonArray;
	}
	
	
	/**
	 * 传入一个json字符串把json 数据变成一个json array 。
	 * @param str
	 * @return
	 */
	public static JSONObject converStringToObject(String str){
//		List<String> aList=jsonToList(str,String);
		JSONObject object=JSONObject.fromObject(str);
		return object;
	}
	
	/**
	 * 传入一个Map 数据变成一个json Object 。
	 * @param str
	 * @return
	 */
	public static JSONObject converStringToObject(Map<String,String> strMap){
//		List<String> aList=jsonToList(str,String);
		JSONObject object=JSONObject.fromObject(strMap);
		return object;

	}
	
	
	
	
	
	
	
	
	
	
	
	
}
