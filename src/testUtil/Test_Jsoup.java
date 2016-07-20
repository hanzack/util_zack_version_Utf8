package testUtil;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class Test_Jsoup {

	
	/**
	 * 利用jsoup挖掘数据�?
	 */
	@Test
	public void getContentByJsoup(){
		try {
			File inputFile =new File("W:\\zackjob\\hacker\\web_Bug\\zhihu\\topic\\test.html");
			Document document=Jsoup.parse(inputFile, "UTF-8");
			Element link = document.select("div.abc > p").get(0);
//			Element nodeBlogStats = document.select("div.abc p").get(1);
			Element nodeBlogStats = document.select("div.abc~p").get(2);
			System.out.println(nodeBlogStats.text());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	
}
