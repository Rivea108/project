package com.letsgotravel.myapp.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class TravelRecommendation {
	
    public ArrayList<Map<String, Object>> changeArray(String openAIResult) throws Exception {
    	        
    	// JSON 객체 생성
        JSONObject jsonObject = new JSONObject(openAIResult);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject message = choices.getJSONObject(0).getJSONObject("message");
        String content = message.getString("content");

        // "```json" 부분 제거 및 JSON 배열 파싱
        String jsonArrayString = content.replace("```json\n", "").replace("\n```", "");
                
        ArrayList<Map<String, Object>> travelCityList = new ArrayList<Map<String, Object>>();
        
        // content가 JSON 배열로 시작하는지 확인
        if (jsonArrayString.contains("[")) {
	        String cleanJson = jsonArrayString.substring(jsonArrayString.indexOf("["), jsonArrayString.lastIndexOf("]") + 1);
	        
	        // JSON 배열 파싱 후 ArrayList로 변환
	        try {
	            JSONArray jsonArray = new JSONArray(cleanJson);
	
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject destination = jsonArray.getJSONObject(i);
	                Map<String, Object> destinationMap = new HashMap<String, Object>();
	
	                // JSONObject의 각 데이터를 Map으로 변환하여 ArrayList에 추가
	                destinationMap.put("나라/도시", destination.getString("나라/도시"));
	                destinationMap.put("대표관광지", destination.getJSONArray("대표관광지").toList());
	                destinationMap.put("대표음식", destination.getJSONArray("대표음식").toList());
	                destinationMap.put("날씨(섭씨)", destination.getString("날씨(섭씨)"));
	                destinationMap.put("성수기여부", destination.getString("성수기여부"));
	                destinationMap.put("한국 대비 물가", destination.getString("한국 대비 물가"));
	                destinationMap.put("치안", destination.getString("치안"));
	                destinationMap.put("위생", destination.getString("위생"));
	                destinationMap.put("교통", destination.getString("교통"));
	                destinationMap.put("주의해야 하는 기간", destination.getString("주의해야 하는 기간"));
	
	                // Map을 ArrayList에 추가
	                travelCityList.add(destinationMap);
	            }
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }
        
        return travelCityList;
    }
    
    public ArrayList<String> changeArray2(String openAIResult) throws Exception {
        
    	// JSON 객체 생성
        JSONObject jsonObject = new JSONObject(openAIResult);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject message = choices.getJSONObject(0).getJSONObject("message");
        String content = message.getString("content");

        // "```json" 부분 제거 및 JSON 배열 파싱
        String jsonArrayString = content.replace("```json\n", "").replace("\n```", "");
      
        ArrayList<String> list = new ArrayList<>();
        // content가 JSON 배열로 시작하는지 확인
        if (jsonArrayString.contains("[")) {
	        String cleanJson = jsonArrayString.substring(jsonArrayString.indexOf("["), jsonArrayString.lastIndexOf("]") + 1);
        	
	        // JSON 배열 객체로 변환
	        JSONArray jsonArray = new JSONArray(cleanJson);

	        // JSONArray를 ArrayList로 변환
	        
	        for (int i = 0; i < jsonArray.length(); i++) {
	            list.add(jsonArray.getString(i));
	        }
        }
        
        return list;
    }
    
    public ArrayList<Map<String, Object>> changeArray3(String openAIResult) throws Exception {
        
    	// JSON 객체 생성
        JSONObject jsonObject = new JSONObject(openAIResult);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject message = choices.getJSONObject(0).getJSONObject("message");
        String content = message.getString("content");

        // "```json" 부분 제거 및 JSON 배열 파싱
        String jsonArrayString = content.replace("```json\n", "").replace("\n```", "");
                
        ArrayList<Map<String, Object>> travelCityList = new ArrayList<Map<String, Object>>();
        
        String cleanJson = jsonArrayString.substring(jsonArrayString.indexOf("["), jsonArrayString.lastIndexOf("]") + 1);
        
        // JSON 배열 파싱 후 ArrayList로 변환
        try {
            JSONArray jsonArray = new JSONArray(cleanJson);

            JSONObject destination1 = jsonArray.getJSONObject(0);
            JSONObject destination2 = jsonArray.getJSONObject(1);
            Map<String, Object> destinationMap = new HashMap<String, Object>();

            // JSONObject의 각 데이터를 Map으로 변환하여 ArrayList에 추가
            destinationMap.put("추천관광지", destination1.getJSONArray("추천관광지").toList());
            destinationMap.put("추천음식점", destination2.getJSONArray("추천음식점").toList());

            // Map을 ArrayList에 추가
            travelCityList.add(destinationMap);

        } catch (Exception e) {
            e.printStackTrace();
        
        }
        
        return travelCityList;
    }
    
    public Map<String, Object> changeString(String openAIResult) throws Exception {
        
    	// JSON 객체 생성
        JSONObject jsonObject = new JSONObject(openAIResult);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject message = choices.getJSONObject(0).getJSONObject("message");
        String content = message.getString("content");
        
        // "```json" 부분 제거 및 JSON 배열 파싱
        String jsonArrayString = content.replace("```json\n", "").replace("\n```", "");

        Map<String, Object> destinationMap = new HashMap<String, Object>();
        
        // content가 JSON 배열로 시작하는지 확인
        if (jsonArrayString.contains("[")) {
	        String cleanJson = jsonArrayString.substring(jsonArrayString.indexOf("["), jsonArrayString.lastIndexOf("]") + 1);
	        
	        // JSON 배열 파싱 후 ArrayList로 변환
	        try {
	            JSONArray jsonArray = new JSONArray(cleanJson);
	
                JSONObject destination = jsonArray.getJSONObject(0);

                // JSONObject의 각 데이터를 Map으로 변환하여 ArrayList에 추가
                destinationMap.put("latitude", destination.getString("latitude"));
                destinationMap.put("longitude", destination.getString("longitude"));
                destinationMap.put("설명", destination.getString("설명"));	
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }

        return destinationMap;
    }
    

    public ArrayList<Map<String, Object>> changeArray4(String openAIResult) throws Exception {
        
    	// JSON 객체 생성
        JSONObject jsonObject = new JSONObject(openAIResult);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject message = choices.getJSONObject(0).getJSONObject("message");
        String content = message.getString("content");

        // "```json" 부분 제거 및 JSON 배열 파싱
        String jsonArrayString = content.replace("```json\n", "").replace("\n```", "");
                
        ArrayList<Map<String, Object>> travelCityList = new ArrayList<Map<String, Object>>();
        
        String cleanJson = jsonArrayString.substring(jsonArrayString.indexOf("["), jsonArrayString.lastIndexOf("]") + 1);
        System.out.println(cleanJson);
     // JSON 배열 파싱 후 ArrayList로 변환
        try {
            JSONArray jsonArray = new JSONArray(cleanJson);
System.out.println(jsonArray);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject destination = jsonArray.getJSONObject(i);
                Map<String, Object> destinationMap = new HashMap<String, Object>();

                // JSONObject의 각 데이터를 Map으로 변환하여 ArrayList에 추가
                destinationMap.put("title", destination.getString("title"));
                destinationMap.put("start", destination.getString("start"));
                destinationMap.put("end", destination.getString("end"));
                // extendedProps 안의 category 값 추가                
                JSONObject extendedProps = destination.getJSONObject("extendedProps");
                destinationMap.put("category", extendedProps.getString("category"));

                // Map을 ArrayList에 추가
                travelCityList.add(destinationMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return travelCityList;
    }
}








//}
//          System.out.println("Invalid JSON format: expected array.");
//      }

//// content가 JSON 배열로 시작하는지 확인
//if (jsonArrayString.trim().startsWith("[")) {
//    // 정상적인 JSON 배열 형식으로 파싱
//    JSONArray travelDataArray = new JSONArray(jsonArrayString);
//
//    // 나라/도시 값 추출            
//    for (int i = 0; i < travelDataArray.length(); i++) {
//        JSONObject cityInfo = travelDataArray.getJSONObject(i);
//        String countryCity = cityInfo.getString("나라/도시");
//        countryCityArray.add(countryCity);
//    }
//    
//} else {
//    System.out.println("Invalid JSON format: expected array.");
//}
    