package Primary_data_type_zack_util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class createID_Unique {
		
	/**
	 * 获取一个关于时间的字符串，可以当成文件名使用。
	 * @return  一个时间的字符串
	 */
	public static String getIDByTimeCalender(){
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		Calendar calendar = Calendar.getInstance();
		String idString = df.format(calendar.getTime());
		return idString;
	}
	
	
}
