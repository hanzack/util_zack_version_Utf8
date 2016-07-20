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
		String contString="̤ʵ��һ�����������Ҫ����ɲ���";
		String toPath="W:\\";
		String fileName="abc.txt";
		getSplitWordsByICTCLASWrtFile(contString, toPath, fileName);
		
	}
	
	
	
	/**
	 * ��ȫ��������
	 * ����һƪ��ͨ���£� Ȼ���������������½��зִʣ��ִ�֮���ټ���ÿ���ʳ��ֵĴ�����Ȼ��һ����ռһ��
	 * ����ָ���ļ���
	 * ��������м������ִʺ���ļ���Ϊ�м��ļ���Ȼ���һ�������Ƶ
	 * ���� ��������ļ���������ݻᱻ���зִʣ�Ȼ��һ����һ���ʵļ�����ֵĴ���
	 * @param fromFilePath
	 * @param toFilePath
	 * @param segFilePath  ��Ϊ�м��漰����Ҫ�����½��зִʣ��м����Ҫ�洢�ִ��ļ���
	 */
	public static void getTFfromFile(String fromFilePath,String toFilePath,String segFilePath){
		//��һƪ���½��зִʣ����ҰѷִʺõĽ���ļ����ظ�afterSegGile
		String afterSegFile=segmentFileWrtToFile(fromFilePath, segFilePath);
		//����������յ���һ�����ִʺ�����£�Ȼ�������ִʺ�����½��д���
		countTFfromsegFilewrtFile(afterSegFile,toFilePath);
	}
	
	
	
	/**
	 * ����һ���ִ��ļ�������������Ѿ��ֺôʵ��ˣ������зִʱ�ע����������������ÿһ���ʳ��ֵĴ�����
	 * ����һ����ռһ�д����ļ�
	 * 
	 * @param afterSegFile
	 */
	public static void countTFfromsegFilewrtFile(String afterSegFile,String toFile){
		Map<String, Integer> wordTFMap=new HashMap<String, Integer>();
		//��ȡÿһ�����ݣ���ÿһ��������ӽ�map��
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		try {
			inputStream = new FileInputStream(afterSegFile);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
			String line = sc.nextLine();
			//ÿһ�е����д�
			String[] lineArr=line.split("\\s");
			for (String word:lineArr) {
				if (wordTFMap.containsKey(word)) {//�ж��Ƿ�����������
                   //�����������ʣ� 
					int countword=wordTFMap.get(word)+1;
					wordTFMap.put(word, countword);
				}else {
					wordTFMap.put(word, 1);
				}
			}
			System.out.println("ÿһ�еĳ���: "+lineArr.length);
			}
			System.out.println("����wordMap�ĳ���: "+wordTFMap.size());
			
			//�ѽ����ӡ���ĵ���ȥ
			String lineCont="";
			Set<String> keySet=wordTFMap.keySet();
			for (String keyword:keySet) { //ȥ���ո�
				if (keyword.matches("\\s+")||keyword.equals("")) {
					continue;
				}
				lineCont=keyword+"\t"+wordTFMap.get(keyword)+"\r\n";
				ReadAndWriteFileFromDirectory.writeStringToFileWithFileName(lineCont, toFile);
			}
			System.out.println("������ϣ����ҰѴ�Ƶд���ˣ� "+toFile);
			
			
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
	
	
	

	/**��һƪ�ĵ����зִʻ�����Ȼ���������һ���ļ���, ����ICTCLAS��������ָ��ʹ�ô��Ա�ע��
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
			//ÿ1000�зִ�һ��
			if (contentcount>=1000) {
				//��ȡ�ִ��ַ���
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
	 * ����һ���ַ���������һ���ִʽ����  �����û��ֵ䡣
	 * @param str
	 * @return   �ִʽ��
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
	        //��ʼ��
	        if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
	        {
	            System.out.println("Init Fail!");
	            return "";
	        }
	        //���ô��Ա�ע��(0 ������������ע����1 ������һ����ע����2 ���������ע����3 ����һ����ע��)
	        testICTCLAS50.ICTCLAS_SetPOSmap(1);
//	        //�����û��ʵ�ǰ�ִ�
//	        byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 0, postTag0or1);//�ִʴ��� 1 ������Ա�ע��
//	         result = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
	        
	    	
	    	//�����û��ֵ�   
			int nCount = 0;
			String usrdir = "userdict.txt"; //�û��ֵ�·��
			byte[] usrdirb = usrdir.getBytes();//��stringת��Ϊbyte����
			//�����û��ֵ�,���ص����û����������һ������Ϊ�û��ֵ�·�����ڶ�������Ϊ�û��ֵ�ı�������
			nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
//			System.out.println("�����û��ʸ���" + nCount);
			nCount = 0;

			//�����û��ֵ���ٷִ�
//			byte nativeBytes1[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 2, postTag0or1);//�Ƿ���д��Ա�ע
			byte nativeBytes1[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 2, postTag0or1);//�Ƿ���д��Ա�ע
//			System.out.println(nativeBytes1.length);
			 result = new String(nativeBytes1, 0, nativeBytes1.length, "GB2312");
//			System.out.println("�����û��ʵ��ķִʽ���� " + result);
			 System.out.println("�ִ����");
			//�����û��ֵ�
			testICTCLAS50.ICTCLAS_SaveTheUsrDic();
			//�ͷŷִ������Դ
			testICTCLAS50.ICTCLAS_Exit();
	    }
	    catch (Exception ex)
	    {  ex.printStackTrace();   }
//	    ReadAndWriteFileFromDirectory.writeStringToFile(result, toPath, filename, "txt");
	    return result;
	}

	
	
	
	
	

	/**
	 * ����һ���ַ���������һ���ִʽ���� ����������Ictclas�ִʡ� ����������ICTCLAS�е�testMain������ 
	 * @param str
	 * @return   �ִʽ��
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
			//��ʼ��
			if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
			{
				System.out.println("Init Fail!");
				return "";
			}
			//���ô��Ա�ע��(0 ������������ע����1 ������һ����ע����2 ���������ע����3 ����һ����ע��)
			testICTCLAS50.ICTCLAS_SetPOSmap(2);
			//�����û��ʵ�ǰ�ִ�
			byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 0, 1);//�ִʴ���
			 result = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
		}
		catch (Exception ex)
		{ 	ex.printStackTrace();	}
		ReadAndWriteFileFromDirectory.writeStringToFile(result, toPath, filename, "txt");
		return result;
	}
	

	
	/**
	 * ����һ���ַ���������һ���ִʽ���� ����������Ictclas�ִʡ� ����������ICTCLAS�е�testMain������ 
	 * @param str
	 * @return   �ִʽ��
	 */
	public static String getSplitWordsByICTCLAS(String str){
		String result="";
		try
		{
			ICTCLAS50 testICTCLAS50 = new ICTCLAS50();
			String argu = ".";
			//��ʼ��
			if (testICTCLAS50.ICTCLAS_Init(argu.getBytes("GB2312")) == false)
			{
				System.out.println("Init Fail!");
				return "";
			}
			//���ô��Ա�ע��(0 ������������ע����1 ������һ����ע����2 ���������ע����3 ����һ����ע��)
			testICTCLAS50.ICTCLAS_SetPOSmap(2);
			//�����û��ʵ�ǰ�ִ�
			byte nativeBytes[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 0, 1);//�ִʴ���
//			System.out.println(nativeBytes.length);
			 result = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
//			System.out.println("δ�����û��ʵ�ķִʽ���� " + result);//��ӡ���
//			//�����û��ֵ�
//			int nCount = 0;
//			String usrdir = "userdict.txt"; //�û��ֵ�·��
//			byte[] usrdirb = usrdir.getBytes();//��stringת��Ϊbyte����
//			//�����û��ֵ�,���ص����û����������һ������Ϊ�û��ֵ�·�����ڶ�������Ϊ�û��ֵ�ı�������
//			nCount = testICTCLAS50.ICTCLAS_ImportUserDictFile(usrdirb, 0);
//			System.out.println("�����û��ʸ���" + nCount);
//			nCount = 0;
//
//			//�����û��ֵ���ٷִ�
//			byte nativeBytes1[] = testICTCLAS50.ICTCLAS_ParagraphProcess(str.getBytes("GB2312"), 2, 0);
//			System.out.println(nativeBytes1.length);
//			String nativeStr1 = new String(nativeBytes1, 0, nativeBytes1.length, "GB2312");
//			System.out.println("�����û��ʵ��ķִʽ���� " + nativeStr1);
//			//�����û��ֵ�
//			testICTCLAS50.ICTCLAS_SaveTheUsrDic();
//			//�ͷŷִ������Դ
//			testICTCLAS50.ICTCLAS_Exit();
		}
		catch (Exception ex)
		{ 	ex.printStackTrace();	}
		return result;
	}
	
	
	
	
	/**
	 * ����һ���ַ�����������зִʡ� 
	 * @param str
	 * @return ���طִʺ���ַ�����
	 */
	public static String getSplitWordsByIKanalyzer(String str){
		String result="";
		if (String_handle_Zack_Util.isEmpty(str)) {
			return "";
		}
		try {
			//ʹ���ֽ���
			byte[] bt=str.getBytes();
			InputStream ip=new ByteArrayInputStream(bt);
			Reader read=new InputStreamReader(ip);
			//ʹ��iksegmenter  true: �������ִܷ�
			IKSegmenter ikSeg=new IKSegmenter(read, true);
			Lexeme tLexeme;
			//��ȡ�ִʵĽ���� 
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
