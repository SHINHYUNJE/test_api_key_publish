package com.company.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoServiceImpl implements KakaoService {
	private static final String KAKAO_LOCAL_API_URL = "https://dapi.kakao.com/v2/local/search/address.json";
	private static final String REST_API_KEY = "c58c19ade079896c9555aee9161dc4b1";

	@Override
	public String getAddressToCoord(String address) {
		String apiUrl = KAKAO_LOCAL_API_URL + "?query=" + address;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK " + REST_API_KEY);
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		
		System.out.println("......... : "+ entity);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

		return response.getBody();
	}
}