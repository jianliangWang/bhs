package com.wjl.system.config;

import com.wjl.system.security.CaptchaFilter;
import com.wjl.system.security.JwtAccessDeniedHandler;
import com.wjl.system.security.JwtAuthenticationEntryPoint;
import com.wjl.system.security.JwtAuthenticationFilter;
import com.wjl.system.security.JwtLogoutSuccessHandler;
import com.wjl.system.security.LoginFailureHandler;
import com.wjl.system.security.LoginSuccessHandler;
import com.wjl.system.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final String[] URL_WHITELIST = {"/login", "/logout", "/captcha", "/favicon.ico", "/swagger-ui"
        + "/**", "/swagger-resources/**", "/v2/api-docs", "/v3/api-docs/**"};

    private final CaptchaFilter captchaFilter;

    private final LoginFailureHandler loginFailureHandler;

    private final LoginSuccessHandler loginSuccessHandler;

    private final JwtAccessDeniedHandler accessDeniedHandler;

    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    private final JwtLogoutSuccessHandler logoutSuccessHandler;

    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(CaptchaFilter captchaFilter,
        LoginFailureHandler loginFailureHandler, LoginSuccessHandler loginSuccessHandler,
        JwtAccessDeniedHandler accessDeniedHandler, JwtAuthenticationEntryPoint authenticationEntryPoint,
        JwtLogoutSuccessHandler logoutSuccessHandler, AuthenticationConfiguration authenticationConfiguration) {
        this.captchaFilter = captchaFilter;
        this.loginFailureHandler = loginFailureHandler;
        this.loginSuccessHandler = loginSuccessHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*允许跨域*/
        return http.cors()
            .and().csrf().disable()
            /*登录*/
            .formLogin()
            .successHandler(loginSuccessHandler)
            .failureHandler(loginFailureHandler)

            /*退出*/
            .and()
            .logout()
            .logoutSuccessHandler(logoutSuccessHandler)

            /* 关闭session */
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            /* 认证放行 */
            .and()
            .authorizeRequests()
            .antMatchers(URL_WHITELIST).permitAll()
            .anyRequest().authenticated()
            /* 配置自定义过滤器 */
            .and()
            .addFilter(jwtAuthenticationFilter())
            .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
            /*异常处理*/
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
            .accessDeniedHandler(accessDeniedHandler).and().build();
    }
}
