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

	
	
	
	// 这个版本有两个一个可以接受文件路径，  另外一个可以接受存放word的list
				/**
				 * 传入一个单词，传入一个List<String>，返回这个   这个单词在list中出现的次数。 注意一个element中可能包含这个词，而不是仅仅等于它
				 * TF = 单词出现的次数
				 * @param fromPath   W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\abc.txt
				 * @param word      某一个词
				 * @param splitMark    对这个文章，按照这个分割符号进行分割放入数组中去
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
				
			
		
		
		
		
		
		// 这个版本有两个一个可以接受文件路径，  另外一个可以接受存放word的list
				/**
				 * 传入一个单词，传入一个文章的地址，返回这个   这个单词的TF = 单词出现的次数
				 * @param fromPath   W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\abc.txt
				 * @param word      某一个词
				 * @param splitMark    对这个文章，按照这个分割符号进行分割放入数组中去
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
				
		
		
		
		
		
		// 这个版本有两个一个可以接受文件路径，  另外一个可以接受存放word的list
			/**
			 * 传入一个单词，传入一个文章的地址，返回这个   这个单词的TF =  (单词频率/文章总词数)
			 * 对于list来说， 总词数： 每个list元素里面都可能含有多个词语。要算进来。 
			 * @param fromPath   W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\abc.txt
			 * @param word      某一个词
			 * @param splitMark    对这个文章，按照这个分割符号进行分割放入数组中去
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
			
			
		
		
	// 这个版本有两个一个可以接受文件路径，  另外一个可以接受存放word的list
			/**
			 * 传入一个单词，传入一个list<String>，返回这个   这个单词的TF =  (单词频率/list的总次数。)
			 *  对于list来说， 总词数： 每个list元素里面都可能含有多个词语。要算进来。 
			 * @param fromPath   W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\abc.txt
			 * @param word      某一个词
			 * @param splitMark    对这个文章，按照这个分割符号进行分割放入数组中去
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
					totalWordNum=totalWordNum+arr1.length;  //获取文章总词数。 
				}
						
				double Tf=(wordOcc*1.0)/(totalWordNum*1.0);
				DecimalFormat dfDecimalFormat=new DecimalFormat("0.000");
				Tf=Double.parseDouble(dfDecimalFormat.format(Tf));
				return Tf;
			}

	
	
	
	// 这个版本有两个一个可以接受文件路径，  另外一个可以接受存放word的list
	/** 
	 * * 传入一个wordList， (每个元素的 frequency / list里面的总词数)。    例如一个元素为    他是  好人  共出现3 词，    而整个list里面出现的总词数 100 那么，这一行会变成
	 *     “他是  好人	0.03”。 并把每一行 写入map中进行 返回map， 可以指定是否按照value进行排序以后再写入。 
	 *  1.dog    2. dog and cat   3.dog            这里dog算作出现3次
	 *  传入一个文件， 然后输出一个文件，后面写有词频。 
	 * 传入一个经过分词后的文件，去掉了单字词组之后的文件， 注意要在去重之前.   注意由于对于一些  单个的词，他有可能被包含在其他词组中，这时候也要计算为一次
	 * @param fromPath    对哪个文件进行处理
	 * @param toPath     d://abc.txt  把数出来的数组写入到哪个文件中
	 */
	public static Map<String, Double> getTFDivideTotWordsReturnMap(List<String> wordList1,boolean ifSortByTF){
		int TotalWords=0;
		 DecimalFormat df = new DecimalFormat("0.00000");  
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=wordList1;
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//如果key里面包括他则进行加1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
			String abc[]=eachWord.split(" ");
			TotalWords=TotalWords+abc.length;
		}
		// 等会要把数出来的累加并记录到另外一个map里面去，而不直接修改原来的map
		Map<String, Double> newMap=new HashMap<String, Double>();
		Set<String> keyset1=termMap.keySet();
		//获取keyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			
			newMap.put(keyoutside,Double.parseDouble(df.format(keyF*1.0/TotalWords*1.0)));
		}
			newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
			
		return newMap;
	}
	
	
	
	
	// 这个版本有两个一个可以接受文件路径，  另外一个可以接受存放word的list
	/** 
	 * * 传入一个文件， 获取文件(每一行的 frequency / 文件总词数)。    例如一个文件第一行为    他是  好人  共出现3 词，    而文件总词数 100 那么，这一行会变成
	 *     “他是  好人	0.03”。 并把每一行 写入map中进行 返回map， 可以指定是否按照value进行排序以后再写入。 
	 *  1.dog    2. dog and cat   3.dog            这里dog算作出现3次
	 *  传入一个文件， 然后输出一个文件，后面写有词频。 
	 * 传入一个经过分词后的文件，去掉了单字词组之后的文件， 注意要在去重之前.   注意由于对于一些  单个的词，他有可能被包含在其他词组中，这时候也要计算为一次
	 * @param fromPath    对哪个文件进行处理
	 * @param toPath     d://abc.txt  把数出来的数组写入到哪个文件中
	 */
	public static Map<String, Double> getTFDivideTotWordsReturnMap(String fromPath,boolean ifSortByTF){
		int TotalWords=0;
		 DecimalFormat df = new DecimalFormat("0.00000");  
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPath, "\r\n");
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//如果key里面包括他则进行加1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
			String abc[]=eachWord.split(" ");
			TotalWords=TotalWords+abc.length;
		}
		// 等会要把数出来的累加并记录到另外一个map里面去，而不直接修改原来的map
		Map<String, Double> newMap=new HashMap<String, Double>();
		Set<String> keyset1=termMap.keySet();
		//获取keyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			
			newMap.put(keyoutside,Double.parseDouble(df.format(keyF*1.0/TotalWords*1.0)));
		}
			newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
			
		return newMap;
	}
	
	
	
	
	// 这个版本有两个一个可以接受文件路径，  另外一个可以接受存放word的list
	/** 
	 * * 传入一个wordList， (每个元素的 frequency / list里面的总词数)。    例如一个元素为    他是  好人  共出现3 词，    而整个list里面出现的总词数 100 那么，这一行会变成
	 *     “他是  好人	0.03”。 并把每一行 写入指定的文件中， 可以指定是否按照value进行排序以后再写入。 
	 *  1.dog    2. dog and cat   3.dog            这里dog算作出现3次
	 *  传入一个文件， 然后输出一个文件，后面写有词频。 
	 * 传入一个经过分词后的文件，去掉了单字词组之后的文件， 注意要在去重之前.   注意由于对于一些  单个的词，他有可能被包含在其他词组中，这时候也要计算为一次
	 * @param fromPath    对哪个文件进行处理
	 * @param toPath     d://abc.txt  把数出来的数组写入到哪个文件中
	 */
	public static boolean getTFDivideTotWordsWrtFile(List<String> wordList1,String toPath,boolean ifSortByTF){
		int TotalWords=0;
		 DecimalFormat df = new DecimalFormat("0.00000");  
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=wordList1;
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//如果key里面包括他则进行加1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
			String abc[]=eachWord.split(" ");
			TotalWords=TotalWords+abc.length;
		}
		// 等会要把数出来的累加并记录到另外一个map里面去，而不直接修改原来的map
		Map<String, Double> newMap=new HashMap<String, Double>();
		Set<String> keyset1=termMap.keySet();
		//获取keyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			
			newMap.put(keyoutside,Double.parseDouble(df.format(keyF*1.0/TotalWords*1.0)));
		}
		String finalContentString="";
		
		if (ifSortByTF) {
			newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
		}
