package cl.josbla.sandwichplanet.security.client.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CorsGlobalConfiguration corsGlobalConfiguration;

    SecurityConfig(CorsGlobalConfiguration corsGlobalConfiguration) {
        this.corsGlobalConfiguration = corsGlobalConfiguration;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain publicFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/api/pagos/**") // Aquí puedes añadir más rutas públicas si deseas
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    @Order(2)
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authHttp -> authHttp
            // Rutas anteriores
            .requestMatchers(HttpMethod.GET, "/authorized").permitAll()
            .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/api/sandwiches/resumen"
                ).permitAll()
            .requestMatchers(HttpMethod.GET, "/list").hasAnyAuthority("SCOPE_read", "SCOPE_write")
            .requestMatchers(HttpMethod.POST, "/create").hasAuthority("SCOPE_write")

            // Nuevas rutas para /api/sandwiches
            .requestMatchers(HttpMethod.GET, "/api/sandwiches").hasAnyAuthority("SCOPE_read", "SCOPE_write")
            .requestMatchers(HttpMethod.POST, "/api/sandwiches").hasAuthority("SCOPE_write")

            .anyRequest().authenticated())
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .oauth2Client(Customizer.withDefaults())
            .oauth2ResourceServer(resourceServer -> resourceServer.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
