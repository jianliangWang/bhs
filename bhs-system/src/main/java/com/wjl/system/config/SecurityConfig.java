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
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] URL_WHITELIST = {"/login", "/logout", "/captcha", "/favicon.ico", "/swagger-ui"
        + "/**", "/swagger-resources/**", "/v2/api-docs", "/v3/api-docs/**"};

    private final CaptchaFilter captchaFilter;

    private final LoginFailureHandler loginFailureHandler;

    private final LoginSuccessHandler loginSuccessHandler;

    private final JwtAccessDeniedHandler accessDeniedHandler;

    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtLogoutSuccessHandler logoutSuccessHandler;

    public SecurityConfig(CaptchaFilter captchaFilter,
        LoginFailureHandler loginFailureHandler, LoginSuccessHandler loginSuccessHandler,
        JwtAccessDeniedHandler accessDeniedHandler, JwtAuthenticationEntryPoint authenticationEntryPoint,
        UserDetailsServiceImpl userDetailsService, JwtLogoutSuccessHandler logoutSuccessHandler) {
        this.captchaFilter = captchaFilter;
        this.loginFailureHandler = loginFailureHandler;
        this.loginSuccessHandler = loginSuccessHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userDetailsService = userDetailsService;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*????????????*/
        http.cors()
            .and().csrf().disable()
            /*??????*/
            .formLogin()
            .successHandler(loginSuccessHandler)
            .failureHandler(loginFailureHandler)

            /*??????*/
            .and()
            .logout()
            .logoutSuccessHandler(logoutSuccessHandler)

            /* ??????session */
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            /* ???????????? */
            .and()
            .authorizeRequests()
            .antMatchers(URL_WHITELIST).permitAll()
            .anyRequest().authenticated()
            /* ???????????????????????? */
            .and()
            .addFilter(jwtAuthenticationFilter())
            .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
            /*????????????*/
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
            .accessDeniedHandler(accessDeniedHandler);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
