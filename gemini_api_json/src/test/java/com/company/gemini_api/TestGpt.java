package com.company.gemini_api;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.company.service.AIApiService;
import com.company.service.GeminiServiceImpl;
import com.company.service.GptServiceImpl;
import com.company.util.JsonWriterAndReader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class TestGpt {
	@Autowired
	ApplicationContext context;

	@Test
	@Ignore
	public void test0() {
		System.out.println(context);

	}

	@Test
	@Ignore
	public void test1() {
		JsonWriterAndReader jsonWriterAndReader = new JsonWriterAndReader();
		String jsonFilePath = "src/main/resources/config/gpt-schedule-mapper.json";

		jsonWriterAndReader.updateGPTJson(jsonFilePath, "새로운 사용자 입력");
	}

	@Test
	@Ignore
	public void test2() {
		String jsonFilePath = "src/main/resources/config/gpt-schedule-mapper.json";
		AIApiService aiApiService = new GptServiceImpl();
		// aiApiService = new GeminiServiceImpl();
		System.out.println(aiApiService.generateContent("신촌 롯데시네마 상영시간표 삭제해줘", jsonFilePath));
	}

	@Test
	// @Ignore
	public void test2_1() {
		String jsonFilePath = "src/main/resources/config/gemini-schedule-mapper.json";
		String jsonFilePath2 = "src/main/resources/config/gpt-schedule-mapper.json";
		String result = "";
		String prompt = "홍대 메가박스 2월 18일 상영시간표 보여줘";
		AIApiService aiApiService = (AIApiService) context.getBean("geminiService");
		result = ((GeminiServiceImpl) aiApiService).generateContent(prompt, jsonFilePath);

		if (result.contains("실패") || result.contains("오류")) {
			// Gemini api 오류시 Gpt api 이용하여 서비스 대체
			aiApiService = (AIApiService) context.getBean("gptService");
			System.out.println(((GptServiceImpl) aiApiService).getGPT_API_URL());
			result = ((GptServiceImpl) aiApiService).generateContent(prompt, jsonFilePath2);
		}
		System.out.println(result);
	}

	@Test
	@Ignore
	public void test3() {
		String result = "";
		AIApiService aiApiService = null;
		String prompt = "신촌 롯데시네마 2관 2월 8일 9시 상영시간표 추가해줘";

		aiApiService = new GeminiServiceImpl();
		String jsonFilePath = "src/main/resources/config/gemini-schedule-mapper.json";
		result = aiApiService.generateContent(prompt, jsonFilePath);

		if (result.contains("실패") || result.contains("오류")) {
			aiApiService = new GptServiceImpl();
			String jsonFilePath1 = "src/main/resources/config/gpt-schedule-mapper.json";
			result = aiApiService.generateContent(prompt, jsonFilePath1);
		}

		System.out.println(result);
	}

	@Test
	@Ignore
	public void test4() {
		String GEMINI_API_KEY = "AIzaSyDUZmC5MeUSB4QBjc6CK0Hq6MbQfzhr4Zo";
		String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=";

		System.out.println(GEMINI_API_URL + GEMINI_API_KEY);
	}
}
