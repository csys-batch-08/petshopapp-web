package com.petshopapp.logger;

public class Logger {
	
	 
	private Logger() {
	}

	public static void printStackTrace(Exception e) {
		e.printStackTrace();
	}
	

	public static void runTimeException(String message) {
		throw new RuntimeException(message);
	}

}
