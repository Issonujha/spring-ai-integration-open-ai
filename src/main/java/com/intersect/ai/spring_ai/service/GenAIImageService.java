package com.intersect.ai.spring_ai.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class GenAIImageService {

	private final OpenAiImageModel openAiImageModel;

	public GenAIImageService(OpenAiImageModel openAiImageModel) {
		this.openAiImageModel = openAiImageModel;
	}

	public ImageResponse generateImage(String prompt, int n, int height, int width, String quality) {
//		return openAiImageModel.call(new ImagePrompt(prompt,
//				OpenAiImageOptions.builder().withModel("dall-e-2").withN(3).withHeight(1024).withWidth(1024).build()));

		return openAiImageModel.call(new ImagePrompt(prompt, OpenAiImageOptions.builder().withModel("dall-e-2").withN(n)
				.withHeight(height).withWidth(width).withQuality(quality).build()));
	}

}
