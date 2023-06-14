package ru.aberezhnoy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    public void authConfig(AuthenticationManagerBuilder authenticationManagerBuilder,
                           UserDetailsService userDetailService,
                           PasswordEncoder passwordEncoder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("mem_user")
                .password(passwordEncoder.encode("user"))
                .roles("SUPER_ADMIN")
                .and()
                .withUser("mem_admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN");

        authenticationManagerBuilder.userDetailsService(userDetailService);
    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                    .httpBasic()
                    .and()
                    .csrf().disable();
        }
    }

    @Configuration
    @Order(2)
    public static class UiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/**/*.css", "/**/*.js").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/**").authenticated()
//                    .antMatchers("/product/**").permitAll()
//                    .antMatchers("/user/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login_processing")
                    .defaultSuccessUrl("/product")
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access_denied");
        }
    }
}
