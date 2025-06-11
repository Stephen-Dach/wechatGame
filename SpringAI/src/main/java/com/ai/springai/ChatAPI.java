package com.ai.springai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ChatAPI {

    final ChatClient chatClient;

    @PostMapping("/chat")
    public String chat(@RequestParam("msg") String msg) {

        return chatClient.prompt().user(msg).call().content();
    }

    @GetMapping("/testGet")
    public String testGet() {
        return "testGet";
    }
}
