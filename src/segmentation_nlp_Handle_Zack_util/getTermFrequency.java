package segmentation_nlp_Handle_Zack_util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import list_Set_Map_Handle_Zack_util.mapHandleZackUtil;

import org.omg.CORBA.portable.ValueBase;

import Primary_data_type_zack_util.String_handle_Zack_Util;
import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class getTermFrequency {

	
	
	
	// ����汾������һ�����Խ����ļ�·����  ����һ�����Խ��ܴ��word��list
				/**
				 * ����һ�����ʣ�����һ��List<String>���������   ���������list�г��ֵĴ����� ע��һ��element�п��ܰ�������ʣ������ǽ���������
				 * TF = ���ʳ��ֵĴ���
				 * @param fromPath   W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\abc.txt
				 * @param word      ĳһ����
				 * @param splitMark    ��������£���������ָ���Ž��зָ����������ȥ
				 * @return
				 */
				public static int getWordTFFromList(List<String> wordList, String word){
					word=word.trim();
					int wordOcc=0;
					if (wordList.size()==0) {
						return 0;
					}
					
					List<String> contArr=wordList;
					for (String aWord:contArr) {
						if (aWord.contains(word+" ")||aWord.contains(" "+word)||aWord.equals(word)||aWord.contains(word)) {
							wordOcc++;
						}
					}
					return wordOcc;
				}
				
			
		
		
		
		
		
		// ����汾������һ�����Խ����ļ�·����  ����һ�����Խ��ܴ��word��list
				/**
				 * ����һ�����ʣ�����һ�����µĵ�ַ���������   ������ʵ�TF = ���ʳ��ֵĴ���
				 * @param fromPath   W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\abc.txt
				 * @param word      ĳһ����
				 * @param splitMark    ��������£���������ָ���Ž��зָ����������ȥ
				 * @return
				 */
				public static int getWordTFFromArticle(String fromPath, String word,String splitMark){
					word=word.trim();
					int wordOcc=0;
					if (null==splitMark||"".equals(splitMark)) {
						splitMark="\r\n";
					}
					if (String_handle_Zack_Util.isEmpty(fromPath)||String_handle_Zack_Util.isEmpty(word)) {
						return 0;
					}
					String content=ReadAndWriteFileFromDirectory.readFileAndReturnContent(fromPath);
					String []contArr=content.split(splitMark);
					for (String aWord:contArr) {
//						aWord=aWord.replaceAll("\\s+", "");
						if (aWord.contains(word+" ")||aWord.contains(" "+word)||aWord.equals(word)||aWord.contains(word)) {
							wordOcc++;
						}
					}
					return wordOcc;
				}
				
		
		
		
		
		
		// ����汾������һ�����Խ����ļ�·����  ����һ�����Խ��ܴ��word��list
			/**
			 * ����һ�����ʣ�����һ�����µĵ�ַ���������   ������ʵ�TF =  (����Ƶ��/�����ܴ���)
			 * ����list��˵�� �ܴ����� ÿ��listԪ�����涼���ܺ��ж�����Ҫ������� 
			 * @param fromPath   W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\abc.txt
			 * @param word      ĳһ����
			 * @param splitMark    ��������£���������ָ���Ž��зָ����������ȥ
			 * @return
			 */
			public static double getWordTFDivideTotWordsFromArticle(String fromPath, String word,String splitMark){
				word=word.trim();
				int wordOcc=0;
				if (null==splitMark||"".equals(splitMark)) {
					splitMark="\r\n";
				}
				if (String_handle_Zack_Util.isEmpty(fromPath)||String_handle_Zack_Util.isEmpty(word)) {
					return 0.0;
				}
				String content=ReadAndWriteFileFromDirectory.readFileAndReturnContent(fromPath);
				String []contArr=content.split(splitMark);
				for (String aWord:contArr) {
//					aWord=aWord.replaceAll("\\s+", "");
					if (aWord.contains(word+" ")||aWord.contains(" "+word)||aWord.equals(word)||aWord.contains(word)) {
						wordOcc++;
					}
				}
//				System.out.println(wordOcc+" "+contArr.length);
				double Tf=(wordOcc*1.0)/(contArr.length*1.0);
				DecimalFormat dfDecimalFormat=new DecimalFormat("0.0000");
				Tf=Double.parseDouble(dfDecimalFormat.format(Tf));
				return Tf;
			}
			
			
		
		
	// ����汾������һ�����Խ����ļ�·����  ����һ�����Խ��ܴ��word��list
			/**
			 * ����һ�����ʣ�����һ��list<String>���������   ������ʵ�TF =  (����Ƶ��/list���ܴ�����)
			 *  ����list��˵�� �ܴ����� ÿ��listԪ�����涼���ܺ��ж�����Ҫ������� 
			 * @param fromPath   W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\abc.txt
			 * @param word      ĳһ����
			 * @param splitMark    ��������£���������ָ���Ž��зָ����������ȥ
			 * @return
			 */
			public static double getWordTFDivideTotWordsFromArticle(List<String> wordList,String word){
				int totalWordNum=0;
				word=word.trim();
				int wordOcc=0;
				if (wordList.size()==0) {
					return 0.00;
				}
				List<String> contArr=wordList;
				for (String aWord:contArr) {
					if (aWord.contains(word+" ")||aWord.contains(" "+word)||aWord.equals(word)||aWord.contains(word)) {
						wordOcc++;
					}
					String []arr1=aWord.split(" ");
					totalWordNum=totalWordNum+arr1.length;  //��ȡ�����ܴ����� 
				}
						
				double Tf=(wordOcc*1.0)/(totalWordNum*1.0);
				DecimalFormat dfDecimalFormat=new DecimalFormat("0.000");
				Tf=Double.parseDouble(dfDecimalFormat.format(Tf));
				return Tf;
			}

	
	
	
	// ����汾������һ�����Խ����ļ�·����  ����һ�����Խ��ܴ��word��list
	/** 
	 * * ����һ��wordList�� (ÿ��Ԫ�ص� frequency / list������ܴ���)��    ����һ��Ԫ��Ϊ    ����  ����  ������3 �ʣ�    ������list������ֵ��ܴ��� 100 ��ô����һ�л���
	 *     ������  ����	0.03���� ����ÿһ�� д��map�н��� ����map�� ����ָ���Ƿ���value���������Ժ���д�롣 
	 *  1.dog    2. dog and cat   3.dog            ����dog��������3��
	 *  ����һ���ļ��� Ȼ�����һ���ļ�������д�д�Ƶ�� 
	 * ����һ�������ִʺ���ļ���ȥ���˵��ִ���֮����ļ��� ע��Ҫ��ȥ��֮ǰ.   ע�����ڶ���һЩ  �����Ĵʣ����п��ܱ����������������У���ʱ��ҲҪ����Ϊһ��
	 * @param fromPath    ���ĸ��ļ����д���
	 * @param toPath     d://abc.txt  ��������������д�뵽�ĸ��ļ���
	 */
	public static Map<String, Double> getTFDivideTotWordsReturnMap(List<String> wordList1,boolean ifSortByTF){
		int TotalWords=0;
		 DecimalFormat df = new DecimalFormat("0.00000");  
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=wordList1;
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//���key�������������м�1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
			String abc[]=eachWord.split(" ");
			TotalWords=TotalWords+abc.length;
		}
		// �Ȼ�Ҫ�����������ۼӲ���¼������һ��map����ȥ������ֱ���޸�ԭ����map
		Map<String, Double> newMap=new HashMap<String, Double>();
		Set<String> keyset1=termMap.keySet();
		//��ȡkeyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			
			newMap.put(keyoutside,Double.parseDouble(df.format(keyF*1.0/TotalWords*1.0)));
		}
			newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
			
		return newMap;
	}
	
	
	
	
	// ����汾������һ�����Խ����ļ�·����  ����һ�����Խ��ܴ��word��list
	/** 
	 * * ����һ���ļ��� ��ȡ�ļ�(ÿһ�е� frequency / �ļ��ܴ���)��    ����һ���ļ���һ��Ϊ    ����  ����  ������3 �ʣ�    ���ļ��ܴ��� 100 ��ô����һ�л���
	 *     ������  ����	0.03���� ����ÿһ�� д��map�н��� ����map�� ����ָ���Ƿ���value���������Ժ���д�롣 
	 *  1.dog    2. dog and cat   3.dog            ����dog��������3��
	 *  ����һ���ļ��� Ȼ�����һ���ļ�������д�д�Ƶ�� 
	 * ����һ�������ִʺ���ļ���ȥ���˵��ִ���֮����ļ��� ע��Ҫ��ȥ��֮ǰ.   ע�����ڶ���һЩ  �����Ĵʣ����п��ܱ����������������У���ʱ��ҲҪ����Ϊһ��
	 * @param fromPath    ���ĸ��ļ����д���
	 * @param toPath     d://abc.txt  ��������������д�뵽�ĸ��ļ���
	 */
	public static Map<String, Double> getTFDivideTotWordsReturnMap(String fromPath,boolean ifSortByTF){
		int TotalWords=0;
		 DecimalFormat df = new DecimalFormat("0.00000");  
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPath, "\r\n");
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//���key�������������м�1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
			String abc[]=eachWord.split(" ");
			TotalWords=TotalWords+abc.length;
		}
		// �Ȼ�Ҫ�����������ۼӲ���¼������һ��map����ȥ������ֱ���޸�ԭ����map
		Map<String, Double> newMap=new HashMap<String, Double>();
		Set<String> keyset1=termMap.keySet();
		//��ȡkeyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			
			newMap.put(keyoutside,Double.parseDouble(df.format(keyF*1.0/TotalWords*1.0)));
		}
			newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
			
		return newMap;
	}
	
	
	
	
	// ����汾������һ�����Խ����ļ�·����  ����һ�����Խ��ܴ��word��list
	/** 
	 * * ����һ��wordList�� (ÿ��Ԫ�ص� frequency / list������ܴ���)��    ����һ��Ԫ��Ϊ    ����  ����  ������3 �ʣ�    ������list������ֵ��ܴ��� 100 ��ô����һ�л���
	 *     ������  ����	0.03���� ����ÿһ�� д��ָ�����ļ��У� ����ָ���Ƿ���value���������Ժ���д�롣 
	 *  1.dog    2. dog and cat   3.dog            ����dog��������3��
	 *  ����һ���ļ��� Ȼ�����һ���ļ�������д�д�Ƶ�� 
	 * ����һ�������ִʺ���ļ���ȥ���˵��ִ���֮����ļ��� ע��Ҫ��ȥ��֮ǰ.   ע�����ڶ���һЩ  �����Ĵʣ����п��ܱ����������������У���ʱ��ҲҪ����Ϊһ��
	 * @param fromPath    ���ĸ��ļ����д���
	 * @param toPath     d://abc.txt  ��������������д�뵽�ĸ��ļ���
	 */
	public static boolean getTFDivideTotWordsWrtFile(List<String> wordList1,String toPath,boolean ifSortByTF){
		int TotalWords=0;
		 DecimalFormat df = new DecimalFormat("0.00000");  
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=wordList1;
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//���key�������������м�1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
			String abc[]=eachWord.split(" ");
			TotalWords=TotalWords+abc.length;
		}
		// �Ȼ�Ҫ�����������ۼӲ���¼������һ��map����ȥ������ֱ���޸�ԭ����map
		Map<String, Double> newMap=new HashMap<String, Double>();
		Set<String> keyset1=termMap.keySet();
		//��ȡkeyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			
			newMap.put(keyoutside,Double.parseDouble(df.format(keyF*1.0/TotalWords*1.0)));
		}
		String finalContentString="";
		
		if (ifSortByTF) {
			newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
		}
