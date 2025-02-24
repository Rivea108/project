package com.letsgotravel.myapp.api;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class EasyCodefExam {

    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        // EasyCodef 占쏙옙체 占쏙옙占쏙옙
        EasyCodef codef = new EasyCodef();

        // 占쏙옙占쏙옙 클占쏙옙占싱억옙트 占쏙옙占쏙옙 占쏙옙占쏙옙 (Codef占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 Client ID占쏙옙 Secret 占쏙옙占�)
        String demoClientId = "339dc4d8-9138-44a1-a2e3-7cf740b089a9";
		String demoClientSecret = "06ab49ab-0fb7-42af-991c-49cc18a76a3f";
		
        // 클占쏙옙占싱억옙트 占쏙옙占쏙옙 占쏙옙占쏙옙
        codef.setClientInfoForDemo(demoClientId, demoClientSecret);

        // RSA 占쌜븝옙키 占쏙옙占쏙옙 (Codef占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 Public Key 占쏙옙占�)
        String publicKey = "MIMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl7WKXMFQvedU6MiAqerFcKAQzL3C18f12EO4MmZf9g7tjX5Pw6+BJD+m5zfhCY2Wn3rsuEgMz3DUT024gA1fggCy5/xJm78Ppr2vzeyxJNOiW8iY/ArLTxrYf5HzEt0KaFxQhthKC6WY2W6Plq6lLHgPKQqIn7fY9S2D7vgBOH8eUTdv7iGW3MGiZn6IbH7XcQ+s+nsbbBHK475ogAyjTjACFGBMTa8uSlrgo0H9r8ekhc6NnLvYYcuPz2d//8lFd1fkQZdNgFdftlJTVhlSRp1Xvj0Lcf7pX9RtntbIi4fDEGSxU7LJ1nYqo5pIIE/ikvCHgXdSwSTc3aC2X8/CLQIDAQAB";
        codef.setPublicKey(publicKey);
        
        // 占쏙옙占쏙옙 타占쏙옙占쏙옙 DEMO占쏙옙 占쏙옙占쏙옙
        String accessToken = codef.requestToken(EasyCodefServiceType.DEMO);
        System.out.println("Access Token: " + accessToken);
    }
}