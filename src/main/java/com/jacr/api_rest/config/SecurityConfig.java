package com.jacr.api_rest.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


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

    /**
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(http -> {
                    //Si se hace una petición de tipo GET al siguiente endpint con permitAll significa
                    //que va a permitir el acceso a todos los que quiera aceder a este desde un metodo get
                    http.requestMatchers(HttpMethod.GET,"/presentation").permitAll();

                    //Si se hace una petición de tipo GET al siguiente endpoint con hasAuthority tiene que
                    //tener una autorización y la autorización la definimos en userDetailsServices
                    http.requestMatchers(HttpMethod.GET, "/api/books").hasAuthority("READ");
                    //Cualquier otro request diferente al que está especificado lo anterior vas
                    // a denegar el acceso tambien se utiliza el metodo authenticated()
                    http.anyRequest().denyAll();

                })

                .build();
    }
    **/

    //SEGUNDA OPCION CON ANOTACINES PARA CREAR COMPONENTE SECURITYFILTERCHAIN LAS ANOTACIONES ES EN CONTROLLER
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
    /*Crear el compnente Autentication manager, donde administra la autenticación*/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //crear el componente un provededor de autenticación que use los usarios de la base de datos
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    /*
     * Emular usuarios de que viene de bases de datos por lo cual se guardará en memoria
     * para hacer pruebas spring security
     * definir usuarios con user y password
     */


   /*
    @Bean
    public UserDetailsService userDetailsService(){

        //crear un solo usuario
        * UserDetails userDetails = User.withUsername("juan")
                .password("GaTo2850")
                .roles("ADMIN")
                .authorities("READ","CREATE","UPDATE")
                .build();
        **
        //lista de usuarios
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(User.withUsername("juan")
                .password("GaTo2850")
                .roles("ADMIN")
                .authorities("READ","CREATE","UPDATE","DELETE")
                .build());
        userDetailsList.add(User.withUsername("santiago")
                .password("2850")
                .roles("USER")
                .authorities("READ")
                .build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }
    */

    //crear el componente passwordEncoder que asegura que no almacen contraseñas en texto plano
    @Bean
    public PasswordEncoder passwordEncoder(){
        //NoOpPasswordEncoder no codifica, no encripta, solo es para pruebas, no para produccion en
        //en producción se debe codificar las contraseñas, para encriptar ponermos BCryptPasswordEncoder();
        //return  NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }
}