//		��ȡmap�������
		Set<String> setKey=newMap.keySet();
		for (String key1:setKey) {
			finalContentString=finalContentString+key1+"\t"+newMap.get(key1)+"\r\n";
//			System.out.println(key1+"\t\t"+newMap.get(key1));
		}
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContentString, toPath);
//		System.out.println("���´����� "+TotalWords);
		return true;
	}
	
	
	
	
	
	// ����汾������һ�����Խ����ļ�·����  ����һ�����Խ��ܴ��word��list
	/** 
	 * * ����һ���ļ��� ��ȡ�ļ�(ÿһ�е� frequency / �ļ��ܴ���)��    ����һ���ļ���һ��Ϊ    ����  ����  ������3 �ʣ�    ���ļ��ܴ��� 100 ��ô����һ�л���
	 *     ������  ����	0.03���� ����ÿһ�� д��ָ�����ļ��У� ����ָ���Ƿ���value���������Ժ���д�롣 
	 *  1.dog    2. dog and cat   3.dog            ����dog��������3��
	 *  ����һ���ļ��� Ȼ�����һ���ļ�������д�д�Ƶ�� 
	 * ����һ�������ִʺ���ļ���ȥ���˵��ִ���֮����ļ��� ע��Ҫ��ȥ��֮ǰ.   ע�����ڶ���һЩ  �����Ĵʣ����п��ܱ����������������У���ʱ��ҲҪ����Ϊһ��
	 * @param fromPath    ���ĸ��ļ����д���
	 * @param toPath     d://abc.txt  ��������������д�뵽�ĸ��ļ���
	 */
	public static boolean getTFDivideTotWordsWrtFile(String fromPath,String toPath,boolean ifSortByTF){
		int TotalWords=0;
		 DecimalFormat df = new DecimalFormat("0.00000");  
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPath, "\r\n");
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//���key�������������м�1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
			String abc[]=eachWord.split(" ");
			TotalWords=TotalWords+abc.length;
		}
		// �Ȼ�Ҫ�����������ۼӲ���¼������һ��map����ȥ������ֱ���޸�ԭ����map
		Map<String, Double> newMap=new HashMap<String, Double>();
		Set<String> keyset1=termMap.keySet();
		//��ȡkeyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			
			newMap.put(keyoutside,Double.parseDouble(df.format(keyF*1.0/TotalWords*1.0)));
		}
		String finalContentString="";
		
		if (ifSortByTF) {
			newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
		}
