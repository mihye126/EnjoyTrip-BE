package com.ssafy.trip.auth.infrastructure;

import static com.ssafy.trip.utils.JsonUtils.*;

import com.ssafy.trip.exception.UnAuthorizedException;
import io.jsonwebtoken.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public static final int REFRESH=2;
    public static final int ACCESS=1;

    public static final long REFRESH_TOKEN_EXPIRE_MINUTES = 1000 * 60 * 60 * 24 * 7 *REFRESH; //주단위
    public static final long ACCESS_TOKEN_EXPIRE_MINUTES = 1000 * 60 * ACCESS;  // 분단위


    public static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);


    private static final String secretKey = "ssafySecret";

    // Signature 설정에 들어갈 key 생성.
    private byte[] generateKey() {
        byte[] key = null;
        try {
            // charset 설정 안하면 사용자 플랫폼의 기본 인코딩 설정으로 인코딩 됨.
            key = secretKey.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            if (logger.isInfoEnabled()) {
                e.printStackTrace();
            } else {
                logger.error("Making JWT Key Error ::: {}", e.getMessage());
            }
        }

        return key;
    }

    public String createToken(String payload,int type,String data) {
        Claims claims = Jwts.claims();
        Date now = new Date();
        Date validity = new Date(System.currentTimeMillis() + (type==ACCESS?ACCESS_TOKEN_EXPIRE_MINUTES:REFRESH_TOKEN_EXPIRE_MINUTES));
        claims.put("data",data);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, generateKey())
                .compact();
    }

    public String getPayload(String token) {
        return Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token).getBody().getSubject();
    }
    public Date getExpire(String token) {
        return Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token).getBody().getExpiration();
    }


    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String parseToken(String token) {

        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(token);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new UnAuthorizedException();

        }
        Map<String, Object> value = claims.getBody();
        logger.info("value : {}", value);
        return toJson(value);
    }
}

