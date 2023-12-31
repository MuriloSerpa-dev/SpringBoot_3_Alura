package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Indicar ao Spring que vamos personalizar alguma configurações
public class SecurityConfigurations {
    @Autowired
    private SecurityFilter securityFilter;
    @Bean //Anotação serve para expor o retorno do metodo, para devolver o objeto para o spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // metodo para configurar como sera feita a cadeia de filtros controle de acesso
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/login").permitAll(); // Aqui estou dizendo para o spring que eu posso disparar essa requisição que
                    // ela sera permitida, sem autenticação
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                    req.anyRequest().authenticated(); // qualquer outra a não ser de login sera bloqueada
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build(); // configurando a ordem que eu quero que os filtros vão funcionar

} // Metodo para mudar a configuração de segurança,desabilitando o bloqueio padrao mudando o prosseso de autenticação para Stateless
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    } // metodo para criar o objeto Authentication manager, ensina para o spring a injetar um objeto então ele necessita da anotação @Bean
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // Metodo para ensinar o spring que é para usar esse Hashi de senha
    }
}
