package com.ssafy.trip.rest;

import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import com.ssafy.trip.models.LoginUser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.ssafy.trip.utils.JsonUtils.fromJson;
import static com.ssafy.trip.utils.JsonUtils.toJson;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
@DisplayName("API 통합 테스트")
public class TripAPIsTest {

    private static final String DEFAULT_HEADER = "X-AUTH-TOKEN";

    private Faker faker = new Faker(new Locale("ko"));

    private MockMvc mockMvc;

    @Autowired
    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("[인증 토큰 테스트] 인증 토큰이 없다면 API 호출을 실패한다.")
    void _01_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/addresses")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("name", faker.name().name());
                            put("phoneNumber", "123");
                        }}
                    )
                )
        );
        result.andDo(print())
            .andExpect(status().isForbidden())
        ;
    }

    @Test
    @DisplayName("[인증 토큰 테스트] 인증 토큰이 올바르지 않다면 API 호출을 실패한다.")
    void _02_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/addresses/merge")
                .header(DEFAULT_HEADER, "This is token!")
                .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
            .andExpect(status().isForbidden())
        ;
    }

    @Test
    @DisplayName("[인증 토큰 테스트] 인증 토큰이 만료되었다면 API 호출을 실패한다.")
    void _03_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/addresses/search")
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
    @DisplayName("[로그인 테스트] 로그인 ID 입력이 올바르지 않으면 로그인을 실패한다.")
    void _04_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/users/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("username", "unknown");
                            put("password", "1111");
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
    @DisplayName("[로그인 테스트] 로그인 ID, 비밀번호 입력이 올바르면 로그인을 성공한다.")
    void _06_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/users/login")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("username", "tester01");
                            put("password", "1111");
                        }}
                    )
                )
        );
        MvcResult apiResult = result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.token").isString())
            .andExpect(jsonPath("$.data.expire").isNumber())
            .andExpect(jsonPath("$.error").doesNotExist())
            .andReturn();

        String responseJson = apiResult.getResponse().getContentAsString();
        String token = JsonPath.read(responseJson, "$.data.token");
        Long expire = JsonPath.read(responseJson, "$.data.expire");
        assertThat(token).isNotNull();
        assertThat(expire).isNotNull();

        String decodedToken = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
        LoginUser loginUser = fromJson(decodedToken, LoginUser.class);
        // $.data.expire 값과 currentUser.getExpire() 값은 같아야 함
        assertThat(expire).isEqualTo(loginUser.getExpire());
        // 토큰은 현재 시간으로부터 1시간 후 만료되야 함
        assertThat(expire).isLessThanOrEqualTo(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1));
    }

    @Test
    @DisplayName("[연락처 추가 테스트] 연락처 길이가 너무 짧으면 연락처 추가를 실패한다. (username: tester01)")
    void _07_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/addresses")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDEsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("name", faker.name().name());
                            put("phoneNumber", "12");
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
    @DisplayName("[연락처 추가 테스트] 연락처 형식이 잘못된 경우 연락처 추가를 실패한다. (username: tester01)")
    void _08_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/addresses")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDEsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("name", faker.name().name());
                            put("phoneNumber", "010a");
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
    @DisplayName("[연락처 추가 테스트] 모든 입력이 올바른 경우 연락처 추가를 성공한다. (username: tester01)")
    void _09_test() throws Exception {
        final String name = faker.name().name();
        final String phoneNumber = faker.phoneNumber().phoneNumber().replaceAll("-", "");
        ResultActions result = mockMvc.perform(
            post("/api/addresses")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDEsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("name", name);
                            put("phoneNumber", phoneNumber);
                        }}
                    )
                )
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.id").isNumber())
            .andExpect(jsonPath("$.data.name", is(name)))
            .andExpect(jsonPath("$.data.phoneNumber", is(phoneNumber)))
            .andExpect(jsonPath("$.data.modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data.createdAt").isNotEmpty())
            .andExpect(jsonPath("$.error").doesNotExist())
        ;
    }

    @Test
    @DisplayName("[연락처 수정 테스트] 연락처명 입력이 올바르지 않으면 연락처 수정을 실패한다. (username: tester01)")
    void _10_test() throws Exception {
        ResultActions result = mockMvc.perform(
            put("/api/addresses/1")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDEsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("name", "");
                            put("phoneNumber", "123");
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
    @DisplayName("[연락처 수정 테스트] 연락처 길이가 너무 짧으면 연락처 수정을 실패한다. (username: tester01)")
    void _11_test() throws Exception {
        ResultActions result = mockMvc.perform(
            put("/api/addresses/1")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDEsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("name", faker.name().name());
                            put("phoneNumber", "12");
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
    @DisplayName("[연락처 수정 테스트] 연락처 형식이 잘못된 경우 연락처 수정을 실패한다. (username: tester01)")
    void _12_test() throws Exception {
        ResultActions result = mockMvc.perform(
            put("/api/addresses/2")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDEsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("name", faker.name().name());
                            put("phoneNumber", "010a");
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
    @DisplayName("[연락처 수정 테스트] 다른 사용자(tester02) 의 연락처를 수정을 실패한다 (username: tester01)")
    void _13_test() throws Exception {
        ResultActions result = mockMvc.perform(
            put("/api/addresses/4")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDEsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("name", faker.name().name());
                            put("phoneNumber", "123");
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
    @DisplayName("[연락처 수정 테스트] 모든 입력이 올바른 경우 연락처 수정을 성공한다. (username: tester01)")
    void _14_test() throws Exception {
        final String name = faker.name().name();
        final String phoneNumber = faker.phoneNumber().phoneNumber().replaceAll("-", "");
        ResultActions result = mockMvc.perform(
            put("/api/addresses/1")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDEsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("name", name);
                            put("phoneNumber", phoneNumber);
                        }}
                    )
                )
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.id", is(1)))
            .andExpect(jsonPath("$.data.name", is(name)))
            .andExpect(jsonPath("$.data.phoneNumber", is(phoneNumber)))
            .andExpect(jsonPath("$.data.modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data.createdAt").isNotEmpty())
            .andExpect(jsonPath("$.error").doesNotExist())
        ;
    }


    @Test
    @DisplayName("[연락처 검색 테스트] 검색 키워드에 '민'이 입력된 경우 1개 연락처를 출력한다. (username: tester04)")
    void _15_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/addresses/search")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDQsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("keyword", "민");
                        }}
                    )
                )
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data.length()", is(1)))
            .andExpect(jsonPath("$.data[0].id", is(10)))
            .andExpect(jsonPath("$.data[0].name", is("철민")))
            .andExpect(jsonPath("$.data[0].phoneNumber", is("01088889999")))
            .andExpect(jsonPath("$.data[0].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[0].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.error").doesNotExist())
        ;
    }

    @Test
    @DisplayName("[연락처 검색 테스트] 검색 키워드에 '철'이 입력된 경우 2개 연락처를 출력한다. (username: tester04)")
    void _16_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/addresses/search")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDQsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("keyword", "철");
                        }}
                    )
                )
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data.length()", is(2)))
            .andExpect(jsonPath("$.data[0].id", is(11)))
            .andExpect(jsonPath("$.data[0].name", is("동철")))
            .andExpect(jsonPath("$.data[0].phoneNumber", is("01099998888")))
            .andExpect(jsonPath("$.data[0].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[0].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.data[1].id", is(10)))
            .andExpect(jsonPath("$.data[1].name", is("철민")))
            .andExpect(jsonPath("$.data[1].phoneNumber", is("01088889999")))
            .andExpect(jsonPath("$.data[1].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[1].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.error").doesNotExist())
        ;
    }

    @Test
    @DisplayName("[연락처 검색 테스트] 검색 키워드에 `empty string`이 입력된 경우 모든 연락처를 출력한다. (username: tester03)")
    void _17_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/addresses/search")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDMsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
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
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data.length()", is(3)))
            .andExpect(jsonPath("$.data[0].id", is(9)))
            .andExpect(jsonPath("$.data[0].name", is("현수")))
            .andExpect(jsonPath("$.data[0].phoneNumber", is("01077778890")))
            .andExpect(jsonPath("$.data[0].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[0].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.data[1].id", is(8)))
            .andExpect(jsonPath("$.data[1].name", is("현수")))
            .andExpect(jsonPath("$.data[1].phoneNumber", is("01077778889")))
            .andExpect(jsonPath("$.data[1].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[1].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.data[2].id", is(7)))
            .andExpect(jsonPath("$.data[2].name", is("현수")))
            .andExpect(jsonPath("$.data[2].phoneNumber", is("01077778888")))
            .andExpect(jsonPath("$.data[2].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[2].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.error").doesNotExist())
        ;
    }

    @Test
    @DisplayName("[연락처 검색 테스트] 검색 키워드에 `null`이 입력된 경우 모든 연락처를 출력한다. (username: tester03)")
    void _18_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/addresses/search")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDMsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("keyword", null);
                        }}
                    )
                )
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data.length()", is(3)))
            .andExpect(jsonPath("$.data[0].id", is(9)))
            .andExpect(jsonPath("$.data[0].name", is("현수")))
            .andExpect(jsonPath("$.data[0].phoneNumber", is("01077778890")))
            .andExpect(jsonPath("$.data[0].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[0].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.data[1].id", is(8)))
            .andExpect(jsonPath("$.data[1].name", is("현수")))
            .andExpect(jsonPath("$.data[1].phoneNumber", is("01077778889")))
            .andExpect(jsonPath("$.data[1].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[1].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.data[2].id", is(7)))
            .andExpect(jsonPath("$.data[2].name", is("현수")))
            .andExpect(jsonPath("$.data[2].phoneNumber", is("01077778888")))
            .andExpect(jsonPath("$.data[2].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[2].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.error").doesNotExist())
        ;
    }

    @Test
    @DisplayName("[연락처 병합 테스트] tester02 연락처 병합 테스트")
    void _19_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/addresses/merge")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDIsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.count", is(2)))
            .andExpect(jsonPath("$.error").doesNotExist())
        ;
        // 병합 후 전체 연락처 목록 조회
        result = mockMvc.perform(
            post("/api/addresses/search")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDIsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("keyword", null);
                        }}
                    )
                )
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data.length()", is(1)))
            .andExpect(jsonPath("$.data[0].id", is(4)))
            .andExpect(jsonPath("$.data[0].name", is("영희")))
            .andExpect(jsonPath("$.data[0].phoneNumber", is("01066667777")))
            .andExpect(jsonPath("$.data[0].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[0].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.error").doesNotExist())
        ;
    }

    @Test
    @DisplayName("[연락처 병합 테스트] tester03 연락처 병합 테스트")
    void _20_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/addresses/merge")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDMsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.count", is(2)))
            .andExpect(jsonPath("$.error").doesNotExist())
        ;
        // 병합 후 전체 연락처 목록 조회
        result = mockMvc.perform(
            post("/api/addresses/search")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDMsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("keyword", null);
                        }}
                    )
                )
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data.length()", is(1)))
            .andExpect(jsonPath("$.data[0].id", is(9)))
            .andExpect(jsonPath("$.data[0].name", is("현수")))
            .andExpect(jsonPath("$.data[0].phoneNumber", is("01077778890")))
            .andExpect(jsonPath("$.data[0].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[0].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.error").doesNotExist())
        ;
    }

    @Test
    @DisplayName("[연락처 병합 테스트] tester04 연락처 병합 테스트")
    void _21_test() throws Exception {
        ResultActions result = mockMvc.perform(
            post("/api/addresses/merge")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDQsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data.count", is(0)))
            .andExpect(jsonPath("$.error").doesNotExist())
        ;
        // 병합 후 전체 연락처 목록 조회
        result = mockMvc.perform(
            post("/api/addresses/search")
                .header(DEFAULT_HEADER, "ewogICJpZCI6IDQsCiAgImV4cGlyZSI6IDE2NzI0ODgwMDAwMDAKfQ==")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    toJson(
                        new HashMap<String, Object>() {{
                            put("keyword", null);
                        }}
                    )
                )
        );
        result.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").isArray())
            .andExpect(jsonPath("$.data.length()", is(2)))
            .andExpect(jsonPath("$.data[0].id", is(11)))
            .andExpect(jsonPath("$.data[0].name", is("동철")))
            .andExpect(jsonPath("$.data[0].phoneNumber", is("01099998888")))
            .andExpect(jsonPath("$.data[0].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[0].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.data[1].id", is(10)))
            .andExpect(jsonPath("$.data[1].name", is("철민")))
            .andExpect(jsonPath("$.data[1].phoneNumber", is("01088889999")))
            .andExpect(jsonPath("$.data[1].modifiedAt").isNotEmpty())
            .andExpect(jsonPath("$.data[1].createdAt").isNotEmpty())
            .andExpect(jsonPath("$.error").doesNotExist())
        ;
    }

}