//		获取map里的数据
		Set<String> setKey=newMap.keySet();
		for (String key1:setKey) {
			finalContentString=finalContentString+key1+"\t"+newMap.get(key1)+"\r\n";
//			System.out.println(key1+"\t\t"+newMap.get(key1));
		}
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContentString, toPath);
//		System.out.println("文章词数： "+TotalWords);
		return true;
	}
	
	
	
	
	
	// 这个版本有两个一个可以接受文件路径，  另外一个可以接受存放word的list
	/** 
	 * * 传入一个文件， 获取文件(每一行的 frequency / 文件总词数)。    例如一个文件第一行为    他是  好人  共出现3 词，    而文件总词数 100 那么，这一行会变成
	 *     “他是  好人	0.03”。 并把每一行 写入指定的文件中， 可以指定是否按照value进行排序以后再写入。 
	 *  1.dog    2. dog and cat   3.dog            这里dog算作出现3次
	 *  传入一个文件， 然后输出一个文件，后面写有词频。 
	 * 传入一个经过分词后的文件，去掉了单字词组之后的文件， 注意要在去重之前.   注意由于对于一些  单个的词，他有可能被包含在其他词组中，这时候也要计算为一次
	 * @param fromPath    对哪个文件进行处理
	 * @param toPath     d://abc.txt  把数出来的数组写入到哪个文件中
	 */
	public static boolean getTFDivideTotWordsWrtFile(String fromPath,String toPath,boolean ifSortByTF){
		int TotalWords=0;
		 DecimalFormat df = new DecimalFormat("0.00000");  
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPath, "\r\n");
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//如果key里面包括他则进行加1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
			String abc[]=eachWord.split(" ");
			TotalWords=TotalWords+abc.length;
		}
		// 等会要把数出来的累加并记录到另外一个map里面去，而不直接修改原来的map
		Map<String, Double> newMap=new HashMap<String, Double>();
		Set<String> keyset1=termMap.keySet();
		//获取keyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			
			newMap.put(keyoutside,Double.parseDouble(df.format(keyF*1.0/TotalWords*1.0)));
		}
		String finalContentString="";
		
		if (ifSortByTF) {
			newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
		}
