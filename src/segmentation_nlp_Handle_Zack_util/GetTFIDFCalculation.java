package segmentation_nlp_Handle_Zack_util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import list_Set_Map_Handle_Zack_util.mapHandleZackUtil;

import org.junit.Test;

import Primary_data_type_zack_util.String_handle_Zack_Util;
import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class GetTFIDFCalculation {

	
	
	
	
	/**
	 * 给一篇文章计算tfidf
	 * 
	 * 计算整个文件夹里面所有单词的tfidf
	 */
	@Test
	public void GetAllfiles_getAWordTFIDF(){
		String fromDirectory="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\";
//		String fileString="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\201603151342390373_Seg_Reg_DS.txt";
		String writeToPath="W:\\zackjob\\hacker\\nlp_workspace_files\\similarityDistance_9_1\\tfidf_9_1_1\\";
		List<String> filesList=ReadAndWriteFileFromDirectory.getAllFilesPathNew(fromDirectory, "txt",new ArrayList<String>());
		int finishOne=0;
		for (String fileString : filesList) {//所有文章
			finishOne++;
			List<String> wordList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fileString, "\r\n");
			String contentWholeString="";
			Map<String, Double> artMap=new HashMap<String, Double>();//每篇文章都要写入到map中
			for (String word:wordList) {//这是一篇文章
				
//				String[] eachLine=word.split("\t");
				double tfidf=GetTFIDFCalculation.getAWordTFIDF(fromDirectory, fileString, word, "txt","\r\n");
				artMap.put(word, tfidf);
			}//这样一篇文章算好了就，然后写入新文件中去。
			
			//给map排序一下。 
			Map<String, Double> newMap=mapHandleZackUtil.sortMapByMapValue(artMap, true);
			Set<String> keSet1=newMap.keySet();
			for (String string : keSet1) {
				contentWholeString=contentWholeString+string+"\t"+newMap.get(string)+"\r\n";
			}
			String filename1=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(fileString);
			String toFilePath=writeToPath+filename1+"_tfidf"+".txt";
			ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(contentWholeString, toFilePath);
			System.out.println("------------------------------------完成一篇文章--------------------剩余---"+(filesList.size()-finishOne));
		}
//		System.out.println(arrList.size());
//		double tfidf=TFIDFCalculation.getAWordTFIDF(fromDirectory, fromFilePath, word, "txt");
//		System.out.println(tfidf);
		System.out.println("finish writing tfidf");
	}
	
	
	
	
	//idf :   log(总文件数/包含这个词的文件的总数)
	/** 传入一个文件夹， 传入一个单词，返回这个这个单词在这个文件夹中的idf= log10(中文件数目/包含这个单词的文件的总个数);
	 * @param fromDirectory   传入文件夹
	 * @param word   传入一个单词
	 * @param postfix   指定一个后缀，只考虑这种后缀的文件。
	 * @return
	 */
	public static double getAWordIDF(String fromDirectory, String word, String postfix){
		if (String_handle_Zack_Util.isEmpty(fromDirectory)||String_handle_Zack_Util.isEmpty(word)) {
			return 0.0;
		}
		DecimalFormat df=new DecimalFormat("0.000");
		double idf=0.0;
		List<String> fileList1=ReadAndWriteFileFromDirectory.getAllFilesPathNew(fromDirectory, postfix,new ArrayList<String>());
		int totDocs=fileList1.size();
		int containWordDocs=0;
		int count=0;
		for (String fileName:fileList1) {//循环这么多文件，数出有多少个文件里面包含这个单词。 注意他应该作为一个词汇，而不是被无空格包围。 
			String fileContent=ReadAndWriteFileFromDirectory.readFileAndReturnContent(fileName);
			
			if (fileContent.contains(word+" ")||fileContent.contains(" "+word)) {//文件内容里包含这个词。
				containWordDocs++;
			}
		}
		System.out.println("总共文档个数: "+totDocs+"   其中有："+containWordDocs+" 个文件包含：  "+word+"     这个词");
		//  10对数求IDF, 注意这里是分母进行加1 防止分母为0的情况。
		idf=Math.log10((totDocs*1.0)/((containWordDocs*1.0)+1.0));
//		idf=Math.log10(3.0);
//		System.out.println("totDocs: "+totDocs+"  containsWordDoc: "+containWordDocs+1);
		idf=Double.parseDouble(df.format(idf));
//		System.out.println("一共有："+totDocs+"篇文档， 其中含有 "+word+" 的文档数量："+containWordDocs);
		return idf;
	}
	
	
	
	/**
	 * 给一个单词， 一个文章集合的目录， 一个这个单词出自的文章（考虑这篇文章 这个单词的TFIDF），以及一个指定后缀，文件夹中只有满足后缀的文件才会被考虑。
	 * 返回这个单词在这篇文章背景下的------------TFIDF  =   TF  *   IDF
	 * @param fromDirectory:  要考虑的所有文件保存的总路径，
	 * @param fromFilePath：   我们要考虑这个单词在A文章的TFIDF，  这里给出A文章的路径     w:\\abc.txt
	 * @param word:   需要计算TFIDF的word
	 * @param postfix：  总文件路径下，哪种后缀的文件需要被考虑。
	 * @Param splitMark:  你传进来的文章怎么分词，你希望如何进行分开。
	 * @return
	 */
	public static double getAWordTFIDF(String fromDirectory,String fromFilePath, String word, String postfix,String splitMark){
		if (String_handle_Zack_Util.isEmpty(fromDirectory)||String_handle_Zack_Util.isEmpty(word)||String_handle_Zack_Util.isEmpty(fromFilePath)) {
			return 0.0;
		}
		double tfidf=0.0;
		double tf=getWordTFDivideTotWordsFromArticle(fromFilePath, word, splitMark);
		double idf=getAWordIDF(fromDirectory, word, postfix);
		System.out.println("word: "+word+"   tf: "+tf+"  idf: "+idf);
//		System.out.println("tf: "+tf+"  idf: "+idf);
		tfidf=tf*idf;
		return tfidf;
		
		
	}
	
	

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
//					System.out.println(aWord);
					if (aWord.contains(word+" ")||aWord.contains(" "+word)||aWord.equals(word)||aWord.contains(word)) {
						wordOcc++;
					}
				}
//				System.out.println(wordOcc+" "+contArr.length);
				double Tf=(wordOcc*1.0)/(contArr.length*1.0);
				System.out.println("本文章含有： "+contArr.length+" 个词汇");
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
					if (aWord.contains(word+" ")||aWord.contains(" "+word)||aWord.equals(word) ||aWord.contains(word)) {
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

	
	
	
	
	
	
}



















