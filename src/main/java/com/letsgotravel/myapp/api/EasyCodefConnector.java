package com.letsgotravel.myapp.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefConnector.java
 * </pre>
 * 
 * Desc : CODEF ?—‘?„¸?Š¤ ?† ?° ë°? ?ƒ?’ˆ ì¡°íšŒë¥? ?œ„?•œ HTTP ?š”ì²? ?´?˜?Š¤
 * @Company : Â©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:35:17 PM
 */
public class EasyCodefConnector {
	private static ObjectMapper mapper = new ObjectMapper();
	private static final int REPEAT_COUNT = 3;
	
	/**
	 * Desc : CODEF ?ƒ?’ˆ ì¡°íšŒ ?š”ì²?
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:35:26 PM
	 * @param urlPath
	 * @param serviceType
	 * @param bodyMap
	 * @param properties
	 * @return
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	protected static EasyCodefResponse execute(String urlPath, int serviceType, HashMap<String, Object> bodyMap, EasyCodefProperties properties) throws InterruptedException {
		/**	#1.?† ?° ì²´í¬	*/
		String domain;
		String clientId;
		String clientSecret;

		if(serviceType == 0) {
			domain = EasyCodefConstant.API_DOMAIN;
			clientId = properties.getClientId();
			clientSecret = properties.getClientSecret();
		} else if(serviceType == 1) {
			domain = EasyCodefConstant.DEMO_DOMAIN;
			clientId = properties.getDemoClientId();
			clientSecret = properties.getDemoClientSecret();
		} else {
			domain = EasyCodefConstant.SANDBOX_DOMAIN;
			clientId = EasyCodefConstant.SANDBOX_CLIENT_ID;
			clientSecret = EasyCodefConstant.SANDBOX_CLIENT_SECRET;
		}
		
		String accessToken = getToken(clientId, clientSecret); // ?† ?° ë°˜í™˜
		
