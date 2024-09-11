package bookstudy.posting.config;

import bookstudy.posting.config.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHENTICATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HEADER_AUTHENTICATION); // 요청 헤더의 Authorization 키의 값을 조회
        String token = getAccessToken(authHeader); // 토큰의 접두사(Bearer)를 제외한 값을 얻음

        if (tokenProvider.validateToken(token)) { // 토큰의 유효성 검사
            Authentication authentication = tokenProvider.getAuthentication(token); // 인증 정보 가져오기
            SecurityContextHolder.getContext().setAuthentication(authentication); // 인증 정보 설정
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) { // 요청 헤더가 null이거나 Bearer로 시작하지 않으면 null반환
            return authHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
