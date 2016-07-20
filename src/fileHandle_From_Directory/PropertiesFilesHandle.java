package fileHandle_From_Directory;

import java.io.InputStream;
import java.util.Properties;

import Primary_data_type_zack_util.String_handle_Zack_Util;

public class PropertiesFilesHandle {


	
	/**传入properties地址，获取properties对象
	 * 传入一个properties 的文件的地址， 例如项目中有个 config 文件夹， 里面有个 searchProject.properties文件。 
	 * 然后返回一个properties对象
	 * @param fileName：   searchProject.properties

	 * 内部方法，获取Properties对象
	 * @param fileName    :  注意  config文件夹， 是一个sourceFolder类型的， 假如里面有一个abc.properties
	 *    则  fileName直接输入   abc.properties
	 * @return
	 */
	public static Properties loadConfig(String fileName){
		Properties properties = new Properties();
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			if(classLoader == null){
				classLoader = PropertiesFilesHandle.class.getClassLoader();
			}
			InputStream is = classLoader.getResourceAsStream(fileName);
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	
	
	
	
	
	
	/**
	 * 传入一个properties 的文件的地址， 例如项目中有个 config 文件夹， 里面有个 searchProject.properties文件。 
	 * 我们需要获取这个文件里   url：  jdbc.... 这一行中url后面的地址。 那么。
	 * 我们需要传入的参数：             url,     defaultValue是指，如果不存在这个url则使用我指定的地址，  fileName使用 searchProject.properties
	 * @param params   properties文件中的参数例如   url,     username,   password
	 * @param defaultValue:   如果在properties中不存在这个参数，  则使用我指定的，例如如果url参数不存在，则我传入一个   www.baidu.com作为我要使用的url地址。
	 * @param fileName       是一个sourceFolder类型的， 假如里面有一个abc.properties
	 *    则  fileName直接输入   abc.properties   输入其他会报错。
	 * @return
	 */
	public static String getConfigParam(String params,String defaultValue,String fileName){
		String result = "";
		if(String_handle_Zack_Util.isEmpty(fileName) || String_handle_Zack_Util.isEmpty(params)){
			return result;
		}
		try {
			Properties properties = loadConfig(fileName);
			// 例如  param=url   则获取了文件中url的地址。
			result = properties.getProperty(params);
			if(String_handle_Zack_Util.isEmpty(result)){
				result = defaultValue;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
