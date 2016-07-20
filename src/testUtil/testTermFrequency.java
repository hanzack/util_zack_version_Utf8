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
		String[] abcStrings="���� һ������  ����".split("\r\n");
		for (int i = 0; i < abcStrings.length; i++) {
			System.out.println(abcStrings[i]);
		}
		
	}
	
	@Test
	public void test_containsWordInMapsKey(){
		String word="�й� ��";
		Map<String,Integer> termMap=new HashMap<String, Integer>();
		termMap.put("�й� ��", 2);
		termMap.put("���� �й� ��Ȼ", 6);
		termMap.put("֪ʶ �й�", 3);
		termMap.put("�й�", 12);
		termMap.put("�й���ջ", 1);
		
		int k=getTermFrequency.containsWordInMapsKey(word,termMap,"");
		System.out.println(k);
	}
	
	
	@Test
	public void test_deletDuplicateAndGetTFComplex(){
		
		String fromPath="W:\\zackjob\\hacker\\nlp_workspace_files\\201603151342390373_Seg_Reg_DS.txt";
		String toPath="W:\\zackjob\\hacker\\nlp_workspace_files\\termFrequencyFile_7\\tf.txt";
		
		
		getTermFrequency.getTFComplexWrtFile(fromPath,toPath,true);
	}
	
	
	//��������½���ȥ���ظ���  ���ÿ���ʻ��ߴ�����ֵĴ�����
	@Test
	public void handleFiles_deletDuplicateAndGetTFComplex(){
		
		String fromPath="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\201603151342390373_Seg_Reg_DS.txt";
		String toPath="W:\\zackjob\\hacker\\nlp_workspace_files\\termFrequencyFile_7\\tf.txt";
		
		getTermFrequency.getTFComplexWrtFile(fromPath,toPath,true);
	}
	
	
	
	//������������½���ȥ���ظ���  ���ÿ���ʻ��ߴ�����ֵĴ�����
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
		
		
		//���Դ�Ƶ   TF/�ܴ����� ����map
		
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
