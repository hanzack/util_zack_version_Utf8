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
	 * �ļ��������Ժ� ��Щ��ֻ��һ���֣������������ȥ����Щ���ֵ��У�����������һ���µ��ļ�
	 * @param fromPath   ������ļ���·��
	 * @param toPath      ���ļ���ĵ��ֵ��и�ȥ�����������µ��ļ�����ȥ
	 */
	public static void deleteSingleWordsCreateNewFile(String fromPath, String toPath){
		
		try {
			String result="";
			File file=new File(fromPath);
			InputStream is=new FileInputStream(file);
			InputStreamReader isReader=new InputStreamReader(is,"gb2312");
			BufferedReader bf=new BufferedReader(isReader);
			String temp="";
			//��ȡ �������ļ������н��룬ȥ�����֡�
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
	 * ����һ���ļ� ȥ���ļ����ظ����У������ظ��Ĵʡ� ����Ҫ�����õ�ʱ��ָ����һ�ִַ���ʽ�� ��������°��� "\r\n" ����list�� ��ô����ȥ���ظ����С�
	 * Ȼ��д���µ��ļ���
	 *
	 * @param fromPath     D://abc.txt
	 * @param toPath       D://jj//kkk.txt
	 * @param splitMark   //���� "\r\n"    �����ָ���ָ���ţ���Ĭ��ʹ�ûس����ж����½���д��list
	 */
	public static void deleteDuplicateLineFromFiles(String fromPath,String toPath, String splitMark){
		if (null==splitMark || splitMark=="") {
			splitMark="\r\n";//�����ָ���ָ���ţ���Ĭ��ʹ�ûس����ж����½���д��list
		}
		
//		System.out.println("fromPath::  "+fromPath);
//		System.out.println("toPath::  "+toPath);
		String wholeContent="";
		//��ȡ����ļ�������д��list����ȥ
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
