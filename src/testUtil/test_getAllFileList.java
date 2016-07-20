package testUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class test_getAllFileList {

	
	@Test
	public void test_OldGetAllFileList(){
		String fileFrom="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\";
		String fileFrom2="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteDuplicate_5\\";
		List<String> fileList=ReadAndWriteFileFromDirectory.getAllFilesPath(fileFrom, "txt");
		System.out.println(fileList.size());
		List<String> fileList1=ReadAndWriteFileFromDirectory.getAllFilesPath(fileFrom, "txt");
		System.out.println(fileList1.size());
	}
	
	

	@Test
	public void test_NewGetAllFileList(){
		String fileFrom="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteSingle_4\\";
		String fileFrom2="W:\\zackjob\\hacker\\nlp_workspace_files\\noiseReduct_DeleteDuplicate_5\\";
		List<String> fileList=ReadAndWriteFileFromDirectory.getAllFilesPathNew(fileFrom, "txt",new ArrayList<String>());
		System.out.println(fileList.size());
		List<String> fileList1=ReadAndWriteFileFromDirectory.getAllFilesPathNew(fileFrom2, "txt",new ArrayList<String>());
		System.out.println(fileList1.size());
	}
	
	
	
}
