package com.letsgotravel.myapp.service;

import org.json.JSONObject;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:properties/googleMap.properties")
public class LocationService {
	
	@Autowired
    private Environment env;  // 🔥 Spring의 Environment 객체 사용

//    public List<Map<String, Object>> getPlaceLocations(List<String> placeList) {
//        String apiKey = env.getProperty("google.maps.api.key");  // 🔥 googleMap.properties에서 API 키 불러오기
//        List<Map<String, Object>> locations = new ArrayList<>();
//
//        for (String place : placeList) {
//            try {
//                String encodedPlace = URLEncoder.encode(place, "UTF-8");
//                String urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedPlace + "&key=" + apiKey;
//                URL url = new URL(urlString);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET");
//
//                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                StringBuilder response = new StringBuilder();
//                String line;
//                while ((line = br.readLine()) != null) {
//                    response.append(line);
//                }
//                br.close();
//
//                JSONObject jsonResponse = new JSONObject(response.toString());
//                JSONArray results = jsonResponse.getJSONArray("results");
//                if (results.length() > 0) {
//                    JSONObject location = results.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
//                    double lat = location.getDouble("lat");
//                    double lng = location.getDouble("lng");
//
//                    Map<String, Object> placeInfo = new HashMap<>();
//                    placeInfo.put("name", place);
//                    placeInfo.put("lat", lat);
//                    placeInfo.put("lng", lng);
//                    locations.add(placeInfo);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return locations;
//    }
	    public List<Map<String, Object>> getPlaceLocations(List<String> placeList) {
	        String apiKey = env.getProperty("google.maps.api.key");
	        List<Map<String, Object>> locations = new ArrayList<>();

	        for (String place : placeList) {
	            try {
	                if (place == null || place.trim().isEmpty()) {
	                    System.out.println("⚠️ 빈 장소명이 감지되었습니다. API 요청을 건너뜁니다.");
	                    continue;
	                }

	                String encodedPlace = URLEncoder.encode(place, "UTF-8");
	                String urlString = "https://maps.googleapis.com/maps/api/geocode/json?address=" + encodedPlace + "&key=" + apiKey;

	                System.out.println("📌 Google Maps API 요청 URL: " + urlString);

	                URL url = new URL(urlString);
	                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	                conn.setRequestMethod("GET");

	                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                StringBuilder response = new StringBuilder();
	                String line;
	                while ((line = br.readLine()) != null) {
	                    response.append(line);
	                }
	                br.close();

	                JSONObject jsonResponse = new JSONObject(response.toString());
	                JSONArray results = jsonResponse.getJSONArray("results");

	                if (results.length() > 0) {
	                    JSONObject location = results.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
	                    double lat = location.getDouble("lat");
	                    double lng = location.getDouble("lng");

	                    Map<String, Object> placeInfo = new HashMap<>();
	                    placeInfo.put("name", place);
	                    placeInfo.put("lat", lat);
	                    placeInfo.put("lng", lng);
	                    locations.add(placeInfo);
	                } else {
	                    System.out.println("⚠️ 장소 '" + place + "'에 대한 위치 정보를 찾을 수 없습니다.");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return locations;
	    }
	}