//		��ȡmap�������
		Set<String> setKey=newMap.keySet();
		for (String key1:setKey) {
			finalContentString=finalContentString+key1+"\t"+newMap.get(key1)+"\r\n";
//			System.out.println(key1+"\t\t"+newMap.get(key1));
		}
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContentString, toPath);
//		System.out.println("���´����� "+TotalWords);
		return true;
	}
	
	
	
	
	
	// ����汾������һ�����Խ����ļ�·����  ����һ�����Խ��ܴ��word��list
	/** 
	 * *  ����ļ����յ���List<String>  �����ÿһ��Ԫ�ض������TF���� 
	 * ��һ���ļ� ȥ���ظ���ͬʱ���term frequency���Ұѽ��д���ļ��� ����ָ���Ƿ���   Tf ����
	 *  1.dog    2. dog and cat   3.dog            ����dog��������3��
	 *  ����һ���ļ��� Ȼ�����һ���ļ�������д�д�Ƶ�� 
	 * ����һ�������ִʺ���ļ���ȥ���˵��ִ���֮����ļ��� ע��Ҫ��ȥ��֮ǰ.   ע�����ڶ���һЩ  �����Ĵʣ����п��ܱ����������������У���ʱ��ҲҪ����Ϊһ��
	 * @param fromPath    ���ĸ��ļ����д���
	 * @param toPath     d://abc.txt  ��������������д�뵽�ĸ��ļ���
	 */
	public static boolean getTFComplexWrtFile(List<String> wordList,String toPath,boolean ifSortByTF){
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=wordList;
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//���key�������������м�1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
		}
		// �Ȼ�Ҫ�����������ۼӲ���¼������һ��map����ȥ������ֱ���޸�ԭ����map
		Map<String, Integer> newMap=new HashMap<String, Integer>();
		Set<String> keyset1=termMap.keySet();
		//��ȡkeyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			newMap.put(keyoutside, keyF);
		}
		String finalContentString="";
		
		if (ifSortByTF) {
			newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
		}
