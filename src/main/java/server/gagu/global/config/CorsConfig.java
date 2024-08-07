package server.gagu.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        // CORS 설정
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*"); // 필요에 따라 수정
        configuration.addAllowedOrigin("http://localhost:3000"); // 필요에 따라 수정
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        // 모든 경로에 대해 CORS 설정 적용
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}
