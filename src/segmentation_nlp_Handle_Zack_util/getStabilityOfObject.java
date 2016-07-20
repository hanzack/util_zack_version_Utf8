package segmentation_nlp_Handle_Zack_util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class getStabilityOfObject {
		
	/**
	 * 对所有获得了TF之后的分词文件进行最后的稳定性计算并进行写出
	 */
	@Test
	public void AllFileStability_getStabilityValueOfObjectAfTF(){
		String fromPath="W:\\zackjob\\hacker\\nlp_workspace_files\\termFrequencyFile_7\\";
		String toPath="W:\\zackjob\\hacker\\nlp_workspace_files\\objectStability_8\\";
		String splitmark="\t";
		List<String> fileList=ReadAndWriteFileFromDirectory.getOnlyFilesPath(fromPath, "txt");
		for (String fileName:fileList) {
			String newFileName=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(fileName)+"_Stabi.txt";
			String topat=toPath+newFileName;
			getStabilityOfObject.getStabilityValueOfObjectAfTF(fileName, topat, splitmark);
		}
		System.out.println("finish writing Stability Value for all files");	
	}
	
	
	
	//在获取TF之后进行稳定性的处理。根据公式计算稳定性。 
	/**
	 * @param fromPath      输入文件， 为   带有term frequency标注的文件，W:\zackjob\hacker\nlp_workspace_files\termFrequencyFile_7\abc.txt
	 * @param toPath        输出文件 为 带有   TF标注， 以及   带有    稳定性 得分标注的文件。 
	 * @param splitmark   注意此处的splitmark  是把list里面每一个元素按照什么进行拆分。 而不是对一篇文章进行拆分。
	 * 在这里， 一篇文章拆分写入list中的splitmark我采用\r\n。     而对 list元素进行拆分的符号默认采用 \t   用户可以进行指定。 
	 * 俄罗斯/ns 质疑/v		1      对于这一行， 目的是获取   俄罗斯/ns 质疑/v  和   1， 他们之间的拆分符号为tab
	 * 
	 */
	public static void getStabilityValueOfObjectAfTF(String fromPath, String toPath,String splitmark){
		String finalContent="";
		if (null==splitmark||"".equals(splitmark)) {
			splitmark="\t";
		}
		//分割符号 设置为\r\n
		List<String> wholeList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPath, "\r\n");
		Map<String, Integer> tfMap=new HashMap<String, Integer>();
		for (String eaLine:wholeList) {
			String[] tempList=eaLine.split(splitmark);
			int tfValue=Integer.parseInt(tempList[1]);
//			System.out.println(tempList[0]+"   :   "+tfValue);
			tfMap.put(tempList[0], tfValue);
		}
		Set<String> keyset1=tfMap.keySet();
		for (String key:keyset1) {
			String[] keyArr=key.split("\\s+");
			double fobject=tfMap.get(key)*1.0;
			double m=keyArr.length;
			double fwi=returnFwiValue(key, tfMap);
//			System.out.println(m);
			double stabilityValue=returnStaValue(fwi, m, fobject);
			DecimalFormat df = new DecimalFormat("0.000");  
			String stabilityValueStr=df.format(stabilityValue);
			//测试计算的结果。
//			System.out.println(stabilityValueStr);
//			System.out.println("the value of fwi: "+fwi);
			//内容显示在此处：
			finalContent=finalContent+key+"\t"+tfMap.get(key)+"\t"+stabilityValueStr+"\r\n";
		}
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContent, toPath);
	}
	
	
	/**
	 * 求取object稳定性， 所用公式的中间产物
	 */
	public static double returnFwiValue(String key,Map<String, Integer> aMap){
		
		double fwiValue=0;
		//首先获得每一个分词
		String[] keyArr=key.split("\\s+");
		for(String aStr:keyArr){//循环每一个分词
			//然后循环aMap里面的key值
			Set<String> keyset1=aMap.keySet();
			for (String eachKey:keyset1) {
				if (eachKey.equals(aStr)||eachKey.contains(aStr+" ")||eachKey.contains(" "+aStr)) {
//					System.out.println(aStr+"---"+eachKey);
					fwiValue=fwiValue+aMap.get(eachKey)*1.0;
				}
			}
		}
		
		return fwiValue;
	}
	
	
	/**
	 * 传入几个参数返回结果
	 */
	public static double returnStaValue(double fwi,double m, double fobject){
		
		double sValue=fobject/(fwi-(m-1)*fobject);
		return sValue;
	}


	
	
	
	
	
	
	
	
}
