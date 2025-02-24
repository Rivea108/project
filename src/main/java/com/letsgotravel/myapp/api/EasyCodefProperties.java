package com.letsgotravel.myapp.api;
/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefProperties.java
 * </pre>
 * 
 * Desc : ì½”ë“œ?—?”„?˜ ?‰¬?š´ ?‚¬?š©?„ ?œ„?•œ ?”„ë¡œí¼?‹° ?´?˜?Š¤ 
 * @Company : Â©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:36:51 PM
 */
public class EasyCodefProperties {
	
	//	?°ëª? ?—‘?„¸?Š¤ ?† ?° ë°œê¸‰?„ ?œ„?•œ ?´?¼?´?–¸?Š¸ ?•„?´?””
	private String demoClientId 	= "";
	
	//	?°ëª? ?—‘?„¸?Š¤ ?† ?° ë°œê¸‰?„ ?œ„?•œ ?´?¼?´?–¸?Š¸ ?‹œ?¬ë¦?
	private String demoClientSecret 	= "";	
	
	//	OAUTH2.0 ?°ëª? ?† ?°
	private String demoAccessToken = "";
	
	//	? •?‹ ?—‘?„¸?Š¤ ?† ?° ë°œê¸‰?„ ?œ„?•œ ?´?¼?´?–¸?Š¸ ?•„?´?””
	private String clientId 	= "";
	
	//	? •?‹ ?—‘?„¸?Š¤ ?† ?° ë°œê¸‰?„ ?œ„?•œ ?´?¼?´?–¸?Š¸ ?‹œ?¬ë¦?
	private String clientSecret 	= "";	
	
	//	OAUTH2.0 ?† ?°
	private String accessToken = "";
	
	//	RSA?•”?˜¸?™”ë¥? ?œ„?•œ ?¼ë¸”ë¦­?‚¤
	private String publicKey 	= "";

	
	/**
	 * Desc : ? •?‹?„œë²? ?‚¬?š©?„ ?œ„?•œ ?´?¼?´?–¸?Š¸ ? •ë³? ?„¤? •
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:02 PM
	 * @param clientId
	 * @param clientSecret
	 */
	public void setClientInfo(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
	
	/**
	 * Desc : ?°ëª¨ì„œë²? ?‚¬?š©?„ ?œ„?•œ ?´?¼?´?–¸?Š¸ ? •ë³? ?„¤? •
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:10 PM
	 * @param demoClientId
	 * @param demoClientSecret
	 */
	public void setClientInfoForDemo(String demoClientId, String demoClientSecret) {
		this.demoClientId = demoClientId;
		this.demoClientSecret = demoClientSecret;
	}
	
	/**
	 * Desc : ?°ëª? ?´?¼?´?–¸?Š¸ ?•„?´?”” ë°˜í™˜
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:17 PM
	 * @return
	 */
	public String getDemoClientId() {
		return demoClientId;
	}

	/**
	 * Desc : ?°ëª? ?´?¼?´?–¸?Š¸ ?‹œ?¬ë¦? ë°˜í™˜
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:23 PM
	 * @return
	 */
	public String getDemoClientSecret() {
		return demoClientSecret;
	}

	/**
	 * Desc : ?°ëª? ? ‘?† ?† ?° ë°˜í™˜
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:30 PM
	 * @return
	 */
	public String getDemoAccessToken() {
		return demoAccessToken;
	}

	/**
	 * Desc : ?°ëª? ?´?¼?´?–¸?Š¸ ?‹œ?¬ë¦? ë°˜í™˜
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:36 PM
	 * @Version : 1.0.1
	 * @return
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Desc : API ?´?¼?´?–¸?Š¸ ?‹œ?¬ë¦? ë°˜í™˜
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:44 PM
	 * @return
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * Desc : API ? ‘?† ?† ?° ë°˜í™˜
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:50 PM
	 * @Version : 1.0.1
	 * @return
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Desc : RSA?•”?˜¸?™”ë¥? ?œ„?•œ ?¼ë¸”ë¦­?‚¤ ë°˜í™˜
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:59 PM
	 * @return
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * Desc : RSA?•”?˜¸?™”ë¥? ?œ„?•œ ?¼ë¸”ë¦­?‚¤ ?„¤? •
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:38:07 PM
	 * @Version : 1.0.1
	 * @param publicKey
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * Desc : ?°ëª? ? ‘?† ?† ?° ?„¤? •
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:38:14 PM
	 * @param demoAccessToken
	 */
	public void setDemoAccessToken(String demoAccessToken) {
		this.demoAccessToken = demoAccessToken;
	}

	/**
	 * Desc : API ? ‘?† ?† ?° ?„¤? •
	 * @Company : Â©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:38:21 PM
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}