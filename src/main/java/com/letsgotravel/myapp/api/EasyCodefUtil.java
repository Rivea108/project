package com.letsgotravel.myapp.api;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefUtil.java
 * </pre>
 * 
 * Desc : ?â¨?ö¥ ÏΩîÎìú?óê?îÑ ?ú†?ã∏ ?Å¥?ûò?ä§
 * @Company : ¬©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:41:39 PM
 */
public class EasyCodefUtil {

	/**
	 * Desc : RSA?ïî?ò∏?ôî
	 * @Company : ¬©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:41:50 PM
	 * @param plainText
	 * @param publicKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encryptRSA(String plainText, String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] bytePublicKey = Base64.getDecoder().decode(publicKey);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey key = keyFactory.generatePublic(new X509EncodedKeySpec(bytePublicKey));
		
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] bytePlain = cipher.doFinal(plainText.getBytes());
		String encrypted = Base64.getEncoder().encodeToString(bytePlain);
	
		return encrypted;
	}
	
	/**
	 * Desc : byteÎ∞∞Ïó¥Î°? Ï∂îÏ∂ú?ïú ?åå?ùº ?†ïÎ≥¥Î?? BASE64 Î¨∏Ïûê?ó¥Î°? ?ù∏ÏΩîÎî©
	 * @Company : ¬©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:41:58 PM
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String encodeToFileString(String filePath) throws IOException {
		File file = new File(filePath);
		
		byte[] fileContent = FileUtils.readFileToByteArray(file);
		String fileString = Base64.getEncoder().encodeToString(fileContent);
		
		return fileString;
	}
	
	/**
	 * ?Ü†?Å∞ Îß? Î≥??ôò
	 * 
	 * @param request
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> getTokenMap(String token) throws JsonParseException, JsonMappingException, IOException {

		/** ?Å¥?ùº?ù¥?ñ∏?ä∏ ?ãùÎ≥? Í∞?, ?öîÏ≤? ?ãùÎ≥? Í∞? Ï∂îÏ∂ú?ùÑ ?úÑ?ïú ?îîÏΩîÎìú */
		String[] split_string = token.split("\\.");
		String base64EncodedBody = split_string[1];
		String tokenBody = new String(Base64.getDecoder().decode(base64EncodedBody));

		/** Îß? Î≥??ôò */
		return new ObjectMapper().readValue(tokenBody, HashMap.class);
	}
	
	/**
	 * Comment  : ?öîÏ≤? ?Ü†?Å∞ ?†ï?ï©?Ñ± Ï≤¥ÌÅ¨
	 * @version : 1.0.1
	 * @tags    : @param headerMap
	 * @tags    : @return
	 * @date    : Jun 24, 2020
	 */
	public static boolean checkValidity(int expInt) {
		long now = new Date().getTime();
		String expStr = expInt + "000";	// ?òÑ?û¨ ?ãúÍ∞? ???ûÑ?ä§?É¨?îÑ?? ?ûêÎ¶¨Ïàò ÎßûÏ∂îÍ∏?(13?ûêÎ¶?)
		long exp  = Long.parseLong(expStr);
		if(now > exp || (exp - now < 3600000)) { // ?ú†?ö®Í∏∞Í∞Ñ ?ôï?ù∏::?ú†?ö®Í∏∞Í∞Ñ?ù¥ Ïß??Ç¨Í±∞ÎÇò ?ïú?ãúÍ∞? ?ù¥?Ç¥Î°? ÎßåÎ£å?êò?äî Í≤ΩÏö∞
			return false;
		}
		
		return true;
	}
}