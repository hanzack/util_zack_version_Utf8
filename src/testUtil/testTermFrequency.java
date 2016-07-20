package testUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import segmentation_nlp_Handle_Zack_util.getTermFrequency;
import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class testTermFrequency {

	
	
	@Test
	public void test_SplitMark(){
		String[] abcStrings="他是 一个好热  但是".split("\r\n");
		for (int i = 0; i < abcStrings.length; i++) {
			System.out.println(abcStrings[i]);
		}
		
	}
	
	@Test
	public void test_containsWordInMapsKey(){
		String word="中国 人";
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		termMap.put("中国 人", 2);
		termMap.put("美丽 中国 自然", 6);
		termMap.put("知识 中国", 3);
		termMap.put("中国", 12);
		termMap.put("中国客栈", 1);
		
		int k=getTermFrequency.containsWordInMapsKey(word,termMap,"");
		System.out.println(k);
	}
	
	
	@Test
	public void test_deletDuplicateAndGetTFComplex(){
		
		String fromPath="W:\\zackjob\\hacker\\nlp_workspace_files\\201603151342390373_Seg_Reg_DS.txt";
		String toPath="W:\\zackjob\\hacker\\nlp_workspace_files\\termFrequencyFile_7\\tf.txt";
		
		
		getTermFrequency.getTFComplexWrtFile(fromPath,toPath,true);
	}
	
	
	//这里对文章进行去除重复和  输出每个词或者词组出现的次数。
	@Test
	public void handleFiles_deletDuplicateAndGetTFComplex(){
		
		String fromPath="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\201603151342390373_Seg_Reg_DS.txt";
		String toPath="W:\\zackjob\\hacker\\nlp_workspace_files\\termFrequencyFile_7\\tf.txt";
		
		getTermFrequency.getTFComplexWrtFile(fromPath,toPath,true);
	}
	
	
	
	//这里对所有文章进行去除重复和  输出每个词或者词组出现的次数。
		@Test
		public void handleAllFiles_deletDuplicateAndGetTFComplex(){
			
			String fromPath="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\";
			String toPath="W:\\zackjob\\hacker\\nlp_workspace_files\\termFrequencyFile_7\\";
			
			List<String> fileL=ReadAndWriteFileFromDirectory.getOnlyFilesPath(fromPath, "txt");
			for (String file:fileL) {
				String newFileName=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(file)+"_Tf"+".txt";
				String toPath1=toPath+newFileName;
				getTermFrequency.getTFComplexWrtFile(file,toPath1,true);
			}
			System.out.println("finish writing!");
		}
		
	
	
		
		@Test
		public void getTFComplexReturnMap(){
			String fromPath="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\201603151342390373_Seg_Reg_DS.txt";
			Map<String, Integer> map11=getTermFrequency.getTFComplexReturnMap(fromPath, true);

			Set<String> ksetSet=map11.keySet();
			for (String ke:ksetSet) {
				System.out.println(ke+" : "+map11.get(ke));
			}
			
		}
		
		
		
		@Test
		public void getTFDivideTotWordsWrtFile(){
			String fromPath="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\201603151342390373_Seg_Reg_DS.txt";
			String topath="W:\\zackjob\\hacker\\nlp_workspace_files\\abc111.txt";
			boolean map11=getTermFrequency.getTFDivideTotWordsWrtFile(fromPath,topath, true);

			System.out.println(map11);
			
		}
		
		
		//测试词频   TF/总词数， 返回map
		
		@Test
		public void getTFComplexReturnMap1(){
			String fromPath="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\201603151342390373_Seg_Reg_DS.txt";
			Map<String, Double> map11=getTermFrequency.getTFDivideTotWordsReturnMap
					(fromPath, true);

			Set<String> ksetSet=map11.keySet();
			for (String ke:ksetSet) {
				System.out.println(ke+" : "+map11.get(ke));
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
}
