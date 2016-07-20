package testUtil;

import java.util.List;

import org.junit.Test;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;
import fileHandle_From_Directory.zipAndUnzipFileToDirectory;

public class test_UnzipFiles {

	
	@Test
	public void test_unzipFile(){
		int count=0;
		String inputPath="W:\\zackjob\\testSomeThingHere\\fullindex\\";
		List<String> comList=ReadAndWriteFileFromDirectory.getAllFilesPath(inputPath, "zip");
		for (String fileName:comList) {
//			System.out.println(fileName);
			count++;
			String toPathString=fileName.replace("company.zip", "");
//			System.out.println(toPathString);
		zipAndUnzipFileToDirectory.unZipFile(fileName, toPathString);
		}
		System.out.println(count+" files has been unziped!");
	}
	
}
