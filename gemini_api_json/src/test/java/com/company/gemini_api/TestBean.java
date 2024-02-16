package com.company.gemini_api;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestBean {
	ApplicationContext context;
	
	@Test
	public void test0() {
		context = new GenericXmlApplicationContext("classpath:/config/aiapi-beans.xml");
		System.out.println(context);
	}
}
