package server.gagu.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Value("${myapp.api-url}")
    private String apiUrl;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("http://localhost:3000"); // 정해진 도메인만 허용
        configuration.addAllowedOrigin("http://localhost:5000");
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedOrigin(apiUrl); // 환경변수에 지정된 URL 사용
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }
}
