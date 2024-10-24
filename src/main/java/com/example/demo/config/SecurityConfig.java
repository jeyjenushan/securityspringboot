package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import com.example.demo.repository.UserRepo;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.example.demo.repository.UserRepo;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //This file we have to change the configuration in the secuiryt rather than
    //default
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(customizer->customizer.disable())//we disable the csrf token given springboot secuirty
                .authorizeHttpRequests(request->request
                        .requestMatchers("register","login")//two lines specify login and register not authenticated
                        .permitAll()
                        .anyRequest().authenticated())//it does not
        //give allow other user to ogin page
                //.formLogin(Customizer.withDefaults())//it create the form but disadvantage is we change the
        //sessionn id it generate every time new form
                .httpBasic(Customizer.withDefaults())//rest api work
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            /*it is tell before add filter*/ .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();




        /*
        we have each method how to work
        Customizer<CsrfConfigurer<HttpSecurity>> customizer=new Customizer<CsrfConfigurer<HttpSecurity>>() {
    @Override
    public void customize(CsrfConfigurer<HttpSecurity> customizer) {
customizer.disable();
    }
}
       http.csrf(customizer)
         */


    }
    //we have to use the database it is must authentication provider we have to change the authentication provider that is muts
    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


    //JWT TOKEN WE DO THE SECOND STEP THAT MEANS AFTER DOWNLOADING DEPENDENCIES
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }



}
//it is ony for not in the dtabase
// @Bean
   /* public UserDetailsService userDetailsService(){
        //without database we create own user
        UserDetails user1= User
                .withDefaultPasswordEncoder()
                .username("Jenushan")
                .password("K@123")
                .roles("USER")
                .build();
        UserDetails user2=User
                .withDefaultPasswordEncoder()
                .username("Harish")
                .password("J@123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }*/