//		获取map里的数据
		Set<String> setKey=newMap.keySet();
		for (String key1:setKey) {
			finalContentString=finalContentString+key1+"\t"+newMap.get(key1)+"\r\n";
//			System.out.println(key1+"\t\t"+newMap.get(key1));
		}
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContentString, toPath);
//		System.out.println("文章词数： "+TotalWords);
		return true;
	}
	
	
	
	
	
	// 这个版本有两个一个可以接受文件路径，  另外一个可以接受存放word的list
	/** 
	 * *  这个文件接收的是List<String>  里面的每一个元素都会进行TF计算 
	 * 对一个文件 去除重复的同时输出term frequency并且把结果写入文件。 可以指定是否按照   Tf 排序？
	 *  1.dog    2. dog and cat   3.dog            这里dog算作出现3次
	 *  传入一个文件， 然后输出一个文件，后面写有词频。 
	 * 传入一个经过分词后的文件，去掉了单字词组之后的文件， 注意要在去重之前.   注意由于对于一些  单个的词，他有可能被包含在其他词组中，这时候也要计算为一次
	 * @param fromPath    对哪个文件进行处理
	 * @param toPath     d://abc.txt  把数出来的数组写入到哪个文件中
	 */
	public static boolean getTFComplexWrtFile(List<String> wordList,String toPath,boolean ifSortByTF){
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=wordList;
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//如果key里面包括他则进行加1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
		}
		// 等会要把数出来的累加并记录到另外一个map里面去，而不直接修改原来的map
		Map<String, Integer> newMap=new HashMap<String, Integer>();
		Set<String> keyset1=termMap.keySet();
		//获取keyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			newMap.put(keyoutside, keyF);
		}
		String finalContentString="";
		
		if (ifSortByTF) {
			newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
		}
