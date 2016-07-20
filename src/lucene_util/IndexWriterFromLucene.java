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
	 * ���ַ�����ӽ�����
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
	 * ����һ��map��������key ��value�����������໥��Ӧ��
	 * 
	 * @param amap: ��Ҫ��������map�� ����map��key   д���ֶ� title,     map��valueд��  ����content���ֶ�
	 * @param indexPath��  Ҫ�������洢��ʲôĿ¼�����û�и�Ŀ¼�򴴽�Ŀ¼
	 * ����  W:\\zackjob\\hacker\\nlp_workspace_files\\luceneIndex_10\\lucene_index\\
	 * @param keyFieldName�� ��Ҫ��map����������ϣ��map��keyֵ�������ʲô���֣� Ϊ�����key  ���뵽 ��title�� ��һ�С�
	 * @param valueFieldName�� ��Ҫ��map����������ϣ��map��valueֵ�������ʲô���֣� Ϊ�����value  ���뵽 ��content�� ��һ�С�
	 * @param needId:  �Ƿ���id������id
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
	//��һ����ָ��һ��analyzer������ʹ�ñ�׼�ģ���ȻҲ����ʹ��IK�ִ�����
			Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_46);
			//			����Ҳ����ʹ���Ҷ��ִ����� ����IK�ִ�����
			//			Analyzer a1=new IKAnalyzer();
			//			  Analyzer analyzer=new PaodingAnalyzer();
						
	//�ڶ���	 �򿪴�����������ļ���д��Direc����ʽ�� �����һ���ļ��У� ��Ҫ�������洢����Ŀ���ļ���	
			Directory dir=FSDirectory.open(new File(indexPath));
	//������     ����IndexWriter������ȥ�ִ��� 		
			IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_46, analyzer);
	//���Ĳ��� ����writer  ׼��������д�뵽dir��ȥ��		
			IndexWriter inWriter= new IndexWriter(dir,config);
			
	
			
			//����������ѭ����map�� ��map��key�����ֶ�  keyFieldName, ��map��value����valueFieldName�ֶ���
			Set<String> set1=amap.keySet();
			int count=0;
			for (String key:set1) {
				//���岽     ����Document���� ��������������ֶΡ�
				Document document=new Document();
				count++;
				System.out.println(count+" key: "+key+" value: "+amap.get(key)+"--- keyFieldName: "+keyFieldName+" valueField: "+valueFieldName);
	//������     ���������ַ����Ž��ֶ�����ȥ�� ����  ����һ���ַ���  "1,С��,  ϲ����ѧ"
			//��ô��ϣ�������ɵ���������, id:1     title: С��     content��ϲ����ѧ��
			//�洢title�� ������  key1 key,   ���д洢
			IndexableField keyField=new StringField(keyFieldName, key, Field.Store.YES);
			//�洢���ݣ� �ֶε����� ��text���͵ġ� ���д洢��
			IndexableField valueField =new TextField(valueFieldName,amap.get(key),Field.Store.YES);
			if (needId) {
				//���߲�   ����Щ�ֶμ��뵽document��ȥ�� ����˵�ѱ����ӽ�document�С�
				//���൱�ڽ���һ�����ÿһ��ָ��һ������ id   title, content  Ȼ����������������
				//�洢id��field�� ���ֽ���id,  field������int �� id��Ӧ��ֵ��1��  ���д洢
				IndexableField idField=new IntField("id", count, Field.Store.YES);
				document.add(idField);
			}
			document.add(keyField);
			document.add(valueField);
			//�ڰ˲�    ��document���뵽writer�� 	
			inWriter.addDocument(document);
			}
		
	//�ھŲ�     ������д�벢�رա�
			inWriter.commit();//�ύ����
			inWriter.close();//�رա�
			System.out.println("һ���ӽ��� "+count+" �� ��������"+indexPath+" Ŀ¼�У�");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	
	
	
	/**
	 * @param indexPath   ��Ҫ�ѽ����õ������ļ�����ʲô�ط��� 
	 * ����  W:\\zackjob\\hacker\\nlp_workspace_files\\luceneIndex_10\\lucene_index\\
	 */
	public static void indexDemo(String indexPath){
		try {
	//��һ����ָ��һ��analyzer������ʹ�ñ�׼�ģ���ȻҲ����ʹ��IK�ִ�����
			Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_46);
			//			����Ҳ����ʹ���Ҷ��ִ����� ����IK�ִ�����
			//			Analyzer a1=new IKAnalyzer();
			//			  Analyzer analyzer=new PaodingAnalyzer();
						
	//�ڶ���	 �򿪴�����������ļ���д��Direc����ʽ�� �����һ���ļ��У� ��Ҫ�������洢����Ŀ���ļ���	
			Directory dir=FSDirectory.open(new File(indexPath));
	//������     ����IndexWriter������ȥ�ִ��� 		
			IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_46, analyzer);
	//���Ĳ��� ����writer  ׼��������д�뵽dir��ȥ��		
			IndexWriter inWriter= new IndexWriter(dir,config);
			
	//���岽     ����Document���� ��������������ֶΡ�
			Document document=new Document();
	//������     ���������ַ����Ž��ֶ�����ȥ�� ����  ����һ���ַ���  "1,С��,  ϲ����ѧ"
			//��ô��ϣ�������ɵ���������, id:1     title: С��     content��ϲ����ѧ��
			//���൱�ڽ���һ�����ÿһ��ָ��һ������ id   title, content  Ȼ����������������
			//�洢id��field�� ���ֽ���id,  field������int �� id��Ӧ��ֵ��1��  ���д洢
			IndexableField idField=new IntField("id", 1, Field.Store.YES);
			//�洢title�� ������  key1 key,   ���д洢
			IndexableField titleField=new StringField("title", "key1 key2", Field.Store.YES);
			//�洢���ݣ� �ֶε����� ��text���͵ġ� ���д洢��
			IndexableField contentField =new TextField("content","key3 key4",Field.Store.YES);
			
	//���߲�   ����Щ�ֶμ��뵽document��ȥ�� ����˵�ѱ����ӽ�document�С�
			document.add(idField);
			document.add(titleField);
			document.add(contentField);
	//�ڰ˲�    ��document���뵽writer�� 		
			inWriter.addDocument(document);
	//�ھŲ�     ������д�벢�رա�
			inWriter.commit();//�ύ����
			inWriter.close();//�رա�
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
