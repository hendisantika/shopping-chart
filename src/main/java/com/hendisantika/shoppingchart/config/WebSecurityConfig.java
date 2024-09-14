package com.hendisantika.shoppingchart.config;

import com.hendisantika.shoppingchart.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by IntelliJ IDEA.
 * Project : shopping-chart
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/07/18
 * Time: 06.45
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

        // Requires login with role ROLE_EMPLOYEE or ROLE_MANAGER.
        // If not, it will redirect to /admin/login.
                .authorizeHttpRequests((req) -> req
                        .requestMatchers("/admin/orderList", "/admin/order", "/admin/accountInfo").permitAll()
                        .requestMatchers("/admin/product").hasRole("ROLE_MANAGER")
                )


        // When user login, role XX.
        // But access to the page requires the YY role,
        // An AccessDeniedException will be thrown.
//        .authorizeRequests().and().exceptionHandling().accessDeniedPage("/403")

        // Configuration for Login Form.
//                // Configuration for the Logout page.
//                // (After logout, go to home page)
//                .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/");

                .formLogin(formLogin -> formLogin
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/admin/login")//
                .defaultSuccessUrl("/admin/accountInfo")//
                .failureUrl("/admin/login?error=true")//
                .usernameParameter("userName")//
                .passwordParameter("password")
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                );
        return http.build();

    }
}
