package com.nagendra.springsection1.config;


import com.nagendra.springsection1.exceptionHandling.CustomAccessDeniedHandler;
import com.nagendra.springsection1.exceptionHandling.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("!prod")
public class ProjectSecurityConfig {

    @Bean

    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        //http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.
                //requiresChannel(rcc-> rcc.anyRequest().requiresInsecure()) // only HTTP --> this is not supported so i disable it
                csrf(csrfConfig->csrfConfig.disable())     // when deleting or register spring security is using the bydefault csrf protection. so here we disable the csrf for regsiter
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount","/myBalance","/myCard","/loans").authenticated()
                .requestMatchers("/notices","/contact","/error","/register").permitAll() );

        http.formLogin(withDefaults());
        http.httpBasic(hbc->hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc->ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }

//    @Bean
//   public UserDetailsService userDetailsService(DataSource dataSource){
////       UserDetails user = User.withUsername("nagendra").password("{noop}nagendra@987").authorities("read").build();
////       UserDetails admin = User.withUsername("priyanka").password("$2a$12$LbQIq.P.XP7i.RFyA28HF.YAVX.PF1UNmPP5oASGbY3lxfmgFzB4i").authorities("admin").build();
////       return new InMemoryUserDetailsManager(user,admin);
//
//        return  new JdbcUserDetailsManager(dataSource);
 //  }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

}
