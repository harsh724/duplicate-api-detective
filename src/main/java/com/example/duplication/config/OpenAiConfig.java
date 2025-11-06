package com.example.duplication.config;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {
    @Bean
    public OpenAIClient openAIClient() {
        // Reads OPENAI_API_KEY from env
        return OpenAIOkHttpClient.fromEnv();
    }
}
