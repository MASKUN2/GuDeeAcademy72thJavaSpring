package test231122;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

	public static void main(String[] args) {
		int requestYear;
		int requestMonth;
		
		LocalDate requestDate;
			requestDate = LocalDate.now().withDayOfMonth(1);
		
		System.out.println(requestDate);
		
		String date	= requestDate.format(DateTimeFormatter.ofPattern("Y-M"));
		System.out.println(date);
		
		String str = "2023-1-1";
		LocalDate datet = LocalDate.parse(str);
		System.out.println(datet);
	}

}
