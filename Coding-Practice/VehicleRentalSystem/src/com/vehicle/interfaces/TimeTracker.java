package com.vehicle.interfaces;

import java.time.LocalDateTime;

//Interface which contains now and format functions
public interface TimeTracker {
	default String now() { 
		return LocalDateTime.now().toString(); 
	}
	static String format(LocalDateTime dt) { 
		return dt.toString(); 
	}

}
