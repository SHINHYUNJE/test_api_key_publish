package com.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.company.service.GeminiServiceImpl;

@Configuration
public class AppConfig {
	@Bean
	public GeminiServiceImpl geminiService(Environment env) {
		GeminiServiceImpl service = new GeminiServiceImpl();
		service.setGEMINI_API_KEY(env.getProperty("gemini.api.key"));
		service.setGEMINI_API_URL(env.getProperty("gemini.api.url"));
		return service;
	}
}