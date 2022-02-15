package com.petshopapp.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HelloServletTest {
	int a=5;
	int b=5;
	@Test
    public void sum() {
		assertEquals(10, (a+b));
    }
	
	public static void main(String[] args) {
		System.out.println("HelloServlet Testing");
		HelloServletTest helloServletTest=new HelloServletTest();
		helloServletTest.sum();
	}
	

}