//		获取map里的数据
		Set<String> setKey=newMap.keySet();
		for (String key1:setKey) {
			finalContentString=finalContentString+key1+"\t"+newMap.get(key1)+"\r\n";
//			System.out.println(key1+"\t\t"+newMap.get(key1));
		}
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContentString, toPath);
		return true;
	}
	
	// 这个版本有两个一个可以接受文件路径，  另外一个可以接受存放word的list
	/** 
	 * * 对一个文件 去除重复的同时输出term frequency并且把结果写入文件。 可以指定是否按照   Tf 排序？
	 *  1.dog    2. dog and cat   3.dog            这里dog算作出现3次
	 *  传入一个文件， 然后输出一个文件，后面写有词频。 
	 * 传入一个经过分词后的文件，去掉了单字词组之后的文件， 注意要在去重之前.   注意由于对于一些  单个的词，他有可能被包含在其他词组中，这时候也要计算为一次
	 * @param fromPath    对哪个文件进行处理
	 * @param toPath     d://abc.txt  把数出来的数组写入到哪个文件中
	 */
	public static boolean getTFComplexWrtFile(String fromPath,String toPath,boolean ifSortByTF){
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPath, "\r\n");
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//如果key里面包括他则进行加1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
		}
		// 等会要把数出来的累加并记录到另外一个map里面去，而不直接修改原来的map
		Map<String, Integer> newMap=new HashMap<String, Integer>();
		Set<String> keyset1=termMap.keySet();
		//获取keyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			newMap.put(keyoutside, keyF);
		}
		String finalContentString="";
		
		if (ifSortByTF) {
			newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
		}
//		获取map里的数据
		Set<String> setKey=newMap.keySet();
		for (String key1:setKey) {
			finalContentString=finalContentString+key1+"\t"+newMap.get(key1)+"\r\n";
//			System.out.println(key1+"\t\t"+newMap.get(key1));
		}
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContentString, toPath);
		return true;
	}
	
	
	
	
	// 这个版本有两个一个可以接受文件路径，  另外一个可以接受存放word的list
	/** 
	 * 接收 单词的List
	 * * 对一个文件 去除重复的同时输出term frequency并且把结果写入map并返回， 可以设定返回的map是否进行按照TF的值进行排序。
	 *  1.dog    2. dog and cat   3.dog            这里dog算作出现3次
	 *  传入一个文件， 然后输出一个文件，后面写有词频。 
	 * 传入一个经过分词后的文件，去掉了单字词组之后的文件， 注意要在去重之前.   注意由于对于一些  单个的词，他有可能被包含在其他词组中，这时候也要计算为一次
	 * @param wordList   这是一个存放了很多word 的list， 把它转入map 数出每一个元素出现的次数。 
	 * @param ifSortByTF     是否对返回的map进行排序处理？
	 * @return
	 */
	public static Map<String, Integer> getTFComplexReturnMap(List<String> wordList,boolean ifSortByTF){
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=wordList;
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//如果key里面包括他则进行加1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
		}
		// 等会要把数出来的累加并记录到另外一个map里面去，而不直接修改原来的map
		Map<String, Integer> newMap=new HashMap<String, Integer>();
		Set<String> keyset1=termMap.keySet();
		//获取keyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			newMap.put(keyoutside, keyF);
		}
		String finalContentString="";
