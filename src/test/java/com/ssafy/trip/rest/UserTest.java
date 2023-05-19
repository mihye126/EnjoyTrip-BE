package com.ssafy.trip.rest;

import static com.ssafy.trip.utils.JsonUtils.toJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.javafaker.Faker;
import com.ssafy.trip.mapper.UserMapper;
import java.util.HashMap;
import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
@DisplayName("USER 통합 테스트")
public class UserTest {

    private static final String DEFAULT_HEADER = "X-AUTH-TOKEN";

    private Faker faker = new Faker(new Locale("ko"));

    private MockMvc mockMvc;
    @Autowired
    UserMapper mapper;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("[인증 토큰 테스트] 인증 토큰이 만료되었다면 API 호출을 실패한다.")
    void _03_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/login/token")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDEsCiAgImV4cGlyZSI6IDE1NTAwMDAwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("keyword", "");
                        }}
                    )
                )
        );
        result.andDo(print())
            .andExpect(status().isForbidden())
        ;
    }

    @Test
    @DisplayName("[로그인 테스트] 비밀번호 입력이 올바르지 않으면 로그인을 실패한다.")
    void _05_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/users/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("username", "tester01");
                            put("password", "2222");
                        }}
                    )
                )
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").doesNotExist())
            .andExpect(jsonPath("$.error").exists())
        ;
    }

    @Test
    @DisplayName("[로그인 테스트] 비밀번호 입력이 올바르지 않으면 로그인을 실패한다.")
    void _06_test() throws Exception {
        System.out.println(mapper.findByEmail("ssafy@email.com"));
    }
}
