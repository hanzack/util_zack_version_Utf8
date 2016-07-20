package testUtil;

import java.util.Properties;

import org.junit.Test;

import fileHandle_From_Directory.PropertiesFilesHandle;

public class Test_PropertiesFilesHandle {

	@Test
	public void test_loadConfig(){
		String fileName="SearchProject.properties";
		
		Properties pro=PropertiesFilesHandle.loadConfig(fileName);
		System.out.println(pro.toString());
	}
	
	
	/**
	 *   获取一个parameter 例如 url的地址
	 */
	@Test
	public void test_properties(){
		String fileName="SearchProject.properties";
	String urlAddress=PropertiesFilesHandle.getConfigParam("url", "", fileName);
	System.out.println(urlAddress);
	}
}
