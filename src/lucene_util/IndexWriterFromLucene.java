package lucene_util;

import java.io.File;










import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import Primary_data_type_zack_util.String_handle_Zack_Util;
import Primary_data_type_zack_util.createID_Unique;

public class IndexWriterFromLucene{

	
	
	
	/**
	 * 把字符串添加进索引
	 */
	@Test
	public void test_MapToLuceneIndex(){
		Map<String, String> map1=new HashMap<String, String>();
		map1.put("go", "she is a good girl");
		map1.put("he","he can fall in love with her");
		map1.put("he1","he1 wants to buy a dog");
		map1.put("lilei","xiaoming wants to buy a dog");
		map1.put("hanmeimei","hanmeimei wants to buy a dog");
		String indexPath="W:\\zackjob\\hacker\\nlp_workspace_files\\luceneIndex_10\\lucene_index\\";
		MapToLuceneIndex(map1,indexPath,"Name","content",true);
		System.out.println("finish writing");
	}
	
	
	
	
	
	
	
	
	/**
	 * 传入一个map，把它的key 与value加入索引中相互对应。
	 * 
	 * @param amap: 需要被索引的map， 例如map的key   写入字段 title,     map的value写入  叫做content的字段
	 * @param indexPath：  要把索引存储到什么目录？如果没有该目录则创建目录
	 * 例如  W:\\zackjob\\hacker\\nlp_workspace_files\\luceneIndex_10\\lucene_index\\
	 * @param keyFieldName： 需要给map做索引，你希望map的key值，被存成什么名字？ 为空则把key  存入到 “title” 这一列。
	 * @param valueFieldName： 需要给map做索引，你希望map的value值，被存成什么名字？ 为空则把value  存入到 “content” 这一列。
	 * @param needId:  是否建立id，加入id
	 */
	public static boolean MapToLuceneIndex(Map<String, String> amap,String indexPath,String keyFieldName, String valueFieldName, boolean needId){
		if (null==amap||"".equals(indexPath)) {
			return false;
		}
		if (String_handle_Zack_Util.isEmpty(keyFieldName)) {
			keyFieldName="key";
		}
		if (String_handle_Zack_Util.isEmpty(valueFieldName)) {
			valueFieldName="value";
		}
		try {
	//第一步先指定一个analyzer，这里使用标准的，当然也可以使用IK分词器。
			Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_46);
			//			这里也可以使用庖丁分词器， 或者IK分词器。
			//			Analyzer a1=new IKAnalyzer();
			//			  Analyzer analyzer=new PaodingAnalyzer();
						
	//第二步	 打开传进来的这个文件。写成Direc的形式。 这个是一个文件夹， 需要把索引存储到的目标文件。	
			Directory dir=FSDirectory.open(new File(indexPath));
	//第三步     配置IndexWriter，穿进去分词器 		
			IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_46, analyzer);
	//第四步， 创建writer  准备把索引写入到dir中去。		
			IndexWriter inWriter= new IndexWriter(dir,config);
			
	
			
			//接下来，是循环，map， 把map的key存入字段  keyFieldName, 把map的value存入valueFieldName字段中
			Set<String> set1=amap.keySet();
			int count=0;
			for (String key:set1) {
				//第五步     创建Document对象， 用来向里面添加字段。
				Document document=new Document();
				count++;
				System.out.println(count+" key: "+key+" value: "+amap.get(key)+"--- keyFieldName: "+keyFieldName+" valueField: "+valueFieldName);
	//第六步     接下来把字符串放进字段里面去。 例如  对于一个字符串  "1,小明,  喜欢上学"
			//那么我希望建立成的索引就是, id:1     title: 小明     content：喜欢上学。
			//存储title， 内容是  key1 key,   进行存储
			IndexableField keyField=new StringField(keyFieldName, key, Field.Store.YES);
			//存储内容， 字段的类型 是text类型的。 进行存储。
			IndexableField valueField =new TextField(valueFieldName,amap.get(key),Field.Store.YES);
			if (needId) {
				//第七步   把这些字段加入到document中去， 或者说把表格添加进document中。
				//这相当于建成一个表格，每一列指定一个名字 id   title, content  然后往里面填入内容
				//存储id的field， 名字叫做id,  field类型是int ， id对应的值是1，  进行存储
				IndexableField idField=new IntField("id", count, Field.Store.YES);
				document.add(idField);
			}
			document.add(keyField);
			document.add(valueField);
			//第八步    把document加入到writer中 	
			inWriter.addDocument(document);
			}
		
	//第九步     把内容写入并关闭。
			inWriter.commit();//提交索引
			inWriter.close();//关闭。
			System.out.println("一共加进了 "+count+" 条 索引，进"+indexPath+" 目录中！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	
	
	
	/**
	 * @param indexPath   需要把建立好的索引文件存入什么地方？ 
	 * 例如  W:\\zackjob\\hacker\\nlp_workspace_files\\luceneIndex_10\\lucene_index\\
	 */
	public static void indexDemo(String indexPath){
		try {
	//第一步先指定一个analyzer，这里使用标准的，当然也可以使用IK分词器。
			Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_46);
			//			这里也可以使用庖丁分词器， 或者IK分词器。
			//			Analyzer a1=new IKAnalyzer();
			//			  Analyzer analyzer=new PaodingAnalyzer();
						
	//第二步	 打开传进来的这个文件。写成Direc的形式。 这个是一个文件夹， 需要把索引存储到的目标文件。	
			Directory dir=FSDirectory.open(new File(indexPath));
	//第三步     配置IndexWriter，穿进去分词器 		
			IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_46, analyzer);
	//第四步， 创建writer  准备把索引写入到dir中去。		
			IndexWriter inWriter= new IndexWriter(dir,config);
			
	//第五步     创建Document对象， 用来向里面添加字段。
			Document document=new Document();
	//第六步     接下来把字符串放进字段里面去。 例如  对于一个字符串  "1,小明,  喜欢上学"
			//那么我希望建立成的索引就是, id:1     title: 小明     content：喜欢上学。
			//这相当于建成一个表格，每一列指定一个名字 id   title, content  然后往里面填入内容
			//存储id的field， 名字叫做id,  field类型是int ， id对应的值是1，  进行存储
			IndexableField idField=new IntField("id", 1, Field.Store.YES);
			//存储title， 内容是  key1 key,   进行存储
			IndexableField titleField=new StringField("title", "key1 key2", Field.Store.YES);
			//存储内容， 字段的类型 是text类型的。 进行存储。
			IndexableField contentField =new TextField("content","key3 key4",Field.Store.YES);
			
	//第七步   把这些字段加入到document中去， 或者说把表格添加进document中。
			document.add(idField);
			document.add(titleField);
			document.add(contentField);
	//第八步    把document加入到writer中 		
			inWriter.addDocument(document);
	//第九步     把内容写入并关闭。
			inWriter.commit();//提交索引
			inWriter.close();//关闭。
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
