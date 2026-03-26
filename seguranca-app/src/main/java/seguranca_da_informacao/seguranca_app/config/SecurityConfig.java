package seguranca_da_informacao.seguranca_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import seguranca_da_informacao.seguranca_app.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .userDetailsService(userDetailsService) // busco usuários no banco de dados

                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                ) // impeço que outro site faça requisições no lugar do usuário

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/cadastro").hasRole("ADMIN")
                        .requestMatchers("/administradores").hasRole("ADMIN")
                        .requestMatchers("/usuarios").hasRole("USER")
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/redirect", true)
                        .permitAll()
                ) // configuro autenticação com formulário customizado e redirecionamento conforme perfil do usuário

                // limpo a sessão
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true) // removo sessão
                        .deleteCookies("JSESSIONID") // removo cookies
                )

                // proteção de sessão
                // troco ID da sessão após login e evito múltiplas sessões
                .sessionManagement(session -> session
                        .sessionFixation().migrateSession()
                        .maximumSessions(1) // só um login por usuário
                )

                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/acesso-negado")
                ); // tratamento de erro com tela amigável


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
