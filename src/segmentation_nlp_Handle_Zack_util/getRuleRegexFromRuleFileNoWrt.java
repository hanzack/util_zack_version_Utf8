package segmentation_nlp_Handle_Zack_util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class getRuleRegexFromRuleFileNoWrt {

	
	/**例如规则   n n  就会被组装成  [/u4e00-/u9fa5a-zA-Z0-9]+/n [/u4e00-/u9fa5a-zA-Z0-9]+/n
	 /* * 传入一个规则文件，   对每一行规则 组合成一个正则表达式，用来匹配符合规则的文字
	 * @param inputPath
	 * @return   List<String> 
	 */
	public static List<String> getRulesRegexFromDirectory(String inputPath){
		//把规则写成正则表达式的形式。   高/a 美/b    规则形式类似于这种。 
		 String regexString="[/u4e00-/u9fa5a-zA-Z0-9]*";
		 List<String> result=new ArrayList<String>();
		 File file=new File(inputPath);
		 FileReader fReader=null;
		 BufferedReader bReader=null;
		 try {
			fReader=new FileReader(file);
			bReader=new BufferedReader(fReader);
			String temp="";
			 while ((temp=bReader.readLine())!=null) {//读取每一行的规则
				 //根据空格把规则进行拆分
				 String str="";
				String[] strArray=temp.split(" ");
				for (String rule:strArray) {
					// 把规则写成正则表达式的形式。
					str+=(regexString+"/"+rule+" ");
				}
				// 去掉最后一个空格
				str=str.substring(0,str.length()-1);
				//把规则放入到result list中去。 
				result.add(str);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{//接下来对reader和相关流进行关闭
			try {
			if (bReader!=null) {bReader.close();}if (fReader!=null) {fReader.close();}}
			catch (Exception e2) {e2.printStackTrace();}// TODO: handle exception
		}
		 return result;
	 }
	
}
