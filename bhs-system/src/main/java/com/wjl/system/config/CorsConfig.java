package com.wjl.system.config;


import com.wjl.consts.system.CorsConsts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOriginPattern(CorsConsts.WHITE_MAIN_URL);
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);
        config.addExposedHeader(CorsConsts.EXPOSED_HEADER);
        source.registerCorsConfiguration(CorsConsts.REGISTER_CORS_PATTERN_ALL, config);
        return new CorsFilter(source);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(CorsConsts.REGISTER_CORS_PATTERN_ALL).allowedOrigins(CorsConfiguration.ALL)
            .allowedMethods("GET", "POST", "DELETE", "PUT").allowedHeaders("*").maxAge(3600);
    }
}
