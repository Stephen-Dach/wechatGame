package com.ai.springai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestAttribute;


@Configuration
@RequiredArgsConstructor
public class Init {

    final OllamaChatModel model;

    @Bean
    public ChatClient chatClient(ChatMemory chatMemory){
        return  ChatClient.builder(model)
                .defaultSystem("加入你是一个资历高深软件工程师")
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .build();
    }

    @Bean
    public ChatMemory chatMemory(){
        return new InMemoryChatMemory();
    }
}
