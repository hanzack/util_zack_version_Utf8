package testUtil;


import html_Doc_Excel_TxT_Zack_Util.Html_Handle_files;

import org.junit.Test;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class Test_readAndWriteFileFromDirectory {

	@Test
	public void test_readFileAndReturnContent(){
		String filePath="W:\\zackjob\\Algorithm_structure_util\\Heritrix_1.144\\jobs\\SohuNews-20160313000256928\\mirror\\news.sohu.com\\20130702\\n380405425.shtml";
		String contentString=ReadAndWriteFileFromDirectory.readFileAndReturnContent(filePath);
		System.out.println(contentString);
		
	}
	
	
	@Test
	public void test_readFileAndWriteToFile(){
		String fromFileString="W:\\zackjob\\Algorithm_structure_util\\Heritrix_1.144\\jobs\\SohuNews-20160313000256928\\mirror\\news.sohu.com\\20130702\\n380405425.shtml";
		String toFilePathString="W:\\zackjob\\Algorithm_structure_util\\Heritrix_1.144\\jobs\\SohuNews-20160313000256928\\mirror\\news.sohu.com\\20130702\\";
		ReadAndWriteFileFromDirectory.readFileAndWriteToFile(fromFileString,toFilePathString,null,"doc");
		System.out.println("finish writing!");
	}
	
	
	@Test
	public void test_writeStringToFile(){
		String toFilePathString="W:\\zackjob\\";
		System.out.println(ReadAndWriteFileFromDirectory.writeStringToFile("my name is zack",toFilePathString,"",""));
		System.out.println("finish writing!");
	}
	
	
	@Test
	public void test_getAllFilesTitleFrmDirWrtNewFile(){
		String fromFileString="W:\\zackjob\\Algorithm_structure_util\\Heritrix_1.144\\jobs\\SohuNews-20160313000256928\\mirror\\news.sohu.com\\";
		String toFilePathString="W:\\zackjob\\";
		Html_Handle_files.getAllFilesTitleFrmDirWrtNewFile(fromFileString, toFilePathString, ".shtml", "news_Title", "txt");
	}
	
	@Test
	public  void test_getFileNameFromFilePath() {
		String filePath="D1_2-d÷–:\\BaiduYunD_o-wnload\\∂æË… √¿æÁ\\∂æË…\\BitLocker ª÷∏¥√‹‘ø C87ABEFA-B819-4390-8230-667736A560CE.txt";
//		String filePath="d12÷–d:\\∂æË…√¿æÁ\\∂æË…\\abc.txt";
		System.out.println(ReadAndWriteFileFromDirectory.getFileNameFromFilePath(filePath));
	}
	
	
	@Test
	public  void test_getFileSurfixFromFilePath() {
		String filePath="D1_2-d÷–:\\BaiduYunD_o-wnload\\∂æË… √¿æÁ\\∂æË…\\BitLocker ª÷∏¥√‹‘ø C87ABEFA-B819-4390-8230-667736A560CE.txt";
//		String filePath="d12÷–d:\\∂æË…√¿æÁ\\∂æË…\\abc.txt";
		System.out.println(ReadAndWriteFileFromDirectory.getFileSurfixFromFilePath(filePath));
	}
	
	
	@Test
	public  void test_getFileNameAndSurfixFromFilePath() {
		String filePath="D1_2-d÷–:\\BaiduYunD_o-wnload\\∂æË… √¿æÁ\\∂æË…\\BitLocker ª÷∏¥√‹‘ø C87ABEFA-B819-4390-8230-667736A560CE.txt";
//		String filePath="d12÷–d:\\∂æË…√¿æÁ\\∂æË…\\abc.txt";
		System.out.println(ReadAndWriteFileFromDirectory.getFileNameAndSurfixFromFilePath(filePath));
	}
	
	
}
