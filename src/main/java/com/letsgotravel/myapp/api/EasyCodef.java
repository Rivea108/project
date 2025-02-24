package com.letsgotravel.myapp.api;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodef.java
 * </pre>
 * 
 * Desc : ì½”ë“œ?—?”„?˜ ?‰¬?š´ ?‚¬?š©?„ ?œ„?•œ ?œ ?‹¸ ?¼?´ë¸ŒëŸ¬ë¦? ?´?˜?Š¤ 
 * @Company : Â©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:28:31 PM
 */
public class EasyCodef {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * EasyCodef ?‚¬?š©?„ ?œ„?•œ ë³??ˆ˜ ?„¤? • ?˜¤ë¸Œì ?Š¸
	 */
	private EasyCodefProperties properties = new EasyCodefProperties();

	/**
	 * Desc : ? •?‹?„œë²? ?‚¬?š©?„ ?œ„?•œ ?´?¼?´?–¸?Š¸ ? •ë³? ?„¤? •
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:30:59 PM
	 * @param clientId
	 * @param clientSecret
	 */
	public void setClientInfo(String clientId, String clientSecret) {
		properties.setClientInfo(clientId, clientSecret);
	}
	
	/**
	 * Desc : ?°ëª¨ì„œë²? ?‚¬?š©?„ ?œ„?•œ ?´?¼?´?–¸?Š¸ ? •ë³? ?„¤? •
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:31:12 PM
	 * @param demoClientId
	 * @param demoClientSecret
	 */
	public void setClientInfoForDemo(String demoClientId, String demoClientSecret) {
		properties.setClientInfoForDemo(demoClientId, demoClientSecret);
	}
	
	/**
	 * Desc : RSA?•”?˜¸?™”ë¥? ?œ„?•œ ?¼ë¸”ë¦­?‚¤ ?„¤? •
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:31:24 PM
	 * @param publicKey
	 */
	public void setPublicKey(String publicKey) {
		properties.setPublicKey(publicKey);
	}
	
	/**
	 * Desc : RSA?•”?˜¸?™”ë¥? ?œ„?•œ ?¼ë¸”ë¦­?‚¤ ë°˜í™˜
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:32:25 PM
	 * @return
	 */
	public String getPublicKey() {
		return properties.getPublicKey();
	}
	
