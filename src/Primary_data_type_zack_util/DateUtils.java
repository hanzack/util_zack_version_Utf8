package Primary_data_type_zack_util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static Date getYear(String year){
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		try {
			date = sdf.parse(year);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}
