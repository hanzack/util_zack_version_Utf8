package segmentation_nlp_Handle_Zack_util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;

public class noise_Reduction {

	
	/**
	 * 文件被规则以后， 有些词只有一个字，这个方法就是去除这些单字的行，其他都放入一个新的文件
	 * @param fromPath   规则后文件的路径
	 * @param toPath      把文件里的单字的行给去除，并存入新的文件里面去
	 */
	public static void deleteSingleWordsCreateNewFile(String fromPath, String toPath){
		
		try {
			String result="";
			File file=new File(fromPath);
			InputStream is=new FileInputStream(file);
			InputStreamReader isReader=new InputStreamReader(is,"gb2312");
			BufferedReader bf=new BufferedReader(isReader);
			String temp="";
			//读取 规则后的文件，进行降噪，去除单字。
			while ((temp=bf.readLine())!=null) {
//				System.out.println("--------------"+temp);
				String[] tempArr=temp.split("/");
				if (tempArr.length>=3|tempArr[0].length()>1) {
					result=result+temp+"\r\n";
				}else {
//					System.out.println(temp);
				}
			}
			ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(result, toPath);
			System.out.println("finish writing!");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	

	/**
	 * 传入一个文件 去除文件中重复的行，或者重复的词。 具体要看调用的时候指定哪一种分词形式。 例如对文章按照 "\r\n" 存入list， 那么就是去除重复的行。
	 * 然后写入新的文件。
	 *
	 * @param fromPath     D://abc.txt
	 * @param toPath       D://jj//kkk.txt
	 * @param splitMark   //例如 "\r\n"    如果不指定分割符号，则默认使用回车换行对文章进行写入list
	 */
	public static void deleteDuplicateLineFromFiles(String fromPath,String toPath, String splitMark){
		if (null==splitMark || splitMark=="") {
			splitMark="\r\n";//如果不指定分割符号，则默认使用回车换行对文章进行写入list
		}
		
//		System.out.println("fromPath::  "+fromPath);
//		System.out.println("toPath::  "+toPath);
		String wholeContent="";
		//获取这个文件的内容写到list里面去
		List<String> fromPList=ReadAndWriteFileFromDirectory.readFileAndWrtToList(fromPath, splitMark);
		HashSet<String> set1=new HashSet<String>();
		for (String pat:fromPList) {
			set1.add(pat);
		}
		for (String setpath:set1) {
			wholeContent=wholeContent+setpath+splitMark;
		}
		ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(wholeContent, toPath);
	}
	
	
}
