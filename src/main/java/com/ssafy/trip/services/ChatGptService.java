package com.ssafy.trip.services;

import com.ssafy.trip.config.ChatGptConfig;
import com.ssafy.trip.interfaces.rest.dto.ChatGptResponseDto;
import com.ssafy.trip.interfaces.rest.dto.ChatGptRequestDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGptService {

    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
            ChatGptConfig.URL,
            chatGptRequestDtoHttpEntity,
            ChatGptResponseDto.class);
        return responseEntity.getBody();
    }

    public ChatGptResponseDto askQuestion(String prompt) {
        return this.getResponse(
            this.buildHttpEntity(
                new ChatGptRequestDto(
                    ChatGptConfig.MODEL,
                    prompt,
                    ChatGptConfig.MAX_TOKEN,
                    ChatGptConfig.TEMPERATURE,
                    ChatGptConfig.TOP_P
                )
            )
        );
    }
}