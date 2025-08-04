package com.example.backend.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.nio.charset.StandardCharsets;

@Component
public class JwtTokenProvider {

    // 토큰의 서명에 사용할 시크릿 키
    // application.properties에 spring.jwt.secret=... 으로 설정
    private final Key key;

    // 토큰 만료 시간 (1시간)
    @Getter
    private final long tokenValidityInMilliseconds = 3600000;

    public JwtTokenProvider(@Value("${spring.jwt.secret}") String secretKey) {
        // 시크릿 키를 Base64로 인코딩하여 안전하게 사용
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 사용자 아이디로 JWT 토큰을 생성하는 메서드
     *
     * @param userId 토큰에 담을 사용자 아이디
     * @return 생성된 JWT 토큰 문자열
     */
    public String generateToken(String userId) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(userId) // 토큰의 주체(subject)로 사용자 아이디 설정
                .setIssuedAt(now) // 토큰 발행 시간
                .setExpiration(validity) // 토큰 만료 시간
                .signWith(key, SignatureAlgorithm.HS512) // 서명 알고리즘과 키 설정
                .compact(); // 토큰 생성
    }

    /**
     * JWT 토큰의 유효성을 검증하는 메서드
     *
     * @param token 검증할 JWT 토큰
     * @return 유효하면 true, 아니면 false
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // 토큰 파싱 또는 서명 검증 실패 시 예외 처리
            return false;
        }
    }

    /**
     * JWT 토큰에서 사용자 아이디(subject)를 추출하는 메서드
     *
     * @param token 사용자 아이디를 추출할 JWT 토큰
     * @return 토큰에 담긴 사용자 아이디
     */
    public String getUserIdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
