package com.letsgotravel.myapp.service;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAiService {
	
	private final RestTemplate restTemplate;

    @Value("${openAI_key}")
    private String openAiKey;
    
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    
    public OpenAiService() {
        this.restTemplate = new RestTemplate();
        this.restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

    }

    public String getTravelRecommendation(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        // �슂泥� �뿤�뜑 �꽕�젙
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAcceptCharset(List.of(StandardCharsets.UTF_8)); // �쐟 UTF-8 �꽕�젙 異붽�
        headers.set("Authorization", "Bearer " + openAiKey);

        // �슂泥� 諛붾뵒 �꽕�젙
        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-4o");
        body.put("messages", new Object[]{
                Map.of("role", "user", "content", prompt)
        });
                
        
        // 요청에 대한 HTTP 헤더 및 본문 설정
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 응답을 String으로 받음 (응답은 ISO-8859-1로 인코딩되어 있을 수 있음)
        String response = restTemplate.postForObject(API_URL, requestEntity, String.class);

        // 응답을 ISO-8859-1로 디코딩한 후 UTF-8로 다시 인코딩
        String decodedResponse = new String(response.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        return decodedResponse;
        
    }
}