//		获取map里的数据
//		Set<String> setKey=newMap.keySet();
//		for (String key1:setKey) {
//			finalContentString=finalContentString+key1+"\t"+newMap.get(key1)+"\r\n";
////			System.out.println(key1+"\t\t"+newMap.get(key1));
//		}
//		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContentString, toPath);
		newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
		
		return newMap;
	}
	
	
	
	// 这个版本有两个一个可以接受文件路径，  另外一个可以接受存放word的list
	/** 
	 * * 对一个文件 去除重复的同时输出term frequency并且把结果写入map并返回， 可以设定返回的map是否进行按照TF的值进行排序。
	 *  1.dog    2. dog and cat   3.dog            这里dog算作出现3次
	 *  传入一个文件， 然后输出一个文件，后面写有词频。 
	 * 传入一个经过分词后的文件，去掉了单字词组之后的文件， 注意要在去重之前.   注意由于对于一些  单个的词，他有可能被包含在其他词组中，这时候也要计算为一次
	 * @param fromPath   对哪个文件进行处理    d://abc.txt 
	 * @param ifSortByTF     是否对返回的map进行排序处理？
	 * @return
	 */
	public static Map<String, Integer> getTFComplexReturnMap(String fromPath,boolean ifSortByTF){
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPath, "\r\n");
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//如果key里面包括他则进行加1
				termMap.put(eachWord, termMap.get(eachWord)+1);
			}else {
				termMap.put(eachWord, 1);
			}
		}
		// 等会要把数出来的累加并记录到另外一个map里面去，而不直接修改原来的map
		Map<String, Integer> newMap=new HashMap<String, Integer>();
		Set<String> keyset1=termMap.keySet();
		//获取keyset1
		for (String keyoutside:keyset1) {
			int keyF=containsWordInMapsKey(keyoutside, termMap, "");
			newMap.put(keyoutside, keyF);
		}
		String finalContentString="";
//		获取map里的数据
//		Set<String> setKey=newMap.keySet();
//		for (String key1:setKey) {
//			finalContentString=finalContentString+key1+"\t"+newMap.get(key1)+"\r\n";
////			System.out.println(key1+"\t\t"+newMap.get(key1));
//		}
//		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(finalContentString, toPath);
		newMap=mapHandleZackUtil.sortMapByMapValue(newMap, true);
		
		return newMap;
	}
	
	
	
	
	
	
	
	
	
	/**  传入进来一个word  和一个map， 找出map的key中，包含这个word的值，并获取这个key对应的value，并进行累加以后返回。map盛放的： ("中国   大学",3) 3 表示中国   大学出现的次数。
	 *     ("中国   美女"，2)   如果传进来的word是中国，  则返回5
	 * @param word
	 * @param strMap
	 * @param keySplitMark   传进来的map中的key  有可能是个词组， 你需要用什么分隔符进行分割。 如果传入null表示不想划分，就默认\r\n
	 *        如果传入""   表示采用默认拆分， 默认按照一个或者多个空格进行拆分
	 */
	public static int containsWordInMapsKey(String word, Map<String, Integer> strMap, String keySplitMark){
		int wordTimes=0;
		if ("".equals(keySplitMark)) {
			keySplitMark="\\s+";//表示按照一个或者多个空格进行拆分
		}
		
		if (null==keySplitMark) {
			keySplitMark="\r\n";//表示不拆分， 只拆分成他自己，或者按行拆分
		}
		Set<String> keyset=strMap.keySet();
		for (String akey:keyset) {
			//使用正则表达式来解决这个问题， 用正则表达式去匹配word，能匹配上就累加
			if(akey.contains(word+" ")||akey.contains(" "+word)||akey.equals(word)) {
				wordTimes=wordTimes+strMap.get(akey);
			}
		}
		return wordTimes;
	}
	
	
	
	/**
	 * 
	 * 
	 * 对一个文件 去除重复的同时输出相同的次数
	 *  例如           1.dog    2. dog and cat   3.dog            这里dog只算作出现2次
	 * 传入一个经过分词后的文件，去掉了单字词组之后的文件， 注意要在去重之前.   注意由于对于一些  单个的词，他有可能被包含在其他词组中，这个方法不计算那种情况。
	 * @param fromPath   
	 */
	public static void deletDuplicateAndGetTFSimple(String fromPath){
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		List<String> artList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPath, "\r\n");
		for (String eachWord:artList) {
			if (termMap.containsKey(eachWord)) {//如果key里面包括他则进行加1
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
