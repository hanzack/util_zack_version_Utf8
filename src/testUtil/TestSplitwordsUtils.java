package testUtil;

import org.junit.Test;

import segmentation_nlp_Handle_Zack_util.splitWords_util;

public class TestSplitwordsUtils {

	@Test
	public void test_IkSplitWords(){
		String new1="�����������뿪�������س� �� Ԥ����������� ���� �� �ص� ���� �� ���Ͼ��ǽ�������� ���� ��̬ ";
		String resString=splitWords_util.getSplitWordsByIKanalyzer(new1);
		System.out.println(resString);
//	Ictclas:	 ���/d ��/nr ����/n ��/d �뿪/v ��/u �����س�/ZQXC ��/w Ԥ��/v ��/nr ����/n ����/t ����/t ��/d �ص�/v ����/ns ��/w ����/f ��/d ��/v ����/t ����/t ��/u ����/a ��̬/n 
//IK �ִʣ�δ�������ִܷʣ� ���---������---����---��---�뿪---����---������---����---�س�---Ԥ��---����---������---����---����---����---��---�ص�---����---����---����---��������---����---����---����---��---����---��̬---
//IK �ִʣ��������ִܷʣ�  ���---������---��---��---����---����---�س�---Ԥ��---������---����---����---��---�ص�---����---����---����---��������---��---����---��̬---
	}
	
	
	
	@Test
	public void test_getSplitWordsByICTCLAS(){
		String new1="�����������뿪�������س� �� Ԥ����������� ���� �� �ص� ���� �� ���Ͼ��ǽ�������� ���� ��̬ ";
		String resString=splitWords_util.getSplitWordsByICTCLAS(new1);
		System.out.println(resString);
	}
	
	
	@Test
	public void test_getSplitWordsByICTCLASWrtfile(){
		String new1="�����������뿪�������س� �� Ԥ����������� ���� �� �ص� ���� �� ���Ͼ��ǽ�������� ���� ��̬ ";
		String resString=splitWords_util.getSplitWordsByICTCLASWrtFile(new1, "", "");
		System.out.println(resString);
	}
	
	
	
	
}
