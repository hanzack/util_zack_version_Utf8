package testUtil;

import org.junit.Test;

import fileHandle_From_Directory.Handle_File_Content;

public class test_Handlefilecontent_replaceContetntoFile {

	@Test
	public void test_replaceContent(){
		String fromPathFile="W:\\zackjob\\hacker\\web_Bug\\tonghuashun\\lungutang.guanggao.words";
		String toPathFile="W:\\zackjob\\hacker\\web_Bug\\tonghuashun\\lungutang1.guanggao1.words";
		Handle_File_Content.replaceContentofFileWrtFile(fromPathFile, toPathFile, "\t", "@@","ths");
	}
}
