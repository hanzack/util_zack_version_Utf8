package testUtil;

import jsonProcessing.JsonHandleFiles;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

public class Test_Json {
	
	/**
	 * �����ַ�����ȡjsonArray
	 */
	@Test
	public void test_converStringToJsonArray(){
		String string="[{'companyId':4188,'positionName':'Java�з�����ʦ','positionType':'��˿���','workYear':'3-5��'},{'companyId':'abc'}]";
	JSONArray jsonArray=JsonHandleFiles.converStringToJsonArray(string);
	for (int i = 0; i < jsonArray.size(); i++) {
		System.out.println(jsonArray.get(i));//�ֳ����������ţ����д�ӡ������
		//����������ÿһ��������ת����һ��json����
		Object object=jsonArray.get(i);
		//ת���ɶ����Ժ� ��ӡ����������ԡ� 
		JSONObject obj=JSONObject.fromObject(object);
		System.out.println(obj.get("companyId"));
		
		//��ȻҲ��������ѭ���ķ�ʽ��ӡ���������Լ������ݣ�
		for (Object obj1:obj.keySet()) {
			System.out.println(obj1.toString());
		}
	
	}
//	System.out.println(jsonArray);
//		System.out.println(arr);
	}
	
	@Test
	public void test_converStringToObject(){
		String string="{'companyId':4188,'positionName':'Java�з�����ʦ','positionType':'��˿���','workYear':'3-5��'}";
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
