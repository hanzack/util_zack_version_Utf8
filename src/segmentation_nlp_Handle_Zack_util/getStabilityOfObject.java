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
	 * �����л����TF֮��ķִ��ļ����������ȶ��Լ��㲢����д��
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
	
	
	
	//�ڻ�ȡTF֮������ȶ��ԵĴ������ݹ�ʽ�����ȶ��ԡ� 
	/**
	 * @param fromPath      �����ļ��� Ϊ   ����term frequency��ע���ļ���W:\zackjob\hacker\nlp_workspace_files\termFrequencyFile_7\abc.txt
	 * @param toPath        ����ļ� Ϊ ����   TF��ע�� �Լ�   ����    �ȶ��� �÷ֱ�ע���ļ��� 
	 * @param splitmark   ע��˴���splitmark  �ǰ�list����ÿһ��Ԫ�ذ���ʲô���в�֡� �����Ƕ�һƪ���½��в�֡�
	 * ����� һƪ���²��д��list�е�splitmark�Ҳ���\r\n��     ���� listԪ�ؽ��в�ֵķ���Ĭ�ϲ��� \t   �û����Խ���ָ���� 
	 * ����˹/ns ����/v		1      ������һ�У� Ŀ���ǻ�ȡ   ����˹/ns ����/v  ��   1�� ����֮��Ĳ�ַ���Ϊtab
	 * 
	 */
	public static void getStabilityValueOfObjectAfTF(String fromPath, String toPath,String splitmark){
		String finalContent="";
		if (null==splitmark||"".equals(splitmark)) {
			splitmark="\t";
		}
		//�ָ���� ����Ϊ\r\n
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
			//���Լ���Ľ����
//			System.out.println(stabilityValueStr);
//			System.out.println("the value of fwi: "+fwi);
			//������ʾ�ڴ˴���
			finalContent=finalContent+key+"\t"+tfMap.get(key)+"\t"+stabilityValueStr+"\r\n";
		}
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContent, toPath);
	}
	
	
	/**
	 * ��ȡobject�ȶ��ԣ� ���ù�ʽ���м����
	 */
	public static double returnFwiValue(String key,Map<String, Integer> aMap){
		
		double fwiValue=0;
		//���Ȼ��ÿһ���ִ�
		String[] keyArr=key.split("\\s+");
		for(String aStr:keyArr){//ѭ��ÿһ���ִ�
			//Ȼ��ѭ��aMap�����keyֵ
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
	 * ���뼸���������ؽ��
	 */
	public static double returnStaValue(double fwi,double m, double fobject){
		
		double sValue=fobject/(fwi-(m-1)*fobject);
		return sValue;
	}


	
	
	
	
	
	
	
	
}
