package com.intersect.ai.spring_ai.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intersect.ai.spring_ai.service.GenAIImageService;
import com.intersect.ai.spring_ai.service.GenAIService;
import com.intersect.ai.spring_ai.service.ReciepeService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class GenAIController {
	
	private final GenAIService genAIService;
	
	private final GenAIImageService genAIImageService;
	
	private final ReciepeService reciepeService;

	
	public GenAIController(GenAIService genAIService, GenAIImageService genAIImageService, ReciepeService reciepeService) {
		this.genAIService = genAIService;
		this.genAIImageService = genAIImageService;
		this.reciepeService = reciepeService;
	}
	
	@GetMapping("/ask-ai")
	public String askAi(@RequestParam String prompt) {
		return genAIService.getResponse(prompt);
	}
	
	@GetMapping("/generate-image")
	public List<String> generateImage(HttpServletResponse response, @RequestParam String prompt,
			@RequestParam(defaultValue = "1") int n, @RequestParam(defaultValue = "1024") int height,
			@RequestParam(defaultValue = "1024") int width, @RequestParam(defaultValue = "hd") String quality)
			throws IOException {
		ImageResponse imageResponse = genAIImageService.generateImage(prompt, n, height, width, quality);
		return imageResponse.getResults().stream().map(i -> i.getOutput().getUrl()).toList();
	}
	
	@GetMapping("/create-recipe")
	public String createRecipe(@RequestParam String ingredients, 
									@RequestParam(defaultValue = "any") String cuisine,
			@RequestParam(defaultValue = "") String dietryRestriction) {
		return reciepeService.createRecipe(ingredients, cuisine, dietryRestriction);
	}
	
	

}
