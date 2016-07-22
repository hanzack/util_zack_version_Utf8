package fileHandle_From_Directory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Primary_data_type_zack_util.String_handle_Zack_Util;
import Primary_data_type_zack_util.createID_Unique;

public class ReadAndWriteFileFromDirectory {

	
	
	/**
	 * 传入文件内容， 保存的文件名以及编码方式
	 * @param contentString
	 * @param toFilePath
	 * @param encode
	 */
	public static void writeStringToFileWithFileNameByte(String contentString,String toFilePath,String encode){
		
//		File outpFile=new File("W:\\zackjob\\hacker\\web_Bug\\zhihu\\ZhiHu_topicTemp.html");
		File outpFile=new File(toFilePath);
		FileOutputStream outputStream=null;
		
		try {
			byte[] bt=contentString.getBytes(encode);
			outputStream=new FileOutputStream(outpFile);
			outputStream.write(bt);
			outputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
	/**
	 * 例如   a 是文件里的内容
	 * 然后， 传入的分数是   1.  分割符号是   tab
	 *    输出文件是     a		1
	 * 传入词典文件， 传入一个指定分数， 给每个单词赋值这个分数。  同时输入  修改后存储的地址。
	 * splitMark:  单词 和  分数中间的分割符号。
	 * @param fromFileName  ：   字典文件存储地址
	 * @param Score： 单词得分
	 * @param splitMark:  分割符号是一个   tab
	 * @param newFilePath:  新的字典文件存储地址  例如 w://abc.txt
	 * @return
	 */
	public static String dictScoreChange(String fromFileName,int Score, String splitMark,String newFilePath) {
		if (String_handle_Zack_Util.isEmpty(newFilePath)) {
			String fileNamwithSurfix=ReadAndWriteFileFromDirectory.getFileNameAndSurfixFromFilePath(fromFileName);
			String fileN=ReadAndWriteFileFromDirectory.getFileNameFromFilePath(fromFileName);
			newFilePath=fromFileName.replace(fileNamwithSurfix, "")+fileN+"_Score.txt";
		}
//		System.out.println("新文件路径："+newFilePath);
		if (String_handle_Zack_Util.isEmpty(splitMark)) {
			splitMark="\t";
		}
		 
        File file = new File(fromFileName);
        BufferedReader reader = null;
        String fileString1="";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString ="";
            int line = 1;
            //写入文件
            //if file doesnt exists, then create it
            File file1 =new File(newFilePath);
	           if(!file1.exists()){
	        	   file1.createNewFile();
	           }
            FileWriter fileWriter=new FileWriter(newFilePath, true);
	        BufferedWriter bw = new BufferedWriter(fileWriter);
            // 对写入文件的配置
	        
            while ((tempString = reader.readLine()) != null) {
//             fileString1=fileString1+tempString+"\r\n";
            	String tempWord=tempString+splitMark+Score+"\r\n";
                line++;
                bw.write(tempWord);
	             bw.flush();
	           
            }
            bw.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return fileString1;
    }
	
	
	
	
	/**
	 * 传入一个html文件，和一个解码的字符集。
	 * @param fileName  ：  传入的文件路径  w://abc.html
	 * @param encoding   :  对这个文件读取的时候进行解码     例如  UTF-8
	 * @return
	 */
	public static String readFileWithEncodingAndReturnContent(String fileName,String encoding){
		if (String_handle_Zack_Util.isEmpty(encoding)) {
			encoding="UTF-8";
		}
		String contentString="";
		File file=new File(fileName);
		try {
			InputStreamReader isR=new InputStreamReader(new FileInputStream(file),encoding);
			BufferedReader bf=new BufferedReader(isR);
			String eachLineString="";
			while ((eachLineString=bf.readLine())!=null) {
				contentString=contentString+eachLineString+"\r\n";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contentString;
	}
	

	
	/**
	 * 传入一个文件的绝对路径，返回这个文件的内容
	 * 
	 * @param fileName
	 * @return
	 */
	public static String readFileAndReturnContent(String fileName) {
//		System.out.println("readFileAndReturnContent:  "+fileName);
        File file = new File(fileName);
        BufferedReader reader = null;
        String fileString1="";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString ="";
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
             fileString1=fileString1+tempString+"\r\n";
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return fileString1;
    }
	
	
	
	
	
	
	/**
	 * 
	 * 把一个指定文件，写入到指定目录下指定的文件中去。如果写入到的目标文件不存在，则生成一个新文件。
	 * @param fromFileName    需要打开的文件，要把它的内容写入别的地方去。 不可以为空例如W:\\zackjob\\Algorithm_structure_util\\Heritrix_1.144\\jobs\\SohuNews-20160313000256928\\mirror\\news.sohu.com\\20130702\\n380405425.shtml
	 * @param toFilePath    需要写入到某个路径下， 不可以为空 例如W:\\zackjob\\Algorithm_structure_util\\
	 * @param newFileName     指定写入到某个文件名。可以为空，如果为空，自动以当前时间精确到毫秒为文件名
	 * @param fileSurfix    指定写入到文件的类型，不指定，则为txt  。 可以为空
	 * @return
	 */
	public static String readFileAndWriteToFile(String fromFileName,String toFilePath,String newFileName, String fileSurfix) {
		
		if (String_handle_Zack_Util.isEmpty(fileSurfix)) {
			fileSurfix="txt";
		}
		//如果用户没有设置文件名， 则分配一个时间给文件名。 
		if (String_handle_Zack_Util.isEmpty(newFileName)) {
			newFileName=createID_Unique.getIDByTimeCalender();
		}
		
		toFilePath=toFilePath+newFileName+"."+fileSurfix;
		 
        File file = new File(fromFileName);
        BufferedReader reader = null;
        String fileString1="";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString ="";
            int line = 1;
            //写入文件
            //if file doesnt exists, then create it
            File file1 =new File(toFilePath);
	           if(!file1.exists()){
	        	   file1.createNewFile();
	           }
            FileWriter fileWriter=new FileWriter(toFilePath, true);
	        BufferedWriter bw = new BufferedWriter(fileWriter);
            // 对写入文件的配置
	        
	        
            while ((tempString = reader.readLine()) != null) {
//             fileString1=fileString1+tempString+"\r\n";
             String tempStr=tempString+"\r\n";
                line++;
                bw.write(tempStr);
	             bw.flush();
	           
            }
            bw.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return fileString1;
    }
	

	/**
	 * 
	 * 把一个字符串追加到一个指定的目录文件中， 如果该文件不存在，则新建一个再进行写入。
	 * @param content      需要写入的字符串
	 * @param toFilePath    需要写入到某个路径下， 不可以为空 例如W:\\zackjob\\Algorithm_structure_util\\
	 * @param newFileName     指定写入到某个文件名。可以为空，如果为空，自动以当前时间精确到毫秒为文件名  abc
	 * @param fileSurfix    指定写入到文件的类型，不指定，则为txt  。 可以为空
	 * @return
	 */
	public static boolean writeStringToFile(String content, String toFilePath,String newFileName, String fileSurfix) {
//		System.out.println("writeStringToFile"+content);
		boolean returnFlag=false;
		if (String_handle_Zack_Util.isEmpty(toFilePath)) {
			return returnFlag;
		}
		if (String_handle_Zack_Util.isEmpty(newFileName)) {
			newFileName=createID_Unique.getIDByTimeCalender();
		}
		if (String_handle_Zack_Util.isEmpty(fileSurfix)) {
			fileSurfix="txt";
		}
		String writeToPath=toFilePath+newFileName+"."+fileSurfix;
		File file=new File(writeToPath);
		 try {
			FileWriter fWriter=new FileWriter(file,true);// true 表示append
			BufferedWriter bw=new BufferedWriter(fWriter);
			
			bw.write(content);
			bw.flush();
			bw.close();
			returnFlag=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return returnFlag;
	}
	

	
	
	/**
	 * 
	 * 把一个字符串追加到一个指定的目录文件中， 如果该文件不存在，则新建一个再进行写入。
	 * @param content      需要写入的字符串
	 * @param toFilePath    需要写入到某个路径下文件名， 不可以为空 例如W:\\zackjob\\Algorithm_structure_util\\abc.txt
	 * @return
	 */
	public static boolean writeStringToFileWithFileName(String content, String toFilePath) {
//		System.out.println("writeStringToFile"+content);
		boolean returnFlag=false;
		if (String_handle_Zack_Util.isEmpty(toFilePath)) {
			return returnFlag;
		}
//		String writeToPath=toFilePath+newFileName+"."+fileSurfix;
		File file=new File(toFilePath);
		 try {
			FileWriter fWriter=new FileWriter(file,true);// true 表示append
			
			BufferedWriter bw=new BufferedWriter(fWriter);
			bw.write(content);
			bw.flush();
			bw.close();
			returnFlag=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return returnFlag;
	}
	
	
	
	/**
	 * 给定一个路径，获取路径下所有文件，以及文件夹下的所有文件， 的路径集合。 返回一个ArrayList 里面是所有文件的路径名包括（文件名）
	 * @param inputPath
	 * @param 指定需要获取的目标文件的格式
	 * @return
	 */
	public static List<String> getAllFilesPathNew(String inputPath,String postfix,List<String> resultPathList1){
		if (String_handle_Zack_Util.isEmpty(postfix)) {
			postfix=".txt";
		}
		try{
			File file=new File(inputPath);
			File[] files=file.listFiles();
			
			for (File f:files) {
				
				if (f.isDirectory()) {
					getAllFilesPathNew(f.getAbsolutePath(),postfix,resultPathList1);//递归获取文件绝对路径
				}else{
					if (f.getName().endsWith(postfix)) {
						resultPathList1.add(f.getAbsolutePath());
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return resultPathList1;
	}
	
	
	
	

	
	
	/**
	 * 给定一个路径，获取路径下所有文件，以及文件夹下的所有文件， 的路径集合。 返回一个ArrayList 里面是所有文件的路径名包括（文件名）
	 * @param inputPath
	 * @param 指定需要获取的目标文件的格式
	 * @return
	 */
	@Deprecated  
	public static List<String> resultPathList=new ArrayList<String>();
	public static List<String> getAllFilesPath(String inputPath,String postfix){
		if (String_handle_Zack_Util.isEmpty(postfix)) {
			postfix=".txt";
		}
		try{
			File file=new File(inputPath);
			File[] files=file.listFiles();
			
			for (File f:files) {
				
				if (f.isDirectory()) {
					getAllFilesPath(f.getAbsolutePath(),postfix);//递归获取文件绝对路径
				}else{
					if (f.getName().endsWith(postfix)) {
						resultPathList.add(f.getAbsolutePath());
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return resultPathList;
	}
	
	
	
	/**获取文件夹里的所有后缀文件，不递归
	 * 传入一个文件路径， 和一种指定的文件类型， 返回某个文件夹里面所有这个文件的文件地址包括文件名。  注意本方法只是获取第一层目录。 而不进行递归。
	 * @param inputPath
	 * @param surfix   用户需要获得的文件类型， 可以为null  或者为空
	 * @return
	 */
	public static List<String> getOnlyFilesPath(String inputPath,String surfix){
		List<String> resultList=new ArrayList<String>();
		try{
			File file=new File(inputPath);
			File[] files=file.listFiles();
			for (File f:files) {
				if (f.isDirectory()) {
					continue;
				}else{
					if (String_handle_Zack_Util.isEmpty(surfix)) {
						resultList.add(f.getAbsolutePath());
					}else {
						if (f.getName().endsWith(surfix)) {
							resultList.add(f.getAbsolutePath());
						}
					}
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
//		System.out.println("1111: "+resultList.size());
		return resultList;
	}
	
	
	
	
	
	
	/**
	 * 传入一个文件地址，返回这个文件的文件名包括后缀名字，可以包含中文空格和-_
	 * //D:\BaiduYunDownload\毒枭 美剧\毒枭ab_t哈 哈-1.txt   返回    毒枭ab_t哈 哈-1.txt
	 * @param filePath
	 * @return
	 */
	public static String getFileNameAndSurfixFromFilePath(String filePath){
		if (String_handle_Zack_Util.isEmpty(filePath)) {
			return "no file inputed";
		}
		String fileName="";
//		String regex="([A-Za-z0-9\\-]|[\u4e00-\u9fa5]*):\\\\([A-Za-z0-9-]|[\u4e00-\u9fa5]\\\\)*(.*).[0-9A-Za-z]+";
		String regex="([0-9A-Za-z\u4e00-\u9fa5\\-_\\s]*:\\\\)([0-9A-Za-z\u4e00-\u9fa5\\-_\\s]*\\\\)*([0-9a-zA-Z\u4e00-\u9fa5\\-_\\s]*).([0-9A-Za-z]+)";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(filePath);
		if (matcher.find()) {
				fileName=matcher.group(3)+"."+matcher.group(4);
		}
		return fileName;
	}
	
	
	

	/**
	 * 传入一个文件地址，返回这个文件的后缀名字，可以包含中文空格和-_
	 * ////D:\BaiduYunDownload\毒枭 美剧\毒枭ab_t哈 哈-1.txt   返回  .txt
	 * @param filePath
	 * @return
	 */
	public static String getFileSurfixFromFilePath(String filePath){
		if (String_handle_Zack_Util.isEmpty(filePath)) {
			return "no file inputed";
		}
		String fileName="";
//		String regex="([A-Za-z0-9\\-]|[\u4e00-\u9fa5]*):\\\\([A-Za-z0-9-]|[\u4e00-\u9fa5]\\\\)*(.*).[0-9A-Za-z]+";
		String regex="([0-9A-Za-z\u4e00-\u9fa5\\-_\\s]*:\\\\)([0-9A-Za-z\u4e00-\u9fa5\\-_\\s]*\\\\)*([0-9a-zA-Z\u4e00-\u9fa5\\-_\\s]*).([0-9A-Za-z]+)";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(filePath);
		if (matcher.find()) {
				fileName=matcher.group(4);
		}
		return fileName;
	}
	
	
	
	/**
	 * 传入一个文件地址，返回这个文件名，可以包含中文空格和-_
	 * //D:\BaiduYunDownload\毒枭 美剧\毒枭.ab_t哈 哈-1.txt
	 * @param filePath
	 * @return
	 */
	public static String getFileNameFromFilePath(String filePath){
		if (String_handle_Zack_Util.isEmpty(filePath)) {
			return "no file inputed";
		}
		String fileName="";
//		String regex="([A-Za-z0-9\\-]|[\u4e00-\u9fa5]*):\\\\([A-Za-z0-9-]|[\u4e00-\u9fa5]\\\\)*(.*).[0-9A-Za-z]+";
		String regex="([0-9A-Za-z\u4e00-\u9fa5\\-_\\s]*:\\\\)([0-9A-Za-z\u4e00-\u9fa5\\-_\\s]*\\\\)*([0-9a-zA-Z\u4e00-\u9fa5\\-_\\s]*).([0-9A-Za-z]+)";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(filePath);
		if (matcher.find()) {
				fileName=matcher.group(3);
		}
		return fileName;
	}
	
	
	/**
	 * 传入一个文件地址， 返回一个Arraylist<String>  根据要求制定分割符号
	 * @param fromPath   对哪个文件进行处理
	 * @param splitMark    分割符号可以指定。 默认是   "\r\n"
	 */
	public static List<String> readFileAndWrtToList(String fromPath, String splitMark){
		if (splitMark==""||splitMark==null) {
			splitMark="\r\n";
		}
		List<String> wordList=new ArrayList<>();
		String content="";
		try {
			File file=new File(fromPath);
			FileReader fReader=new FileReader(file);
			BufferedReader bReader=new BufferedReader(fReader);
			String tempLine="";
			while ((tempLine=bReader.readLine())!=null) { //先组合成整篇文章再进行分割
				content=content+tempLine+"\r\n";
			}
			String[] tempwordList=content.split(splitMark);
			for (int i = 0; i < tempwordList.length; i++) {
				wordList.add((tempwordList[i].trim()));
			}
//			System.out.println(tempwordList[0]+"========");
//			wordList=Arrays.asList(tempwordList);
			
			fReader.close();
			bReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return wordList;	
	}
	
	
	
	
	
	/**
	 * 读取大文件
	 * 传入一个文件地址， 返回一个Arraylist<String>  一行一行的读取，读取一行就加入到list里面去
	 * @param fromPath   对哪个文件进行处理
	 * @param 这个方法不指定分割符号
	 */
	public static List<String> readBigFileAndWrtToList(String fromPath){
		List<String> wordList=new ArrayList<>();
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		try {
			inputStream = new FileInputStream(fromPath);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
			String line = sc.nextLine();
			wordList.add(line);
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
		
		return wordList;	
	}
	
	
	

	/**
	 * 读取大文件
	 * 传入一个文件地址， 返回一个Arraylist<String>  一行一行的读取，读取一行就加入到list里面去
	 * @param fromPath   对哪个文件进行处理
	 * @param splitMark    分割符号可以指定。 默认是   "\r\n"
	 */
	public static List<String> readBigFileAndWrtToList(String fromPath, String splitMark){
		if (splitMark==""||splitMark==null) {
			splitMark="\r\n";
		}
		List<String> wordList=new ArrayList<>();
		String content="";
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		try {
			inputStream = new FileInputStream(fromPath);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
			String line = sc.nextLine()+splitMark;
			wordList.add(line);
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
									}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wordList;	
	}
	
	
	
	/**
	 * 读取大文件
	 * 传入一个文件地址， 返回一个Arraylist<String>  一行一行的读取，读取一行就加入到list里面去
	 * @param fromPath   对哪个文件进行处理
	 * @param splitMark    分割符号可以指定。 默认是   "\r\n"
	 */
	public static String readBigFileAndRtContent(String fromPath, String splitMark){
		if (splitMark==null) {
			splitMark="\r\n";
		}
		String content="";
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		try {
			inputStream = new FileInputStream(fromPath);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
			String line = sc.nextLine()+splitMark;
			content=content+line;
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
		
		return content;	
	}
	
	
	 /**
	  * 读取文件里的内容并进行返回
	   * @param eachFile
	   * @return
	   */
	  private static String readBigFileReturnContent(File eachFile) {
	      // TODO Auto-generated method stub
	      String content="";
	      FileInputStream inputStream=null;
	      Scanner sc=null;
	      try {
	          inputStream=new FileInputStream(eachFile);
	      } catch (FileNotFoundException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	      }
	      sc=new Scanner(inputStream,"UTF-8");
	      while(sc.hasNext()){
	          String line=sc.nextLine();
	          content=content+line;
	      }
	      sc.close();
	      return content;
	  }
	
	
}