//		��ȡmap�������
		Set<String> setKey=newMap.keySet();
		for (String key1:setKey) {
			finalContentString=finalContentString+key1+"\t"+newMap.get(key1)+"\r\n";
//			System.out.println(key1+"\t\t"+newMap.get(key1));
		}
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContentString, toPath);
		return true;
	}
	
	// ����汾������һ�����Խ����ļ�·����  ����һ�����Խ��ܴ��word��list
	/** 
	 * * ��һ���ļ� ȥ���ظ���ͬʱ���term frequency���Ұѽ��д���ļ��� ����ָ���Ƿ���   Tf ����
	 *  1.dog    2. dog and cat   3.dog            ����dog��������3��
	 *  ����һ���ļ��� Ȼ�����һ���ļ�������д�д�Ƶ�� 
	 * ����һ�������ִʺ���ļ���ȥ���˵��ִ���֮����ļ��� ע��Ҫ��ȥ��֮ǰ.   ע�����ڶ���һЩ  �����Ĵʣ����п��ܱ����������������У���ʱ��ҲҪ����Ϊһ��
	 * @param fromPath    ���ĸ��ļ����д���
	 * @param toPath     d://abc.txt  ��������������д�뵽�ĸ��ļ���
	 */
	public static boolean getTFComplexWrtFile(String fromPath,String toPath,boolean ifSortByTF){
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPath, "\r\n");
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//���key�������������м�1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
		}
		// �Ȼ�Ҫ�����������ۼӲ���¼������һ��map����ȥ������ֱ���޸�ԭ����map
		Map<String, Integer> newMap=new HashMap<String, Integer>();
		Set<String> keyset1=termMap.keySet();
		//��ȡkeyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			newMap.put(keyoutside, keyF);
		}
		String finalContentString="";
		
		if (ifSortByTF) {
			newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
		}
