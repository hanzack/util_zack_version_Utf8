package segmentation_nlp_Handle_Zack_util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.htmlparser.lexer.Stream;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;
import Primary_data_type_zack_util.String_handle_Zack_Util;

public class WordRightAndLeftIntegration {

	
	
	
	

	/**
     * 本算法  是找出所有完整性的词。 什么叫做完整， 左完整加右完整:
     * 例如   苹果操作系统，   对于操作系统 如果出现这个词，前面总是出现苹果，就认为操作系统这个词就是不完整的
     * 完整的词应该为 苹果操作系统  如果操作系统前面出现  了  android有时出现苹果， 那么就认为他是完整的。
     * 这种是左完整，同时还有右完整，如果左完整右也完整就是完整词。
     * 本方法在于对每个词进行标注后输出到新的文件中去，    每个词都会  跟着   0 1 的组合，  0表示完整， 1表示不完整。
     */
	/**
	 * 非完整性过滤
	 * @param inputPath
	 * @param outputPath
	 */
	public static void wordRightAndLeftIntegration_Final(String inputPath,String sourcePath,String outputPath){
			try {
				String result = "";
				List<String> wordsList = ReadAndWriteFileFromDirectory.readFileAndWrtToList(inputPath, "\r\n");
				for(String word : wordsList){
					
					//读分词之后的源文件  原文章内容的读取。
					String sourceWord = ReadAndWriteFileFromDirectory.readFileAndReturnContent(sourcePath);
					String[] sourceWords = sourceWord.split(" ");
					String[] words = word.split(" ");
					Set<String> leftSet = new HashSet<String>();
					Set<String> rightSet = new HashSet<String>();
					
					//是0的时候，表示完整；1的时候表示不完整
					int left = 0;
					int right = 0;
					
					int leftFirst = 1;
					int rightLast = 1;
					if(words.length == 1){
						
						for(int i=0;i<sourceWords.length;i++){
							if(words[0].equals(sourceWords[i])){
								if(i == 0){
									leftFirst = 0;
									rightSet.add(sourceWords[i+1]);
								}else if(i == sourceWords.length-1){
									rightLast = 0;
									leftSet.add(sourceWords[i-1]);
								}else{
									leftSet.add(sourceWords[i-1]);
									rightSet.add(sourceWords[i+1]);
								}
								
							}
							if(leftFirst == 0){
								left = 0;
							}else{
								if(leftSet.size() == 1){
									left = 1;
								}else{
									left = 0;
								}
							}
							
							if(rightLast == 0){
								right = 0;
							}else{
								if(rightSet.size() == 1){
									right = 1;
								}else{
									right = 0;
								}
							}
						}
					}else if(words.length == 2){
						
						for(int i=0;i<sourceWords.length-1;i++){
							if(words[0].equals(sourceWords[i]) && words[1].equals(sourceWords[i+1])){
								if(i == 0){
									leftFirst = 0;
									rightSet.add(sourceWords[i+2]);
								}else if(i == sourceWords.length-1){
									rightLast = 0;
									leftSet.add(sourceWords[i-1]);
								}else{
									leftSet.add(sourceWords[i-1]);
									rightSet.add(sourceWords[i+2]);
								}
								
							}
							if(leftFirst == 0){
								left = 0;
							}else{
								if(leftSet.size() == 1){
									left = 1;
								}else{
									left = 0;
								}
							}
							
							if(rightLast == 0){
								right = 0;
							}else{
								if(rightSet.size() == 1){
									right = 1;
								}else{
									right = 0;
								}
							}
						}
					}else if(words.length == 2){
						for(int i=0;i<sourceWords.length-2;i++){
							if(words[0].equals(sourceWords[i]) && words[1].equals(sourceWords[i+1]) && words[2].equals(sourceWords[i+2])){
								if(i == 0){
									leftFirst = 0;
									rightSet.add(sourceWords[i+3]);
								}else if(i == sourceWords.length-1){
									rightLast = 0;
									leftSet.add(sourceWords[i-1]);
								}else{
									leftSet.add(sourceWords[i-1]);
									rightSet.add(sourceWords[i+3]);
								}
							}
							if(leftFirst == 0){
								left = 0;
							}else{
								if(leftSet.size() == 1){
									left = 1;
								}else{
									left = 0;
								}
							}
							
							if(rightLast == 0){
								right = 0;
							}else{
								if(rightSet.size() == 1){
									right = 1;
								}else{
									right = 0;
								}
							}
						}
					}
					result += word+"\t"+left+"\t"+right+"\r\n";
				}
				ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(result, outputPath);
//				StringUtils.string2File(result, outputPath);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 本算法  是找出所有完整性的词。 什么叫做完整， 左完整加右完整:
	 * 例如   苹果操作系统，   对于操作系统 如果出现这个词，前面总是出现苹果，就认为操作系统这个词就是不完整的
	 * 完整的词应该为 苹果操作系统  如果操作系统前面出现  了  android有时出现苹果， 那么就认为他是完整的。
	 * 这种是左完整，同时还有右完整，如果左完整右也完整就是完整词。
	 * 本算法意在提取这些词。
	 */
	/**
	 * 本方法接受三个参数， 第一个是   经过规则化后的文件，里面是所有关键词。    第二个是相对应的原文章，该文章已经被打上标签了。 第三个是把结果存储到的目标路径。
	 * 方法做的是， 根据打过标签的原文章  判断  规则化后的文件中哪些关键词是完整性的。   完整性  定义在上面！
	 * @param fromPathAfRule
	 * @param fromPathSeg
	 * @param toPath  只写路径注意   例如  d:\\go\\   后面不能加文件名
	 */
	public static void rightAndLeftIntegrationFilter(String fromPathAfRule, String fromPathSeg, String toPath){
		String finalWordsContentString="";
	// 首先分别获取两个文件的list
		List<String> afRuleList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPathAfRule, "\r\n");
//		for (String abc:afRuleList) {
//			System.out.println("list11111:   "+abc);
//		}
		//获取原文分词后的list
		List<String> originList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPathSeg, " ");//这个是按照空格进行分词
//		for (String abc:originList) {
//			System.out.println("list222222:   "+abc);
//		}
//		System.out.println(originList.get(0)+"---"+afRuleList.get(0)+"---res:"+originList.get(0).equals(afRuleList.get(0)));
//		String content=readAndWriteFileFromDirectory.readFileAndReturnContent(fromPathSeg);
//		//循环每一个词，把这个词放入到存放文章的list去进行进一步处理。
		for (String word:afRuleList) {
			//求出word长度，大于两个的词直接加入进去
			String []ak=word.split(" ");//如果word里面拥有两个及以上的词，就直接加入新文件中去。
			boolean leftComplete=ifWordLeftCompleteInArticleList(word,originList, 0, "",true,0);  //返回的是true说明这个是左不完整的
			boolean rightComplete=ifWordRightCompleteInArticleList(word,originList, 0, "",true,0); //返回的是true说明这个是右不完整的
			if (ak.length>=2||(!(leftComplete && rightComplete))) {//这些都是完整性的词。
				// 写入新文件中去。
				finalWordsContentString=finalWordsContentString+word+"\r\n";
//				System.out.println("------------"+word+"-----------");
			}
			
		}
		String newFileNameString=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(fromPathAfRule)+"_Int";
		ReadAndWriteFileFromDirectory.writeStringToFile(finalWordsContentString, toPath, newFileNameString, "txt");
	}
	
	
	/**
	 * //返回的是true说明这个是左不完整的。  此外本方法无法判断     词组是否完整的。 只能判断一个词是否完整的， 因为给定的文章的list被打散了。 
	 * 处理单词完整性的核心代码， 属于这个方法：   rightLeftIntegrationFilter   的一部分 是核心代码。
	 * @param word   需要判读这个词是否  在  某篇文章里左完整。 
	 * @param articleList     这篇文章的list， 注意这个文章是posttag之后的， 按照空格写入了list中去的
	 * @param from     从list的第几个词进行比较， 需要从第0个词开始比较
	 * @param currLeftWord      被记录下来的   word 左边的那个词。    刚开始传进来的是一个空 ""  当第一次读取word左边的词时候，把那个词赋值给currLeftWord
	 * @param flag     判断  当前word左边的词  与上一次word左边的词是否一样， 不一样则false
	 * @param foundTime     判断第几次匹配上word这个词， 第一次找到word的时候需要把word左边的词赋值给currLeftWord
	 * @return
	 */
	public static boolean ifWordLeftCompleteInArticleList(String word, List<String> articleList, int from,String currLeftWord,boolean flag, int foundTime){
		if (!flag) {
			return false;
		}
		if (from==articleList.size()) {
			return true;
		}
		String templeftWord="";
//		for (int i = from; i < articleList.size(); i++) {
			if (word.equals(articleList.get(from))) {
				 //判断这个词是否左边有词
				if (from-1>=0) {//表示他左边有词要判断是否左完整
//					System.out.println("111");
					foundTime++;
					templeftWord=articleList.get(from-1);//得到左边的词了。接下来要做的是判断这个左边的词时候和上一次获取的左边的词
					if (foundTime==1) {// 第一次找到词的时候就  使 currLeftWord等于第一次发现左边的词，只要递归中有一次不一样就false
						currLeftWord=templeftWord;
					}
					if(templeftWord.equals(currLeftWord)){//若相同则找下一个左边的词
						flag=ifWordLeftCompleteInArticleList(word,articleList,from+1,currLeftWord,flag,foundTime);	
					}else{
						return false;
					}
				}else {
					//若左边没有词
					flag=ifWordLeftCompleteInArticleList(word,articleList,from+1,currLeftWord,flag,foundTime);
				}
			}else {
				//若word不等于当前词，则递归去看word是否等于下一个词
				flag=ifWordLeftCompleteInArticleList(word,articleList,from+1,currLeftWord,flag,foundTime);
			}
			
//		}
		return flag;
	}
	
	
	
