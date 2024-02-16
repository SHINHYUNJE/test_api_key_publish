package com.company.gemini_api;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.service.KakaoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
@Log4j
public class TestKakaoLocal {
	@Autowired
	ApplicationContext context;

	@Autowired
	KakaoService service;

	@Test
	//@Ignore
	public void test1() throws IOException {
		String address = "경기도 성남시 수정구 ";
		String coordInfo = service.getAddressToCoord(address);

		log.info(coordInfo);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(coordInfo);
		JsonNode documents = root.path("documents");

		for (JsonNode document : documents) {
			String x = document.path("x").asText();
			String y = document.path("y").asText();
			System.out.println("x: " + x + ", y: " + y);
		}

	}

	@Test
	@Ignore
	public void test2() {
		String recipeUrl = "https://www.10000recipe.com/recipe/6881450";

		try {
			Document document = Jsoup.connect(recipeUrl).get();

			// 재료와 필요한 양 추출
			Elements ingredients = document.select("div.cont_ingre2 div.ready_ingre3 ul:not(.case1) li");
			for (Element ingredient : ingredients) {
				// 각 재료 요소에서 텍스트 추출
				String text = ingredient.text();

				// 텍스트에서 "구매"까지의 부분을 잘라냅니다.
				int endIndex = text.indexOf("구매");
				String name = text.substring(0, endIndex);

				// 필요한 양 추출
				String quantity = ingredient.select("span.ingre_unit").text();

				// 여기서 name과 quantity를 저장하면 됩니다.
				System.out.println("재료: " + name + ", 필요한 양: " + quantity);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
