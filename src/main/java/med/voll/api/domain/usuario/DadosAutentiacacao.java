package med.voll.api.domain.usuario;

import com.auth0.jwt.JWT;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

public record DadosAutentiacacao(String login, String senha) {
}
