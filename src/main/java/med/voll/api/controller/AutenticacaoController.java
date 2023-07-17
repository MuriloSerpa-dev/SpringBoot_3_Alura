package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutentiacacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutentiacacao dados){
        var autheticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); // UsernamePasswordAuthenticationToken é como se fosse um DTO do proprio spring
       var authentication =  manager.authenticate(autheticationToken); // Este método devolve o objeto que representa o usuário autenticado no sistema.

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal()); // gera token com informações do usuario

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

}
