package com.ai.springai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.BindParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DeepseekAI {

    private final ChatClient chatClient;

    public DeepseekAI(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/deepseek/chat")
    public String chat(@RequestParam("msg") String msg) {
        return this.chatClient.prompt()
                .user(msg)
                .call()
                .content();
    }
}