package com.petshopapp.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloServletTest {
	
	@Test
    public void sum() {
		assertEquals(20, 20);
    }
	
	public static void main(String[] args) {
		System.out.println("HelloServlet Testing");
		HelloServletTest helloServletTest=new HelloServletTest();
		helloServletTest.sum();
	}
	

}
