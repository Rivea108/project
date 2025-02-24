package com.letsgotravel.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.letsgotravel.myapp.service.OpenAiService;

import java.util.Map;

@RestController
@RequestMapping("/api/openai")
public class OpenAiController {

	 @Autowired
	    private OpenAiService openAiService;

	 @RequestMapping(value = "/recommend", produces = "application/text; charset=UTF-8")
	 @ResponseBody
	 public String getTravelRecommendation(@RequestBody Map<String, String> requestBody) {
	     System.out.println("recommend 들어옴");
	     String prompt = requestBody.get("prompt");
	     System.out.println("prompt: " + prompt);
	     System.out.println("컨트롤러 : " + openAiService.getTravelRecommendation(prompt));
	     return openAiService.getTravelRecommendation(prompt);
	 }
}
