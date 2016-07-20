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
     * ���㷨  ���ҳ����������ԵĴʡ� ʲô���������� ��������������:
     * ����   ƻ������ϵͳ��   ���ڲ���ϵͳ �����������ʣ�ǰ�����ǳ���ƻ��������Ϊ����ϵͳ����ʾ��ǲ�������
     * �����Ĵ�Ӧ��Ϊ ƻ������ϵͳ  �������ϵͳǰ�����  ��  android��ʱ����ƻ���� ��ô����Ϊ���������ġ�
     * ��������������ͬʱ�����������������������Ҳ�������������ʡ�
     * ���������ڶ�ÿ���ʽ��б�ע��������µ��ļ���ȥ��    ÿ���ʶ���  ����   0 1 ����ϣ�  0��ʾ������ 1��ʾ��������
     */
	/**
	 * �������Թ���
	 * @param inputPath
	 * @param outputPath
	 */
	public static void wordRightAndLeftIntegration_Final(String inputPath,String sourcePath,String outputPath){
			try {
				String result = "";
				List<String> wordsList = ReadAndWriteFileFromDirectory.readFileAndWrtToList(inputPath, "\r\n");
				for(String word : wordsList){
					
					//���ִ�֮���Դ�ļ�  ԭ�������ݵĶ�ȡ��
					String sourceWord = ReadAndWriteFileFromDirectory.readFileAndReturnContent(sourcePath);
					String[] sourceWords = sourceWord.split(" ");
					String[] words = word.split(" ");
					Set<String> leftSet = new HashSet<String>();
					Set<String> rightSet = new HashSet<String>();
					
					//��0��ʱ�򣬱�ʾ������1��ʱ���ʾ������
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
	 * ���㷨  ���ҳ����������ԵĴʡ� ʲô���������� ��������������:
	 * ����   ƻ������ϵͳ��   ���ڲ���ϵͳ �����������ʣ�ǰ�����ǳ���ƻ��������Ϊ����ϵͳ����ʾ��ǲ�������
	 * �����Ĵ�Ӧ��Ϊ ƻ������ϵͳ  �������ϵͳǰ�����  ��  android��ʱ����ƻ���� ��ô����Ϊ���������ġ�
	 * ��������������ͬʱ�����������������������Ҳ�������������ʡ�
	 * ���㷨������ȡ��Щ�ʡ�
	 */
	/**
	 * �������������������� ��һ����   �������򻯺���ļ������������йؼ��ʡ�    �ڶ��������Ӧ��ԭ���£��������Ѿ������ϱ�ǩ�ˡ� �������ǰѽ���洢����Ŀ��·����
	 * ���������ǣ� ���ݴ����ǩ��ԭ����  �ж�  ���򻯺���ļ�����Щ�ؼ����������Եġ�   ������  ���������棡
	 * @param fromPathAfRule
	 * @param fromPathSeg
	 * @param toPath  ֻд·��ע��   ����  d:\\go\\   ���治�ܼ��ļ���
	 */
	public static void rightAndLeftIntegrationFilter(String fromPathAfRule, String fromPathSeg, String toPath){
		String finalWordsContentString="";
	// ���ȷֱ��ȡ�����ļ���list
		List<String> afRuleList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPathAfRule, "\r\n");
//		for (String abc:afRuleList) {
//			System.out.println("list11111:   "+abc);
//		}
		//��ȡԭ�ķִʺ��list
		List<String> originList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPathSeg, " ");//����ǰ��տո���зִ�
//		for (String abc:originList) {
//			System.out.println("list222222:   "+abc);
//		}
//		System.out.println(originList.get(0)+"---"+afRuleList.get(0)+"---res:"+originList.get(0).equals(afRuleList.get(0)));
//		String content=readAndWriteFileFromDirectory.readFileAndReturnContent(fromPathSeg);
//		//ѭ��ÿһ���ʣ�������ʷ��뵽������µ�listȥ���н�һ������
		for (String word:afRuleList) {
			//���word���ȣ����������Ĵ�ֱ�Ӽ����ȥ
			String []ak=word.split(" ");//���word����ӵ�����������ϵĴʣ���ֱ�Ӽ������ļ���ȥ��
			boolean leftComplete=ifWordLeftCompleteInArticleList(word,originList, 0, "",true,0);  //���ص���true˵���������������
			boolean rightComplete=ifWordRightCompleteInArticleList(word,originList, 0, "",true,0); //���ص���true˵��������Ҳ�������
			if (ak.length>=2||(!(leftComplete && rightComplete))) {//��Щ���������ԵĴʡ�
				// д�����ļ���ȥ��
				finalWordsContentString=finalWordsContentString+word+"\r\n";
//				System.out.println("------------"+word+"-----------");
			}
			
		}
		String newFileNameString=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(fromPathAfRule)+"_Int";
		ReadAndWriteFileFromDirectory.writeStringToFile(finalWordsContentString, toPath, newFileNameString, "txt");
	}
	
	
	/**
	 * //���ص���true˵��������������ġ�  ���Ȿ�����޷��ж�     �����Ƿ������ġ� ֻ���ж�һ�����Ƿ������ģ� ��Ϊ���������µ�list����ɢ�ˡ� 
	 * �����������Եĺ��Ĵ��룬 �������������   rightLeftIntegrationFilter   ��һ���� �Ǻ��Ĵ��롣
	 * @param word   ��Ҫ�ж�������Ƿ�  ��  ĳƪ�������������� 
	 * @param articleList     ��ƪ���µ�list�� ע�����������posttag֮��ģ� ���տո�д����list��ȥ��
	 * @param from     ��list�ĵڼ����ʽ��бȽϣ� ��Ҫ�ӵ�0���ʿ�ʼ�Ƚ�
	 * @param currLeftWord      ����¼������   word ��ߵ��Ǹ��ʡ�    �տ�ʼ����������һ���� ""  ����һ�ζ�ȡword��ߵĴ�ʱ�򣬰��Ǹ��ʸ�ֵ��currLeftWord
	 * @param flag     �ж�  ��ǰword��ߵĴ�  ����һ��word��ߵĴ��Ƿ�һ���� ��һ����false
	 * @param foundTime     �жϵڼ���ƥ����word����ʣ� ��һ���ҵ�word��ʱ����Ҫ��word��ߵĴʸ�ֵ��currLeftWord
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
				 //�ж�������Ƿ�����д�
				if (from-1>=0) {//��ʾ������д�Ҫ�ж��Ƿ�������
//					System.out.println("111");
					foundTime++;
					templeftWord=articleList.get(from-1);//�õ���ߵĴ��ˡ�������Ҫ�������ж������ߵĴ�ʱ�����һ�λ�ȡ����ߵĴ�
					if (foundTime==1) {// ��һ���ҵ��ʵ�ʱ���  ʹ currLeftWord���ڵ�һ�η�����ߵĴʣ�ֻҪ�ݹ�����һ�β�һ����false
						currLeftWord=templeftWord;
					}
					if(templeftWord.equals(currLeftWord)){//����ͬ������һ����ߵĴ�
						flag=ifWordLeftCompleteInArticleList(word,articleList,from+1,currLeftWord,flag,foundTime);	
					}else{
						return false;
					}
				}else {
					//�����û�д�
					flag=ifWordLeftCompleteInArticleList(word,articleList,from+1,currLeftWord,flag,foundTime);
				}
			}else {
				//��word�����ڵ�ǰ�ʣ���ݹ�ȥ��word�Ƿ������һ����
				flag=ifWordLeftCompleteInArticleList(word,articleList,from+1,currLeftWord,flag,foundTime);
			}
			
//		}
		return flag;
	}
	
	
	
	/**
	 * //���ص���true˵��������Ҳ�������         ���Ȿ�����޷��ж�     �����Ƿ������ġ� ֻ���ж�һ�����Ƿ������ģ� ��Ϊ���������µ�list����ɢ�ˡ� 
	 * �����������Եĺ��Ĵ��룬 �������������   rightLeftIntegrationFilter   ��һ���� �Ǻ��Ĵ��롣
	
	 * @param word   ��Ҫ�ж�������Ƿ�  ��  ĳƪ�������������� 
	 * @param articleList     ��ƪ���µ�list�� ע�����������posttag֮��ģ� ���տո�д����list��ȥ��
	 * @param from     ��list�ĵڼ����ʽ��бȽϣ� ��Ҫ�ӵ�0���ʿ�ʼ�Ƚ�
	 * @param currRightWord      ����¼������   word ��ߵ��Ǹ��ʡ�    �տ�ʼ����������һ���� ""  ����һ�ζ�ȡword��ߵĴ�ʱ�򣬰��Ǹ��ʸ�ֵ��currLeftWord
	 * @param flag     �ж�  ��ǰword��ߵĴ�  ����һ��word��ߵĴ��Ƿ�һ���� ��һ����false
	 * @param foundTime     �жϵڼ���ƥ����word����ʣ� ��һ���ҵ�word��ʱ����Ҫ��word��ߵĴʸ�ֵ��currLeftWord
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
//				System.out.println(articleList.get(from)+"�����article");
//				System.out.println("111");
				 //�ж�������Ƿ�����д�
//					System.out.println("111");
					foundTime++;
					templeftWord=articleList.get(from+1);//�õ���ߵĴ��ˡ�������Ҫ�������ж������ߵĴ�ʱ�����һ�λ�ȡ����ߵĴ�
					if (foundTime==1) {// ��һ���ҵ��ʵ�ʱ���  ʹ currLeftWord���ڵ�һ�η�����ߵĴʣ�ֻҪ�ݹ�����һ�β�һ����false
						currRightWord=templeftWord;
					}
					if(templeftWord.equals(currRightWord)){//����ͬ������һ����ߵĴ�
						flag=ifWordRightCompleteInArticleList(word,articleList,from+1,currRightWord,flag,foundTime);	
					}else{
						return false;
					}
			}else {
				//��word�����ڵ�ǰ�ʣ���ݹ�ȥ��word�Ƿ������һ����
				flag=ifWordRightCompleteInArticleList(word,articleList,from+1,currRightWord,flag,foundTime);
			}
		return flag;
	}
	
	
	
}
