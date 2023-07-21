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
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutentiacacao dados) {

            var autheticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); // UsernamePasswordAuthenticationToken é como se fosse um DTO do
        // proprio spring, que recebe os dados de login e senha
            var authentication = manager.authenticate(autheticationToken); // Este método devolve o objeto que representa o usuário autenticado no sistema, que dispara a
        // autenticação, de login

            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal()); // metodo que gera o token ele recebe os dados da classe authentication que dentro dela
        //tem os dados do usuario, como esse metodo devolve um object  fazemos o casting para Usuario, com isso ele gera o Token

            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

        }
    }

