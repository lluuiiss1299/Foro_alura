package com.alura_curso.foro_alura.configuracion;
import com.alura_curso.foro_alura.security.jwt.ConfiguracionJWT;
import com.alura_curso.foro_alura.security.jwt.ProveedorDeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SeguridadWebConfiguracion {
    @Value("${api.security.secret}")
    private String jwtSecreto;
    @Value("${api.security.validity-in-seconds}")
    private Long jwtValidacionEnSegundos;

    @Bean
     SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
         ConfiguracionJWT configuracionJWT = new ConfiguracionJWT(proveedorDeToken());

         http
                 .cors(Customizer.withDefaults())
                 .csrf(AbstractHttpConfigurer::disable)
                 .authorizeHttpRequests(
                         a->a
                                 .requestMatchers("/api/admin/**")
                                 .hasRole("ADMIN")
                                 .anyRequest()
                                 .permitAll()
                 )
                 .sessionManagement(h->h.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .apply(configuracionJWT);

         return http.build();
     }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

     @Bean
    public ProveedorDeToken proveedorDeToken() {
         return new ProveedorDeToken(jwtSecreto, jwtValidacionEnSegundos);
    }

}
