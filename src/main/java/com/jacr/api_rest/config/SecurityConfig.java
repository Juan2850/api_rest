package com.jacr.api_rest.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

//Anotacion @Configuración especifica que va a ser una clase de configuración
@Configuration

//Seguridad web
@EnableWebSecurity

//Congigurar con anotaciones
@EnableMethodSecurity

public class SecurityConfig {

    /* crear el primer componente Cadena de filtros de seguridad
        (Security Filter Chain)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                /*
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(http -> {
                    http.requestMatchers(HttpMethod.GET,"");
                })*/

                .build();
    }

    /*Crear el compnente Autentication manager, donde administra la autenticación*/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //crear el componente un provededor de autenticación que use los usarios de la base de datos
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    /**
     * Emular usuarios de que viene de bases de datos por lo cual se guardará en memoria
     * para hacer pruebas spring security
     * definir usuarios con user y password
     */
    @Bean
    public UserDetailsService userDetailsService(){
        /*
        //crear un solo usuario
        * UserDetails userDetails = User.withUsername("juan")
                .password("GaTo2850")
                .roles("ADMIN")
                .authorities("READ","CREATE","UPDATE")
                .build();
        * */
        //lista de usuarios
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(User.withUsername("juan")
                .password("GaTo2850")
                .roles("ADMIN")
                .authorities("READ","CREATE","UPDATE")
                .build());
        userDetailsList.add(User.withUsername("santiago")
                .password("2850")
                .roles("USER")
                .authorities("READ")
                .build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }


    //crear el componente passwordEncoder que asegura que no almacen contraseñas en texto plano
    @Bean
    public PasswordEncoder passwordEncoder(){
        //NoOpPasswordEncoder no codifica, no encripta, solo es para pruebas, no para produccion en
        //en producción se debe codificar las contraseñas, para encriptar ponermos BCryptPasswordEncoder();
        return new BCryptPasswordEncoder();
    }
}
