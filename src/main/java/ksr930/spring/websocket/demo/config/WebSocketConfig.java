package ksr930.spring.websocket.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 인가(접근권한) 설정
        http.authorizeHttpRequests().antMatchers("/").permitAll();
        http.authorizeHttpRequests().antMatchers("/admin").hasRole("ADMIN");
        http.authorizeHttpRequests().antMatchers("/member/**").hasAnyRole("ADMIN", "MEMBER");
        http.authorizeHttpRequests().antMatchers("/user2/loginSuccess").hasAnyRole("3", "4", "5");

        // 사이트 위변조 요청 방지
        http.csrf().disable();

        return http.build();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .setAllowedOrigins("http://localhost:8080")
                .withSockJS();

    }

}