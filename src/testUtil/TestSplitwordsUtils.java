package testUtil;

import org.junit.Test;

import segmentation_nlp_Handle_Zack_util.splitWords_util;

public class TestSplitwordsUtils {

	@Test
	public void test_IkSplitWords(){
		String new1="随后温总理就离开了舟曲县城 ， 预计温总理今天 下午 就 回到 北京 。 以上就是今天上午的 最新 动态 ";
		String resString=splitWords_util.getSplitWordsByIKanalyzer(new1);
		System.out.println(resString);
//	Ictclas:	 随后/d 温/nr 总理/n 就/d 离开/v 了/u 舟曲县城/ZQXC ，/w 预计/v 温/nr 总理/n 今天/t 下午/t 就/d 回到/v 北京/ns 。/w 以上/f 就/d 是/v 今天/t 上午/t 的/u 最新/a 动态/n 
//IK 分词，未开启智能分词： 随后---温总理---总理---就---离开---开了---舟曲县---舟曲---县城---预计---计温---温总理---总理---今天---下午---就---回到---北京---以上---就是---今天上午---今天---天上---上午---的---最新---动态---
//IK 分词，开启智能分词：  随后---温总理---就---离---开了---舟曲---县城---预计---温总理---今天---下午---就---回到---北京---以上---就是---今天上午---的---最新---动态---
	}
	
	
	
	@Test
	public void test_getSplitWordsByICTCLAS(){
		String new1="随后温总理就离开了舟曲县城 ， 预计温总理今天 下午 就 回到 北京 。 以上就是今天上午的 最新 动态 ";
		String resString=splitWords_util.getSplitWordsByICTCLAS(new1);
		System.out.println(resString);
	}
	
	
	@Test
	public void test_getSplitWordsByICTCLASWrtfile(){
		String new1="随后温总理就离开了舟曲县城 ， 预计温总理今天 下午 就 回到 北京 。 以上就是今天上午的 最新 动态 ";
		String resString=splitWords_util.getSplitWordsByICTCLASWrtFile(new1, "", "");
		System.out.println(resString);
	}
	
	
	
	
}
