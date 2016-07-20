package fileHandle_From_Directory;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import Primary_data_type_zack_util.String_handle_Zack_Util;

public class Handle_File_Content {

	
	
	/**
	 * 抽取文章的某一列写入其他文件
	 * 假如一篇文章，是有一列一列的，列与列之间是按照某种splitMark分成一列一列的，然后可以指定第几列，把这一列的所有内容存入指定的文件中。
	 * 例如    有文件 w:\\abc.txt  目的是把内容1， 内容2存放到   w:\\def.txt里面
	 * 		 id1	A	内容1
	 *       id2    A	内容2  
	 *       那么这里的变量就是  fromPathFile： w:\\abc.txt ，  splitString："\t" ,  columnNum : 2    toFilePath：   w:\\def.txt   
	 * @param fromPathFile
	 * @param splitString
	 * @param columnNum
	 * @param toFilePath
	 * @param encode
	 */
	public static void extractContentIntoFile(String fromPathFile, String splitString, int columnNum, String toFilePath, String encode,String addNew){
		if (null==addNew) {
			addNew="";
		}
		if (null==encode||encode.equals("")) {
			encode="UTF-8";
		}
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		try {
			inputStream = new FileInputStream(fromPathFile);
			sc = new Scanner(inputStream, encode);
			String[] lineStr=null;
			while (sc.hasNextLine()) {
			String line = sc.nextLine();
			lineStr=line.split(splitString);
			String newtext=lineStr[columnNum]+addNew+"\r\n";
			ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(newtext, toFilePath);
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
											throw sc.ioException();
										}
			} finally {
			if (inputStream != null) {
									inputStream.close();
								}
			if (sc != null) {
				sc.close();
									}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	/**
	 * 传入一个文件把文件中的所有指定的origin字符全部替换成指定字符并存入文件。按行读取，并且按行进行存储到新文件中
	 * @param fromPathFile  w:\\abc.txt
	 * @param toPathFile    w:\\bd.txt
	 * @param orgin        "--"
	 * @param replace         "@@"
	 */
	public static boolean replaceContentofFileWrtFile(String fromPathFile,String toPathFile,String origin, String replace){
		
		List<String> wordList=new ArrayList<>();
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		try {
			inputStream = new FileInputStream(fromPathFile);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String newLine=line.replace(origin, replace)+"\r\n"; //String newLine=line.replace("\t", "@@"); 
			ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(newLine, toPathFile);
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
											throw sc.ioException();
										}
			} finally {
			if (inputStream != null) {
									inputStream.close();
								}
			if (sc != null) {
				sc.close();
									}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;	
		
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 传入一个文件把文件中的所有指定的origin字符全部替换成指定字符并存入文件。按行读取，并且按行进行存储到新文件中
	 * 除此之外还在每一行末尾加上指定的 addnew
	 * 例如  你是--s          我希望改成    你是@@s_b    那么origin就是--   replace 就是@@    addnew就是_b    
	 * @param fromPathFile  w:\\abc.txt
	 * @param toPathFile    w:\\bd.txt
	 * @param orgin        "--"
	 * @param replace         "@@"
	 */
	public static boolean replaceContentofFileWrtFile(String fromPathFile,String toPathFile,String origin, String replace, String addnew){
		
		List<String> wordList=new ArrayList<>();
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		try {
			inputStream = new FileInputStream(fromPathFile);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String newLine=line.replace(origin, replace)+addnew+"\r\n"; //String newLine=line.replace("\t", "@@"); 
			System.out.println(newLine);
			ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(newLine, toPathFile);
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
											throw sc.ioException();
										}
			} finally {
			if (inputStream != null) {
									inputStream.close();
								}
			if (sc != null) {
				sc.close();
									}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;	
		
	}
	
	
	
	/**
	 * 假如一篇文章里面每一行都是一个条目，条目写明了 这个条目所属的类，然后按照这个类把内容拆分到不同文件。
	 * 例如   一篇文章叫做abc一共100个条目，共属于2个类：  A与B， 然后分成两个文件，abc_A.txt   abc_B.txt
	 * 属于哪一个类就放入到哪个文件中去。
	 * 这里的变量介绍如下
	 * @param fromFilePath：  需要被拆分的文件名地址：  w:\\abc.txt
	 * @param splitMark:   每一行怎么找到这个类的类名呢， 可以用分割符号找到它。 例如   117	A	你好啊    对于这个条目利用分割符号\t可以变成数组找到A
	 * @param classPosition: 对于上面的例子，类名所属的位置是  1 号位置
	 * @param toFilePath：   把结果文件都存入这个文件夹里面， 例如 w:\\。 而具体的文件名会变成原文件名+类名+.txt
	 */
	public static void splitFileIntoOtherFiles(String fromFilePath,String splitMark, int classPosition,String toFilePath,String encoding){
		if (String_handle_Zack_Util.isEmpty(splitMark)) {
			splitMark="\t";
		}
		if (String_handle_Zack_Util.isEmpty(encoding)) {
			encoding="UTF-8";
		}
		String originfileName=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(fromFilePath);
		//循环文档的每一行，读取文档
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		try {
			inputStream = new FileInputStream(fromFilePath);
			sc = new Scanner(inputStream, encoding);
			while (sc.hasNextLine()) {
				//这里得到了每一行，然后写入这个类独有的文件
			String line = sc.nextLine();
			String[] lineArr=line.split(splitMark);//得到分割后的结果，然后获取类名
			String className=lineArr[classPosition];//得到类名
			String toFileNameHere=toFilePath+originfileName+"_"+className+".txt";
//			System.out.println(toFileNameHere);
			ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(line+"\r\n", toFileNameHere);
			
			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
											throw sc.ioException();
										}
			} finally {
			if (inputStream != null) {
									inputStream.close();
								}
			if (sc != null) {
				sc.close();
									}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
}