//		��ȡmap�������
		Set<String> setKey=newMap.keySet();
		for (String key1:setKey) {
			finalContentString=finalContentString+key1+"\t"+newMap.get(key1)+"\r\n";
//			System.out.println(key1+"\t\t"+newMap.get(key1));
		}
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContentString, toPath);
		return true;
	}
	
	
	
	
	// ����汾������һ�����Խ����ļ�·����  ����һ�����Խ��ܴ��word��list
	/** 
	 * ���� ���ʵ�List
	 * * ��һ���ļ� ȥ���ظ���ͬʱ���term frequency���Ұѽ��д��map�����أ� �����趨���ص�map�Ƿ���а���TF��ֵ��������
	 *  1.dog    2. dog and cat   3.dog            ����dog��������3��
	 *  ����һ���ļ��� Ȼ�����һ���ļ�������д�д�Ƶ�� 
	 * ����һ�������ִʺ���ļ���ȥ���˵��ִ���֮����ļ��� ע��Ҫ��ȥ��֮ǰ.   ע�����ڶ���һЩ  �����Ĵʣ����п��ܱ����������������У���ʱ��ҲҪ����Ϊһ��
	 * @param wordList   ����һ������˺ܶ�word ��list�� ����ת��map ����ÿһ��Ԫ�س��ֵĴ����� 
	 * @param ifSortByTF     �Ƿ�Է��ص�map����������
	 * @return
	 */
	public static Map<String, Integer> getTFComplexReturnMap(List<String> wordList,boolean ifSortByTF){
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=wordList;
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//���key�������������м�1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
		}
		// �Ȼ�Ҫ�����������ۼӲ���¼������һ��map����ȥ������ֱ���޸�ԭ����map
		Map<String, Integer> newMap=new HashMap<String, Integer>();
		Set<String> keyset1=termMap.keySet();
		//��ȡkeyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			newMap.put(keyoutside, keyF);
		}
		String finalContentString="";
