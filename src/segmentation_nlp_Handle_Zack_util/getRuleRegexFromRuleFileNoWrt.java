package segmentation_nlp_Handle_Zack_util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class getRuleRegexFromRuleFileNoWrt {

	
	/**�������   n n  �ͻᱻ��װ��  [/u4e00-/u9fa5a-zA-Z0-9]+/n [/u4e00-/u9fa5a-zA-Z0-9]+/n
	 /* * ����һ�������ļ���   ��ÿһ�й��� ��ϳ�һ��������ʽ������ƥ����Ϲ��������
	 * @param inputPath
	 * @return   List<String> 
	 */
	public static List<String> getRulesRegexFromDirectory(String inputPath){
		//�ѹ���д��������ʽ����ʽ��   ��/a ��/b    ������ʽ���������֡� 
		 String regexString="[/u4e00-/u9fa5a-zA-Z0-9]*";
		 List<String> result=new ArrayList<String>();
		 File file=new File(inputPath);
		 FileReader fReader=null;
		 BufferedReader bReader=null;
		 try {
			fReader=new FileReader(file);
			bReader=new BufferedReader(fReader);
			String temp="";
			 while ((temp=bReader.readLine())!=null) {//��ȡÿһ�еĹ���
				 //���ݿո�ѹ�����в��
				 String str="";
				String[] strArray=temp.split(" ");
				for (String rule:strArray) {
					// �ѹ���д��������ʽ����ʽ��
					str+=(regexString+"/"+rule+" ");
				}
				// ȥ�����һ���ո�
				str=str.substring(0,str.length()-1);
				//�ѹ�����뵽result list��ȥ�� 
				result.add(str);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{//��������reader����������йر�
			try {
			if (bReader!=null) {bReader.close();}if (fReader!=null) {fReader.close();}}
			catch (Exception e2) {e2.printStackTrace();}// TODO: handle exception
		}
		 return result;
	 }
	
}
