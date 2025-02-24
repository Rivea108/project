package com.letsgotravel.myapp.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import java.io.*;
import java.util.HashMap;
import org.apache.commons.codec.binary.Base64;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


public class EasyCodefToken {
//    public static void main(String[] args) {
//        try {
//            // #1. EasyCodef 객체 생성
//            EasyCodef codef = new EasyCodef();
//
//            // #2. 클라이언트 정보 설정 (데모용)
//            codef.setClientInfoForDemo("fbbcf915-2395-4dfe-9316-a5ce610fab1a", "2b152335-b63a-4596-bf34-5b44f79b41b0");
//
//            // #3. 퍼블릭 키 설정 (RSA 암호화를 위해 필요)
//            codef.setPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtphbcADV32+LTG5pki0L89WxekjcQpZNy4skAu5Ncxo4u2TKGe1+OM23zw02u8Huwfo62Y7xqc1GtFkoz/KK8J660y5K7xGRsEnptrmd4Uox3IqzJ8eIU4xCEoPLDrTK5CKbVFaoZhIxBEGwFvpNB3pA46khTc15f+FgXdHGZ7n0TWIZvZbJo+LPH4znxlcIUHqu9NCcGkkTljDXBOnGvbbCAS8X9AHKOg+pIo8jQiwDnokAfD0CxmzcREO5aUhe3I9hV3mnxzUqIaETlhRYyr2pfQHB/kH2yCceO8uT6VT1hQ91EmsGniAZYPVYkNMsC5j+1OXZY4mJ7hg/z0jU8QIDAQAB");
//
//            // #4. 토큰 요청 (SANDBOX 환경)
//            String accessToken = codef.requestToken(EasyCodefServiceType.DEMO);
//            System.out.println("Access Token: " + accessToken);
//            // Access Token: eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzZXJ2aWNlX3R5cGUiOiIxIiwic2NvcGUiOlsicmVhZCJdLCJzZXJ2aWNlX25vIjoiMDAwMDA0OTgzMDAyIiwiZXhwIjoxNzM1MTI0NDI4LCJhdXRob3JpdGllcyI6WyJJTlNVUkFOQ0UiLCJQVUJMSUMiLCJCQU5LIiwiRVRDIiwiU1RPQ0siLCJDQVJEIl0sImp0aSI6ImEyYTg3NjIxLTBjNjktNGExNi1iM2Y2LTJlNTFhM2ZhMjg2NCIsImNsaWVudF9pZCI6ImZiYmNmOTE1LTIzOTUtNGRmZS05MzE2LWE1Y2U2MTBmYWIxYSJ9.WdBefZFbiGjHAJEicQ-k8JTXR7Vyp3ZLa4RCskA2Bq_IxeAf-wVbvTxkiop7ByT7myNau1rwKh6blmcQesdrtJPcgwJvi3g5i8_fJc7DPKYvQwrDdyXOVBmq4COXHvjtCo625miGgvWcWAr-kS7JWumIyIfbe1cUrGN5qO4eGIfGU1DEHSP7a37j7UT-nhEF9sz2S3UHtKb3RoNckoSJGrAQCRa6CXS2UuowJ_4UPhDs4rU0esY3g-wxIYHV-NkERfCOWg-bGRPbAI0A52A-cElUsWp8OdqwddjG_mO7i8ig-3pwA-JHt-QcanAHZaZ_S_fLT_TpzRygl3zmuUDBNQ
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

	  
//  public static void main(String[] args) {
//      String clientId = "fbbcf915-2395-4dfe-9316-a5ce610fab1a";
//      String clientSecret = "2b152335-b63a-4596-bf34-5b44f79b41b0";
//
//      HashMap<String, Object> token = publishToken(clientId, clientSecret);
//      
//      if (token != null) {
//          System.out.println("Access Token: " + token.get("access_token"));
//      } else {
//          System.out.println("Failed to retrieve the token.");
//      }
//  }

	public String getAccessToken(String clientId, String clientSecret) {

      HashMap<String, Object> token = publishToken(clientId, clientSecret);
      
      if (token != null) {
          System.out.println("Access Token: " + token.get("access_token"));
          return token.get("access_token").toString();
      } else {
          System.out.println("Failed to retrieve the token.");
          return "";
      }      
            
	}
        

	private final String OAUTH_DOMAIN = "https://oauth.codef.io"; // 실제 OAuth 2.0 도메인으로 변경
	private final String GET_TOKEN = "/oauth/token"; // 실제 토큰 엔드포인트로 변경
	private final ObjectMapper mapper = new ObjectMapper();
	 
    protected HashMap publishToken(String clientId, String clientSecret) {
		BufferedReader br = null;
		try {
			// HTTP 요청을 위한 URL 오브젝트 생성
			    
			URL url = new URL(EasyCodefConstant.OAUTH_DOMAIN + EasyCodefConstant.GET_TOKEN);
			String params = "grant_type=client_credentials&scope=read";	// Oauth2.0 사용자 자격증명 방식(client_credentials) 토큰 요청 설정
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			// 클라이언트아이디, 시크릿코드 Base64 인코딩
			String auth = clientId + ":" + clientSecret;
			byte[] authEncBytes = Base64.encodeBase64(auth.getBytes());
			String authStringEnc = new String(authEncBytes);
			String authHeader = "Basic " + authStringEnc;
			
			con.setRequestProperty("Authorization", authHeader);
			con.setDoInput(true);
			con.setDoOutput(true);
			
			// 리퀘스트 바디 전송
			OutputStream os = con.getOutputStream();
			os.write(params.getBytes());
			os.flush();
			os.close();
	
			// 응답 코드 확인
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {	// 정상 응답
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {	 // 에러 발생
				return null;
			}
			
			// 응답 바디 read
			String inputLine;
			StringBuffer responseStr = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				responseStr.append(inputLine);
			}
			br.close();
			// 응답결과 URL Decoding(UTF-8)
			HashMap tokenMap = mapper.readValue(URLDecoder.decode(responseStr.toString(), "UTF-8"), new TypeReference<HashMap<String, Object>>(){}); 
			return tokenMap;
		} catch (Exception e) {
			return null;
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) { }
			}
		}
	}
}
