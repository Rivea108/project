package com.letsgotravel.myapp.api;

import java.util.HashMap;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefTokenMap.java
 * </pre>
 * 
 * Desc : ?‰¬?š´ ì½”ë“œ?—?”„ ?´?š©?„ ?œ„?•œ ?† ?° ê´?ë¦? ?´?˜?Š¤
 * @Company : Â©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:41:13 PM
 */
public class EasyCodefTokenMap {
	
	/**	?‰¬?š´ ì½”ë“œ?—?”„ ?´?š©?„ ?œ„?•œ ?† ?° ???¥ ë§?	*/
	private static HashMap<String, String> ACCESS_TOKEN_MAP = new HashMap<String, String>();
	
	/**
	 * Desc : ?† ?° ???¥ 
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:41:21 PM
	 * @param clientId
	 * @param accessToken
	 */
	public static void setToken(String clientId, String accessToken) {
		ACCESS_TOKEN_MAP.put(clientId, accessToken);
	}
	
	/**
	 * Desc : ?† ?° ë°˜í™˜
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:41:28 PM
	 * @param clientId
	 * @return
	 */
	public static String getToken(String clientId) {
		return ACCESS_TOKEN_MAP.get(clientId);
	}
}
