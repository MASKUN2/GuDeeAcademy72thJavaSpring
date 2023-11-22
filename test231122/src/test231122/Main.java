package test231122;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		int requestYear;
		int requestMonth;
		
		LocalDate requestDate;
			requestDate = LocalDate.now().withDayOfMonth(1);
		
		System.out.println(requestDate);
		
		System.out.println(requestDate.getDayOfWeek());
		
		

	}

}