//		��ȡmap�������
//		Set<String> setKey=newMap.keySet();
//		for (String key1:setKey) {
//			finalContentString=finalContentString+key1+"\t"+newMap.get(key1)+"\r\n";
////			System.out.println(key1+"\t\t"+newMap.get(key1));
//		}
//		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContentString, toPath);
		newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
		
		return newMap;
	}
	
	
	
	// ����汾������һ�����Խ����ļ�·����  ����һ�����Խ��ܴ��word��list
	/** 
	 * * ��һ���ļ� ȥ���ظ���ͬʱ���term frequency���Ұѽ��д��map�����أ� �����趨���ص�map�Ƿ���а���TF��ֵ��������
	 *  1.dog    2. dog and cat   3.dog            ����dog��������3��
	 *  ����һ���ļ��� Ȼ�����һ���ļ�������д�д�Ƶ�� 
	 * ����һ�������ִʺ���ļ���ȥ���˵��ִ���֮����ļ��� ע��Ҫ��ȥ��֮ǰ.   ע�����ڶ���һЩ  �����Ĵʣ����п��ܱ����������������У���ʱ��ҲҪ����Ϊһ��
	 * @param fromPath   ���ĸ��ļ����д���    d://abc.txt 
	 * @param ifSortByTF     �Ƿ�Է��ص�map����������
	 * @return
	 */
	public static Map<String, Integer> getTFComplexReturnMap(String fromPath,boolean ifSortByTF){
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPath, "\r\n");
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//���key�������������м�1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
		}
		// �Ȼ�Ҫ�����������ۼӲ���¼������һ��map����ȥ������ֱ���޸�ԭ����map
		Map<String, Integer> newMap=new HashMap<String, Integer>();
		Set<String> keyset1=termMap.keySet();
		//��ȡkeyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			newMap.put(keyoutside, keyF);
		}
		String finalContentString="";
//		��ȡmap�������
//		Set<String> setKey=newMap.keySet();
//		for (String key1:setKey) {
//			finalContentString=finalContentString+key1+"\t"+newMap.get(key1)+"\r\n";
////			System.out.println(key1+"\t\t"+newMap.get(key1));
//		}
//		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContentString, toPath);
		newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
		
		return newMap;
	}
	
	
	
	
	
	
	
	
	
	/**  �������һ��word  ��һ��map�� �ҳ�map��key�У��������word��ֵ������ȡ���key��Ӧ��value���������ۼ��Ժ󷵻ء�mapʢ�ŵģ� ("�й�   ��ѧ",3) 3 ��ʾ�й�   ��ѧ���ֵĴ�����
	 *     ("�й�   ��Ů"��2)   �����������word���й���  �򷵻�5
	 * @param word
	 * @param strMap
	 * @param keySplitMark   ��������map�е�key  �п����Ǹ����飬 ����Ҫ��ʲô�ָ������зָ �������null��ʾ���뻮�֣���Ĭ��\r\n
	 *        �������""   ��ʾ����Ĭ�ϲ�֣� Ĭ�ϰ���һ�����߶���ո���в��
	 */
	public static int containsWordInMapsKey(String word, Map<String, Integer> strMap, String keySplitMark){
		int wordTimes=0;
		if ("".equals(keySplitMark)) {
			keySplitMark="\\s+";//��ʾ����һ�����߶���ո���в��
		}
		
		if (null==keySplitMark) {
			keySplitMark="\r\n";//��ʾ����֣� ֻ��ֳ����Լ������߰��в��
		}
		Set<String> keyset=strMap.keySet();
		for (String akey:keyset) {
			//ʹ��������ʽ�����������⣬ ��������ʽȥƥ��word����ƥ���Ͼ��ۼ�
			if(akey.contains(word+" ")||akey.contains(" "+word)||akey.equals(word)) {
				wordTimes=wordTimes+strMap.get(akey);
			}
		}
		return wordTimes;
	}
	
	
	
	/**
	 * 
	 * 
	 * ��һ���ļ� ȥ���ظ���ͬʱ�����ͬ�Ĵ���
	 *  ����           1.dog    2. dog and cat   3.dog            ����dogֻ��������2��
	 * ����һ�������ִʺ���ļ���ȥ���˵��ִ���֮����ļ��� ע��Ҫ��ȥ��֮ǰ.   ע�����ڶ���һЩ  �����Ĵʣ����п��ܱ����������������У�����������������������
	 * @param fromPath   
	 */
	public static void deletDuplicateAndGetTFSimple(String fromPath){
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPath, "\r\n");
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//���key�������������м�1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
		}
		Set<String> setKey=termMap.keySet();
		for (String key1:setKey) {
			System.out.println(key1+"\t\t"+termMap.get(key1));
		}
		
	}
	
	
}
