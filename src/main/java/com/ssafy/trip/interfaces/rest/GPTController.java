package com.ssafy.trip.interfaces.rest;

import static com.ssafy.trip.web.ApiResult.succeed;

import com.ssafy.trip.interfaces.rest.dto.QuestionRequestDto;
import com.ssafy.trip.services.ChatGptService;
import com.ssafy.trip.web.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/chat-gpt")
public class GPTController {

    @Autowired
    private ChatGptService chatGptService;

    @PostMapping("/question")
    public ApiResult<ChatGptResponseDto> sendQuestion(@RequestBody QuestionRequestDto requestDto) {
         String prompt = "what is an ideal itinerary for "+requestDto.getDays()+" days in "+requestDto.getCity()+"return format is html. 한국어로 말해줘";
        return succeed(chatGptService.askQuestion(prompt));
    }
}