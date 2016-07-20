package segmentation_nlp_Handle_Zack_util;


import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import fileHandle_From_Directory.ReadAndWriteFileFromDirectory;
import ICTCLAS.I3S.AC.ICTCLAS50;
import Primary_data_type_zack_util.String_handle_Zack_Util;

public class splitWords_util {
	
	@Test
	public void testTemp(){
		String contString="踏实是一个社会至关重要的组成部分";
		String toPath="W:\\";
		String fileName="abc.txt";
		getSplitWordsByICTCLASWrtFile(contString, toPath, fileName);
		
	}
	
	
	
	/**
	 * 最全方法集合
	 * 传入一篇普通文章， 然后这个方法会对文章进行分词，分词之后，再计算每个词出现的次数，然后一个词占一行
	 * 存入指定文件。
	 * 这个方法中间会产生分词后的文件作为中间文件，然后进一步计算词频
	 * 例如 被传入的文件里面的内容会被进行分词，然后一个词一个词的计算出现的次数
	 * @param fromFilePath
	 * @param toFilePath
	 * @param segFilePath  因为中间涉及到需要把文章进行分词，中间会需要存储分词文件。
	 */
	public static void getTFfromFile(String fromFilePath,String toFilePath,String segFilePath){
		//把一篇文章进行分词，并且把分词好的结果文件返回给afterSegGile
		String afterSegFile=segmentFileWrtToFile(fromFilePath, segFilePath);
		//这个方法接收的是一个被分词后的文章，然后对这个分词后的文章进行处理。
		countTFfromsegFilewrtFile(afterSegFile,toFilePath);
	}
	
	
	
