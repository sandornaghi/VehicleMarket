package com.scheduler;

import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		Main main = new Main();
		main.runSchedule();
	}
	
	private void runSchedule() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		calendar.set(Calendar.MINUTE, 35);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		Timer time = new Timer();
		
		time.schedule(new ImportTimer(), calendar.getTime(), TimeUnit.HOURS.toMillis(8));
	}
}
