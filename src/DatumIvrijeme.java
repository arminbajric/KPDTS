
import java.io.IOException;

import java.time.LocalDateTime;

public class DatumIvrijeme {

	public String getHours() throws IOException {

		int sat = LocalDateTime.now().getHour();
		String Hours = null;
		if (sat < 10) {
			Hours = "0" + String.valueOf(sat);
		} else {
			Hours = String.valueOf(sat);
		}
		return Hours;
	}

	public String getMinutes() throws IOException {

		int minute = LocalDateTime.now().getMinute();
		String Minutes = null;
		if (minute < 10) {
			Minutes = "0" + String.valueOf(minute);
		} else {
			Minutes = String.valueOf(minute);
		}
		return Minutes;
	}

	public String getSekunde() throws IOException {

		int sekunde = LocalDateTime.now().getSecond();
		String Seconds = null;
		if (sekunde < 10) {
			Seconds = "0" + String.valueOf(sekunde);
		} else {
			Seconds = String.valueOf(sekunde);
		}
		return Seconds;
	}

	public String getYear() {
		int y = LocalDateTime.now().getYear();

		return String.valueOf(y);

	}

	public String getMonth() {
		int m = LocalDateTime.now().getMonthValue();
		String month;
		if (m < 10) {
			month = "0" + String.valueOf(m);
		} else {
			month = String.valueOf(m);
		}
		return month;

	}

	public String getDay() {
		int d = LocalDateTime.now().getDayOfMonth();
		String day = null;
		if (d < 10) {
			day = "0" + String.valueOf(d);

		} else {
			day = String.valueOf(d);
		}
		return day;
	}

	public String getMonthint(String month) {
		int MM = 0;
		switch (month) {
		case "Jan":
			MM = 1;
			break;
		case "Feb":
			MM = 2;
			break;
		case "Mar":
			MM = 3;
			break;
		case "Apr":
			MM = 4;
			break;
		case "May":
			MM = 5;
			break;
		case "Jun":
			MM = 6;
			break;
		case "Jul":
			MM = 7;
			break;
		case "Aug":
			MM = 8;
			break;
		case "Sep":
			MM = 9;
			break;
		case "Oct":
			MM = 10;
		case "Nov":
			MM = 11;
			break;
		case "Dec":
			MM = 12;
			break;

		}
		String mm;
		if (MM < 10) {
			mm = "0" + String.valueOf(MM);
		} else {
			mm = String.valueOf(MM);
		}

		return mm;
	}
}
