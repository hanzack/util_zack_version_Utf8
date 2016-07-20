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
	 * ��һƪ���¼���tfidf
	 * 
	 * ���������ļ����������е��ʵ�tfidf
	 */
	@Test
	public void GetAllfiles_getAWordTFIDF(){
		String fromDirectory="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\";
//		String fileString="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\201603151342390373_Seg_Reg_DS.txt";
		String writeToPath="W:\\zackjob\\hacker\\nlp_workspace_files\\similarityDistance_9_1\\tfidf_9_1_1\\";
		List<String> filesList=ReadAndWriteFileFromDirectory.getAllFilesPathNew(fromDirectory, "txt",new ArrayList<String>());
		int finishOne=0;
		for (String fileString : filesList) {//��������
			finishOne++;
			List<String> wordList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fileString, "\r\n");
			String contentWholeString="";
			Map<String, Double> artMap=new HashMap<String, Double>();//ÿƪ���¶�Ҫд�뵽map��
			for (String word:wordList) {//����һƪ����
				
//				String[] eachLine=word.split("\t");
				double tfidf=GetTFIDFCalculation.getAWordTFIDF(fromDirectory, fileString, word, "txt","\r\n");
				artMap.put(word, tfidf);
			}//����һƪ��������˾ͣ�Ȼ��д�����ļ���ȥ��
			
			//��map����һ�¡� 
			Map<String, Double> newMap=mapHandleZackUtil.sortMapByMapValue(artMap, true);
			Set<String> keSet1=newMap.keySet();
			for (String string : keSet1) {
				contentWholeString=contentWholeString+string+"\t"+newMap.get(string)+"\r\n";
			}
			String filename1=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(fileString);
			String toFilePath=writeToPath+filename1+"_tfidf"+".txt";
			ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(contentWholeString, toFilePath);
			System.out.println("------------------------------------���һƪ����--------------------ʣ��---"+(filesList.size()-finishOne));
		}
//		System.out.println(arrList.size());
//		double tfidf=TFIDFCalculation.getAWordTFIDF(fromDirectory, fromFilePath, word, "txt");
//		System.out.println(tfidf);
		System.out.println("finish writing tfidf");
	}
	
	
	
	
	//idf :   log(���ļ���/��������ʵ��ļ�������)
	/** ����һ���ļ��У� ����һ�����ʣ���������������������ļ����е�idf= log10(���ļ���Ŀ/����������ʵ��ļ����ܸ���);
	 * @param fromDirectory   �����ļ���
	 * @param word   ����һ������
	 * @param postfix   ָ��һ����׺��ֻ�������ֺ�׺���ļ���
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
		for (String fileName:fileList1) {//ѭ����ô���ļ��������ж��ٸ��ļ��������������ʡ� ע����Ӧ����Ϊһ���ʻ㣬�����Ǳ��޿ո��Χ�� 
			String fileContent=ReadAndWriteFileFromDirectory.readFileAndReturnContent(fileName);
			
			if (fileContent.contains(word+" ")||fileContent.contains(" "+word)) {//�ļ��������������ʡ�
				containWordDocs++;
			}
		}
		System.out.println("�ܹ��ĵ�����: "+totDocs+"   �����У�"+containWordDocs+" ���ļ�������  "+word+"     �����");
		//  10������IDF, ע�������Ƿ�ĸ���м�1 ��ֹ��ĸΪ0�������
		idf=Math.log10((totDocs*1.0)/((containWordDocs*1.0)+1.0));
//		idf=Math.log10(3.0);
//		System.out.println("totDocs: "+totDocs+"  containsWordDoc: "+containWordDocs+1);
		idf=Double.parseDouble(df.format(idf));
//		System.out.println("һ���У�"+totDocs+"ƪ�ĵ��� ���к��� "+word+" ���ĵ�������"+containWordDocs);
		return idf;
	}
	
	
	
	/**
	 * ��һ�����ʣ� һ�����¼��ϵ�Ŀ¼�� һ��������ʳ��Ե����£�������ƪ���� ������ʵ�TFIDF�����Լ�һ��ָ����׺���ļ�����ֻ�������׺���ļ��Żᱻ���ǡ�
	 * ���������������ƪ���±����µ�------------TFIDF  =   TF  *   IDF
	 * @param fromDirectory:  Ҫ���ǵ������ļ��������·����
	 * @param fromFilePath��   ����Ҫ�������������A���µ�TFIDF��  �������A���µ�·��     w:\\abc.txt
	 * @param word:   ��Ҫ����TFIDF��word
	 * @param postfix��  ���ļ�·���£����ֺ�׺���ļ���Ҫ�����ǡ�
	 * @Param splitMark:  �㴫������������ô�ִʣ���ϣ����ν��зֿ���
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
//					System.out.println(aWord);
					if (aWord.contains(word+" ")||aWord.contains(" "+word)||aWord.equals(word)||aWord.contains(word)) {
						wordOcc++;
					}
				}
//				System.out.println(wordOcc+" "+contArr.length);
				double Tf=(wordOcc*1.0)/(contArr.length*1.0);
				System.out.println("�����º��У� "+contArr.length+" ���ʻ�");
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
					if (aWord.contains(word+" ")||aWord.contains(" "+word)||aWord.equals(word) ||aWord.contains(word)) {
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

	
	
	
	
	
	
}



















