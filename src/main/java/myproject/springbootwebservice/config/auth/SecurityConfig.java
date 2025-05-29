package myproject.springbootwebservice.config.auth;

import lombok.RequiredArgsConstructor;
import myproject.springbootwebservice.domain.user.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity  // 웹 보안 활성화
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean // SecurityFilterChain을 빈으로 등록
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // 람다식 DSL 사용 (권장)
                .headers(headers -> headers // 람다식 DSL 사용 (권장)
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable) // X-Frame-Options 비활성화
                )
                .authorizeHttpRequests(authorize -> authorize // authorizeRequests() 대신 authorizeHttpRequests() 사용
                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() // antMatchers() 대신 requestMatchers() 사용
                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name()) // antMatchers() 대신 requestMatchers() 사용
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )
                .logout(logout -> logout // 람다식 DSL 사용 (권장)
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 리다이렉트 URL
                )
                .oauth2Login(oauth2 -> oauth2 // 람다식 DSL 사용 (권장)
                        .userInfoEndpoint(userInfo -> userInfo // userInfoEndpoint 설정
                                .userService(customOAuth2UserService) // 커스텀 OAuth2 사용자 서비스 지정
                        )
                );

        return http.build(); // SecurityFilterChain 객체 반환
    }
}
