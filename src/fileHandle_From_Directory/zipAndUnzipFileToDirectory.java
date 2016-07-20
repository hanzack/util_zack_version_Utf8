package fileHandle_From_Directory;  
  
import java.io.*;  
import java.util.zip.*;  

import org.junit.Test;

import Primary_data_type_zack_util.String_handle_Zack_Util;
/** 
 * 程序实现了ZIP压缩。共分为2部分 ： 
 * 压缩（compression）与解压（decompression） 
 * <p> 
 * 大致功能包括用了多态，递归等JAVA核心技术，可以对单个文件和任意级联文件夹进行压缩和解压。 
 * 需在代码中自定义源输入路径和目标输出路径。 
 * <p> 
 * 在本段代码中，实现的是解压部分；压缩部分见本包中compression部分。 
 * @author HAN 
 * 
 */  
public class zipAndUnzipFileToDirectory {  
	
	
	@Test
	public void test_unZipFile(){
		String fileString="W:\\zackjob\\testSomeThingHere\\testZip\\company.zip";
		String topathString="W:\\zackjob\\testSomeThingHere\\unzip\\";
		System.out.println(unZipFile(fileString,topathString));
		
	}
	
	
	
      
    /**
     * 传入待压缩文件，和需要解压到的文件夹
     * @param fileNeedUnzip:  例如"W:\\zackjob\\testSomeThingHere\\testZip\\company.zip"
     * @param toPath：    例如："W:\\zackjob\\testSomeThingHere\\unzip"
     * @return
     */
    public static boolean unZipFile(String fileNeedUnzip, String toPath) {  
    	
    	if (String_handle_Zack_Util.isEmpty(fileNeedUnzip)||String_handle_Zack_Util.isEmpty(toPath)) {
			return false;
		}
        // TODO Auto-generated method stub  
        long startTime=System.currentTimeMillis();  
        try {  
            ZipInputStream Zin=new ZipInputStream(new FileInputStream(fileNeedUnzip));//输入源zip路径  
            BufferedInputStream Bin=new BufferedInputStream(Zin);  
            String Parent=toPath; //输出路径（文件夹目录）  
            File Fout=null;  
            ZipEntry entry;  
            try {  
                while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
                    Fout=new File(Parent,entry.getName());  
                    if(!Fout.exists()){  
                        (new File(Fout.getParent())).mkdirs();  
                    }  
                    FileOutputStream out=new FileOutputStream(Fout);  
                    BufferedOutputStream Bout=new BufferedOutputStream(out);  
                    int b;  
                    while((b=Bin.read())!=-1){  
                        Bout.write(b);  
                    }  
                    Bout.close();  
                    out.close();  
                    System.out.println(Fout+"解压成功");      
                }  
                Bin.close();  
                Zin.close();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        long endTime=System.currentTimeMillis();  
        System.out.println("耗费时间： "+(endTime-startTime)+" ms");  
        return true;
    }  
  
} 