package testUtil;

import jsonProcessing.JsonHandleFiles;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class Test_Json {
	
	/**
	 * 传入字符串获取jsonArray
	 */
	@Test
	public void test_converStringToJsonArray(){
		String string="[{'companyId':4188,'positionName':'Java研发工程师','positionType':'后端开发','workYear':'3-5年'},{'companyId':'abc'}]";
	JSONArray jsonArray=JsonHandleFiles.converStringToJsonArray(string);
	for (int i = 0; i < jsonArray.size(); i++) {
		System.out.println(jsonArray.get(i));//分成两个大括号，进行打印出来。
		//接下来，把每一个大括号转化成一个json对象
		Object object=jsonArray.get(i);
		//转化成对象以后， 打印出里面的属性。 
		JSONObject obj=JSONObject.fromObject(object);
		System.out.println(obj.get("companyId"));
		
		//当然也可以利用循环的方式打印出所有属性及其内容！
		for (Object obj1:obj.keySet()) {
			System.out.println(obj1.toString());
		}
	
	}
//	System.out.println(jsonArray);
//		System.out.println(arr);
	}
	
	@Test
	public void test_converStringToObject(){
		String string="{'companyId':4188,'positionName':'Java研发工程师','positionType':'后端开发','workYear':'3-5年'}";
		JSONObject jsonObj=JsonHandleFiles.converStringToObject(string);
		
		System.out.println(jsonObj);
	}
	
	
	
	@Test
	public void test_convertJsonFileInDocument(){
		String filePathString="W:\\zackjob\\hacker\\web_Bug\\testFile\\lagou_Java\\lagou_201604201950130280.json";
//		JsonHandleFiles.convertJsonFileInDocument(filePathString, "");
	}

	
	
	@Test
	public void test_convertJsonStrInDocument(){
		String fileStr="{'id':1,'content':[{'company':'AA'},{'company':'BBB'},{'company':'CCC'}],'time':'1203'}";
		JsonHandleFiles.convertJsonStrInDocument(fileStr,0);
		
	}
	
	
	
}