	/**
	 * //返回的是true说明这个是右不完整的         此外本方法无法判断     词组是否完整的。 只能判断一个词是否完整的， 因为给定的文章的list被打散了。 
	 * 处理单词完整性的核心代码， 属于这个方法：   rightLeftIntegrationFilter   的一部分 是核心代码。
	
	 * @param word   需要判读这个词是否  在  某篇文章里左完整。 
	 * @param articleList     这篇文章的list， 注意这个文章是posttag之后的， 按照空格写入了list中去的
	 * @param from     从list的第几个词进行比较， 需要从第0个词开始比较
	 * @param currRightWord      被记录下来的   word 左边的那个词。    刚开始传进来的是一个空 ""  当第一次读取word左边的词时候，把那个词赋值给currLeftWord
	 * @param flag     判断  当前word左边的词  与上一次word左边的词是否一样， 不一样则false
	 * @param foundTime     判断第几次匹配上word这个词， 第一次找到word的时候需要把word左边的词赋值给currLeftWord
	 * @return
	 */
	public static boolean ifWordRightCompleteInArticleList(String word, List<String> articleList, int from,String currRightWord,boolean flag, int foundTime){
//		System.out.println("222");
		if (!flag) {
			return false;
		}
		if (from==(articleList.size()-1)) {
//			System.out.println("222");
			return true;
		}
		String templeftWord="";
			if (word.equals(articleList.get(from))) {
//				System.out.println(articleList.get(from)+"这个是article");
//				System.out.println("111");
				 //判断这个词是否左边有词
//					System.out.println("111");
					foundTime++;
					templeftWord=articleList.get(from+1);//得到左边的词了。接下来要做的是判断这个左边的词时候和上一次获取的左边的词
					if (foundTime==1) {// 第一次找到词的时候就  使 currLeftWord等于第一次发现左边的词，只要递归中有一次不一样就false
						currRightWord=templeftWord;
					}
					if(templeftWord.equals(currRightWord)){//若相同则找下一个左边的词
						flag=ifWordRightCompleteInArticleList(word,articleList,from+1,currRightWord,flag,foundTime);	
					}else{
						return false;
					}
			}else {
				//若word不等于当前词，则递归去看word是否等于下一个词
				flag=ifWordRightCompleteInArticleList(word,articleList,from+1,currRightWord,flag,foundTime);
			}
		return flag;
	}
	
	
	
}
