package com.intersect.ai.spring_ai.service;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ReciepeService {
	
	private final ChatModel chatModel;
	
	public ReciepeService(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	public String createRecipe(String ingredients, String cuisine, String dietryRestrictions) {
		var template = """
				I want to create recipe from following ingredients: {ingredients}.
				The cuisine type I prefer is {cuisine}.
				Please consider the following dietry Restrictions: {dietryRestrictions}.
				please provide me with a detailed recipe including title, list of ingredients and cooking instructions.
				""";
		PromptTemplate promptTemplate = new PromptTemplate(template);

		Map<String, Object> model = Map.of("ingredients", ingredients, "cuisine", cuisine, "dietryRestrictions",
				dietryRestrictions);
		Prompt prompt = promptTemplate.create(model,
				OpenAiChatOptions.builder().withModel("gpt-3.5-turbo-0125").withTemperature(0.4).build());
		return chatModel.call(prompt).getResult().getOutput().getContent();
	}

}
