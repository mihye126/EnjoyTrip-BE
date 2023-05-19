package com.ssafy.trip.rest;

import static com.ssafy.trip.utils.JsonUtils.fromJson;
import static com.ssafy.trip.utils.JsonUtils.toJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import com.ssafy.trip.auth.application.AuthService;
import com.ssafy.trip.auth.dto.TokenRequest;
import com.ssafy.trip.auth.infrastructure.JwtTokenProvider;
import com.ssafy.trip.models.LoginUser;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
@DisplayName("Auth 통합 테스트")
@AutoConfigureMockMvc
public class AuthTest {

    private static final String DEFAULT_HEADER = "X-AUTH-TOKEN";

    private Faker faker = new Faker(new Locale("ko"));

    private MockMvc mockMvc;

    private String email="ssafy@email.com";
    private String password="ssafy";

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    AuthService authService;
    @Test
    @DisplayName("[인증 토큰 테스트] 인증토큰 생성 테스트")
    void _01_test() throws Exception {
        JwtTokenProvider jwtTokenProvider=new JwtTokenProvider();
        TokenRequest request=new TokenRequest(email,password);
    }

    @Test
    @DisplayName("[로그인 테스트] 로그인 ID 입력이 올바르지 않으면 로그인을 실패한다.")
    void _04_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/login/token")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("username", email);
                            put("password", password);
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
    @DisplayName("[로그인 테스트] 로그인 ID, 비밀번호 입력이 올바르면 로그인을 성공한다.")
    void _06_test() throws Exception {
        JwtTokenProvider jwtTokenProvider=new JwtTokenProvider();
        ResultActions result = mockMvc.perform(
            post("/login/token")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("email", email);
                            put("password", password);
                        }}
                    )
                )
        );
        MvcResult apiResult = result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.accessToken").isString())
            .andExpect(jsonPath("$.data.expire").isNumber())
            .andExpect(jsonPath("$.error").doesNotExist())
            .andReturn();

        String responseJson = apiResult.getResponse().getContentAsString();
        String token = JsonPath.read(responseJson, "$.data.accessToken");
        Long expire = JsonPath.read(responseJson, "$.data.expire");
        assertThat(token).isNotNull();
        assertThat(expire).isNotNull();
        decodeTokenParts(token);


        String decodedToken = jwtTokenProvider.parseToken(token);
        LoginUser currentToken = fromJson(decodedToken, LoginUser.class);
        // $.data.expire 값과 currentUser.getExpire() 값은 같아야 함
        assertThat(expire).isEqualTo(currentToken.getExpire());
        // 토큰은 현재 시간으로부터 1시간 후 만료되야 함
        assertThat(expire).isLessThanOrEqualTo(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1));
    }

    public static void decodeTokenParts(String token)
    {
        String[] parts = token.split("\\.", 0);

        for (String part : parts) {
            byte[] bytes = Base64.getUrlDecoder().decode(part);
            String decodedString = new String(bytes, StandardCharsets.UTF_8);

            System.out.println("Decoded: " + decodedString);
        }
    }

}