		/**	#2.?š”ì²? ?ŒŒ?¼ë¯¸í„° ?¸ì½”ë”©	*/
		String bodyString;
		try {
			bodyString = mapper.writeValueAsString(bodyMap);
			bodyString = URLEncoder.encode(bodyString, "UTF-8");
		} catch (JsonProcessingException e) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.INVALID_JSON); 
			return response;
		} catch (UnsupportedEncodingException e) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.UNSUPPORTED_ENCODING); 
			return response;
		}
		
		/**	#3.?ƒ?’ˆ ì¡°íšŒ ?š”ì²?	*/
		HashMap<String, Object> responseMap = requestProduct(domain + urlPath, accessToken, bodyString);
		if(EasyCodefConstant.INVALID_TOKEN.equals(responseMap.get("error")) || "CF-00401".equals(((HashMap<String, Object>)responseMap.get(EasyCodefConstant.RESULT)).get(EasyCodefConstant.CODE))){	// ?•¡?„¸?Š¤ ?† ?° ?œ ?š¨ê¸°ê°„ ë§Œë£Œ?˜?—ˆ?„ ê²½ìš° ?† ?° ?¬ë°œê¸‰ ?›„ ?ƒ?’ˆ ì¡°íšŒ ?š”ì²? ì§„í–‰
			EasyCodefTokenMap.setToken(clientId, null);		// ?† ?° ? •ë³? ì´ˆê¸°?™”
			accessToken = getToken(clientId, clientSecret); // ?† ?° ?„¤? •
			responseMap = requestProduct(domain + urlPath, accessToken, bodyString);
		} else if (EasyCodefConstant.ACCESS_DENIED.equals(responseMap.get("error")) || "CF-00403".equals(((HashMap<String, Object>)responseMap.get(EasyCodefConstant.RESULT)).get(EasyCodefConstant.CODE))) {	// ? ‘ê·? ê¶Œí•œ?´ ?—†?Š” ê²½ìš° - ?˜¤ë¥˜ì½”?“œ ë°˜í™˜
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.UNAUTHORIZED, EasyCodefConstant.ACCESS_DENIED); 
			return response;
		}
		
		/**	#4.?ƒ?’ˆ ì¡°íšŒ ê²°ê³¼ ë°˜í™˜	*/
		EasyCodefResponse response = new EasyCodefResponse(responseMap); 
		return response;
	}
	
	/**
	 * Desc : CODEF HTTP POST ?š”ì²?
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:35:34 PM
	 * @param urlPath
	 * @param token
	 * @param bodyString
	 * @return
	 */

	    // ê¸°ì¡´ requestProduct ë©”ì„œ?“œ (private ?œ ì§?)
	    private static HashMap<String, Object> requestProduct(String urlPath, String token, String bodyString) {
	        BufferedReader br = null;
	        try {
	            // HTTP ?š”ì²??„ ?œ„?•œ URL ?˜¤ë¸Œì ?Š¸ ?ƒ?„±
	            URL url = new URL(urlPath);
	            HttpURLConnection con = (HttpURLConnection) url.openConnection();
	            con.setDoOutput(true);
	            con.setRequestMethod("POST");
	            con.setRequestProperty("Accept", "application/json");

	            if (token != null && !"".equals(token)) {
	                con.setRequestProperty("Authorization", "Bearer " + token); // ?—‘?„¸?Š¤ ?† ?° ?—¤?” ?„¤? •
	            }

	            // ë¦¬í?˜ìŠ¤?Š¸ ë°”ë”” ? „?†¡
	            OutputStream os = con.getOutputStream();
	            if (bodyString != null && !"".equals(bodyString)) {
	                os.write(bodyString.getBytes());
	            }
	            os.flush();
	            os.close();

	            // ?‘?‹µ ì½”ë“œ ?™•?¸
	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
	            } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
	                EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.BAD_REQUEST, urlPath); 
	                return response;
	            } else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
	                EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.UNAUTHORIZED, urlPath); 
	                return response; 
	            } else if (responseCode == HttpURLConnection.HTTP_FORBIDDEN) {
	                EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.FORBIDDEN, urlPath); 
	                return response; 
	            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
	                EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.NOT_FOUND, urlPath); 
	                return response; 
	            } else {
	                EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.SERVER_ERROR, urlPath); 
	                return response;
	            }

	            // ?‘?‹µ ë°”ë”” read
	            String inputLine;
	            StringBuffer responseStr = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                responseStr.append(inputLine);
	            }
	            br.close();

	            // ê²°ê³¼ ë°˜í™˜
	            return mapper.readValue(URLDecoder.decode(responseStr.toString(), "UTF-8"), new TypeReference<HashMap<String, Object>>() {});
	        } catch (Exception e) {
	            EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.LIBRARY_SENDER_ERROR, e.getMessage()); 
	            return response;
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {}
	            }
	        }
	    }

	    // Getter ë©”ì„œ?“œ ì¶”ê?
	    public static HashMap<String, Object> getRequestProduct(String urlPath, String token, String bodyString) {
	        return requestProduct(urlPath, token, bodyString);
	    }

	
	/**
	 * Desc : ?—‘?„¸?Š¤ ?† ?° ë°˜í™˜
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:35:47 PM
	 * @param clientId
	 * @param clientSecret
	 * @return
	 * @throws InterruptedException
	 */
	private static String getToken(String clientId, String clientSecret) throws InterruptedException {
		int i = 0;
		String accessToken = EasyCodefTokenMap.getToken(clientId);
		if(accessToken == null || "".equals(accessToken) || !checkToken(accessToken)) { //ë§Œë£Œ ì¡°ê±´ ì¶”ê?
			while(i < REPEAT_COUNT) {	// ?† ?° ë°œê¸‰ ?š”ì²??? ìµœë? 3?šŒê¹Œì? ?¬?‹œ?„
				HashMap<String, Object> tokenMap = publishToken(clientId, clientSecret);	// ?† ?° ë°œê¸‰ ?š”ì²?
				if(tokenMap != null) {
					String newToken = (String)tokenMap.get("access_token");
					EasyCodefTokenMap.setToken(clientId, newToken);	// ?† ?° ???¥
					accessToken = newToken;
				}
				
				if(accessToken != null || !"".equals(accessToken)) {
					break;	// ? •?ƒ ë°œê¸‰?‹œ ë°˜ë³µë¬? ì¢…ë£Œ
				}
				
				Thread.sleep(20);
				i++;
			}
		}
		
		return accessToken;
	}
	
	/**
	 * Desc : CODEF ?—‘?„¸?Š¤ ?† ?° ë°œê¸‰ ?š”ì²?
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:36:01 PM
	 * @param clientId
	 * @param clientSecret
	 * @return
	 */
	protected static HashMap<String, Object> publishToken(String clientId, String clientSecret) {
		BufferedReader br = null;
		try {
			// HTTP ?š”ì²??„ ?œ„?•œ URL ?˜¤ë¸Œì ?Š¸ ?ƒ?„±
			URL url = new URL(EasyCodefConstant.OAUTH_DOMAIN + EasyCodefConstant.GET_TOKEN);
			String params = "grant_type=client_credentials&scope=read";	// Oauth2.0 ?‚¬?š©? ?ê²©ì¦ëª? ë°©ì‹(client_credentials) ?† ?° ?š”ì²? ?„¤? •
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			// ?´?¼?´?–¸?Š¸?•„?´?””, ?‹œ?¬ë¦¿ì½”?“œ Base64 ?¸ì½”ë”©
			String auth = clientId + ":" + clientSecret;
			byte[] authEncBytes = Base64.encodeBase64(auth.getBytes());
			String authStringEnc = new String(authEncBytes);
			String authHeader = "Basic " + authStringEnc;
			
			con.setRequestProperty("Authorization", authHeader);
			con.setDoInput(true);
			con.setDoOutput(true);
			
			// ë¦¬í?˜ìŠ¤?Š¸ ë°”ë”” ? „?†¡
			OutputStream os = con.getOutputStream();
			os.write(params.getBytes());
			os.flush();
			os.close();
	
			// ?‘?‹µ ì½”ë“œ ?™•?¸
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {	// ? •?ƒ ?‘?‹µ
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {	 // ?—?Ÿ¬ ë°œìƒ
				return null;
			}
			
			// ?‘?‹µ ë°”ë”” read
			String inputLine;
			StringBuffer responseStr = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				responseStr.append(inputLine);
			}
			br.close();
			
			HashMap<String, Object> tokenMap = mapper.readValue(URLDecoder.decode(responseStr.toString(), "UTF-8"), new TypeReference<HashMap<String, Object>>(){});
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

	/**
	 * ?† ?° ?œ ?š¨ê¸°ê°„ ?™•?¸
	 * @param accessToken
	 * @return
	 */
	private static boolean checkToken(String accessToken) {
        HashMap<String, Object> tokenMap = null;
        try {
            tokenMap = EasyCodefUtil.getTokenMap(accessToken);
        } catch (IOException e) {
            // ?™•?¸ ì¤? ?˜¤ë¥? ë°œìƒ ?‹œ
            return false;
        }
        // ?† ?°?˜ ?œ ?š¨ ê¸°ê°„ ?™•?¸
        return EasyCodefUtil.checkValidity((int) (tokenMap.get("exp")));
    }
}