	/**
	 * 接收一个分词文件，这个文章是已经分好词的了，并且有分词标注，这个方法用来输出每一个词出现的次数，
	 * 并且一个词占一行存入文件
	 * 
	 * @param afterSegFile
	 */
	public static void countTFfromsegFilewrtFile(String afterSegFile,String toFile){
		Map<String, Integer> wordTFMap=new HashMap<String, Integer>();
		//获取每一行内容，把每一个词语添加进map中
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		try {
			inputStream = new FileInputStream(afterSegFile);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
			String line = sc.nextLine();
			//每一行的所有词
			String[] lineArr=line.split("\\s");
			for (String word:lineArr) {
				if (wordTFMap.containsKey(word)) {//判断是否包含这个单词
                   //如果包含这个词， 
					int countword=wordTFMap.get(word)+1;
					wordTFMap.put(word, countword);
				}else {
					wordTFMap.put(word, 1);
				}
			}
			System.out.println("每一行的长度: "+lineArr.length);
			}
			System.out.println("整个wordMap的长度: "+wordTFMap.size());
			
			//把结果打印到文档中去
			String lineCont="";
			Set<String> keySet=wordTFMap.keySet();
			for (String keyword:keySet) { //去除空格
				if (keyword.matches("\\s+")||keyword.equals("")) {
					continue;
				}
				lineCont=keyword+"\t"+wordTFMap.get(keyword)+"\r\n";
				ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(lineCont, toFile);
			}
			System.out.println("处理完毕，并且把词频写入了： "+toFile);
			
			
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
	
	
	

	/**对一篇文档进行分词化处理，然后存入另外一个文件中, 采用ICTCLAS处理，并且指定使用词性标注。
	 * @param fromFile
	 * @param toFile
	 */
	public static String segmentFileWrtToFile(String fromFile, String toFile){
			
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		try {
			inputStream = new FileInputStream(fromFile);
			sc = new Scanner(inputStream, "UTF-8");
			int contentcount=0;
			String content="";
			while (sc.hasNextLine()) {
				contentcount++;
			String line = sc.nextLine();
			content=content+line;
			//每1000行分词一下
			if (contentcount>=1000) {
				//获取分词字符串
				String segSen=getSplitWordsByICTCLASWithUserDict(content, 1);
//		     	System.out.println(segSen);
				ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(segSen+"\r\n", toFile);
				contentcount=0;
				content="";
			   }
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
			return toFile;
		}
	
	
	/**
	 * 
	 * 传入一个字符串，返回一个分词结果。  采用用户字典。
	 * @param str
	 * @return   分词结果
	 * @param str
	 * @param toPath
	 * @param filename
	 * @return
	 */
	public static String getSplitWordsByICTCLASWithUserDict(String str,int postTag0or1){
	    String result="";
	    try
	    {
	        ICTCLAS50 testICTCLAS50 = new ICTCLAS50();
	        String argu = ".";
	        //初始化
	        if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
	        {
	            System.out.println("Init Fail!");
	            return "";
	        }
	        //设置词性标注集(0 计算所二级标注集，1 计算所一级标注集，2 北大二级标注集，3 北大一级标注集)
	        testICTCLAS50.ICTCLAS_SetPOSmap(1);
//	        //导入用户词典前分词
//	        byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 0, postTag0or1);//分词处理 1 代表词性标注。
//	         result = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
	        
	    	
	    	//导入用户字典   
			int nCount = 0;
			String usrdir = "userdict.txt"; //用户字典路径
			byte[] usrdirb = usrdir.getBytes();//将string转化为byte类型
			//导入用户字典,返回导入用户词语个数第一个参数为用户字典路径，第二个参数为用户字典的编码类型
			nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
//			System.out.println("导入用户词个数" + nCount);
			nCount = 0;

			//导入用户字典后再分词
//			byte nativeBytes1[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 2, postTag0or1);//是否进行词性标注
			byte nativeBytes1[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 2, postTag0or1);//是否进行词性标注
//			System.out.println(nativeBytes1.length);
			 result = new String(nativeBytes1, 0, nativeBytes1.length, "GB2312");
//			System.out.println("导入用户词典后的分词结果： " + result);
			 System.out.println("分词完成");
			//保存用户字典
			testICTCLAS50.ICTCLAS_SaveTheUsrDic();
			//释放分词组件资源
			testICTCLAS50.ICTCLAS_Exit();
	    }
	    catch (Exception ex)
	    {  ex.printStackTrace();   }
//	    ReadAndWriteFileFromDirectory.writeStringToFile(result, toPath, filename, "txt");
	    return result;
	}

	
	
	
	
	

	/**
	 * 传入一个字符串，返回一个分词结果。 本方法采用Ictclas分词。 本方法来自ICTCLAS中的testMain方法。 
	 * @param str
	 * @return   分词结果
	 */
	public static String getSplitWordsByICTCLASWrtFile(String str,String toPath,String filename){
		if (String_handle_Zack_Util.isEmpty(toPath)) {
			toPath="W:\\zackjob\\hacker\\nlp_workspace_files\\";
		}
		String result="";
		try
		{
			ICTCLAS50 testICTCLAS50 = new ICTCLAS50();
			String argu = ".";
			//初始化
			if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
			{
				System.out.println("Init Fail!");
				return "";
			}
			//设置词性标注集(0 计算所二级标注集，1 计算所一级标注集，2 北大二级标注集，3 北大一级标注集)
			testICTCLAS50.ICTCLAS_SetPOSmap(2);
			//导入用户词典前分词
			byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 0, 1);//分词处理
			 result = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
		}
		catch (Exception ex)
		{ 	ex.printStackTrace();	}
		ReadAndWriteFileFromDirectory.writeStringToFile(result, toPath, filename, "txt");
		return result;
	}
	

	
	/**
	 * 传入一个字符串，返回一个分词结果。 本方法采用Ictclas分词。 本方法来自ICTCLAS中的testMain方法。 
	 * @param str
	 * @return   分词结果
	 */
	public static String getSplitWordsByICTCLAS(String str){
		String result="";
		try
		{
			ICTCLAS50 testICTCLAS50 = new ICTCLAS50();
			String argu = ".";
			//初始化
			if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
			{
				System.out.println("Init Fail!");
				return "";
			}
			//设置词性标注集(0 计算所二级标注集，1 计算所一级标注集，2 北大二级标注集，3 北大一级标注集)
			testICTCLAS50.ICTCLAS_SetPOSmap(2);
			//导入用户词典前分词
			byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 0, 1);//分词处理
//			System.out.println(nativeBytes.length);
			 result = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
//			System.out.println("未导入用户词典的分词结果： " + result);//打印结果
//			//导入用户字典
//			int nCount = 0;
//			String usrdir = "userdict.txt"; //用户字典路径
//			byte[] usrdirb = usrdir.getBytes();//将string转化为byte类型
//			//导入用户字典,返回导入用户词语个数第一个参数为用户字典路径，第二个参数为用户字典的编码类型
//			nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
//			System.out.println("导入用户词个数" + nCount);
//			nCount = 0;
//
//			//导入用户字典后再分词
//			byte nativeBytes1[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 2, 0);
//			System.out.println(nativeBytes1.length);
//			String nativeStr1 = new String(nativeBytes1, 0, nativeBytes1.length, "GB2312");
//			System.out.println("导入用户词典后的分词结果： " + nativeStr1);
//			//保存用户字典
//			testICTCLAS50.ICTCLAS_SaveTheUsrDic();
//			//释放分词组件资源
//			testICTCLAS50.ICTCLAS_Exit();
		}
		catch (Exception ex)
		{ 	ex.printStackTrace();	}
		return result;
	}
	
	
	
	
	/**
	 * 传入一个字符串，对其进行分词。 
	 * @param str
	 * @return 返回分词后的字符串。
	 */
	public static String getSplitWordsByIKanalyzer(String str){
		String result="";
		if (String_handle_Zack_Util.isEmpty(str)) {
			return "";
		}
		try {
			//使用字节流
			byte[] bt=str.getBytes();
			InputStream ip=new ByteArrayInputStream(bt);
			Reader read=new InputStreamReader(ip);
			//使用iksegmenter  true: 启用智能分词
			IKSegmenter ikSeg=new IKSegmenter(read, true);
			Lexeme tLexeme;
			//获取分词的结果。 
			while ((tLexeme=ikSeg.next())!=null) {
				result+=tLexeme.getLexemeText()+"---";
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	

	
}
