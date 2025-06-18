package time;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Test1 {
	public static void main(String[] args) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(timestamp);
		System.out.println(timestamp.toString());
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh-mm-ss");
		System.out.println(sdf.format(timestamp));
	}
}
