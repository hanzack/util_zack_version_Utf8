package Primary_data_type_zack_util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class createID_Unique {
		
	/**
	 * ��ȡһ������ʱ����ַ��������Ե����ļ���ʹ�á�
	 * @return  һ��ʱ����ַ���
	 */
	public static String getIDByTimeCalender(){
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		Calendar calendar = Calendar.getInstance();
		String idString = df.format(calendar.getTime());
		return idString;
	}
	
	
}
