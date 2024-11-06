//package ru.school_assistant_rest.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfiguration{
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        return httpSecurity.authorizeHttpRequests(requests -> requests
//                        .requestMatchers(HttpMethod.GET).hasAnyAuthority("admin", "user")
//                        .requestMatchers(HttpMethod.POST).hasAuthority("admin")
//                        .requestMatchers(HttpMethod.PUT).hasAuthority("admin"))
//                .rememberMe(rememberMe -> rememberMe.key("uniqieAndSecret"))
//                .logout(logout -> logout.deleteCookies("JSESSIONID"))
//                .build();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}