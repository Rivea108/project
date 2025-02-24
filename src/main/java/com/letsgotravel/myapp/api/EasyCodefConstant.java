package com.letsgotravel.myapp.api;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefConstant.java
 * </pre>
 * 
 * Desc : EasyCodefë¥? ?‚¬?š©?•˜ê¸? ?œ„?•´ ?•„?š”?•œ ?ƒ?’ˆ ?š”ì²? ê´?? ¨ ? •ë³? ?´?˜?Š¤
 * @Company : Â©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:36:32 PM
 */
public class EasyCodefConstant {
	
	/**	OAUTH ?„œë²? ?„ë©”ì¸	*/
	protected static final String OAUTH_DOMAIN = "https://oauth.codef.io";
	
	/**	OAUTH ?—‘?„¸?Š¤ ?† ?° ë°œê¸‰ URL PATH	*/
	protected static final String GET_TOKEN = "/oauth/token";
	
	
	/**	?ƒŒ?“œë°•ìŠ¤ ?„œë²? ?„ë©”ì¸	*/
	protected static final String SANDBOX_DOMAIN = "https://sandbox.codef.io";
	
	/**	?ƒŒ?“œë°•ìŠ¤ ?—‘?„¸?Š¤ ?† ?° ë°œê¸‰?„ ?œ„?•œ ?´?¼?´?–¸?Š¸ ?•„?´?””	*/
	protected static final String SANDBOX_CLIENT_ID 	= "ef27cfaa-10c1-4470-adac-60ba476273f9";
	
	/**	?ƒŒ?“œë°•ìŠ¤ ?—‘?„¸?Š¤ ?† ?° ë°œê¸‰?„ ?œ„?•œ ?´?¼?´?–¸?Š¸ ?‹œ?¬ë¦?	*/
	protected static final String SANDBOX_CLIENT_SECRET 	= "83160c33-9045-4915-86d8-809473cdf5c3";
	
	
	/**	?°ëª? ?„œë²? ?„ë©”ì¸	*/
	protected static final String DEMO_DOMAIN = "https://development.codef.io";
	
	/**	? •?‹ ?„œë²? ?„ë©”ì¸	*/
	protected static final String API_DOMAIN = "https://api.codef.io";
	
	
	/** ?‘?‹µë¶? ?ˆ˜?–‰ ê²°ê³¼ ?‚¤?›Œ?“œ	*/
	protected static final String RESULT = "result";
	
	/** ?‘?‹µë¶? ?ˆ˜?–‰ ê²°ê³¼ ë©”ì‹œì§? ì½”ë“œ ?‚¤?›Œ?“œ	*/
	protected static final String CODE = "code";

	/** ?‘?‹µë¶? ?ˆ˜?–‰ ê²°ê³¼ ë©”ì‹œì§? ?‚¤?›Œ?“œ	*/
	protected static final String MESSAGE = "message";
	
	/** ?‘?‹µë¶? ?ˆ˜?–‰ ê²°ê³¼ ì¶”ê? ë©”ì‹œì§? ?‚¤?›Œ?“œ	*/
	protected static final String EXTRA_MESSAGE = "extraMessage";
	
	/**	?‘?‹µë¶? ?ˆ˜?–‰ ê²°ê³¼ ?°?´?„° ?‚¤?›Œ?“œ	*/
	protected static final String DATA = "data";
	
	/** ê³„ì • ëª©ë¡  ?‚¤?›Œ?“œ	*/
	protected static final String ACCOUNT_LIST = "accountList";
	
	protected static final String CONNECTED_ID = "connectedId";
	
	
	/**	?—‘?„¸?Š¤ ?† ?° ê±°ì ˆ ?‚¬?œ 1	*/
	protected static String INVALID_TOKEN = "invalid_token";
	
	/**	?—‘?„¸?Š¤ ?† ?° ê±°ì ˆ ?‚¬?œ 2	*/
	protected static String ACCESS_DENIED = "access_denied";
	
	/**	ê³„ì • ?“±ë¡? URL	*/
	protected static final String CREATE_ACCOUNT = "/v1/account/create";
	
	/**	ê³„ì • ì¶”ê? URL	*/
	protected static final String ADD_ACCOUNT = "/v1/account/add";
	
	/**	ê³„ì • ?ˆ˜? • URL	*/
	protected static final String UPDATE_ACCOUNT = "/v1/account/update";
	
	/**	ê³„ì • ?‚­? œ URL	*/
	protected static final String DELETE_ACCOUNT = "/v1/account/delete";
	
	/**	ê³„ì • ëª©ë¡ ì¡°íšŒ URL	*/
	protected static final String GET_ACCOUNT_LIST = "/v1/account/list";
	
	/**	ì»¤ë„¥?‹°?“œ ?•„?´?”” ëª©ë¡ ì¡°íšŒ URL	*/
	protected static final String GET_CID_LIST = "/v1/account/connectedId-list"; 
	
}