package testUtil;

import org.junit.Test;

import fileHandle_From_Directory.Handle_File_Content;

public class test_HandleFileContent {

	@Test
	public void test_splitFileIntoOtherFiles(){
		String frompathString="W:\\zackjob\\hacker\\web_Bug\\tonghuashun\\smalltest.txt";
		String topathString="W:\\zackjob\\hacker\\web_Bug\\tonghuashun\\";
		Handle_File_Content.splitFileIntoOtherFiles(frompathString,"\t",1,topathString,"gbk");
	}
	
}
