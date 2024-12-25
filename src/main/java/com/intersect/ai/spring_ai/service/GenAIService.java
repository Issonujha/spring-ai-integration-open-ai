package com.intersect.ai.spring_ai.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class GenAIService {

	private final ChatModel chatModel;

	public GenAIService(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	public String getResponse(String prompt) {
		ChatResponse chatResponse = chatModel
				.call(new Prompt(prompt, OpenAiChatOptions.builder().withModel("gpt-3.5-turbo-0125").withTemperature(0.4).build()));
		return chatResponse.getResults().get(0).getOutput().getContent();
	}
	
	

}
