package com.ai.springai;

import org.junit.jupiter.api.Test;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = SpringAiApplication.class)
public class TestOllama {

        @Autowired
        private OllamaChatModel ollamaChatModel;

        @Test
        public void testChatModel() {
            String prompt = "请将以下英文翻译成中文：";
            String message = "Ollama now supports tool calling with popular models such as Llama 3.1.";

            String result = ollamaChatModel.call(prompt + " " + message);
            System.out.println(result);
        }

}