	/**
	 * Desc : ?ƒ?’ˆ ?š”ì²? 
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:32:31 PM
	 * @param productUrl
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String requestProduct(String productUrl, EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		boolean validationFlag = true;
		
		/**	#1.?•„?ˆ˜ ?•­ëª? ì²´í¬ - ?´?¼?´?–¸?Š¸ ? •ë³?	*/
		validationFlag = checkClientInfo(serviceType.getServiceType());
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.EMPTY_CLIENT_INFO);
			return mapper.writeValueAsString(response);
		}
		
		/**	#2.?•„?ˆ˜ ?•­ëª? ì²´í¬ - ?¼ë¸”ë¦­ ?‚¤	*/
		validationFlag = checkPublicKey();
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.EMPTY_PUBLIC_KEY);
			return mapper.writeValueAsString(response);
		}
		
		/**	#3.ì¶”ê??¸ì¦? ?‚¤?›Œ?“œ ì²´í¬	*/
		validationFlag = checkTwoWayKeyword(parameterMap);
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.INVALID_2WAY_KEYWORD);
			return mapper.writeValueAsString(response);
		}
		
		/**	#4.?ƒ?’ˆ ì¡°íšŒ ?š”ì²?	*/
		EasyCodefResponse response = EasyCodefConnector.execute(productUrl, serviceType.getServiceType(), parameterMap, properties);
		
		/**	#5.ê²°ê³¼ ë°˜í™˜	*/
		return mapper.writeValueAsString(response);
	}
	
	/**
	 * Desc : ?ƒ?’ˆ ì¶”ê??¸ì¦? ?š”ì²?
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:32:41 PM
	 * @param productUrl
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String requestCertification(String productUrl, EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		boolean validationFlag = true;
		
		/**	#1.?•„?ˆ˜ ?•­ëª? ì²´í¬ - ?´?¼?´?–¸?Š¸ ? •ë³?	*/
		validationFlag = checkClientInfo(serviceType.getServiceType());
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.EMPTY_CLIENT_INFO);
			return mapper.writeValueAsString(response);
		}
		
		/**	#2.?•„?ˆ˜ ?•­ëª? ì²´í¬ - ?¼ë¸”ë¦­ ?‚¤	*/
		validationFlag = checkPublicKey();
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.EMPTY_PUBLIC_KEY);
			return mapper.writeValueAsString(response);
		}
		
		/**	#3.ì¶”ê??¸ì¦? ?ŒŒ?¼ë¯¸í„° ?•„?ˆ˜ ?…? ¥ ì²´í¬	*/
		validationFlag = checkTwoWayInfo(parameterMap);
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.INVALID_2WAY_INFO);
			return mapper.writeValueAsString(response);
		}
		
		/**	#4.?ƒ?’ˆ ì¡°íšŒ ?š”ì²?	*/
		EasyCodefResponse response = EasyCodefConnector.execute(productUrl, serviceType.getServiceType(), parameterMap, properties);
		
		/**	#5.ê²°ê³¼ ë°˜í™˜	*/
		return mapper.writeValueAsString(response);
	}
	
	
	/**
	 * Desc : ?„œë¹„ìŠ¤ ???…?— ?”°ë¥? ?´?¼?´?–¸?Š¸ ? •ë³? ?„¤? • ?™•?¸
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:33:23 PM
	 * @param serviceType
	 * @return
	 */
	private boolean checkClientInfo(int serviceType) {
		if(serviceType == 0) {
			if(properties.getClientId() == null || "".equals(properties.getClientId().trim())) {
				return false;
			}
			if(properties.getClientSecret() == null || "".equals(properties.getClientSecret().trim())) {
				return false;
			}
		} else if(serviceType == 1) {
			if(properties.getDemoClientId() == null || "".equals(properties.getDemoClientId().trim())) {
				return false;
			}
			if(properties.getDemoClientSecret() == null || "".equals(properties.getDemoClientSecret().trim())) {
				return false;
			}
		} else {
			if(EasyCodefConstant.SANDBOX_CLIENT_ID == null || "".equals(EasyCodefConstant.SANDBOX_CLIENT_ID.trim())) {
				return false;
			}
			if(EasyCodefConstant.SANDBOX_CLIENT_SECRET == null || "".equals(EasyCodefConstant.SANDBOX_CLIENT_SECRET.trim())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Desc : ?¼ë¸”ë¦­?‚¤ ? •ë³? ?„¤? • ?™•?¸
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:33:31 PM
	 * @return
	 */
	private boolean checkPublicKey() {
		if(properties.getPublicKey() == null || "".equals(properties.getPublicKey().trim())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Desc : ì¶”ê??¸ì¦? ?ŒŒ?¼ë¯¸í„° ?„¤? • ?™•?¸
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:33:39 PM
	 * @param parameterMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean checkTwoWayInfo(HashMap<String, Object> parameterMap) {
		if(!parameterMap.containsKey("is2Way") || !(parameterMap.get("is2Way") instanceof Boolean) || !(boolean)parameterMap.get("is2Way")){
			return false;
		}
		
		if(!parameterMap.containsKey("twoWayInfo")) {
			return false;
		}
		
		HashMap<String, Object> twoWayInfoMap = (HashMap<String, Object>)parameterMap.get("twoWayInfo");
		if(!twoWayInfoMap.containsKey("jobIndex") || !twoWayInfoMap.containsKey("threadIndex") || !twoWayInfoMap.containsKey("jti") || !twoWayInfoMap.containsKey("twoWayTimestamp")){
			return false;
		}
		
		return true;
	}
	
	/**
	 * Desc : ì¶”ê??¸ì¦? ?‚¤?›Œ?“œ ?™•?¸
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:33:45 PM
	 * @param parameterMap
	 * @return
	 */
	private boolean checkTwoWayKeyword(HashMap<String, Object> parameterMap) {
		if(parameterMap != null && (parameterMap.containsKey("is2Way") || parameterMap.containsKey("twoWayInfo"))) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Desc : connectedId ë°œê¸‰?„ ?œ„?•œ ê³„ì • ?“±ë¡?
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:02 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String createAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.CREATE_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Desc : ê³„ì • ? •ë³? ì¶”ê?
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:11 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String addAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.ADD_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Desc : ê³„ì • ? •ë³? ?ˆ˜? • 
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:21 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String updateAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.UPDATE_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Desc : ê³„ì • ? •ë³? ?‚­? œ
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:30 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String deleteAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.DELETE_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Desc : connectedIdë¡? ?“±ë¡ëœ ê³„ì • ëª©ë¡ ì¡°íšŒ
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:37 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String getAccountList(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.GET_ACCOUNT_LIST, serviceType, parameterMap);
	}
	
	/**
	 * Desc : ?´?¼?´?–¸?Š¸ ? •ë³´ë¡œ ?“±ë¡ëœ ëª¨ë“  connectedId ëª©ë¡ ì¡°íšŒ
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:44 PM
	 * @param serviceType
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String getConnectedIdList(EasyCodefServiceType serviceType) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.GET_CID_LIST, serviceType, null);
	}
	
	/**
	 * Desc : ?† ?° ë°˜í™˜ ?š”ì²? - ë³´ìœ  ì¤‘ì¸ ?œ ?š¨?•œ ?† ?°?´ ?ˆ?Š” ê²½ìš° ë°˜í™˜, ?—†?Š” ê²½ìš° ?‹ ê·? ë°œê¸‰ ?›„ ë°˜í™˜
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:35:03 PM
	 * @param serviceType
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public String requestToken(EasyCodefServiceType serviceType) throws JsonParseException, JsonMappingException, IOException {
		String clientId = null;
		String clientSecret = null;
		
		if(serviceType.getServiceType() == 0) {
			clientId = properties.getClientId();
			clientSecret = properties.getClientSecret();
		} else if(serviceType.getServiceType() == 1) {
			clientId = properties.getDemoClientId();
			clientSecret = properties.getDemoClientSecret();
		} else {
			clientId = EasyCodefConstant.SANDBOX_CLIENT_ID;
			clientSecret = EasyCodefConstant.SANDBOX_CLIENT_SECRET;
		}
		
		String accessToken = EasyCodefTokenMap.getToken(clientId); // ë³´ìœ  ì¤‘ì¸ ?† ?°?´ ?ˆ?Š” ê²½ìš° ë°˜í™˜
		if(accessToken != null) {
			HashMap<String, Object> tokenMap = EasyCodefUtil.getTokenMap(accessToken);
			if(EasyCodefUtil.checkValidity((int)(tokenMap.get("exp")))) {	// ?† ?°?˜ ?œ ?š¨ ê¸°ê°„ ?™•?¸
				return accessToken;	// ? •?ƒ ?† ?°?¸ ê²½ìš° ë°˜í™˜
			}
		}
		
		HashMap<String, Object> tokenMap = EasyCodefConnector.publishToken(clientId, clientSecret);	// ë³´ìœ  ì¤‘ì¸ ?† ?°?´ ?—†ê±°ë‚˜ ?‹ ê·? ë°œê¸‰ ì¡°ê±´?— ?•´?‹¹?•˜?Š” ê²½ìš° ë°œê¸‰ ?›„ ë°˜í™˜(ë§Œë£Œ?¼?‹œë¥? ì§??‚¬ê±°ë‚˜ ?•œ?‹œê°? ?´?‚´ë¡? ?„?˜?•œ ê²½ìš° ?‹ ê·? ë°œê¸‰)
		if(tokenMap != null) {
			accessToken = (String)tokenMap.get("access_token");
			EasyCodefTokenMap.setToken(clientId, accessToken);	// ë°œê¸‰ ?† ?° ???¥
			return accessToken;
		} else {
			return null;
		}
	}
	
	/**
	 * Desc : ?† ?° ?‹ ê·? ë°œê¸‰ ?›„ ë°˜í™˜(ì½”ë“œ?—?”„ ?´?š© ì¤? ì¶”ê? ?—…ë¬? ?‚¬?š©?„ ?•˜?Š” ?“± ?† ?° ê¶Œí•œ ë³?ê²½ì´ ?•„?š”?•˜ê±°ë‚˜ ?‹ ê·? ?† ?°?´ ?•„?š”?•œ ê²½ìš°?‹œ ?‚¬?š©)
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Sep 16, 2020 11:58:32 AM
	 * @param serviceType
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String requestNewToken(EasyCodefServiceType serviceType) throws JsonParseException, JsonMappingException, IOException {
		String clientId = null;
		String clientSecret = null;
		
		if(serviceType.getServiceType() == 0) {
			clientId = properties.getClientId();
			clientSecret = properties.getClientSecret();
		} else if(serviceType.getServiceType() == 1) {
			clientId = properties.getDemoClientId();
			clientSecret = properties.getDemoClientSecret();
		} else {
			clientId = EasyCodefConstant.SANDBOX_CLIENT_ID;
			clientSecret = EasyCodefConstant.SANDBOX_CLIENT_SECRET;
		}
		
		String accessToken = null;
		HashMap<String, Object> tokenMap = EasyCodefConnector.publishToken(clientId, clientSecret);	// ?† ?° ?‹ ê·? ë°œê¸‰
		if(tokenMap != null) {
			accessToken = (String)tokenMap.get("access_token");
			EasyCodefTokenMap.setToken(clientId, accessToken);	// ë°œê¸‰ ?† ?° ???¥
			return accessToken;
		} else {
			return null;
		}
	}
}
