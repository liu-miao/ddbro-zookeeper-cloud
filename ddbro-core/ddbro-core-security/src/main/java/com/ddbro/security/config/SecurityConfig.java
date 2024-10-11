package com.ddbro.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //写入配置中
    private static final String[] ignoreList = {"/error", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/doc.html"};

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        //此处可添加别的规则,目前只设置 允许双 //
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()//暂时全部放开，集成完毕后取消
                )
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptions -> exceptions
                                .withObjectPostProcessor(new ObjectPostProcessor<ExceptionTranslationFilter>() {
                                    @Override
                                    public <O extends ExceptionTranslationFilter> O postProcess(O filter) {

                                        return filter;
                                    }
                                })
                );


        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 设置这些不需要授权，考虑写进配置
     *
     * @return
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                // 忽略 /error 页面及swagger相关
                .requestMatchers(ignoreList)
                // 忽略常见的静态资源路径
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    AuthenticationSuccessHandler successHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler();
    }

    @Bean
    AuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login?error");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
}
