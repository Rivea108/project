package com.letsgotravel.myapp.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;

import com.letsgotravel.myapp.api.EasyCodefToken;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// value.properties 파일에서 apikey 값 주입
    @Value("${openAI_key}")
    private String openAIKey;
		
	@RequestMapping(value = "/main.do")
	public String mainPage(Model model, HttpSession session) {
		
		logger.info("main들어옴");
		   // session값으로 로그인 됐는지 확인
        System.out.println("session에 저장된 midx값 : " + session.getAttribute("midx"));
		
		
		// CodefToken 占쏙옙占쏙옙
		String clientId = "339dc4d8-9138-44a1-a2e3-7cf740b089a9";
		String clientSecret = "06ab49ab-0fb7-42af-991c-49cc18a76a3f";

		// CodefTokenExample 占쏙옙체 占쏙옙占쏙옙
		EasyCodefToken easyCodefToken = new EasyCodefToken();
        
        // getAccessToken 호占쏙옙
        String accessToken = easyCodefToken.getAccessToken(clientId, clientSecret);
        
        // 占쏙옙占� 占쏙옙占�
        System.out.println("Access Token: " + accessToken);
        
        // CodefToken 占쏙옙占�
//		        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		        con.setRequestProperty("Authorization", "Bearer " + token);

        
        // 주입된 accessToken 값을 출력
        System.out.println("openAIKey : " + openAIKey);
		
        return "WEB-INF/main";
	}
}