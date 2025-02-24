package com.letsgotravel.myapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.letsgotravel.myapp.controller.NaverImageController;
import org.apache.commons.text.StringEscapeUtils; // Apache Commons Text 라이브러리 필요
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class NaverImageSearchService {

    @Value("${naver.api.client-id}")
    private String clientId;

    @Value("${naver.api.client-secret}")
    private String clientSecret;

    private static final Logger logger = LoggerFactory.getLogger(NaverImageController.class);
    private static final String NAVER_IMAGE_SEARCH_URL = "https://openapi.naver.com/v1/search/image.json";

    public String searchImages(String query) {
    	try {
            logger.info("🔵 네이버 API 요청을 위한 원본 검색어: {}", query);

            // ✅ 올바르게 인코딩
           

            // ✅ 네이버 API URL 생성
            String url = NAVER_IMAGE_SEARCH_URL + "?query="+ query +"&display=10&start=1&sort=sim";
            logger.info("🔵 네이버 API 요청 URL: {}", url);

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", clientId);
            headers.set("X-Naver-Client-Secret", clientSecret);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // ✅ API 호출
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );

            String responseBody = responseEntity.getBody();
            logger.info("🟢 네이버 API 응답 (디코딩 전): {}", responseBody);

            // ✅ title 값만 디코딩 (중복 API 요청 제거)
            responseBody = decodeNaverResponse(responseBody);
            logger.info("🟢 네이버 API 응답 (디코딩 후): {}", responseBody);

            return responseBody;
        } catch (Exception e) {
            logger.error("❌ 네이버 이미지 검색 API 호출 실패", e);
            return "{\"error\":\"네이버 이미지 검색 API 호출 중 오류 발생\"}";
        }
    }
    
    
    private String decodeNaverResponse(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode items = rootNode.path("items");

            for (JsonNode item : items) {
                String encodedTitle = item.path("title").asText();

                // ✅ HTML 엔티티 & URL 퍼센트 인코딩 제거
                String decodedTitle = StringEscapeUtils.unescapeHtml4(encodedTitle);
                decodedTitle = URLDecoder.decode(decodedTitle, StandardCharsets.UTF_8.toString());

                // ✅ ISO-8859-1 → UTF-8 변환 (깨짐 방지)
                decodedTitle = new String(decodedTitle.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

                ((ObjectNode) item).put("title", decodedTitle);
            }

            return objectMapper.writeValueAsString(rootNode);
        } catch (Exception e) {
            logger.error("❌ 응답 디코딩 실패", e);
            return responseBody;
        }
    }


    
    }
